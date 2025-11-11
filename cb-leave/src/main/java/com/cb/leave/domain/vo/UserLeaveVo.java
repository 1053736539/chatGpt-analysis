package com.cb.leave.domain.vo;


/**
 * 用户休假类型和剩余休假VO
 */

public class UserLeaveVo {
    private Integer id;

    /** 假期名称（如年休假、病假等） */
    private String leaveName;

    /** 假期类型描述 */
    private String description;
    /** 年份 */
    private Integer leaveYear;

    private Long userId;

    /** 假期总天数 */
    private Long totalDays;

    /** 已休天数 */
    private Long usedDays;

    /** 待休天数 */
    private Long pendingDays;

    /** 剩余天数 */
    private Long remainingDays;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLeaveName() {
        return leaveName;
    }

    public void setLeaveName(String leaveName) {
        this.leaveName = leaveName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLeaveYear() {
        return leaveYear;
    }

    public void setLeaveYear(Integer leaveYear) {
        this.leaveYear = leaveYear;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Long totalDays) {
        this.totalDays = totalDays;
    }

    public Long getUsedDays() {
        return usedDays;
    }

    public void setUsedDays(Long usedDays) {
        this.usedDays = usedDays;
    }

    public Long getPendingDays() {
        return pendingDays;
    }

    public void setPendingDays(Long pendingDays) {
        this.pendingDays = pendingDays;
    }

    public Long getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(Long remainingDays) {
        this.remainingDays = remainingDays;
    }
}
