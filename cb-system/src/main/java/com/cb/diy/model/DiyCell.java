package com.cb.diy.model;

import java.util.Map;

/**
 * 元素
 * @author xiehong
 */
public class DiyCell {
    private String id;
    private String shape;
    private Map<String, Object> attrs;
    private Integer zIndex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public Map<String, Object> getAttrs() {
        return attrs;
    }

    public void setAttrs(Map<String, Object> attrs) {
        this.attrs = attrs;
    }

    public Integer getzIndex() {
        return zIndex;
    }

    public void setzIndex(Integer zIndex) {
        this.zIndex = zIndex;
    }

    @Override
    public String toString() {
        return "DiyCell{" +
                "id='" + id + '\'' +
                ", shape='" + shape + '\'' +
                ", attrs=" + attrs +
                ", zIndex=" + zIndex +
                '}';
    }
}
