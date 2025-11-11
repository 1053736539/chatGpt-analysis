package com.cb.adopt.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 信息采用记录的相关单位及分值记录对象 biz_adopt_record_score
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
public class BizAdoptRecordScore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private String id;

    /** 记录id */
    @Excel(name = "记录id")
    private String recordId;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String dept;

    /** 分值 */
    @Excel(name = "分值")
    private Integer score;

    /** 是否主报单位 1-是 0-否 */
    @Excel(name = "是否主报单位 1-是 0-否")
    private Integer mainFlag;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setRecordId(String recordId) 
    {
        this.recordId = recordId;
    }

    public String getRecordId() 
    {
        return recordId;
    }
    public void setDept(String dept) 
    {
        this.dept = dept;
    }

    public String getDept() 
    {
        return dept;
    }
    public void setScore(Integer score) 
    {
        this.score = score;
    }

    public Integer getScore() 
    {
        return score;
    }
    public void setMainFlag(Integer mainFlag) 
    {
        this.mainFlag = mainFlag;
    }

    public Integer getMainFlag() 
    {
        return mainFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("recordId", getRecordId())
            .append("dept", getDept())
            .append("score", getScore())
            .append("mainFlag", getMainFlag())
            .toString();
    }
}
