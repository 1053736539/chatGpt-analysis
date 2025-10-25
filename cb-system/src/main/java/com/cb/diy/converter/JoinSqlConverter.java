package com.cb.diy.converter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cb.common.utils.StringUtils;
import com.cb.diy.DiyTools;
import com.cb.diy.model.DiyNode;
import com.cb.diy.model.DiyProcess;
import org.springframework.stereotype.Component;

/**
 * 表连接任务
 * @author xiehong
 */
@Component
public class JoinSqlConverter extends AbstractSqlConverter {
    @Override
    public String getShape() {
        return "join-table-node";
    }

    @Override
    public String getSql(DiyProcess diyProcess, DiyNode diyNode) throws Exception {
        JSONObject nodeData = (JSONObject) diyNode.getData();
        JSONArray items = nodeData.getJSONArray("items");
        if (items == null || items.size() == 0) {
            throw new Exception("没有关联字段");
        }
        DiyNode leftNode = DiyTools.getPreviousTableNode(diyProcess, diyNode);
        DiyNode rightNode = DiyTools.getNextTableNode(diyProcess, diyNode);
        if (leftNode.getData() == null || rightNode.getData() == null) {
            throw new Exception("表不存在");
        }
        JSONObject leftData = (JSONObject) leftNode.getData();
        JSONObject rightData = (JSONObject) rightNode.getData();
        String rightTableName = rightData.getString("tableName");
        if (StringUtils.isBlank(rightTableName)) {
            throw new Exception("表名不存在");
        }
        String leftAlias = leftData.getString("alias"); // 左别名
        String rightAlias = rightData.getString("alias"); // 右别名
        String rightTableSql = "";
        String rightWhereSql = DiyTools.getRuleSql(diyProcess, rightNode);
        if (StringUtils.isNotBlank(rightWhereSql)) {
            rightTableSql += " (SELECT * FROM " + rightTableName + rightWhereSql + ") AS " + rightAlias;
        } else {
            rightTableSql += rightTableName + " AS " + rightAlias;
        }
        StringBuilder sql = new StringBuilder();
        String type = nodeData.getString("type");
        if (type.equals("right")) {
            sql.append(" RIGHT JOIN ");
        } else if (type.equals("inner")) {
            sql.append(" INNER JOIN ");
        } else {
            sql.append(" LEFT JOIN ");
        }
        sql.append(rightTableSql)
                .append(" ON ");
        for (int i = 0; i < items.size(); i++) {
            JSONObject obj = items.getJSONObject(i);
            String leftKey = obj.getString("leftValue");
            String rightKey = obj.getString("rightValue");
            sql.append(leftAlias)
                    .append(".")
                    .append(leftKey)
                    .append(" = ")
                    .append(rightAlias)
                    .append(".")
                    .append(rightKey);
            if (i + 1 < items.size()) {
                sql.append(" AND ");
            }
        }
        return sql.toString();
    }
}
