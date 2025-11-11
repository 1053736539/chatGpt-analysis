package com.cb.assess.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * 考核指标对象 biz_assess_indicator
 *
 * @author ouyang
 * @date 2023-10-26
 */
@Data
public class BizAssessIndicator extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 指标ID
     */
    private String indId;

    /**
     * 指标编号
     */
    @Excel(name = "指标编号")
    private String indNo;
    /**
     * 排序
     */
    private Integer indOrder;

    /**
     * 指标名称
     */
    @Excel(name = "指标名称")
    private String indName;

    /**
     * 考核内容
     */
    @Excel(name = "考核内容")
    private String indContent;

    /**
     * 指标属性（字典值）
     */
    @Excel(name = "指标属性", readConverterExp = "字=典值")
    private String indAttribute;

    /**
     * 指定依据
     */
    @Excel(name = "指定依据")
    private String indAccording;

    /**
     * 考评标准
     */
    @Excel(name = "考评标准")
    private String indStandard;
    /**
     * 状态
     */
    @Excel(name = "状态")
    private String status;
    /**
     * 删除标记
     */
    private String delFlag;
}
