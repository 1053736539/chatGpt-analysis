package com.cb.common.core.domain;

import java.util.List;

/**
 * @author lsj
 * @date 2021/11/3 14:53
 */
public class EsPageResult<T> {
    /**
     * 总记录数
     */
    private int total;
    /**
     * 本页的数据列表
     */
    private List<T> data;

    public EsPageResult() {
    }

    public EsPageResult(int total, List<T> data) {
        this.total = total;
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }


}