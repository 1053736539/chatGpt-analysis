package com.cb.table.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 数据库字段对象 db_table_structure_column
 * 
 * @author ouyang
 * @date 2024-11-06
 */
public class DbTableStructureColumn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String columnId;

    /** 关联表db_table_structure ID */
    @Excel(name = "关联表db_table_structure ID")
    private String tableId;

    /** 排序 */
    @Excel(name = "排序")
    private Integer orderNum;

    /** 字段名称 */
    @Excel(name = "字段名称")
    private String columnName;

    /** 字段备注 */
    @Excel(name = "字段备注")
    private String columnComments;

    /** 字段长度 */
    @Excel(name = "字段长度")
    private Integer columnLength;

    /** 小数点 */
    @Excel(name = "小数点")
    private Integer columnPointLength;

    /** 字段类型 */
    @Excel(name = "字段类型")
    private String columnType;

    /** 默认值 */
    @Excel(name = "默认值")
    private String defaultValue;

    /** 是否主键 0 否 1是 */
    @Excel(name = "是否主键 0 否 1是")
    private Integer primaryKey;

    /** 非空 0 否 1是 */
    @Excel(name = "非空 0 否 1是")
    private Integer mustFill;

    /** 是否同步数据库 0 否 1是 */
    @Excel(name = "是否同步数据库 0 否 1是")
    private Integer syncDb;

    public void setColumnId(String columnId) 
    {
        this.columnId = columnId;
    }

    public String getColumnId() 
    {
        return columnId;
    }
    public void setTableId(String tableId) 
    {
        this.tableId = tableId;
    }

    public String getTableId() 
    {
        return tableId;
    }
    public void setOrderNum(Integer orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Integer getOrderNum() 
    {
        return orderNum;
    }
    public void setColumnName(String columnName) 
    {
        this.columnName = columnName;
    }

    public String getColumnName() 
    {
        return columnName;
    }
    public void setColumnComments(String columnComments) 
    {
        this.columnComments = columnComments;
    }

    public String getColumnComments() 
    {
        return columnComments;
    }
    public void setColumnLength(Integer columnLength) 
    {
        this.columnLength = columnLength;
    }

    public Integer getColumnLength() 
    {
        return columnLength;
    }
    public void setColumnPointLength(Integer columnPointLength) 
    {
        this.columnPointLength = columnPointLength;
    }

    public Integer getColumnPointLength() 
    {
        return columnPointLength;
    }
    public void setColumnType(String columnType) 
    {
        this.columnType = columnType;
    }

    public String getColumnType() 
    {
        return columnType;
    }
    public void setDefaultValue(String defaultValue) 
    {
        this.defaultValue = defaultValue;
    }

    public String getDefaultValue() 
    {
        return defaultValue;
    }
    public void setPrimaryKey(Integer primaryKey) 
    {
        this.primaryKey = primaryKey;
    }

    public Integer getPrimaryKey() 
    {
        return primaryKey;
    }
    public void setMustFill(Integer mustFill) 
    {
        this.mustFill = mustFill;
    }

    public Integer getMustFill() 
    {
        return mustFill;
    }
    public void setSyncDb(Integer syncDb) 
    {
        this.syncDb = syncDb;
    }

    public Integer getSyncDb() 
    {
        return syncDb;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("columnId", getColumnId())
            .append("tableId", getTableId())
            .append("orderNum", getOrderNum())
            .append("columnName", getColumnName())
            .append("columnComments", getColumnComments())
            .append("columnLength", getColumnLength())
            .append("columnPointLength", getColumnPointLength())
            .append("columnType", getColumnType())
            .append("defaultValue", getDefaultValue())
            .append("primaryKey", getPrimaryKey())
            .append("mustFill", getMustFill())
            .append("syncDb", getSyncDb())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
