package com.cb.basedata.domain;

import java.util.List;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 数据清洗任务对象 basic_data_clean_task
 *
 * @author ouyang
 * @date 2024-11-01
 */
public class BasicDataCleanTask extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private String taskId;

    /**
     * 任务名称
     */
    @Excel(name = "任务名称")
    private String taskName;

    /**
     * 源数据表格
     */
    @Excel(name = "源数据表格")
    private String sourceTable;

    /**
     * 目标数据表格
     */
    @Excel(name = "目标数据表格")
    private String targetTable;

    /**
     * 是否启用
     */
    @Excel(name = "是否启用")
    private String enabled;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 数据清洗任务字段关系信息
     */
    private List<BasicDataCleanTaskRelation> basicDataCleanTaskRelationList;

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setSourceTable(String sourceTable) {
        this.sourceTable = sourceTable;
    }

    public String getSourceTable() {
        return sourceTable;
    }

    public void setTargetTable(String targetTable) {
        this.targetTable = targetTable;
    }

    public String getTargetTable() {
        return targetTable;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public List<BasicDataCleanTaskRelation> getBasicDataCleanTaskRelationList() {
        return basicDataCleanTaskRelationList;
    }

    public void setBasicDataCleanTaskRelationList(List<BasicDataCleanTaskRelation> basicDataCleanTaskRelationList) {
        this.basicDataCleanTaskRelationList = basicDataCleanTaskRelationList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("taskId", getTaskId())
                .append("taskName", getTaskName())
                .append("sourceTable", getSourceTable())
                .append("targetTable", getTargetTable())
                .append("enabled", getEnabled())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("basicDataCleanTaskRelationList", getBasicDataCleanTaskRelationList())
                .toString();
    }
}
