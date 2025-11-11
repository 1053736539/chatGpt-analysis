package com.cb.assess.service;

import com.cb.assess.domain.BizAssessTaskManage;
import com.cb.assess.domain.vo.ExaminerVo;
import com.cb.common.core.domain.entity.SysUser;

import java.util.List;

/**
 * 考核任务用户获取service
 */
public interface IBizAssessUserService {

    /**
     * 获取被考核人员的UserId集合
     * @param manage
     * @return
     */
    public List<SysUser> selectAssessedUserByTaskManage(BizAssessTaskManage manage);


    /**
     * 获取考官信息
     * @param manage
     * @return
     */
    public List<ExaminerVo> selectExaminerVoListByTaskManage(BizAssessTaskManage manage, SysUser user);



}
