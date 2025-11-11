package com.cb.assess.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 省统计局二级巡视员、总师、处（室）负责人年度考核评议B类对象 biz_assess_annual_review_type_b
 * 
 * @author ruoyi
 * @date 2023-11-24
 */
public class BizAssessAnnualReviewTypeB extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 被评议人 */
    @Excel(name = "被评议人")
    private Long userId;

    /** 评议人 */
    @Excel(name = "评议人")
    private Long discussantUserId;

    /** 年度考核推荐的等次 */
    @Excel(name = "年度考核推荐的等次")
    private String recommendGrade;

    /** $column.columnComment */
    private String delFlag;
    private String assessmentYear;
    private String status;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getAssessmentYear() {
        return assessmentYear;
    }

    public void setAssessmentYear(String assessmentYear) {
        this.assessmentYear = assessmentYear;
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
    public void setDiscussantUserId(Long discussantUserId)
    {
        this.discussantUserId = discussantUserId;
    }

    public Long getDiscussantUserId()
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
            .toString();
    }
}
