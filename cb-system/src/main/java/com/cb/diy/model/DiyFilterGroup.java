package com.cb.diy.model;

import java.util.List;

/**
 * 查询组
 * @author xiehong
 */
public class DiyFilterGroup {
    private String andor;
    private List<DiyFilterItem> items;

    public String getAndor() {
        return andor;
    }

    public void setAndor(String andor) {
        this.andor = andor;
    }

    public List<DiyFilterItem> getItems() {
        return items;
    }

    public void setItems(List<DiyFilterItem> items) {
        this.items = items;
    }
}
