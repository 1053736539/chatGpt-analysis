package com.cb.probity.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 申请修改廉政档案的记录对象 biz_probity_modify_approval
 *
 * @author ruoyi
 * @date 2025-03-18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BizProbityModifyApproval extends BaseEntity {
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

    private String applyUser;

    private Integer year;

    /**
     * 状态 1-待审批 2-审批通过 3-审批驳回
     */
    @Excel(name = "状态 1-待审批 2-审批通过 3-审批驳回")
    private Integer status;

    /**
     * 审批时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审批时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date approvalTime;

    /**
     * 驳回原因
     */
    @Excel(name = "驳回原因")
    private String rejectReason;

    private String deptName;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("probityId", getProbityId())
                .append("status", getStatus())
                .append("deptName", getDeptName())
                .append("approvalTime", getApprovalTime())
                .append("rejectReason", getRejectReason())
                .toString();
    }
}
