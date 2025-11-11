package com.cb.assess.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 机关参公和事业单位等次对象 biz_assess_assessment_grade
 * 
 * @author ruoyi
 * @date 2023-11-25
 */
public class BizAssessAssessmentGrade extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 被评议人 */
    @Excel(name = "被评议人")
    private Long userId;

    /** 评议人 */
    @Excel(name = "评议人")
    private Integer discussantUserId;

    /** 年度考核推荐的等次 */
    @Excel(name = "年度考核推荐的等次")
    private String recommendGrade;

    /** $column.columnComment */
    private String delFlag;

    /** 考核年度 */
    @Excel(name = "考核年度")
    private String assessmentYear;

    /** 0未评议，1已评议 */
    @Excel(name = "0未评议，1已评议")
    private String status;

    /** 类型，1为参公，2为事业单位 */
    @Excel(name = "类型，1为参公，2为事业单位")
    private String type;
    private Long deptId;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
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
    public void setDiscussantUserId(Integer discussantUserId) 
    {
        this.discussantUserId = discussantUserId;
    }

    public Integer getDiscussantUserId() 
    {
        return discussantUserId;
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
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("discussantUserId", getDiscussantUserId())
            .append("recommendGrade", getRecommendGrade())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("assessmentYear", getAssessmentYear())
            .append("status", getStatus())
            .append("type", getType())
            .toString();
    }
}
