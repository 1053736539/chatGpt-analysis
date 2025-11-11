package com.cb.basedata.mapper;

import java.util.List;
import com.cb.basedata.domain.BasicDataCleanTask;
import com.cb.basedata.domain.BasicDataCleanTaskRelation;

/**
 * 数据清洗任务Mapper接口
 * 
 * @author ouyang
 * @date 2024-11-01
 */
public interface BasicDataCleanTaskMapper 
{
    /**
     * 查询数据清洗任务
     * 
     * @param taskId 数据清洗任务ID
     * @return 数据清洗任务
     */
    public BasicDataCleanTask selectBasicDataCleanTaskById(String taskId);

    /**
     * 查询数据清洗任务列表
     * 
     * @param basicDataCleanTask 数据清洗任务
     * @return 数据清洗任务集合
     */
    public List<BasicDataCleanTask> selectBasicDataCleanTaskList(BasicDataCleanTask basicDataCleanTask);

    /**
     * 新增数据清洗任务
     * 
     * @param basicDataCleanTask 数据清洗任务
     * @return 结果
     */
    public int insertBasicDataCleanTask(BasicDataCleanTask basicDataCleanTask);

    /**
     * 修改数据清洗任务
     * 
     * @param basicDataCleanTask 数据清洗任务
     * @return 结果
     */
    public int updateBasicDataCleanTask(BasicDataCleanTask basicDataCleanTask);

    /**
     * 删除数据清洗任务
     * 
     * @param taskId 数据清洗任务ID
     * @return 结果
     */
    public int deleteBasicDataCleanTaskById(String taskId);

    /**
     * 批量删除数据清洗任务
     * 
     * @param taskIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasicDataCleanTaskByIds(String[] taskIds);

    /**
     * 批量删除数据清洗任务字段关系
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasicDataCleanTaskRelationByCleanTaskIds(String[] taskIds);
    
    /**
     * 批量新增数据清洗任务字段关系
     * 
     * @param basicDataCleanTaskRelationList 数据清洗任务字段关系列表
     * @return 结果
     */
    public int batchBasicDataCleanTaskRelation(List<BasicDataCleanTaskRelation> basicDataCleanTaskRelationList);
    

    /**
     * 通过数据清洗任务ID删除数据清洗任务字段关系信息
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public int deleteBasicDataCleanTaskRelationByCleanTaskId(String taskId);
}
