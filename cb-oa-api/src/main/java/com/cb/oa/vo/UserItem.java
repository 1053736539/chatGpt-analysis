package com.cb.oa.vo;

/**
 * @Description:
 * @Author ARPHS
 * @Date 2023/11/30 11:38
 */
public class UserItem {
    private String id;//ID
    private String username;//用户名
    private String usernameLetter;
    private String nickName;//昵称
    private Object officeTel;//办公室电话
    private String shortNumber;//短号码
    private String userAppellation;//称谓
    private String userPost;//职务
    private String officeAddress;//办公地点
    private String gender;//性别
    private String phone;//手机号码
    private String email;//邮箱
    private Boolean enabled;//状态：1 启用、0 禁用
    private String mainDeptid;//部门 id
    private String pinyin;//名称拼音
    private Integer csort;//排序

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameLetter() {
        return usernameLetter;
    }

    public void setUsernameLetter(String usernameLetter) {
        this.usernameLetter = usernameLetter;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Object getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(Object officeTel) {
        this.officeTel = officeTel;
    }

    public String getShortNumber() {
        return shortNumber;
    }

    public void setShortNumber(String shortNumber) {
        this.shortNumber = shortNumber;
    }

    public String getUserAppellation() {
        return userAppellation;
    }

    public void setUserAppellation(String userAppellation) {
        this.userAppellation = userAppellation;
    }

    public String getUserPost() {
        return userPost;
    }

    public void setUserPost(String userPost) {
        this.userPost = userPost;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getMainDeptid() {
        return mainDeptid;
    }

    public void setMainDeptid(String mainDeptid) {
        this.mainDeptid = mainDeptid;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public Integer getCsort() {
        return csort;
    }

    public void setCsort(Integer csort) {
        this.csort = csort;
    }
}
