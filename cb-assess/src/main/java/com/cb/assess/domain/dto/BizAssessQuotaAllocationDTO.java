package com.cb.assess.domain.dto;

import com.cb.assess.domain.BizAssessQuotaAllocation;

public class BizAssessQuotaAllocationDTO extends BizAssessQuotaAllocation {
    private String allocationUsers;

    public String getAllocationUsers() {
        return allocationUsers;
    }

    public void setAllocationUsers(String allocationUsers) {
        this.allocationUsers = allocationUsers;
    }
}
