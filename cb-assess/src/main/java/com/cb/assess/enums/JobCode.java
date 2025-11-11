package com.cb.assess.enums;

public enum JobCode {

    MAIN_HALL_LEVEL(111, "正厅级"),
    DEPUTY_HALL_LEVEL(112, "副厅级"),
    DIVISION_LEVEL(121,"正处级"),
    DEPUTY_DIVISION_LEVEL(122,"副处级"),
    MAIN_SECTION_LEVEL(131,"正科级"),
    DEPUTY_SECTION_LEVEL(132,"副科级"),
    FIRST_LEVEL_INSPECTOR(211,"一级巡视员"),
    SECOND_LEVEL_INSPECTOR(212,"二级巡视员"),
    FIRST_LEVEL_RESEARCHER(221,"一级调研员"),
    SECOND_LEVEL_RESEARCHER(222,"二级调研员"),
    THIRD_LEVEL_RESEARCHER(223,"三级调研员"),
    FOURTH_LEVEL_RESEARCHER(224,"三级调研员");
    private final Integer code;
    private final String desc;

    JobCode(Integer code, String dec) {
        this.code = code;
        this.desc = dec;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

