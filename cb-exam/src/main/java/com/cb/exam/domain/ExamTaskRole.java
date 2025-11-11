package com.cb.exam.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 考试任务与角色关系对象 exam_task_role
 *
 * @author hu
 * @date 2023-11-08
 */
public class ExamTaskRole extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 考试任务id
     */
    @Excel(name = "考试任务id")
    private Long taskId;

    /**
     * 角色id
     */
    @Excel(name = "角色id")
    private Long roleId;

    /**
     * 创建用户
     */
    @Excel(name = "创建用户")
    private String createUser;

    /**
     * 修改用户
     */
    @Excel(name = "修改用户")
    private String modifyUser;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRoleId() {
        return roleId;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("taskId", getTaskId())
                .append("roleId", getRoleId())
                .append("createUser", getCreateBy())
                .append("modifyUser", getUpdateBy())
                .append("createTime", getCreateTime())
                .append("modifyTime", getUpdateTime())
                .toString();
    }
}
