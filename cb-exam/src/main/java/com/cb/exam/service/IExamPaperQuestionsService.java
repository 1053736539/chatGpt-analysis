package com.cb.exam.service;

import com.cb.exam.domain.ExamPaperQuestions;
import com.cb.exam.vo.TaskPaperVo;

import java.util.List;

/**
 * 试卷与试题关系Service接口
 *
 * @author hu
 * @date 2023-11-08
 */
public interface IExamPaperQuestionsService {
    /**
     * 查询试卷与试题关系
     *
     * @param id 试卷与试题关系主键
     * @return 试卷与试题关系
     */
    public ExamPaperQuestions selectExamPaperQuestionsById(Long id);

    /**
     * 根据试卷查询生成题目
     *
     * @param id 试卷与试题关系
     * @return 试卷与试题关系集合
     */
    public TaskPaperVo selectPaperQuestionByPaperList(Long id);

    /**
     * 查询试卷与试题关系列表
     *
     * @param examPaperQuestions 试卷与试题关系
     * @return 试卷与试题关系集合
     */
    public List<ExamPaperQuestions> selectExamPaperQuestionsList(ExamPaperQuestions examPaperQuestions);

    /**
     * 新增试卷与试题关系
     *
     * @param examPaperQuestions 试卷与试题关系
     * @return 结果
     */
    public int insertExamPaperQuestions(ExamPaperQuestions examPaperQuestions);

    /**
     * 批量新增试卷与试题关系
     *
     * @param examPaperQuestionsList 通过试卷详情，批量添加试题与试卷关系
     * @return 结果
     */
    public int insertBatchExamPaperQuestions(List<ExamPaperQuestions> examPaperQuestionsList);

    /**
     * 修改试卷与试题关系
     *
     * @param examPaperQuestions 试卷与试题关系
     * @return 结果
     */
    public int updateExamPaperQuestions(ExamPaperQuestions examPaperQuestions);

    /**
     * 批量删除试卷与试题关系
     *
     * @param ids 需要删除的试卷与试题关系主键集合
     * @return 结果
     */
    public int deleteExamPaperQuestionsByIds(Long[] ids);

    /**
     * 删除试卷与试题关系信息
     *
     * @param id 试卷与试题关系主键
     * @return 结果
     */
    public int deleteExamPaperQuestionsById(Long id);


    public boolean deleteExamPaperQuestionsByPaperids(Long[] paperIds);
}
