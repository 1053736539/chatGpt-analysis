package com.cb.probity.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况对象 biz_probity_foreign_deposit
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public class BizProbityForeignDeposit extends BaseEntity {
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
     * 存款人姓名
     */
    @Excel(name = "存款人姓名")
    private String depositor;

    /**
     * 存款的国家（地区）及城市
     */
    @Excel(name = "存款的国家", readConverterExp = "地=区")
    private String countryRegion;

    /**
     * 开户银行或金融机构全称
     */
    @Excel(name = "开户银行或金融机构全称")
    private String bank;

    /**
     * 币种
     */
    @Excel(name = "币种")
    private String currency;

    /**
     * 金额（万）
     */
    @Excel(name = "金额", readConverterExp = "万=")
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

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public String getDepositor() {
        return depositor;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBank() {
        return bank;
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
                .append("depositor", getDepositor())
                .append("countryRegion", getCountryRegion())
                .append("bank", getBank())
                .append("currency", getCurrency())
                .append("amount", getAmount())
                .toString();
    }
}
