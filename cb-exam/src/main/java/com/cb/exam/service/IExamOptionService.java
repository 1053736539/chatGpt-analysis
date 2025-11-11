package com.cb.exam.service;

import com.cb.exam.domain.ExamOption;

import java.util.List;

/**
 * 试题选项Service接口
 *
 * @author hu
 * @date 2023-11-07
 */
public interface IExamOptionService {
    /**
     * 查询试题选项
     *
     * @param id 试题选项主键
     * @return 试题选项
     */
    public ExamOption selectExamOptionById(Long id);

    /**
     * 查询试题选项列表
     *
     * @param examOption 试题选项
     * @return 试题选项集合
     */
    public List<ExamOption> selectExamOptionList(ExamOption examOption);

    /**
     * 新增试题选项
     *
     * @param examOption 试题选项
     * @return 结果
     */
    public int insertExamOption(ExamOption examOption);

    /**
     * 批量新增试题选项
     *
     * @param examOption 试题选项
     * @return 结果
     */
    public int insertBatchExamOption(List<ExamOption> examOption);

    /**
     * 修改试题选项
     *
     * @param examOption 试题选项
     * @return 结果
     */
    public int updateExamOption(ExamOption examOption);

    /**
     * 批量删除试题选项
     *
     * @param ids 需要删除的试题选项主键集合
     * @return 结果
     */
    public int deleteExamOptionByIds(Long[] ids);

    /**
     * 根据题目id删除试题选项
     *
     * @param questionId 需要删除的题目的id
     * @return 结果
     */
    public int deleteExamOptionByQuestionId(Long questionId);

    /**
     * 删除试题选项信息
     *
     * @param id 试题选项主键
     * @return 结果
     */
    public int deleteExamOptionById(Long id);


    /**
     * 根据批量的题目去查询选项
     *
     * @param questionIds 题目集合
     * @return List<ExamOption> 结果
     */
    public List<ExamOption> selectExamOptionByquestionId(Long[] questionIds);


}
