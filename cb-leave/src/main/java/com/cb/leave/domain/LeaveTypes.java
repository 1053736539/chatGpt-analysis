package com.cb.leave.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 请休假类型对象 leave_types
 * 
 * @author ruoyi
 * @date 2024-09-09
 */
public class LeaveTypes extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 假期类型ID */
    @Excel(name = "假期类型序号",cellType = Excel.ColumnType.NUMERIC,prompt = "假期类型序号")
    private Integer id;

    /** 假期名称（如年休假、病假等） */
    @Excel(name = "假期名称")
    private String leaveName;

    /** 假期类型描述 */
    @Excel(name = "假期类型描述")
    private String description;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setLeaveName(String leaveName) 
    {
        this.leaveName = leaveName;
    }

    public String getLeaveName() 
    {
        return leaveName;
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
            .append("leaveName", getLeaveName())
            .append("description", getDescription())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
