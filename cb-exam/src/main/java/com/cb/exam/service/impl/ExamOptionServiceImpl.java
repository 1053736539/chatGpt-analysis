package com.cb.exam.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.exam.domain.ExamOption;
import com.cb.exam.mapper.ExamOptionMapper;
import com.cb.exam.service.IExamOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 试题选项Service业务层处理
 *
 * @author hu
 * @date 2023-11-07
 */
@Service
public class ExamOptionServiceImpl implements IExamOptionService {
    @Autowired
    private ExamOptionMapper examOptionMapper;

    /**
     * 查询试题选项
     *
     * @param id 试题选项主键
     * @return 试题选项
     */
    @Override
    public ExamOption selectExamOptionById(Long id) {
        return examOptionMapper.selectExamOptionById(id);
    }

    /**
     * 查询试题选项列表
     *
     * @param examOption 试题选项
     * @return 试题选项
     */
    @Override
    public List<ExamOption> selectExamOptionList(ExamOption examOption) {
        return examOptionMapper.selectExamOptionList(examOption);
    }

    /**
     * 新增试题选项
     *
     * @param examOption 试题选项
     * @return 结果
     */
    @Override
    public int insertExamOption(ExamOption examOption) {
        examOption.setCreateTime(DateUtils.getNowDate());
        return examOptionMapper.insertExamOption(examOption);
    }

    @Override
    public int insertBatchExamOption(List<ExamOption> examOption) {
        return examOptionMapper.insertBatchExamOption(examOption);
    }

    /**
     * 修改试题选项
     *
     * @param examOption 试题选项
     * @return 结果
     */
    @Override
    public int updateExamOption(ExamOption examOption) {
        return examOptionMapper.updateExamOption(examOption);
    }

    /**
     * 批量删除试题选项
     *
     * @param ids 需要删除的试题选项主键
     * @return 结果
     */
    @Override
    public int deleteExamOptionByIds(Long[] ids) {
        return examOptionMapper.deleteExamOptionByIds(ids);
    }


    /**
     * 批量删除试题选项
     *
     * @param questionId 需要删除的题目的id
     * @return 结果
     */
    @Override
    public int deleteExamOptionByQuestionId(Long questionId) {
        return examOptionMapper.deleteExamOptionByQuestionId(questionId);
    }

    /**
     * 删除试题选项信息
     *
     * @param id 试题选项主键
     * @return 结果
     */
    @Override
    public int deleteExamOptionById(Long id) {
        return examOptionMapper.deleteExamOptionById(id);
    }

    @Override
    public List<ExamOption> selectExamOptionByquestionId(Long[] questionIds) {
        return examOptionMapper.selectExamOptionByquestionId(questionIds);
    }
}
