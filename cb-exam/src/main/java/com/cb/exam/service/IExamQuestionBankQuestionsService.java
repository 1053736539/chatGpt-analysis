package com.cb.exam.service;

import com.cb.exam.domain.ExamQuestionBankQuestions;

import java.util.List;

/**
 * 题库与试题关系Service接口
 *
 * @author hu
 * @date 2023-11-07
 */
public interface IExamQuestionBankQuestionsService {
    /**
     * 查询题库与试题关系
     *
     * @param id 题库与试题关系主键
     * @return 题库与试题关系
     */
    public ExamQuestionBankQuestions selectExamQuestionBankQuestionsById(Long id);

    /**
     * 查询题库与试题关系列表
     *
     * @param examQuestionBankQuestions 题库与试题关系
     * @return 题库与试题关系集合
     */
    public List<ExamQuestionBankQuestions> selectExamQuestionBankQuestionsList(ExamQuestionBankQuestions examQuestionBankQuestions);

    /**
     * 新增题库与试题关系
     *
     * @param examQuestionBankQuestions 题库与试题关系
     * @return 结果
     */
    public int insertExamQuestionBankQuestions(ExamQuestionBankQuestions examQuestionBankQuestions);

    /**
     * 新增题库与试题关系
     *
     * @param examQuestionBankQuestions 题库与试题关系
     * @return 结果
     */
    public int insertBatchExamQuestionBankQuestions(List<ExamQuestionBankQuestions> examQuestionBankQuestions);


    /**
     * 修改题库与试题关系
     *
     * @param examQuestionBankQuestions 题库与试题关系
     * @return 结果
     */
    public int updateExamQuestionBankQuestions(ExamQuestionBankQuestions examQuestionBankQuestions);

    /**
     * 批量删除题库与试题关系
     *
     * @param ids 需要删除的题库与试题关系主键集合
     * @return 结果
     */
    public int deleteExamQuestionBankQuestionsByIds(Long[] ids);

    /**
     * 删除题库与试题关系信息
     *
     * @param id 题库与试题关系主键
     * @return 结果
     */
    public int deleteExamQuestionBankQuestionsById(Long id);

    /**
     * 根据题目的id删除题库与试题关系信息
     *
     * @param questionId 题库与试题关系主键
     * @return 结果
     */
    public int deleteExamQuestionBankQuestionsByQuestionId(Long questionId);

}
