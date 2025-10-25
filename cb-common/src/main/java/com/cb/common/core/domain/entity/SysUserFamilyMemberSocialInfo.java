package com.cb.common.core.domain.entity;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 家庭成员及主要社会关系信息对象 sys_user_family_member_social_info
 *
 * @author hu
 * @date 2023-10-29
 */
public class SysUserFamilyMemberSocialInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String familyMemberSocialInfoId;

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
     * 称谓
     */
    @Excel(name = "称谓")
    private String appellation;

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String familyMembersName;

    /**
     * 出生日期
     */
    @Excel(name = "出生日期")
    private String birthDate;

    /**
     * 政治面貌
     */
    @Excel(name = "政治面貌")
    private String politicalOutlook;

    /**
     * 工作单位及职务
     */
    @Excel(name = "工作单位及职务")
    private String workUnitAndPosition;

    /**
     * 情况说明
     */
    @Excel(name = "情况说明")
    private String situationDesc;

    /**
     * 删除标记 0-未删除 1-已删除
     */
    private String delFlag;

    /**
     * 联系电话
     * @param 联系电话
     */
    private String contactNumber;

    public void setFamilyMemberSocialInfoId(String familyMemberSocialInfoId) {
        this.familyMemberSocialInfoId = familyMemberSocialInfoId;
    }

    public String getFamilyMemberSocialInfoId() {
        return familyMemberSocialInfoId;
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

    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

    public String getAppellation() {
        return appellation;
    }

    public void setFamilyMembersName(String familyMembersName) {
        this.familyMembersName = familyMembersName;
    }

    public String getFamilyMembersName() {
        return familyMembersName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setPoliticalOutlook(String politicalOutlook) {
        this.politicalOutlook = politicalOutlook;
    }

    public String getPoliticalOutlook() {
        return politicalOutlook;
    }

    public void setWorkUnitAndPosition(String workUnitAndPosition) {
        this.workUnitAndPosition = workUnitAndPosition;
    }

    public String getWorkUnitAndPosition() {
        return workUnitAndPosition;
    }

    public void setSituationDesc(String situationDesc) {
        this.situationDesc = situationDesc;
    }

    public String getSituationDesc() {
        return situationDesc;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("familyMemberSocialInfoId", getFamilyMemberSocialInfoId())
                .append("userId", getUserId())
                .append("sortNum", getSortNum())
                .append("appellation", getAppellation())
                .append("familyMembersName", getFamilyMembersName())
                .append("birthDate", getBirthDate())
                .append("politicalOutlook", getPoliticalOutlook())
                .append("workUnitAndPosition", getWorkUnitAndPosition())
                .append("situationDesc", getSituationDesc())
                .append("delFlag", getDelFlag())
                .append("contactNumber",getContactNumber())
                .toString();
    }
}