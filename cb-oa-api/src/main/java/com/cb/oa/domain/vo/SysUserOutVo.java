package com.cb.oa.domain.vo;

import com.cb.oa.domain.SysUserOut;

public class SysUserOutVo extends SysUserOut {
    private String deptOutName;

    //系统部门id
    private Long sysDeptId;

    //用户匹配状态
    private Integer userStatus;

    public String getDeptOutName() {
        return deptOutName;
    }

    public void setDeptOutName(String deptOutName) {
        this.deptOutName = deptOutName;
    }

    public Long getSysDeptId() {
        return sysDeptId;
    }

    public void setSysDeptId(Long sysDeptId) {
        this.sysDeptId = sysDeptId;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }
}
