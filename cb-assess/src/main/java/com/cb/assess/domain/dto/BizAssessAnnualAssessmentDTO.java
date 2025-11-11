package com.cb.assess.domain.dto;

import java.util.List;

public class BizAssessAnnualAssessmentDTO {
    //机关评定意见
    private String organOpinions;
   //盖章
    private String institutionalReviewStamp;
    //签名
    private String organOpinionsSign;
    //时间
    private String organOpinionsTime;
    private List<String> ids;
    private Integer auditStatus;

    public String getOrganOpinions() {
        return organOpinions;
    }

    public void setOrganOpinions(String organOpinions) {
        this.organOpinions = organOpinions;
    }

    public String getInstitutionalReviewStamp() {
        return institutionalReviewStamp;
    }

    public void setInstitutionalReviewStamp(String institutionalReviewStamp) {
        this.institutionalReviewStamp = institutionalReviewStamp;
    }

    public String getOrganOpinionsSign() {
        return organOpinionsSign;
    }

    public void setOrganOpinionsSign(String organOpinionsSign) {
        this.organOpinionsSign = organOpinionsSign;
    }

    public String getOrganOpinionsTime() {
        return organOpinionsTime;
    }

    public void setOrganOpinionsTime(String organOpinionsTime) {
        this.organOpinionsTime = organOpinionsTime;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }
}
