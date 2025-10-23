package com.cb.worksituation.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 考勤统计（上班情况）对象 work_situation
 * 
 * @author ruoyi
 * @date 2023-11-15
 */
public class WorkSituation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
//    @Excel(name = "${comment}")
    private Long userId;

    /** 工作日（天） */
    @Excel(name = "工作日")
    private Integer workDay;

    /** 出勤（天） */
    @Excel(name = "出勤")
    private Integer attendanceDay;

    /** 请假（天） */
    @Excel(name = "请假")
    private Float leaveDay;

    /** 正常（天） */
    @Excel(name = "正常")
    private Integer normal;

    /** 补卡（天） */
    @Excel(name = "补卡")
    private Integer cardReplacement;

    /** 缺卡（天） */
    @Excel(name = "缺卡")
    private Integer vacancyDay;

    /** 迟到（次） */
    @Excel(name = "迟到")
    private Integer arriveLate;

    /** 早退（次） */
    @Excel(name = "早退")
    private Integer leaveEarly;
    private Integer sort;

    @Excel(name = "年")
    private String situationYear;
    @Excel(name = "月")
    private String situationMonth;

    /** $column.columnComment */
    private String delFlag;
    private String outUserId;
    /**
     * 是否公示
     */
    private String isPublicity;

    /** 假期类型ID */
    private Long leaveTypeId;

    /** 请假时长，单位秒 */
    @Excel(name = "请假时长，单位秒")
    private Long totalTime;

    /** 实际开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date realityStartTime;

    /** 实际结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date realityEndTime;
    private String leaveName;

    public String getIsPublicity() {
        return isPublicity;
    }

    public void setIsPublicity(String isPublicity) {
        this.isPublicity = isPublicity;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getOutUserId() {
        return outUserId;
    }

    public void setOutUserId(String outUserId) {
        this.outUserId = outUserId;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public java.lang.Integer getWorkDay() {
        return workDay;
    }

    public void setWorkDay(java.lang.Integer workDay) {
        this.workDay = workDay;
    }

    public Integer getAttendanceDay() {
        return attendanceDay;
    }

    public void setAttendanceDay(Integer attendanceDay) {
        this.attendanceDay = attendanceDay;
    }

    public Float getLeaveDay() {
        return leaveDay;
    }

    public void setLeaveDay(Float leaveDay) {
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

    public String getSituationYear() {
        return situationYear;
    }

    public void setSituationYear(String situationYear) {
        this.situationYear = situationYear;
    }

    public String getSituationMonth() {
        return situationMonth;
    }

    public void setSituationMonth(String situationMonth) {
        this.situationMonth = situationMonth;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(Long leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public Date getRealityStartTime() {
        return realityStartTime;
    }

    public void setRealityStartTime(Date realityStartTime) {
        this.realityStartTime = realityStartTime;
    }

    public Date getRealityEndTime() {
        return realityEndTime;
    }

    public void setRealityEndTime(Date realityEndTime) {
        this.realityEndTime = realityEndTime;
    }

    public String getLeaveName() {
        return leaveName;
    }

    public void setLeaveName(String leaveName) {
        this.leaveName = leaveName;
    }
}
