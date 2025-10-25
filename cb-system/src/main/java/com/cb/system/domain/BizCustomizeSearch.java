package com.cb.system.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 自定义查询方案对象 biz_customize_search
 *
 * @author hujilie
 * @date 2023-11-27
 */
public class BizCustomizeSearch extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long userId;

    /** 方案名称 */
    @Excel(name = "方案名称")
    private String name;

    /** 查询表达式 */
    @Excel(name = "查询表达式")
    private String expList;

    /** 共享状态 */
    @Excel(name = "共享状态")
    private String shareStatus;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
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
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setExpList(String expList)
    {
        this.expList = expList;
    }

    public String getExpList()
    {
        return expList;
    }
    public void setShareStatus(String shareStatus)
    {
        this.shareStatus = shareStatus;
    }

    public String getShareStatus()
    {
        return shareStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("name", getName())
            .append("expList", getExpList())
            .append("shareStatus", getShareStatus())
            .toString();
    }
}
