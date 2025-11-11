package com.cb.assess.domain.dto;

import com.cb.assess.domain.BizAssessCadrePoliticalQuality;

import javax.validation.constraints.NotBlank;

public class BizAssessCadrePoliticalQualityDTO extends BizAssessCadrePoliticalQuality {
    @NotBlank(message = "评测人不能为空")
    private String reviewerUserIds;
    @NotBlank(message = "评测对象不能为空")
    private String userIds;

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getReviewerUserIds() {
        return reviewerUserIds;
    }

    public void setReviewerUserIds(String reviewerUserIds) {
        this.reviewerUserIds = reviewerUserIds;
    }
}
