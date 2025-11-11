package com.cb.basedata.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 数据清洗任务字段关系对象 basic_data_clean_task_relation
 * 
 * @author ouyang
 * @date 2024-11-01
 */
public class BasicDataCleanTaskRelation
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String relationId;

    /** 任务ID */
    @Excel(name = "任务ID")
    private String cleanTaskId;

    /** 源数据字段 */
    @Excel(name = "源数据字段")
    private String sourceColumn;

    /** 目标字段 */
    @Excel(name = "目标字段")
    private String targetColumn;

    /** 是否清洗  0否，1是 */
    @Excel(name = "是否清洗  0否，1是")
    private Integer enableClean;

    /** AI 清洗模型编码 */
    @Excel(name = "AI 清洗模型编码")
    private String aiModeCode;

    public void setRelationId(String relationId) 
    {
        this.relationId = relationId;
    }

    public String getRelationId() 
    {
        return relationId;
    }
    public void setCleanTaskId(String cleanTaskId) 
    {
        this.cleanTaskId = cleanTaskId;
    }

    public String getCleanTaskId() 
    {
        return cleanTaskId;
    }
    public void setSourceColumn(String sourceColumn) 
    {
        this.sourceColumn = sourceColumn;
    }

    public String getSourceColumn() 
    {
        return sourceColumn;
    }
    public void setTargetColumn(String targetColumn) 
    {
        this.targetColumn = targetColumn;
    }

    public String getTargetColumn() 
    {
        return targetColumn;
    }
    public void setEnableClean(Integer enableClean) 
    {
        this.enableClean = enableClean;
    }

    public Integer getEnableClean() 
    {
        return enableClean;
    }
    public void setAiModeCode(String aiModeCode) 
    {
        this.aiModeCode = aiModeCode;
    }

    public String getAiModeCode() 
    {
        return aiModeCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("relationId", getRelationId())
            .append("cleanTaskId", getCleanTaskId())
            .append("sourceColumn", getSourceColumn())
            .append("targetColumn", getTargetColumn())
            .append("enableClean", getEnableClean())
            .append("aiModeCode", getAiModeCode())
            .toString();
    }
}
