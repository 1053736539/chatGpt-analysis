package com.cb.system.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户负责人关系对象 sys_user_supervisor
 * 
 * @author yangcd
 * @date 2024-09-12
 */
public class SysUserSupervisor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Integer userId;

    /** 负责人ID */
    @Excel(name = "负责人ID")
    private Integer supervisorId;

    /** 负责人姓名 */
    @Excel(name = "负责人姓名")
    private String leader;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setUserId(Integer userId) 
    {
        this.userId = userId;
    }

    public Integer getUserId() 
    {
        return userId;
    }
    public void setSupervisorId(Integer supervisorId) 
    {
        this.supervisorId = supervisorId;
    }

    public Integer getSupervisorId() 
    {
        return supervisorId;
    }
    public void setLeader(String leader) 
    {
        this.leader = leader;
    }

    public String getLeader() 
    {
        return leader;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("supervisorId", getSupervisorId())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("leader", getLeader())
            .append("userName", getUserName())
            .toString();
    }
}
