package com.cb.worksituation.vo;

import com.cb.common.annotation.Excel;
import com.cb.worksituation.domain.WorkSituation;

import java.util.List;

public class WorkSituationVo extends WorkSituation {
    @Excel(name = "姓名")
    private String name;
    private String deptName;
    //用来查询部门的
    private String deptId;
    private List<String> situationYears;
    private  List<String> situationMonths;
    /**
     * 月份，前端传过来的，数据['2023-01','2023-02']
     */
    private  List<String> months;

    public List<String> getMonths() {
        return months;
    }

    public void setMonths(List<String> months) {
        this.months = months;
    }

    public List<String> getSituationYears() {
        return situationYears;
    }

    public void setSituationYears(List<String> situationYears) {
        this.situationYears = situationYears;
    }


    public List<String> getSituationMonths() {
        return situationMonths;
    }

    public void setSituationMonths(List<String> situationMonths) {
        this.situationMonths = situationMonths;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
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
