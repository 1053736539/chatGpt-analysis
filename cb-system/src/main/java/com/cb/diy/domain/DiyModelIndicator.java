package com.cb.diy.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * DIY模型指标
 * @author xiehong
 */
public class DiyModelIndicator extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 模块ID
     */
    @Excel(name = "模块ID")
    private Long modelId;

    /**
     * 指标名称
     */
    @Excel(name = "指标名称")
    private String name;

    /**
     * 指标介绍
     */
    @Excel(name = "指标介绍")
    private String introduction;

    /**
     * 流程数据
     */
    @Excel(name = "流程数据")
    private String process;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getProcess() {
        return process;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("modelId", getModelId())
                .append("name", getName())
                .append("introduction", getIntroduction())
                .append("process", getProcess())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
