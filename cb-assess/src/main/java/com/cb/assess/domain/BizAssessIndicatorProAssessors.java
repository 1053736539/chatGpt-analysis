package com.cb.assess.domain;

import lombok.Data;


/**
 * 考核方案为专项考核的考官信息 biz_assess_indicator_pro_assessors
 *
 * @author ouyang
 * @date 2023-11-01
 */
@Data
public class BizAssessIndicatorProAssessors {
    private static final long serialVersionUID = 1L;

    /**
     * 方案ID
     */
    private String proId;

    /**
     * 考官ID
     */
    private Long userId;
}
