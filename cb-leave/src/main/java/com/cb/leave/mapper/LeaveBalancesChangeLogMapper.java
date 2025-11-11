package com.cb.leave.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.cb.leave.domain.LeaveBalancesChangeLog;

/**
 * 假期额度变更日志Mapper接口
 *
 * @author ruoyi
 * @date 2025-03-19
 */
public interface LeaveBalancesChangeLogMapper
{
    /**
     * 查询假期额度变更日志
     *
     * @param id 假期额度变更日志ID
     * @return 假期额度变更日志
     */
    public LeaveBalancesChangeLog selectLeaveBalancesChangeLogById(Integer id);

    /**
     * 根据ids查询
     * @param ids
     * @return
     */
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
     *
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<LeaveBalancesChangeLog> entities);

    /**
     * 修改假期额度变更日志
     *
     * @param leaveBalancesChangeLog 假期额度变更日志
     * @return 结果
     */
    public int updateLeaveBalancesChangeLog(LeaveBalancesChangeLog leaveBalancesChangeLog);

    /**
     * 删除假期额度变更日志
     *
     * @param id 假期额度变更日志ID
     * @return 结果
     */
    public int deleteLeaveBalancesChangeLogById(Integer id);

    /**
     * 批量删除假期额度变更日志
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLeaveBalancesChangeLogByIds(Integer[] ids);

    /**
     * 检查并返回存在的id
     * @param ids
     * @return
     */
    public List<Integer> checkExistIds(Integer[] ids);

    /**
     * 查询可能需要修正的数据(有确认记录，但是额度那边是否修改的标记是否的)
     */
    public List<LeaveBalancesChangeLog> listMaybeNeedFixChangeFlagList();
}
