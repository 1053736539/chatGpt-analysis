package com.cb.common.enums;

public enum SecretLevel {
    NON(0, "非涉密"),
    CONFIDENTIAL(1, "秘密级"),
    MOST_CONFIDENTIAL(2, "机密级"),
    TOP_CONFIDENTIAL(3, "绝密级");

    private final Integer level;
    private final String desc;

    SecretLevel(Integer level, String desc) {
        this.level = level;
        this.desc = desc;
    }

    public Integer getLevel() {
        return level;
    }

    public String getDesc() {
        return desc;
    }
}
