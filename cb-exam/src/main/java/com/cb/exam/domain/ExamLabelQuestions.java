package com.cb.exam.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 试题与标签的关系对象 exam_label_questions
 *
 * @author hu
 * @date 2023-11-07
 */
public class ExamLabelQuestions extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 试题id
     */
    @Excel(name = "试题id")
    private Long examinationQuestionsId;

    /**
     * 标签id
     */
    @Excel(name = "标签id")
    private Long labelId;

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setExaminationQuestionsId(Long examinationQuestionsId) {
        this.examinationQuestionsId = examinationQuestionsId;
    }

    public Long getExaminationQuestionsId() {
        return examinationQuestionsId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public Long getLabelId() {
        return labelId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("examinationQuestionsId", getExaminationQuestionsId())
                .append("labelId", getLabelId())
                .append("createUser", getCreateBy())
                .append("createTime", getCreateTime())
                .append("modifyUser", getUpdateBy())
                .append("modifyTime", getUpdateTime())
                .toString();
    }
}
