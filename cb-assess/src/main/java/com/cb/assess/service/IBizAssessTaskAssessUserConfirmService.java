package com.cb.assess.service;


import com.cb.assess.domain.BizAssessTaskAssessUserConfirm;
import com.cb.assess.domain.BizAssessTaskManage;
import com.cb.assess.domain.VOrdinaryAssessTask;
import com.cb.common.core.domain.entity.SysUser;

import java.util.List;

/**
 * 考核任务下发确认Service接口
 *
 * @author ouyang
 * @date 2024-05-31
 */
public interface IBizAssessTaskAssessUserConfirmService {
    public  List<BizAssessTaskAssessUserConfirm> selectTaskAssessUserConfirmByTaskId(String taskId);

    public List<VOrdinaryAssessTask> selectTaskManageNeedConfirmList(VOrdinaryAssessTask vOrdinaryAssessTask);
    /**
     * 获取考核任务被考核人
     * @param manage
     * @return
     */
    public BizAssessTaskAssessUserConfirm selectAssessUser(BizAssessTaskManage manage);


    /**
     * 获取考核人人管理员确认部门被考核人
     * @param taskId
     * @param proId
     * @param deptId
     * @return
     */
    public BizAssessTaskAssessUserConfirm selectChargeAssessUser(String taskId,String proId,Long  deptId);

    /***
     * 驳回功能
     * @param confirm
     * @return
     */
    public int rejection(BizAssessTaskAssessUserConfirm confirm);
    /**
     * 新增考核任务下发确认
     *
     * @param
     * @return 结果
     */
    public int saveOrUpdate(BizAssessTaskAssessUserConfirm confirm);


    public int deleteTaskAssessUserConfirmByTaskId(String taskId);

    public BizAssessTaskAssessUserConfirm selectConfirmInfoById(String id);

    public int updateConfirmInfo(BizAssessTaskAssessUserConfirm confirm);
}
