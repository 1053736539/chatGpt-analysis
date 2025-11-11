package com.cb.assess.domain.dto;

import com.cb.assess.domain.BizAssessAssessmentGrade;

public class BizAssessAssessmentGradeDTO extends BizAssessAssessmentGrade {
    private String userIds;

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }
}
