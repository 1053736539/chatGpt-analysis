package com.cb.leave.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Date;
import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.leave.mapper.LeaveBalancesChangeLogMapper;
import com.cb.leave.domain.LeaveBalancesChangeLog;
import com.cb.leave.service.ILeaveBalancesChangeLogService;

/**
 * 假期额度变更日志Service业务层处理
 *
 * @author ruoyi
 * @date 2025-03-19
 */
@Service
public class LeaveBalancesChangeLogServiceImpl implements ILeaveBalancesChangeLogService
{
    @Autowired
    private LeaveBalancesChangeLogMapper leaveBalancesChangeLogMapper;

    /**
     * 查询假期额度变更日志
     *
     * @param id 假期额度变更日志ID
     * @return 假期额度变更日志
     */
    @Override
    public LeaveBalancesChangeLog selectLeaveBalancesChangeLogById(Integer id)
    {
        return leaveBalancesChangeLogMapper.selectLeaveBalancesChangeLogById(id);
    }

    @Override
    public List<LeaveBalancesChangeLog> selectListByIds(Integer[] ids) {
        if(null == ids || ids.length < 1){
            return Collections.emptyList();
        }
        return leaveBalancesChangeLogMapper.selectListByIds(ids);
    }

    /**
     * 查询假期额度变更日志列表
     *
     * @param leaveBalancesChangeLog 假期额度变更日志
     * @return 假期额度变更日志
     */
    @Override
    public List<LeaveBalancesChangeLog> selectLeaveBalancesChangeLogList(LeaveBalancesChangeLog leaveBalancesChangeLog)
    {
        return leaveBalancesChangeLogMapper.selectLeaveBalancesChangeLogList(leaveBalancesChangeLog);
    }

    /**
     * 新增假期额度变更日志
     *
     * @param leaveBalancesChangeLog 假期额度变更日志
     * @return 结果
     */
    @Override
    public int insertLeaveBalancesChangeLog(LeaveBalancesChangeLog leaveBalancesChangeLog)
    {
        try{
            leaveBalancesChangeLog.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        leaveBalancesChangeLog.setCreateTime(DateUtils.getNowDate());
        return leaveBalancesChangeLogMapper.insertLeaveBalancesChangeLog(leaveBalancesChangeLog);
    }


    /**
     * 批量新增假期额度变更日志
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<LeaveBalancesChangeLog> entities) {
        if(null == entities || entities.isEmpty()){
            return 0;
        }
        String userName = null;
        try{
            userName = SecurityUtils.getUsername();
        } catch (Exception e){}
        Date nowDate = DateUtils.getNowDate();
        String finalUserName = userName;
        entities.parallelStream().forEach(item -> {
//            item.setId(IdUtils.randomUUID());
            item.setCreateBy(finalUserName);
            item.setCreateTime(nowDate);
        });
        // 定义每批次的大小
        int batchSize = 500;
        int totalInserted = 0;
        int num = entities.size() / batchSize + (entities.size() % batchSize == 0 ? 0 : 1);
        for (int i = 0; i < num; i++) {
            int start = i * batchSize;
            int end = Math.min(start + batchSize, entities.size());
            totalInserted +=  leaveBalancesChangeLogMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改假期额度变更日志
     *
     * @param leaveBalancesChangeLog 假期额度变更日志
     * @return 结果
     */
    @Override
    public int updateLeaveBalancesChangeLog(LeaveBalancesChangeLog leaveBalancesChangeLog)
    {
        try{
            leaveBalancesChangeLog.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        leaveBalancesChangeLog.setUpdateTime(DateUtils.getNowDate());
        return leaveBalancesChangeLogMapper.updateLeaveBalancesChangeLog(leaveBalancesChangeLog);
    }

    /**
     * 批量删除假期额度变更日志
     *
     * @param ids 需要删除的假期额度变更日志ID
     * @return 结果
     */
    @Override
    public int deleteLeaveBalancesChangeLogByIds(Integer[] ids)
    {
        return leaveBalancesChangeLogMapper.deleteLeaveBalancesChangeLogByIds(ids);
    }

    /**
     * 删除假期额度变更日志信息
     *
     * @param id 假期额度变更日志ID
     * @return 结果
     */
    @Override
    public int deleteLeaveBalancesChangeLogById(Integer id)
    {
        return leaveBalancesChangeLogMapper.deleteLeaveBalancesChangeLogById(id);
    }

    @Override
    public List<Integer> checkExistIds(Integer[] ids) {
        if(null == ids || ids.length < 1){
            return Collections.emptyList();
        }
        return leaveBalancesChangeLogMapper.checkExistIds(ids);
    }

    @Override
    public List<LeaveBalancesChangeLog> listMaybeNeedFixChangeFlagList() {
        return leaveBalancesChangeLogMapper.listMaybeNeedFixChangeFlagList();
    }
}
