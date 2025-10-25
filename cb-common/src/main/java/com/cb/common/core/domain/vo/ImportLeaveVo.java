package com.cb.common.core.domain.vo;

import com.cb.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class ImportLeaveVo implements Serializable {

    @Excel(name = "序号")
    private String orderNo;
    /** 申请人 */
    @Excel(name = "申请人")
    private String applyUserName;

    /** 申请时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请时间", dateFormat = "yyyy-MM-dd")
    private String applyTime;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String deptName;

    /** 职务 */
    @Excel(name = "职务")
    private String workPost;

    /** 请假天数 */
    @Excel(name = "请假天数")
    private String leaveNum;



    /** 请假类型 */
    @Excel(name = "请假类型")
    private String type;

    /** 原因 */
    @Excel(name = "原因")
    private String reason;

    /** 开始时间 */
    @Excel(name = "开始时间", dateFormat = "yyyy-MM-dd")
    private String leaveStartTime;

    /** 结束时间 */
    @Excel(name = "结束时间",  dateFormat = "yyyy-MM-dd")
    private String leaveEndTime;

    /** 请假时长，单位秒 */
    @Excel(name = "请假时长，单位秒")
    private String totalTime;

    /** 实际开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际开始时间",  dateFormat = "yyyy-MM-dd")
    private String realityStartTime;

    /** 实际结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际结束时间",  dateFormat = "yyyy-MM-dd")
    private String realityEndTime;

    /** 销假日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "销假日期", dateFormat = "yyyy-MM-dd")
    private String destructionDate;

    /** 主要领导意见 */
    @Excel(name = "主要领导意见")
    private String mainLeaderOpinions;

    /** 分管副书记意见 */
    @Excel(name = "分管副书记意见")
    private String deputySecretaryOpinions;

    /** 领衔常委（委员）意见 */
    @Excel(name = "领衔常委(委员)意见")
    private String standingCommitteeOpinions;

    /** 组织部审核意见 */
    @Excel(name = "组织部审核意见")
    private String organizationOpinions;

    /** 部门负责人意见 */
    @Excel(name = "部门负责人意见")
    private String departmentOpinions;

    /**
     * 假期年度
     */
    @Excel(name="假期年度")
    private String holidayYear;

    /**
     * 是否调休
     */
    @Excel(name = "是否调休", readConverterExp = "0=是,1=否")
    private String compensatoryFlag;
    /**
     * 计划休假时间
     */
    private String compensatoryPlanTime;

    /**
     *  跨年调休补休原因
     */
    private String compensatoryReason;

    /**
     *  跨年调休补休时间
     */
    private String compensatoryTime;
    /**
     * 是否生病住院请假
     */
    @Excel(name = "是否生病住院请假", readConverterExp = "0=是,1=否")
    private String sickHospitalizationFlag;
    /**
     * 是否离昆
     */
    @Excel(name="是否离昆", readConverterExp = "0=是,1=否")
    private String isLeaveKunming;
    /**
     * 离开昆明，需要填写具体去向
     */
    @Excel(name="离昆去向")
    private String destination;

    public String getIsLeaveKunming() {
        return isLeaveKunming;
    }

    public void setIsLeaveKunming(String isLeaveKunming) {
        this.isLeaveKunming = isLeaveKunming;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getReason()
    {
        return reason;
    }
    public void setLeaveStartTime(String leaveStartTime)
    {
        this.leaveStartTime = leaveStartTime;
    }

    public String getLeaveStartTime()
    {
        return leaveStartTime;
    }
    public void setLeaveEndTime(String leaveEndTime)
    {
        this.leaveEndTime = leaveEndTime;
    }

    public String getLeaveEndTime()
    {
        return leaveEndTime;
    }
    public void setTotalTime(String totalTime)
    {
        this.totalTime = totalTime;
    }

    public String getTotalTime()
    {
        return totalTime;
    }
    public void setRealityStartTime(String realityStartTime)
    {
        this.realityStartTime = realityStartTime;
    }

    public String getRealityStartTime()
    {
        return realityStartTime;
    }
    public void setRealityEndTime(String realityEndTime)
    {
        this.realityEndTime = realityEndTime;
    }

    public String getRealityEndTime()
    {
        return realityEndTime;
    }

    public void setApplyUserName(String applyUserName)
    {
        this.applyUserName = applyUserName;
    }

    public String getApplyUserName()
    {
        return applyUserName;
    }
    public void setApplyTime(String applyTime)
    {
        this.applyTime = applyTime;
    }

    public String getApplyTime()
    {
        return applyTime;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getWorkPost() {
        return workPost;
    }

    public void setWorkPost(String workPost) {
        this.workPost = workPost;
    }

    public String getLeaveNum() {
        return leaveNum;
    }

    public void setLeaveNum(String leaveNum) {
        this.leaveNum = leaveNum;
    }

    public String getDestructionDate() {
        return destructionDate;
    }

    public void setDestructionDate(String destructionDate) {
        this.destructionDate = destructionDate;
    }

    public String getMainLeaderOpinions() {
        return mainLeaderOpinions;
    }

    public void setMainLeaderOpinions(String mainLeaderOpinions) {
        this.mainLeaderOpinions = mainLeaderOpinions;
    }

    public String getDeputySecretaryOpinions() {
        return deputySecretaryOpinions;
    }

    public void setDeputySecretaryOpinions(String deputySecretaryOpinions) {
        this.deputySecretaryOpinions = deputySecretaryOpinions;
    }

    public String getStandingCommitteeOpinions() {
        return standingCommitteeOpinions;
    }

    public void setStandingCommitteeOpinions(String standingCommitteeOpinions) {
        this.standingCommitteeOpinions = standingCommitteeOpinions;
    }

    public String getOrganizationOpinions() {
        return organizationOpinions;
    }

    public void setOrganizationOpinions(String organizationOpinions) {
        this.organizationOpinions = organizationOpinions;
    }

    public String getDepartmentOpinions() {
        return departmentOpinions;
    }

    public void setDepartmentOpinions(String departmentOpinions) {
        this.departmentOpinions = departmentOpinions;
    }


    public String getHolidayYear() {
        return holidayYear;
    }

    public void setHolidayYear(String holidayYear) {
        this.holidayYear = holidayYear;
    }
    public String getCompensatoryFlag() {
        return compensatoryFlag;
    }

    public void setCompensatoryFlag(String compensatoryFlag) {
        this.compensatoryFlag = compensatoryFlag;
    }

    public String getSickHospitalizationFlag() {
        return sickHospitalizationFlag;
    }

    public void setSickHospitalizationFlag(String sickHospitalizationFlag) {
        this.sickHospitalizationFlag = sickHospitalizationFlag;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCompensatoryPlanTime() {
        return compensatoryPlanTime;
    }

    public void setCompensatoryPlanTime(String compensatoryPlanTime) {
        this.compensatoryPlanTime = compensatoryPlanTime;
    }

    public String getCompensatoryReason() {
        return compensatoryReason;
    }

    public void setCompensatoryReason(String compensatoryReason) {
        this.compensatoryReason = compensatoryReason;
    }

    public String getCompensatoryTime() {
        return compensatoryTime;
    }

    public void setCompensatoryTime(String compensatoryTime) {
        this.compensatoryTime = compensatoryTime;
    }
}
