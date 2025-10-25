package com.cb.common.core.domain.vo;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import com.cb.common.core.domain.entity.UserDeptPost;

import java.util.List;

public class ExportUserAppointInfo extends BaseEntity {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 手机号码
     */
    @Excel(name = "手机", sort = 19, height = 30)
    private String phonenumber;

    /**
     * 用户性别
     */
    @Excel(name = "性别",sort = 3, width = 5, readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    @Excel(name = "处室", type = Excel.Type.EXPORT, sort = 1)
    private String deptName;

    @Excel(name = "处室\n代码", type = Excel.Type.EXPORT, sort = 16,width = 10)
    private String deptCode;

    //统计局项目新增字段---开始
    @Excel(name = "姓名",sort = 2, width = 8)
    private String name;

    @Excel(name = "出生日期", sort = 5, width = 10)
    private String birthday;

    @Excel(name = "民族", sort = 4, width = 6)
    private String nation;

    @Excel(name = "籍贯", sort = 21)
    private String nativePlace;

    //@Excel(name = "姓名")
    private String birthPlace;

    @Excel(name = "政治面貌", sort = 8, width = 9)
    private String politicalIdentity;

    @Excel(name = "入党\n时间", sort = 22, width = 10)
    private String partyJoinTime;

    @Excel(name = "参加工\n作时间",sort = 7, width = 10)
    private String startWorkTime;

    @Excel(name = "身份证号",sort = 20, width = 18)
    private String idcard;

    @Excel(name = "编制",sort = 15, dictType = "preparation_type", width = 5)
    private String identityType;

    @Excel(name = "职务", sort = 10)
    private String workPost;

    @Excel(name = "职务\n代码", sort = 9, width = 8)
    private String workPostCode;

    @Excel(name = "任现职务\n时间", sort = 24, width = 10)
    private String workPostTime;

    @Excel(name = "任同级职\n务时间", sort = 11,width = 10)
    private String sameWorkPostTime;

    @Excel(name = "职级", sort = 13)
    private String workTitle;

    @Excel(name = "职级\n代码", sort = 12)
    private String workTitleCode;

    @Excel(name = "任现职级\n时间", sort = 25, width = 10)
    private String workTitleTime;

    @Excel(name = "任同级职\n级时间", sort = 14, width = 10)
    private String sameWorkTitleTime;

    @Excel(name = "办公电话", sort = 18)
    private String officeTel;

    @Excel(name = "主要\n负责\n人", sort = 17, dictType = "yes_no_num", width = 8)
    private String isMainLeader;

    @Excel(name = "进入省\n本单位时间", sort = 23, width = 10)
    private String joinOrgTime;

    //单位：年
    @Excel(name = "基层经历", sort = 26, width = 5)
    private String grassrootsWorkTime;

    //@Excel(name = "正科\n任职时间", sort = 40, width = 10)
    private String sectionChiefTime;

    //@Excel(name = "在本处室\n工作时间", sort = 41)
    private String joinDeptTime;

    //@Excel(name = "婚否", sort = 42, dictType = "yes_no_num",width = 8)
    private String maritalStatus;

    //@Excel(name = "是否军转", sort = 43, dictType = "yes_no_num", width = 10)
    private String isVeterans;

    @Excel(name = "学历", sort = 6, width = 8)
    private String education;

    private String degree;

    //人员状态
    private String personnelStatus;

    //导出时用到字段---开始
    //@Excel(name = "毕业院校", sort = 27)
    private String schoolAndDepartmentDx;
    //@Excel(name = "专业", sort = 28)
    private String professionalNameDx;
    //@Excel(name = "毕业\n时间", sort = 29, width = 10)
    private String completionDate;
    //@Excel(name = "国民系\n列学历", sort = 30)
    private String educationGm;
    //@Excel(name = "国民系列院\n校", sort = 31)
    private String schoolAndDepartmentGm;
    //@Excel(name = "国民系列\n专业", sort = 32)
    private String professionalNameGm;
    //@Excel(name = "职称\n资格", sort = 33, width = 10)
    private String technicalQualificationName;
    //@Excel(name = "获得资\n格时间", sort = 34, width = 10)
    private String qualificationDate;
    //导出时用到字段---结束

    //统计局项目新增字段---结束

    private List<UserDeptPost> userDeptPostList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<UserDeptPost> getUserDeptPostList() {
        return userDeptPostList;
    }

    public void setUserDeptPostList(List<UserDeptPost> userDeptPostList) {
        this.userDeptPostList = userDeptPostList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getPoliticalIdentity() {
        return politicalIdentity;
    }

    public void setPoliticalIdentity(String politicalIdentity) {
        this.politicalIdentity = politicalIdentity;
    }

    public String getPartyJoinTime() {
        return partyJoinTime;
    }

    public void setPartyJoinTime(String partyJoinTime) {
        this.partyJoinTime = partyJoinTime;
    }

    public String getStartWorkTime() {
        return startWorkTime;
    }

    public void setStartWorkTime(String startWorkTime) {
        this.startWorkTime = startWorkTime;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
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

    public String getWorkPostCode() {
        return workPostCode;
    }

    public void setWorkPostCode(String workPostCode) {
        this.workPostCode = workPostCode;
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

    public String getSameWorkTitleTime() {
        return sameWorkTitleTime;
    }

    public void setSameWorkTitleTime(String sameWorkTitleTime) {
        this.sameWorkTitleTime = sameWorkTitleTime;
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }

    public String getIsMainLeader() {
        return isMainLeader;
    }

    public void setIsMainLeader(String isMainLeader) {
        this.isMainLeader = isMainLeader;
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

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
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

    public String getSchoolAndDepartmentDx() {
        return schoolAndDepartmentDx;
    }

    public void setSchoolAndDepartmentDx(String schoolAndDepartmentDx) {
        this.schoolAndDepartmentDx = schoolAndDepartmentDx;
    }

    public String getProfessionalNameDx() {
        return professionalNameDx;
    }

    public void setProfessionalNameDx(String professionalNameDx) {
        this.professionalNameDx = professionalNameDx;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    public String getEducationGm() {
        return educationGm;
    }

    public void setEducationGm(String educationGm) {
        this.educationGm = educationGm;
    }

    public String getSchoolAndDepartmentGm() {
        return schoolAndDepartmentGm;
    }

    public void setSchoolAndDepartmentGm(String schoolAndDepartmentGm) {
        this.schoolAndDepartmentGm = schoolAndDepartmentGm;
    }

    public String getProfessionalNameGm() {
        return professionalNameGm;
    }

    public void setProfessionalNameGm(String professionalNameGm) {
        this.professionalNameGm = professionalNameGm;
    }

    public String getTechnicalQualificationName() {
        return technicalQualificationName;
    }

    public void setTechnicalQualificationName(String technicalQualificationName) {
        this.technicalQualificationName = technicalQualificationName;
    }

    public String getQualificationDate() {
        return qualificationDate;
    }

    public void setQualificationDate(String qualificationDate) {
        this.qualificationDate = qualificationDate;
    }

    public String getPersonnelStatus() {
        return personnelStatus;
    }

    public void setPersonnelStatus(String personnelStatus) {
        this.personnelStatus = personnelStatus;
    }

}
