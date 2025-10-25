package com.cb.common.core.domain.entity;

import com.cb.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 专业技术职务信息对象 sys_user_technical_position_info
 *
 * @author hu
 * @date 2023-10-28
 */
public class SysUserTechnicalPositionInfo {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String technicalPositionInfoId;

    /**
     * 用户ID
     */
    @Excel(name = "用户ID")
    private Long userId;

    /**
     * 专业技术资格代码
     */
    @Excel(name = "专业技术资格代码")
    private String technicalQualificationCode;

    /**
     * 专业技术资格名称
     */
    @Excel(name = "专业技术资格名称")
    private String technicalQualificationName;

    /**
     * 获得资格日期
     */
    @Excel(name = "获得资格日期")
    private String qualificationDate;

    /**
     * 获得资格途径
     */
    @Excel(name = "获得资格途径")
    private String qualificationRoad;

    /**
     * 评委会或考试名称
     */
    @Excel(name = "评委会或考试名称")
    private String juryOrExamName;

    /**
     * 删除标记 0-未删除 1-已删除
     */
    private String delFlag;

    public void setTechnicalPositionInfoId(String technicalPositionInfoId) {
        this.technicalPositionInfoId = technicalPositionInfoId;
    }

    public String getTechnicalPositionInfoId() {
        return technicalPositionInfoId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setTechnicalQualificationCode(String technicalQualificationCode) {
        this.technicalQualificationCode = technicalQualificationCode;
    }

    public String getTechnicalQualificationCode() {
        return technicalQualificationCode;
    }

    public void setTechnicalQualificationName(String technicalQualificationName) {
        this.technicalQualificationName = technicalQualificationName;
    }

    public String getTechnicalQualificationName() {
        return technicalQualificationName;
    }

    public void setQualificationDate(String qualificationDate) {
        this.qualificationDate = qualificationDate;
    }

    public String getQualificationDate() {
        return qualificationDate;
    }

    public void setQualificationRoad(String qualificationRoad) {
        this.qualificationRoad = qualificationRoad;
    }

    public String getQualificationRoad() {
        return qualificationRoad;
    }

    public void setJuryOrExamName(String juryOrExamName) {
        this.juryOrExamName = juryOrExamName;
    }

    public String getJuryOrExamName() {
        return juryOrExamName;
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
                .append("technicalPositionInfoId", getTechnicalPositionInfoId())
                .append("userId", getUserId())
                .append("technicalQualificationCode", getTechnicalQualificationCode())
                .append("technicalQualificationName", getTechnicalQualificationName())
                .append("qualificationDate", getQualificationDate())
                .append("qualificationRoad", getQualificationRoad())
                .append("juryOrExamName", getJuryOrExamName())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
