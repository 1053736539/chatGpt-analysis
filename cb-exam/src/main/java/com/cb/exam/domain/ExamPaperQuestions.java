package com.cb.exam.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 试卷与试题关系对象 exam_paper_questions
 *
 * @author hu
 * @date 2023-11-07
 */
public class ExamPaperQuestions extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 试卷id
     */
    @Excel(name = "试卷id")
    private Long examinationPaperId;

    /**
     * 试题id
     */
    @Excel(name = "试题id")
    private Long examinationQuestionsId;

    /**
     * 题目类型
     */
    @Excel(name = "题目类型")
    private String examinationQuestionsType;

    /**
     * 创建用户
     */
    @Excel(name = "创建用户")
    private String createUser;

    /**
     * 修改用户
     */
    @Excel(name = "修改用户")
    private String modifyUser;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    /**
     * 题目分值
     */
    @Excel(name = "题目分值")
    private Long score;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setExaminationPaperId(Long examinationPaperId) {
        this.examinationPaperId = examinationPaperId;
    }

    public Long getExaminationPaperId() {
        return examinationPaperId;
    }

    public void setExaminationQuestionsId(Long examinationQuestionsId) {
        this.examinationQuestionsId = examinationQuestionsId;
    }

    public Long getExaminationQuestionsId() {
        return examinationQuestionsId;
    }

    public void setExaminationQuestionsType(String examinationQuestionsType) {
        this.examinationQuestionsType = examinationQuestionsType;
    }

    public String getExaminationQuestionsType() {
        return examinationQuestionsType;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getScore() {
        return score;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("examinationPaperId", getExaminationPaperId())
                .append("examinationQuestionsId", getExaminationQuestionsId())
                .append("examinationQuestionsType", getExaminationQuestionsType())
                .append("createUser", getCreateBy())
                .append("createTime", getCreateTime())
                .append("modifyUser", getUpdateBy())
                .append("modifyTime", getUpdateTime())
                .append("score", getScore())
                .toString();
    }
}
