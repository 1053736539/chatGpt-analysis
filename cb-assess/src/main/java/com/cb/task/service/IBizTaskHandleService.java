package com.cb.task.service;

import com.cb.task.domain.BizTaskHandle;
import com.cb.task.vo.TaskHandleVo;

import java.util.List;

/**
 * 任务分配的执行人/部门Service接口
 * 
 * @author yangxin
 * @date 2023-10-30
 */
public interface IBizTaskHandleService 
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
     * 批量删除任务分配的执行人/部门
     * 
     * @param ids 需要删除的任务分配的执行人/部门ID
     * @return 结果
     */
    public int deleteBizTaskHandleByIds(String[] ids);

    /**
     * 删除任务分配的执行人/部门信息
     * 
     * @param id 任务分配的执行人/部门ID
     * @return 结果
     */
    public int deleteBizTaskHandleById(String id);

    /**
     * 根据指定的任务id删除任务处理记录
     * @param taskId
     * @return
     */
    public int deleteBizTaskHandleByTaskId(String taskId);

    /**
     * 查询我的待认领列表
     * @param bizTaskHandle
     * @param userName
     * @return
     */
    public List<BizTaskHandle> listMyUnClaimList(BizTaskHandle bizTaskHandle,String userName);


    /**
     * 查询我的已阅列表
     * @param bizTaskHandle
     * @param userName
     * @return
     */
    public List<BizTaskHandle> listMyReadList(BizTaskHandle bizTaskHandle,String userName);

    /**
     * 查询我的待办列表
     * @param bizTaskHandle
     * @param userName 当前用户username
     * @param leaderDeptId 若为当前单位负责人，设置其当前负责的单位id,否则设置null
     * @return
     */
    public List<BizTaskHandle> listMyTodoList(BizTaskHandle bizTaskHandle,String userName,Long leaderDeptId);

    /**
     * 查询已完成的列表（我个人已完成 + 当前为部门负责人时的部门已完成任务）
     * @param bizTaskHandle
     * @param userName 当前用户username
     * @param leaderDeptId 若为当前单位负责人，设置其当前负责的单位id,否则设置null
     * @return
     */
    public List<BizTaskHandle> listMyCompleteList(BizTaskHandle bizTaskHandle,String userName,Long leaderDeptId);

    /**
     * 查询统计用的任务列表（压力关爱）
     * @param bizTaskHandle
     * @param userId
     * @param userName
     * @param deptId
     * @return
     */
    public List<BizTaskHandle> listDeptOrUserList4Statistics(BizTaskHandle bizTaskHandle,Long userId, String userName,Long deptId);

    /**
     * 查询我的过期列表
     * @param bizTaskHandle
     * @param userName
     * @param leaderDeptId
     * @return
     */
    public List<BizTaskHandle> listMyExpireList(BizTaskHandle bizTaskHandle,String userName);

    /**
     * 指派部门任务到具体的执行人
     * @param req
     */
    public void assign(TaskHandleVo.AssignReq req);

    /**
     * 确认任务（执行人接受确认）
     * @param handleId
     */
    public void confirm(String handleId);

    /**
     * 转办
     * @param req
     */
    public void transfer(TaskHandleVo.TransferReq req);

    /**
     * 进度更新
     * @param req
     */
    public void progess(TaskHandleVo.ProgressReq req);

    /**
     * 处理完成
     * @param req
     */
    public void complete(TaskHandleVo.CompleteReq req);

    /**
     * 认领
     * @param id
     */
    public void claim(String id);


    /**
     * 已阅
     * @param id
     */
    public void read(String id);

    /**
     * 催办
     * @param id
     */
    public void urge(String id,String msg);

    /**
     * 查询需要到期提醒的经办记录
     * @param now 当前时间
     * @param endTime 过期检查时间
     * @return
     */
    public List<BizTaskHandle> list4ExpireNotice(String now,String endTime);

    /**
     * 查询用于年度/季度考核时一键引用的任务经办信息
     * @param userName
     * @param begin
     * @param end
     * @return
     */
    public List<TaskHandleVo.List4AssessResp> list4Assess(TaskHandleVo.List4AssessReq req);

}
