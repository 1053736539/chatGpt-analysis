package com.cb.common.core.domain.vo;

import com.cb.common.annotation.Excel;

import java.io.Serializable;

public class ImportUserVo implements Serializable {

    @Excel(name = "序号")
    private String orderNo;

    @Excel(name = "处室")
    private String deptName;

    @Excel(name = "处室\n代码")
    private String deptCode;

    @Excel(name = "姓名")
    private String name;

    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    @Excel(name = "民族")
    private String nation;

    @Excel(name = "出生日期", dateFormat = "yyyy-MM-dd")
    private String birthday;

    @Excel(name = "学历")
    private String education;

    @Excel(name = "参加工\n作时间", dateFormat = "yyyy-MM-dd")
    private String startWorkTime;

    @Excel(name = "政治\n面貌")
    private String politicalIdentity;

    @Excel(name = "职务\n代码")
    private String workPostCode;

    @Excel(name = "职务")
    private String workPost;

    @Excel(name = "任现职务时间", dateFormat = "yyyy-MM-dd")
    private String workPostTime;

    @Excel(name = "任同级职务时间", dateFormat = "yyyy-MM-dd")
    private String sameWorkPostTime;

    @Excel(name = "职级\n代码")
    private String workTitleCode;

    @Excel(name = "职级")
    private String workTitle;

    @Excel(name = "任现职级时间", dateFormat = "yyyy-MM-dd")
    private String workTitleTime;

    @Excel(name = "任同级职级时间", dateFormat = "yyyy-MM-dd")
    private String sameWorkTitleTime;

    @Excel(name = "编制", readConverterExp = "1=行政,2=参公,3=事业,4=企业,5=合同")
    private String identityType;

    @Excel(name = "主要负责人")
    private String isMainLeader;

    @Excel(name = "办公电话")
    private String officeTel;

    @Excel(name = "手机")
    private String phonenumber;

    @Excel(name = "身份证号")
    private String idcard;

    @Excel(name = "籍贯")
    private String nativePlace;

    @Excel(name = "入党\n时间", dateFormat = "yyyy-MM-dd")
    private String joinPartyTime;

    @Excel(name = "进入省\n统时间", dateFormat = "yyyy-MM-dd")
    private String joinOrgTime;

    @Excel(name = "基层\n经历")
    private String grassrootsWorkTime;

    @Excel(name = "毕业院校")
    private String school;

    @Excel(name = "专业")
    private String major;

    @Excel(name = "毕业\n时间", dateFormat = "yyyy-MM-dd")
    private String graduationTime;

    @Excel(name = "国民系列学历")
    private String education1;

    @Excel(name = "国民系列院校")
    private String school1;

    @Excel(name = "国民系列专业")
    private String major1;

    @Excel(name = "职称\n资格")
    private String position;

    @Excel(name = "取得资\n格时间", dateFormat = "yyyy-MM-dd")
    private String positionTime;

    @Excel(name = "正科\n任职时间", dateFormat = "yyyy-MM-dd")
    private String sectionChiefTime;

    @Excel(name = "在本处室工作时间", dateFormat = "yyyy-MM-dd")
    private String joinDeptTime;

    @Excel(name = "婚否", readConverterExp = "0=未婚,1=已婚")
    private String maritalStatus;

    @Excel(name = "是否军转")
    private String isVeterans;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getStartWorkTime() {
        return startWorkTime;
    }

    public void setStartWorkTime(String startWorkTime) {
        this.startWorkTime = startWorkTime;
    }

    public String getPoliticalIdentity() {
        return politicalIdentity;
    }

    public void setPoliticalIdentity(String politicalIdentity) {
        this.politicalIdentity = politicalIdentity;
    }

    public String getWorkPostCode() {
        return workPostCode;
    }

    public void setWorkPostCode(String workPostCode) {
        this.workPostCode = workPostCode;
    }

    public String getWorkPost() {
        return workPost;
    }

    public void setWorkPost(String workPost) {
        this.workPost = workPost;
    }

    public String getWorkPostTime() {
        return workPostTime;
    }

    public void setWorkPostTime(String workPostTime) {
        this.workPostTime = workPostTime;
    }

    public String getSameWorkPostTime() {
        return sameWorkPostTime;
    }

    public void setSameWorkPostTime(String sameWorkPostTime) {
        this.sameWorkPostTime = sameWorkPostTime;
    }

    public String getWorkTitleCode() {
        return workTitleCode;
    }

    public void setWorkTitleCode(String workTitleCode) {
        this.workTitleCode = workTitleCode;
    }

    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public String getWorkTitleTime() {
        return workTitleTime;
    }

    public void setWorkTitleTime(String workTitleTime) {
        this.workTitleTime = workTitleTime;
    }

    public String getSameWorkTitleTime() {
        return sameWorkTitleTime;
    }

    public void setSameWorkTitleTime(String sameWorkTitleTime) {
        this.sameWorkTitleTime = sameWorkTitleTime;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getIsMainLeader() {
        return isMainLeader;
    }

    public void setIsMainLeader(String isMainLeader) {
        this.isMainLeader = isMainLeader;
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getJoinPartyTime() {
        return joinPartyTime;
    }

    public void setJoinPartyTime(String joinPartyTime) {
        this.joinPartyTime = joinPartyTime;
    }

    public String getJoinOrgTime() {
        return joinOrgTime;
    }

    public void setJoinOrgTime(String joinOrgTime) {
        this.joinOrgTime = joinOrgTime;
    }

    public String getGrassrootsWorkTime() {
        return grassrootsWorkTime;
    }

    public void setGrassrootsWorkTime(String grassrootsWorkTime) {
        this.grassrootsWorkTime = grassrootsWorkTime;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(String graduationTime) {
        this.graduationTime = graduationTime;
    }

    public String getEducation1() {
        return education1;
    }

    public void setEducation1(String education1) {
        this.education1 = education1;
    }

    public String getSchool1() {
        return school1;
    }

    public void setSchool1(String school1) {
        this.school1 = school1;
    }

    public String getMajor1() {
        return major1;
    }

    public void setMajor1(String major1) {
        this.major1 = major1;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPositionTime() {
        return positionTime;
    }

    public void setPositionTime(String positionTime) {
        this.positionTime = positionTime;
    }

    public String getSectionChiefTime() {
        return sectionChiefTime;
    }

    public void setSectionChiefTime(String sectionChiefTime) {
        this.sectionChiefTime = sectionChiefTime;
    }

    public String getJoinDeptTime() {
        return joinDeptTime;
    }

    public void setJoinDeptTime(String joinDeptTime) {
        this.joinDeptTime = joinDeptTime;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getIsVeterans() {
        return isVeterans;
    }

    public void setIsVeterans(String isVeterans) {
        this.isVeterans = isVeterans;
    }
}
