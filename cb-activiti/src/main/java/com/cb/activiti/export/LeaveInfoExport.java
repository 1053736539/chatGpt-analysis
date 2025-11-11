package com.cb.activiti.export;

import com.alibaba.fastjson.JSON;
import com.cb.activiti.domain.BizLeave;
import com.cb.activiti.mapper.BizLeaveMapper;
import com.cb.exportSql.component.ExportHandler;
import com.cb.exportSql.component.ExportSql;
import com.cb.exportSql.domain.SqlFile;
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
 * @Description 请假信息导出
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class LeaveInfoExport extends ExportHandler implements ExportSql {

    private final String tableName = "db_qjqk";

    private final BizLeaveMapper bizLeaveMapper;

    @Override
    public List<Map<String, Object>> exportMethod(Map<String, Object> params) {
        List<BizLeave> result = bizLeaveMapper.exportBizLeaveData(params);
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
            .append("'id' INTEGER NOT NULL PRIMARY KEY,").append("'type' INTEGER,")
            .append("'title' TEXT,").append("'reason' TEXT,").append("'leaveStartTime' TEXT,")
            .append("'leaveEndTime' TEXT,").append("'totalTime' INTEGER,").append("'realityStartTime' TEXT,")
            .append("'realityEndTime' TEXT,").append("'applyUserId' TEXT,").append("'applyUserName' TEXT,")
            .append("'applyTime' TEXT,").append("'instanceId' TEXT,").append("'processKey' TEXT,")
            .append("'activityId' TEXT,").append("'delFlag' TEXT,").append("'deptId' INTEGER,")
            .append("'deptName' TEXT,").append("'workPost' TEXT,").append("'leaveNum' REAL,")
            .append("'isHalfDay' INTEGER,").append("'nextUserName' TEXT,").append("'destructionDate' TEXT,")
            .append("'mainLeaderOpinions' TEXT,").append("'deputySecretaryOpinions' TEXT,")
            .append("'standingCommitteeOpinions' TEXT,").append("'organizationOpinions' TEXT,")
            .append("'departmentOpinions' TEXT,").append("'createUserLevel' TEXT,").append("'holidayYear' TEXT,")
            .append("'compensatoryFlag' INTEGER,").append("'sickHospitalizationFlag' INTEGER,")
            .append("'compensatoryPlanTime' TEXT,").append("'compensatoryReason' TEXT,")
            .append("'compensatoryTime' TEXT,").append("'status' TEXT,").append("'isLeaveKunming' TEXT,")
            .append("'destination' TEXT,").append("'attach' TEXT,").append("'inspectionTeamHeadOpinions' TEXT,")
            .append("'params' TEXT,").append("'createBy' TEXT,").append("'createTime' TEXT,")
            .append("'updateBy' TEXT,").append("'updateTime' TEXT,").append("'remark' TEXT,")
            .append("'md5' TEXT")
            .append(")");
    }

}
