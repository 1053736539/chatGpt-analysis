package com.cb.assess.domain;

import com.cb.common.core.domain.BaseEntity;
import com.cb.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 政治素质鉴定对象 biz_assess_cadre_political_quality_identify
 * 
 * @author ruoyi
 * @date 2023-12-22
 */
public class BizAssessCadrePoliticalQualityIdentify extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    private Long userId;
    private Long managerUserId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long deptId;

    /** 职务 */
    @Excel(name = "职务")
    private String workPost;

    /** 自查自评情况 */
    @Excel(name = "自查自评情况")
    private String selfAssessment;

    /** 发出政治素质考察表份数 */
    @Excel(name = "发出政治素质考察表份数")
    private Integer votes;

    /** 收回票数 */
    @Excel(name = "收回票数")
    private Integer recoverVotes;

    /** 有效票数 */
    @Excel(name = "有效票数")
    private Integer effectiveVotes;

    /** 好的票数 */
    @Excel(name = "好的票数")
    private Integer goodVotes;

    /** 较好票数 */
    @Excel(name = "较好票数")
    private Integer preferablyVotes;

    /** 一般票数 */
    @Excel(name = "一般票数")
    private Integer generalVotes;

    /** 较差票数 */
    @Excel(name = "较差票数")
    private Integer badVotes;

    /** 主管领导评语 */
    @Excel(name = "主管领导评语")
    private String leaderComment;

    /** 党委（党组鉴定意见） */
    @Excel(name = "党委", readConverterExp = "党=组鉴定意见")
    private String expertOpinion;

    /** 是否上报人事处，0未上报，1已上报 */
    @Excel(name = "是否上报人事处，0未上报，1已上报")
    private String isReport;

    /** 考核年度 */
    @Excel(name = "考核年度")
    private String assessmentYear;


    /** $column.columnComment */
    private String delFlag;

    private String leaderCommentGrade;
    private String finalIdentify;

    public Long getManagerUserId() {
        return managerUserId;
    }

    public void setManagerUserId(Long managerUserId) {
        this.managerUserId = managerUserId;
    }

    public String getFinalIdentify() {
        return finalIdentify;
    }

    public void setFinalIdentify(String finalIdentify) {
        this.finalIdentify = finalIdentify;
    }

    public String getLeaderCommentGrade() {
        return leaderCommentGrade;
    }

    public void setLeaderCommentGrade(String leaderCommentGrade) {
        this.leaderCommentGrade = leaderCommentGrade;
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
    public void setWorkPost(String workPost) 
    {
        this.workPost = workPost;
    }

    public String getWorkPost() 
    {
        return workPost;
    }
    public void setSelfAssessment(String selfAssessment) 
    {
        this.selfAssessment = selfAssessment;
    }

    public String getSelfAssessment() 
    {
        return selfAssessment;
    }
    public void setVotes(Integer votes) 
    {
        this.votes = votes;
    }

    public Integer getVotes() 
    {
        return votes;
    }
    public void setRecoverVotes(Integer recoverVotes) 
    {
        this.recoverVotes = recoverVotes;
    }

    public Integer getRecoverVotes() 
    {
        return recoverVotes;
    }
    public void setEffectiveVotes(Integer effectiveVotes) 
    {
        this.effectiveVotes = effectiveVotes;
    }

    public Integer getEffectiveVotes() 
    {
        return effectiveVotes;
    }
    public void setGoodVotes(Integer goodVotes) 
    {
        this.goodVotes = goodVotes;
    }

    public Integer getGoodVotes() 
    {
        return goodVotes;
    }
    public void setPreferablyVotes(Integer preferablyVotes) 
    {
        this.preferablyVotes = preferablyVotes;
    }

    public Integer getPreferablyVotes() 
    {
        return preferablyVotes;
    }
    public void setGeneralVotes(Integer generalVotes) 
    {
        this.generalVotes = generalVotes;
    }

    public Integer getGeneralVotes() 
    {
        return generalVotes;
    }
    public void setBadVotes(Integer badVotes) 
    {
        this.badVotes = badVotes;
    }

    public Integer getBadVotes() 
    {
        return badVotes;
    }
    public void setLeaderComment(String leaderComment) 
    {
        this.leaderComment = leaderComment;
    }

    public String getLeaderComment() 
    {
        return leaderComment;
    }
    public void setExpertOpinion(String expertOpinion) 
    {
        this.expertOpinion = expertOpinion;
    }

    public String getExpertOpinion() 
    {
        return expertOpinion;
    }
    public void setIsReport(String isReport) 
    {
        this.isReport = isReport;
    }

    public String getIsReport() 
    {
        return isReport;
    }
    public void setAssessmentYear(String assessmentYear) 
    {
        this.assessmentYear = assessmentYear;
    }

    public String getAssessmentYear() 
    {
        return assessmentYear;
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
            .append("workPost", getWorkPost())
            .append("selfAssessment", getSelfAssessment())
            .append("votes", getVotes())
            .append("recoverVotes", getRecoverVotes())
            .append("effectiveVotes", getEffectiveVotes())
            .append("goodVotes", getGoodVotes())
            .append("preferablyVotes", getPreferablyVotes())
            .append("generalVotes", getGeneralVotes())
            .append("badVotes", getBadVotes())
            .append("leaderComment", getLeaderComment())
            .append("expertOpinion", getExpertOpinion())
            .append("isReport", getIsReport())
            .append("assessmentYear", getAssessmentYear())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
