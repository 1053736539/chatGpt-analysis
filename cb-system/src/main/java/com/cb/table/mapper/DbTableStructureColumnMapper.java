package com.cb.table.mapper;

import com.cb.table.domain.DbTableStructureColumn;

import java.util.List;

/**
 * 数据库字段Mapper接口
 * 
 * @author ouyang
 * @date 2024-11-07
 */
public interface DbTableStructureColumnMapper 
{
    /**
     * 查询数据库字段
     * 
     * @param columnId 数据库字段ID
     * @return 数据库字段
     */
    public DbTableStructureColumn selectDbTableStructureColumnById(String columnId);

    public List<DbTableStructureColumn> selectDbTableStructureColumnByTableId(String tableId);

    /**
     * 查询数据库字段列表
     * 
     * @param dbTableStructureColumn 数据库字段
     * @return 数据库字段集合
     */
    public List<DbTableStructureColumn> selectDbTableStructureColumnList(DbTableStructureColumn dbTableStructureColumn);

    /**
     * 新增数据库字段
     * 
     * @param dbTableStructureColumn 数据库字段
     * @return 结果
     */
    public int insertDbTableStructureColumn(DbTableStructureColumn dbTableStructureColumn);

    /**
     * 修改数据库字段
     * 
     * @param dbTableStructureColumn 数据库字段
     * @return 结果
     */
    public int updateDbTableStructureColumn(DbTableStructureColumn dbTableStructureColumn);

    /**
     * 删除数据库字段
     * 
     * @param columnId 数据库字段ID
     * @return 结果
     */
    public int deleteDbTableStructureColumnById(String columnId);

    /**
     * 批量删除数据库字段
     * 
     * @param columnIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbTableStructureColumnByIds(String[] columnIds);
}
