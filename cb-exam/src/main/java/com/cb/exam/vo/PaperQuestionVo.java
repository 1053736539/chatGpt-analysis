package com.cb.exam.vo;

import lombok.Data;

import java.util.List;

/**
 * @version V1.0
 * @Package com.cb.exam.vo
 * @ClassName PaperQuestionVo
 * @Description TODO
 * @Author hu
 * @date 2023/11/08
 **/
@Data
public class PaperQuestionVo {

    /**
     * id
     */
    private Long id;

    /**
     * 试卷ID
     */
    private Long paperId;

    /**
     * 题目
     */
    private Long questionsId;

    /**
     * 题目内容
     */
    private String title;

    /**
     * 图片
     */
    private String images;

    /**
     * 题目类型
     */
    private String questionsType;

    /**
     * 分值
     */
    private Long score;

    /**
     * 解析
     */
    private String analysis;

    /**
     * 问答题答案
     */
    private String answer;

    /**
     * 判断题答案
     */
    private Integer judgeAnswer;

    /**
     * 选择题答案
     */
    private String selectAnswer;

    /**
     * 选择题的选项集合
     */
    private List<PaperQuestionOptionVo> optionList;

}
