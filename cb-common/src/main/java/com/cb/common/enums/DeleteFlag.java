package com.cb.common.enums;

/**
 * 逻辑业务删除枚举
 */
public enum DeleteFlag {

    NORMAL(1, "正常"), DELETED(2, "删除");

    private final Integer code;
    private final String dec;

    DeleteFlag(Integer code, String dec)
    {
        this.code = code;
        this.dec = dec;
    }

    public Integer getCode()
    {
        return code;
    }

    public String getDec()
    {
        return dec;
    }
}
