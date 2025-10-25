package com.cb.common.core.domain.entity;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户年度考核信息对象 sys_user_annual_appraisal_info
 *
 * @author hu
 * @date 2023-10-29
 */
public class SysUserAnnualAppraisalInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String annualAppraisalInfoId;

    /**
     * 用户ID
     */
    @Excel(name = "用户ID")
    private Long userId;

    /**
     * 序号
     */
    @Excel(name = "序号")
    private String sortNum;

    /**
     * 考核年度
     */
    @Excel(name = "考核年度")
    private String assessmentYear;

    /**
     * 考核结论
     */
    @Excel(name = "考核结论")
    private String assessmentConclusions;

    /**
     * 考核描述
     */
    @Excel(name = "考核描述")
    private String assessmentDesc;

    /**
     * 考核结果
     */
    @Excel(name = "考核结果")
    private String assessmentResults;

    /**
     * 删除标记 0-未删除 1-已删除
     */
    private String delFlag;

    public void setAnnualAppraisalInfoId(String annualAppraisalInfoId) {
        this.annualAppraisalInfoId = annualAppraisalInfoId;
    }

    public String getAnnualAppraisalInfoId() {
        return annualAppraisalInfoId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setSortNum(String sortNum) {
        this.sortNum = sortNum;
    }

    public String getSortNum() {
        return sortNum;
    }

    public void setAssessmentYear(String assessmentYear) {
        this.assessmentYear = assessmentYear;
    }

    public String getAssessmentYear() {
        return assessmentYear;
    }

    public void setAssessmentConclusions(String assessmentConclusions) {
        this.assessmentConclusions = assessmentConclusions;
    }

    public String getAssessmentConclusions() {
        return assessmentConclusions;
    }

    public void setAssessmentDesc(String assessmentDesc) {
        this.assessmentDesc = assessmentDesc;
    }

    public String getAssessmentDesc() {
        return assessmentDesc;
    }

    public void setAssessmentResults(String assessmentResults) {
        this.assessmentResults = assessmentResults;
    }

    public String getAssessmentResults() {
        return assessmentResults;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("annualAppraisalInfoId", getAnnualAppraisalInfoId())
                .append("userId", getUserId())
                .append("sortNum", getSortNum())
                .append("assessmentYear", getAssessmentYear())
                .append("assessmentConclusions", getAssessmentConclusions())
                .append("assessmentDesc", getAssessmentDesc())
                .append("assessmentResults", getAssessmentResults())
                .append("delFlag", getDelFlag())
                .toString();
    }
}