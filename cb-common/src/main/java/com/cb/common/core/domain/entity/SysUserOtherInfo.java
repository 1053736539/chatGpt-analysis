package com.cb.common.core.domain.entity;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户其他信息对象 sys_user_other_info
 *
 * @author hu
 * @date 2023-10-29
 */
public class SysUserOtherInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Excel(name = "ID")
    private String otherInfoId;

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
     * 考录
     */
    @Excel(name = "考录")
    private String admission;

    /**
     * 录用时间
     */
    @Excel(name = "录用时间")
    private String employmentTime;

    /**
     * 选调生
     */
    @Excel(name = "选调生")
    private String selectedStudents;

    /**
     * 进入时间
     */
    @Excel(name = "进入时间")
    private String entryTime;

    /**
     * 成长地
     */
    @Excel(name = "成长地")
    private String growingUp;

    /**
     * 级别
     */
    @Excel(name = "级别")
    private String levelType;

    /**
     * 任职资格
     */
    @Excel(name = "任职资格")
    private String qualifications;

    /**
     * 联系方式
     */
    @Excel(name = "联系方式")
    private String contactInfo;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remarkInfo;

    /**
     * 删除标记 0-未删除 1-已删除
     */
    private String delFlag;

    public void setOtherInfoId(String otherInfoId) {
        this.otherInfoId = otherInfoId;
    }

    public String getOtherInfoId() {
        return otherInfoId;
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

    public void setAdmission(String admission) {
        this.admission = admission;
    }

    public String getAdmission() {
        return admission;
    }

    public void setEmploymentTime(String employmentTime) {
        this.employmentTime = employmentTime;
    }

    public String getEmploymentTime() {
        return employmentTime;
    }

    public void setSelectedStudents(String selectedStudents) {
        this.selectedStudents = selectedStudents;
    }

    public String getSelectedStudents() {
        return selectedStudents;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setGrowingUp(String growingUp) {
        this.growingUp = growingUp;
    }

    public String getGrowingUp() {
        return growingUp;
    }

    public void setLevelType(String levelType) {
        this.levelType = levelType;
    }

    public String getLevelType() {
        return levelType;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setRemarkInfo(String remarkInfo) {
        this.remarkInfo = remarkInfo;
    }

    public String getRemarkInfo() {
        return remarkInfo;
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
                .append("otherInfoId", getOtherInfoId())
                .append("userId", getUserId())
                .append("sortNum", getSortNum())
                .append("admission", getAdmission())
                .append("employmentTime", getEmploymentTime())
                .append("selectedStudents", getSelectedStudents())
                .append("entryTime", getEntryTime())
                .append("growingUp", getGrowingUp())
                .append("levelType", getLevelType())
                .append("qualifications", getQualifications())
                .append("contactInfo", getContactInfo())
                .append("remarkInfo", getRemarkInfo())
                .append("delFlag", getDelFlag())
                .toString();
    }
}