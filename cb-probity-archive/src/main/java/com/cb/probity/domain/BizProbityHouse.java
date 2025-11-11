package com.cb.probity.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况对象 biz_probity_house
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public class BizProbityHouse extends BaseEntity {
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
     * 产权人姓名
     */
    @Excel(name = "产权人姓名")
    private String owner;

    /**
     * 房产来源（
     */
    @Excel(name = "房产来源", readConverterExp = "去=向")
    private String houseSourceDest;

    /**
     * 房产去向
     */
    @Excel(name = "房产去向", readConverterExp = "去=向")
    private String houseTargetDest;

    /**
     * 具体地址
     */
    @Excel(name = "具体地址")
    private String addr;

    /**
     * 建筑面积（㎡）
     */
    @Excel(name = "建筑面积", readConverterExp = "㎡=")
    private String buildArea;

    /**
     * 房产性质和功能类型
     */
    @Excel(name = "房产性质和功能类型")
    private String houseNature;

    /**
     * 交易时间（年月）
     */
    @Excel(name = "交易时间", readConverterExp = "年=月")
    private String tradeDate;

    /**
     * 交易价格（万元）
     */
    @Excel(name = "交易价格", readConverterExp = "万=元")
    private String tradePrice;

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

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setHouseSourceDest(String houseSourceDest) {
        this.houseSourceDest = houseSourceDest;
    }

    public String getHouseSourceDest() {
        return houseSourceDest;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getAddr() {
        return addr;
    }

    public void setBuildArea(String buildArea) {
        this.buildArea = buildArea;
    }

    public String getBuildArea() {
        return buildArea;
    }

    public void setHouseNature(String houseNature) {
        this.houseNature = houseNature;
    }

    public String getHouseNature() {
        return houseNature;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradePrice(String tradePrice) {
        this.tradePrice = tradePrice;
    }

    public String getTradePrice() {
        return tradePrice;
    }

    public String getHouseTargetDest() {
        return houseTargetDest;
    }

    public void setHouseTargetDest(String houseTargetDest) {
        this.houseTargetDest = houseTargetDest;
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
                .append("owner", getOwner())
                .append("houseSourceDest", getHouseSourceDest())
                .append("addr", getAddr())
                .append("buildArea", getBuildArea())
                .append("houseNature", getHouseNature())
                .append("tradeDate", getTradeDate())
                .append("tradePrice", getTradePrice())
                .toString();
    }
}
