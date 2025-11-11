package com.cb.assess.domain.vo;

import com.cb.assess.domain.BizAssessQuotaAllocation;

public class BizAssessQuotaAllocationVo extends BizAssessQuotaAllocation {
    private String deptName;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
