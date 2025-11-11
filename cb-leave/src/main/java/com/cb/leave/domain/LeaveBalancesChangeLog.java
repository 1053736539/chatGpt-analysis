package com.cb.leave.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 假期额度变更日志对象 leave_balances_change_log
 *
 * @author ruoyi
 * @date 2025-03-19
 */
public class LeaveBalancesChangeLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** leave_balances的id */
    private Integer id;

    /** 最初的信息,json对象字符串 */
    @Excel(name = "最初的信息,json对象字符串")
    private String initData;

    /** 最后的信息,json对象字符串 */
    @Excel(name = "最后的信息,json对象字符串")
    private String lastData;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setInitData(String initData)
    {
        this.initData = initData;
    }

    public String getInitData()
    {
        return initData;
    }
    public void setLastData(String lastData)
    {
        this.lastData = lastData;
    }

    public String getLastData()
    {
        return lastData;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("initData", getInitData())
                .append("lastData", getLastData())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
