package com.cb.common.core.domain.vo;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import com.cb.common.core.domain.entity.UserDeptPost;

import java.io.Serializable;
import java.util.List;

public class ExportUserVo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    /*@Excel(name = "用户序号", cellType = Excel.ColumnType.NUMERIC, prompt = "用户编号")*/
    private Long userId;

    /**
     * 部门ID
     */
    @Excel(name = "部门编号", type = Excel.Type.IMPORT)
    private Long deptId;

    /**
     * 用户账号
     */
    /*@Excel(name = "登录名称")*/
    private String userName;

    /**
     * 用户昵称
     */
    /*@Excel(name = "用户名称")*/
    private String nickName;

    /**
     * 用户邮箱
     */
    /*@Excel(name = "用户邮箱")*/
    private String email;

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

    /**
     * 用户头像
     */
    private String avatar;


    /**
     * 帐号状态（0正常 1停用）
     */
    //@Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    @Excel(name = "处室", type = Excel.Type.EXPORT, sort = 1)
    private String deptName;

    @Excel(name = "处室\n代码", type = Excel.Type.EXPORT, sort = 16,width = 10)
    private String deptCode;

    /**
     * 用户类型 00 普通用户 01 系统管理员 02 安全管理员 03 审计员
     */
    private String userType;

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

    //@Excel(name = "第二党派")
    private String secondParty;

    //@Excel(name = "第三党派")
    private String thirdParty;

    @Excel(name = "参加工\n作时间",sort = 7, width = 10)
    private String startWorkTime;

    //@Excel(name = "健康状况")
    private String healthCondition;

    //@Excel(name = "专业特长")
    private String speciality;

    @Excel(name = "身份证号",sort = 20, width = 18)
    private String idcard;

    //@Excel(name = "管理类别")
    private String manageType;

    //@Excel(name = "人员类别")
    private String staffType;

    @Excel(name = "编制",sort = 15, readConverterExp = "1=行政,2=参公,3=事业,4=企业", width = 5) //dictType = "preparation_type"
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

    //@Excel(name = "是否考录")
    private String isEnrollment;

    //@Excel(name = "录用审批时间")
    private String enrollmentTime;

    //@Excel(name = "是否选调生")
    private String isSelectedStudent;

    //@Excel(name = "进入选调生时间")
    private String selectedStudentTime;

    //@Excel(name = "成长地")
    private String growPlace;

    //@Excel(name = "级别")
    private String level;

    //@Excel(name = "任职资格")
    private String qualifications;

    //@Excel(name = "保留待遇")
    private String treatment;

    @Excel(name = "办公电话", sort = 18)
    private String officeTel;

    @Excel(name = "主要\n负责\n人", sort = 17, readConverterExp = "0=否,1=是", width = 8)//dictType = "yes_no_num"
    private String isMainLeader;

    @Excel(name = "进入省\n本单位时间", sort = 23, width = 10)
    private String joinOrgTime;

    //单位：年
    @Excel(name = "基层经历", sort = 26, width = 5)
    private String grassrootsWorkTime;

    @Excel(name = "正科\n任职时间", sort = 40, width = 10)
    private String sectionChiefTime;

    @Excel(name = "在本处室\n工作时间", sort = 41)
    private String joinDeptTime;

    @Excel(name = "婚否", sort = 42, readConverterExp = "0=否,1=是",width = 8)//dictType = "yes_no_num"
    private String maritalStatus;

    @Excel(name = "是否军转", sort = 43, readConverterExp = "0=否,1=是", width = 10)//dictType = "yes_no_num"
    private String isVeterans;

    @Excel(name = "学历", sort = 6, width = 8)
    private String education;

    private String degree;

    /**
     * 招录批次
     */
    private String recruitLot;

    /**
     * 涉密等级
     */
    private Integer secretLevel;

    /**
     * 排序
     */
    private Integer sequence;

    /**
     * 借调单位
     */
    private String lendingUnits;

    /**
     * 退休时间
     */
    private String retirementTime;

    /**
     * 人员状态（1-在职，2-借调，3-退休，4-离职，5-其他）
     * */
    private String personnelStatus;

    //导出时用到字段---开始
    @Excel(name = "毕业院校", sort = 27)
    private String schoolAndDepartmentDx;
    @Excel(name = "专业", sort = 28)
    private String professionalNameDx;
    @Excel(name = "毕业\n时间", sort = 29, width = 10)
    private String completionDate;
    @Excel(name = "国民系\n列学历", sort = 30)
    private String educationGm;
    @Excel(name = "国民系列院\n校", sort = 31)
    private String schoolAndDepartmentGm;
    @Excel(name = "国民系列\n专业", sort = 32)
    private String professionalNameGm;
    @Excel(name = "职称\n资格", sort = 33, width = 10)
    private String technicalQualificationName;
    @Excel(name = "获得资\n格时间", sort = 34, width = 10)
    private String qualificationDate;

    /**储备干部*/
    @Excel(name = "储备干部",sort = 35, width = 10, readConverterExp = "1=处级储备干部,2=副处级储备干部,3=科级储备干部")
    private String  reserveUser;
    //导出时用到字段---结束

    //统计局项目新增字段---结束

    private List<UserDeptPost> userDeptPostList;

    /*干部能力标签*/
    @Excel(name = "干部标签", sort = 36)
    private  String  abilityLabel;

    /*其他标签*/
    private String otherLabel;

    private String abilityLabelId;

    private String [] abilityLabelIds;

    /** 储备干部（多选筛选） */
    private String[] reserveUsers;

    /** 优点 */
    private String advantages;

    /** 缺点 */
    private String disadvantages;

    /** 现任职务 */
    private String currentPosition;

    /** 职务简称 */
    private String positionShort;

    /** 拟任职务 */
    private String proposedAppointmentPosition;

    /** 拟免职务 */
    private String proposedRemovalPosition;

    /** 简历 */
    private String resumeJsonArray;

    /** 奖惩情况 */
    private String rewardAndPunishment;

    /** 年度考核结果 */
    private String annualAssessment;

    /** 任免理由 */
    private String reasonForAppointmentOrRemoval;

    /** 家庭成员 */
    private String familyMemberJsonArray;

    /** 职务 */
    private String userDuty;

    /** 职级 */
    private String userRank;

    /** 职务职级类型 */
    private String dutyRankType;

    /** 专业技术职务 */
    private String professionalDuty;

    /** 全日制教育 */
    private String fullTimeEducationLevel;

    /** 全日制教育-毕业院校系及专业 */
    private String fullTimeEducationSchoolAndMajor;

    /** 在职教育 */
    private String onJobEducationLevel;

    /** 在职教育-毕业院校系及专业 */
    private String onJobEducationSchoolAndMajor;

    /** 是否主持工作 */
    private String isHostingWork;

    /** 统计所在单位信息 */
    private String statisticalRelationUnit;

    /** 公务员登记时间 */
    private String civilServantRegisterTime;

    /** 是否有两年以上基层工作经历 */
    private String hasTwoYearsGrassrootsWork;



    public String getReserveUser() {
        return reserveUser;
    }

    public void setReserveUser(String reserveUser) {
        this.reserveUser = reserveUser;
    }

    public String getAbilityLabel() {
        return abilityLabel;
    }

    public void setAbilityLabel(String abilityLabel) {
        this.abilityLabel = abilityLabel;
    }

    public String getOtherLabel() {
        return otherLabel;
    }

    public void setOtherLabel(String otherLabel) {
        this.otherLabel = otherLabel;
    }

    public String getAbilityLabelId() {
        return abilityLabelId;
    }

    public void setAbilityLabelId(String abilityLabelId) {
        this.abilityLabelId = abilityLabelId;
    }

    public String[] getAbilityLabelIds() {
        return abilityLabelIds;
    }

    public void setAbilityLabelIds(String[] abilityLabelIds) {
        this.abilityLabelIds = abilityLabelIds;
    }

    public String getLendingUnits() {
        return lendingUnits;
    }

    public void setLendingUnits(String lendingUnits) {
        this.lendingUnits = lendingUnits;
    }

    public String getRetirementTime() {
        return retirementTime;
    }

    public void setRetirementTime(String retirementTime) {
        this.retirementTime = retirementTime;
    }

    public String[] getReserveUsers() {
        return reserveUsers;
    }

    public void setReserveUsers(String[] reserveUsers) {
        this.reserveUsers = reserveUsers;
    }

    public String getRecruitLot() {
        return recruitLot;
    }

    public void setRecruitLot(String recruitLot) {
        this.recruitLot = recruitLot;
    }

    public Integer getSecretLevel() {
        return secretLevel;
    }

    public void setSecretLevel(Integer secretLevel) {
        this.secretLevel = secretLevel;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getAdvantages() {
        return advantages;
    }

    public void setAdvantages(String advantages) {
        this.advantages = advantages;
    }

    public String getDisadvantages() {
        return disadvantages;
    }

    public void setDisadvantages(String disadvantages) {
        this.disadvantages = disadvantages;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getPositionShort() {
        return positionShort;
    }

    public void setPositionShort(String positionShort) {
        this.positionShort = positionShort;
    }

    public String getProposedAppointmentPosition() {
        return proposedAppointmentPosition;
    }

    public void setProposedAppointmentPosition(String proposedAppointmentPosition) {
        this.proposedAppointmentPosition = proposedAppointmentPosition;
    }

    public String getProposedRemovalPosition() {
        return proposedRemovalPosition;
    }

    public void setProposedRemovalPosition(String proposedRemovalPosition) {
        this.proposedRemovalPosition = proposedRemovalPosition;
    }

    public String getResumeJsonArray() {
        return resumeJsonArray;
    }

    public void setResumeJsonArray(String resumeJsonArray) {
        this.resumeJsonArray = resumeJsonArray;
    }

    public String getRewardAndPunishment() {
        return rewardAndPunishment;
    }

    public void setRewardAndPunishment(String rewardAndPunishment) {
        this.rewardAndPunishment = rewardAndPunishment;
    }

    public String getAnnualAssessment() {
        return annualAssessment;
    }

    public void setAnnualAssessment(String annualAssessment) {
        this.annualAssessment = annualAssessment;
    }

    public String getReasonForAppointmentOrRemoval() {
        return reasonForAppointmentOrRemoval;
    }

    public void setReasonForAppointmentOrRemoval(String reasonForAppointmentOrRemoval) {
        this.reasonForAppointmentOrRemoval = reasonForAppointmentOrRemoval;
    }

    public String getFamilyMemberJsonArray() {
        return familyMemberJsonArray;
    }

    public void setFamilyMemberJsonArray(String familyMemberJsonArray) {
        this.familyMemberJsonArray = familyMemberJsonArray;
    }

    public String getUserDuty() {
        return userDuty;
    }

    public void setUserDuty(String userDuty) {
        this.userDuty = userDuty;
    }

    public String getUserRank() {
        return userRank;
    }

    public void setUserRank(String userRank) {
        this.userRank = userRank;
    }

    public String getDutyRankType() {
        return dutyRankType;
    }

    public void setDutyRankType(String dutyRankType) {
        this.dutyRankType = dutyRankType;
    }

    public String getProfessionalDuty() {
        return professionalDuty;
    }

    public void setProfessionalDuty(String professionalDuty) {
        this.professionalDuty = professionalDuty;
    }

    public String getFullTimeEducationLevel() {
        return fullTimeEducationLevel;
    }

    public void setFullTimeEducationLevel(String fullTimeEducationLevel) {
        this.fullTimeEducationLevel = fullTimeEducationLevel;
    }

    public String getFullTimeEducationSchoolAndMajor() {
        return fullTimeEducationSchoolAndMajor;
    }

    public void setFullTimeEducationSchoolAndMajor(String fullTimeEducationSchoolAndMajor) {
        this.fullTimeEducationSchoolAndMajor = fullTimeEducationSchoolAndMajor;
    }

    public String getOnJobEducationLevel() {
        return onJobEducationLevel;
    }

    public void setOnJobEducationLevel(String onJobEducationLevel) {
        this.onJobEducationLevel = onJobEducationLevel;
    }

    public String getOnJobEducationSchoolAndMajor() {
        return onJobEducationSchoolAndMajor;
    }

    public void setOnJobEducationSchoolAndMajor(String onJobEducationSchoolAndMajor) {
        this.onJobEducationSchoolAndMajor = onJobEducationSchoolAndMajor;
    }

    public String getIsHostingWork() {
        return isHostingWork;
    }

    public void setIsHostingWork(String isHostingWork) {
        this.isHostingWork = isHostingWork;
    }

    public String getStatisticalRelationUnit() {
        return statisticalRelationUnit;
    }

    public void setStatisticalRelationUnit(String statisticalRelationUnit) {
        this.statisticalRelationUnit = statisticalRelationUnit;
    }

    public String getCivilServantRegisterTime() {
        return civilServantRegisterTime;
    }

    public void setCivilServantRegisterTime(String civilServantRegisterTime) {
        this.civilServantRegisterTime = civilServantRegisterTime;
    }

    public String getHasTwoYearsGrassrootsWork() {
        return hasTwoYearsGrassrootsWork;
    }

    public void setHasTwoYearsGrassrootsWork(String hasTwoYearsGrassrootsWork) {
        this.hasTwoYearsGrassrootsWork = hasTwoYearsGrassrootsWork;
    }

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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

    public String getSecondParty() {
        return secondParty;
    }

    public void setSecondParty(String secondParty) {
        this.secondParty = secondParty;
    }

    public String getThirdParty() {
        return thirdParty;
    }

    public void setThirdParty(String thirdParty) {
        this.thirdParty = thirdParty;
    }

    public String getStartWorkTime() {
        return startWorkTime;
    }

    public void setStartWorkTime(String startWorkTime) {
        this.startWorkTime = startWorkTime;
    }

    public String getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(String healthCondition) {
        this.healthCondition = healthCondition;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getManageType() {
        return manageType;
    }

    public void setManageType(String manageType) {
        this.manageType = manageType;
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
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

    public String getIsEnrollment() {
        return isEnrollment;
    }

    public void setIsEnrollment(String isEnrollment) {
        this.isEnrollment = isEnrollment;
    }

    public String getEnrollmentTime() {
        return enrollmentTime;
    }

    public void setEnrollmentTime(String enrollmentTime) {
        this.enrollmentTime = enrollmentTime;
    }

    public String getIsSelectedStudent() {
        return isSelectedStudent;
    }

    public void setIsSelectedStudent(String isSelectedStudent) {
        this.isSelectedStudent = isSelectedStudent;
    }

    public String getSelectedStudentTime() {
        return selectedStudentTime;
    }

    public void setSelectedStudentTime(String selectedStudentTime) {
        this.selectedStudentTime = selectedStudentTime;
    }

    public String getGrowPlace() {
        return growPlace;
    }

    public void setGrowPlace(String growPlace) {
        this.growPlace = growPlace;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
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
