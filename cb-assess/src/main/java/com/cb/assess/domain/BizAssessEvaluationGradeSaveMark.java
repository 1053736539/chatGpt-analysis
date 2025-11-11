package com.cb.assess.domain;

import com.cb.common.core.domain.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.Transient;

/**
 * 考核上报数据保存标记对象 biz_assess_evaluation_grade_save_mark
 *
 * @author ouyang
 * @date 2023-12-07
 */
@Data
public class BizAssessEvaluationGradeSaveMark extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * $column.columnComment
     */
    private String taskId;

    /**
     * $column.columnComment
     */
    private Long deptId;
    @Transient
    private String deptName;

    private String year;

    private String cycleDesc;
    // 上报标记（0保存，1上报 2 通过 3 退回）
    private String escalationFlag;

    // 退回时填写意见
    private String options;
    /**
     * 创建人姓名
     */
    private transient String createUserName;

}
