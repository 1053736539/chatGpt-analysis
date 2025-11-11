package com.cb.basedata.service;

import java.util.List;
import com.cb.basedata.domain.BasicDataCleanTask;
import com.cb.basedata.domain.vo.CleanSourceDataParam;
import com.cb.common.core.domain.AjaxResult;

/**
 * 数据清洗任务Service接口
 * 
 * @author ouyang
 * @date 2024-11-01
 */
public interface IBasicDataCleanTaskService 
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
     * 批量删除数据清洗任务
     * 
     * @param taskIds 需要删除的数据清洗任务ID
     * @return 结果
     */
    public int deleteBasicDataCleanTaskByIds(String[] taskIds);

    /**
     * 删除数据清洗任务信息
     * 
     * @param taskId 数据清洗任务ID
     * @return 结果
     */
    public int deleteBasicDataCleanTaskById(String taskId);

    /**
     * 数据清洗
     * @param param
     * @return
     */
    public int dataCleaning(CleanSourceDataParam param);


    public AjaxResult importTemplate(String taskId);
}
