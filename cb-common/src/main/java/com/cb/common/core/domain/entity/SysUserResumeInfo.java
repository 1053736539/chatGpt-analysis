package com.cb.common.core.domain.entity;

import com.cb.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户简历信息对象 sys_user_resume_info
 *
 * @author hu
 * @date 2023-10-30
 */
public class SysUserResumeInfo {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String resumeInfoId;

    /**
     * 用户ID
     */
    @Excel(name = "用户ID")
    private Long userId;

    /**
     * 起始日期
     */
    @Excel(name = "起始日期")
    private String startDate;

    /**
     * 终止日期
     */
    @Excel(name = "终止日期")
    private String endDate;

    /**
     * 从事工作或担任职务
     */
    @Excel(name = "从事工作或担任职务")
    private String workJobName;

    /**
     * 序号
     */
    @Excel(name = "序号")
    private String sortNum;

    /**
     * 删除标记 0-未删除 1-已删除
     */
    private String delFlag;

    /**
     * 简历描述
     */
    @Excel(name = "简历描述")
    private String resumeDesc;

    public void setResumeInfoId(String resumeInfoId) {
        this.resumeInfoId = resumeInfoId;
    }

    public String getResumeInfoId() {
        return resumeInfoId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setWorkJobName(String workJobName) {
        this.workJobName = workJobName;
    }

    public String getWorkJobName() {
        return workJobName;
    }

    public void setSortNum(String sortNum) {
        this.sortNum = sortNum;
    }

    public String getSortNum() {
        return sortNum;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setResumeDesc(String resumeDesc) {
        this.resumeDesc = resumeDesc;
    }

    public String getResumeDesc() {
        return resumeDesc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("resumeInfoId", getResumeInfoId())
                .append("userId", getUserId())
                .append("startDate", getStartDate())
                .append("endDate", getEndDate())
                .append("workJobName", getWorkJobName())
                .append("sortNum", getSortNum())
                .append("delFlag", getDelFlag())
                .append("resumeDesc", getResumeDesc())
                .toString();
    }
}
