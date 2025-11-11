package com.cb.assess.domain.vo;

import com.cb.assess.domain.BizAssessIndicator;
import lombok.Data;

import java.util.List;

@Data
public class IndicatorIsmConfigVo {
    private String configId;
    /**
     * 指标分类Code
     */
    private String indicatorType;
    /**
     * 指标
     */
    private List<BizAssessIndicator> indicatorList;
    /**
     * 分值权重
     */
    private Integer scoreWeight;

}
