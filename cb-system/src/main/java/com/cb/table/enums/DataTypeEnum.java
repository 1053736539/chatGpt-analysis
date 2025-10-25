package com.cb.table.enums;

import java.util.Arrays;

public enum DataTypeEnum {
    STRING("String", "VARCHAR", "字符串"),
    INTEGER("Integer", "INTEGER", "整型"),
    LONG("Long", "BIGINT", "长整型"),
    FLOAT("float", "FLOAT", "单精度浮点型"),
    DOUBLE("Double", "DOUBLE", "双精度浮点型"),
    DATE_TIME("DateTime", "TIMESTAMP", "日期"),
    BIG_DECIMAL("BigDecimal", "DECIMAL", "BigDecimal"),
    TEXT("Text", "CLOB", "长文本"),
    BLOB("Blob", "BLOB", "Blob");

    private final String javaType;
    private final String jdbcType;
    private final String desc;


    DataTypeEnum(String javaType, String jdbcType, String desc) {
        this.javaType = javaType;
        this.jdbcType = jdbcType;
        this.desc = desc;
    }

    public String getJavaType() {
        return javaType;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public String getDesc() {
        return desc;
    }
    public static DataTypeEnum ofJdbcType(String javaType) {
        return Arrays.stream(DataTypeEnum.values()).filter(item -> item.getJavaType().equals(javaType)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("根据" + javaType + ",未获取到枚举！"));
    }
}
