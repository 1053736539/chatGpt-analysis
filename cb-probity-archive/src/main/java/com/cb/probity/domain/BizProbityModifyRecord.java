package com.cb.probity.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 修改廉政档案记录对象 biz_probity_modify_record
 *
 * @author ruoyi
 * @date 2025-03-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BizProbityModifyRecord extends BaseEntity {
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
     * 记录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordTime;

    /**
     * 修改记录
     */
    @Excel(name = "修改记录")
    private String modifyRecord;

    /**
     * 修改的字段
     */
    @Excel(name = "修改字段")
    private String modifyFiled;

    /**
     * 用户
     */
    @Excel(name = "用户")
    private String userName;

    /**
     * 年度
     */
    @Excel(name = "年度")
    private Integer year;

    private Long userId;

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
                .append("recordTime", getRecordTime())
                .append("modifyRecord", getModifyRecord())
                .append("userName", getUserName())
                .append("year", getYear())
                .toString();
    }
}
