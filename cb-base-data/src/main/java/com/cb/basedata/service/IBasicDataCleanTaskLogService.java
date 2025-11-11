package com.cb.basedata.service;

import java.util.List;

import com.cb.basedata.domain.BasicDataCleanTaskLog;

/**
 * 数据清洗任务日志Service接口
 *
 * @author ouyang
 * @date 2024-11-01
 */
public interface IBasicDataCleanTaskLogService {
    /**
     * 查询数据清洗任务日志
     *
     * @param logId 数据清洗任务日志ID
     * @return 数据清洗任务日志
     */
    public BasicDataCleanTaskLog selectBasicDataCleanTaskLogById(String logId);

    /**
     * 查询数据清洗任务日志列表
     *
     * @param basicDataCleanTaskLog 数据清洗任务日志
     * @return 数据清洗任务日志集合
     */
    public List<BasicDataCleanTaskLog> selectBasicDataCleanTaskLogList(BasicDataCleanTaskLog basicDataCleanTaskLog);

    /**
     * 新增数据清洗任务日志
     *
     * @param basicDataCleanTaskLog 数据清洗任务日志
     * @return 结果
     */
    public int insertBasicDataCleanTaskLog(BasicDataCleanTaskLog basicDataCleanTaskLog);

    /**
     * 修改数据清洗任务日志
     *
     * @param basicDataCleanTaskLog 数据清洗任务日志
     * @return 结果
     */
    public int updateBasicDataCleanTaskLog(BasicDataCleanTaskLog basicDataCleanTaskLog);

    /**
     * 批量删除数据清洗任务日志
     *
     * @param logIds 需要删除的数据清洗任务日志ID
     * @return 结果
     */
    public int deleteBasicDataCleanTaskLogByIds(String[] logIds);

    /**
     * 删除数据清洗任务日志信息
     *
     * @param logId 数据清洗任务日志ID
     * @return 结果
     */
    public int deleteBasicDataCleanTaskLogById(String logId);
}
