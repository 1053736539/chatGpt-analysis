package com.cb.assess.domain;

import lombok.Data;

import java.util.List;

@Data
public class VAssessIndicator {

    private static final long serialVersionUID = 1L;

    /**
     * 指标ID
     */
    private String indId;

    /**
     * 指标编号
     */
    private String indNo;
    /**
     * 字典排序
     */
    private Integer dictOrder;
    /**
     * 排序
     */
    private Integer indOrder;

    /**
     * 指标名称
     */
    private String indName;

    /**
     * 指标属性（字典值）
     */
    private String indAttribute;

    /**
     * 考评标准
     */
    private String indStandard;
    /**
     * 状态
     */
    private String status;

    /**
     * 删除标记
     */
    private String delFlag;
}
