package com.cb.assess.enums;

import java.util.Arrays;

/**
 * 考核类型Enums
 */
public enum Special {
    NORMAL("0", "平时考核"),
    PECULIAR("1", "专项考核");

    private final String code;
    private final String desc;

    Special(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
    public static Special ofCode(String code) {
        return Arrays.stream(Special.values()).filter(item -> item.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("根据" + code + ",未获取到枚举！"));
    }
}
