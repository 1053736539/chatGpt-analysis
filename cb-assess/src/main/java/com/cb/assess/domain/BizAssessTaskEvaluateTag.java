package com.cb.assess.domain;

import com.cb.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 任务用户互评标记对象 biz_assess_task_evaluate_tag
 *
 * @author ouyang
 * @date 2023-11-17
 */
@Data
public class BizAssessTaskEvaluateTag extends BaseEntity {
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
     * 评价人
     */
    private Long voteUserId;

    /**
     * 被评人
     */
    private Long assessedUserId;

    // 0 普通科员 1主要负责人 2 分管领导 3 局主要领导  4.专项测评的，不再区分
    private String voteType;

    /**
     * 0 未评价 1 已评价
     */
    private String status;
}
