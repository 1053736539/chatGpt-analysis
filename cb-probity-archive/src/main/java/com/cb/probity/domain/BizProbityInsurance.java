package com.cb.probity.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况对象 biz_probity_insurance
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public class BizProbityInsurance extends BaseEntity {
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
     * 投保人姓名
     */
    @Excel(name = "投保人姓名")
    private String policyholder;

    /**
     * 保险产品全称
     */
    @Excel(name = "保险产品全称")
    private String insuranceProduct;

    /**
     * 保单号
     */
    @Excel(name = "保单号")
    private String policyNo;

    /**
     * 保险公司名称
     */
    @Excel(name = "保险公司名称")
    private String insuranceCompany;

    /**
     * 累积缴纳保费、投资金（万元）
     */
    @Excel(name = "累积缴纳保费、投资金", readConverterExp = "万=元")
    private Long accumulatedAmount;

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

    public void setPolicyholder(String policyholder) {
        this.policyholder = policyholder;
    }

    public String getPolicyholder() {
        return policyholder;
    }

    public void setInsuranceProduct(String insuranceProduct) {
        this.insuranceProduct = insuranceProduct;
    }

    public String getInsuranceProduct() {
        return insuranceProduct;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setAccumulatedAmount(Long accumulatedAmount) {
        this.accumulatedAmount = accumulatedAmount;
    }

    public Long getAccumulatedAmount() {
        return accumulatedAmount;
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
                .append("policyholder", getPolicyholder())
                .append("insuranceProduct", getInsuranceProduct())
                .append("policyNo", getPolicyNo())
                .append("insuranceCompany", getInsuranceCompany())
                .append("accumulatedAmount", getAccumulatedAmount())
                .toString();
    }
}
