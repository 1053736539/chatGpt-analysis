package com.cb.knowledge.base.export;

import com.alibaba.fastjson.JSON;
import com.cb.exportSql.component.ExportHandler;
import com.cb.exportSql.component.ExportSql;
import com.cb.exportSql.domain.SqlFile;
import com.cb.knowledge.base.domain.entity.KnowledgeBase;
import com.cb.knowledge.base.mapper.KnowledgeBaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/4/27 09:16
 * @Copyright (c) 2025
 * @Description 知识库数据导出
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class KnowledgeBaseFileExport extends ExportHandler implements ExportSql {

    private final String tableName = "db_knowledge";

    private final KnowledgeBaseMapper knowledgeBaseMapper;

    @Override
    public List<Map<String, Object>> exportMethod(Map<String, Object> params) {
        List<KnowledgeBase> result = knowledgeBaseMapper.exportKnowledgeData(params);
        if (!result.isEmpty()) {
            return result.stream()
                .map(item -> {
                    item.setFileLevel(item.getFileLevelName());
                    item.setFileType(item.getFileTypeName());
                    Map<String, Object> objectMap = JSON.parseObject(JSON.toJSONString(item), Map.class);
                    objectMap.remove("fileLevelName");
                    objectMap.remove("fileTypeName");
                    return objectMap;
                })
                .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public void setSqlFileAndData(SqlFile sqlFile, List<Map<String, Object>> result) {
        sqlFile.setTableName(tableName);
    }

    @Override
    public void createSqliteTableSql(StringBuilder sql) {
        sql.append(tableName).append(" (")
            .append("'id' INTEGER NOT NULL PRIMARY KEY,")
            .append("'fileName' TEXT,")
            .append("'fileType' TEXT,")
            .append("'fileLevel' TEXT,")
            .append("'fileDesc' TEXT,")
            .append("'fileTags' TEXT,")
            .append("'filePath' TEXT,")
            .append("'fileSize' INTEGER,")
            .append("'fileSuffix' TEXT,")
            .append("'fileContent' TEXT,")
            .append("'fileTokenizer' TEXT,")
            .append("'delFlag' TEXT,")
            .append("'remark' TEXT,")
            .append("'createBy' TEXT,")
            .append("'createTime' TEXT,")
            .append("'updateBy' TEXT,")
            .append("'updateTime' TEXT,")
            .append("'params' TEXT,")
            .append("'md5' TEXT")
            .append(")");
    }

}
