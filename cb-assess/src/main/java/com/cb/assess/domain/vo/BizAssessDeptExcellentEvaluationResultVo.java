package com.cb.assess.domain.vo;

import com.cb.assess.domain.BizAssessDeptExcellentEvaluationResult;

import java.util.ArrayList;
import java.util.List;

public class BizAssessDeptExcellentEvaluationResultVo extends BizAssessDeptExcellentEvaluationResult {
    //姓名
    private String name;
    //单位处室
    private String deptName;


    //前端查询参数，1是优秀，2是不优秀，不传就是全部
    private String isExcellent;
    /**
     * 用来接收前端的参数，然后再
     */
    private String quarter4;
    private String quarter3;
    private String quarter2;
    private String quarter1;
    //导出excel时有个序号
    private String index;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getIsExcellent() {
        return isExcellent;
    }

    public void setIsExcellent(String isExcellent) {
        this.isExcellent = isExcellent;
    }

    public String getQuarter4() {
        return quarter4;
    }

    public void setQuarter4(String quarter4) {
        this.quarter4 = quarter4;
    }

    public String getQuarter3() {
        return quarter3;
    }

    public void setQuarter3(String quarter3) {
        this.quarter3 = quarter3;
    }

    public String getQuarter2() {
        return quarter2;
    }

    public void setQuarter2(String quarter2) {
        this.quarter2 = quarter2;
    }

    public String getQuarter1() {
        return quarter1;
    }

    public void setQuarter1(String quarter1) {
        this.quarter1 = quarter1;
    }

    //平时考核
    private List<RegularAssessmentVo> regularAssessmentVos=new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
