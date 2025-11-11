package com.cb.activiti.export;

import com.cb.exportSql.component.ExportHandler;
import com.cb.exportSql.component.ExportSql;
import com.cb.exportSql.domain.SqlFile;
import com.cb.leave.mapper.LeaveBalancesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/4/27 09:16
 * @Copyright (c) 2025
 * @Description 应休未休信息导出
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class LeaveBalancesExport extends ExportHandler implements ExportSql {

    private final String tableName = "db_yxwxqk";

    private final LeaveBalancesMapper balancesMapper;

    @Override
    public List<Map<String, Object>> exportMethod(Map<String, Object> params) {
        return balancesMapper.exportLeaveBalancesData(params);
    }

    @Override
    public void setSqlFileAndData(SqlFile sqlFile, List<Map<String, Object>> result) {
        sqlFile.setTableName(tableName);
    }

    @Override
    public void createSqliteTableSql(StringBuilder sql) {
        sql.append(tableName).append(" (")
            .append("'id' INTEGER NOT NULL PRIMARY KEY,").append("'userId' INTEGER NOT NULL,")
            .append("'userName' TEXT,").append("'deptName' TEXT,").append("'leaveTypeId' INTEGER,")
            .append("'leaveName' TEXT,").append("'leaveYear' INTEGER,").append("'totalDays' REAL,")
            .append("'usedDays' REAL,").append("'pendingDays' REAL,").append("'remainingDays' REAL,")
            .append("'leaveRate' TEXT,").append("'createBy' TEXT,").append("'createTime' TEXT,")
            .append("'updateBy' TEXT,").append("'updateTime' TEXT,").append("'status' INTEGER,")
            .append("'approvalOpinion' TEXT,").append("'leaderId' TEXT,").append("'leaderName' TEXT,")
            .append("'changeFlag' TEXT,").append("'md5' TEXT")
            .append(")");
    }

}
