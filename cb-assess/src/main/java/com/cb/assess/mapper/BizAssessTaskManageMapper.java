package com.cb.assess.mapper;

import com.cb.assess.domain.BizAssessTaskManage;

import java.util.List;

/**
 * 考核任务管理Mapper接口
 *
 * @author ouyang
 * @date 2023-11-04
 */
public interface BizAssessTaskManageMapper {
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
     * 删除考核任务管理
     *
     * @param taskId 考核任务管理ID
     * @return 结果
     */
    public int deleteBizAssessTaskManageById(String taskId);

    /**
     * 批量删除考核任务管理
     *
     * @param taskIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAssessTaskManageByIds(String[] taskIds);

    public int changStatus(BizAssessTaskManage manage);

    public int logicDeleteBizAssessTaskManageById(String taskId);

    public int logicDeleteBizAssessTaskManageByIds(String[] taskIds);

//    public boolean checkTaskExist(BizAssessTaskManage manage);

    public boolean checkTaskReleased(String taskId);

    public int checkTaskQuarter(BizAssessTaskManage manage);
}
