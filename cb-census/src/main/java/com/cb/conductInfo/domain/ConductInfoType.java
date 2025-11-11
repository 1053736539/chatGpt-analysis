package com.cb.conductInfo.domain;

import com.cb.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 普查员宣传资料库-类型对象 biz_conduct_info_type
 * 
 * @author yangxin
 * @date 2023-10-20
 */
public class ConductInfoType implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** code */
    @Excel(name = "code")
    private String code;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 删除标记 0-未删除 1-已删除 */
    private Integer delFlag;

    /** $column.columnComment */
    @Excel(name = "名称")
    private String desc;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setDelFlag(Integer delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag() 
    {
        return delFlag;
    }
    public void setDesc(String desc) 
    {
        this.desc = desc;
    }

    public String getDesc() 
    {
        return desc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("name", getName())
            .append("delFlag", getDelFlag())
            .append("desc", getDesc())
            .toString();
    }
}
