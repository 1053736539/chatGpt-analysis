package com.cb.common.core.domain.vo;

import java.io.Serializable;

public class SysDeptBasicInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 部门ID */
    private Long deptId;

    /** 父部门ID */
    private Long parentId;

    /** 祖级列表 */
    private String ancestors;

    /** 部门名称 */
    private String deptName;

    /** 简称 */
    private String deptAbbreviation;

    /** 机构编码 */
    private String deptCode;

    /** 部门类型 1-参公 2-事业单位 */
    private String deptIdentityType;

    /** 显示顺序 */
    private String orderNum;

    /** 负责人userId字符串 */
    private String leaderCharge;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptAbbreviation() {
        return deptAbbreviation;
    }

    public void setDeptAbbreviation(String deptAbbreviation) {
        this.deptAbbreviation = deptAbbreviation;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptIdentityType() {
        return deptIdentityType;
    }

    public void setDeptIdentityType(String deptIdentityType) {
        this.deptIdentityType = deptIdentityType;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getLeaderCharge() {
        return leaderCharge;
    }

    public void setLeaderCharge(String leaderCharge) {
        this.leaderCharge = leaderCharge;
    }
}
