package com.cb.diy.model.res;

import java.util.Map;

/**
 * 指标请求
 * @author xiehong
 */
public class DiyIndicatorRes {
    /**
     * 指标ID
     */
    private Long id;
    /**
     * 请求参数
     */
    private Map<String, Object> variables;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    @Override
    public String toString() {
        return "DiyIndicatorRes{" +
                "id=" + id +
                ", variables=" + variables +
                '}';
    }
}
