package com.cb.assess.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 被考核部门对象 biz_assess_indicator_pro_duty
 *
 * @author ouyang
 * @date 2023-11-01
 */
@Data
public class BizAssessIndicatorProDuty {
    private static final long serialVersionUID = 1L;

    /**
     * 考核方案ID
     */
    private String proId;

    private String type ;
    /**
     * 部门ID
     */
    private Long deptId;

    private String deptName;

    private Long userId;
}
