package com.cb.assess.enums;


public enum TaskStatus {
    DRAFT("-1", "草稿"),
    ISSUANCE("0", "下发确认"),
    RELEASE("1", "发布"),
    REVOKE_RELEASE("2", "撤销发布");
    private final String code;
    private final String desc;

    TaskStatus(String code, String desc) {
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


