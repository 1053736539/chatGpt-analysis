package com.cb.basedata.domain;

import java.util.Date;

import com.cb.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cb.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Transient;

/**
 * 数据清洗任务日志对象 basic_data_clean_task_log
 *
 * @author ouyang
 * @date 2024-11-01
 */
public class BasicDataCleanTaskLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private String logId;

    /**
     * 数据清洗任务ID
     */
    private String cleanTaskId;
    @Excel(name = "数据清洗任务")
    private String cleanTaskName;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 状态 0 未结束 1 已结束
     */
    @Excel(name = "状态 0 未结束 1 已结束")
    private String status;

    private String cleanResult;
    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;


    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getLogId() {
        return logId;
    }

    public void setCleanTaskId(String cleanTaskId) {
        this.cleanTaskId = cleanTaskId;
    }

    public String getCleanTaskId() {
        return cleanTaskId;
    }

    public String getCleanTaskName() {
        return cleanTaskName;
    }

    public void setCleanTaskName(String cleanTaskName) {
        this.cleanTaskName = cleanTaskName;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getCleanResult() {
        return cleanResult;
    }

    public void setCleanResult(String cleanResult) {
        this.cleanResult = cleanResult;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }
}
