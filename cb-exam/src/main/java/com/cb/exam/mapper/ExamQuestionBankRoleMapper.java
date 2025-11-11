package com.cb.exam.mapper;

import com.cb.exam.domain.ExamQuestionBankRole;

import java.util.List;

/**
 * 题库与角色关系Mapper接口
 *
 * @author hu
 * @date 2023-11-01
 */
public interface ExamQuestionBankRoleMapper {
    /**
     * 查询题库与角色关系
     *
     * @param id 题库与角色关系主键
     * @return 题库与角色关系
     */
    public ExamQuestionBankRole selectExamQuestionBankRoleById(Long id);

    /**
     * 查询题库与角色关系列表
     *
     * @param examQuestionBankRole 题库与角色关系
     * @return 题库与角色关系集合
     */
    public List<ExamQuestionBankRole> selectExamQuestionBankRoleList(ExamQuestionBankRole examQuestionBankRole);

    /**
     * 新增题库与角色关系
     *
     * @param examQuestionBankRole 题库与角色关系
     * @return 结果
     */
    public int insertExamQuestionBankRole(ExamQuestionBankRole examQuestionBankRole);

    /**
     * 修改题库与角色关系
     *
     * @param examQuestionBankRole 题库与角色关系
     * @return 结果
     */
    public int updateExamQuestionBankRole(ExamQuestionBankRole examQuestionBankRole);

    /**
     * 删除题库与角色关系
     *
     * @param id 题库与角色关系主键
     * @return 结果
     */
    public int deleteExamQuestionBankRoleById(Long id);

    /**
     * 批量删除题库与角色关系
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteExamQuestionBankRoleByIds(Long[] ids);
}
