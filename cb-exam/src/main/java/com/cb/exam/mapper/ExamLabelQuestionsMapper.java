package com.cb.exam.mapper;

import com.cb.exam.domain.ExamLabelQuestions;

import java.util.List;

/**
 * 试题与标签的关系Mapper接口
 *
 * @author hu
 * @date 2023-11-07
 */
public interface ExamLabelQuestionsMapper {
    /**
     * 查询试题与标签的关系
     *
     * @param id 试题与标签的关系主键
     * @return 试题与标签的关系
     */
    public ExamLabelQuestions selectExamLabelQuestionsById(Long id);

    /**
     * 查询试题与标签的关系列表
     *
     * @param examLabelQuestions 试题与标签的关系
     * @return 试题与标签的关系集合
     */
    public List<ExamLabelQuestions> selectExamLabelQuestionsList(ExamLabelQuestions examLabelQuestions);

    /**
     * 新增试题与标签的关系
     *
     * @param examLabelQuestions 试题与标签的关系
     * @return 结果
     */
    public int insertExamLabelQuestions(ExamLabelQuestions examLabelQuestions);

    /**
     * 新增试题与标签的关系
     *
     * @param examLabelQuestions 试题与标签的关系
     * @return 结果
     */
    public int insertBatchExamLabelQuestions(List<ExamLabelQuestions> examLabelQuestions);


    /**
     * 修改试题与标签的关系
     *
     * @param examLabelQuestions 试题与标签的关系
     * @return 结果
     */
    public int updateExamLabelQuestions(ExamLabelQuestions examLabelQuestions);

    /**
     * 删除试题与标签的关系
     *
     * @param id 试题与标签的关系主键
     * @return 结果
     */
    public int deleteExamLabelQuestionsById(Long id);

    /**
     * 根据题目的id删除试题与标签的关系
     *
     * @param questionId 试题与标签的关系主键
     * @return 结果
     */
    public int deleteExamLabelQuestionsByQuestionId(Long questionId);

    /**
     * 批量删除试题与标签的关系
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteExamLabelQuestionsByIds(Long[] ids);
}
