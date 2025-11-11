package com.cb.basedata.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.cb.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cb.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 水费对象 bas_water_rent
 * 
 * @author ouyang
 * @date 2024-10-25
 */
public class BasWaterRent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;
    /**
     * 源数据ID
     */
    private String sourceId;

    @Excel(name = "楼盘编码")
    private String buildingCode;
    /** 用户编号 */
    @Excel(name = "用户编号")
    private String userNumber;

    /** 用户姓名 */
    @Excel(name = "用户姓名")
    private String userName;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idCardNumber;

    /** 电话 */
    @Excel(name = "电话")
    private String phoneNumber;


    /** 所属区划 */
    @Excel(name = "所属区划")
    private String area;

    /** 小区名称 */
    @Excel(name = "小区名称")
    private String community;

    /** 用水楼栋 */
    @Excel(name = "用水楼栋")
    private String buildingNumber;

    /** 用水单元 */
    @Excel(name = "用水单元")
    private String unit;

    /** 房间编号 */
    @Excel(name = "房间编号")
    private String roomNumber;

    /** 用水地址 */
    @Excel(name = "用水地址")
    private String address;

    /** 缴费金额 */
    @Excel(name = "缴费金额")
    private BigDecimal paymentAmount;

    /** 缴费时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "缴费时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date paymentTime;

    /** 缴费方式 */
    @Excel(name = "缴费方式")
    private String paymentType;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public void setUserNumber(String userNumber)
    {
        this.userNumber = userNumber;
    }

    public String getUserNumber() 
    {
        return userNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBuildingCode(String buildingCode)
    {
        this.buildingCode = buildingCode;
    }

    public String getBuildingCode() 
    {
        return buildingCode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public void setBuildingNumber(String buildingNumber)
    {
        this.buildingNumber = buildingNumber;
    }

    public String getBuildingNumber() 
    {
        return buildingNumber;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setRoomNumber(String roomNumber) 
    {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() 
    {
        return roomNumber;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public void setPaymentTime(Date paymentTime)
    {
        this.paymentTime = paymentTime;
    }

    public Date getPaymentTime() 
    {
        return paymentTime;
    }
    public void setPaymentType(String paymentType) 
    {
        this.paymentType = paymentType;
    }

    public String getPaymentType() 
    {
        return paymentType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userNumber", getUserNumber())
            .append("buildingCode", getBuildingCode())
            .append("buildingNumber", getBuildingNumber())
            .append("unit", getUnit())
            .append("roomNumber", getRoomNumber())
            .append("address", getAddress())
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
