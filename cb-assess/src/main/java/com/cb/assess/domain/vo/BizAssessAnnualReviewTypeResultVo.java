package com.cb.assess.domain.vo;

import com.cb.assess.domain.BizAssessAnnualReviewTypeResult;

import java.util.ArrayList;
import java.util.List;

public class BizAssessAnnualReviewTypeResultVo extends BizAssessAnnualReviewTypeResult {
    private String name="";
    private String deptName="";
    /**
     * 导出表里有序号
     */
    private String index="";
    /**
     * 平时考核季度，四个季度
     */
    private String quarter1="";
    private String quarter2="";
    private String quarter3="";
    private String quarter4="";
    /**
     * 四个评价的等次,列表里面有，现在只是导出优秀的，如果后面他要求其他等次的也干，那就都拿，依次填入
     */
    private String recommendGrade1="";
    private String recommendGrade2="";
    private String recommendGrade3="";
    private String recommendGrade4="";
    /**
     * 导出类型，1导出A类表，2导出B类表
     */
    private String exportType="1";
    private List<RegularAssessmentVo> regularAssessmentVos=new ArrayList<>();
    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getExportType() {
        return exportType;
    }

    public void setExportType(String exportType) {
        this.exportType = exportType;
    }

    public String getRecommendGrade1() {
        return recommendGrade1;
    }

    public void setRecommendGrade1(String recommendGrade1) {
        this.recommendGrade1 = recommendGrade1;
    }

    public String getRecommendGrade2() {
        return recommendGrade2;
    }

    public void setRecommendGrade2(String recommendGrade2) {
        this.recommendGrade2 = recommendGrade2;
    }

    public String getRecommendGrade3() {
        return recommendGrade3;
    }

    public void setRecommendGrade3(String recommendGrade3) {
        this.recommendGrade3 = recommendGrade3;
    }

    public String getRecommendGrade4() {
        return recommendGrade4;
    }

    public void setRecommendGrade4(String recommendGrade4) {
        this.recommendGrade4 = recommendGrade4;
    }

    public String getQuarter1() {
        return quarter1;
    }

    public void setQuarter1(String quarter1) {
        this.quarter1 = quarter1;
    }

    public String getQuarter2() {
        return quarter2;
    }

    public void setQuarter2(String quarter2) {
        this.quarter2 = quarter2;
    }

    public String getQuarter3() {
        return quarter3;
    }

    public void setQuarter3(String quarter3) {
        this.quarter3 = quarter3;
    }

    public String getQuarter4() {
        return quarter4;
    }

    public void setQuarter4(String quarter4) {
        this.quarter4 = quarter4;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }



    public List<RegularAssessmentVo> getRegularAssessmentVos() {
        return regularAssessmentVos;
    }

    public void setRegularAssessmentVos(List<RegularAssessmentVo> regularAssessmentVos) {
        this.regularAssessmentVos = regularAssessmentVos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
