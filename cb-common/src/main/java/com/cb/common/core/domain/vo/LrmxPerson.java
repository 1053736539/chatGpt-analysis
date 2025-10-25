package com.cb.common.core.domain.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

public class LrmxPerson implements Serializable {

    @JSONField(name = "ShenFenZheng")
    private String idcard;

    @JSONField(name = "XingMing")
    private String name;

    @JSONField(name = "XingBie")
    private String gender;

    @JSONField(name = "ChuShengNianYue")
    private String birthday;

    @JSONField(name = "MinZu")
    private String nation;

    @JSONField(name = "JiGuan")
    private String nativePlace;

    @JSONField(name = "ChuShengDi")
    private String birthPlace;

    @JSONField(name = "CanJiaGongZuoShiJian")
    private String startWorkTime;

    @JSONField(name = "JianKangZhuangKuang")
    private String healthCondition;

    @JSONField(name = "ZhuanYeJiShuZhiWu")
    private String position;

    @JSONField(name = "ShuXiZhuanYeYouHeZhuanChang")
    private String speciality;

    @JSONField(name = "QuanRiZhiJiaoYu_XueLi")
    private String xueLi1;

    @JSONField(name = "QuanRiZhiJiaoYu_XueWei")
    private String xueWei1;

    @JSONField(name = "QuanRiZhiJiaoYu_XueLi_BiYeYuanXiaoXi")
    private String school1;

    @JSONField(name = "ZaiZhiJiaoYu_XueLi")
    private String xueLi2;

    @JSONField(name = "ZaiZhiJiaoYu_XueWei")
    private String xueWei2;

    @JSONField(name = "ZaiZhiJiaoYu_XueLi_BiYeYuanXiaoXi")
    private String school2;

    @JSONField(name = "XianRenZhiWu")
    private String curPosition;

    @JSONField(name = "JianLi")
    private String jianLi;

    @JSONField(name = "JiangChengQingKuang")
    private String rewards;

    @JSONField(name = "NianDuKaoHeJieGuo")
    private String assessResult;
    @JSONField(name = "RuDangShiJian")
    private String partyJoinTime;

    @JSONField(name = "JiaTingChengYuan")
    private FamilyMember familyMember;

    private String deptId;

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getXueLi1() {
        return xueLi1;
    }

    public void setXueLi1(String xueLi1) {
        this.xueLi1 = xueLi1;
    }

    public String getXueWei1() {
        return xueWei1;
    }

    public void setXueWei1(String xueWei1) {
        this.xueWei1 = xueWei1;
    }

    public String getSchool1() {
        return school1;
    }

    public void setSchool1(String school1) {
        this.school1 = school1;
    }

    public String getXueLi2() {
        return xueLi2;
    }

    public void setXueLi2(String xueLi2) {
        this.xueLi2 = xueLi2;
    }

    public String getXueWei2() {
        return xueWei2;
    }

    public void setXueWei2(String xueWei2) {
        this.xueWei2 = xueWei2;
    }

    public String getSchool2() {
        return school2;
    }

    public void setSchool2(String school2) {
        this.school2 = school2;
    }

    public String getCurPosition() {
        return curPosition;
    }

    public void setCurPosition(String curPosition) {
        this.curPosition = curPosition;
    }

    public String getJianLi() {
        return jianLi;
    }

    public void setJianLi(String jianLi) {
        this.jianLi = jianLi;
    }

    public String getRewards() {
        return rewards;
    }

    public void setRewards(String rewards) {
        this.rewards = rewards;
    }

    public String getAssessResult() {
        return assessResult;
    }

    public void setAssessResult(String assessResult) {
        this.assessResult = assessResult;
    }

    public FamilyMember getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(FamilyMember familyMember) {
        this.familyMember = familyMember;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getPartyJoinTime() {
        return partyJoinTime;
    }

    public void setPartyJoinTime(String partyJoinTime) {
        this.partyJoinTime = partyJoinTime;
    }

    public static class FamilyMember {
        @JSONField(name = "Item")
        List<FamilyMemberInfo> memberList;

        public List<FamilyMemberInfo> getMemberList() {
            return memberList;
        }

        public void setMemberList(List<FamilyMemberInfo> memberList) {
            this.memberList = memberList;
        }
    }

    public static class FamilyMemberInfo {
        @JSONField(name = "XingMing")
        private String name;

        @JSONField(name = "ChengWei")
        private String title;

        @JSONField(name = "ChuShengRiQi")
        private String birthday;

        @JSONField(name = "ZhengZhiMianMao")
        private String politicalId;

        @JSONField(name = "GongZuoDanWeiJiZhiWu")
        private String work;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getPoliticalId() {
            return politicalId;
        }

        public void setPoliticalId(String politicalId) {
            this.politicalId = politicalId;
        }

        public String getWork() {
            return work;
        }

        public void setWork(String work) {
            this.work = work;
        }
    }
}
