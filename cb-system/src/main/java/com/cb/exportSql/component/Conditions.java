package com.cb.exportSql.component;

import java.util.Collections;
import java.util.Map;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/4/28 10:41
 * @Copyright (c) 2025
 * @Description 条件处理接口
 */
public interface Conditions {
    /**
     * 主键名称
     * @return
     */
    default String primaryKeyName()  {
        return "id";
    }

    /**
     * 比对条件处理
     * @return
     */
    default Map<String, Object> comparisonConditions()  {
        return Collections.emptyMap();
    }

    /**
     * 导出条件处理
     * @return
     */
    default Map<String, Object> exportConditions() {
        return Collections.emptyMap();
    }
}
