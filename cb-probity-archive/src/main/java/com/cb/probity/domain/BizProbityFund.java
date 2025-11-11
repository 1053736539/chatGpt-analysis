package com.cb.probity.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况对象 biz_probity_fund
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public class BizProbityFund extends BaseEntity {
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
     * 基金、理财产品代码
     */
    @Excel(name = "基金、理财产品代码")
    private String fundNameCode;

    /**
     * 基金或理财产品购买金额
     */
    @Excel(name = "基金或理财产品购买金额")
    private Long purchaseAmount;

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

    public void setFundNameCode(String fundNameCode) {
        this.fundNameCode = fundNameCode;
    }

    public String getFundNameCode() {
        return fundNameCode;
    }

    public void setPurchaseAmount(Long purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public Long getPurchaseAmount() {
        return purchaseAmount;
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
                .append("fundNameCode", getFundNameCode())
                .append("purchaseAmount", getPurchaseAmount())
                .toString();
    }
}
