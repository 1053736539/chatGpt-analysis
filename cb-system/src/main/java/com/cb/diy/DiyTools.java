package com.cb.diy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cb.common.utils.StringUtils;
import com.cb.diy.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * DIY工具
 * @author xiehong
 */
public class DiyTools {
    /**
     * 转为DIY流程节点集
     * @param process 流程字符串
     */
    public static List<DiyCell> toDiyCells(String process) {
        List<DiyCell> cells = new ArrayList<>();
        JSONObject jsonObject = JSON.parseObject(process);
        if(jsonObject == null) {
            return cells;
        }
        JSONArray jsonArray = jsonObject.getJSONArray("cells");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String shape = obj.getString("shape");
            if (shape.equals("edge")) {
                cells.add(obj.toJavaObject(DiyEdge.class));
            } else {
                cells.add(obj.toJavaObject(DiyNode.class));
            }
        }
        return cells;
    }

    /**
     * 获取上一个表节点
     * @param diyProcess 流程
     * @param diyNode    节点
     */
    public static DiyNode getPreviousTableNode(DiyProcess diyProcess, DiyNode diyNode) throws Exception {
        List<DiyNode> nodes = diyProcess.findPreviousNode(diyNode, "table-node");
        if (nodes == null || nodes.size() == 0) {
            throw new Exception("没有输入表");
        }
        if (nodes.size() > 1) {
            throw new Exception("存在多张输入表");
        }
        return nodes.get(0);
    }

    /**
     * 获取下一个表节点
     * @param diyProcess 流程
     * @param diyNode    节点
     */
    public static DiyNode getNextTableNode(DiyProcess diyProcess, DiyNode diyNode) throws Exception {
        List<DiyNode> nodes = diyProcess.findNextNode(diyNode, "table-node");
        if (nodes == null || nodes.size() == 0) {
            throw new Exception("没有输出表");
        }
        if (nodes.size() > 1) {
            throw new Exception("存在多张输出表");
        }
        return nodes.get(0);
    }

    /**
     * 数字类型条件转sql字符串
     */
    public static String toConditionSql(DiyFilterItem item) {
        switch (item.getCondition()) {
            case 0: {
                if(StringUtils.isBlank(item.getValue()) || item.getValue().equalsIgnoreCase("null")) {
                    return " IS NULL";
                }
                return "=" + item.getValue();
            }
            case 1: {
                return ">" + item.getValue();
            }
            case 2: {
                return "<" + item.getValue();
            }
            case 3: {
                return ">=" + item.getValue();
            }
            case 4: {
                return "<=" + item.getValue();
            }
            case 5: {
                if(StringUtils.isBlank(item.getValue()) || item.getValue().equalsIgnoreCase("null")) {
                    return " IS NOT NULL";
                }
                return "<>" + item.getValue();
            }
            case 6: {
                return "LIKE '%" + item.getValue() + "'";
            }
            case 7: {
                return "LIKE '" + item.getValue() + "%'";
            }
            case 8: {
                return "LIKE '%" + item.getValue() + "%'";
            }
        }
        return null;
    }

    /**
     * 过滤转sql
     */
    public static String toFilterSql(JSONObject data) {
        JSONArray filters = data.getJSONArray("items");
        StringBuilder sql = new StringBuilder();
        for (int i = 0; i < filters.size(); i++) {
            DiyFilterGroup filterGroup = filters.getObject(i, DiyFilterGroup.class);
            List<DiyFilterItem> items = filterGroup.getItems();
            StringBuilder sqlItem = new StringBuilder();
            if (filters.size() > 1) {
                if (i > 0) {
                    sqlItem.append(filterGroup.getAndor());
                }
                sqlItem.append(" (");
            }
            for (int j = 0; j < items.size(); j++) {
                DiyFilterItem item = items.get(j);
                if (item == null || StringUtils.isBlank(item.getField()) || item.getCondition() == null) {
                    continue;
                }
                if (j > 0) {
                    sqlItem.append(" ")
                            .append(item.getAndor())
                            .append(" ");
                }
                sqlItem.append(item.getField())
                        .append(DiyTools.toConditionSql(item));
            }
            if (filters.size() > 1) {
                sqlItem.append(") ");
            }
            if (!sqlItem.toString().equals("AND () ") && !sqlItem.toString().equals("OR () ")) {
                sql.append(sqlItem);
            }
        }
        if (StringUtils.isNotBlank(sql)) {
            sql.insert(0, " WHERE ");
        }
        return sql.toString();
    }

    /**
     * 分组转sql
     */
    public static String toGroupSql(JSONObject data) {
        JSONArray items = data.getJSONArray("items");
        StringBuilder sql = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            JSONObject obj = items.getJSONObject(i);
            String field = obj.getString("field");
            if (StringUtils.isNotBlank(field)) {
                sql.append(" ").append(field);
                if (i + 1 < items.size()) {
                    sql.append(",");
                }
            }
        }
        if (StringUtils.isNotBlank(sql)) {
            sql.insert(0, " GROUP BY");
        }
        return sql.toString();
    }

    /**
     * 包含转sql
     */
    public static String toHavingSql(JSONObject data) {
        String havingSql = data.getString("having");
        StringBuilder sql = new StringBuilder();
        if (StringUtils.isNotBlank(havingSql)) {
            sql.append(" HAVING ").append(havingSql.trim());
        }
        return sql.toString();
    }

    /**
     * 排序转sql
     */
    public static String toOrderSql(JSONObject data) {
        JSONArray items = data.getJSONArray("items");
        StringBuilder sql = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            JSONObject obj = items.getJSONObject(i);
            String field = obj.getString("field");
            if (StringUtils.isNotBlank(field)) {
                sql.append(" ")
                        .append(field)
                        .append(" ")
                        .append(obj.getString("order"));
                if (i + 1 < items.size()) {
                    sql.append(",");
                }
            }
        }
        if (StringUtils.isNotBlank(sql)) {
            sql.insert(0, " GROUP BY");
        }
        return sql.toString();
    }

    /**
     * 分页转sql
     */
    public static String toLimitSql(JSONObject data) {
        Integer havingSql = data.getInteger("offset");
        Integer rows = data.getInteger("rows");
        return " LIMIT " + havingSql + "," + rows;
    }

    /**
     * 获取规则sql
     * @param diyProcess 流程
     * @param diyNode    节点
     */
    public static String getRuleSql(DiyProcess diyProcess, DiyNode diyNode) {
        List<DiyNode> ruleNodes = getRuleNodes(diyProcess, diyNode);
        if (ruleNodes == null || ruleNodes.size() == 0) {
            return null;
        }
        String whereSql = null;
        String groupSql = null;
        String havingSql = null;
        String orderSql = null;
        String limitSql = null;
        for (DiyNode ruleNode : ruleNodes) {
            String shape = ruleNode.getShape();
            JSONObject jsonObject = (JSONObject) ruleNode.getData();
            if (jsonObject != null) {
                switch (shape) {
                    case "filter-table-node":
                        whereSql = toFilterSql(jsonObject);
                        break;
                    case "group-table-node":
                        groupSql = toGroupSql(jsonObject);
                        break;
                    case "having-table-node":
                        havingSql = toHavingSql(jsonObject);
                        break;
                    case "order-table-node":
                        orderSql = toOrderSql(jsonObject);
                        break;
                    case "limit-table-node":
                        limitSql = toLimitSql(jsonObject);
                        break;
                }
            }
        }
        String sql = "";
        if (StringUtils.isNotBlank(whereSql)) {
            sql += whereSql;
        }
        if (StringUtils.isNotBlank(groupSql)) {
            sql += groupSql;
        }
        if (StringUtils.isNotBlank(havingSql)) {
            sql += havingSql;
        }
        if (StringUtils.isNotBlank(orderSql)) {
            sql += orderSql;
        }
        if (StringUtils.isNotBlank(limitSql)) {
            sql += limitSql;
        }
        return sql;
    }

    /**
     * 获取规则节点
     */
    private static List<DiyNode> getRuleNodes(DiyProcess diyProcess, DiyNode diyNode) {
        String ruleInId = diyProcess.findNodePortId(diyNode, "ruleIn");
        if (StringUtils.isNotBlank(ruleInId)) {
            List<DiyEdge> edges = diyProcess.getIncomingEdges(diyNode);
            if (edges != null && edges.size() > 0) {
                List<DiyNode> ruleNodes = new ArrayList<>();
                for (DiyEdge edge : edges) {
                    String portId = edge.getTarget().getPort();
                    if (StringUtils.isNotBlank(portId) && portId.equals(ruleInId)) {
                        ruleNodes.add(diyProcess.findNode(edge.getSource().getCell()));
                    }
                }
                return ruleNodes;
            }
        }
        return null;
    }
}
