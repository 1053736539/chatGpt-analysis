package com.cb.exam.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 试卷的试题的选项对象 exam_paper_questions_option
 *
 * @author hu
 * @date 2023-11-08
 */
public class ExamPaperQuestionsOption extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 试题选项id
     */
    @Excel(name = "试题选项id")
    private Long optionId;

    /**
     * 试题序号ABCD
     */
    @Excel(name = "试题序号ABCD")
    private String serialNumber;

    /**
     * 考试试题的id
     */
    @Excel(name = "考试试题的id")
    private Long examinationPaperQuestionsId;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setExaminationPaperQuestionsId(Long examinationPaperQuestionsId) {
        this.examinationPaperQuestionsId = examinationPaperQuestionsId;
    }

    public Long getExaminationPaperQuestionsId() {
        return examinationPaperQuestionsId;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("主键id", getId())
                .append("optionId", getOptionId())
                .append("serialNumber", getSerialNumber())
                .append("examinationPaperQuestionsId", getExaminationPaperQuestionsId())
                .append("createUser", getCreateBy())
                .append("createTime", getCreateTime())
                .append("modifyUser", getUpdateBy())
                .append("modifyTime", getUpdateTime())
                .toString();
    }
}
