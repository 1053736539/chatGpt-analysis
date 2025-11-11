package com.cb.exam.mapper;

import com.cb.exam.domain.ExamLabel;
import com.cb.exam.vo.ExamBankQuestionCountVo;

import java.util.List;

/**
 * 标签Mapper接口
 *
 * @author hu
 * @date 2023-11-07
 */
public interface ExamLabelMapper {
    /**
     * 查询标签
     *
     * @param id 标签主键
     * @return 标签
     */
    public ExamLabel selectExamLabelById(Long id);


    public List<ExamLabel> selectLabelByQuestionIdList(Long questionId);


    public List<ExamBankQuestionCountVo> selectExamLabelQuestionCount(ExamBankQuestionCountVo examBankQuestionCountVo);


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
     * 删除标签
     *
     * @param id 标签主键
     * @return 结果
     */
    public int deleteExamLabelById(Long id);

    /**
     * 批量删除标签
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteExamLabelByIds(Long[] ids);
}
