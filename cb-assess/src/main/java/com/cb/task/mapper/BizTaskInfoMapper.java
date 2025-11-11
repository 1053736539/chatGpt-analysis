package com.cb.task.mapper;

import com.cb.task.domain.BizTaskHandle;
import com.cb.task.domain.BizTaskInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 任务信息Mapper接口
 * 
 * @author yangxin
 * @date 2023-10-30
 */
public interface BizTaskInfoMapper 
{
    /**
     * 查询任务信息
     * 
     * @param id 任务信息ID
     * @return 任务信息
     */
    public BizTaskInfo selectBizTaskInfoById(String id);

    public List<BizTaskInfo> selectBizTaskInfoByIdIn(@Param("ids") List<String> ids);

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
     * @return
     */
    public List<BizTaskInfo> listByDept(BizTaskInfo bizTaskInfo);

    /**
     * 查询我待审核列表
     * @param bizTaskInfo
     * @param leaderDeptId
     * @return
     */
    public List<BizTaskInfo> listMyAudit(@Param("entity") BizTaskInfo bizTaskInfo, @Param("leaderDeptId") Long leaderDeptId);

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
     * 删除任务信息
     * 
     * @param id 任务信息ID
     * @return 结果
     */
    public int deleteBizTaskInfoById(String id);

    /**
     * 批量删除任务信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizTaskInfoByIds(String[] ids);

    /**
     *
     * @param userName
     * @param beginTime
     * @param endTime
     */
    public List<BizTaskHandle> listHandle4Evaluation(@Param("userName") String userName,
                                                           @Param("beginTime") String beginTime,
                                                           @Param("endTime") String endTime,
                                                     @Param("isDeptLeader") boolean isDeptLeader);
}
