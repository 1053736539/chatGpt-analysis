package com.cb.system.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 干部任免信息修改日志对象 sys_user_info_change_log
 *
 * @author ruoyi
 * @date 2025-03-11
 */
public class SysUserInfoChangeLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** user_id */
    private Long userId;

    /** 最初的信息 */
    @Excel(name = "最初的信息")
    private String initData;

    /** 最后的信息 */
    @Excel(name = "最后的信息")
    private String lastData;

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
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
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userId", getUserId())
                .append("initData", getInitData())
                .append("lastData", getLastData())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}