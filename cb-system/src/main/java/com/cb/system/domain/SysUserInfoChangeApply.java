package com.cb.system.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Transient;

/**
 * 用户信息修改申请对象 sys_user_info_change_apply
 *
 * @author ruoyi
 * @date 2025-05-15
 */
public class SysUserInfoChangeApply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 之前的数据 */
    @Excel(name = "之前的数据")
    private String beforeData;

    /** 修改后的数据 */
    @Excel(name = "修改后的数据")
    private String afterData;

    /** 审批状态 1-待审批 2-审批通过 3-审批驳回 */
    @Excel(name = "审批状态")
    private String status;

    /** 审批意见 */
    @Excel(name = "审批意见")
    private String approvalOpinion;

    /** 审批人 */
    @Excel(name = "审批人")
    private String approvalBy;

    /**
     * 以下为扩展属性，不持久化
     */
    @Transient
    private String userName;// 用户名称

    @Transient
    private String approvalName;// 审批人名称


    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setBeforeData(String beforeData)
    {
        this.beforeData = beforeData;
    }

    public String getBeforeData()
    {
        return beforeData;
    }
    public void setAfterData(String afterData)
    {
        this.afterData = afterData;
    }

    public String getAfterData()
    {
        return afterData;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setApprovalOpinion(String approvalOpinion)
    {
        this.approvalOpinion = approvalOpinion;
    }

    public String getApprovalOpinion()
    {
        return approvalOpinion;
    }
    public void setApprovalBy(String approvalBy)
    {
        this.approvalBy = approvalBy;
    }

    public String getApprovalBy()
    {
        return approvalBy;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(String approvalName) {
        this.approvalName = approvalName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userId", getUserId())
                .append("beforeData", getBeforeData())
                .append("afterData", getAfterData())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("status", getStatus())
                .append("approvalOpinion", getApprovalOpinion())
                .append("approvalBy", getApprovalBy())
                .toString();
    }
}
