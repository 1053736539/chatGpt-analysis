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
 * 廉政档案记录对象 biz_probity
 *
 * @author ruoyi
 * @date 2025-03-13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BizProbity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 年度
     */
    @Excel(name = "年度")
    private Integer year;

    /**
     * userId
     */
    @Excel(name = "userId")
    private Long userId;

    private String userName;

    /**
     * 状态 1-未确认 2-已确认 3-允许修改
     */
    @Excel(name = "状态 1-未确认 2-已确认 3-允许修改")
    private String confirmStatus;

    /**
     * 确认时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "确认时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date confirmTime;

    private String deptName;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("year", getYear())
                .append("userId", getUserId())
                .append("userName", getUserName())
                .append("deptName", getDeptName())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("confirmStatus", getConfirmStatus())
                .append("confirmTime", getConfirmTime())
                .toString();
    }
}
