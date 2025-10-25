package com.cb.diy.model.resp;

import com.alibaba.fastjson.JSONObject;
import com.cb.diy.model.DiyColumns;

import java.util.List;
import java.util.Map;

/**
 * 指标
 * @author xiehong
 */
public class IndicatorData {
    /**
     * 指标名称
     */
    private String name;
    /**
     * 字段
     */
    private List<DiyColumns> columns;
    /**
     * 表
     */
    private List<JSONObject> tables;
    /**
     * 数据
     */
    private List<Map<String, Object>> items;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DiyColumns> getColumns() {
        return columns;
    }

    public void setColumns(List<DiyColumns> columns) {
        this.columns = columns;
    }

    public List<JSONObject> getTables() {
        return tables;
    }

    public void setTables(List<JSONObject> tables) {
        this.tables = tables;
    }

    public List<Map<String, Object>> getItems() {
        return items;
    }

    public void setItems(List<Map<String, Object>> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "IndicatorResp{" +
                "columns=" + columns +
                ", items=" + items +
                '}';
    }
}
