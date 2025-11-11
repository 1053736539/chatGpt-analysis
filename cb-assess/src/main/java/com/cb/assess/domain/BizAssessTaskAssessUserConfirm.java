package com.cb.assess.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 考核任务下发确认对象 biz_assess_task_assess_user_confirm
 *
 * @author ouyang
 * @date 2024-05-31
 */
@Data
public class BizAssessTaskAssessUserConfirm extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 部门Id
     */
    @Excel(name = "部门Id")
    private Long deptId;

    private String deptName;

    /**
     * 用户JSON
     */
    @Excel(name = "用户JSON")
    private String usersJson;

    /**
     * 状态 0未确认 1 已确认 2 驳回 3 不参加
     */
    @Excel(name = "状态")
    private String status;
    /**
     * 季度
     */
    private String cycleDesc;

    /**
     * 驳回理由
     */
    private String rejection;
}
