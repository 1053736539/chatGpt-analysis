package com.cb.knowledge.base.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/5/19 09:16
 * @Copyright (c) 2025
 * @Description 常数配置
 */
public class DataCostant {
    public static final String INDEX_DIR = "index/";

    public static final String SQLITE_DB = "jdbc:sqlite:meta.db";

    public static final int INDEX_ID = 0;

    public static final Map<String, Integer> FILE_PATH_ID = new ConcurrentHashMap<>(16);
}
