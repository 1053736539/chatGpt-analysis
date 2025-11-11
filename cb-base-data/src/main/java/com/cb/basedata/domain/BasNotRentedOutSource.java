package com.cb.basedata.domain;

import java.util.Date;

import com.cb.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cb.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 代码-昆明市主城区未配租数据对象 bas_not_rented_out_source
 * 
 * @author ruoyi
 * @date 2024-10-30
 */
public class BasNotRentedOutSource extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据源id */
    private String sourceId;

    /** 楼盘 */
    @Excel(name = "楼盘")
    private String propertiesSale;
    @Excel(name = "项目名称")
    private String projectName;

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
    @Excel(name = "区域")
    private String areaId;

    /** 导入时间 */
    private Date importTime;

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
    public void setImportTime(Date importTime) 
    {
        this.importTime = importTime;
    }

    public Date getImportTime() 
    {
        return importTime;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
            .append("importTime", getImportTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
