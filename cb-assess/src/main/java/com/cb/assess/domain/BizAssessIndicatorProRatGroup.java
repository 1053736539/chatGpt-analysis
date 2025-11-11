package com.cb.assess.domain;

import lombok.Data;

/**
 * 考核方案关联评分组对象 biz_assess_indicator_pro_rat_group
 *
 * @author ouyang
 * @date 2023-11-01
 */
@Data
public class BizAssessIndicatorProRatGroup {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private String groupId;

    /**
     * $column.columnComment
     */
    private String proId;

    /**
     * 关联考核体系的分值配置ID
     */
    private String name;
    /**
     * 是否部门内考核
     */
    private String deptAssess;

    /**分值构成权重*/
    private Integer weight;
}
