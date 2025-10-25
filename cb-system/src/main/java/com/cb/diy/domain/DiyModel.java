package com.cb.diy.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * DIY模型
 * @author xiehong
 */
public class DiyModel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long modelId;

    /**
     * 模型名称
     */
    @Excel(name = "模型名称")
    private String name;

    /**
     * 背景介绍
     */
    @Excel(name = "背景介绍")
    private String backgroundInformation;

    /**
     * 数据描述
     */
    @Excel(name = "数据描述")
    private String dataDescription;

    private Integer indicatorCount;

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBackgroundInformation(String backgroundInformation) {
        this.backgroundInformation = backgroundInformation;
    }

    public String getBackgroundInformation() {
        return backgroundInformation;
    }

    public void setDataDescription(String dataDescription) {
        this.dataDescription = dataDescription;
    }

    public String getDataDescription() {
        return dataDescription;
    }

    public Integer getIndicatorCount() {
        return indicatorCount;
    }

    public void setIndicatorCount(Integer indicatorCount) {
        this.indicatorCount = indicatorCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("modelId", getModelId())
                .append("name", getName())
                .append("backgroundInformation", getBackgroundInformation())
                .append("dataDescription", getDataDescription())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
