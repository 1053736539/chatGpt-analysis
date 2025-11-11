package com.cb.exam.vo;

import com.cb.common.annotation.Excel;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author hu
 * @Package com.cb.exam.vo
 * @date 2023/11/08
 * @description
 */
@Data
public class ExamQuestionVo{

    private static final long serialVersionUID = 1L;

    /** 题目的图片 */
    private String images;

    /** 主键id */
    private Long id;


    @Excel(name = "题目")
    /** 题目 */
    private String name;

    @Excel(name = "题型ID")
    /** 题目类型 */
    private String type;

    @Excel(name = "答案")
    /** 问答题答案 */
    private String answer;

    /** 判断题答案 */
    private Integer judgeAnswer;

    /** 单选题或者多选题答案 */
    private String selectAnswer;

    /** 试题解析 */
    private String analysis;

    /** 创建用户 */
    private String createBy;

    /** 创建时间*/
    private Date createTime;

    /** 修改用户 */
    private String updateBy;

    /** 修改时间 */
    private Date updateTime;

    /** 是否启用 */
    private Integer isEnable;

    private String labels;

    private String banks;

    private List<ExamOptionVo> optionVoList;

    /** 知识点 */
    private String knowledgePoint;

    @Excel(name = "来源")
    /** 来源 */
    private String source;

    /** 考点 */
    private String examPoint;

    /** 关键字 */
    private String keywords;

    /** 难度 */
    private String difficulty;

    @Excel(name = "ID")
    private String strId;
}
