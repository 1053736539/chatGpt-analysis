package com.cb.probity.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 廉政档案-6.2本人因私出国（境）及往来港澳台情况对象 biz_probity_go_abroad
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public class BizProbityGoAbroad extends BaseEntity {
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
     * 往返时间
     */
    @Excel(name = "往返时间")
    private String roundTripTime;

    /**
     * 所到国家、地区
     */
    @Excel(name = "所到国家、地区")
    private String countryRegion;

    /**
     * 事由
     */
    @Excel(name = "事由")
    private String content;

    /**
     * 审批机构
     */
    @Excel(name = "审批机构")
    private String approvalDept;

    /**
     * 所用护照号或港澳台通行证号
     */
    @Excel(name = "所用护照号或港澳台通行证号")
    private String certificateNo;

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

    public void setRoundTripTime(String roundTripTime) {
        this.roundTripTime = roundTripTime;
    }

    public String getRoundTripTime() {
        return roundTripTime;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setApprovalDept(String approvalDept) {
        this.approvalDept = approvalDept;
    }

    public String getApprovalDept() {
        return approvalDept;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getCertificateNo() {
        return certificateNo;
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
                .append("roundTripTime", getRoundTripTime())
                .append("countryRegion", getCountryRegion())
                .append("content", getContent())
                .append("approvalDept", getApprovalDept())
                .append("certificateNo", getCertificateNo())
                .toString();
    }
}
