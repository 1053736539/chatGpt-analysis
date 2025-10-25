package com.cb.common.core.domain.vo;

import java.util.List;

public class DictDataVo {
    private Long id;
    private Long pId;
    private String label;

    private String fullLabel;

    private String value;

    private  List<DictDataVo> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFullLabel() {
        return fullLabel;
    }

    public void setFullLabel(String fullLabel) {
        this.fullLabel = fullLabel;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<DictDataVo> getChildren() {
        return children;
    }

    public void setChildren(List<DictDataVo> children) {
        this.children = children;
    }
}
