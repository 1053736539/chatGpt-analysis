package com.cb.exam.service;

import com.cb.exam.domain.ExamUserAnswerDetails;

import java.util.List;

/**
 * 答题明细Service接口
 *
 * @author hu
 * @date 2023-11-08
 */
public interface IExamUserAnswerDetailsService {
    /**
     * 查询答题明细
     *
     * @param id 答题明细主键
     * @return 答题明细
     */
    public ExamUserAnswerDetails selectExamUserAnswerDetailsById(Long id);

    /**
     * 查询答题明细列表
     *
     * @param examUserAnswerDetails 答题明细
     * @return 答题明细集合
     */
    public List<ExamUserAnswerDetails> selectExamUserAnswerDetailsList(ExamUserAnswerDetails examUserAnswerDetails);

    /**
     * 新增答题明细
     *
     * @param examUserAnswerDetails 答题明细
     * @return 结果
     */
    public int insertExamUserAnswerDetails(ExamUserAnswerDetails examUserAnswerDetails);

    /**
     * 修改答题明细
     *
     * @param examUserAnswerDetails 答题明细
     * @return 结果
     */
    public int updateExamUserAnswerDetails(ExamUserAnswerDetails examUserAnswerDetails);

    /**
     * 批量删除答题明细
     *
     * @param ids 需要删除的答题明细主键集合
     * @return 结果
     */
    public int deleteExamUserAnswerDetailsByIds(Long[] ids);

    /**
     * 删除答题明细信息
     *
     * @param id 答题明细主键
     * @return 结果
     */
    public int deleteExamUserAnswerDetailsById(Long id);
}
