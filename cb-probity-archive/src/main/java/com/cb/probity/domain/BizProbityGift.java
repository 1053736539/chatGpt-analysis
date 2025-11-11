package com.cb.probity.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 廉政档案-3.本人拒收或上交礼金、礼品情况登记对象 biz_probity_gift
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public class BizProbityGift extends BaseEntity {
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
     * 拒收或上交礼金、有价证券或支付凭证金额
     */
    @Excel(name = "拒收或上交礼金、有价证券或支付凭证金额")
    private String rejectionSubmitAmount;

    /**
     * 拒收或上交礼品情况-名称
     */
    @Excel(name = "拒收或上交礼品情况-名称")
    private String giftName;

    /**
     * 拒收或上交礼品情况-数量
     */
    @Excel(name = "拒收或上交礼品情况-数量")
    private String giftNum;

    /**
     * 拒收或上交礼品情况-折合价值
     */
    @Excel(name = "拒收或上交礼品情况-折合价值")
    private String giftEquivalentValue;

    /**
     * 时间
     */
    @Excel(name = "时间")
    private String giftTime;

    /**
     * 上交部门
     */
    @Excel(name = "上交部门")
    private String submitDept;

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

    public void setRejectionSubmitAmount(String rejectionSubmitAmount) {
        this.rejectionSubmitAmount = rejectionSubmitAmount;
    }

    public String getRejectionSubmitAmount() {
        return rejectionSubmitAmount;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftNum(String giftNum) {
        this.giftNum = giftNum;
    }

    public String getGiftNum() {
        return giftNum;
    }

    public void setGiftEquivalentValue(String giftEquivalentValue) {
        this.giftEquivalentValue = giftEquivalentValue;
    }

    public String getGiftEquivalentValue() {
        return giftEquivalentValue;
    }

    public void setGiftTime(String giftTime) {
        this.giftTime = giftTime;
    }

    public String getGiftTime() {
        return giftTime;
    }

    public void setSubmitDept(String submitDept) {
        this.submitDept = submitDept;
    }

    public String getSubmitDept() {
        return submitDept;
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
                .append("rejectionSubmitAmount", getRejectionSubmitAmount())
                .append("giftName", getGiftName())
                .append("giftNum", getGiftNum())
                .append("giftEquivalentValue", getGiftEquivalentValue())
                .append("giftTime", getGiftTime())
                .append("submitDept", getSubmitDept())
                .toString();
    }
}
