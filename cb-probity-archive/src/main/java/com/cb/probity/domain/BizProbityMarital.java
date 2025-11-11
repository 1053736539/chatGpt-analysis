package com.cb.probity.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 廉政档案-4.本人婚姻状况及紧急联系人情况对象 biz_probity_marital
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public class BizProbityMarital extends BaseEntity {
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
     * 婚姻现状
     */
    @Excel(name = "婚姻现状")
    private String maritalStatus;

    /**
     * 紧急联系人及联系方式
     */
    @Excel(name = "紧急联系人及联系方式")
    private String linkUserPhone;

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

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setLinkUserPhone(String linkUserPhone) {
        this.linkUserPhone = linkUserPhone;
    }

    public String getLinkUserPhone() {
        return linkUserPhone;
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
                .append("maritalStatus", getMaritalStatus())
                .append("linkUserPhone", getLinkUserPhone())
                .toString();
    }
}
