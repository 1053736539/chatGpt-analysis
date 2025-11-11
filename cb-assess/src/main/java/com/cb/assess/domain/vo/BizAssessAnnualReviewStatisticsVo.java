package com.cb.assess.domain.vo;

import java.util.ArrayList;
import java.util.List;

public class BizAssessAnnualReviewStatisticsVo {
    private Long excellentA;
    private Long numA;
    private Double scoreA;
    private Double scoreB;
    private Double totalScore;
    private Long excellentB;
    private Long numB;
    private String name;
    private String workPost;
    private Long userId;
    private String type;
    private String assessmentYear;
    private List<RegularAssessmentVo> regularAssessmentVos=new ArrayList<>();

    public List<RegularAssessmentVo> getRegularAssessmentVos() {
        return regularAssessmentVos;
    }

    public void setRegularAssessmentVos(List<RegularAssessmentVo> regularAssessmentVos) {
        this.regularAssessmentVos = regularAssessmentVos;
    }

    public String getAssessmentYear() {
        return assessmentYear;
    }

    public void setAssessmentYear(String assessmentYear) {
        this.assessmentYear = assessmentYear;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getScoreA() {
        return scoreA;
    }

    public void setScoreA(Double scoreA) {
        this.scoreA = scoreA;
    }

    public Double getScoreB() {
        return scoreB;
    }

    public void setScoreB(Double scoreB) {
        this.scoreB = scoreB;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getWorkPost() {
        return workPost;
    }

    public void setWorkPost(String workPost) {
        this.workPost = workPost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getExcellentA() {
        return excellentA;
    }

    public void setExcellentA(Long excellentA) {
        this.excellentA = excellentA;
    }

    public Long getNumA() {
        return numA;
    }

    public void setNumA(Long numA) {
        this.numA = numA;
    }

    public Long getExcellentB() {
        return excellentB;
    }

    public void setExcellentB(Long excellentB) {
        this.excellentB = excellentB;
    }

    public Long getNumB() {
        return numB;
    }

    public void setNumB(Long numB) {
        this.numB = numB;
    }
}
