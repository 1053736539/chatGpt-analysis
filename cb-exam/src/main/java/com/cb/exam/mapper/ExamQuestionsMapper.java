package com.cb.exam.mapper;

import com.cb.exam.domain.ExamPaperDetail;
import com.cb.exam.domain.ExamQuestionEditVo;
import com.cb.exam.domain.ExamQuestions;
import com.cb.exam.domain.QuestionCount;
import com.cb.exam.vo.ExamQuestionShowVo;

import java.util.List;

/**
 * 试题Mapper接口
 *
 * @author hu
 * @date 2023-11-07
 */
public interface ExamQuestionsMapper {

    /***
     *
     *  查询试题  附带题库和标签
     * @param examPaperDetail
     * @return: java.util.List<com.cb.exam.vo.ExamQuestionShowVo>
     * @author: hu
     * @date: 2023/11/08
     * @throws:
     */
    public List<ExamQuestionShowVo> selectExamQuestionShowVo(ExamQuestions examPaperDetail);

    /**
     * 查询试题
     *
     * @param id 试题主键
     * @return 试题
     */
    //public ExamQuestions selectExamQuestionsById(Long id);
    public ExamQuestionEditVo selectExamQuestionsById(Long id);

    /**
     * 查询试题 根据题库和标签 查询不同的题目类型的题目数量
     *
     * @param paperDetail 生成试卷的详情数据
     * @return 不同的题目类型的试题数量
     */
    public List<QuestionCount> selectExamQuestionCountByType(ExamPaperDetail paperDetail);

    /**
     * 查询试题列表
     *
     * @param examQuestions 试题
     * @return 试题集合
     */
    public List<ExamQuestions> selectExamQuestionsList(ExamQuestions examQuestions);


    /**
     * 查询试题列表 根据题目类型，题库 标签查询
     *
     * @param examPaperDetail 试题
     * @return 试题集合
     */
    public List<ExamQuestions> selectExamQuestionByLabelsBanks(ExamPaperDetail examPaperDetail);

    /**
     * 新增试题
     *
     * @param examQuestions 试题
     * @return 结果
     */
    public int insertExamQuestions(ExamQuestions examQuestions);

    /**
     * 修改试题
     *
     * @param examQuestions 试题
     * @return 结果
     */
    public int updateExamQuestions(ExamQuestions examQuestions);

    /**
     * 删除试题
     *
     * @param id 试题主键
     * @return 结果
     */
    public int deleteExamQuestionsById(Long id);

    /**
     * 批量删除试题
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteExamQuestionsByIds(Long[] ids);
}
