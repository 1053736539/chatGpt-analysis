package com.cb.probity.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况对象 biz_probity_enterprise
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public class BizProbityEnterprise extends BaseEntity {
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
     * 统一社会信用代码/注册号
     */
    @Excel(name = "统一社会信用代码/注册号")
    private String uscc;

    /**
     * 企业或其他市场主体名称
     */
    @Excel(name = "企业或其他市场主体名称")
    private String enterpriseName;

    /**
     * 成立日期
     */
    @Excel(name = "成立日期")
    private String establishmentTime;

    /**
     * 经营范围
     */
    @Excel(name = "经营范围")
    private String businessScope;

    /**
     * 注册地
     */
    @Excel(name = "注册地")
    private String registeredPlace;

    /**
     * 经营地
     */
    @Excel(name = "经营地")
    private String businessPlace;

    /**
     * 企业或其他市场主体类型
     */
    @Excel(name = "企业或其他市场主体类型")
    private String enterpriseType;

    /**
     * 注册资本（金）或资金数额（出资额）（万元）
     */
    @Excel(name = "注册资本", readConverterExp = "金=")
    private Long registeredCapital;

    /**
     * 企业状态
     */
    @Excel(name = "企业状态")
    private String enterpriseStatus;

    /**
     * 个人认缴出资额或个人出资额
     */
    @Excel(name = "个人认缴出资额或个人出资额")
    private Long subscribedCapital;

    /**
     * 个人认缴出资比例或个人出资比例
     */
    @Excel(name = "个人认缴出资比例或个人出资比例")
    private Long subscribedPercent;

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

    public void setUscc(String uscc) {
        this.uscc = uscc;
    }

    public String getUscc() {
        return uscc;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEstablishmentTime(String establishmentTime) {
        this.establishmentTime = establishmentTime;
    }

    public String getEstablishmentTime() {
        return establishmentTime;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setRegisteredPlace(String registeredPlace) {
        this.registeredPlace = registeredPlace;
    }

    public String getRegisteredPlace() {
        return registeredPlace;
    }

    public void setBusinessPlace(String businessPlace) {
        this.businessPlace = businessPlace;
    }

    public String getBusinessPlace() {
        return businessPlace;
    }

    public void setEnterpriseType(String enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public String getEnterpriseType() {
        return enterpriseType;
    }

    public void setRegisteredCapital(Long registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public Long getRegisteredCapital() {
        return registeredCapital;
    }

    public void setEnterpriseStatus(String enterpriseStatus) {
        this.enterpriseStatus = enterpriseStatus;
    }

    public String getEnterpriseStatus() {
        return enterpriseStatus;
    }

    public void setSubscribedCapital(Long subscribedCapital) {
        this.subscribedCapital = subscribedCapital;
    }

    public Long getSubscribedCapital() {
        return subscribedCapital;
    }

    public void setSubscribedPercent(Long subscribedPercent) {
        this.subscribedPercent = subscribedPercent;
    }

    public Long getSubscribedPercent() {
        return subscribedPercent;
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
                .append("uscc", getUscc())
                .append("enterpriseName", getEnterpriseName())
                .append("establishmentTime", getEstablishmentTime())
                .append("businessScope", getBusinessScope())
                .append("registeredPlace", getRegisteredPlace())
                .append("businessPlace", getBusinessPlace())
                .append("enterpriseType", getEnterpriseType())
                .append("registeredCapital", getRegisteredCapital())
                .append("enterpriseStatus", getEnterpriseStatus())
                .append("subscribedCapital", getSubscribedCapital())
                .append("subscribedPercent", getSubscribedPercent())
                .toString();
    }
}
