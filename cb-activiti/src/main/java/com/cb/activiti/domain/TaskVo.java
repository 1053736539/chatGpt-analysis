package com.cb.activiti.domain;

import com.cb.common.core.domain.entity.SysUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.Date;
import java.util.Map;

/**
 * @author 一只闲鹿
 */
@Data
public class TaskVo {

    private String taskId;

    private String taskName;

    private String instanceId;

    private String instanceName;

    private String suspendState;

    private String suspendStateName;

    private  String taskDefKey;

    @Transient
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Map formData;

    private String userId;

    private Integer pageNum;

    private Integer pageSize;

    private Integer offset;

    private String assignee;

    private String assigneeName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 类型 todo 待办 done 已办
     */
    private String type;
    /**
     * 发起人
     */
    private SysUser applyUser;

}
