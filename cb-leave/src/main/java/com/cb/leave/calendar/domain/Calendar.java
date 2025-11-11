package com.cb.leave.calendar.domain;


import java.io.Serializable;

/**
 * 日历表
 * @TableName calendar
 */

public class Calendar implements Serializable {
    /**
     * 用年月日拼起来的
     */
    private Integer id;

    /**
     * 年（阳历）
     */
    private Integer year;

    /**
     * 月（阳历）
     */
    private Integer month;

    /**
     * 日（阳历）
     */
    private Integer day;

    /**
     * 年（农历）
     */
    private String lunarYear;

    /**
     * 月（农历）
     */
    private String lunarMonth;

    /**
     * 日（农历）
     */
    private String lunarDate;

    /**
     * 月（农历描述）
     */
    private String lMonth;

    /**
     * 日（农历描述）
     */
    private String lDate;

    /**
     * 年-干支
     */
    private String gzYear;

    /**
     * 日-干支
     */
    private String gzMonth;

    /**
     * 日-干支
     */
    private String gzDate;

    /**
     * 生肖年
     */
    private String animal;

    /**
     * 星期描述（一二三四五六日）
     */
    private String cnDay;

    /**
     * 是否是农历大月（1-是）
     */
    private String isBigMonth;

    /**
     * 节假日描述（含节气）
     */
    private String term;

    /**
     * 节假日状态（1-休假，2-补班）
     */
    private String status;

    /**
     * 节假日类型
     */
    private String type;

    /**
     * 数据来源yj_from
     */
    private String yjFrom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getLunarYear() {
        return lunarYear;
    }

    public void setLunarYear(String lunarYear) {
        this.lunarYear = lunarYear;
    }

    public String getLunarMonth() {
        return lunarMonth;
    }

    public void setLunarMonth(String lunarMonth) {
        this.lunarMonth = lunarMonth;
    }

    public String getLunarDate() {
        return lunarDate;
    }

    public void setLunarDate(String lunarDate) {
        this.lunarDate = lunarDate;
    }

    public String getlMonth() {
        return lMonth;
    }

    public void setlMonth(String lMonth) {
        this.lMonth = lMonth;
    }

    public String getlDate() {
        return lDate;
    }

    public void setlDate(String lDate) {
        this.lDate = lDate;
    }

    public String getGzYear() {
        return gzYear;
    }

    public void setGzYear(String gzYear) {
        this.gzYear = gzYear;
    }

    public String getGzMonth() {
        return gzMonth;
    }

    public void setGzMonth(String gzMonth) {
        this.gzMonth = gzMonth;
    }

    public String getGzDate() {
        return gzDate;
    }

    public void setGzDate(String gzDate) {
        this.gzDate = gzDate;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getCnDay() {
        return cnDay;
    }

    public void setCnDay(String cnDay) {
        this.cnDay = cnDay;
    }

    public String getIsBigMonth() {
        return isBigMonth;
    }

    public void setIsBigMonth(String isBigMonth) {
        this.isBigMonth = isBigMonth;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYjFrom() {
        return yjFrom;
    }

    public void setYjFrom(String yjFrom) {
        this.yjFrom = yjFrom;
    }
}