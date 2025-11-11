package com.cb.exam.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.exam.domain.ExamTaskRole;
import com.cb.exam.mapper.ExamTaskRoleMapper;
import com.cb.exam.service.IExamTaskRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 考试任务与角色关系Service业务层处理
 *
 * @author hu
 * @date 2023-11-08
 */
@Service
public class ExamTaskRoleServiceImpl implements IExamTaskRoleService {
    @Autowired
    private ExamTaskRoleMapper examTaskRoleMapper;

    /**
     * 查询考试任务与角色关系
     *
     * @param id 考试任务与角色关系主键
     * @return 考试任务与角色关系
     */
    @Override
    public ExamTaskRole selectExamTaskRoleById(Long id) {
        return examTaskRoleMapper.selectExamTaskRoleById(id);
    }

    /**
     * 查询考试任务与角色关系列表
     *
     * @param examTaskRole 考试任务与角色关系
     * @return 考试任务与角色关系
     */
    @Override
    public List<ExamTaskRole> selectExamTaskRoleList(ExamTaskRole examTaskRole) {
        return examTaskRoleMapper.selectExamTaskRoleList(examTaskRole);
    }

    /**
     * 新增考试任务与角色关系
     *
     * @param examTaskRole 考试任务与角色关系
     * @return 结果
     */
    @Override
    public int insertExamTaskRole(ExamTaskRole examTaskRole) {
        examTaskRole.setCreateTime(DateUtils.getNowDate());
        return examTaskRoleMapper.insertExamTaskRole(examTaskRole);
    }

    /**
     * 修改考试任务与角色关系
     *
     * @param examTaskRole 考试任务与角色关系
     * @return 结果
     */
    @Override
    public int updateExamTaskRole(ExamTaskRole examTaskRole) {
        return examTaskRoleMapper.updateExamTaskRole(examTaskRole);
    }

    /**
     * 批量删除考试任务与角色关系
     *
     * @param ids 需要删除的考试任务与角色关系主键
     * @return 结果
     */
    @Override
    public int deleteExamTaskRoleByIds(Long[] ids) {
        return examTaskRoleMapper.deleteExamTaskRoleByIds(ids);
    }

    /**
     * 删除考试任务与角色关系信息
     *
     * @param id 考试任务与角色关系主键
     * @return 结果
     */
    @Override
    public int deleteExamTaskRoleById(Long id) {
        return examTaskRoleMapper.deleteExamTaskRoleById(id);
    }
}
