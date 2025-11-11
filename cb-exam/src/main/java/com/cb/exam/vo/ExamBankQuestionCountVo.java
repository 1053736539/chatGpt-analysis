package com.cb.exam.vo;

import com.cb.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * @version V1.0
 * @Package com.cb.exam.vo
 * @ClassName ExamBankQuestionCountVo
 * @Description 题目表的接口
 * @Author hu
 * @date 2023/11/01 11:53
 **/
@Data
public class ExamBankQuestionCountVo extends BaseEntity {

    /**
     * 题库的ID 或者标签的ID
     */
    private Integer id;

    /**
     * 题库的名称 标签的名称
     */
    private String name;

    /**
     * 题库描述
     */
    private String description;

    /**
     * 单选题数量
     */
    private Integer selectCount;

    /**
     * 多选题数量
     */
    private Integer selectsCount;

    /**
     * 判断题数量
     */
    private Integer verdictCount;

    /**
     * 问答题数量
     */
    private Integer answerCount;

    private Long userId;

    private List<Long> roleIds;
}
