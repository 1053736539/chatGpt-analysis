package com.cb.table.service;

import com.cb.table.domain.DbTableStructure;

import java.util.List;

/**
 * online表单Service接口
 * 
 * @author ouyang
 * @date 2024-11-06
 */
public interface IDbTableStructureService 
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
     * 批量删除online表单
     * 
     * @param ids 需要删除的online表单ID
     * @return 结果
     */
    public int deleteDbTableStructureByIds(String[] ids);

    /**
     * 删除online表单信息
     * 
     * @param id online表单ID
     * @return 结果
     */
    public int deleteDbTableStructureById(String id);

    public void asyncStructure2Db(String id);
}
