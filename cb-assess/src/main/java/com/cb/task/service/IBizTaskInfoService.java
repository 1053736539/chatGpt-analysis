package com.cb.task.service;

import com.cb.task.domain.BizTaskInfo;
import com.cb.task.vo.TaskInfoVo;

import java.util.List;

/**
 * 任务信息Service接口
 * 
 * @author yangxin
 * @date 2023-10-30
 */
public interface IBizTaskInfoService 
{
    /**
     * 查询任务信息
     * 
     * @param id 任务信息ID
     * @return 任务信息
     */
    public BizTaskInfo selectBizTaskInfoById(String id);

    /**
     * 查询任务信息列表
     * 
     * @param bizTaskInfo 任务信息
     * @return 任务信息集合
     */
    public List<BizTaskInfo> selectBizTaskInfoList(BizTaskInfo bizTaskInfo);

    /**
     * 查询部门的任务（待办/超时/已完结）
     * @param bizTaskInfo
     * @param expireQuery 是否查询已超时任务
     * @return
     */
    public List<BizTaskInfo> listByDept(BizTaskInfo bizTaskInfo, boolean expireQuery);

    /**
     * 查询待审核的
     * @param bizTaskInfo
     * @return
     */
    public List<BizTaskInfo> listMyAudit(BizTaskInfo bizTaskInfo,Long leaderDeptId);

    /**
     * 新增任务信息
     * 
     * @param bizTaskInfo 任务信息
     * @return 结果
     */
    public int insertBizTaskInfo(BizTaskInfo bizTaskInfo);

    /**
     * 修改任务信息
     * 
     * @param bizTaskInfo 任务信息
     * @return 结果
     */
    public int updateBizTaskInfo(BizTaskInfo bizTaskInfo);

    /**
     * 批量删除任务信息
     * 
     * @param ids 需要删除的任务信息ID
     * @return 结果
     */
    public int deleteBizTaskInfoByIds(String[] ids);

    /**
     * 删除任务信息信息
     * 
     * @param id 任务信息ID
     * @return 结果
     */
    public int deleteBizTaskInfoById(String id);

    /**
     * OA交办任务
     * @param req
     */
    public void fromOA(TaskInfoVo.FormOAReq req);

    /**
     * 创建任务
     * @param req
     */
    public void create(TaskInfoVo.CreateReq req);

    /**
     * 审核
     * @param req
     */
    public void audit(TaskInfoVo.AuditReq req);

    /**
     * 任务详情提交处理日志
     * @param req
     */
    public void submitComment(TaskInfoVo.SubmitCommentReq req);

    /**
     * 办结
     * @param taskId
     */
    public void complete(String taskId);

    /**
     * 获取用户的任务处理情况
     * @param req
     * @return
     */
    public TaskInfoVo.UserHandle4EvaluationResp getUserHandle4Evaluation(TaskInfoVo.UserHandle4EvaluationReq req);

}
