package com.cb.table.domain;


import java.util.List;
import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;


/**
 * online表单对象 db_table_structure
 * 
 * @author ouyang
 * @date 2024-11-06
 */
public class DbTableStructure extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 表类型 0 主表 */
    @Excel(name = "表类型 0 主表")
    private Integer tableType;


    /** 表名 */
    @Excel(name = "表名")
    private String tableName;

    /** 表描述 */
    @Excel(name = "表描述")
    private String tableComments;

    /** 主键策略 0 自增长 1 UUID */
    @Excel(name = "主键策略 0 自增长 1 UUID")
    private Integer primaryKeyStrategy;

    /** 是否同步数据库 0 否 1是 */
    @Excel(name = "是否同步数据库 0 否 1是")
    private Integer syncDb;

    /** 数据库字段信息 */
    private List<DbTableStructureColumn> tableStructureColumnList;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setTableType(Integer tableType) 
    {
        this.tableType = tableType;
    }

    public Integer getTableType() 
    {
        return tableType;
    }
    public void setTableName(String tableName) 
    {
        this.tableName = tableName;
    }

    public String getTableName() 
    {
        return tableName;
    }
    public void setTableComments(String tableComments) 
    {
        this.tableComments = tableComments;
    }

    public String getTableComments() 
    {
        return tableComments;
    }
    public void setPrimaryKeyStrategy(Integer primaryKeyStrategy) 
    {
        this.primaryKeyStrategy = primaryKeyStrategy;
    }

    public Integer getPrimaryKeyStrategy() 
    {
        return primaryKeyStrategy;
    }
    public void setSyncDb(Integer syncDb) 
    {
        this.syncDb = syncDb;
    }

    public Integer getSyncDb() 
    {
        return syncDb;
    }

    public List<DbTableStructureColumn> getTableStructureColumnList() {
        return tableStructureColumnList;
    }

    public void setTableStructureColumnList(List<DbTableStructureColumn> tableStructureColumnList) {
        this.tableStructureColumnList = tableStructureColumnList;
    }


}
