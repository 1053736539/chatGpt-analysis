package com.cb.exam.vo;

import com.cb.common.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * @author hu
 * @Package com.cb.exam.vo
 * @date 2023/11/07 16:57
 * @description
 */
@Data
public class ExamOptionVo {

    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 题目表的Id */
    private Long optionId;

    @Excel(name = "选项标题")
    /** 选项内容 */
    private String optionContent;

    @Excel(name = "选项")
    /** 选项内容 */
    private String option;

    @Excel(name = "题目ID")
    /** 选项内容 */
    private String questionId;

    /** 创建用户 */
    private String createUser;

    /** 修改用户 */
    private String modifyUser;

    /** 修改时间 */
    private Date modifyTime;

}
