package com.cb.basedata.domain;

import java.util.Date;

import com.cb.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cb.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 代码-昆明市主城区已配租数据对象 bas_rented_out_source
 * 
 * @author ruoyi
 * @date 2024-10-30
 */
public class BasRentedOutSource extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据id */
    private String sourceId;

    /** 楼盘 */
    @Excel(name = "楼盘名称")
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
    private String monthlyRent;

    /** 区域id */
    @Excel(name = "区域")
    private String areaId;

    /** 共同申请人1 */
    @Excel(name = "姓名")
    private String name01;

    /** 身份证1 */
    @Excel(name = "身份证号")
    private String idcard01;

    /** 共同申请人2 */
    @Excel(name = "共同申请人2")
    private String name02;

    /** 身份证2 */
    @Excel(name = "身份证号2")
    private String idcard02;

    /** 共同申请人3 */
    @Excel(name = "共同申请人3")
    private String name03;

    /** 身份证3 */
    @Excel(name = "身份证号3")
    private String idcard03;

    /** 共同申请人4 */
    @Excel(name = "共同申请人4")
    private String name04;

    /** 身份证4 */
    @Excel(name = "身份证号4")
    private String idcard04;

    /** 共同申请人5 */
    @Excel(name = "共同申请人5")
    private String name05;

    /** 身份证5 */
    @Excel(name = "身份证号5")
    private String idcard05;

    /** 共同申请人6 */
    @Excel(name = "共同申请人6")
    private String name06;

    /** 身份证6 */
    @Excel(name = "身份证号6")
    private String idcard06;

    /** 导入时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "导入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date importTime;

    /** 申请人数量 */
    @Excel(name = "申请人数量")
    private Integer numberApplicants;

    /** $column.columnComment */
    private String delFlag;
    private Boolean isCleaning;

    public void setSourceId(String sourceId) 
    {
        this.sourceId = sourceId;
    }

    public String getSourceId() 
    {
        return sourceId;
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
    public void setMonthlyRent(String monthlyRent)
    {
        this.monthlyRent = monthlyRent;
    }

    public String getMonthlyRent()
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

    public Boolean getIsCleaning() {
        return isCleaning;
    }

    public void setIsCleaning(Boolean cleaning) {
        isCleaning = cleaning;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("sourceId", getSourceId())
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
