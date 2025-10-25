package com.cb.diy.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 连接桩
 * @author xiehong
 */
public class DiyNodePorts {
    private JSONObject groups;
    private JSONArray items;

    public JSONObject getGroups() {
        return groups;
    }

    public void setGroups(JSONObject groups) {
        this.groups = groups;
    }

    public JSONArray getItems() {
        return items;
    }

    public void setItems(JSONArray items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "DiyNodePorts{" +
                "groups=" + groups +
                ", items=" + items +
                '}';
    }
}
