package com.cb.exam.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 试题对象 exam_questions
 *
 * @author hu
 * @date 2023-11-07
 */
public class ExamQuestions extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 题目的图片
     */
    /*@Excel(name = "题目图片")*/
    private String images;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 题目
     */
    @Excel(name = "题目")
    private String name;

    /**
     * 题目类型
     */
    @Excel(name = "题目类型", dictType = "question_type")
    private String type;

    /**
     * 问答题答案
     */
    @Excel(name = "问答题答案")
    private String answer;

    /**
     * 判断题答案
     */
    @Excel(name = "判断题答案")
    private Integer judgeAnswer;

    /**
     * 单选题或者多选题答案
     */
    @Excel(name = "单选题或者多选题答案")
    private String selectAnswer;

    /**
     * 试题解析
     */
    @Excel(name = "试题解析")
    private String analysis;

    /**
     * 创建用户
     */
    /*@Excel(name = "创建用户")
    private String createUser;*/

    /**
     * 修改用户
     */
    /*@Excel(name = "修改用户")
    private String modifyUser;*/

    /**
     * 修改时间
     */
    /*@JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;*/

    /**
     * 是否启用
     */
    @Excel(name = "是否启用", readConverterExp="0=启用,1=未启用")
    private Integer isEnable;

    /**
     * 知识点
     */
    @Excel(name = "知识点")
    private String knowledgePoint;

    /**
     * 来源
     */
    @Excel(name = "来源")
    private String source;

    /**
     * 考点
     */
    @Excel(name = "考点")
    private String examPoint;

    /**
     * 关键字
     */
    @Excel(name = "关键字")
    private String keywords;

    /**
     * 难度
     */
    @Excel(name = "难度")
    private String difficulty;

    /**
     * 用户的ID
     */
    private Long userId;

    private List<Long> roleIds;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
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

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setJudgeAnswer(Integer judgeAnswer) {
        this.judgeAnswer = judgeAnswer;
    }

    public Integer getJudgeAnswer() {
        return judgeAnswer;
    }

    public void setSelectAnswer(String selectAnswer) {
        this.selectAnswer = selectAnswer;
    }

    public String getSelectAnswer() {
        return selectAnswer;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public String getKnowledgePoint() {
        return knowledgePoint;
    }

    public void setKnowledgePoint(String knowledgePoint) {
        this.knowledgePoint = knowledgePoint;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getExamPoint() {
        return examPoint;
    }

    public void setExamPoint(String examPoint) {
        this.examPoint = examPoint;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("type", getType())
                .append("answer", getAnswer())
                .append("judgeAnswer", getJudgeAnswer())
                .append("selectAnswer", getSelectAnswer())
                .append("analysis", getAnalysis())
                .append("createUser", getCreateBy())
                .append("createTime", getCreateTime())
                .append("modifyUser", getUpdateBy())
                .append("modifyTime", getUpdateTime())
                .append("isEnable", getIsEnable())
                .append("images", getImages())
                .toString();
    }
}
