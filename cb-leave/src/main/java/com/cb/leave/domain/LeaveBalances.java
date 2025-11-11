package com.cb.leave.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Transient;

/**
 * 假期额度对象 leave_balances
 * 
 * @author yangcd
 * @date 2024-09-09
 */
public class LeaveBalances extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Integer id;

    /** 用户ID */

    private Long userId;

    /** 假期类型ID */

    private Integer leaveTypeId;

    // 新增字段
    @Excel(name = "用户名")
    private String nickName; // 用户名
    @Excel(name = "假期类型")
    private String leaveName; // 假期类型名称

    /** 年份 */
    @Excel(name = "年份")
    private Integer leaveYear;

    /** 假期总天数 */
    @Excel(name = "假期总天数")
    private Float totalDays;

    /** 已休天数 */
    @Excel(name = "已休天数")
    private Float usedDays;

    /** 待休天数 */
    @Excel(name = "待休天数")
    private Float pendingDays;

    /** 剩余天数 */
    @Excel(name = "剩余天数")
    private Float remainingDays;

    /** 确认状态 */
    @Excel(name = "确认状态", readConverterExp = "1=待确认,2=审核中,3=已确认,4=审核驳回")
    private Integer status;

    /** 审批意见 */
    @Excel(name = "审批意见")
    private String approvalOpinion;

    /** 审批人 */
    private Long leaderId;

    /** 确认时是否有修改的标记 1-是 0-否 */
    @Excel(name = "确认时是否有修改")
    private Integer changeFlag;

    /** 审核人名称 */
    @Transient
    @Excel(name = "审核人名称")
    private String leaderName;

    @Transient
    @Excel(name = "部门名称")
    private String deptName;

    @Transient
    private String identityType;//编制类型 1=行政,2=参公,3=事业,4=企业

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setLeaveTypeId(Integer leaveTypeId)
    {
        this.leaveTypeId = leaveTypeId;
    }

    public Integer getLeaveTypeId()
    {
        return leaveTypeId;
    }
    public void setLeaveYear(Integer leaveYear)
    {
        this.leaveYear = leaveYear;
    }

    public Integer getLeaveYear()
    {
        return leaveYear;
    }
    public void setTotalDays(Float totalDays)
    {
        this.totalDays = totalDays;
    }

    public Float getTotalDays()
    {
        return totalDays;
    }
    public void setUsedDays(Float usedDays)
    {
        this.usedDays = usedDays;
    }

    public Float getUsedDays()
    {
        return usedDays;
    }
    public void setPendingDays(Float pendingDays)
    {
        this.pendingDays = pendingDays;
    }

    public Float getPendingDays()
    {
        return pendingDays;
    }
    public void setRemainingDays(Float remainingDays)
    {
        this.remainingDays = remainingDays;
    }

    public Float getRemainingDays()
    {
        return remainingDays;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLeaveName() {
        return leaveName;
    }

    public void setLeaveName(String leaveName) {
        this.leaveName = leaveName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getApprovalOpinion() {
        return approvalOpinion;
    }

    public void setApprovalOpinion(String approvalOpinion) {
        this.approvalOpinion = approvalOpinion;
    }

    public Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }

    public Integer getChangeFlag() {
        return changeFlag;
    }

    public void setChangeFlag(Integer changeFlag) {
        this.changeFlag = changeFlag;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("leaveTypeId", getLeaveTypeId())
            .append("leaveYear", getLeaveYear())
            .append("totalDays", getTotalDays())
            .append("usedDays", getUsedDays())
            .append("pendingDays", getPendingDays())
            .append("remainingDays", getRemainingDays())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
