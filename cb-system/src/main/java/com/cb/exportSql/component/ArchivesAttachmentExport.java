package com.cb.exportSql.component;

import com.alibaba.fastjson.JSON;
import com.cb.common.core.domain.entity.RsDescript;
import com.cb.exportSql.domain.SqlFile;
import com.cb.system.mapper.RsDescriptMapper;
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
 * @CreateTime 2025/4/27 09:20
 * @Copyright (c) 2025
 * @Description 档案附件导出
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ArchivesAttachmentExport extends ExportHandler implements ExportSql {
    // 档案附件信息（dafjxx)
    private final String tableName = "db_dafjxx";

    private final RsDescriptMapper rsDescriptMapper;

    @Override
    public List<Map<String, Object>> exportMethod(Map<String, Object> params) {
        List<RsDescript> result = rsDescriptMapper.exportArchivesAttachmentData(params);
        if (!result.isEmpty()) {
            return result.stream()
                    .map(item -> (Map<String, Object>) JSON.parseObject(JSON.toJSONString(item), Map.class))
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
            .append("'id' INTEGER NOT NULL PRIMARY KEY,").append("'archId' INTEGER,")
            .append("'length' INTEGER,").append("'pdfKey' TEXT,").append("'serialMumber' INTEGER,")
            .append("'oldFileName' TEXT,").append("'newFileName' TEXT,").append("'gaoqingLeangth' INTEGER,")
            .append("'gaoqingPdfKey' TEXT,").append("'path' TEXT,").append("'params' TEXT,")
            .append("'createBy' TEXT,").append("'createTime' TEXT,").append("'updateBy' TEXT,")
            .append("'updateTime' TEXT,").append("'md5' TEXT")
            .append(")");
    }

}
