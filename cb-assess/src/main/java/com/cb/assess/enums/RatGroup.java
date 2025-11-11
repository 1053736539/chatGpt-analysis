package com.cb.assess.enums;

public enum RatGroup {
    RATE_EACH("0","民主测评"),
    RATE_DIRECTOR("1","主要负责人评价"),
    RATE_CHARGE_LEADER ("2","分管领导评分"),
    RATE_MAIN_LEADER("3","主要领导评分"),
    RATE_SPECIAL("4","专项考核评分");

    private final String code;
    private final String desc;

    RatGroup(String code, String desc) {
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
