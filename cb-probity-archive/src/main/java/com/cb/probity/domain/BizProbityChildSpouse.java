package com.cb.probity.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况对象 biz_probity_child_spouse
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public class BizProbityChildSpouse extends BaseEntity {
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
     * 子女姓名
     */
    @Excel(name = "子女姓名")
    private String name;

    /**
     * 子女配偶基本情况-姓名
     */
    @Excel(name = "子女配偶基本情况-姓名")
    private String spouseName;

    /**
     * 子女配偶基本情况-国家、地区
     */
    @Excel(name = "子女配偶基本情况-国家、地区")
    private String spouseContryRegion;

    /**
     * 子女配偶基本情况-工作（学习）单位
     */
    @Excel(name = "子女配偶基本情况-工作", readConverterExp = "学=习")
    private String spouseWorkStudyDept;

    /**
     * 子女配偶基本情况-职务
     */
    @Excel(name = "子女配偶基本情况-职务")
    private String spousePosition;

    /**
     * 子女配偶基本情况-结婚时间
     */
    @Excel(name = "子女配偶基本情况-结婚时间")
    private String spouseMarriageTime;

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

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseContryRegion(String spouseContryRegion) {
        this.spouseContryRegion = spouseContryRegion;
    }

    public String getSpouseContryRegion() {
        return spouseContryRegion;
    }

    public void setSpouseWorkStudyDept(String spouseWorkStudyDept) {
        this.spouseWorkStudyDept = spouseWorkStudyDept;
    }

    public String getSpouseWorkStudyDept() {
        return spouseWorkStudyDept;
    }

    public void setSpousePosition(String spousePosition) {
        this.spousePosition = spousePosition;
    }

    public String getSpousePosition() {
        return spousePosition;
    }

    public void setSpouseMarriageTime(String spouseMarriageTime) {
        this.spouseMarriageTime = spouseMarriageTime;
    }

    public String getSpouseMarriageTime() {
        return spouseMarriageTime;
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
                .append("spouseName", getSpouseName())
                .append("spouseContryRegion", getSpouseContryRegion())
                .append("spouseWorkStudyDept", getSpouseWorkStudyDept())
                .append("spousePosition", getSpousePosition())
                .append("spouseMarriageTime", getSpouseMarriageTime())
                .toString();
    }
}
