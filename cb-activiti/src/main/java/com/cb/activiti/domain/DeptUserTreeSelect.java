package com.cb.activiti.domain;

import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.domain.entity.SysUser;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * 部门-用户树结构实体类
 */
public class DeptUserTreeSelect implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 节点ID */
    private Long id;

    /** 节点名称 */
    private String label;


    /** 用户账号 */
    private String userName;

    /** 父节点ID */
    private Long parentId;


    /** 节点对应的用户列表 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DeptUserTreeSelect> userList;


    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DeptUserTreeSelect> children;

    public DeptUserTreeSelect(SysDept dept)
    {
        this.id = dept.getDeptId();
        this.label = dept.getDeptName();
        this.parentId = dept.getParentId() == null ? 0L : dept.getParentId();
    }

    public DeptUserTreeSelect(SysUser user)
    {
        this.id = user.getUserId();
        this.label = user.getName();
        this.parentId = user.getDeptId();
        this.userName = user.getUserName();
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public Long getUserId()
    {
        return id;
    }

    public void setUserId(Long userId)
    {
        this.id = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public List<DeptUserTreeSelect> getChildren()
    {
        return children;
    }

    public void setChildren(List<DeptUserTreeSelect> children)
    {
        this.children = children;
    }

    public List<DeptUserTreeSelect> getUserList()
    {
        return userList;
    }

    public void setUserList(List<DeptUserTreeSelect> userList)
    {
        this.userList = userList;
    }
}