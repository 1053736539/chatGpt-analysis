package com.cb.exam.service;

import com.cb.exam.domain.ExamLabel;
import com.cb.exam.vo.ExamBankQuestionCountVo;

import java.util.List;

/**
 * 标签Service接口
 *
 * @author hu
 * @date 2023-11-07
 */
public interface IExamLabelService {
    /**
     * 查询标签
     *
     * @param id 标签主键
     * @return 标签
     */
    public ExamLabel selectExamLabelById(Long id);


    public List<ExamBankQuestionCountVo> selectExamLabelQuestionCount(ExamBankQuestionCountVo examBankQuestionCountVo);


    public List<ExamLabel> selectExamLabelByQuestionIdList(Long questionid);


    /**
     * 查询标签列表
     *
     * @param examLabel 标签
     * @return 标签集合
     */
    public List<ExamLabel> selectExamLabelList(ExamLabel examLabel);

    /**
     * 新增标签
     *
     * @param examLabel 标签
     * @return 结果
     */
    public int insertExamLabel(ExamLabel examLabel);

    /**
     * 新增标签
     *
     * @param examOption 标签集合
     * @return 结果
     */
    public int insertBatchExamLabel(List<ExamLabel> examOption);

    /**
     * 修改标签
     *
     * @param examLabel 标签
     * @return 结果
     */
    public int updateExamLabel(ExamLabel examLabel);

    /**
     * 批量删除标签
     *
     * @param ids 需要删除的标签主键集合
     * @return 结果
     */
    public int deleteExamLabelByIds(Long[] ids);

    /**
     * 删除标签信息
     *
     * @param id 标签主键
     * @return 结果
     */
    public int deleteExamLabelById(Long id);
}
