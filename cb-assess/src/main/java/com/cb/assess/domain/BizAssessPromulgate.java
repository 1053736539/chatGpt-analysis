package com.cb.assess.domain;

import java.util.Date;

import com.cb.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cb.common.annotation.Excel;
import lombok.Data;
import org.springframework.data.annotation.Transient;

/**
 * 考核公示对象 biz_assess_promulgate
 *
 * @author ouyang
 * @date 2023-12-29
 */
@Data
public class BizAssessPromulgate extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private String id;

    /**
     * 任务ID
     */
    @Excel(name = "任务ID")
    private String taskId;

    @Transient
    private String belongYear;
    @Transient
    private String special;
    /**
     * 季度
     */
    @Excel(name = "季度")
    private String quarter;

    /**
     * 公示时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "公示时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date publicityTime;

    /**
     * 公示天数
     */
    @Excel(name = "公示天数")
    private Integer publicityDays;

    //公示状态  0 正常 1 结束
    private String finishStatus;

}
