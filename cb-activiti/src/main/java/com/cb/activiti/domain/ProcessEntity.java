package com.cb.activiti.domain;

import com.cb.common.core.domain.BaseEntity;
import com.cb.common.core.domain.entity.SysUser;
import lombok.Data;

/**
 * @author 一只闲鹿
 */
@Data
public class ProcessEntity extends BaseEntity {

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 任务名称
     */
    private String taskName;

    private  String assignee;

    private  String assigneeName;

    /**
     * 实例状态 1 激活 2 挂起
     */
    private String suspendState;

    /**
     * 已激活/已挂起
     */
    private String suspendStateName;
    /**
     * 申请人
     */
    private SysUser applyUser;

}
