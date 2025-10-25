package com.cb.common.core.domain.entity;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 部门表 sys_dept
 * 
 * @author ruoyi
 */
public class SysDept extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 部门ID */
    private Long deptId;

    /** 父部门ID */
    private Long parentId;

    /** 祖级列表 */
    private String ancestors;

    /** 部门名称 */
    private String deptName;

    /** 显示顺序 */
    private String orderNum;

    /** 负责人 */
    private String leader;

    /** 联系电话 */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 部门状态:0正常,1停用 */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 机构编码 */
    private String deptCode;

    /** 简称 */
    private String deptAbbreviation;

    /** 机构类型（1-法人单位，2-内设机构，3-机构分组） */
    private String deptType;

    /** 所属政区 */
    private String region;

    /** 隶属关系 */
    private String affiliations;

    /** 机构类型 */
    private String deptInstitution;

    /** 机构级别 */
    private String  orgLevel;

    /** 机构成立批准时间 */
    private String  establishedTime;

    /** 参照管理申请日期 */
    private String  approvalDate;

    /** 正职领导职数 */
    private Integer  principalLeaderNo;

    /** 副职领导职数 */
    private Integer  assistantLeaderNo;

    /** 行政编制数 */
    private Integer  administrativeStaffing;


    /** 事业编制(参公) */
    private Integer  causeOrgPublic;

    /** 事业编制(非参公) */
    private Integer  causeOrgPrivate;

    /** 工勤编制数 */
    private Integer  workforceHeadcount;

    /** 其他编制数 */
    private Integer  otherHeadcount;

    /** 机构成立批准文号 */
    private Integer  ratifyNumber;

    /** 参照管理申请文号 */
    private String  applicationNumber;


    /** 参照管理审批文号 */
    private String  approvalNumber;

    /** 领导职数 */
    private Integer  leaderNumber;

    /** 备注 */
    private String  remarks;

    /** 父部门名称 */
    private String parentName;
    private String leaderCharge;

    public String getLeaderCharge() {
        return leaderCharge;
    }

    public void setLeaderCharge(String leaderCharge) {
        this.leaderCharge = leaderCharge;
    }

    /** 子部门 */
    private List<SysDept> children = new ArrayList<SysDept>();

    public Long getDeptId()
    {
        return deptId;
    }

    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public String getAncestors()
    {
        return ancestors;
    }

    public void setAncestors(String ancestors)
    {
        this.ancestors = ancestors;
    }

    @NotBlank(message = "部门名称不能为空")
    @Size(min = 0, max = 30, message = "部门名称长度不能超过30个字符")
    public String getDeptName()
    {
        return deptName;
    }

    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    @NotBlank(message = "显示顺序不能为空")
    public String getOrderNum()
    {
        return orderNum;
    }

    public void setOrderNum(String orderNum)
    {
        this.orderNum = orderNum;
    }

    public String getLeader()
    {
        return leader;
    }

    public void setLeader(String leader)
    {
        this.leader = leader;
    }

    @Size(min = 0, max = 11, message = "联系电话长度不能超过11个字符")
    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptAbbreviation() {
        return deptAbbreviation;
    }

    public void setDeptAbbreviation(String deptAbbreviation) {
        this.deptAbbreviation = deptAbbreviation;
    }

    public String getDeptType() {
        return deptType;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAffiliations() {
        return affiliations;
    }

    public void setAffiliations(String affiliations) {
        this.affiliations = affiliations;
    }

    public String getDeptInstitution() {
        return deptInstitution;
    }

    public void setDeptInstitution(String deptInstitution) {
        this.deptInstitution = deptInstitution;
    }

    public String getOrgLevel() {
        return orgLevel;
    }

    public void setInstitutionalLevel(String orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getEstablishedTime() {
        return establishedTime;
    }

    public void setEstablishedTime(String establishedTime) {
        this.establishedTime = establishedTime;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Integer getPrincipalLeaderNo() {
        return principalLeaderNo;
    }

    public void setPrincipalLeaderNo(Integer principalLeaderNo) {
        this.principalLeaderNo = principalLeaderNo;
    }

    public Integer getAssistantLeaderNo() {
        return assistantLeaderNo;
    }

    public void setAssistantLeaderNo(Integer assistantLeaderNo) {
        this.assistantLeaderNo = assistantLeaderNo;
    }

    public Integer getAdministrativeStaffing() {
        return administrativeStaffing;
    }

    public void setAdministrativeStaffing(Integer administrativeStaffing) {
        this.administrativeStaffing = administrativeStaffing;
    }

    public Integer getCauseOrgPublic() {
        return causeOrgPublic;
    }

    public void setCauseOrgPublic(Integer causeOrgPublic) {
        this.causeOrgPublic = causeOrgPublic;
    }

    public Integer getCauseOrgPrivate() {
        return causeOrgPrivate;
    }

    public void setCauseOrgPrivate(Integer causeOrgPrivate) {
        this.causeOrgPrivate = causeOrgPrivate;
    }

    public Integer getWorkforceHeadcount() {
        return workforceHeadcount;
    }

    public void setWorkforceHeadcount(Integer workforceHeadcount) {
        this.workforceHeadcount = workforceHeadcount;
    }

    public Integer getOtherHeadcount() {
        return otherHeadcount;
    }

    public void setOtherHeadcount(Integer otherHeadcount) {
        this.otherHeadcount = otherHeadcount;
    }

    public Integer getRatifyNumber() {
        return ratifyNumber;
    }

    public void setRatifyNumber(Integer ratifyNumber) {
        this.ratifyNumber = ratifyNumber;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public Integer getLeaderNumber() {
        return leaderNumber;
    }

    public void setLeaderNumber(Integer leaderNumber) {
        this.leaderNumber = leaderNumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getParentName()
    {
        return parentName;
    }

    public void setParentName(String parentName)
    {
        this.parentName = parentName;
    }

    public List<SysDept> getChildren()
    {
        return children;
    }

    public void setChildren(List<SysDept> children)
    {
        this.children = children;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("deptId", getDeptId())
            .append("parentId", getParentId())
            .append("ancestors", getAncestors())
            .append("deptName", getDeptName())
            .append("orderNum", getOrderNum())
            .append("leader", getLeader())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("deptCode",getDeptCode())
            .append("deptAbbreviation",getDeptAbbreviation())
            .append("deptType",getDeptType())
            .append("region",getRegion())
            .append("affiliations",getAffiliations())
            .append("deptInstitution",getDeptInstitution())
            .append("orgLevel",getOrgLevel())
            .append("establishedTime",getEstablishedTime())
            .append("approvalDate",getApprovalDate())
            .append("principalLeaderNo",getPrincipalLeaderNo())
            .append("assistantLeaderNo",getAssistantLeaderNo())
            .append("administrativeStaffing",getAdministrativeStaffing())
            .append("causeOrgPublic",getCauseOrgPublic())
            .append("causeOrgPrivate",getCauseOrgPrivate())
            .append("workforceHeadcount",getWorkforceHeadcount())
            .append("otherHeadcount",getOtherHeadcount())
            .append("ratifyNumber",getRatifyNumber())
            .append("applicationNumber",getApplicationNumber())
            .append("approvalNumber",getApprovalNumber())
            .append("leaderNumber",getLeaderNumber())
            .append("remarks",getRemarks())
            .toString();
    }
}
