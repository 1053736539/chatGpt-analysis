package com.cb.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 监督管理-身心健康情况对象 transferring_student_health
 *
 * @author yangxin
 * @date 2024-08-27
 */
public class TransferringStudentHealth extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    private Long userId;

    /** $column.columnComment */
    private String delFlag;

    /** 年度 */
    @Excel(name = "年度")
    private String year;

    /** 是否体检(0=否,1=是) */
    @Excel(name = "是否体检",readConverterExp = "0=否,1=是")
    private String bodyCheck;

    /** 是否心理测试(0=否,1=是) */
    @Excel(name = "是否心理测试",readConverterExp = "0=否,1=是")
    private String psychologyCheck;

    /** 是否存在重大身心疾病(0=否,1=是) */
    @Excel(name = "是否存在重大身心疾病",readConverterExp = "0=否,1=是")
    private String diseaseStatus;

    /** 疾病信息 */
    @Excel(name = "疾病信息")
    private String diseaseInfo;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }
    public void setYear(String year)
    {
        this.year = year;
    }

    public String getYear()
    {
        return year;
    }
    public void setBodyCheck(String bodyCheck)
    {
        this.bodyCheck = bodyCheck;
    }

    public String getBodyCheck()
    {
        return bodyCheck;
    }
    public void setPsychologyCheck(String psychologyCheck)
    {
        this.psychologyCheck = psychologyCheck;
    }

    public String getPsychologyCheck()
    {
        return psychologyCheck;
    }
    public void setDiseaseStatus(String diseaseStatus)
    {
        this.diseaseStatus = diseaseStatus;
    }

    public String getDiseaseStatus()
    {
        return diseaseStatus;
    }
    public void setDiseaseInfo(String diseaseInfo)
    {
        this.diseaseInfo = diseaseInfo;
    }

    public String getDiseaseInfo()
    {
        return diseaseInfo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userId", getUserId())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .append("year", getYear())
                .append("bodyCheck", getBodyCheck())
                .append("psychologyCheck", getPsychologyCheck())
                .append("diseaseStatus", getDiseaseStatus())
                .append("diseaseInfo", getDiseaseInfo())
                .toString();
    }
}