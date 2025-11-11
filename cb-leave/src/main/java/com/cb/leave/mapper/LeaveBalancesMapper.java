package com.cb.leave.mapper;

import com.cb.leave.domain.LeaveBalances;
import com.cb.leave.domain.vo.LeaveBalancesVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 假期额度Mapper接口
 * 
 * @author yangcd
 * @date 2024-09-09
 */
public interface LeaveBalancesMapper 
{
    /**
     * 查询假期额度
     * 
     * @param id 假期额度ID
     * @return 假期额度
     */
    public LeaveBalances selectLeaveBalancesById(Integer id);

    /**
     * 根据ids查询假期额度
     *
     * @param ids ids
     * @return 结果
     */
    public List<LeaveBalances> selectListByIds(Integer[] ids);

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
    public int insertLeaveBalances(LeaveBalances leaveBalances);

    /**
     * 修改假期额度
     * 
     * @param leaveBalances 假期额度
     * @return 结果
     */
    public int updateLeaveBalances(LeaveBalances leaveBalances);

    /**
     * 删除假期额度
     * 
     * @param id 假期额度ID
     * @return 结果
     */
    public int deleteLeaveBalancesById(Integer id);

    /**
     * 批量删除假期额度
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLeaveBalancesByIds(Integer[] ids);

    /**
     * @Auther: yangcd
     * @Date: 2024/9/10 10:07
     * @param userId leaveTypeId leaveYear
     * @Description: 根据用户ID、假期类型和年份查询假期余额
     */
    LeaveBalances selectByUserIdAndType(@Param("userId") Long userId,
                                        @Param("leaveTypeId") Long leaveTypeId,
                                        @Param("leaveYear") Integer leaveYear);

    /**
     * 查询所有用户的应休未休统计数据
     *
     * @return List<LeaveBalanceDTO> 应休未休统计列表
     */

    List<LeaveBalancesVo> findUnusedLeaveStatistics(LeaveBalances leaveBalances);

    /**
     *  根据当前用户查询假期额度
     *  1.若是管理员看所有，
     *  3.若是部门负责人看自己部门(设置参数deptId)，
     *  4.若是普通用户看自己的（设置query的userId）
     * @param leaveBalances
     * @param deptIds
     * @return
     */
    List<LeaveBalancesVo> listLeaveBalanceByUser(@Param("query") LeaveBalances leaveBalances,
                                                 @Param("deptIds") List<Long> deptIds,
                                                 @Param("leaderId") Long leaderId,
                                                 @Param("exclude") Boolean exclude);

    /**
     * 根据年份批量开启状态为待确认
     * @param year
     * @param updateBy
     * @param updateTime
     * @return
     */
    int batchOpenStatusByYear(@Param("year") Integer year,
                              @Param("updateBy") String updateBy,
                              @Param("updateTime") Date updateTime);

    /**
     * 批量确认
     * @param ids
     * @param status
     * @param leaderId
     * @param updateBy
     * @param updateTime
     * @return
     */
    int batchConfirm(@Param("ids") Integer[] ids,
                     @Param("status") Integer status,
                     @Param("leaderId") Long leaderId,
                     @Param("updateBy") String updateBy,
                     @Param("updateTime") Date updateTime);

    /**
     * 批量审核
     * @param ids 主键数组
     * @param status 审核状态 3-审核通过 4-审核不通过  {@link com.cb.leave.enums.BalanceStatus}
     * @param approvalOpinion 审核意见
     * @param updateBy
     * @param updateTime
     * @return
     */
    int batchApproveByIds(@Param("ids") Integer[] ids,
                          @Param("status") Integer status,
                          @Param("approvalOpinion") String approvalOpinion,
                          @Param("updateBy") String updateBy,
                          @Param("updateTime") Date updateTime);

    /**
     * 批量设置changeFlag
     * @param ids
     * @param changeFlag
     * @return
     */
    int batchSetChangeFlag(@Param("ids") Integer[] ids,
                              @Param("changeFlag") Integer changeFlag);

    /**
     * 应休未休数据导出
     * @param ids
     * @param changeFlag
     * @return
     */
    List<Map<String, Object>> exportLeaveBalancesData(Map<String, Object> params);

    LeaveBalances selectLeaveBalanceByUserId(LeaveBalances leaveBalances);
}
