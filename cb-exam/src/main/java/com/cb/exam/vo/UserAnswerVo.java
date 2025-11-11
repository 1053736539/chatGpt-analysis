package com.cb.exam.vo;

import lombok.Data;

import java.util.List;

/**
 * @author hu
 * @date 2023/11/08
 */
@Data
public class UserAnswerVo {

    /**
     * 试卷名称
     */
    private String paperName;


    /**
     * 用户得分
     */
    private Long totalPoints;

    /**
     * 单选题分值
     */
    private Long selectScore;

    /**
     * 多选题分值
     */
    private Long selectsScore;

    /**
     * 判断题分值
     */
    private Long judgeScore;

    /**
     * 问答题分值
     */
    private Long answerScore;

    /**
     * 及格线
     */
    private Long passingScore;

    /**
     * 优秀线
     */
    private Long goodScore;

    /**
     * 题目数量
     */
    private Long questionCount;

    /**
     * 试卷总分
     */
    private Long totalPoint;

    /**
     * 考试时长
     */
    private Integer duration;

    /**
     * 试题集合
     */
    private List<UserAnswerDetailVo> questionList;

}
