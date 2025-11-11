package com.cb.exam.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.exam.domain.ExamLabel;
import com.cb.exam.mapper.ExamLabelMapper;
import com.cb.exam.service.IExamLabelService;
import com.cb.exam.vo.ExamBankQuestionCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标签Service业务层处理
 *
 * @author hu
 * @date 2023-11-07
 */
@Service
public class ExamLabelServiceImpl implements IExamLabelService {
    @Autowired
    private ExamLabelMapper examLabelMapper;

    /**
     * 查询标签
     *
     * @param id 标签主键
     * @return 标签
     */
    @Override
    public ExamLabel selectExamLabelById(Long id) {
        return examLabelMapper.selectExamLabelById(id);
    }

    @Override
    public List<ExamBankQuestionCountVo> selectExamLabelQuestionCount(ExamBankQuestionCountVo examBankQuestionCountVo) {
        return examLabelMapper.selectExamLabelQuestionCount(examBankQuestionCountVo);
    }

    @Override
    public List<ExamLabel> selectExamLabelByQuestionIdList(Long questionid) {
        return examLabelMapper.selectLabelByQuestionIdList(questionid);
    }

    /**
     * 查询标签列表
     *
     * @param examLabel 标签
     * @return 标签
     */
    @Override
    public List<ExamLabel> selectExamLabelList(ExamLabel examLabel) {
        return examLabelMapper.selectExamLabelList(examLabel);
    }

    /**
     * 新增标签
     *
     * @param examLabel 标签
     * @return 结果
     */
    @Override
    public int insertExamLabel(ExamLabel examLabel) {
        examLabel.setCreateTime(DateUtils.getNowDate());
        return examLabelMapper.insertExamLabel(examLabel);
    }

    @Override
    public int insertBatchExamLabel(List<ExamLabel> examOption) {
        return examLabelMapper.insertBatchExamLabel(examOption);
    }

    /**
     * 修改标签
     *
     * @param examLabel 标签
     * @return 结果
     */
    @Override
    public int updateExamLabel(ExamLabel examLabel) {
        return examLabelMapper.updateExamLabel(examLabel);
    }

    /**
     * 批量删除标签
     *
     * @param ids 需要删除的标签主键
     * @return 结果
     */
    @Override
    public int deleteExamLabelByIds(Long[] ids) {
        return examLabelMapper.deleteExamLabelByIds(ids);
    }

    /**
     * 删除标签信息
     *
     * @param id 标签主键
     * @return 结果
     */
    @Override
    public int deleteExamLabelById(Long id) {
        return examLabelMapper.deleteExamLabelById(id);
    }
}
