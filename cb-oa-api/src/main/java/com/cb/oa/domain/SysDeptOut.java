package com.cb.oa.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * oa系统的部门列对象 sys_dept_out
 * 
 * @author ruoyi
 * @date 2023-12-01
 */
public class SysDeptOut extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "ID")
    private String id;

    /** 机构编码 */
    @Excel(name = "机构编码")
    private String deptCode;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 名称拼音首字母 */
    @Excel(name = "名称拼音首字母")
    private String nameLetter;

    /** $column.columnComment */
  //  @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer sort;

    /** $column.columnComment */
   // @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String pid;

    /** $column.columnComment */
    //@Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String treePids;

    /** $column.columnComment */
    //@Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String treeLevel;

    /** $column.columnComment */
//    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String enabled;

    /** 自己系统内部门的id */
    @Excel(name = "自己系统内部门的id")
    private Long deptId;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setDeptCode(String deptCode) 
    {
        this.deptCode = deptCode;
    }

    public String getDeptCode() 
    {
        return deptCode;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setNameLetter(String nameLetter) 
    {
        this.nameLetter = nameLetter;
    }

    public String getNameLetter() 
    {
        return nameLetter;
    }
    public void setSort(Integer sort) 
    {
        this.sort = sort;
    }

    public Integer getSort() 
    {
        return sort;
    }
    public void setPid(String pid) 
    {
        this.pid = pid;
    }

    public String getPid() 
    {
        return pid;
    }
    public void setTreePids(String treePids) 
    {
        this.treePids = treePids;
    }

    public String getTreePids() 
    {
        return treePids;
    }
    public void setTreeLevel(String treeLevel) 
    {
        this.treeLevel = treeLevel;
    }

    public String getTreeLevel() 
    {
        return treeLevel;
    }
    public void setEnabled(String enabled) 
    {
        this.enabled = enabled;
    }

    public String getEnabled() 
    {
        return enabled;
    }
    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deptCode", getDeptCode())
            .append("name", getName())
            .append("nameLetter", getNameLetter())
            .append("sort", getSort())
            .append("pid", getPid())
            .append("treePids", getTreePids())
            .append("treeLevel", getTreeLevel())
            .append("enabled", getEnabled())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("deptId", getDeptId())
            .toString();
    }
}
