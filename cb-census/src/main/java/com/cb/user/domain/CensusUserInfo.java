package com.cb.user.domain;

import com.cb.common.annotation.EncryptField;
import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import com.cb.common.enums.EncryptType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 普查人员对象 census_user_info
 * 
 * @author ruoyi
 * @date 2023-10-20
 */
public class CensusUserInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户id */
    private String userId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String userName;

    /** 性别，存字典值 */
    @Excel(name = "性别", dictType = "sys_user_sex")
    private String gender;

    /** 电话 */
    @Excel(name = "电话")
    @EncryptField(fieldType = EncryptType.SM4)
    private String phone;

    /**角色
     */
    @Excel(name = "角色")
    private String roleName;

    /** 单位 */
    @Excel(name = "所属机构")
    private String unitInfo;

    @Excel(name = "所属机构码")
    private Long deptCode;

    /** 账号状态 */
    @Excel(name = "账号状态")
    private String accountStatus;

    /** 身份类别 */
    @Excel(name = "身份类别")
    private String identityType;

    /** 学历，暂存字典 */
    @Excel(name = "教育情况")
    private String education;

    /** 出生年 */
    @Excel(name = "出生年")
    private Integer birthYear;

    /** 工作单位 */
    @Excel(name = "工作单位")
    private String workUnit;

    /** 全职工作年限*/
    @Excel(name = "全职工作年限")
    private Integer workYear;

    /** 统计工作年限*/
    @Excel(name = "统计工作年限")
    private Integer statisticsYear;

    /** 所属辖区 */
    @Excel(name = "所属辖区")
    private String regionalDivision;

    /** 是否负责投入产出任务*/
    @Excel(name = "是否负责投入产出任务")
    private String isTask;


    /** 投入产出角色*/
    @Excel(name = "投入产出角色")
    private String taskRole;


    /** 参加调查类型*/
    @Excel(name = "参加调查类型")
    private String surveyType;


    /** 调查经验说明*/
    @Excel(name = "调查经验说明")
    private String surveyDesc;


    /** 选调情况*/
    @Excel(name = "选调情况")
    private Integer chooseDesc;


    /** 工作岗位*/
    @Excel(name = "工作岗位")
    private Integer workPosition;


    /** 普查小区*/
    @Excel(name = "普查小区")
    private String censusAreas;


    /** 民族，暂存字典 */
    private String nation;

    /** 籍贯 */
    private String nativePlace;

    /** 政治面貌 */
    private String politicalOutlook;

    /** 职务 */
    private String duties;

    /** 职级 */
    private String rank;

    /** 生日 */
    private String birthday;

    /** 参加工作时间 */
    private String enterWorkTime;

    /** 毕业院校 */
    private String graduationInstitution;

    /** 专业 */
    private String speciality;

    /** 职称资格 */
    private String titleQualification;

    /** 编制类别,暂定存字典 */
    private String preparationCategory;

    /** 身份证号 */
    @EncryptField(fieldType = EncryptType.SM4)
    private String idNumber;

    /** 删除标志 */
    private String delFlag;

    private Long censusDeptId;

    private String avatar;

    /** 普查次数 */
    private Integer censusNumber;


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setCensusDeptId(Long censusDeptId)
    {
        this.censusDeptId = censusDeptId;
    }

    public Long getCensusDeptId()
    {
        return censusDeptId;
    }


    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }

    public String getUnitInfo() {
        return unitInfo;
    }

    public void setUnitInfo(String unitInfo) {
        this.unitInfo = unitInfo;
    }

    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }
    public void setNation(String nation) 
    {
        this.nation = nation;
    }

    public String getNation() 
    {
        return nation;
    }
    public void setNativePlace(String nativePlace) 
    {
        this.nativePlace = nativePlace;
    }

    public String getNativePlace() 
    {
        return nativePlace;
    }
    public void setPoliticalOutlook(String politicalOutlook) 
    {
        this.politicalOutlook = politicalOutlook;
    }

    public String getPoliticalOutlook() 
    {
        return politicalOutlook;
    }
    public void setDuties(String duties) 
    {
        this.duties = duties;
    }

    public String getDuties() 
    {
        return duties;
    }
    public void setRank(String rank) 
    {
        this.rank = rank;
    }

    public String getRank() 
    {
        return rank;
    }
    public void setBirthday(String birthday) 
    {
        this.birthday = birthday;
    }

    public String getBirthday() 
    {
        return birthday;
    }
    public void setEnterWorkTime(String enterWorkTime) 
    {
        this.enterWorkTime = enterWorkTime;
    }

    public String getEnterWorkTime() 
    {
        return enterWorkTime;
    }
    public void setEducation(String education) 
    {
        this.education = education;
    }

    public String getEducation() 
    {
        return education;
    }
    public void setGraduationInstitution(String graduationInstitution) 
    {
        this.graduationInstitution = graduationInstitution;
    }

    public String getGraduationInstitution() 
    {
        return graduationInstitution;
    }
    public void setSpeciality(String speciality) 
    {
        this.speciality = speciality;
    }

    public String getSpeciality() 
    {
        return speciality;
    }
    public void setTitleQualification(String titleQualification) 
    {
        this.titleQualification = titleQualification;
    }

    public String getTitleQualification() 
    {
        return titleQualification;
    }
    public void setPreparationCategory(String preparationCategory) 
    {
        this.preparationCategory = preparationCategory;
    }

    public String getPreparationCategory() 
    {
        return preparationCategory;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setIdNumber(String idNumber) 
    {
        this.idNumber = idNumber;
    }

    public String getIdNumber() 
    {
        return idNumber;
    }
    public void setRegionalDivision(String regionalDivision) 
    {
        this.regionalDivision = regionalDivision;
    }

    public String getRegionalDivision() 
    {
        return regionalDivision;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public Integer getCensusNumber() {
        return censusNumber;
    }

    public void setCensusNumber(Integer censusNumber) {
        this.censusNumber = censusNumber;
    }

    public Long getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(Long deptCode) {
        this.deptCode = deptCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public Integer getWorkYear() {
        return workYear;
    }

    public void setWorkYear(Integer workYear) {
        this.workYear = workYear;
    }

    public Integer getStatisticsYear() {
        return statisticsYear;
    }

    public void setStatisticsYear(Integer statisticsYear) {
        this.statisticsYear = statisticsYear;
    }

    public String getIsTask() {
        return isTask;
    }

    public void setIsTask(String isTask) {
        this.isTask = isTask;
    }

    public String getTaskRole() {
        return taskRole;
    }

    public void setTaskRole(String taskRole) {
        this.taskRole = taskRole;
    }

    public String getSurveyType() {
        return surveyType;
    }

    public void setSurveyType(String surveyType) {
        this.surveyType = surveyType;
    }

    public String getSurveyDesc() {
        return surveyDesc;
    }

    public void setSurveyDesc(String surveyDesc) {
        this.surveyDesc = surveyDesc;
    }

    public Integer getChooseDesc() {
        return chooseDesc;
    }

    public void setChooseDesc(Integer chooseDesc) {
        this.chooseDesc = chooseDesc;
    }

    public Integer getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(Integer workPosition) {
        this.workPosition = workPosition;
    }

    public String getCensusAreas() {
        return censusAreas;
    }

    public void setCensusAreas(String censusAreas) {
        this.censusAreas = censusAreas;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("unitInfo", getUnitInfo())
            .append("userName", getUserName())
            .append("gender", getGender())
            .append("nation", getNation())
            .append("nativePlace", getNativePlace())
            .append("politicalOutlook", getPoliticalOutlook())
            .append("duties", getDuties())
            .append("rank", getRank())
            .append("birthday", getBirthday())
            .append("enterWorkTime", getEnterWorkTime())
            .append("education", getEducation())
            .append("graduationInstitution", getGraduationInstitution())
            .append("speciality", getSpeciality())
            .append("titleQualification", getTitleQualification())
            .append("preparationCategory", getPreparationCategory())
            .append("phone", getPhone())
            .append("idNumber", getIdNumber())
            .append("regionalDivision", getRegionalDivision())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
