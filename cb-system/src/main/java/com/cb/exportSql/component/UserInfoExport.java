package com.cb.exportSql.component;

import com.alibaba.fastjson.JSON;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.core.domain.entity.SysUserEducationAndDegreeInfo;
import com.cb.exportSql.domain.SqlFile;
import com.cb.system.mapper.SysUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/4/27 09:16
 * @Copyright (c) 2025
 * @Description 人员信息导出
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserInfoExport extends ExportHandler implements ExportSql {
    // 人员名册信息（rymcxx）
    private final String tableName = "db_rymcxx";

    private final SysUserMapper sysUserMapper;

    @Override
    public String primaryKeyName() {
        return "userId";
    }

    @Override
    public List<Map<String, Object>> exportMethod(Map<String, Object> params) {
        List<SysUser> result = sysUserMapper.exportUserInfoData(params);
        if (!result.isEmpty()) {
            setEduInfo(result);
            return result.stream()
                    .map(item -> (Map<String, Object>) JSON.parseObject(JSON.toJSONString(item), Map.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public void createSqliteTableSql(StringBuilder sql) {
        sql.append(tableName).append(" (")
            .append("'userId' INTEGER PRIMARY KEY,").append("'deptId' INTEGER,").append("'deptName' TEXT,")
            .append("'userName' TEXT,").append("'nickName' TEXT,").append("'email' TEXT,")
            .append("'phonenumber' TEXT,").append("'sex' TEXT,").append("'recruitLot' TEXT,")
            .append("'avatar' TEXT,").append("'password' TEXT,").append("'status' TEXT,")
            .append("'delFlag' TEXT,").append("'loginIp' TEXT,").append("'loginDate' TEXT,")
            .append("'dept' TEXT,").append("'roles' TEXT,").append("'roleIds' TEXT,")
            .append("'postIds' TEXT,").append("'userIds' TEXT,").append("'userType' TEXT,")
            .append("'secretLevel' INTEGER,").append("'sequence' INTEGER,").append("'abilityLabel' TEXT,")
            .append("'otherLabel' TEXT,").append("'abilityLabelId' TEXT,").append("'admin' BOOLEAN,")
            .append("'audAdmin' BOOLEAN,").append("'secAdmin' BOOLEAN,").append("'name' TEXT,")
            .append("'birthday' TEXT,").append("'nation' TEXT,").append("'nativePlace' TEXT,")
            .append("'birthPlace' TEXT,").append("'politicalIdentity' TEXT,").append("'partyJoinTime' TEXT,")
            .append("'secondParty' TEXT,").append("'thirdParty' TEXT,").append("'startWorkTime' TEXT,")
            .append("'healthCondition' TEXT,").append("'speciality' TEXT,").append("'idcard' TEXT,")
            .append("'manageType' TEXT,").append("'staffType' TEXT,").append("'identityType' TEXT,")
            .append("'workPost' TEXT,").append("'workPostCode' TEXT,").append("'workPostTime' TEXT,")
            .append("'sameWorkPostTime' TEXT,").append("'workTitle' TEXT,").append("'workTitleCode' TEXT,")
            .append("'workTitleTime' TEXT,").append("'sameWorkTitleTime' TEXT,").append("'isEnrollment' TEXT,")
            .append("'enrollmentTime' TEXT,").append("'isSelectedStudent' TEXT,").append("'selectedStudentTime' TEXT,")
            .append("'growPlace' TEXT,").append("'level' TEXT,").append("'qualifications' TEXT,")
            .append("'treatment' TEXT,").append("'officeTel' TEXT,").append("'isMainLeader' TEXT,")
            .append("'joinOrgTime' TEXT,").append("'grassrootsWorkTime' TEXT,").append("'sectionChiefTime' TEXT,")
            .append("'joinDeptTime' TEXT,").append("'maritalStatus' TEXT,").append("'isVeterans' TEXT,")
            .append("'education' TEXT,").append("'degree' TEXT,").append("'personnelStatus' TEXT,")
            .append("'lendingUnits' TEXT,").append("'retirementTime' TEXT,").append("'signImg' TEXT,")
            .append("'isHostingWork' TEXT,").append("'professionalDuty' TEXT,").append("'fullTimeEducationLevel' TEXT,")
            .append("'fullTimeEducationSchoolAndMajor' TEXT,").append("'onJobEducationLevel' TEXT,").append("'onJobEducationSchoolAndMajor' TEXT,")
            .append("'currentPosition' TEXT,").append("'positionShort' TEXT,").append("'proposedAppointmentPosition' TEXT,")
            .append("'proposedRemovalPosition' TEXT,").append("'resumeJsonArray' TEXT,").append("'rewardAndPunishment' TEXT,")
            .append("'annualAssessment' TEXT,").append("'reasonForAppointmentOrRemoval' TEXT,").append("'familyMemberJsonArray' TEXT,")
            .append("'userDeptPostList' TEXT,").append("'positionInfos' TEXT,").append("'educationAndDegreeInfos' TEXT,")
            .append("'rewardsInfos' TEXT,").append("'appraisalInfos' TEXT,").append("'familyInfos' TEXT,")
            .append("'sysUserResumeInfoList' TEXT,").append("'sysUserWorkUnitAndPositionInfoList' TEXT,").append("'currentPostInfos' TEXT,")
            .append("'grassrootsWorkInfos' TEXT,").append("'secondmentWorkInfos' TEXT,").append("'advantages' TEXT,")
            .append("'disadvantages' TEXT,").append("'remark' TEXT,").append("'params' TEXT,").append("'createBy' TEXT,")
            .append("'createTime' TEXT,").append("'updateBy' TEXT,").append("'updateTime' TEXT,")
            .append("'reserveUser' TEXT,").append("'md5' TEXT")
            .append(")");
    }

    @Override
    public void setSqlFileAndData(SqlFile sqlFile, List<Map<String, Object>> result) {
        sqlFile.setTableName(tableName);
    }

    /**
     * 设置教育学历信息
     *
     * @param list
     */
    private void setEduInfo(List<SysUser> list) {
        //设置学历信息
        List<Long> userIds = list.stream().map(SysUser::getUserId).collect(Collectors.toList());
        Map<Long, List<SysUserEducationAndDegreeInfo>> userIdEduMap =
                sysUserMapper.selectEducationAndDegreeInfoByUserIds(userIds)
                        .stream().collect(Collectors.groupingBy(SysUserEducationAndDegreeInfo::getUserId));
        list.forEach(o -> {
            List<SysUserEducationAndDegreeInfo> eduList = userIdEduMap.get(o.getUserId());
            if (null == eduList) {
                o.setEducationAndDegreeInfos(Collections.emptyList());
            } else {
                eduList.forEach(i -> {
                    if (null == i.getCompletionDate()) {
                        i.setCompletionDate("");
                    }
                });
                eduList = eduList.stream().sorted(Comparator.comparing(SysUserEducationAndDegreeInfo::getCompletionDate).reversed())
                        .collect(Collectors.toList());
                o.setEducationAndDegreeInfos(eduList);
            }
        });
    }
}
