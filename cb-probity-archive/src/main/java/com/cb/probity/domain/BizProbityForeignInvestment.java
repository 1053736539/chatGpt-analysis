package com.cb.probity.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况对象 biz_probity_foreign_investment
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public class BizProbityForeignInvestment extends BaseEntity {
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
     * 投资人姓名
     */
    @Excel(name = "投资人姓名")
    private String investor;

    /**
     * 投资的国家（地区）及城市
     */
    @Excel(name = "投资的国家", readConverterExp = "地=区")
    private String countryRegion;

    /**
     * 投资情况
     */
    @Excel(name = "投资情况")
    private String investmentSituation;

    /**
     * 币种
     */
    @Excel(name = "币种")
    private String currency;

    /**
     * 投资金额（万）
     */
    @Excel(name = "投资金额", readConverterExp = "万=")
    private Long amount;

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

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public String getInvestor() {
        return investor;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public void setInvestmentSituation(String investmentSituation) {
        this.investmentSituation = investmentSituation;
    }

    public String getInvestmentSituation() {
        return investmentSituation;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getAmount() {
        return amount;
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
                .append("investor", getInvestor())
                .append("countryRegion", getCountryRegion())
                .append("investmentSituation", getInvestmentSituation())
                .append("currency", getCurrency())
                .append("amount", getAmount())
                .toString();
    }
}
