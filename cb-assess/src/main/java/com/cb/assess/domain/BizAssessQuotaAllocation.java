package com.cb.assess.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * xxxx年度机关事业单位考核人数及优秀等次名额分配对象 biz_assess_quota_allocation
 * 
 * @author ruoyi
 * @date 2023-11-16
 */
public class BizAssessQuotaAllocation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private Long deptId;

    /** 人数 */
    @Excel(name = "人数")
    private Integer peopleNumber;

    /** 优秀名额 */
    @Excel(name = "优秀名额")
    private Integer excellentQuota;

    /** 考核年度 */
    @Excel(name = "考核年度")
    private String assessmentYear;

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
    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }
    public void setPeopleNumber(Integer peopleNumber) 
    {
        this.peopleNumber = peopleNumber;
    }

    public Integer getPeopleNumber() 
    {
        return peopleNumber;
    }
    public void setExcellentQuota(Integer excellentQuota) 
    {
        this.excellentQuota = excellentQuota;
    }

    public Integer getExcellentQuota() 
    {
        return excellentQuota;
    }
    public void setAssessmentYear(String assessmentYear) 
    {
        this.assessmentYear = assessmentYear;
    }

    public String getAssessmentYear() 
    {
        return assessmentYear;
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
            .append("deptId", getDeptId())
            .append("peopleNumber", getPeopleNumber())
            .append("excellentQuota", getExcellentQuota())
            .append("assessmentYear", getAssessmentYear())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
