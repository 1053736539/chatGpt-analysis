package com.cb.exam.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.exam.domain.ExamPaperQuestionsOption;
import com.cb.exam.domain.PaperOption;
import com.cb.exam.mapper.ExamPaperQuestionsOptionMapper;
import com.cb.exam.service.IExamPaperQuestionsOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 试卷的试题的选项Service业务层处理
 *
 * @author hu
 * @date 2023-11-08
 */
@Service
public class ExamPaperQuestionsOptionServiceImpl implements IExamPaperQuestionsOptionService {
    @Autowired
    private ExamPaperQuestionsOptionMapper examPaperQuestionsOptionMapper;

    /**
     * 查询试卷的试题的选项
     *
     * @param id 试卷的试题的选项主键
     * @return 试卷的试题的选项
     */
    @Override
    public ExamPaperQuestionsOption selectExamPaperQuestionsOptionByid(Long id) {
        return examPaperQuestionsOptionMapper.selectExamPaperQuestionsOptionByid(id);
    }

    /**
     * 查询试卷的试题的选项列表
     *
     * @param examPaperQuestionsOption 试卷的试题的选项
     * @return 试卷的试题的选项
     */
    @Override
    public List<ExamPaperQuestionsOption> selectExamPaperQuestionsOptionList(ExamPaperQuestionsOption examPaperQuestionsOption) {
        return examPaperQuestionsOptionMapper.selectExamPaperQuestionsOptionList(examPaperQuestionsOption);
    }

    @Override
    public List<PaperOption> selectPaperOptionByQuestionIdList(List<Long> questionIds) {
        return examPaperQuestionsOptionMapper.selectPaperOptionByQuestionIdList(questionIds);
    }

    /**
     * 新增试卷的试题的选项
     *
     * @param examPaperQuestionsOption 试卷的试题的选项
     * @return 结果
     */
    @Override
    public int insertExamPaperQuestionsOption(ExamPaperQuestionsOption examPaperQuestionsOption) {
        examPaperQuestionsOption.setCreateTime(DateUtils.getNowDate());
        return examPaperQuestionsOptionMapper.insertExamPaperQuestionsOption(examPaperQuestionsOption);
    }

    @Override
    public int insertBatchExamPaperQuestionsOption(List<ExamPaperQuestionsOption> examPaperQuestionsOptionList) {
        return examPaperQuestionsOptionMapper.insertBatchExamPaperQuestionsOption(examPaperQuestionsOptionList);
    }

    /**
     * 修改试卷的试题的选项
     *
     * @param examPaperQuestionsOption 试卷的试题的选项
     * @return 结果
     */
    @Override
    public int updateExamPaperQuestionsOption(ExamPaperQuestionsOption examPaperQuestionsOption) {
        return examPaperQuestionsOptionMapper.updateExamPaperQuestionsOption(examPaperQuestionsOption);
    }

    /**
     * 批量删除试卷的试题的选项
     *
     * @param ids 需要删除的试卷的试题的选项主键
     * @return 结果
     */
    @Override
    public int deleteExamPaperQuestionsOptionByids(Long[] ids) {
        return examPaperQuestionsOptionMapper.deleteExamPaperQuestionsOptionByids(ids);
    }

    /**
     * 删除试卷的试题的选项信息
     *
     * @param id 试卷的试题的选项主键
     * @return 结果
     */
    @Override
    public int deleteExamPaperQuestionsOptionByid(Long id) {
        return examPaperQuestionsOptionMapper.deleteExamPaperQuestionsOptionByid(id);
    }
}
