package com.cb.diy.model.res;

import java.util.Map;

/**
 * 模型请求
 * @author xiehong
 */
public class DiyModelRes {
    /**
     * 模型ID
     */
    private Long modelId;
    /**
     * 是否缓存
     */
    private Boolean isCache;
    /**
     * 参数
     */
    private Map<Long, Map<String, Object>> variables;

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Boolean getCache() {
        return isCache;
    }

    public void setCache(Boolean cache) {
        isCache = cache;
    }

    public Map<Long, Map<String, Object>> getVariables() {
        return variables;
    }

    public void setVariables(Map<Long, Map<String, Object>> variables) {
        this.variables = variables;
    }
}
