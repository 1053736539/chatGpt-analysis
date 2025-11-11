package com.cb.assess.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 个人总结对象 biz_assess_personal_summary
 * 
 * @author ruoyi
 * @date 2023-12-12
 */
public class BizAssessPersonalSummary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long userId;

    /** 上报时间 */
    @Excel(name = "上报时间")
    private String reportTime;

    /** $column.columnComment */
    @Excel(name = "上报时间")
    private Long deptId;

    /** 个人总结内容 */
    @Excel(name = "个人总结内容")
    private String content;

    /** 是否上报到人事处，0未上报，1已上报 */
    @Excel(name = "是否上报到人事处，0未上报，1已上报")
    private String isReport;

    /** 是否公示，0未公示，1已公示 */
    @Excel(name = "是否公示，0未公示，1已公示")
    private String isPublicity;

    /** 考核年度 */
    @Excel(name = "考核年度")
    private String assessmentYear;

    /** 公示开始时间 */
    @Excel(name = "公示开始时间")
    private String publicityStartTime;

    /** 公示结束时间 */
    @Excel(name = "公示结束时间")
    private String publicityEndTime;

    /** $column.columnComment */
    private String delFlag;

    /** 审核标记 1-待审核 2-审核通过 3-驳回 */
    private String auditFlag;

    /** 驳回理由 */
    private String rejectReason;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setReportTime(String reportTime) 
    {
        this.reportTime = reportTime;
    }

    public String getReportTime() 
    {
        return reportTime;
    }
    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setIsReport(String isReport) 
    {
        this.isReport = isReport;
    }

    public String getIsReport() 
    {
        return isReport;
    }
    public void setIsPublicity(String isPublicity) 
    {
        this.isPublicity = isPublicity;
    }

    public String getIsPublicity() 
    {
        return isPublicity;
    }
    public void setAssessmentYear(String assessmentYear) 
    {
        this.assessmentYear = assessmentYear;
    }

    public String getAssessmentYear() 
    {
        return assessmentYear;
    }
    public void setPublicityStartTime(String publicityStartTime) 
    {
        this.publicityStartTime = publicityStartTime;
    }

    public String getPublicityStartTime() 
    {
        return publicityStartTime;
    }
    public void setPublicityEndTime(String publicityEndTime) 
    {
        this.publicityEndTime = publicityEndTime;
    }

    public String getPublicityEndTime() 
    {
        return publicityEndTime;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public String getAuditFlag() {
        return auditFlag;
    }

    public void setAuditFlag(String auditFlag) {
        this.auditFlag = auditFlag;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("reportTime", getReportTime())
            .append("deptId", getDeptId())
            .append("content", getContent())
            .append("isReport", getIsReport())
            .append("isPublicity", getIsPublicity())
            .append("assessmentYear", getAssessmentYear())
            .append("publicityStartTime", getPublicityStartTime())
            .append("publicityEndTime", getPublicityEndTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
