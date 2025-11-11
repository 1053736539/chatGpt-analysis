package com.cb.probity.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况对象 biz_probity_migrate
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public class BizProbityMigrate extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * probity_id
     */
    @Excel(name = "probity_id")
    private String probityId;

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String name;

    /**
     * 移居国家、地区
     */
    @Excel(name = "移居国家、地区")
    private String migrateCountryRegion;

    /**
     * 现居住城市
     */
    @Excel(name = "现居住城市")
    private String liveCity;

    /**
     * 移居证件号码
     */
    @Excel(name = "移居证件号码")
    private String migrateCertificateNo;

    /**
     * 移居类别
     */
    @Excel(name = "移居类别")
    private String migrateType;

    /**
     * 移居时间
     */
    @Excel(name = "移居时间")
    private String migrateTime;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setProbityId(String probityId) {
        this.probityId = probityId;
    }

    public String getProbityId() {
        return probityId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMigrateCountryRegion(String migrateCountryRegion) {
        this.migrateCountryRegion = migrateCountryRegion;
    }

    public String getMigrateCountryRegion() {
        return migrateCountryRegion;
    }

    public void setLiveCity(String liveCity) {
        this.liveCity = liveCity;
    }

    public String getLiveCity() {
        return liveCity;
    }

    public void setMigrateCertificateNo(String migrateCertificateNo) {
        this.migrateCertificateNo = migrateCertificateNo;
    }

    public String getMigrateCertificateNo() {
        return migrateCertificateNo;
    }

    public void setMigrateType(String migrateType) {
        this.migrateType = migrateType;
    }

    public String getMigrateType() {
        return migrateType;
    }

    public void setMigrateTime(String migrateTime) {
        this.migrateTime = migrateTime;
    }

    public String getMigrateTime() {
        return migrateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("probityId", getProbityId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("name", getName())
                .append("migrateCountryRegion", getMigrateCountryRegion())
                .append("liveCity", getLiveCity())
                .append("migrateCertificateNo", getMigrateCertificateNo())
                .append("migrateType", getMigrateType())
                .append("migrateTime", getMigrateTime())
                .toString();
    }
}
