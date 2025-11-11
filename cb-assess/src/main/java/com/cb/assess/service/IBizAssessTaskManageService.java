package com.cb.assess.service;

import com.cb.assess.domain.BizAssessTaskAssessUserConfirm;
import com.cb.assess.domain.BizAssessTaskManage;

import java.util.List;

/**
 * 考核任务管理Service接口
 *
 * @author ouyang
 * @date 2023-11-04
 */
public interface IBizAssessTaskManageService {
    /**
     * 查询考核任务管理
     *
     * @param taskId 考核任务管理ID
     * @return 考核任务管理
     */
    public BizAssessTaskManage selectBizAssessTaskManageById(String taskId);

    /**
     * 查询考核任务管理列表
     *
     * @param bizAssessTaskManage 考核任务管理
     * @return 考核任务管理集合
     */
    public List<BizAssessTaskManage> selectBizAssessTaskManageList(BizAssessTaskManage bizAssessTaskManage);

    /**
     * 新增考核任务管理
     *
     * @param bizAssessTaskManage 考核任务管理
     * @return 结果
     */
    public int insertBizAssessTaskManage(BizAssessTaskManage bizAssessTaskManage);

    /**
     * 修改考核任务管理
     *
     * @param bizAssessTaskManage 考核任务管理
     * @return 结果
     */
    public int updateBizAssessTaskManage(BizAssessTaskManage bizAssessTaskManage);

    /**
     * 批量删除考核任务管理
     *
     * @param taskIds 需要删除的考核任务管理ID
     * @return 结果
     */
    public int deleteBizAssessTaskManageByIds(String[] taskIds);

    /**
     * 删除考核任务管理信息
     *
     * @param taskId 考核任务管理ID
     * @return 结果
     */
    public int deleteBizAssessTaskManageById(String taskId);

    public int distribute(BizAssessTaskManage manage);


    public List<BizAssessTaskAssessUserConfirm> confirmList(BizAssessTaskManage manage);
    /**
     * 发布
     * @param manage
     * @return
     */

    public int release(BizAssessTaskManage manage);

    /**
     *撤销发布
     * @param manage
     * @return
     */
    public int revoke(BizAssessTaskManage manage);


    public int logicDeleteBizAssessTaskManageById(String taskId);

    public int logicDeleteBizAssessTaskManageByIds(String[] taskIds);

//    @Deprecated
//    public Boolean checkTaskExist(BizAssessTaskManage manage);

    public void checkTaskIsSameYear(BizAssessTaskManage manage);

    /**
     * 检查考核季度是否存在
     * @param manage
     * @return
     */
    public void checkTaskQuarter(BizAssessTaskManage manage);

}
