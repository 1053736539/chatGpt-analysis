package com.cb.assess.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 年度处室（单位）年度考核优秀评议对象 biz_assess_dept_excellent_evaluation
 * 
 * @author ruoyi
 * @date 2023-12-09
 */
public class BizAssessDeptExcellentEvaluation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 被评人 */
    @Excel(name = "被评人")
    private Long userId;

    /** $column.columnComment */
    @Excel(name = "被评人")
    private Long deptId;

    /** 评议人id */
    @Excel(name = "评议人id")
    private Long discussantUserId;

    /** 考核年度 */
    @Excel(name = "考核年度")
    private String assessmentYear;

    /** 0未评议，1已评议 */
    @Excel(name = "0未评议，1已评议")
    private String status;

    /** 年度考核推荐的等次 */
    @Excel(name = "年度考核推荐的等次")
    private String recommendGrade;

    /** $column.columnComment */
    private String delFlag;
    //是否上报人事处
    private String escalation;
    private String identityType;

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getEscalation() {
        return escalation;
    }

    public void setEscalation(String escalation) {
        this.escalation = escalation;
    }

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
    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }
    public void setDiscussantUserId(Long discussantUserId)
    {
        this.discussantUserId = discussantUserId;
    }

    public Long getDiscussantUserId()
    {
        return discussantUserId;
    }
    public void setAssessmentYear(String assessmentYear) 
    {
        this.assessmentYear = assessmentYear;
    }

    public String getAssessmentYear() 
    {
        return assessmentYear;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setRecommendGrade(String recommendGrade) 
    {
        this.recommendGrade = recommendGrade;
    }

    public String getRecommendGrade() 
    {
        return recommendGrade;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("discussantUserId", getDiscussantUserId())
            .append("assessmentYear", getAssessmentYear())
            .append("status", getStatus())
            .append("recommendGrade", getRecommendGrade())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
