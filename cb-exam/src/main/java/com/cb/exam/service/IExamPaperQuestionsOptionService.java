package com.cb.exam.service;

import com.cb.exam.domain.ExamPaperQuestionsOption;
import com.cb.exam.domain.PaperOption;

import java.util.List;

/**
 * 试卷的试题的选项Service接口
 *
 * @author hu
 * @date 2023-11-08
 */
public interface IExamPaperQuestionsOptionService {
    /**
     * 查询试卷的试题的选项
     *
     * @param id 试卷的试题的选项主键
     * @return 试卷的试题的选项
     */
    public ExamPaperQuestionsOption selectExamPaperQuestionsOptionByid(Long id);

    /**
     * 查询试卷的试题的选项列表
     *
     * @param examPaperQuestionsOption 试卷的试题的选项
     * @return 试卷的试题的选项集合
     */
    public List<ExamPaperQuestionsOption> selectExamPaperQuestionsOptionList(ExamPaperQuestionsOption examPaperQuestionsOption);

    /**
     * 查询试卷的试题的选项列表
     *
     * @param questionIds 试卷的试题的id集合
     * @return 试卷的试题的选项集合
     */
    public List<PaperOption> selectPaperOptionByQuestionIdList(List<Long> questionIds);

    /**
     * 新增试卷的试题的选项
     *
     * @param examPaperQuestionsOption 试卷的试题的选项
     * @return 结果
     */
    public int insertExamPaperQuestionsOption(ExamPaperQuestionsOption examPaperQuestionsOption);

    /**
     * 批量新增试卷的试题的选项
     *
     * @param examPaperQuestionsOptionList 试卷的试题的选项
     * @return 结果
     */
    public int insertBatchExamPaperQuestionsOption(List<ExamPaperQuestionsOption> examPaperQuestionsOptionList);

    /**
     * 修改试卷的试题的选项
     *
     * @param examPaperQuestionsOption 试卷的试题的选项
     * @return 结果
     */
    public int updateExamPaperQuestionsOption(ExamPaperQuestionsOption examPaperQuestionsOption);

    /**
     * 批量删除试卷的试题的选项
     *
     * @param ids 需要删除的试卷的试题的选项主键集合
     * @return 结果
     */
    public int deleteExamPaperQuestionsOptionByids(Long[] ids);

    /**
     * 删除试卷的试题的选项信息
     *
     * @param id 试卷的试题的选项主键
     * @return 结果
     */
    public int deleteExamPaperQuestionsOptionByid(Long id);
}
