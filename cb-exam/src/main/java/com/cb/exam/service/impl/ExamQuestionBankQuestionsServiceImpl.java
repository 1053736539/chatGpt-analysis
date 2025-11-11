package com.cb.exam.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.exam.domain.ExamQuestionBankQuestions;
import com.cb.exam.mapper.ExamQuestionBankQuestionsMapper;
import com.cb.exam.service.IExamQuestionBankQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 题库与试题关系Service业务层处理
 *
 * @author hu
 * @date 2023-11-07
 */
@Service
public class ExamQuestionBankQuestionsServiceImpl implements IExamQuestionBankQuestionsService {
    @Autowired
    private ExamQuestionBankQuestionsMapper examQuestionBankQuestionsMapper;

    /**
     * 查询题库与试题关系
     *
     * @param id 题库与试题关系主键
     * @return 题库与试题关系
     */
    @Override
    public ExamQuestionBankQuestions selectExamQuestionBankQuestionsById(Long id) {
        return examQuestionBankQuestionsMapper.selectExamQuestionBankQuestionsById(id);
    }

    /**
     * 查询题库与试题关系列表
     *
     * @param examQuestionBankQuestions 题库与试题关系
     * @return 题库与试题关系
     */
    @Override
    public List<ExamQuestionBankQuestions> selectExamQuestionBankQuestionsList(ExamQuestionBankQuestions examQuestionBankQuestions) {
        return examQuestionBankQuestionsMapper.selectExamQuestionBankQuestionsList(examQuestionBankQuestions);
    }

    /**
     * 新增题库与试题关系
     *
     * @param examQuestionBankQuestions 题库与试题关系
     * @return 结果
     */
    @Override
    public int insertExamQuestionBankQuestions(ExamQuestionBankQuestions examQuestionBankQuestions) {
        examQuestionBankQuestions.setCreateTime(DateUtils.getNowDate());
        return examQuestionBankQuestionsMapper.insertExamQuestionBankQuestions(examQuestionBankQuestions);
    }

    /**
     * @author
     * @Description 批量添加
     * @Date 2021/12/6 14:58
     * @Param * @param null
     * @Return
     * @name
     */
    @Override
    public int insertBatchExamQuestionBankQuestions(List<ExamQuestionBankQuestions> examQuestionBankQuestions) {
        return examQuestionBankQuestionsMapper.insertBatchExamQuestionBankQuestions(examQuestionBankQuestions);
    }

    /**
     * 修改题库与试题关系
     *
     * @param examQuestionBankQuestions 题库与试题关系
     * @return 结果
     */
    @Override
    public int updateExamQuestionBankQuestions(ExamQuestionBankQuestions examQuestionBankQuestions) {
        return examQuestionBankQuestionsMapper.updateExamQuestionBankQuestions(examQuestionBankQuestions);
    }

    /**
     * 批量删除题库与试题关系
     *
     * @param ids 需要删除的题库与试题关系主键
     * @return 结果
     */
    @Override
    public int deleteExamQuestionBankQuestionsByIds(Long[] ids) {
        return examQuestionBankQuestionsMapper.deleteExamQuestionBankQuestionsByIds(ids);
    }

    /**
     * 删除题库与试题关系信息
     *
     * @param id 题库与试题关系主键
     * @return 结果
     */
    @Override
    public int deleteExamQuestionBankQuestionsById(Long id) {
        return examQuestionBankQuestionsMapper.deleteExamQuestionBankQuestionsById(id);
    }


    /**
     * 删除题库与试题关系信息
     *
     * @param questionId 题库与试题关系主键
     * @return 结果
     */
    @Override
    public int deleteExamQuestionBankQuestionsByQuestionId(Long questionId) {
        return examQuestionBankQuestionsMapper.deleteExamQuestionBankQuestionsByQuestionId(questionId);
    }
}
