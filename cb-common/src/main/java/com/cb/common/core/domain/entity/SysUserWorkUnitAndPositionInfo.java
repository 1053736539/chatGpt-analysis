package com.cb.common.core.domain.entity;

import com.cb.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 工作单位及职务信息对象 sys_user_work_unit_and_position_info
 *
 * @author hu
 * @date 2023-10-30
 */
public class SysUserWorkUnitAndPositionInfo {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String workUnitAndPositionInfoId;

    /**
     * 用户ID
     */
    @Excel(name = "用户ID")
    private Long userId;

    /**
     * 职务类型
     */
    @Excel(name = "职务类型")
    private String jobType;

    /**
     * 任职机构
     */
    @Excel(name = "任职机构")
    private String employmentOrg;

    /**
     * 任职机构名称
     */
    @Excel(name = "任职机构名称")
    private String employmentOrgName;

    /**
     * 是否领导成员
     */
    @Excel(name = "是否领导成员")
    private String leaderMember;

    /**
     * 成员类别
     */
    @Excel(name = "成员类别")
    private String memberCategory;

    /**
     * 是否破格提拔
     */
    @Excel(name = "是否破格提拔")
    private String unconventionallyPromote;

    /**
     * 选拔任用方式
     */
    @Excel(name = "选拔任用方式")
    private String selectionAndAppointment;

    /**
     * 任职状态
     */
    @Excel(name = "任职状态")
    private String jobStatus;

    /**
     * 任职时间
     */
    @Excel(name = "任职时间")
    private String takeOfficeTime;

    /**
     * 任职文号
     */
    @Excel(name = "任职文号")
    private String takeOfficeNo;

    /**
     * 免职时间
     */
    @Excel(name = "免职时间")
    private String dismissalTime;

    /**
     * 免职文号
     */
    @Excel(name = "免职文号")
    private String dismissalNo;

    /**
     * 职务全称
     */
    @Excel(name = "职务全称")
    private String postFullName;

    /**
     * 职务简称
     */
    @Excel(name = "职务简称")
    private String postAbbreviation;

    /**
     * 具有两年以上基层工作经历
     */
    @Excel(name = "具有两年以上基层工作经历")
    private String grassrootsWorkExperience;

    /**
     * 统计关系所在单位
     */
    @Excel(name = "统计关系所在单位")
    private String relationshipUnit;

    /**
     * 职务变动原因
     */
    @Excel(name = "职务变动原因")
    private String jobChangeReason;

    /** 序号 */
    @Excel(name = "序号")
    private String sortNum;

    /**
     * 删除标记 0-未删除 1-已删除
     */
    private String delFlag;

    public void setWorkUnitAndPositionInfoId(String workUnitAndPositionInfoId) {
        this.workUnitAndPositionInfoId = workUnitAndPositionInfoId;
    }

    public String getWorkUnitAndPositionInfoId() {
        return workUnitAndPositionInfoId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobType() {
        return jobType;
    }

    public void setEmploymentOrg(String employmentOrg) {
        this.employmentOrg = employmentOrg;
    }

    public String getEmploymentOrg() {
        return employmentOrg;
    }

    public void setEmploymentOrgName(String employmentOrgName) {
        this.employmentOrgName = employmentOrgName;
    }

    public String getEmploymentOrgName() {
        return employmentOrgName;
    }

    public void setLeaderMember(String leaderMember) {
        this.leaderMember = leaderMember;
    }

    public String getLeaderMember() {
        return leaderMember;
    }

    public void setMemberCategory(String memberCategory) {
        this.memberCategory = memberCategory;
    }

    public String getMemberCategory() {
        return memberCategory;
    }

    public void setUnconventionallyPromote(String unconventionallyPromote) {
        this.unconventionallyPromote = unconventionallyPromote;
    }

    public String getUnconventionallyPromote() {
        return unconventionallyPromote;
    }

    public void setSelectionAndAppointment(String selectionAndAppointment) {
        this.selectionAndAppointment = selectionAndAppointment;
    }

    public String getSelectionAndAppointment() {
        return selectionAndAppointment;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setTakeOfficeTime(String takeOfficeTime) {
        this.takeOfficeTime = takeOfficeTime;
    }

    public String getTakeOfficeTime() {
        return takeOfficeTime;
    }

    public void setTakeOfficeNo(String takeOfficeNo) {
        this.takeOfficeNo = takeOfficeNo;
    }

    public String getTakeOfficeNo() {
        return takeOfficeNo;
    }

    public void setDismissalTime(String dismissalTime) {
        this.dismissalTime = dismissalTime;
    }

    public String getDismissalTime() {
        return dismissalTime;
    }

    public void setDismissalNo(String dismissalNo) {
        this.dismissalNo = dismissalNo;
    }

    public String getDismissalNo() {
        return dismissalNo;
    }

    public void setPostFullName(String postFullName) {
        this.postFullName = postFullName;
    }

    public String getPostFullName() {
        return postFullName;
    }

    public void setPostAbbreviation(String postAbbreviation) {
        this.postAbbreviation = postAbbreviation;
    }

    public String getPostAbbreviation() {
        return postAbbreviation;
    }

    public void setGrassrootsWorkExperience(String grassrootsWorkExperience) {
        this.grassrootsWorkExperience = grassrootsWorkExperience;
    }

    public String getGrassrootsWorkExperience() {
        return grassrootsWorkExperience;
    }

    public void setRelationshipUnit(String relationshipUnit) {
        this.relationshipUnit = relationshipUnit;
    }

    public String getRelationshipUnit() {
        return relationshipUnit;
    }

    public void setJobChangeReason(String jobChangeReason) {
        this.jobChangeReason = jobChangeReason;
    }

    public String getJobChangeReason() {
        return jobChangeReason;
    }

    public void setSortNum(String sortNum) {
        this.sortNum = sortNum;
    }

    public String getSortNum() {
        return sortNum;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("workUnitAndPositionInfoId", getWorkUnitAndPositionInfoId())
                .append("userId", getUserId())
                .append("jobType", getJobType())
                .append("employmentOrg", getEmploymentOrg())
                .append("employmentOrgName", getEmploymentOrgName())
                .append("leaderMember", getLeaderMember())
                .append("memberCategory", getMemberCategory())
                .append("unconventionallyPromote", getUnconventionallyPromote())
                .append("selectionAndAppointment", getSelectionAndAppointment())
                .append("jobStatus", getJobStatus())
                .append("takeOfficeTime", getTakeOfficeTime())
                .append("takeOfficeNo", getTakeOfficeNo())
                .append("dismissalTime", getDismissalTime())
                .append("dismissalNo", getDismissalNo())
                .append("postFullName", getPostFullName())
                .append("postAbbreviation", getPostAbbreviation())
                .append("grassrootsWorkExperience", getGrassrootsWorkExperience())
                .append("relationshipUnit", getRelationshipUnit())
                .append("jobChangeReason", getJobChangeReason())
                .append("sortNum", getSortNum())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
