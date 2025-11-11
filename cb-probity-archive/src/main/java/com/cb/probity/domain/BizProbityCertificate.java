package com.cb.probity.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 廉政档案-6.1本人持有护照、往来港澳台证件情况对象 biz_probity_certificate
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public class BizProbityCertificate extends BaseEntity {
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
     * 持有证件名称及编号
     */
    @Excel(name = "持有证件名称及编号")
    private String certificateNameNo;

    /**
     * 有效期
     */
    @Excel(name = "有效期")
    private String expireDate;

    /**
     * 保管机构
     */
    @Excel(name = "保管机构")
    private String depositoryDept;

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

    public void setCertificateNameNo(String certificateNameNo) {
        this.certificateNameNo = certificateNameNo;
    }

    public String getCertificateNameNo() {
        return certificateNameNo;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setDepositoryDept(String depositoryDept) {
        this.depositoryDept = depositoryDept;
    }

    public String getDepositoryDept() {
        return depositoryDept;
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
                .append("certificateNameNo", getCertificateNameNo())
                .append("expireDate", getExpireDate())
                .append("depositoryDept", getDepositoryDept())
                .toString();
    }
}
