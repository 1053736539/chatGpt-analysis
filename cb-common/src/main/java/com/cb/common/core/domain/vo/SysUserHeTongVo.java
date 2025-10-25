package com.cb.common.core.domain.vo;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Size;

/**
 * 用户对象 sys_user
 *
 * @author ruoyi
 */
public class SysUserHeTongVo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
//    @Excel(name = "用户序号", cellType = Excel.ColumnType.NUMERIC)
    private Long userId;

    /**
     * 编制类型
     */
    @Excel(name = "编制类型", readConverterExp = "1=行政,2=参公,3=事业,4=企业,5=合同")
    private String identityType;

    /**
     * 部门
     */
    @Excel(name = "部门", type = Excel.Type.IMPORT)
     private String deptName;
    /**
     * 用户昵称
     */
    @Excel(name = "姓名")
    private String nickName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生年月",dateFormat = "yyyy-MM-dd")
    private String birthday;

    /**
     * 用户性别
     */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    @Excel(name = "政治面貌")
    private String politicalIdentity;

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }


    public String getPoliticalIdentity() {
        return politicalIdentity;
    }

    public void setPoliticalIdentity(String politicalIdentity) {
        this.politicalIdentity = politicalIdentity;
    }

    @Excel(name="学历")
    private String fullTimeEducationLevel;

    public String getFullTimeEducationSchool() {
        return fullTimeEducationSchool;
    }

    public void setFullTimeEducationSchool(String fullTimeEducationSchool) {
        this.fullTimeEducationSchool = fullTimeEducationSchool;
    }

    @Excel(name="毕业学校")
    private String fullTimeEducationSchool;

    @Excel(name="专业")
    private String major;

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Excel(name = "民族")
    private String nation;

    @Excel(name = "移动电话")
    private String  phonenumber;

    @Excel(name = "身份证号码")
    private String idcard;

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    @JsonFormat(pattern = "yyyy-MM")
    @Excel(name = "进入单位时间", dateFormat = "yyyy-MM")
    private String startWorkTime;

    /**
     * 招录批次
     */
    @Excel(name = "招录批次")
    private String recruitLot;

    @Excel(name = "备注")
    private String remarkHeTong;

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getRemarkHeTong() {
        return remarkHeTong;
    }

    public void setRemarkHeTong(String remarkHeTong) {
        this.remarkHeTong = remarkHeTong;
    }

    public String getRecruitLot() {
        return recruitLot;
    }

    public void setRecruitLot(String recruitLot) {
        this.recruitLot = recruitLot;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符")
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }


    public String getStartWorkTime() {
        return startWorkTime;
    }

    public void setStartWorkTime(String startWorkTime) {
        this.startWorkTime = startWorkTime;
    }


    public String getFullTimeEducationLevel() {
        return fullTimeEducationLevel;
    }

    public void setFullTimeEducationLevel(String fullTimeEducationLevel) {
        this.fullTimeEducationLevel = fullTimeEducationLevel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userId", getUserId())
                .append("nickName", getNickName())
                .append("sex", getSex())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
