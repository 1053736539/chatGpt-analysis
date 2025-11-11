package com.cb.assess.enums;


public enum AssessType {
    MONTHLY("1", "月度考核"),
    QUARTERLY("2", "季度考核"),
    YEARLY("3", "年度考核"),
    OTHER("4", "专项考核");
    private final String code;
    private final String desc;

    AssessType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}


