package com.cb.common.core.domain.vo;


import java.util.Date;

public class VSysUser {
    private Long userId;
    private Long deptId;
    private String deptName;
    private String userName;
    private String nickName;
    private String name;
    private String phonenumber;
    private String status;
    private String delFlag;
    private String userType;
    private String identityType;
    private String isMainLeader;
    private String isHostingWork;
    private String workLevelCode;
    private String personnelStatus;
    private String deptIdentityType;

    private Integer age;

    private Integer postTime;
    /**
     * {@link com.cb.assess.enums.PersonnelType#code}
     */
    private String levelType;

    private Long[] roleIds;


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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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

    public String getIsHostingWork() {
        return isHostingWork;
    }

    public void setIsHostingWork(String isHostingWork) {
        this.isHostingWork = isHostingWork;
    }

    public String getWorkLevelCode() {
        return workLevelCode;
    }

    public void setWorkLevelCode(String workLevelCode) {
        this.workLevelCode = workLevelCode;
    }

    public String getPersonnelStatus() {
        return personnelStatus;
    }

    public void setPersonnelStatus(String personnelStatus) {
        this.personnelStatus = personnelStatus;
    }

    public String getDeptIdentityType() {
        return deptIdentityType;
    }

    public void setDeptIdentityType(String deptIdentityType) {
        this.deptIdentityType = deptIdentityType;
    }

    public String getLevelType() {
        return levelType;
    }

    public void setLevelType(String levelType) {
        this.levelType = levelType;
    }

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getPostTime() {
        return postTime;
    }

    public void setPostTime(Integer postTime) {
        this.postTime = postTime;
    }
}
