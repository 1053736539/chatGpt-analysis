package com.cb.basedata.domain;

import java.util.Date;

import com.cb.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cb.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 代码-昆明市主城区已配租数据对象 bas_rented_out
 * 
 * @author ruoyi
 * @date 2024-10-25
 */
public class BasRentedOut extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;
    private String sourceId;

    /** 楼盘 */
    @Excel(name = "楼盘")
    private String propertiesSale;

    /** 楼栋 */
    @Excel(name = "楼栋")
    private String building;

    /** 单元 */
    @Excel(name = "单元")
    private String unit;

    /** 房号 */
    @Excel(name = "房号")
    private String roomNumber;

    /** 月租金（分） */
    @Excel(name = "月租金", readConverterExp = "分=")
    private Integer monthlyRent;

    /** 区域id */
    @Excel(name = "区域id")
    private String areaId;

    /** 姓名1 */
    @Excel(name = "姓名1")
    private String name01;

    /** 身份证1 */
    @Excel(name = "身份证1")
    private String idcard01;

    /** 姓名2 */
    @Excel(name = "姓名2")
    private String name02;

    /** 身份证2 */
    @Excel(name = "身份证2")
    private String idcard02;

    /** 姓名3 */
    @Excel(name = "姓名3")
    private String name03;

    /** 身份证3 */
    @Excel(name = "身份证3")
    private String idcard03;

    /** 姓名4 */
    @Excel(name = "姓名4")
    private String name04;

    /** 身份证4 */
    @Excel(name = "身份证4")
    private String idcard04;

    /** 姓名5 */
    @Excel(name = "姓名5")
    private String name05;

    /** 身份证5 */
    @Excel(name = "身份证5")
    private String idcard05;

    /** 姓名6 */
    @Excel(name = "姓名6")
    private String name06;

    /** 身份证6 */
    @Excel(name = "身份证6")
    private String idcard06;
    @Excel(name = "姓名06")
    private String name07;

    /** 身份证6 */
    @Excel(name = "身份证06")
    private String idcard07;
    @Excel(name = "姓名07")
    private String name08;

    /** 身份证6 */
    @Excel(name = "身份证07")
    private String idcard08;

    /** 导入时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "导入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date importTime;

    /** 申请人数量 */
    @Excel(name = "申请人数量")
    private Integer numberApplicants;

    /** $column.columnComment */
    @Excel(name = "申请人数量")
    private String delFlag;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setPropertiesSale(String propertiesSale) 
    {
        this.propertiesSale = propertiesSale;
    }

    public String getPropertiesSale() 
    {
        return propertiesSale;
    }
    public void setBuilding(String building) 
    {
        this.building = building;
    }

    public String getBuilding() 
    {
        return building;
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
    public void setMonthlyRent(Integer monthlyRent) 
    {
        this.monthlyRent = monthlyRent;
    }

    public Integer getMonthlyRent() 
    {
        return monthlyRent;
    }
    public void setAreaId(String areaId) 
    {
        this.areaId = areaId;
    }

    public String getAreaId() 
    {
        return areaId;
    }
    public void setName01(String name01) 
    {
        this.name01 = name01;
    }

    public String getName01() 
    {
        return name01;
    }
    public void setIdcard01(String idcard01) 
    {
        this.idcard01 = idcard01;
    }

    public String getIdcard01() 
    {
        return idcard01;
    }
    public void setName02(String name02) 
    {
        this.name02 = name02;
    }

    public String getName02() 
    {
        return name02;
    }
    public void setIdcard02(String idcard02) 
    {
        this.idcard02 = idcard02;
    }

    public String getIdcard02() 
    {
        return idcard02;
    }
    public void setName03(String name03) 
    {
        this.name03 = name03;
    }

    public String getName03() 
    {
        return name03;
    }
    public void setIdcard03(String idcard03) 
    {
        this.idcard03 = idcard03;
    }

    public String getIdcard03() 
    {
        return idcard03;
    }
    public void setName04(String name04) 
    {
        this.name04 = name04;
    }

    public String getName04() 
    {
        return name04;
    }
    public void setIdcard04(String idcard04) 
    {
        this.idcard04 = idcard04;
    }

    public String getIdcard04() 
    {
        return idcard04;
    }
    public void setName05(String name05) 
    {
        this.name05 = name05;
    }

    public String getName05() 
    {
        return name05;
    }
    public void setIdcard05(String idcard05) 
    {
        this.idcard05 = idcard05;
    }

    public String getIdcard05() 
    {
        return idcard05;
    }
    public void setName06(String name06) 
    {
        this.name06 = name06;
    }

    public String getName06() 
    {
        return name06;
    }
    public void setIdcard06(String idcard06) 
    {
        this.idcard06 = idcard06;
    }

    public String getIdcard06() 
    {
        return idcard06;
    }
    public void setImportTime(Date importTime) 
    {
        this.importTime = importTime;
    }

    public Date getImportTime() 
    {
        return importTime;
    }
    public void setNumberApplicants(Integer numberApplicants) 
    {
        this.numberApplicants = numberApplicants;
    }

    public Integer getNumberApplicants() 
    {
        return numberApplicants;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public String getName07() {
        return name07;
    }

    public void setName07(String name07) {
        this.name07 = name07;
    }

    public String getIdcard07() {
        return idcard07;
    }

    public void setIdcard07(String idcard07) {
        this.idcard07 = idcard07;
    }

    public String getName08() {
        return name08;
    }

    public void setName08(String name08) {
        this.name08 = name08;
    }

    public String getIdcard08() {
        return idcard08;
    }

    public void setIdcard08(String idcard08) {
        this.idcard08 = idcard08;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("propertiesSale", getPropertiesSale())
            .append("building", getBuilding())
            .append("unit", getUnit())
            .append("roomNumber", getRoomNumber())
            .append("monthlyRent", getMonthlyRent())
            .append("areaId", getAreaId())
            .append("name01", getName01())
            .append("idcard01", getIdcard01())
            .append("name02", getName02())
            .append("idcard02", getIdcard02())
            .append("name03", getName03())
            .append("idcard03", getIdcard03())
            .append("name04", getName04())
            .append("idcard04", getIdcard04())
            .append("name05", getName05())
            .append("idcard05", getIdcard05())
            .append("name06", getName06())
            .append("idcard06", getIdcard06())
            .append("importTime", getImportTime())
            .append("numberApplicants", getNumberApplicants())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
