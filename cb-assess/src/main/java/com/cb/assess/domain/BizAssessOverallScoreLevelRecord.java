package com.cb.assess.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.math.BigDecimal;

/**
 * 平时考核最终综合得分及建议评定等次记录对象 biz_assess_overall_score_level_record
 *
 * @author ouyang
 * @date 2023-12-12
 */
@Data
public class BizAssessOverallScoreLevelRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private String id;

    /**
     * 任务id
     */
//    @Excel(name = "任务ID")
    private String taskId;

    /**
     * 用户id
     */
//    @Excel(name = "用户ID")
    private Long assessedUserId;

    @Excel(name = "姓名")
    private String userName;


    @Excel(name = "处室（单位）")
    private String deptName;

    @Excel(name = "年度")
    private String belongYear;

    @Excel(name = "是否专项考核")
    private String special;
    /**
     * 季度
     */
    @Excel(name = "季度")
    private String quarter;

    /**
     * 最终得分
     */
    @Excel(name = "最终得分")
    private BigDecimal finalScore;

    /**
     * 最终评定等次
     */
    @Excel(name = "最终评定等次")
    private String finalOption;
    private Long deptId;
    private Boolean isFillRegular;
    private String cycle;
    private String cycleDesc;
    private String personnelType;

    private String taskName;

}
