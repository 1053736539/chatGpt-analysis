package com.cb.diy.model.resp;

import java.util.Map;

/**
 * 预警缓存数据
 * @author xiehong
 */
public class WarningCacheData extends WarningData {
    private Map<String, Object> params;

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
