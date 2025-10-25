package com.cb.common.core.domain.entity;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 人员借调工作信息对象 sys_user_secondment_work_info
 *
 * @author huhaojie
 * @date 2023-12-21
 */
public class SysUserSecondmentWorkInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Excel(name = "ID")
    private String secondmentWorkInfoId;

    /**
     * 借入单位名称
     */
    @Excel(name = "借入单位名称")
    private String borrowUnitName;

    /**
     * 借入单位行政层级
     */
    @Excel(name = "借入单位行政层级")
    private String borrowUnitLevel;

    /**
     * 借入单位性质
     */
    @Excel(name = "借入单位性质")
    private String borrowUnitNature;

    /**
     * 借出单位名称
     */
    @Excel(name = "借出单位名称")
    private String lendingUnitName;

    /**
     * 借出单位行政层级
     */
    @Excel(name = "借出单位行政层级")
    private String lendingUnitLevel;

    /**
     * 借出单位性质
     */
    @Excel(name = "借出单位性质")
    private String lendingUnitNature;

    /**
     * 用户ID
     */
    @Excel(name = "用户ID")
    private Long userId;

    /**
     * 身份类别
     */
    @Excel(name = "身份类别")
    private String identityCategory;

    /**
     * 借调开始时间
     */
    @Excel(name = "借调开始时间")
    private String startDate;

    /**
     * 借调结束时间
     */
    @Excel(name = "借调结束时间")
    private String endDate;

    /**
     * 借调类型
     */
    @Excel(name = "借调类型")
    private String secondmentType;

    /**
     * 借调处室
     */
    @Excel(name = "借调处室")
    private String secondmentDept;

    /**
     * 备案时间
     */
    @Excel(name = "备案时间")
    private String filingTime;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String secondmentNotes;

    public void setSecondmentWorkInfoId(String secondmentWorkInfoId) {
        this.secondmentWorkInfoId = secondmentWorkInfoId;
    }

    public String getSecondmentWorkInfoId() {
        return secondmentWorkInfoId;
    }

    public void setBorrowUnitName(String borrowUnitName) {
        this.borrowUnitName = borrowUnitName;
    }

    public String getBorrowUnitName() {
        return borrowUnitName;
    }

    public void setBorrowUnitLevel(String borrowUnitLevel) {
        this.borrowUnitLevel = borrowUnitLevel;
    }

    public String getBorrowUnitLevel() {
        return borrowUnitLevel;
    }

    public void setBorrowUnitNature(String borrowUnitNature) {
        this.borrowUnitNature = borrowUnitNature;
    }

    public String getBorrowUnitNature() {
        return borrowUnitNature;
    }

    public void setLendingUnitName(String lendingUnitName) {
        this.lendingUnitName = lendingUnitName;
    }

    public String getLendingUnitName() {
        return lendingUnitName;
    }

    public void setLendingUnitLevel(String lendingUnitLevel) {
        this.lendingUnitLevel = lendingUnitLevel;
    }

    public String getLendingUnitLevel() {
        return lendingUnitLevel;
    }

    public void setLendingUnitNature(String lendingUnitNature) {
        this.lendingUnitNature = lendingUnitNature;
    }

    public String getLendingUnitNature() {
        return lendingUnitNature;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setIdentityCategory(String identityCategory) {
        this.identityCategory = identityCategory;
    }

    public String getIdentityCategory() {
        return identityCategory;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setSecondmentType(String secondmentType) {
        this.secondmentType = secondmentType;
    }

    public String getSecondmentType() {
        return secondmentType;
    }

    public void setSecondmentDept(String secondmentDept) {
        this.secondmentDept = secondmentDept;
    }

    public String getSecondmentDept() {
        return secondmentDept;
    }

    public String getFilingTime() {
        return filingTime;
    }

    public void setFilingTime(String filingTime) {
        this.filingTime = filingTime;
    }

    public void setSecondmentNotes(String secondmentNotes) {
        this.secondmentNotes = secondmentNotes;
    }

    public String getSecondmentNotes() {
        return secondmentNotes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("secondmentWorkInfoId", getSecondmentWorkInfoId())
                .append("borrowUnitName", getBorrowUnitName())
                .append("borrowUnitLevel", getBorrowUnitLevel())
                .append("borrowUnitNature", getBorrowUnitNature())
                .append("lendingUnitName", getLendingUnitName())
                .append("lendingUnitLevel", getLendingUnitLevel())
                .append("lendingUnitNature", getLendingUnitNature())
                .append("userId", getUserId())
                .append("identityCategory", getIdentityCategory())
                .append("startDate", getStartDate())
                .append("endDate", getEndDate())
                .append("secondmentType", getSecondmentType())
                .append("secondmentDept", getSecondmentDept())
                .append("secondmentNotes", getSecondmentNotes())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
