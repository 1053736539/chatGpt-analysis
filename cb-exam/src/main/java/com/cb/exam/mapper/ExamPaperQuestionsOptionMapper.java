package com.cb.exam.mapper;

import com.cb.exam.domain.ExamPaperQuestionsOption;
import com.cb.exam.domain.PaperOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 试卷的试题的选项Mapper接口
 *
 * @author hu
 * @date 2023-11-08
 */
public interface ExamPaperQuestionsOptionMapper {
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
    public List<PaperOption> selectPaperOptionByQuestionIdList(@Param("questionIds") List<Long> questionIds);

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
     * 删除试卷的试题的选项
     *
     * @param id 试卷的试题的选项主键
     * @return 结果
     */
    public int deleteExamPaperQuestionsOptionByid(Long id);

    /**
     * 批量删除试卷的试题的选项
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteExamPaperQuestionsOptionByids(Long[] ids);

    public int deleteExamPaperQuestionsOptionByPaperIds(Long[] paperQuestionIds);

}
