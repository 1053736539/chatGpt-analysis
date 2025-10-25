package com.cb.table.mapper;

import com.cb.table.domain.DbTableStructure;
import com.cb.table.domain.DbTableStructureColumn;

import java.util.List;

/**
 * online表单Mapper接口
 * 
 * @author ouyang
 * @date 2024-11-06
 */
public interface DbTableStructureMapper 
{
    /**
     * 查询online表单
     * 
     * @param id online表单ID
     * @return online表单
     */
    public DbTableStructure selectDbTableStructureById(String id);


    /**
     * 查询online表单列表
     * 
     * @param dbTableStructure online表单
     * @return online表单集合
     */
    public List<DbTableStructure> selectDbTableStructureList(DbTableStructure dbTableStructure);

    /**
     * 新增online表单
     * 
     * @param dbTableStructure online表单
     * @return 结果
     */
    public int insertDbTableStructure(DbTableStructure dbTableStructure);

    /**
     * 修改online表单
     * 
     * @param dbTableStructure online表单
     * @return 结果
     */
    public int updateDbTableStructure(DbTableStructure dbTableStructure);

    /**
     * 删除online表单
     * 
     * @param id online表单ID
     * @return 结果
     */
    public int deleteDbTableStructureById(String id);

    /**
     * 批量删除online表单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbTableStructureByIds(String[] ids);

    /**
     * 批量删除数据库字段
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbTableStructureColumnByTableIds(String[] ids);
    
    /**
     * 批量新增数据库字段
     * 
     * @param dbTableStructureColumnList 数据库字段列表
     * @return 结果
     */
    public int batchDbTableStructureColumn(List<DbTableStructureColumn> dbTableStructureColumnList);


    /**
     * 通过online表单ID删除数据库字段信息
     * 
     * @param
     * @return 结果
     */
    public int deleteDbTableStructureColumnByTableId(String id);

    public int changeAsyncStatus(String id);

}
