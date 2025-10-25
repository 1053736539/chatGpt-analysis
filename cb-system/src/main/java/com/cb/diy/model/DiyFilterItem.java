package com.cb.diy.model;

/**
 * 查询项
 * @author xiehong
 */
public class DiyFilterItem {
    private String andor;
    private String field;
    private Integer condition;
    private String value;
    private String type;

    public String getAndor() {
        return andor;
    }

    public void setAndor(String andor) {
        this.andor = andor;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Integer getCondition() {
        return condition;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
