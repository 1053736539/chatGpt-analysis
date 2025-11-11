package com.cb.assess.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * AB类评分结果，对象 biz_assess_annual_review_type_result
 * 
 * @author ruoyi
 * @date 2023-12-16
 */
public class BizAssessAnnualReviewTypeResult extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long userId;

    /** 考核年度 */
    @Excel(name = "考核年度")
    private String assessmentYear;

    /** 职务 */
    @Excel(name = "职务")
    private String workPost;

    /** A类优秀票数 */
    @Excel(name = "A类优秀票数")
    private Integer aTypeVotes;

    /** B类优秀票数 */
    @Excel(name = "B类优秀票数")
    private Integer bTypeVotes;

    /** a类优秀得分 */
    @Excel(name = "a类优秀得分")
    private Float aTypeScore;

    /** b类优秀得分 */
    @Excel(name = "b类优秀得分")
    private Float bTypeScore;

    /** a类投票人数 */
    @Excel(name = "a类投票人数")
    private Integer aTotalNum;

    /** b类投票人数 */
    @Excel(name = "b类投票人数")
    private Integer bTotalNum;

    /** 总分 */
    @Excel(name = "总分")
    private Float totalScore;

    /** 是否公示，0未公示，1已公示 */
    @Excel(name = "是否公示，0未公示，1已公示")
    private String isPublicity;

    /** 最后的推荐等次结果 */
    @Excel(name = "最后的推荐等次结果")
    private String recommendGrade;

    /** $column.columnComment */
    private String delFlag;
    private String type;
    private String canEdit;
    private Long deptId;

    public String getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(String canEdit) {
        this.canEdit = canEdit;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    public void setAssessmentYear(String assessmentYear) 
    {
        this.assessmentYear = assessmentYear;
    }

    public String getAssessmentYear() 
    {
        return assessmentYear;
    }
    public void setWorkPost(String workPost) 
    {
        this.workPost = workPost;
    }

    public String getWorkPost() 
    {
        return workPost;
    }
    public void setaTypeVotes(Integer aTypeVotes) 
    {
        this.aTypeVotes = aTypeVotes;
    }

    public Integer getaTypeVotes() 
    {
        return aTypeVotes;
    }
    public void setbTypeVotes(Integer bTypeVotes) 
    {
        this.bTypeVotes = bTypeVotes;
    }

    public Integer getbTypeVotes() 
    {
        return bTypeVotes;
    }
    public void setaTypeScore(Float aTypeScore)
    {
        this.aTypeScore = aTypeScore;
    }

    public Float getaTypeScore()
    {
        return aTypeScore;
    }
    public void setbTypeScore(Float bTypeScore)
    {
        this.bTypeScore = bTypeScore;
    }

    public Float getbTypeScore()
    {
        return bTypeScore;
    }
    public void setaTotalNum(Integer aTotalNum) 
    {
        this.aTotalNum = aTotalNum;
    }

    public Integer getaTotalNum() 
    {
        return aTotalNum;
    }
    public void setbTotalNum(Integer bTotalNum) 
    {
        this.bTotalNum = bTotalNum;
    }

    public Integer getbTotalNum() 
    {
        return bTotalNum;
    }
    public void setTotalScore(Float totalScore)
    {
        this.totalScore = totalScore;
    }

    public Float getTotalScore()
    {
        return totalScore;
    }
    public void setIsPublicity(String isPublicity) 
    {
        this.isPublicity = isPublicity;
    }

    public String getIsPublicity() 
    {
        return isPublicity;
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
            .append("assessmentYear", getAssessmentYear())
            .append("workPost", getWorkPost())
            .append("aTypeVotes", getaTypeVotes())
            .append("bTypeVotes", getbTypeVotes())
            .append("aTypeScore", getaTypeScore())
            .append("bTypeScore", getbTypeScore())
            .append("aTotalNum", getaTotalNum())
            .append("bTotalNum", getbTotalNum())
            .append("totalScore", getTotalScore())
            .append("isPublicity", getIsPublicity())
            .append("recommendGrade", getRecommendGrade())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
