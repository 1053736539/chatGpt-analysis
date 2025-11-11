package com.cb.leave.service;

import java.util.List;

import com.cb.common.core.domain.entity.SysUser;
import com.cb.leave.domain.LeaveBalances;
import com.cb.leave.domain.vo.LeaveBalancesVo;

/**
 * 假期额度Service接口
 *
 * @author yangcd
 * @date 2024-09-09
 */
public interface ILeaveBalancesService
{
    /**
     * 查询假期额度
     *
     * @param id 假期额度ID
     * @return 假期额度
     */
    public LeaveBalances selectLeaveBalancesById(Integer id);

    /**
     * 查询假期额度列表
     *
     * @param leaveBalances 假期额度
     * @return 假期额度集合
     */
    public List<LeaveBalances> selectLeaveBalancesList(LeaveBalances leaveBalances);

    /**
     * 新增假期额度
     *
     * @param leaveBalances 假期额度
     * @return 结果
     */
    public int insertLeaveBalances(LeaveBalances leaveBalances) throws Exception;

    /**
     * 根据假期年度自动生成假期额度
     * @param  leaveBalances 假期额度
     * @return
     * @throws Exception
     */
    public int batchGenerateLeaveBalances(LeaveBalances leaveBalances) throws Exception;

    /**
     * 修改假期额度
     *
     * @param leaveBalances 假期额度
     * @return 结果
     */
    public int updateLeaveBalances(LeaveBalances leaveBalances) throws Exception;

    /**
     * 批量删除假期额度
     *
     * @param ids 需要删除的假期额度ID
     * @return 结果
     */
    public int deleteLeaveBalancesByIds(Integer[] ids);

    /**
     * 删除假期额度信息
     *
     * @param id 假期额度ID
     * @return 结果
     */
    public int deleteLeaveBalancesById(Integer id);

    /**
     * @Auther: yangcd
     * @Date: 2024/9/10 14:15
     * @param userId 用户ID
     * @param leaveTypeId 假期类型ID
     * @param requestedDays 请假的天数
     * @param leaveYear 假期年份
     * @Description: 检查假期额度是否足够
     */
    public boolean checkBalance(Long userId, Long leaveTypeId, Integer requestedDays, Integer leaveYear) throws Exception;


    /**
     * 更新假期余额
     *
     * @param userId 用户ID
     * @param leaveTypeId 假期类型ID
     * @param approvedDays 批准的天数
     * @param leaveYear 假期年份
     * @throws Exception 如果未找到记录或其他错误
     */
    void updateLeaveBalancesAfterApproval(Long userId, Long leaveTypeId, Float approvedDays, Integer leaveYear) throws Exception;

    /**
     * 获取所有用户的应休未休统计
     *
     * @return List<LeaveBalanceDTO> 应休未休统计列表
     */
    List<LeaveBalancesVo> getUnusedLeaveStatistics(LeaveBalances leaveBalances);

    /**
     * 根据当前用户查询假期额度 若是管理员看所有，若是部门负责人看自己部门，若是普通用户看自己的
     * @param leaveBalances 查询条件
     * @param sysUser 当前用户
     * @param sysUser 是否分页查询
     * @return
     */
    List<LeaveBalancesVo> listLeaveBalanceByUser(LeaveBalances leaveBalances, SysUser sysUser, boolean pageQuery);

    void addAnnualLeaveForUser(SysUser sysUser);

    /**
     * 公休假应休未休根据年度发送提醒（用于定时任务触发）
     * @param year
     */
    void notifyUnusedPublicHolidayByYear(Integer year);

    /**
     * 公休假应休未休发送提醒
     * @param leaveBalances
     */
    void notifyUnusedPublicHoliday(LeaveBalances leaveBalances);
    //假期年度不是当前年
    void addUsedLeaveForUser(SysUser sysUser, Integer holidayYear);

    /**
     * 开始休假情况确认
     * @param year 年度
     */
    void openBalanceConfirm(Integer year);

    /**
     * 确认额度
     * @param ids
     * @return
     */
    int confirmBalance(Integer[] ids,Long leaderId);

    /**
     * 审批
     * @param ids
     * @param status 状态 3-审核通过 4-审核驳回
     * @param reason 意见
     * @return
     */
    int approveBalance(Integer[] ids,Integer status,String reason);

    /**
     * 修复是否修改的状态
     * @return
     */
    int fixChangeFlag();

    LeaveBalances leaveBalanceByUserId(LeaveBalances leaveBalances);
}
