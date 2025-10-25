package com.cb.diy.model.res;

import java.util.Map;

/**
 * 调试请求
 * @author xiehong
 */
public class DiyDebugRes {
    /**
     * 指标名称
     */
    private String name;
    /**
     * 请求参数
     */
    private Map<String, Object> variables;
    /**
     * 流程数据
     */
    private String process;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    @Override
    public String toString() {
        return "DiyDebugRes{" +
                "variables=" + variables +
                ", process='" + process + '\'' +
                '}';
    }
}
