package com.cb.common.core.domain.entity;

import com.cb.common.annotation.EncryptField;
import com.cb.common.annotation.Excel;
import com.cb.common.annotation.Excels;
import com.cb.common.core.domain.BaseEntity;
import com.cb.common.enums.EncryptType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * 用户对象 sys_user
 *
 * @author ruoyi
 */
public class SysUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Excel(name = "用户序号", cellType = Excel.ColumnType.NUMERIC, prompt = "用户编号")
    private Long userId;

    /**
     * 部门ID
     */
    @Excel(name = "部门编号", type = Excel.Type.IMPORT)
    private Long deptId;
    /**
     * 关联部门表查询部门
     */
     private String deptName;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * 用户账号
     */
    @Excel(name = "登录名称")
    private String userName;

    /**
     * 用户昵称
     */
    @Excel(name = "用户名称")
    private String nickName;

    /**
     * 用户邮箱
     */
    @Excel(name = "用户邮箱")
    private String email;

    /**
     * 手机号码
     */
    @Excel(name = "手机号码")
    @EncryptField(fieldType = EncryptType.SM4)
    private String phonenumber;

    /**
     * 用户性别
     */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /**
     * 招录批次
     */
    @Excel(name = "招录批次")
    private String recruitLot;

    public String getRecruitLot() {
        return recruitLot;
    }

    public void setRecruitLot(String recruitLot) {
        this.recruitLot = recruitLot;
    }

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐加密
     */
    private String salt;

    /**
     * 帐号状态（0正常 1停用）
     */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 最后登录IP
     */
    @Excel(name = "最后登录IP", type = Excel.Type.EXPORT)
    private String loginIp;

    /**
     * 最后登录时间
     */
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    private Date loginDate;

    /**
     * 部门对象
     */
    @Excels({
            @Excel(name = "部门名称", targetAttr = "deptName", type = Excel.Type.EXPORT),
            @Excel(name = "部门负责人", targetAttr = "leader", type = Excel.Type.EXPORT)
    })
    private SysDept dept;

    /**
     * 角色对象
     */
    private List<SysRole> roles;

    /**
     * 角色组
     */
    private Long[] roleIds;

    /**
     * 岗位组
     */
    private Long[] postIds;
    /**
     * 用户ID数组
     */
    private Long[] userIds;



    /**
     * 用户类型 00 普通用户 01 系统管理员 02 安全管理员 03 审计员
     */
    private String userType;

    @Excel(name = "涉密等级", readConverterExp = "0=非涉密,1=秘密级,2=机密级,3=绝密级")
    private Integer secretLevel;

    /**排序*/
    private Integer sequence;

   /**干部能力标签*/
    private  String  abilityLabel;

    /**其他标签*/
    private String otherLabel;

    public String getReserveUser() {
        return reserveUser;
    }

    public void setReserveUser(String reserveUser) {
        this.reserveUser = reserveUser;
    }

    private String abilityLabelId;

    private String [] abilityLabelIds;

    public String[] getAbilityLabelIds() {
        return abilityLabelIds;
    }

    public void setAbilityLabelIds(String[] abilityLabelIds) {
        this.abilityLabelIds = abilityLabelIds;
    }

    /**
     * 优点
     */
    private  String  advantages;
    /**
     * 缺点
     */
    private  String disadvantages;

    public String getDisadvantages() {
        return disadvantages;
    }

    public void setDisadvantages(String disadvantages) {
        this.disadvantages = disadvantages;
    }

    public String getAdvantages() {
        return advantages;
    }

    public void setAdvantages(String advantages) {
        this.advantages = advantages;
    }

    public String getAbilityLabelId() {
        return abilityLabelId;
    }

    public void setAbilityLabelId(String abilityLabelId) {
        this.abilityLabelId = abilityLabelId;
    }

    public String getOtherLabel() {
        return otherLabel;
    }

    public void setOtherLabel(String otherLabel) {
        this.otherLabel = otherLabel;
    }

    public String  getAbilityLabel() {
        return abilityLabel;
    }

    public void setAbilityLabel(String abilityLabel) {
        this.abilityLabel = abilityLabel;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    //统计局项目新增字段---开始
    @Excel(name = "姓名")
    private String name;

    @Excel(name = "出生年月")
    private String birthday;

    @Excel(name = "民族")
    private String nation;

    @Excel(name = "籍贯")
    private String nativePlace;

    @Excel(name = "出生地")
    private String birthPlace;

    @Excel(name = "政治面貌")
    private String politicalIdentity;

    @Excel(name = "入党时间")
    private String partyJoinTime;

    @Excel(name = "第二党派")
    private String secondParty;

    @Excel(name = "第三党派")
    private String thirdParty;

    @Excel(name = "参加工作时间")
    private String startWorkTime;

    @Excel(name = "健康状况")
    private String healthCondition;

    @Excel(name = "专业特长")
    private String speciality;

    @Excel(name = "身份证号码")
    private String idcard;

    @Excel(name = "管理类别")
    private String manageType;

    @Excel(name = "人员类别")
    private String staffType;

    @Excel(name = "编制类型")
    private String identityType;

    @Excel(name = "现职务")
    private String workPost;

    @Excel(name = "现职务代码")
    private String workPostCode;

    @Excel(name = "任现职务时间")
    private String workPostTime;

    @Excel(name = "任同级职务时间")
    private String sameWorkPostTime;

    @Excel(name = "现职级")
    private String workTitle;

    @Excel(name = "现职级代码")
    private String workTitleCode;

    @Excel(name = "任现职级时间")
    private String workTitleTime;

    @Excel(name = "任同级职级时间")
    private String sameWorkTitleTime;

    @Excel(name = "是否考录")
    private String isEnrollment;

    @Excel(name = "录用审批时间")
    private String enrollmentTime;

    @Excel(name = "是否选调生")
    private String isSelectedStudent;

    @Excel(name = "进入选调生时间")
    private String selectedStudentTime;

    @Excel(name = "成长地")
    private String growPlace;

    @Excel(name = "级别")
    private String level;

    @Excel(name = "任职资格")
    private String qualifications;

    @Excel(name = "保留待遇")
    private String treatment;

    @Excel(name = "办公电话")
    private String officeTel;

    @Excel(name = "是否主要负责人")
    private String isMainLeader;

    @Excel(name = "进入本单位时间")
    private String joinOrgTime;

    //单位：年
    @Excel(name = "基层经历时间")
    private String grassrootsWorkTime;

    @Excel(name = "正科任职时间")
    private String sectionChiefTime;

    @Excel(name = "进入本处室时间")
    private String joinDeptTime;

    @Excel(name = "婚否")
    private String maritalStatus;

    @Excel(name = "是否军转")
    private String isVeterans;

    private String education;

    private String degree;
    /**
     * 人员状态（1-在职，2-借调，3-退休，4-离职，5-其他）
     * */
    @Excel(name = "人员状态")
    private String personnelStatus;

    @Excel(name= "借调单位")
    private String lendingUnits;

    @Excel(name="退休时间")
    private String retirementTime;

    private String signImg;

    /**
     * 是否主持工作（1-是，0-否）
     * */
    private String isHostingWork;

    //统计局项目新增字段---结束

    @Transient
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastPwdModifyTime;

    /**
     * 以下为市纪委新增字段
     * @return
     */
    @Excel(name="专业技术职务")
    private String professionalDuty;

    @Excel(name="全日制教育")
    private String fullTimeEducationLevel;

    @Excel(name="全日制教育-毕业院校系及专业")
    private String fullTimeEducationSchoolAndMajor;

    @Excel(name="在职教育")
    private String onJobEducationLevel;

    @Excel(name="在职教育-毕业院校系及专业")
    private String onJobEducationSchoolAndMajor;

    @Excel(name="现任职务")
    private String currentPosition;

    @Excel(name="职务简称")
    private String positionShort;

    @Excel(name="拟任职务")
    private String proposedAppointmentPosition;

    @Excel(name="拟免职务")
    private String proposedRemovalPosition;

//    @Excel(name="简历")
    private String resumeJsonArray;

    @Excel(name="奖惩情况")
    private String rewardAndPunishment;

    @Excel(name="年度考核结果")
    private String annualAssessment;

    @Excel(name="任免理由")
    private String reasonForAppointmentOrRemoval;

    /**储备干部*/
    @Excel(name = "储备干部", readConverterExp = "1=处级储备干部,2=副处级储备干部,3=科级储备干部")
    private String  reserveUser;
    private String[] reserveUsers;
    /**
     * 职务
     */
    private String userDuty;

    /**
     * 职级
     * @return
     */
    private String userRank;

    /**
     * 职务职级类型  1- 机关 2-巡察 3-派驻
     * @return
     */
    private String dutyRankType;

    public String getDutyRankType() {
        return dutyRankType;
    }

    public void setDutyRankType(String dutyRankType) {
        this.dutyRankType = dutyRankType;
    }

    public String getUserRank() {
        return userRank;
    }

    public void setUserRank(String userRank) {
        this.userRank = userRank;
    }

    public String getUserDuty() {
        return userDuty;
    }

    public void setUserDuty(String userDuty) {
        this.userDuty = userDuty;
    }

    public String[] getReserveUsers() {
        return reserveUsers;
    }

    public void setReserveUsers(String[] reserveUsers) {
        this.reserveUsers = reserveUsers;
    }

    //    @Excel(name="家庭成员")
    private String familyMemberJsonArray;

    public String getSignImg() {
        return signImg;
    }

    public void setSignImg(String signImg) {
        this.signImg = signImg;
    }

    private List<UserDeptPost> userDeptPostList;
    public SysUser() {

    }

    public SysUser(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isAdmin() {
        return isAdmin(this.userId);
    }

    public boolean isSecAdmin() {
        return isSecAdmin(this.userId);
    }

    public boolean isAudAdmin() {
        return isAudAdmin(this.userId);
    }

    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }
    public static boolean isSecAdmin(Long userId) {
        return userId != null && 2L == userId;
    }
    public static boolean isAudAdmin(Long userId) {
        return userId != null && 3L == userId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    @Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符")
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /*@NotBlank(message = "用户账号不能为空")*/
    @Size(min = 0, max = 30, message = "用户账号长度不能超过30个字符")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
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

    @JsonIgnore
    @JsonProperty
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public SysDept getDept() {
        return dept;
    }

    public void setDept(SysDept dept) {
        this.dept = dept;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }

    public Long[] getPostIds() {
        return postIds;
    }

    public void setPostIds(Long[] postIds) {
        this.postIds = postIds;
    }

    public Long[] getUserIds() {
        return userIds;
    }

    public void setUserIds(Long[] userIds) {
        this.userIds = userIds;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getSecretLevel() {
        return secretLevel;
    }

    public void setSecretLevel(Integer secretLevel) {
        this.secretLevel = secretLevel;
    }

    public Date getLastPwdModifyTime() {
        return lastPwdModifyTime;
    }

    public void setLastPwdModifyTime(Date lastPwdModifyTime) {
        this.lastPwdModifyTime = lastPwdModifyTime;
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

    /** 专业技术职务信息信息 */
    private List<SysUserTechnicalPositionInfo> positionInfos;

    public List<SysUserTechnicalPositionInfo> getPositionInfos() {
        return positionInfos;
    }

    public void setPositionInfos(List<SysUserTechnicalPositionInfo> positionInfos) {
        this.positionInfos = positionInfos;
    }

    /** 学历学位信息信息 */
    private List<SysUserEducationAndDegreeInfo> educationAndDegreeInfos;

    public List<SysUserEducationAndDegreeInfo> getEducationAndDegreeInfos() {
        return educationAndDegreeInfos;
    }

    public void setEducationAndDegreeInfos(List<SysUserEducationAndDegreeInfo> educationAndDegreeInfos) {
        this.educationAndDegreeInfos = educationAndDegreeInfos;
    }

    /** 奖惩信息信息 */
    private List<SysUserRewardsAndPenaltiesInfo> rewardsInfos;

    public List<SysUserRewardsAndPenaltiesInfo> getRewardsInfos() {
        return rewardsInfos;
    }

    public void setRewardsInfos(List<SysUserRewardsAndPenaltiesInfo> rewardsInfos) {
        this.rewardsInfos = rewardsInfos;
    }

    /** 用户其他信息信息 */
    /*private List<SysUserOtherInfo> sysUserOtherInfoList;

    public List<SysUserOtherInfo> getSysUserOtherInfoList() {
        return sysUserOtherInfoList;
    }

    public void setSysUserOtherInfoList(List<SysUserOtherInfo> sysUserOtherInfoList) {
        this.sysUserOtherInfoList = sysUserOtherInfoList;
    }*/

    /** 用户年度考核信息信息 */
    private List<SysUserAnnualAppraisalInfo> appraisalInfos;

    public List<SysUserAnnualAppraisalInfo> getAppraisalInfos() {
        return appraisalInfos;
    }

    public void setAppraisalInfos(List<SysUserAnnualAppraisalInfo> appraisalInfos) {
        this.appraisalInfos = appraisalInfos;
    }

    /** 家庭成员及主要社会关系信息信息 */
    private List<SysUserFamilyMemberSocialInfo> familyInfos;

    public List<SysUserFamilyMemberSocialInfo> getFamilyInfos() {
        return familyInfos;
    }

    public void setFamilyInfos(List<SysUserFamilyMemberSocialInfo> familyInfos) {
        this.familyInfos = familyInfos;
    }

    /** 用户简历信息信息 */
    private List<SysUserResumeInfo> sysUserResumeInfoList;

    public List<SysUserResumeInfo> getSysUserResumeInfoList() {
        return sysUserResumeInfoList;
    }

    public void setSysUserResumeInfoList(List<SysUserResumeInfo> sysUserResumeInfoList) {
        this.sysUserResumeInfoList = sysUserResumeInfoList;
    }

    /** 工作单位及职务信息信息 */
    private List<SysUserWorkUnitAndPositionInfo> sysUserWorkUnitAndPositionInfoList;

    public List<SysUserWorkUnitAndPositionInfo> getSysUserWorkUnitAndPositionInfoList() {
        return sysUserWorkUnitAndPositionInfoList;
    }

    public void setSysUserWorkUnitAndPositionInfoList(List<SysUserWorkUnitAndPositionInfo> sysUserWorkUnitAndPositionInfoList) {
        this.sysUserWorkUnitAndPositionInfoList = sysUserWorkUnitAndPositionInfoList;
    }

    /** 用户现职级信息信息 */
    private List<SysUserCurrentPostInfo> currentPostInfos;

    public List<SysUserCurrentPostInfo> getCurrentPostInfos() {
        return currentPostInfos;
    }

    public void setCurrentPostInfos(List<SysUserCurrentPostInfo> currentPostInfos) {
        this.currentPostInfos = currentPostInfos;
    }

    /** 用户基层工作经历信息信息 */
    private List<SysUserGrassrootsWorkInfo> grassrootsWorkInfos;

    public List<SysUserGrassrootsWorkInfo> getGrassrootsWorkInfos() {
        return grassrootsWorkInfos;
    }

    public void setGrassrootsWorkInfos(List<SysUserGrassrootsWorkInfo> grassrootsWorkInfos) {
        this.grassrootsWorkInfos = grassrootsWorkInfos;
    }

    /** 借调工作人员信息 */
    private List<SysUserSecondmentWorkInfo> secondmentWorkInfos;

    public List<SysUserSecondmentWorkInfo> getSecondmentWorkInfos() {
        return secondmentWorkInfos;
    }

    public void setSecondmentWorkInfos(List<SysUserSecondmentWorkInfo> secondmentWorkInfos) {
        this.secondmentWorkInfos = secondmentWorkInfos;
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

    public String getPersonnelStatus() {
        return personnelStatus;
    }

    public void setPersonnelStatus(String personnelStatus) {
        this.personnelStatus = personnelStatus;
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

    public String getIsHostingWork() {
        return isHostingWork;
    }

    public void setIsHostingWork(String isHostingWork) {
        this.isHostingWork = isHostingWork;
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

    public String getPositionShort() {
        return positionShort;
    }

    public void setPositionShort(String positionShort) {
        this.positionShort = positionShort;
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

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userId", getUserId())
                .append("deptId", getDeptId())
                .append("userName", getUserName())
                .append("nickName", getNickName())
                .append("email", getEmail())
                .append("phonenumber", getPhonenumber())
                .append("sex", getSex())
                .append("avatar", getAvatar())
                .append("password", getPassword())
                .append("salt", getSalt())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .append("loginIp", getLoginIp())
                .append("loginDate", getLoginDate())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("dept", getDept())
                .append("userType", getUserType())
                .append("personnelStatus", getPersonnelStatus())
                .append("lendingUnits", getLendingUnits())
                .append("retirementTime",getRetirementTime())
                .append("signImg",getSignImg())
                .append("isHostingWork", getIsHostingWork())
                .append("sysUserResumeInfoList", getSysUserResumeInfoList())
                .append("sysUserWorkUnitAndPositionInfoList", getSysUserWorkUnitAndPositionInfoList())
                .toString();
    }
}
