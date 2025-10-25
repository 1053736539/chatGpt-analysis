package com.cb.common.core.domain.entity;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 干部能力标签对象 SYS_USER_ABILITY_LABEL
 * 
 * @author ruoyi
 * @date 2025-02-22
 */
public class SysUserAbilityLabel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 干部能力标签id */
    private Integer id;

    /** 干部能力标签 */
    @Excel(name = "干部能力标签")
    private String abilityLabel;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setAbilityLabel(String abilityLabel) 
    {
        this.abilityLabel = abilityLabel;
    }

    public String getAbilityLabel() 
    {
        return abilityLabel;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("abilityLabel", getAbilityLabel())
            .append("description", getDescription())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
