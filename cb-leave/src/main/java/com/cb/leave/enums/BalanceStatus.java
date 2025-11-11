package com.cb.leave.enums;

/**
 * @Description:
 * @Author: ARPHS
 * @Date: 2025-03-19 11:21
 * @Version: 1.0
 **/
public enum BalanceStatus {
    UN_CONFIRMED(1, "待确认"),
    IN_REVIEW(2, "审核中"),
    CONFIRMED(3, "已确认"),
    REJECTED(4, "审核驳回"),
    ;

    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    BalanceStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
