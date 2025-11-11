package com.cb.assess.domain.vo;

import com.cb.assess.domain.BizAssessAnnualAssessment;

import java.util.List;

public class BizAssessAnnualAssessmentVo extends BizAssessAnnualAssessment {
    private String name;
    private String deptName;
    private String sex;
    private String birthday;
    private String workTitle;

   private List<String> workCodes;
   private List<Long> deptIds;

    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public List<Long> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<Long> deptIds) {
        this.deptIds = deptIds;
    }

    public List<String> getWorkCodes() {
        return workCodes;
    }

    public void setWorkCodes(List<String> workCodes) {
        this.workCodes = workCodes;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

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
}
