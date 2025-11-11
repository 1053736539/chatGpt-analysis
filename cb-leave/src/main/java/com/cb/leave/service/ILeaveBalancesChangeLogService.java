package com.cb.leave.service;

import java.util.List;
import com.cb.leave.domain.LeaveBalancesChangeLog;

/**
 * 假期额度变更日志Service接口
 *
 * @author ruoyi
 * @date 2025-03-19
 */
public interface ILeaveBalancesChangeLogService
{
    /**
     * 查询假期额度变更日志
     *
     * @param id 假期额度变更日志ID
     * @return 假期额度变更日志
     */
    public LeaveBalancesChangeLog selectLeaveBalancesChangeLogById(Integer id);

    public List<LeaveBalancesChangeLog> selectListByIds(Integer[] ids);

    /**
     * 查询假期额度变更日志列表
     *
     * @param leaveBalancesChangeLog 假期额度变更日志
     * @return 假期额度变更日志集合
     */
    public List<LeaveBalancesChangeLog> selectLeaveBalancesChangeLogList(LeaveBalancesChangeLog leaveBalancesChangeLog);

    /**
     * 新增假期额度变更日志
     *
     * @param leaveBalancesChangeLog 假期额度变更日志
     * @return 结果
     */
    public int insertLeaveBalancesChangeLog(LeaveBalancesChangeLog leaveBalancesChangeLog);

    /**
     * 批量新增假期额度变更日志
     * @param entities
     * @return
     */
    public int insertBatch(List<LeaveBalancesChangeLog> entities);

    /**
     * 修改假期额度变更日志
     *
     * @param leaveBalancesChangeLog 假期额度变更日志
     * @return 结果
     */
    public int updateLeaveBalancesChangeLog(LeaveBalancesChangeLog leaveBalancesChangeLog);

    /**
     * 批量删除假期额度变更日志
     *
     * @param ids 需要删除的假期额度变更日志ID
     * @return 结果
     */
    public int deleteLeaveBalancesChangeLogByIds(Integer[] ids);

    /**
     * 删除假期额度变更日志信息
     *
     * @param id 假期额度变更日志ID
     * @return 结果
     */
    public int deleteLeaveBalancesChangeLogById(Integer id);

    /**
     * 检查并返回存在的id
     * @param ids
     * @return
     */
    List<Integer> checkExistIds(Integer[] ids);

    List<LeaveBalancesChangeLog> listMaybeNeedFixChangeFlagList();

}
