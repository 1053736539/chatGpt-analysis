package com.cb.common.core.domain.entity;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户现职级信息对象 sys_user_current_post_info
 *
 * @author ruoyi
 * @date 2023-11-04
 */
public class SysUserCurrentPostInfo {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String currentPostId;

    /**
     * 用户ID
     */
    @Excel(name = "用户ID")
    private Long userId;

    /**
     * 职级
     */
    @Excel(name = "职级")
    private String rank;

    /**
     * 任职级状态
     */
    @Excel(name = "任职级状态")
    private String postLevelStatus;

    /**
     * 批准日期
     */
    @Excel(name = "批准日期")
    private String approvalDate;

    /**
     * 终止日期
     */
    @Excel(name = "终止日期")
    private String endDate;

    /**
     * 删除标记 0-未删除 1-已删除
     */
    private String delFlag;

    /**
     * 任同级职级时间
     */
    @Excel(name = "任同级职级时间")
    private String peerLevel;

    public void setCurrentPostId(String currentPostId) {
        this.currentPostId = currentPostId;
    }

    public String getCurrentPostId() {
        return currentPostId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public void setPostLevelStatus(String postLevelStatus) {
        this.postLevelStatus = postLevelStatus;
    }

    public String getPostLevelStatus() {
        return postLevelStatus;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setPeerLevel(String peerLevel) {
        this.peerLevel = peerLevel;
    }

    public String getPeerLevel() {
        return peerLevel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("currentPostId", getCurrentPostId())
                .append("userId", getUserId())
                .append("rank", getRank())
                .append("postLevelStatus", getPostLevelStatus())
                .append("approvalDate", getApprovalDate())
                .append("endDate", getEndDate())
                .append("delFlag", getDelFlag())
                .append("peerLevel", getPeerLevel())
                .toString();
    }
}
