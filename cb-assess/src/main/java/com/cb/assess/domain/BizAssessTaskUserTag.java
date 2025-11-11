package com.cb.assess.domain;

import com.cb.common.core.domain.BaseEntity;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.domain.entity.SysUser;
import lombok.Data;
import org.springframework.data.annotation.Transient;

/**
 * 任务与用户小结关联表 biz_assess_task_user_tag
 *
 * @author ouyang
 * @date 2023-11-08
 */
@Data
public class BizAssessTaskUserTag extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 用户ID
     */
    private Long userId;
    /*查询参数*/
    private String name;
    private String deptName;

    @Transient
    private BizIndividualAssessmentTask task;

    @Transient
    private SysUser user;

    @Transient
    private SysDept dept;
}
