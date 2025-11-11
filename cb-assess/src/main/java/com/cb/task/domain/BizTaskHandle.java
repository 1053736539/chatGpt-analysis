package com.cb.task.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Transient;

/**
 * 任务分配的执行人/部门对象 biz_task_handle
 * 
 * @author yangxin
 * @date 2023-10-30
 */
public class BizTaskHandle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 任务信息id */
    /*@Excel(name = "任务信息id")*/
    private String taskId;

    /** 处理类型 字典表 1-个人处理 2-部门处理 */
    @Excel(name = "处理类型",readConverterExp = "1=个人处理,2=部门处理")
    private String handleType;

    /** 处理部门 部门任务时有值 */
    /*@Excel(name = "处理部门 部门任务时有值")*/
    private Long handleDept;

    /** 处理人 个人任务/部门任务领导指派时有值 */
    /*@Excel(name = "处理人 个人任务/部门任务领导指派时有值")*/
    private String handleUser;

    /** 分数百分比 */
    @Excel(name = "分数百分比")
    private Long scorePercent;

    /** 完成度百分比 （0-100） */
    @Excel(name = "完成度百分比 ")
    private Integer progress;

    /** 任务状态 字典表 1-执行中 2-已完成 3-暂停 4-取消 5-作废 */
    @Excel(name = "任务状态",readConverterExp = "1=执行中,2=已完成,3=暂停,4=取消,5=作废")
    private String handleStatus;

    /** 指派状态 0-未指派 1-已指派 */
    @Excel(name = "指派状态",readConverterExp = "0=未指派,1=已指派")
    private String assignStatus;

    /** 执行开始时间 yyyy-MM-dd HH:mm:ss */
    @Excel(name = "执行开始时间")
    private String receiveTime;

    /** 完成时间 yyyy-MM-dd HH:mm:ss */
    @Excel(name = "完成时间")
    private String completeTime;

    private String resultAttach;//处理结果附件

    private String parentId;//父级处理记录id

    private String transferUser;//转办人（父级处理人）

    @Excel(name = "转办备注")
    private String transferRemark;//转办备注

    @Excel(name = "处理角色",readConverterExp = "1=主办,2=协办")
    private String handleRole;//处理角色字典表 1-主办 2-协办

    /** 完成情况 */
    @Excel(name = "完成情况")
    private String completeSituation;

    private Boolean masterFlag;//是否是主要负责人

    private String claimStatus;//认领状态 0-待认领 1-已认领

    private String claimType;//认领类型 1-办件 2-已阅

    /** 以下为扩展字段 **/

    /** 任务来源字典表 1-OA办文事项 2-OA通知公告 3-系统自建 */
    @Transient
    private String causation;

    @Transient
    @Excel(name = "任务名称")
    private String taskName;

    @Transient
    @Excel(name = "任务开始时间")
    private String taskBeginTime;

    @Transient
    @Excel(name = "任务结束时间")
    private String taskEndTime;

    @Transient
    private String taskAttach;//任务信息附件

    @Transient
    private String extInfo;//任务额外信息

    @Transient
    @Excel(name = "处理部门")
    private String handleDeptName;

    @Transient
    @Excel(name = "处理人")
    private String handleUserName;

    @Transient
    @Excel(name = "转办人")
    private String transferUserName;

    @Transient
    private BizTaskInfo taskInfo;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setTaskId(String taskId) 
    {
        this.taskId = taskId;
    }

    public String getTaskId() 
    {
        return taskId;
    }
    public void setHandleType(String handleType) 
    {
        this.handleType = handleType;
    }

    public String getHandleType() 
    {
        return handleType;
    }
    public void setHandleDept(Long handleDept)
    {
        this.handleDept = handleDept;
    }

    public Long getHandleDept()
    {
        return handleDept;
    }
    public void setHandleUser(String handleUser) 
    {
        this.handleUser = handleUser;
    }

    public String getHandleUser() 
    {
        return handleUser;
    }

    public void setScorePercent(Long scorePercent)
    {
        this.scorePercent = scorePercent;
    }

    public Long getScorePercent()
    {
        return scorePercent;
    }

    public void setProgress(Integer progress) 
    {
        this.progress = progress;
    }

    public Integer getProgress() 
    {
        return progress;
    }
    public void setHandleStatus(String handleStatus) 
    {
        this.handleStatus = handleStatus;
    }

    public String getHandleStatus() 
    {
        return handleStatus;
    }
    public void setAssignStatus(String assignStatus) 
    {
        this.assignStatus = assignStatus;
    }

    public String getAssignStatus() 
    {
        return assignStatus;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public String getResultAttach() {
        return resultAttach;
    }

    public void setResultAttach(String resultAttach) {
        this.resultAttach = resultAttach;
    }

    public String getCompleteSituation() {
        return completeSituation;
    }

    public void setCompleteSituation(String completeSituation) {
        this.completeSituation = completeSituation;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTransferUser() {
        return transferUser;
    }

    public void setTransferUser(String transferUser) {
        this.transferUser = transferUser;
    }

    public String getTransferRemark() {
        return transferRemark;
    }

    public void setTransferRemark(String transferRemark) {
        this.transferRemark = transferRemark;
    }

    public String getHandleRole() {
        return handleRole;
    }

    public void setHandleRole(String handleRole) {
        this.handleRole = handleRole;
    }

    public Boolean getMasterFlag() {
        return masterFlag;
    }

    public void setMasterFlag(Boolean masterFlag) {
        this.masterFlag = masterFlag;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    public String getCausation() {
        return causation;
    }

    public void setCausation(String causation) {
        this.causation = causation;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskBeginTime() {
        return taskBeginTime;
    }

    public void setTaskBeginTime(String taskBeginTime) {
        this.taskBeginTime = taskBeginTime;
    }

    public String getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(String taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public String getTaskAttach() {
        return taskAttach;
    }

    public void setTaskAttach(String taskAttach) {
        this.taskAttach = taskAttach;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    public String getHandleDeptName() {
        return handleDeptName;
    }

    public void setHandleDeptName(String handleDeptName) {
        this.handleDeptName = handleDeptName;
    }

    public String getHandleUserName() {
        return handleUserName;
    }

    public void setHandleUserName(String handleUserName) {
        this.handleUserName = handleUserName;
    }

    public String getTransferUserName() {
        return transferUserName;
    }

    public void setTransferUserName(String transferUserName) {
        this.transferUserName = transferUserName;
    }

    public BizTaskInfo getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(BizTaskInfo taskInfo) {
        this.taskInfo = taskInfo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskId", getTaskId())
            .append("handleType", getHandleType())
            .append("handleDept", getHandleDept())
            .append("handleUser", getHandleUser())
            .append("score", getScorePercent())
            .append("progress", getProgress())
            .append("handleStatus", getHandleStatus())
            .append("assignStatus", getAssignStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
