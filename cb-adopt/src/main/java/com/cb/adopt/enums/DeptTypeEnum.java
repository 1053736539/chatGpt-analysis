package com.cb.adopt.enums;

public enum DeptTypeEnum {
    XQJW(1, "各县(市)区纪委监委"),
    JGBM(2,"市纪委监委机关各部门及市委巡察机构"),
    PZJG(3,"市纪委监委各派驻（出）机构")
    ;
    private Integer code;
    private String name;

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    private DeptTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }


}
