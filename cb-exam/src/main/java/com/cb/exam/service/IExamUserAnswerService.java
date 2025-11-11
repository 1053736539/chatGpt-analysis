package com.cb.exam.service;

import com.cb.exam.domain.ExamUserAnswer;
import com.cb.exam.domain.VExamUserAnswer;
import com.cb.exam.dto.ExamUserAnswerDto;
import com.cb.exam.vo.ExamUserAnswerVo;
import com.cb.exam.vo.UserAnswerVo;

import java.util.List;

/**
 * 用户答题Service接口
 *
 * @author hu
 * @date 2023-11-08
 */
public interface IExamUserAnswerService {
    /**
     * 查询用户答题
     *
     * @param id 用户答题主键
     * @return 用户答题
     */
    public ExamUserAnswer selectExamUserAnswerById(Long id);

    /**
     * 查询用户答题列表
     *
     * @param examUserAnswer 用户答题
     * @return 用户答题集合
     */
    //public List<ExamUserAnswerVo> selectExamUserAnswerList(ExamUerAnswer examUserAnswer);
    public List<VExamUserAnswer> selectExamUserAnswerList(VExamUserAnswer examUserAnswer);

    /**
     * 查询用户答题列表
     *
     * @param id 用户对试卷答题的 答题id
     * @return 用户答题集合
     */
    public UserAnswerVo selectExamUserAnswerByPaperIdList(Long id);

    /**
     * 新增用户答题
     *
     * @param examUserAnswer 用户答题
     * @return 结果
     */
    public int insertExamUserAnswer(ExamUserAnswer examUserAnswer);


    /**
     * 新增用户历史答题
     *
     * @param examUserAnswer 用户答题
     * @return 结果
     */
    public int insertExamUserAnswer(ExamUserAnswerDto.Add.Input examUserAnswer);

    /**
     * 修改用户答题
     *
     * @param examUserAnswer 用户答题
     * @return 结果
     */
    public int updateExamUserAnswer(ExamUserAnswer examUserAnswer);

    /**
     * 批量删除用户答题
     *
     * @param ids 需要删除的用户答题主键集合
     * @return 结果
     */
    public int deleteExamUserAnswerByIds(Long[] ids);

    /**
     * 删除用户答题信息
     *
     * @param id 用户答题主键
     * @return 结果
     */
    public int deleteExamUserAnswerById(Long id);
}
