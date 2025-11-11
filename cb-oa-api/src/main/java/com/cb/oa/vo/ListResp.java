package com.cb.oa.vo;

import java.util.List;

/**
 * @Description:
 * @Author ARPHS
 * @Date 2023/11/30 11:39
 */
public class ListResp<T> {

    private Integer code;
    private String msg;
    private List<T> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
