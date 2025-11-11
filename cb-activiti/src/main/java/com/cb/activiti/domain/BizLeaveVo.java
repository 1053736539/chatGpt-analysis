package com.cb.activiti.domain;

import com.cb.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 请假对象 biz_leave
 *
 * @author 一只闲鹿
 * @date 2020-11-29
 */
public class BizLeaveVo extends ProcessEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 请假类型 */
    @Excel(name = "请假类型")
    private Long type;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 原因 */
    @Excel(name = "原因")
    private String reason;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date leaveStartTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date leaveEndTime;

    /** 请假时长，单位秒 */
    @Excel(name = "请假时长，单位秒")
    private Long totalTime;

    /** 实际开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date realityStartTime;

    /** 实际结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date realityEndTime;

    /** 申请人 */
    @Excel(name = "申请人")
    private String applyUserId;

    /** 申请人 */
    @Excel(name = "申请人")
    private String applyUserName;

    /** 申请时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applyTime;

    /** 流程实例ID */
    @Excel(name = "流程实例ID")
    private String instanceId;

    /** 流程定义key */
    @Excel(name = "流程定义key")
    private String processKey;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Long deptId;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String deptName;

    /** 职务 */
    @Excel(name = "职务")
    private String workPost;

    /** 请假天数 */
    @Excel(name = "请假天数")
    private Float leaveNum;

    /** 请半天假 */
    @Excel(name = "是否请半天假",readConverterExp = "0=否,1=是" )
    private   Integer isHalfDay;

    /** 下一步审批人 */
    private String nextUserName;

    private  String pass;

    public void setNextUserName(String nextUserName) {
        this.nextUserName = nextUserName;
    }

    public String getNextUserName() {
        return nextUserName;
    }

    public void setNextUserUserName(String nextUserName) {
        this.nextUserName = nextUserName;
    }

    public Integer getIsHalfDay() {
        return isHalfDay;
    }

    public void setIsHalfDay(Integer isHalfDay) {
        this.isHalfDay = isHalfDay;
    }

    /** 销假日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "销假日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date destructionDate;

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

    /** 发起人类型(用于bpm流程判断) */
    @Excel(name = "发起人类型(用于bpm流程判断)")
    private String createUserLevel;
    /**
     * 假期年度
     */
    private String holidayYear;

    /**
     * 是否调休
     */
    private Integer compensatoryFlag;

    /**
     * 是否生病住院请假
     */
    private Integer sickHospitalizationFlag;

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
     * 状态 0待审核 1审核通过 2不通过
     */
    private String status;
    private String isLeaveKunming;
    private String destination;
    private String attach;

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

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getReason()
    {
        return reason;
    }
    public void setLeaveStartTime(Date leaveStartTime)
    {
        this.leaveStartTime = leaveStartTime;
    }

    public Date getLeaveStartTime()
    {
        return leaveStartTime;
    }
    public void setLeaveEndTime(Date leaveEndTime)
    {
        this.leaveEndTime = leaveEndTime;
    }

    public Date getLeaveEndTime()
    {
        return leaveEndTime;
    }
    public void setTotalTime(Long totalTime)
    {
        this.totalTime = totalTime;
    }

    public Long getTotalTime()
    {
        return totalTime;
    }
    public void setRealityStartTime(Date realityStartTime)
    {
        this.realityStartTime = realityStartTime;
    }

    public Date getRealityStartTime()
    {
        return realityStartTime;
    }
    public void setRealityEndTime(Date realityEndTime)
    {
        this.realityEndTime = realityEndTime;
    }

    public Date getRealityEndTime()
    {
        return realityEndTime;
    }
    public void setApplyUserId(String applyUserId)
    {
        this.applyUserId = applyUserId;
    }

    public String getApplyUserId()
    {
        return applyUserId;
    }
    public void setApplyUserName(String applyUserName)
    {
        this.applyUserName = applyUserName;
    }

    public String getApplyUserName()
    {
        return applyUserName;
    }
    public void setApplyTime(Date applyTime)
    {
        this.applyTime = applyTime;
    }

    public Date getApplyTime()
    {
        return applyTime;
    }
    public void setInstanceId(String instanceId)
    {
        this.instanceId = instanceId;
    }

    public String getInstanceId()
    {
        return instanceId;
    }
    public void setProcessKey(String processKey)
    {
        this.processKey = processKey;
    }

    public String getProcessKey()
    {
        return processKey;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
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

    public Float getLeaveNum() {
        return leaveNum;
    }

    public void setLeaveNum(Float leaveNum) {
        this.leaveNum = leaveNum;
    }

    public Date getDestructionDate() {
        return destructionDate;
    }

    public void setDestructionDate(Date destructionDate) {
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

    public String getCreateUserLevel() {
        return createUserLevel;
    }

    public void setCreateUserLevel(String createUserLevel) {
        this.createUserLevel = createUserLevel;
    }

    public String getHolidayYear() {
        return holidayYear;
    }

    public void setHolidayYear(String holidayYear) {
        this.holidayYear = holidayYear;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCompensatoryFlag() {
        return compensatoryFlag;
    }

    public void setCompensatoryFlag(Integer compensatoryFlag) {
        this.compensatoryFlag = compensatoryFlag;
    }

    public Integer getSickHospitalizationFlag() {
        return sickHospitalizationFlag;
    }

    public void setSickHospitalizationFlag(Integer sickHospitalizationFlag) {
        this.sickHospitalizationFlag = sickHospitalizationFlag;
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
