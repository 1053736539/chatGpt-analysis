package com.cb.task.mapper;

import com.cb.task.domain.BizTaskHandle;
import com.cb.task.vo.TaskHandleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 任务分配的执行人/部门Mapper接口
 * 
 * @author yangxin
 * @date 2023-10-30
 */
public interface BizTaskHandleMapper 
{
    /**
     * 查询任务分配的执行人/部门
     * 
     * @param id 任务分配的执行人/部门ID
     * @return 任务分配的执行人/部门
     */
    public BizTaskHandle selectBizTaskHandleById(String id);

    /**
     * 查询任务分配的执行人/部门列表
     * 
     * @param bizTaskHandle 任务分配的执行人/部门
     * @return 任务分配的执行人/部门集合
     */
    public List<BizTaskHandle> selectBizTaskHandleList(BizTaskHandle bizTaskHandle);

    /**
     * 新增任务分配的执行人/部门
     * 
     * @param bizTaskHandle 任务分配的执行人/部门
     * @return 结果
     */
    public int insertBizTaskHandle(BizTaskHandle bizTaskHandle);

    /**
     * 修改任务分配的执行人/部门
     * 
     * @param bizTaskHandle 任务分配的执行人/部门
     * @return 结果
     */
    public int updateBizTaskHandle(BizTaskHandle bizTaskHandle);

    /**
     * 删除任务分配的执行人/部门
     * 
     * @param id 任务分配的执行人/部门ID
     * @return 结果
     */
    public int deleteBizTaskHandleById(String id);

    /**
     * 根据taskId删除处理记录
     * @param taskId
     * @return
     */
    public int deleteBizTaskHandleByTaskId(String taskId);

    /**
     * 批量删除任务分配的执行人/部门
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizTaskHandleByIds(String[] ids);

    /**
     * 查询我的待认领列表
     * @param bizTaskHandle
     * @param userName
     * @return
     */
    public List<BizTaskHandle> listMyUnClaimList(@Param("entity") BizTaskHandle bizTaskHandle,
                                                 @Param("userName") String userName);

    /**
     * 查询我已读列表
     * @param bizTaskHandle
     * @param userName
     * @return
     */
    public List<BizTaskHandle> listMyReadList(@Param("entity") BizTaskHandle bizTaskHandle,
                                              @Param("userName") String userName);

    /**
     * 查询我的待办列表
     * @param bizTaskHandle
     * @return
     */
    public List<BizTaskHandle> listMyTodoList(@Param("entity") BizTaskHandle bizTaskHandle,
                                              @Param("userName") String userName,
                                              @Param("leaderDeptId") Long leaderDeptId);

    /**
     * 查询已完成的列表（我个人已完成 + 当前为部门负责人时的部门已完成任务）
     * @param bizTaskHandle
     * @param userName
     * @param leaderDeptId
     * @return
     */
    public List<BizTaskHandle> listMyCompleteList(@Param("entity") BizTaskHandle bizTaskHandle,
                                                  @Param("userName") String userName,
                                                  @Param("leaderDeptId") Long leaderDeptId);

    /**
     * 查询用于年度/季度考核时一键引用的任务经办信息
     * @return
     */
    public List<TaskHandleVo.List4AssessResp> list4Assess(@Param("req") TaskHandleVo.List4AssessReq req);

    /**
     * 查询统计用的已完成、进行中的任务列表（压力关爱）
     * @param bizTaskHandle
     * @param userName
     * @param deptId
     * @return
     */
    public List<BizTaskHandle> listDeptOrUserList4Statistics(@Param("entity") BizTaskHandle bizTaskHandle,
                                                             @Param("userId") Long userId,
                                                             @Param("userName") String userName,
                                                             @Param("deptId") Long deptId);

    /**
     * 查询我的超时的列表
     * @param bizTaskHandle
     * @param userName
     * @param currDateTime 当前系统时间字符串 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public List<BizTaskHandle> listMyExpireList(@Param("entity") BizTaskHandle bizTaskHandle,
                                                  @Param("userName") String userName,
                                                  @Param("currDateTime") String currDateTime);


    /**
     * 查询需要到期提醒的经办记录
     * @param now 当前时间
     * @param endTime 过期检查时间
     * @return
     */
    public List<BizTaskHandle> list4ExpireNotice(@Param("now")String now,
                                                 @Param("endTime") String endTime);

}
