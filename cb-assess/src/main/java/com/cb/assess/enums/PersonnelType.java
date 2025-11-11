package com.cb.assess.enums;

import java.util.Arrays;

public enum PersonnelType {
    CIVIL_SERVANT_SPECIAL("civil_servant_special", new String[]{"0", "2", "3"},new String[]{"1","2"} ,"二级巡视员、总师、督查员、主要负责人"),
    CIVIL_SERVANT_GENERAL("civil_servant_other", new String[]{"0", "1"}, new String[]{"1","2"},"其他考核对象"),
    INSTITUTION_SPECIAL("institution_special", new String[]{"0", "2", "3"},new String[]{"3"} ,"主要负责人"),
    INSTITUTION_GENERAL("institution_other", new String[]{"0", "1"}, new String[]{"3"},"其他考核对象"),
    SPECIAL_ASSESS("1", new String[]{"4"},new String[]{"1","2","3"}, "专项考核评分");
    private final String code;
    // 分值权重必须配置项
    private final String[] must;
    // 编制类型
    private final String[] identity;
    private final String desc;

    PersonnelType(String code, String[] must,String[] identity, String dec) {
        this.code = code;
        this.must = must;
        this.identity = identity;
        this.desc = dec;
    }

    public String getCode() {
        return code;
    }

    public String[] getMust() {
        return must;
    }

    public String[] getIdentity() {
        return identity;
    }

    public String getDesc() {
        return desc;
    }

    public static PersonnelType ofCode(String code) {
        return Arrays.stream(PersonnelType.values()).filter(item -> item.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("根据" + code + ",未获取到枚举！"));
    }

}
