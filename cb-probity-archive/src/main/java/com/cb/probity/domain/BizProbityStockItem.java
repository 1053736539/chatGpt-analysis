package com.cb.probity.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 廉政档案-13.2持有股票记录信息项对象 biz_probity_stock_item
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public class BizProbityStockItem extends BaseEntity {
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
     * 持有人姓名
     */
    @Excel(name = "持有人姓名")
    private String holder;

    /**
     * 股票名称或代码
     */
    @Excel(name = "股票名称或代码")
    private String stockNameCode;

    /**
     * 持股数量
     */
    @Excel(name = "持股数量")
    private String holdNum;

    /**
     * 填报前一交易日市值（万元）
     */
    @Excel(name = "填报前一交易日市值", readConverterExp = "万=元")
    private Long marketValue;

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

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getHolder() {
        return holder;
    }

    public void setStockNameCode(String stockNameCode) {
        this.stockNameCode = stockNameCode;
    }

    public String getStockNameCode() {
        return stockNameCode;
    }

    public void setHoldNum(String holdNum) {
        this.holdNum = holdNum;
    }

    public String getHoldNum() {
        return holdNum;
    }

    public void setMarketValue(Long marketValue) {
        this.marketValue = marketValue;
    }

    public Long getMarketValue() {
        return marketValue;
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
                .append("holder", getHolder())
                .append("stockNameCode", getStockNameCode())
                .append("holdNum", getHoldNum())
                .append("marketValue", getMarketValue())
                .toString();
    }
}
