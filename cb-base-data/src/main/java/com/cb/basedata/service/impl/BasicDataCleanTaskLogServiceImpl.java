package com.cb.basedata.service.impl;

import java.util.List;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.basedata.mapper.BasicDataCleanTaskLogMapper;
import com.cb.basedata.domain.BasicDataCleanTaskLog;
import com.cb.basedata.service.IBasicDataCleanTaskLogService;

/**
 * 数据清洗任务日志Service业务层处理
 *
 * @author ouyang
 * @date 2024-11-01
 */
@Service
public class BasicDataCleanTaskLogServiceImpl implements IBasicDataCleanTaskLogService {
    @Autowired
    private BasicDataCleanTaskLogMapper basicDataCleanTaskLogMapper;

    /**
     * 查询数据清洗任务日志
     *
     * @param logId 数据清洗任务日志ID
     * @return 数据清洗任务日志
     */
    @Override
    public BasicDataCleanTaskLog selectBasicDataCleanTaskLogById(String logId) {
        return basicDataCleanTaskLogMapper.selectBasicDataCleanTaskLogById(logId);
    }

    /**
     * 查询数据清洗任务日志列表
     *
     * @param basicDataCleanTaskLog 数据清洗任务日志
     * @return 数据清洗任务日志
     */
    @Override
    public List<BasicDataCleanTaskLog> selectBasicDataCleanTaskLogList(BasicDataCleanTaskLog basicDataCleanTaskLog) {
        return basicDataCleanTaskLogMapper.selectBasicDataCleanTaskLogList(basicDataCleanTaskLog);
    }

    /**
     * 新增数据清洗任务日志
     *
     * @param basicDataCleanTaskLog 数据清洗任务日志
     * @return 结果
     */
    @Override
    public int insertBasicDataCleanTaskLog(BasicDataCleanTaskLog basicDataCleanTaskLog) {
        basicDataCleanTaskLog.setLogId(IdUtils.randomUUID());
        basicDataCleanTaskLog.setCreateTime(DateUtils.getNowDate());
        basicDataCleanTaskLog.setCreateBy(SecurityUtils.getUsername());
        return basicDataCleanTaskLogMapper.insertBasicDataCleanTaskLog(basicDataCleanTaskLog);
    }

    /**
     * 修改数据清洗任务日志
     *
     * @param basicDataCleanTaskLog 数据清洗任务日志
     * @return 结果
     */
    @Override
    public int updateBasicDataCleanTaskLog(BasicDataCleanTaskLog basicDataCleanTaskLog) {
        return basicDataCleanTaskLogMapper.updateBasicDataCleanTaskLog(basicDataCleanTaskLog);
    }

    /**
     * 批量删除数据清洗任务日志
     *
     * @param logIds 需要删除的数据清洗任务日志ID
     * @return 结果
     */
    @Override
    public int deleteBasicDataCleanTaskLogByIds(String[] logIds) {
        return basicDataCleanTaskLogMapper.deleteBasicDataCleanTaskLogByIds(logIds);
    }

    /**
     * 删除数据清洗任务日志信息
     *
     * @param logId 数据清洗任务日志ID
     * @return 结果
     */
    @Override
    public int deleteBasicDataCleanTaskLogById(String logId) {
        return basicDataCleanTaskLogMapper.deleteBasicDataCleanTaskLogById(logId);
    }
}
