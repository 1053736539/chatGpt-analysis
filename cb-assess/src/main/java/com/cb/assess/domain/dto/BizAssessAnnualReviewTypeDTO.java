package com.cb.assess.domain.dto;

import com.cb.common.annotation.Excel;

public class BizAssessAnnualReviewTypeDTO {
    /** 被评议人 */
    private String userIds;

    /** 评议人 */
    private String discussantUserIds;

    /** 年度考核推荐的等次 */
    private String recommendGrade;
    private String assessmentYear;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAssessmentYear() {
        return assessmentYear;
    }

    public void setAssessmentYear(String assessmentYear) {
        this.assessmentYear = assessmentYear;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getDiscussantUserIds() {
        return discussantUserIds;
    }

    public void setDiscussantUserIds(String discussantUserIds) {
        this.discussantUserIds = discussantUserIds;
    }

    public String getRecommendGrade() {
        return recommendGrade;
    }

    public void setRecommendGrade(String recommendGrade) {
        this.recommendGrade = recommendGrade;
    }
}
