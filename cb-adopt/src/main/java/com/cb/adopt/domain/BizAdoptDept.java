package com.cb.adopt.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 报送单位信息对象 biz_adopt_dept
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
public class BizAdoptDept extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Integer id;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String name;

    /** 单位类型 1-各县（市）区纪委监委 2-市纪委监委机关各部门及市委巡察机构 3-市纪委监委各派驻（出）机构 */
    @Excel(name = "单位类型 1-各县（市）区纪委监委 2-市纪委监委机关各部门及市委巡察机构 3-市纪委监委各派驻（出）机构")
    private Integer type;

    /** 排序号 */
    @Excel(name = "排序号")
    private Integer orderNo;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setOrderNo(Integer orderNo) 
    {
        this.orderNo = orderNo;
    }

    public Integer getOrderNo() 
    {
        return orderNo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("type", getType())
            .append("orderNo", getOrderNo())
            .toString();
    }
}
