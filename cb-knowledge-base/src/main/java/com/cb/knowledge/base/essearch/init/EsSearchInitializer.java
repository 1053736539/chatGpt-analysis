package com.cb.knowledge.base.essearch.init;

import com.cb.common.config.RuoYiConfig;
import com.cb.knowledge.base.domain.DataCostant;
import com.cb.knowledge.base.domain.entity.KnowledgeBase;
import com.cb.knowledge.base.essearch.FileWatcher;
import com.cb.knowledge.base.essearch.IndexManager;
import com.cb.knowledge.base.essearch.QueryEngine;
import com.cb.knowledge.base.mapper.KnowledgeBaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/5/18 00:52
 * @Copyright (c) 2025
 * @Description 初始化
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EsSearchInitializer implements CommandLineRunner {

    private final KnowledgeBaseMapper knowledgeBaseMapper;

    @Override
    public void run(String... args) throws Exception {
        try (Connection conn = DriverManager.getConnection(DataCostant.SQLITE_DB);
             Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE IF NOT EXISTS documents (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "path TEXT UNIQUE NOT NULL," +
                    "last_modified INTEGER NOT NULL," +
                    "doc_id INTEGER NOT NULL)");

            stmt.execute("CREATE INDEX IF NOT EXISTS idx_doc_id ON documents(doc_id)");
            // 从主库查询增加所有文件的索引
            List<KnowledgeBase> knowledgeBases = knowledgeBaseMapper.selectKnowledgeList(new KnowledgeBase());
            for (KnowledgeBase kb : knowledgeBases) {
                String filePath = kb.getFilePath();
                if (filePath.startsWith("/profile/upload")) {
                    filePath = RuoYiConfig.getUploadPath() + filePath.replaceFirst("/profile/upload", "");
                } else if (filePath.startsWith("/profile/avatar")) {
                    filePath = RuoYiConfig.getAvatarPath() + filePath.replaceFirst("/profile/avatar", "");
                } else {
                    filePath = RuoYiConfig.getOtherAvatarPath() + filePath.replaceFirst("/profile/otherAvatar", "");
                }
                DataCostant.FILE_PATH_ID.put(filePath, kb.getId());
            }
        }
        IndexManager indexManager = new IndexManager(100);
        indexManager.loadExistingIndex();

        FileWatcher watcher = new FileWatcher(String.join(File.separator, RuoYiConfig.getUploadPath(), "knowledgeBase"), 4, indexManager);
        //indexManager.warmUp();
        new Thread(watcher).start();
        QueryEngine.setManager(indexManager);
    }
}
