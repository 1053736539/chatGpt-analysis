package com.cb.assess.domain;

import com.cb.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 指标配置对象 biz_assess_indicator_ism_config
 *
 * @author ouyang
 * @date 2023-10-31
 */
public class BizAssessIndicatorIsmConfig {
    private static final long serialVersionUID = 1L;
    /**
     * $column.columnComment
     */
    private String configId;

    private String ismId;
    /**
     * 指标分类Code
     */
    @Excel(name = "指标分类Code")
    private String indicatorType;

    /**
     * 分值权重
     */
    @Excel(name = "分值权重")
    private Integer scoreWeight;

    /**
     * 考核指标
     */
//    @Excel(name = "考核指标")
    private String content;

    /**
     * 考核指标biz_assess_indicator对象数组
     */
    private String indicatorSnapshot;

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getConfigId() {
        return configId;
    }

    public String getIsmId() {
        return ismId;
    }

    public void setIsmId(String ismId) {
        this.ismId = ismId;
    }

    public void setIndicatorType(String indicatorType) {
        this.indicatorType = indicatorType;
    }

    public String getIndicatorType() {
        return indicatorType;
    }

    public void setScoreWeight(Integer scoreWeight) {
        this.scoreWeight = scoreWeight;
    }

    public Integer getScoreWeight() {
        return scoreWeight;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getIndicatorSnapshot() {
        return indicatorSnapshot;
    }

    public void setIndicatorSnapshot(String indicatorSnapshot) {
        this.indicatorSnapshot = indicatorSnapshot;
    }
}
