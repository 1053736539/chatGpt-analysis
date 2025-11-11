package com.cb.assess.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 年度处室（单位）年度考核优秀评议(最终部门上报结果)对象 biz_assess_dept_excellent_evaluation_result
 * 
 * @author ruoyi
 * @date 2023-12-11
 */
public class BizAssessDeptExcellentEvaluationResult extends BaseEntity
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

    /** 考核年度 */
    @Excel(name = "考核年度")
    private String assessmentYear;

    /** 被推荐的等次 */
    @Excel(name = "被推荐的等次")
    private String recommendGrade;

    /** $column.columnComment */
    private String delFlag;

    /** 优秀票数 */
    @Excel(name = "优秀票数")
    private Integer recommendGradeExcellent;

    /** 投票总人数 */
    @Excel(name = "投票总人数")
    private Integer number;
    /** 已评价人数 */
    @Excel(name = "已评价人数")
    private Integer reviewed;

    /** 是否上报人事处，0未上报，1已上报 */
    @Excel(name = "是否上报人事处，0未上报，1已上报")
    private String escalation;
    private String identityType;
    private String isPublicity;
    /**
     * 平时考核的json，手动录入的时候要填
     */
    private String regularAssessment;
    /**
     * 是否是手动录入的标记，0不是手动录入，1是手动录入
     */
    private String isEntry;

    public String getRegularAssessment() {
        return regularAssessment;
    }

    public void setRegularAssessment(String regularAssessment) {
        this.regularAssessment = regularAssessment;
    }

    public String getIsEntry() {
        return isEntry;
    }

    public void setIsEntry(String isEntry) {
        this.isEntry = isEntry;
    }
    public String getIsPublicity() {
        return isPublicity;
    }

    public void setIsPublicity(String isPublicity) {
        this.isPublicity = isPublicity;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }
    public Integer getReviewed() {
        return reviewed;
    }

    public void setReviewed(Integer reviewed) {
        this.reviewed = reviewed;
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
    public void setAssessmentYear(String assessmentYear) 
    {
        this.assessmentYear = assessmentYear;
    }

    public String getAssessmentYear() 
    {
        return assessmentYear;
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
    public void setRecommendGradeExcellent(Integer recommendGradeExcellent) 
    {
        this.recommendGradeExcellent = recommendGradeExcellent;
    }

    public Integer getRecommendGradeExcellent() 
    {
        return recommendGradeExcellent;
    }
    public void setNumber(Integer number) 
    {
        this.number = number;
    }

    public Integer getNumber() 
    {
        return number;
    }
    public void setEscalation(String escalation) 
    {
        this.escalation = escalation;
    }

    public String getEscalation() 
    {
        return escalation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("assessmentYear", getAssessmentYear())
            .append("recommendGrade", getRecommendGrade())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("delFlag", getDelFlag())
            .append("recommendGradeExcellent", getRecommendGradeExcellent())
            .append("number", getNumber())
            .append("escalation", getEscalation())
            .toString();
    }
}
