package com.cb.task.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 使用反馈对象 biz_feedback
 * 
 * @author yangxin
 * @date 2024-01-09
 */
public class BizFeedback extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 问题描述 */
    @Excel(name = "问题描述")
    private String problem;

    /** 附件ids，逗号字符串拼接 */
    @Excel(name = "附件ids，逗号字符串拼接")
    private String attach;

    /** 状态 1-待处理 2-已处理 */
    @Excel(name = "状态 1-待处理 2-已处理")
    private Integer status;

    /** 处理情况 */
    @Excel(name = "处理情况")
    private String handleSituation;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setProblem(String problem) 
    {
        this.problem = problem;
    }

    public String getProblem() 
    {
        return problem;
    }
    public void setAttach(String attach) 
    {
        this.attach = attach;
    }

    public String getAttach() 
    {
        return attach;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setHandleSituation(String handleSituation) 
    {
        this.handleSituation = handleSituation;
    }

    public String getHandleSituation() 
    {
        return handleSituation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("problem", getProblem())
            .append("attach", getAttach())
            .append("status", getStatus())
            .append("handleSituation", getHandleSituation())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
