package com.cb.task.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Transient;

import java.util.List;

/**
 * 任务信息对象 biz_task_info
 * 
 * @author yangxin
 * @date 2023-10-30
 */
public class BizTaskInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 任务来源字典表 1-OA办文事项 2-OA通知公告 3-系统自建 */
    @Excel(name = "任务来源字典表",readConverterExp="1=OA办文事项,2=OA通知公告,3=系统自建")
    private String causation;

    /** 业务主键（OA办文事项/OA通知公告id） */
    @Excel(name = "业务主键")
    private String businessId;

    /** 任务依据（任务来源为系统创建时必填） */
    @Excel(name = "任务依据")
    private String basis;

    /** 所属顶级任务id */
   /* @Excel(name = "所属顶级任务id")*/
    private String topId;

    /** 父级任务id 不空则为子任务 */
    /*@Excel(name = "父级任务id 不空则为子任务")*/
    private String parentId;

    /** 任务标题 */
    @Excel(name = "任务标题")
    private String title;

    /** 任务内容 */
    @Excel(name = "任务内容")
    private String desc;

    /** 开始时间 yyyy-MM-dd HH:mm:ss */
    @Excel(name = "开始时间")
    private String beginTime;

    /** 结束时间 yyyy-MM-dd HH:mm:ss */
    @Excel(name = "结束时间")
    private String endTime;

    /** 级别 字典表 1-一般 2-重要 3-紧急 4-非常紧急 */
    @Excel(name = "级别",readConverterExp = "1=一般,2=重要,3=紧急,4=非常紧急")
    private String urgency;

    /** 总分数 */
    @Excel(name = "总分数")
    private Long planScore;

    /** 难度系数 字典表 1-简单 2-一般 3-难 4-较难 5-高难 */
    @Excel(name = "难度系数",readConverterExp = "1=简单,2=一般,3=难,4=较难,5=高难")
    private String difficult;

    /** 完成度百分比 （0-100） */
    @Excel(name = "完成度百分比")
    private Integer progress;

    /** 完成情况 */
    @Excel(name = "完成情况")
    private String completeSituation;

    /** 任务状态 字典表 1-执行中 2-已完成 3-暂停 4-取消 5-作废 */
    @Excel(name = "任务状态",readConverterExp = "1=执行中,2=已完成,3=暂停,4=取消,5=作废")
    private String status;

    /** 任务审核状态 字典表 0-草稿 1-待审核 2-审核驳回 3-审核通过 */
    @Excel(name = "任务审核状态",readConverterExp = "0=草稿,1=待审核,2=审核驳回,3=审核通过")
    private String auditStatus;

    /** 创建人所属部门 */
    /*@Excel(name = "创建人所属部门")*/
    private Long createDept;

    /** 驳回原因 */
    @Excel(name = "驳回原因")
    private String rejectReason;

    private String attach;//附件attachId拼接字符串

    @Excel(name = "任务公文类型",readConverterExp = "1=待办件,2=待阅件")
    private String docType;

    private String extInfo;//oa交办的公文额外信息，json字符串存储


    /** 以下为扩展字段 **/
    @Transient
    @Excel(name = "父级任务名称")
    private String parentTaskName;

    @Transient
    @Excel(name = "所属根顶级任务名称")
    private String topTaskName;

    @Transient
    @Excel(name = "创建人")
    private String createByName;

    @Transient
    @Excel(name = "创建人部门名称")
    private String createDeptName;


    @Transient
    private List<BizTaskHandle> handleList;//处理人列表
    @Transient
    private List<BizTaskComment> commentList;//评论列表

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setCausation(String causation) 
    {
        this.causation = causation;
    }

    public String getCausation() 
    {
        return causation;
    }
    public void setBusinessId(String businessId) 
    {
        this.businessId = businessId;
    }

    public String getBusinessId() 
    {
        return businessId;
    }
    public void setBasis(String basis) 
    {
        this.basis = basis;
    }

    public String getBasis() 
    {
        return basis;
    }
    public void setTopId(String topId) 
    {
        this.topId = topId;
    }

    public String getTopId() 
    {
        return topId;
    }
    public void setParentId(String parentId) 
    {
        this.parentId = parentId;
    }

    public String getParentId() 
    {
        return parentId;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setDesc(String desc) 
    {
        this.desc = desc;
    }

    public String getDesc() 
    {
        return desc;
    }
    public void setBeginTime(String beginTime) 
    {
        this.beginTime = beginTime;
    }

    public String getBeginTime() 
    {
        return beginTime;
    }
    public void setEndTime(String endTime) 
    {
        this.endTime = endTime;
    }

    public String getEndTime() 
    {
        return endTime;
    }
    public void setUrgency(String urgency) 
    {
        this.urgency = urgency;
    }

    public String getUrgency() 
    {
        return urgency;
    }
    public void setPlanScore(Long planScore) 
    {
        this.planScore = planScore;
    }

    public Long getPlanScore() 
    {
        return planScore;
    }

    public void setDifficult(String difficult) 
    {
        this.difficult = difficult;
    }

    public String getDifficult() 
    {
        return difficult;
    }
    public void setProgress(Integer progress) 
    {
        this.progress = progress;
    }

    public Integer getProgress() 
    {
        return progress;
    }
    public void setCompleteSituation(String completeSituation) 
    {
        this.completeSituation = completeSituation;
    }

    public String getCompleteSituation() 
    {
        return completeSituation;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setAuditStatus(String auditStatus) 
    {
        this.auditStatus = auditStatus;
    }

    public String getAuditStatus() 
    {
        return auditStatus;
    }
    public void setCreateDept(Long createDept)
    {
        this.createDept = createDept;
    }

    public Long getCreateDept()
    {
        return createDept;
    }
    public void setRejectReason(String rejectReason) 
    {
        this.rejectReason = rejectReason;
    }

    public String getRejectReason() 
    {
        return rejectReason;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getParentTaskName() {
        return parentTaskName;
    }

    public void setParentTaskName(String parentTaskName) {
        this.parentTaskName = parentTaskName;
    }

    public String getTopTaskName() {
        return topTaskName;
    }

    public void setTopTaskName(String topTaskName) {
        this.topTaskName = topTaskName;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public String getCreateDeptName() {
        return createDeptName;
    }

    public void setCreateDeptName(String createDeptName) {
        this.createDeptName = createDeptName;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    public List<BizTaskHandle> getHandleList() {
        return handleList;
    }

    public void setHandleList(List<BizTaskHandle> handleList) {
        this.handleList = handleList;
    }

    public List<BizTaskComment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<BizTaskComment> commentList) {
        this.commentList = commentList;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("causation", getCausation())
            .append("businessId", getBusinessId())
            .append("basis", getBasis())
            .append("topId", getTopId())
            .append("parentId", getParentId())
            .append("title", getTitle())
            .append("desc", getDesc())
            .append("beginTime", getBeginTime())
            .append("endTime", getEndTime())
            .append("urgency", getUrgency())
            .append("planScore", getPlanScore())
            .append("difficult", getDifficult())
            .append("progress", getProgress())
            .append("completeSituation", getCompleteSituation())
            .append("status", getStatus())
            .append("auditStatus", getAuditStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("createDept", getCreateDept())
            .append("rejectReason", getRejectReason())
            .toString();
    }
}
