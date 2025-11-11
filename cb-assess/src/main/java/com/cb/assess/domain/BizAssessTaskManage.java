package com.cb.assess.domain;

import java.util.Date;

import com.cb.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cb.common.annotation.Excel;
import lombok.Data;

/**
 * 考核任务管理对象 biz_assess_task_manage
 *
 * @author ruoyi
 * @date 2023-11-06
 */
@Data
public class BizAssessTaskManage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    private String taskId;

    /** 名称 */
    @Excel(name = "名称")
    private String taskName;

    /** 考核方案ID */
    @Excel(name = "考核方案ID")
    private String proId;

    /** 考核周期（月度，季度和年度） */
    @Excel(name = "考核周期", readConverterExp = "月=度，季度和年度")
    private String assessCycle;

    /**
     * 是否专项考核 0否 1是
     */
    private String special;

    /**
     * 归属年度
     */
    private String belongYear;

    /** 任务年度 */
    @Excel(name = "任务年度")
    private String taskYear;

    /** 任务季度 */
    @Excel(name = "任务季度")
    private String taskQuarter;

    /** 任务月度 */
    @Excel(name = "任务月度")
    private String taskMoth;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 状态（-1 草稿 0 下发确认 1已发布, 2 撤销发布） */
    @Excel(name = "发布状态", readConverterExp = "-1=草稿 ,0=下发确认,1=已发布,2=撤销发布")
    private String status;

    /** 删除状态 */
    private String delFlag;
    /**
     * 选择的考核方案
     */
    private BizAssessIndicatorPro pro;

    private Boolean enableRevoke;
}