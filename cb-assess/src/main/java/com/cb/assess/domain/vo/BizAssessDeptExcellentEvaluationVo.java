package com.cb.assess.domain.vo;

import com.cb.assess.domain.BizAssessDeptExcellentEvaluation;
import com.cb.common.core.domain.entity.SysUser;

import java.util.ArrayList;
import java.util.List;

public class BizAssessDeptExcellentEvaluationVo extends BizAssessDeptExcellentEvaluation {
    //姓名
    private String name="";
    //单位处室
    private String deptName="";
    //平评议人姓名
    private String discussantUserName="";
    //数据类型，1为user_id等于当前用户，2为discussantUserId等于当前用户，3为全部不加次限制
    private String dataType="";
    /** 被评议人ids */
    private String userIds="";

    /** 评议人ids */
    private String discussantUserIds="";
    //推荐优秀的人数
    private Integer recommendGradeExcellent=0;
    //已评价数量
    private Integer reviewed=0;
    //全部数量，俩相当代表评价完了
    private Integer number=0;
    private String escalation="";
    /**
     * 导出字段需要
     */
    private String quarter1="";
    private String quarter2="";
    private String quarter3="";
    private String quarter4="";

    /**
     * 导出时那个表需要一个序号
     */
    private String index="";

    /**
     * 统计字段
     */
    private Integer countTotal=0;

    public Integer getCountTotal() {
        return countTotal;
    }

    public void setCountTotal(Integer countTotal) {
        this.countTotal = countTotal;
    }

    //平时考核
    private List<RegularAssessmentVo> regularAssessmentVos=new ArrayList<>();

    //给前端传用户列表的
    private List<SysUser> sysUsers;

    public List<SysUser> getSysUsers() {
        return sysUsers;
    }

    public void setSysUsers(List<SysUser> sysUsers) {
        this.sysUsers = sysUsers;
    }

    @Override
    public String getEscalation() {
        return escalation;
    }

    @Override
    public void setEscalation(String escalation) {
        this.escalation = escalation;
    }



    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public Integer getReviewed() {
        return reviewed;
    }

    public void setReviewed(Integer reviewed) {
        this.reviewed = reviewed;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getRecommendGradeExcellent() {
        return recommendGradeExcellent;
    }

    public void setRecommendGradeExcellent(Integer recommendGradeExcellent) {
        this.recommendGradeExcellent = recommendGradeExcellent;
    }

    public String getQuarter1() {
        return quarter1;
    }

    public void setQuarter1(String quarter1) {
        this.quarter1 = quarter1;
    }

    public String getQuarter2() {
        return quarter2;
    }

    public void setQuarter2(String quarter2) {
        this.quarter2 = quarter2;
    }

    public String getQuarter3() {
        return quarter3;
    }

    public void setQuarter3(String quarter3) {
        this.quarter3 = quarter3;
    }

    public String getQuarter4() {
        return quarter4;
    }

    public void setQuarter4(String quarter4) {
        this.quarter4 = quarter4;
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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDiscussantUserName() {
        return discussantUserName;
    }

    public void setDiscussantUserName(String discussantUserName) {
        this.discussantUserName = discussantUserName;
    }

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

    public List<RegularAssessmentVo> getRegularAssessmentVos() {
        return regularAssessmentVos;
    }

    public void setRegularAssessmentVos(List<RegularAssessmentVo> regularAssessmentVos) {
        this.regularAssessmentVos = regularAssessmentVos;
    }
}
