package com.cb.exam.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 试卷对象 exam_paper
 *
 * @author hu
 * @date 2023-11-08
 */
public class ExamPaper extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 试卷名称
     */
    @Excel(name = "试卷名称")
    private String name;

    /**
     * 考试时长
     */
    @Excel(name = "考试时长", width = 30)
    private Integer duration;

    /**
     * 试卷总分
     */
    @Excel(name = "试卷总分")
    private Long totalPoint;

    /**
     * 题库ids
     */
    @Excel(name = "题库ids")
    private String questionBankIds;

    /**
     * 题目数量
     */
    @Excel(name = "题目数量")
    private Long questionCount;

    /**
     * 是否发布
     */
    @Excel(name = "是否发布")
    private Integer isIssue;

    /**
     * 标签ids
     */
    @Excel(name = "标签ids")
    private String labelIds;

    /**
     * 及格线
     */
    @Excel(name = "及格线")
    private Long passingScore;

    /**
     * 优秀线
     */
    @Excel(name = "优秀线")
    private Long goodScore;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createUser;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    /**
     * 修改人
     */
    @Excel(name = "修改人")
    private String modifyUser;

    /**
     * 问答题数量
     */
    @Excel(name = "单选题分值")
    private Long selectScore;

    /**
     * 问答题数量
     */
    @Excel(name = "多选题分值")
    private Long selectsScore;

    /**
     * 问答题数量
     */
    @Excel(name = "判断题分值")
    private Long judgeScore;

    /**
     * 问答题数量
     */
    @Excel(name = "问答题分值")
    private Long answerScore;

    private Long createUserId;

    public Long getSelectScore() {
        return selectScore;
    }

    public void setSelectScore(Long selectScore) {
        this.selectScore = selectScore;
    }

    public Long getSelectsScore() {
        return selectsScore;
    }

    public void setSelectsScore(Long selectsScore) {
        this.selectsScore = selectsScore;
    }

    public Long getJudgeScore() {
        return judgeScore;
    }

    public void setJudgeScore(Long judgeScore) {
        this.judgeScore = judgeScore;
    }

    public Long getAnswerScore() {
        return answerScore;
    }

    public void setAnswerScore(Long answerScore) {
        this.answerScore = answerScore;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setTotalPoint(Long totalPoint) {
        this.totalPoint = totalPoint;
    }

    public Long getTotalPoint() {
        return totalPoint;
    }

    public void setQuestionBankIds(String questionBankIds) {
        this.questionBankIds = questionBankIds;
    }

    public String getQuestionBankIds() {
        return questionBankIds;
    }

    public void setQuestionCount(Long questionCount) {
        this.questionCount = questionCount;
    }

    public Long getQuestionCount() {
        return questionCount;
    }

    public void setIsIssue(Integer isIssue) {
        this.isIssue = isIssue;
    }

    public Integer getIsIssue() {
        return isIssue;
    }

    public void setLabelIds(String labelIds) {
        this.labelIds = labelIds;
    }

    public String getLabelIds() {
        return labelIds;
    }

    public void setPassingScore(Long passingScore) {
        this.passingScore = passingScore;
    }

    public Long getPassingScore() {
        return passingScore;
    }

    public void setGoodScore(Long goodScore) {
        this.goodScore = goodScore;
    }

    public Long getGoodScore() {
        return goodScore;
    }

/*

    @Override
    public String getCreateUser() {
        return createUser;
    }

    @Override
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public Date getModifyTime() {
        return modifyTime;
    }

    @Override
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String getModifyUser() {
        return modifyUser;
    }

    @Override
    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }
*/

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("duration", getDuration())
                .append("totalPoint", getTotalPoint())
                .append("questionBankIds", getQuestionBankIds())
                .append("questionCount", getQuestionCount())
                .append("isIssue", getIsIssue())
                .append("labelIds", getLabelIds())
                .append("passingScore", getPassingScore())
                .append("goodScore", getGoodScore())
                .append("createTime", getCreateTime())
                .append("createUser", getCreateBy())
                .append("modifyTime", getUpdateTime())
                .append("modifyUser", getUpdateBy())
                .toString();
    }
}
