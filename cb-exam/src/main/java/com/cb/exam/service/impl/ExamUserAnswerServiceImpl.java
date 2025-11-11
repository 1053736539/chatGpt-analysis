package com.cb.exam.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.cb.common.exception.base.BaseException;
import com.cb.common.utils.DateUtils;
import com.cb.exam.domain.ExamUserAnswer;
import com.cb.exam.domain.ExamUserAnswerDetails;
import com.cb.exam.domain.VExamUserAnswer;
import com.cb.exam.dto.ExamUserAnswerDto;
import com.cb.exam.mapper.ExamUserAnswerDetailsMapper;
import com.cb.exam.mapper.ExamUserAnswerMapper;
import com.cb.exam.service.IExamPaperQuestionsService;
import com.cb.exam.service.IExamUserAnswerService;
import com.cb.exam.vo.ExamUserAnswerVo;
import com.cb.exam.vo.TaskPaperVo;
import com.cb.exam.vo.UserAnswerDetailVo;
import com.cb.exam.vo.UserAnswerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户答题Service业务层处理
 *
 * @author hu
 * @date 2023-11-08
 */
@Service
public class ExamUserAnswerServiceImpl implements IExamUserAnswerService {
    @Autowired
    private ExamUserAnswerMapper examUserAnswerMapper;

    @Autowired
    private ExamUserAnswerDetailsMapper examUserAnswerDetailsMapper;

    @Autowired
    private IExamPaperQuestionsService paperQuestionsService;

    /**
     * 查询用户答题
     *
     * @param id 用户答题主键
     * @return 用户答题
     */
    @Override
    public ExamUserAnswer selectExamUserAnswerById(Long id) {
        return examUserAnswerMapper.selectExamUserAnswerById(id);
    }

    /**
     * 查询用户答题列表
     *
     * @param examUserAnswer 用户答题
     * @return 用户答题
     */
    @Override
    /*public List<ExamUserAnswerVo> selectExamUserAnswerList(ExamUserAnswer examUserAnswer) {
        return examUserAnswerMapper.selectExamUserAnswerListVo(examUserAnswer);
    }*/
    public List<VExamUserAnswer> selectExamUserAnswerList(VExamUserAnswer examUserAnswer) {
        return examUserAnswerMapper.selectExamUserAnswerListVo(examUserAnswer);
    }

    @Override
    public UserAnswerVo selectExamUserAnswerByPaperIdList(Long id) {
        //先根据试卷id和用户id查找到历史的错题
        ExamUserAnswer userAnswer = this.examUserAnswerMapper.selectExamUserAnswerById(id);
        //查找到试卷
        TaskPaperVo taskPaperVo = this.paperQuestionsService.selectPaperQuestionByPaperList(userAnswer.getExaminationPaperId());
        //查找到用户此次答题的详情
        ExamUserAnswerDetails userAnswerDetails = new ExamUserAnswerDetails();
        userAnswerDetails.setUserAnswerId(id);
        List<ExamUserAnswerDetails> userAnswerDetailsList = this.examUserAnswerDetailsMapper
                .selectExamUserAnswerDetailsList(userAnswerDetails);
        UserAnswerVo userAnswerVo = new UserAnswerVo();
        BeanUtil.copyProperties(taskPaperVo, userAnswerVo, "");
        List<UserAnswerDetailVo> list = userAnswerVo.getQuestionList().stream().map(item -> {
            ExamUserAnswerDetails answerDetails = userAnswerDetailsList.stream().filter(obj -> obj.getExaminationPaperQuestionsId().equals(item.getId())).findFirst().orElse(new ExamUserAnswerDetails());
            item.setUserAnswer(answerDetails.getUserAnswer());
            item.setSelectAnswer(answerDetails.getQuestionsAnswer());
            item.setIsCorrect(answerDetails.getIsCorrect());
            return item;
        }).collect(Collectors.toList());
        userAnswerVo.setQuestionList(list);
        userAnswerVo.setTotalPoints(userAnswer.getTotalPoints());
        return userAnswerVo;
    }

    /**
     * 新增用户答题
     *
     * @param examUserAnswer 用户答题
     * @return 结果
     */
    @Override
    public int insertExamUserAnswer(ExamUserAnswer examUserAnswer) {
        examUserAnswer.setCreateTime(DateUtils.getNowDate());
        return examUserAnswerMapper.insertExamUserAnswer(examUserAnswer);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertExamUserAnswer(ExamUserAnswerDto.Add.Input examUserAnswer) {
        ExamUserAnswer userAnswer = new ExamUserAnswer();
        BeanUtil.copyProperties(examUserAnswer, userAnswer, "");
        userAnswer.setTotalPoints(examUserAnswer.getTotalPoints().longValue());
        Date start = DateUtils.getNowDate();
        userAnswer.setCreateTime(start);
        int count = examUserAnswerMapper.insertExamUserAnswer(userAnswer);
        if (count != 1) {
            throw new BaseException("插入用户的答题试卷失败");
        }
        List<ExamUserAnswerDetails> userAnswerDetailsList =
                examUserAnswer.getOptionList().stream().map(item -> {
                    ExamUserAnswerDetails userAnswerDetails = new ExamUserAnswerDetails();
                    userAnswerDetails.setUserAnswerId(userAnswer.getId());
                    userAnswerDetails.setCreateBy(examUserAnswer.getCreateUser());
                    BeanUtil.copyProperties(item, userAnswerDetails, "");
                    userAnswerDetails.setCreateTime(start);
                    return userAnswerDetails;
                }).collect(Collectors.toList());
        count = this.examUserAnswerDetailsMapper.insertBatchExamUserAnswerDetails(userAnswerDetailsList);
        if (count != userAnswerDetailsList.size()) {
            throw new BaseException("插入用户的答题详情失败");
        }
        return userAnswer.getId().intValue();
    }

    /**
     * 修改用户答题
     *
     * @param examUserAnswer 用户答题
     * @return 结果
     */
    @Override
    public int updateExamUserAnswer(ExamUserAnswer examUserAnswer) {
        return examUserAnswerMapper.updateExamUserAnswer(examUserAnswer);
    }

    /**
     * 批量删除用户答题
     *
     * @param ids 需要删除的用户答题主键
     * @return 结果
     */
    @Override
    public int deleteExamUserAnswerByIds(Long[] ids) {
        return examUserAnswerMapper.deleteExamUserAnswerByIds(ids);
    }

    /**
     * 删除用户答题信息
     *
     * @param id 用户答题主键
     * @return 结果
     */
    @Override
    public int deleteExamUserAnswerById(Long id) {
        return examUserAnswerMapper.deleteExamUserAnswerById(id);
    }
}
