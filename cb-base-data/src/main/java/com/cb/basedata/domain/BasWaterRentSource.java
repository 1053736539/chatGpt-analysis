package com.cb.basedata.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.cb.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cb.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 水费-原始数据对象 bas_water_rent_source
 *
 * @author ouyang
 * @date 2024-10-29
 */
public class BasWaterRentSource extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private String sourceId;

    /**
     * 管理单位
     */
    @Excel(name = "管理单位")
    private String manageUnit;

    /**
     * 用户编号
     */
    @Excel(name = "用户编号")
    private String userNumber;

    /**
     * 用户姓名
     */
    @Excel(name = "用户姓名")
    private String userName;

    /**
     * 地址
     */
    @Excel(name = "地址")
    private String address;

    /**
     * 身份证号
     */
    @Excel(name = "身份证号")
    private String idCardNumber;

    /**
     * 电话
     */
    @Excel(name = "电话")
    private String phoneNumber;

    /**
     * 缴费金额
     */
    @Excel(name = "缴费金额")
    private BigDecimal paymentAmount;

    /**
     * 缴费时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "缴费时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date paymentTime;

    /**
     * 缴费方式
     */
    @Excel(name = "缴费方式")
    private String paymentType;

    /**
     * 是否清洗
     */
    private Boolean isCleaning;

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setManageUnit(String manageUnit) {
        this.manageUnit = manageUnit;
    }

    public String getManageUnit() {
        return manageUnit;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public Boolean getIsCleaning() {
        return isCleaning;
    }

    public void setCleaning(Boolean isCleaning) {
        this.isCleaning = isCleaning;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("sourceId", getSourceId())
                .append("manageUnit", getManageUnit())
                .append("userNumber", getUserNumber())
                .append("userName", getUserName())
                .append("address", getAddress())
                .append("idCardNumber", getIdCardNumber())
                .append("phoneNumber", getPhoneNumber())
                .append("paymentAmount", getPaymentAmount())
                .append("paymentTime", getPaymentTime())
                .append("paymentType", getPaymentType())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
