package com.cb.exam.domain;

import com.cb.common.core.domain.BaseEntity;
/**
 * @author: hu
 * @date:
 * @description:
 */
public class ExamQuestionEditVo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 题目的图片 */
    private String images;

    /** 主键id */
    private Long id;

    /** 题目 */
    private String name;

    /** 题目类型 */
    private String type;

    /** 问答题答案 */
    private String answer;

    /** 判断题答案 */
    private Integer judgeAnswer;

    /** 单选题或者多选题答案 */
    private String selectAnswer;

    /** 试题解析 */
    private String analysis;

    /** 是否启用 */
    private Integer isEnable;

    private String labels;

    /** 知识点 */
    private String knowledgePoint;

    /** 来源 */
    private String source;

    /** 考点 */
    private String examPoint;

    /** 关键字 */
    private String keywords;

    /** 难度 */
    private String difficulty;

    /** 题库id */
    private String banks;

    /** 题库名称 */
    private String questionBankName;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getJudgeAnswer() {
        return judgeAnswer;
    }

    public void setJudgeAnswer(Integer judgeAnswer) {
        this.judgeAnswer = judgeAnswer;
    }

    public String getSelectAnswer() {
        return selectAnswer;
    }

    public void setSelectAnswer(String selectAnswer) {
        this.selectAnswer = selectAnswer;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getBanks() {
        return banks;
    }

    public void setBanks(String banks) {
        this.banks = banks;
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

    public String getQuestionBankName() {
        return questionBankName;
    }

    public void setQuestionBankName(String questionBankName) {
        this.questionBankName = questionBankName;
    }

}
