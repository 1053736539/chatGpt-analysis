package com.cb.common.core.domain.entity;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 奖惩信息对象 sys_user_rewards_and_penalties_info
 *
 * @author hu
 * @date 2023-10-29
 */
public class SysUserRewardsAndPenaltiesInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String rewardsAndPenaltiesInfoId;

    /**
     * 用户ID
     */
    @Excel(name = "用户ID")
    private Long userId;

    /**
     * 奖惩名称代码
     */
    @Excel(name = "奖惩名称代码")
    private String rewardsAndPenaltiesNameCode;

    /**
     * 奖惩名称
     */
    @Excel(name = "奖惩名称")
    private String rewardsAndPenaltiesName;

    /**
     * 批准日期
     */
    @Excel(name = "批准日期")
    private String approvalDate;

    /**
     * 批准机关
     */
    @Excel(name = "批准机关")
    private String approvalAuthority;

    /**
     * 批准机关级别
     */
    @Excel(name = "批准机关级别")
    private String approvalAuthorityLevel;

    /**
     * 受奖惩时职务层次
     */
    @Excel(name = "受奖惩时职务层次")
    private String positionHierarchy;

    /**
     * 受奖惩时职级层次
     */
    @Excel(name = "受奖惩时职级层次")
    private String rankLevel;

    /**
     * 撤销日期
     */
    @Excel(name = "撤销日期")
    private String revocationDate;

    /**
     * 批准机关性质
     */
    @Excel(name = "批准机关性质")
    private String approvalAuthorityNature;

    /**
     * 奖惩综述
     */
    @Excel(name = "奖惩综述")
    private String rewardAndPenaltiesDesc;

    /**
     * 删除标记 0-未删除 1-已删除
     */
    private String delFlag;

    /**
     * 序号
     */
    @Excel(name = "序号")
    private String sortNum;

    public void setRewardsAndPenaltiesInfoId(String rewardsAndPenaltiesInfoId) {
        this.rewardsAndPenaltiesInfoId = rewardsAndPenaltiesInfoId;
    }

    public String getRewardsAndPenaltiesInfoId() {
        return rewardsAndPenaltiesInfoId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setRewardsAndPenaltiesNameCode(String rewardsAndPenaltiesNameCode) {
        this.rewardsAndPenaltiesNameCode = rewardsAndPenaltiesNameCode;
    }

    public String getRewardsAndPenaltiesNameCode() {
        return rewardsAndPenaltiesNameCode;
    }

    public void setRewardsAndPenaltiesName(String rewardsAndPenaltiesName) {
        this.rewardsAndPenaltiesName = rewardsAndPenaltiesName;
    }

    public String getRewardsAndPenaltiesName() {
        return rewardsAndPenaltiesName;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalAuthority(String approvalAuthority) {
        this.approvalAuthority = approvalAuthority;
    }

    public String getApprovalAuthority() {
        return approvalAuthority;
    }

    public void setApprovalAuthorityLevel(String approvalAuthorityLevel) {
        this.approvalAuthorityLevel = approvalAuthorityLevel;
    }

    public String getApprovalAuthorityLevel() {
        return approvalAuthorityLevel;
    }

    public void setPositionHierarchy(String positionHierarchy) {
        this.positionHierarchy = positionHierarchy;
    }

    public String getPositionHierarchy() {
        return positionHierarchy;
    }

    public void setRankLevel(String rankLevel) {
        this.rankLevel = rankLevel;
    }

    public String getRankLevel() {
        return rankLevel;
    }

    public void setRevocationDate(String revocationDate) {
        this.revocationDate = revocationDate;
    }

    public String getRevocationDate() {
        return revocationDate;
    }

    public void setApprovalAuthorityNature(String approvalAuthorityNature) {
        this.approvalAuthorityNature = approvalAuthorityNature;
    }

    public String getApprovalAuthorityNature() {
        return approvalAuthorityNature;
    }

    public void setRewardAndPenaltiesDesc(String rewardAndPenaltiesDesc) {
        this.rewardAndPenaltiesDesc = rewardAndPenaltiesDesc;
    }

    public String getRewardAndPenaltiesDesc() {
        return rewardAndPenaltiesDesc;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setSortNum(String sortNum) {
        this.sortNum = sortNum;
    }

    public String getSortNum() {
        return sortNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("rewardsAndPenaltiesInfoId", getRewardsAndPenaltiesInfoId())
                .append("userId", getUserId())
                .append("rewardsAndPenaltiesNameCode", getRewardsAndPenaltiesNameCode())
                .append("rewardsAndPenaltiesName", getRewardsAndPenaltiesName())
                .append("approvalDate", getApprovalDate())
                .append("approvalAuthority", getApprovalAuthority())
                .append("approvalAuthorityLevel", getApprovalAuthorityLevel())
                .append("positionHierarchy", getPositionHierarchy())
                .append("rankLevel", getRankLevel())
                .append("revocationDate", getRevocationDate())
                .append("approvalAuthorityNature", getApprovalAuthorityNature())
                .append("rewardAndPenaltiesDesc", getRewardAndPenaltiesDesc())
                .append("delFlag", getDelFlag())
                .append("sortNum", getSortNum())
                .toString();
    }
}