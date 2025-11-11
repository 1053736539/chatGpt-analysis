package com.cb.assess.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 优秀名额分配单位人员名单对象 biz_assess_quota_allocation_user
 * 
 * @author ruoyi
 * @date 2023-11-16
 */
public class BizAssessQuotaAllocationUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long userId;

    /** 分配表id */
    @Excel(name = "分配表id")
    private String quotaAllocationId;

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setQuotaAllocationId(String quotaAllocationId) 
    {
        this.quotaAllocationId = quotaAllocationId;
    }

    public String getQuotaAllocationId() 
    {
        return quotaAllocationId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("quotaAllocationId", getQuotaAllocationId())
            .toString();
    }
}
