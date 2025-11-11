package com.cb.domain;

import java.util.Date;

import com.cb.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cb.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 选调生考核评价对象 transferring_student_examine
 * 
 * @author ruoyi
 * @date 2023-10-27
 */
public class TransferringStudentExamine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String userId;

    /** 考核编号 */
    @Excel(name = "考核编号")
    private String assessmentNumber;

    /** 考核内容 */
    @Excel(name = "考核内容")
    private String assessmentContent;

    /** 考核时间 */
    @Excel(name = "考核时间")
    private String assessmentTime;

    /** 考核成绩 */
    @Excel(name = "考核成绩")
    private String assessmentAchievement;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    /** $column.columnComment */


    /** $column.columnComment */
    private String delFlag;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setAssessmentNumber(String assessmentNumber) 
    {
        this.assessmentNumber = assessmentNumber;
    }

    public String getAssessmentNumber() 
    {
        return assessmentNumber;
    }
    public void setAssessmentContent(String assessmentContent) 
    {
        this.assessmentContent = assessmentContent;
    }

    public String getAssessmentContent() 
    {
        return assessmentContent;
    }
    public void setAssessmentTime(String assessmentTime) 
    {
        this.assessmentTime = assessmentTime;
    }

    public String getAssessmentTime() 
    {
        return assessmentTime;
    }
    public void setAssessmentAchievement(String assessmentAchievement) 
    {
        this.assessmentAchievement = assessmentAchievement;
    }

    public String getAssessmentAchievement() 
    {
        return assessmentAchievement;
    }
    public void setCreateTime(Date craeteTime)
    {
        this.createTime = craeteTime;
    }

    public Date getCreateTime()
    {
        return createTime;
    }



    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("assessmentNumber", getAssessmentNumber())
            .append("assessmentContent", getAssessmentContent())
            .append("assessmentTime", getAssessmentTime())
            .append("assessmentAchievement", getAssessmentAchievement())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())

            .append("delFlag", getDelFlag())
            .toString();
    }
}
