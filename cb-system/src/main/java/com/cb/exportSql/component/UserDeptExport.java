package com.cb.exportSql.component;

import com.alibaba.fastjson.JSON;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.exportSql.domain.SqlFile;
import com.cb.system.mapper.SysDeptMapper;
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
 * @Description 人员部门导出
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserDeptExport extends ExportHandler implements ExportSql {
    // 人员名册信息（rymcxx）
    private final String tableName = "db_rybmxx";

    private final SysDeptMapper sysDeptMapper;

    @Override
    public String primaryKeyName() {
        return "deptId";
    }

    @Override
    public List<Map<String, Object>> exportMethod(Map<String, Object> params) {
        List<SysDept> result = sysDeptMapper.exportDeptInfoData(params);
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
            .append("'deptId' INTEGER NOT NULL PRIMARY KEY,").append("'deptName' TEXT,").append("'parentId' INTEGER,")
            .append("'leader' TEXT,").append("'affiliations' TEXT,").append("'orderNum' TEXT,")
            .append("'orgLevel' TEXT,").append("'deptAbbreviation' TEXT,").append("'ancestors' TEXT,")
            .append("'email' TEXT,").append("'deptInstitution' TEXT,").append("'deptType' TEXT,")
            .append("'phone' TEXT,").append("'region' TEXT,").append("'deptCode' TEXT,")
            .append("'status' TEXT,").append("'children' TEXT,").append("'applicationNumber' TEXT,")
            .append("'approvalNumber' TEXT,").append("'params' TEXT,").append("'delFlag' TEXT,")
            .append("'leaderCharge' TEXT,").append("'createBy' TEXT,").append("'createTime' TEXT,")
            .append("'updateBy' TEXT,").append("'updateTime' TEXT,").append("'md5' TEXT")
            .append(")");

    }

}
