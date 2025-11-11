package com.cb.assess.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 考核评议记录对象 biz_assess_task_evaluate_record
 *
 * @author ouyang
 * @date 2023-11-20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BizAssessTaskEvaluateRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 评议ID
     */
    private String evaluateId;

    private String evaluateTagId;

    /**
     * 考核体系指标配置ID
     */
    @Excel(name = "考核体系指标配置ID")
    private String ismConfigId;

    /**
     * 得分
     */
    @Excel(name = "得分")
    private BigDecimal score;
}
