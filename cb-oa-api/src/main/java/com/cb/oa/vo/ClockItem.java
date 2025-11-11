package com.cb.oa.vo;

/**
 * @Description:
 * @Author ARPHS
 * @Date 2023/11/30 11:55
 */
public class ClockItem {

    private String userId;//用户 ID
    private String username;//用户名称
    private String deptId;//部门 ID
    private String name;//名部名称
    private Integer sort;//排序
    private Integer userSort;//排序
    private Integer outForWork;//出勤天数
    private Integer workNum;//工作天数
    private Integer normal;//正常次数
    private Integer makeWorkCount;//补卡次数
    private Integer notWorkCount;//缺卡次数
    private Integer lateCount;//迟到次数
    private Integer leaveCount;//早退次数
    private Integer leaveDate;//请假天数

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getUserSort() {
        return userSort;
    }

    public void setUserSort(Integer userSort) {
        this.userSort = userSort;
    }

    public Integer getOutForWork() {
        return outForWork;
    }

    public void setOutForWork(Integer outForWork) {
        this.outForWork = outForWork;
    }

    public Integer getWorkNum() {
        return workNum;
    }

    public void setWorkNum(Integer workNum) {
        this.workNum = workNum;
    }

    public Integer getNormal() {
        return normal;
    }

    public void setNormal(Integer normal) {
        this.normal = normal;
    }

    public Integer getMakeWorkCount() {
        return makeWorkCount;
    }

    public void setMakeWorkCount(Integer makeWorkCount) {
        this.makeWorkCount = makeWorkCount;
    }

    public Integer getNotWorkCount() {
        return notWorkCount;
    }

    public void setNotWorkCount(Integer notWorkCount) {
        this.notWorkCount = notWorkCount;
    }

    public Integer getLateCount() {
        return lateCount;
    }

    public void setLateCount(Integer lateCount) {
        this.lateCount = lateCount;
    }

    public Integer getLeaveCount() {
        return leaveCount;
    }

    public void setLeaveCount(Integer leaveCount) {
        this.leaveCount = leaveCount;
    }

    public Integer getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Integer leaveDate) {
        this.leaveDate = leaveDate;
    }
}
