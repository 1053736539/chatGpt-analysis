package com.cb.assess.domain;

import java.util.Date;
import com.cb.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cb.common.annotation.Excel;

/**
 * 年度考核对象 biz_assess_annual_assessment
 * 
 * @author ruoyi
 * @date 2023-11-17
 */
public class BizAssessAnnualAssessment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** biz_assess_task_user_tag的ID */
    @Excel(name = "biz_assess_task_user_tag的ID")
    private String userTagId;

    /** 部门及职务 */
    @Excel(name = "部门及职务")
    private String post;

    /** 从事或分管工作 */
    @Excel(name = "从事或分管工作")
    private String responsibilities;

    /** 个人总结 */
    @Excel(name = "个人总结")
    private String personalSummary;

    /** 政治面貌 */
    @Excel(name = "政治面貌")
    private String politicalIdentity;

    /** 现任职务时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "现任职务时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String officeTime;

    /** 奖惩情况与不良行为记录 */
    @Excel(name = "奖惩情况与不良行为记录")
    private String rewardsPunishMisconduct;

    /** 主管领导点评和考核等次建议 */
    @Excel(name = "主管领导点评和考核等次建议")
    private String evaluationLevelSuggest;

    /** 机关审定意见 */
    @Excel(name = "机关审定意见")
    private String organOpinions;

    /** 未确定等次或不参加考核情况说明 */
    @Excel(name = "未确定等次或不参加考核情况说明")
    private String othersRemark;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Integer auditStatus;

    /** 编制类型 0 公务员 1 事业编 */
    @Excel(name = "编制类型 0 公务员 1 事业编")
    private String establishType;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private Long deptId;

    /** 本人意见签名 */
    @Excel(name = "本人意见签名")
    private String myOpinionSign;
    /**
     * 本人意见
     */
    private String myOpinion;
    /**
     * 主管领导盖章
     */
    private String evaluationLevelStamp;
    /**
     * 机关审核意见盖章
     */
    private String institutionalReviewStamp;

    /** 本人意见签名时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "本人意见签名时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date myOpinionTime;

    /** 主管领导签名 */
    @Excel(name = "主管领导签名")
    private String evaluationLevelSign;

    /** 主管领导签名时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "主管领导签名时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String evaluationLevelTime;

    /** 机关负责人签名 */
    @Excel(name = "机关负责人签名")
    private String organOpinionsSign;

    /** 个人总结签名 */
    @Excel(name = "个人总结签名")
    private String mySummarySign;

    /** 个人总结签名时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "个人总结签名时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date mySummaryTime;

    /** 机关负责人签名时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "机关负责人签名时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String organOpinionsTime;

    /** 需要说明的情况 */
    @Excel(name = "需要说明的情况")
    private String illustratsSituation;

    /** 需要说明的情况签名 */
    @Excel(name = "需要说明的情况签名")
    private String illustratsSituationSign;

    /** 需要说明的情况签名时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "需要说明的情况签名时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date illustratsSituationTime;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 年份 */
    @Excel(name = "年份")
    private String year;
    private String workTitle;
    //脱产培训情况
    private String trainingSituation;

    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public String getOfficeTime() {
        return officeTime;
    }

    public void setOfficeTime(String officeTime) {
        this.officeTime = officeTime;
    }

    public String getEvaluationLevelStamp() {
        return evaluationLevelStamp;
    }

    public void setEvaluationLevelStamp(String evaluationLevelStamp) {
        this.evaluationLevelStamp = evaluationLevelStamp;
    }

    public String getInstitutionalReviewStamp() {
        return institutionalReviewStamp;
    }

    public void setInstitutionalReviewStamp(String institutionalReviewStamp) {
        this.institutionalReviewStamp = institutionalReviewStamp;
    }

    public String getMyOpinion() {
        return myOpinion;
    }

    public void setMyOpinion(String myOpinion) {
        this.myOpinion = myOpinion;
    }

    public String getTrainingSituation() {
        return trainingSituation;
    }

    public void setTrainingSituation(String trainingSituation) {
        this.trainingSituation = trainingSituation;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setUserTagId(String userTagId)
    {
        this.userTagId = userTagId;
    }

    public String getUserTagId()
    {
        return userTagId;
    }
    public void setPost(String post)
    {
        this.post = post;
    }

    public String getPost()
    {
        return post;
    }
    public void setResponsibilities(String responsibilities)
    {
        this.responsibilities = responsibilities;
    }

    public String getResponsibilities()
    {
        return responsibilities;
    }
    public void setPersonalSummary(String personalSummary)
    {
        this.personalSummary = personalSummary;
    }

    public String getPersonalSummary()
    {
        return personalSummary;
    }
    public void setPoliticalIdentity(String politicalIdentity)
    {
        this.politicalIdentity = politicalIdentity;
    }

    public String getPoliticalIdentity()
    {
        return politicalIdentity;
    }

    public void setRewardsPunishMisconduct(String rewardsPunishMisconduct)
    {
        this.rewardsPunishMisconduct = rewardsPunishMisconduct;
    }

    public String getRewardsPunishMisconduct()
    {
        return rewardsPunishMisconduct;
    }
    public void setEvaluationLevelSuggest(String evaluationLevelSuggest)
    {
        this.evaluationLevelSuggest = evaluationLevelSuggest;
    }

    public String getEvaluationLevelSuggest()
    {
        return evaluationLevelSuggest;
    }
    public void setOrganOpinions(String organOpinions)
    {
        this.organOpinions = organOpinions;
    }

    public String getOrganOpinions()
    {
        return organOpinions;
    }
    public void setOthersRemark(String othersRemark)
    {
        this.othersRemark = othersRemark;
    }

    public String getOthersRemark()
    {
        return othersRemark;
    }
    public void setAuditStatus(Integer auditStatus)
    {
        this.auditStatus = auditStatus;
    }

    public Integer getAuditStatus()
    {
        return auditStatus;
    }
    public void setEstablishType(String establishType)
    {
        this.establishType = establishType;
    }

    public String getEstablishType()
    {
        return establishType;
    }
    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }
    public void setMyOpinionSign(String myOpinionSign)
    {
        this.myOpinionSign = myOpinionSign;
    }

    public String getMyOpinionSign()
    {
        return myOpinionSign;
    }
    public void setMyOpinionTime(Date myOpinionTime)
    {
        this.myOpinionTime = myOpinionTime;
    }

    public Date getMyOpinionTime()
    {
        return myOpinionTime;
    }
    public void setEvaluationLevelSign(String evaluationLevelSign)
    {
        this.evaluationLevelSign = evaluationLevelSign;
    }

    public String getEvaluationLevelSign()
    {
        return evaluationLevelSign;
    }
    public void setEvaluationLevelTime(String evaluationLevelTime)
    {
        this.evaluationLevelTime = evaluationLevelTime;
    }

    public String getEvaluationLevelTime()
    {
        return evaluationLevelTime;
    }
    public void setOrganOpinionsSign(String organOpinionsSign)
    {
        this.organOpinionsSign = organOpinionsSign;
    }

    public String getOrganOpinionsSign()
    {
        return organOpinionsSign;
    }
    public void setMySummarySign(String mySummarySign)
    {
        this.mySummarySign = mySummarySign;
    }

    public String getMySummarySign()
    {
        return mySummarySign;
    }
    public void setMySummaryTime(Date mySummaryTime)
    {
        this.mySummaryTime = mySummaryTime;
    }

    public Date getMySummaryTime()
    {
        return mySummaryTime;
    }
    public void setOrganOpinionsTime(String organOpinionsTime)
    {
        this.organOpinionsTime = organOpinionsTime;
    }

    public String getOrganOpinionsTime()
    {
        return organOpinionsTime;
    }
    public void setIllustratsSituation(String illustratsSituation)
    {
        this.illustratsSituation = illustratsSituation;
    }

    public String getIllustratsSituation()
    {
        return illustratsSituation;
    }
    public void setIllustratsSituationSign(String illustratsSituationSign)
    {
        this.illustratsSituationSign = illustratsSituationSign;
    }

    public String getIllustratsSituationSign()
    {
        return illustratsSituationSign;
    }
    public void setIllustratsSituationTime(Date illustratsSituationTime)
    {
        this.illustratsSituationTime = illustratsSituationTime;
    }

    public Date getIllustratsSituationTime()
    {
        return illustratsSituationTime;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setYear(String year)
    {
        this.year = year;
    }

    public String getYear()
    {
        return year;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userTagId", getUserTagId())
            .append("post", getPost())
            .append("responsibilities", getResponsibilities())
            .append("personalSummary", getPersonalSummary())
            .append("politicalIdentity", getPoliticalIdentity())
            .append("officeTime", getOfficeTime())
            .append("rewardsPunishMisconduct", getRewardsPunishMisconduct())
            .append("evaluationLevelSuggest", getEvaluationLevelSuggest())
            .append("organOpinions", getOrganOpinions())
            .append("othersRemark", getOthersRemark())
            .append("auditStatus", getAuditStatus())
            .append("establishType", getEstablishType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("deptId", getDeptId())
            .append("myOpinionSign", getMyOpinionSign())
            .append("myOpinionTime", getMyOpinionTime())
            .append("evaluationLevelSign", getEvaluationLevelSign())
            .append("evaluationLevelTime", getEvaluationLevelTime())
            .append("organOpinionsSign", getOrganOpinionsSign())
            .append("mySummarySign", getMySummarySign())
            .append("mySummaryTime", getMySummaryTime())
            .append("organOpinionsTime", getOrganOpinionsTime())
            .append("illustratsSituation", getIllustratsSituation())
            .append("illustratsSituationSign", getIllustratsSituationSign())
            .append("illustratsSituationTime", getIllustratsSituationTime())
            .append("userId", getUserId())
            .append("year", getYear())
            .toString();
    }
}
