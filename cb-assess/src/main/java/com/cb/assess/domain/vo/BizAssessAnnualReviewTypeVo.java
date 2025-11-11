package com.cb.assess.domain.vo;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;

import java.util.ArrayList;
import java.util.List;

public class BizAssessAnnualReviewTypeVo extends BaseEntity {
    private String id;

    /** 被评议人 */
//    @Excel(name = "被评议人")
    private Long userId;
    @Excel(name = "被评议人")
    private String userName;

    /** 评议人 */

    private Long discussantUserId;
    @Excel(name = "评议人")
    private String discussantUserName;

    /** 年度考核推荐的等次 */
    @Excel(name = "年度考核推荐的等次")
    private String recommendGrade;

    /** $column.columnComment */
    private String delFlag;
    private String assessmentYear;


    private String name;
    //列表类型，1为我要填别人的，2是别人填我的
    private String dataType;
    /**
     * 1公务员,2事业单位
     */
    private String type;
    private String status;
    private String workPost;
    private Integer annualReviewTypeCount;

    public Integer getAnnualReviewTypeCount() {
        return annualReviewTypeCount;
    }

    public void setAnnualReviewTypeCount(Integer annualReviewTypeCount) {
        this.annualReviewTypeCount = annualReviewTypeCount;
    }

    private List<RegularAssessmentVo> regularAssessmentVos=new ArrayList<>();

    public List<RegularAssessmentVo> getRegularAssessmentVos() {
        return regularAssessmentVos;
    }

    public void setRegularAssessmentVos(List<RegularAssessmentVo> regularAssessmentVos) {
        this.regularAssessmentVos = regularAssessmentVos;
    }

    public String getWorkPost() {
        return workPost;
    }

    public void setWorkPost(String workPost) {
        this.workPost = workPost;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDiscussantUserName() {
        return discussantUserName;
    }

    public void setDiscussantUserName(String discussantUserName) {
        this.discussantUserName = discussantUserName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDiscussantUserId() {
        return discussantUserId;
    }

    public void setDiscussantUserId(Long discussantUserId) {
        this.discussantUserId = discussantUserId;
    }

    public String getRecommendGrade() {
        return recommendGrade;
    }

    public void setRecommendGrade(String recommendGrade) {
        this.recommendGrade = recommendGrade;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getAssessmentYear() {
        return assessmentYear;
    }

    public void setAssessmentYear(String assessmentYear) {
        this.assessmentYear = assessmentYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
