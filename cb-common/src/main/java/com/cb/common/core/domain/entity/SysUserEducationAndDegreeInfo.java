package com.cb.common.core.domain.entity;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 学历学位信息对象 sys_user_education_and_degree_info
 *
 * @author hu
 * @date 2023-10-29
 */
public class SysUserEducationAndDegreeInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String educationAndDegreeInfoId;

    /**
     * 用户ID
     */
    @Excel(name = "用户ID")
    private Long userId;

    /**
     * 教育类别
     */
    @Excel(name = "教育类别")
    private String educationCategory;

    /**
     * 学历
     */
    @Excel(name = "学历")
    private String education;

    /**
     * 学历名称
     */
    @Excel(name = "学历名称")
    private String educationalName;

    /**
     * 学制（一位小数！）
     */
    @Excel(name = "学制", readConverterExp = "一=位小数！")
    private String educationalSystem;

    /**
     * 学位
     */
    @Excel(name = "学位")
    private String degree;

    /**
     * 学位名称
     */
    @Excel(name = "学位名称")
    private String degreeName;

    /**
     * 学校及院系名称
     */
    @Excel(name = "学校及院系名称")
    private String schoolAndDepartment;

    /**
     * 所学专业类别
     */
    @Excel(name = "所学专业类别")
    private String professionalCategory;

    /**
     * 专业名称
     */
    @Excel(name = "专业名称")
    private String professionalName;

    /**
     * 入学日期
     */
    @Excel(name = "入学日期")
    private String enrollmentDate;

    /**
     * 毕（肆）业日期
     */
    @Excel(name = "毕（肆）业日期")
    private String completionDate;

    /**
     * 学位授予日期
     */
    @Excel(name = "学位授予日期")
    private String degreeAwardDate;

    /**
     * del_flag  删除标记 0-未删除 1-已删除
     */
    private String delFlag;

    public void setEducationAndDegreeInfoId(String educationAndDegreeInfoId) {
        this.educationAndDegreeInfoId = educationAndDegreeInfoId;
    }

    public String getEducationAndDegreeInfoId() {
        return educationAndDegreeInfoId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setEducationCategory(String educationCategory) {
        this.educationCategory = educationCategory;
    }

    public String getEducationCategory() {
        return educationCategory;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEducation() {
        return education;
    }

    public void setEducationalName(String educationalName) {
        this.educationalName = educationalName;
    }

    public String getEducationalName() {
        return educationalName;
    }

    public void setEducationalSystem(String educationalSystem) {
        this.educationalSystem = educationalSystem;
    }

    public String getEducationalSystem() {
        return educationalSystem;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setSchoolAndDepartment(String schoolAndDepartment) {
        this.schoolAndDepartment = schoolAndDepartment;
    }

    public String getSchoolAndDepartment() {
        return schoolAndDepartment;
    }

    public void setProfessionalCategory(String professionalCategory) {
        this.professionalCategory = professionalCategory;
    }

    public String getProfessionalCategory() {
        return professionalCategory;
    }

    public void setProfessionalName(String professionalName) {
        this.professionalName = professionalName;
    }

    public String getProfessionalName() {
        return professionalName;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public void setDegreeAwardDate(String degreeAwardDate) {
        this.degreeAwardDate = degreeAwardDate;
    }

    public String getDegreeAwardDate() {
        return degreeAwardDate;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("educationAndDegreeInfoId", getEducationAndDegreeInfoId())
                .append("userId", getUserId())
                .append("educationCategory", getEducationCategory())
                .append("education", getEducation())
                .append("educationalName", getEducationalName())
                .append("educationalSystem", getEducationalSystem())
                .append("degree", getDegree())
                .append("degreeName", getDegreeName())
                .append("schoolAndDepartment", getSchoolAndDepartment())
                .append("professionalCategory", getProfessionalCategory())
                .append("professionalName", getProfessionalName())
                .append("enrollmentDate", getEnrollmentDate())
                .append("completionDate", getCompletionDate())
                .append("degreeAwardDate", getDegreeAwardDate())
                .append("delFlag", getDelFlag())
                .toString();
    }
}