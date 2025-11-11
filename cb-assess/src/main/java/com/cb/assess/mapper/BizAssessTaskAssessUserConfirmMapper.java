package com.cb.assess.mapper;

import com.cb.assess.domain.BizAssessTaskAssessUserConfirm;
import com.cb.assess.domain.BizAssessTaskManage;
import com.cb.assess.domain.VOrdinaryAssessTask;
import com.cb.assess.domain.vo.VTaskAssessConfirmUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 考核任务下发确认Mapper接口
 * 
 * @author ouyang
 * @date 2024-05-31
 */
public interface BizAssessTaskAssessUserConfirmMapper 
{

    /**
     * 查询需要确认考核用户的列表
     * @param bizAssessTaskManage
     * @param deptId
     * @return
     */
    public List<VOrdinaryAssessTask> selectTaskManageNeedConfirmList(@Param("et") VOrdinaryAssessTask task, @Param("deptId") Long deptId);

    /**
     * 查询考核任务下发确认
     * 
     * @param taskId 考核任务下发确认ID
     * @return 考核任务下发确认
     */
    public BizAssessTaskAssessUserConfirm selectTaskAssessUserConfirm(@Param("taskId") String taskId, @Param("deptId") Long deptId);

    public List<VTaskAssessConfirmUser> selectConfirmUser(@Param("cycleDesc") String cycleDesc,@Param("id") String id);


    public  List<BizAssessTaskAssessUserConfirm> selectTaskAssessUserConfirmByTaskId(String taskId);
    /**
     * 新增考核任务下发确认
     * 
     * @param bizAssessTaskAssessUserConfirm 考核任务下发确认
     * @return 结果
     */
    public int insertBizAssessTaskAssessUserConfirm(BizAssessTaskAssessUserConfirm bizAssessTaskAssessUserConfirm);

    /**
     * 修改考核任务下发确认
     * 
     * @param bizAssessTaskAssessUserConfirm 考核任务下发确认
     * @return 结果
     */
    public int updateBizAssessTaskAssessUserConfirm(BizAssessTaskAssessUserConfirm bizAssessTaskAssessUserConfirm);

    public int updateBizAssessTaskAssessUserConfirmById(BizAssessTaskAssessUserConfirm bizAssessTaskAssessUserConfirm);

    /**
     * 删除考核任务下发确认
     * 
     * @param taskId 考核任务下发确认ID
     * @return 结果
     */
    public int deleteBizAssessTaskAssessUserConfirmById(String taskId);

    /**
     * 通过ID 获取考核信息
     * @param id
     * @return
     */
    public BizAssessTaskAssessUserConfirm selectConfirmInfoById(String id);
}
