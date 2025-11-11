package com.cb.vo;

import com.cb.common.core.domain.entity.SysUserEducationAndDegreeInfo;
import com.cb.domain.TransferringStudent;

import java.util.List;

public class StudentVo extends TransferringStudent {
    private String name;

    private String sex;

    //身份证号
    private String idcard;
    //籍贯
    private String nativePlace;
    //出生年月
    private String birthday;
    //联系方式
    private String phonenumber;
    //学历信息
    private List<SysUserEducationAndDegreeInfo> sysUserEducationAndDegreeInfoList;

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public List<SysUserEducationAndDegreeInfo> getSysUserEducationAndDegreeInfoList() {
        return sysUserEducationAndDegreeInfoList;
    }

    public void setSysUserEducationAndDegreeInfoList(List<SysUserEducationAndDegreeInfo> sysUserEducationAndDegreeInfoList) {
        this.sysUserEducationAndDegreeInfoList = sysUserEducationAndDegreeInfoList;
    }
}
