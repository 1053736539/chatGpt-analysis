package com.cb.assess.domain.vo;

import com.cb.assess.domain.BizAssessAssessmentGrade;

public class BizAssessAssessmentGradeVo extends BizAssessAssessmentGrade {
    private String name;
    private String deptName;
    //职务
    private String workPost;

    public String getWorkPost() {
        return workPost;
    }

    public void setWorkPost(String workPost) {
        this.workPost = workPost;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
