package com.cb.worksituation.vo;

import com.cb.common.annotation.Excel;
import com.cb.worksituation.domain.WorkSituation;

import java.io.Serializable;
import java.util.List;

public class WorkSituationImportVo implements Serializable {
    @Excel(name = "序号")
    private Integer sort;

    @Excel(name = "部门")
    private String deptName;

    @Excel(name = "姓名")
    private String name;

    /** 工作日（天） */
    @Excel(name = "工作日（天）")
    private Integer workDay;

    /** 出勤（天） */
    @Excel(name = "出勤（天）")
    private Integer attendanceDay;

    /** 请假（天） */
    @Excel(name = "请假（天）")
    private Integer leaveDay;

    /** 正常（天） */
    @Excel(name = "正常（次）")
    private Integer normal;

    /** 补卡（天） */
    @Excel(name = "补卡（次）")
    private Integer cardReplacement;

    /** 缺卡（天） */
    @Excel(name = "缺卡（次）")
    private Integer vacancyDay;

    /** 迟到（次） */
    @Excel(name = "迟到（次）")
    private Integer arriveLate;

    /** 早退（次） */
    @Excel(name = "早退（次）")
    private Integer leaveEarly;

    @Excel(name = "备注")
    private String remark;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public Integer getWorkDay() {
        return workDay;
    }

    public void setWorkDay(Integer workDay) {
        this.workDay = workDay;
    }

    public Integer getAttendanceDay() {
        return attendanceDay;
    }

    public void setAttendanceDay(Integer attendanceDay) {
        this.attendanceDay = attendanceDay;
    }

    public Integer getLeaveDay() {
        return leaveDay;
    }

    public void setLeaveDay(Integer leaveDay) {
        this.leaveDay = leaveDay;
    }

    public Integer getNormal() {
        return normal;
    }

    public void setNormal(Integer normal) {
        this.normal = normal;
    }

    public Integer getCardReplacement() {
        return cardReplacement;
    }

    public void setCardReplacement(Integer cardReplacement) {
        this.cardReplacement = cardReplacement;
    }

    public Integer getVacancyDay() {
        return vacancyDay;
    }

    public void setVacancyDay(Integer vacancyDay) {
        this.vacancyDay = vacancyDay;
    }

    public Integer getArriveLate() {
        return arriveLate;
    }

    public void setArriveLate(Integer arriveLate) {
        this.arriveLate = arriveLate;
    }

    public Integer getLeaveEarly() {
        return leaveEarly;
    }

    public void setLeaveEarly(Integer leaveEarly) {
        this.leaveEarly = leaveEarly;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
