package com.cb.common.core.domain.vo;

import com.cb.common.core.domain.entity.SysUserSecondmentWorkInfo;

public class SysUserSecondmentWorkInfoVo extends SysUserSecondmentWorkInfo {

    /**
     * 姓名
     */
    private String name;

    /**
     * 单位处室
     */
    private String deptName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

}
