package com.cb.probity.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 廉政档案-1.报告人基本情况对象 biz_probity_basic
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public class BizProbityBasic extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * probity_id
     */
    @Excel(name = "probity_id")
    private String probityId;

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String name;

    /**
     * 性别
     */
    @Excel(name = "性别")
    private String gender;

    /**
     * 出生年月
     */
    @Excel(name = "出生年月")
    private String birth;

    /**
     * 民族
     */
    @Excel(name = "民族")
    private String nation;

    /**
     * 籍贯
     */
    @Excel(name = "籍贯")
    private String nativePlace;

    /**
     * 出生地
     */
    @Excel(name = "出生地")
    private String birthPlace;

    /**
     * 政治面貌
     */
    @Excel(name = "政治面貌")
    private String politicalStatus;

    /**
     * 入党时间
     */
    @Excel(name = "入党时间")
    private String partyJoinTime;

    /**
     * 参加工作时间
     */
    @Excel(name = "参加工作时间")
    private String startWorkTime;

    /**
     * 全日制教育
     */
    @Excel(name = "全日制教育")
    private String fullTimeEduLevel;

    /**
     * 全日制教育-毕业院校系及专业
     */
    @Excel(name = "全日制教育-毕业院校系及专业")
    private String fullTimeEduSchoolMajor;

    /**
     * 在职教育
     */
    @Excel(name = "在职教育")
    private String onJobEduLevel;

    /**
     * 在职教育-毕业院校系及专业
     */
    @Excel(name = "在职教育-毕业院校系及专业")
    private String onJobEduSchoolMajor;

    /**
     * 工作单位
     */
    @Excel(name = "工作单位")
    private String workDept;

    /**
     * 现任职务
     */
    @Excel(name = "现任职务")
    private String currentPosition;

    /**
     * 职级
     */
    @Excel(name = "职级")
    private String positionLevel;

    /**
     * 是否党代表、人大代表、政协委员
     */
    @Excel(name = "是否党代表、人大代表、政协委员")
    private String delegateFlag;

    /**
     * 身份证号码
     */
    @Excel(name = "身份证号码")
    private String idNo;

    /**
     * 家庭住址
     */
    @Excel(name = "家庭住址")
    private String homeAddr;

    /**
     * 工作简历
     */
    @Excel(name = "工作简历")
    private String workResume;

    /**
     * 奖惩情况
     */
    @Excel(name = "奖惩情况")
    private String rewardPunishment;

    /**
     * 年度考核结果
     */
    @Excel(name = "年度考核结果")
    private String annualAssessment;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setProbityId(String probityId) {
        this.probityId = probityId;
    }

    public String getProbityId() {
        return probityId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getBirth() {
        return birth;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNation() {
        return nation;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPartyJoinTime(String partyJoinTime) {
        this.partyJoinTime = partyJoinTime;
    }

    public String getPartyJoinTime() {
        return partyJoinTime;
    }

    public void setStartWorkTime(String startWorkTime) {
        this.startWorkTime = startWorkTime;
    }

    public String getStartWorkTime() {
        return startWorkTime;
    }

    public void setFullTimeEduLevel(String fullTimeEduLevel) {
        this.fullTimeEduLevel = fullTimeEduLevel;
    }

    public String getFullTimeEduLevel() {
        return fullTimeEduLevel;
    }

    public void setFullTimeEduSchoolMajor(String fullTimeEduSchoolMajor) {
        this.fullTimeEduSchoolMajor = fullTimeEduSchoolMajor;
    }

    public String getFullTimeEduSchoolMajor() {
        return fullTimeEduSchoolMajor;
    }

    public void setOnJobEduLevel(String onJobEduLevel) {
        this.onJobEduLevel = onJobEduLevel;
    }

    public String getOnJobEduLevel() {
        return onJobEduLevel;
    }

    public void setOnJobEduSchoolMajor(String onJobEduSchoolMajor) {
        this.onJobEduSchoolMajor = onJobEduSchoolMajor;
    }

    public String getOnJobEduSchoolMajor() {
        return onJobEduSchoolMajor;
    }

    public void setWorkDept(String workDept) {
        this.workDept = workDept;
    }

    public String getWorkDept() {
        return workDept;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setPositionLevel(String positionLevel) {
        this.positionLevel = positionLevel;
    }

    public String getPositionLevel() {
        return positionLevel;
    }

    public void setDelegateFlag(String delegateFlag) {
        this.delegateFlag = delegateFlag;
    }

    public String getDelegateFlag() {
        return delegateFlag;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setHomeAddr(String homeAddr) {
        this.homeAddr = homeAddr;
    }

    public String getHomeAddr() {
        return homeAddr;
    }

    public void setWorkResume(String workResume) {
        this.workResume = workResume;
    }

    public String getWorkResume() {
        return workResume;
    }

    public void setRewardPunishment(String rewardPunishment) {
        this.rewardPunishment = rewardPunishment;
    }

    public String getRewardPunishment() {
        return rewardPunishment;
    }

    public void setAnnualAssessment(String annualAssessment) {
        this.annualAssessment = annualAssessment;
    }

    public String getAnnualAssessment() {
        return annualAssessment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("probityId", getProbityId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("name", getName())
                .append("gender", getGender())
                .append("birth", getBirth())
                .append("nation", getNation())
                .append("nativePlace", getNativePlace())
                .append("birthPlace", getBirthPlace())
                .append("politicalStatus", getPoliticalStatus())
                .append("partyJoinTime", getPartyJoinTime())
                .append("startWorkTime", getStartWorkTime())
                .append("fullTimeEduLevel", getFullTimeEduLevel())
                .append("fullTimeEduSchoolMajor", getFullTimeEduSchoolMajor())
                .append("onJobEduLevel", getOnJobEduLevel())
                .append("onJobEduSchoolMajor", getOnJobEduSchoolMajor())
                .append("workDept", getWorkDept())
                .append("currentPosition", getCurrentPosition())
                .append("positionLevel", getPositionLevel())
                .append("delegateFlag", getDelegateFlag())
                .append("idNo", getIdNo())
                .append("homeAddr", getHomeAddr())
                .append("workResume", getWorkResume())
                .append("rewardPunishment", getRewardPunishment())
                .append("annualAssessment", getAnnualAssessment())
                .toString();
    }
}
