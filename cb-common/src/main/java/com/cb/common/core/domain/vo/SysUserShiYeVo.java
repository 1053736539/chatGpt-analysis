package com.cb.common.core.domain.vo;

import com.cb.common.annotation.EncryptField;
import com.cb.common.annotation.Excel;
import com.cb.common.annotation.Excels;
import com.cb.common.core.domain.BaseEntity;
import com.cb.common.core.domain.entity.*;
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
public class SysUserShiYeVo extends BaseEntity {
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



    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    @Excel(name = "现职级")
    private String workTitle;

    @Excel(name = "任现职级时间", dateFormat = "yyyy-MM-dd")
    private String workTitleTime;
    /**
     * 用户性别
     */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生年月",dateFormat = "yyyy-MM-dd")
    private String birthday;

    @Excel(name = "民族")
    private String nation;

    @Excel(name="全日制教育")
    private String fullTimeEducationLevel;

    @Excel(name="全日制教育-毕业院校系及专业")
    private String fullTimeEducationSchoolAndMajor;

    @Excel(name="在职教育")
    private String onJobEducationLevel;

    @Excel(name="在职教育-毕业院校系及专业")
    private String onJobEducationSchoolAndMajor;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入党时间", dateFormat = "yyyy-MM-dd")
    private String partyJoinTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "参加工作时间", dateFormat = "yyyy-MM-dd")
    private String startWorkTime;

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
