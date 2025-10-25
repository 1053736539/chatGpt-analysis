package com.cb.common.core.domain.vo;

import com.cb.common.core.domain.entity.SysDept;

public class SysDeptVo extends SysDept {

    /**
     * 部门分管领导名称
     */
    private String leaderChargeName;

    public String getLeaderChargeName() {
        return leaderChargeName;
    }

    public void setLeaderChargeName(String leaderChargeName) {
        this.leaderChargeName = leaderChargeName;
    }
}
