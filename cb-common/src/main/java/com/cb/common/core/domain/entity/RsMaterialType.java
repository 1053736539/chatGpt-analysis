package com.cb.common.core.domain.entity;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * 材料类型对象 RS_MATERIAL_TYPE
 * 
 * @author ruoyi
 * @date 2025-02-25
 */
public class RsMaterialType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 材料类型ID */
    private Integer id;

    /** 材料类型名称 */
    @Excel(name = "材料类型名称")
    private String descript;

    /** 材料上级ID */
    @Excel(name = "材料上级ID")
    private Integer parentId;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 类型
      */
    private  Integer type;

    public List<RsMaterialType> getChildren() {
        return children;
    }

    public void setChildren(List<RsMaterialType> children) {
        this.children = children;
    }

    /** 子类 */
    private List<RsMaterialType> children = new ArrayList<RsMaterialType>();

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getParentType() {
        return parentType;
    }

    public void setParentType(Integer parentType) {
        this.parentType = parentType;
    }

    /**
     * 上级类型
     */
    private  Integer parentType;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }

    public void setDescript(String descript) 
    {
        this.descript = descript;
    }

    public String getDescript() 
    {
        return descript;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("descript", getDescript())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("parentId", getParentId())
                .append("type", getType())
                .append("parentType", getParentType())
            .toString();
    }
}
