package com.cb.activiti.mapper;

import com.cb.activiti.domain.BizLeave;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 请假Mapper接口
 *
 * @author 一只闲鹿
 * @date 2020-11-29
 */
public interface BizLeaveMapper
{
    /**
     * 查询请假
     *
     * @param id 请假ID
     * @return 请假
     */
    public BizLeave selectBizLeaveById(Long id);

    /**
     * 查询请假列表
     *
     * @param bizLeave 请假
     * @return 请假集合
     */
    public List<BizLeave> selectBizLeaveList(BizLeave bizLeave);

    /**
     * 新增请假
     *
     * @param bizLeave 请假
     * @return 结果
     */
    public int insertBizLeave(BizLeave bizLeave);

    /**
     * 修改请假
     *
     * @param bizLeave 请假
     * @return 结果
     */
    public int updateBizLeave(BizLeave bizLeave);

    /**
     * 删除请假
     *
     * @param id 请假ID
     * @return 结果
     */
    public int deleteBizLeaveById(Long id);

    /**
     * 批量删除请假
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizLeaveByIds(Long[] ids);


    /**
     * 手动更新activiti历史记录的办理人
     * @param taskId
     * @param assignee
     * @return
     */
    public int DynamicAssigneeRefreshActHis(@Param("taskId") String taskId, @Param("assignee") String assignee);

    /**
     * 查询病假列表
     * @param bizLeave
     * @return 病假集合
     */
    public  List<BizLeave> listByTypeOrHospital(BizLeave bizLeave);

     List<BizLeave> selectBizLeaveByIds(Long[] ids);

    /**
     * 查询某一时间段内审批通过的请假记录
     * @param bizLeave
     * @return
     */
    List<BizLeave> selectPassedBizLeaveList(BizLeave bizLeave);

    /**
     * 导出请假信息
     * @param bizLeave
     * @return
     */
    List<BizLeave> exportBizLeaveData(Map<String, Object> params);
}
