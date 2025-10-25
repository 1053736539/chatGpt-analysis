package com.cb.diy.converter;

import com.alibaba.fastjson.JSONObject;
import com.cb.common.utils.StringUtils;
import com.cb.diy.DiyTools;
import com.cb.diy.model.DiyNode;
import com.cb.diy.model.DiyProcess;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 连接
 * @author xiehong
 */
@Component
public class TableSqlConverter extends AbstractSqlConverter {
    @Override
    public String getShape() {
        return "table-node";
    }

    @Override
    public String getSql(DiyProcess diyProcess, DiyNode diyNode) throws Exception {
        List<DiyNode> prevNodes = diyProcess.findPreviousNode(diyNode, "join-table-node");
        if (prevNodes != null && prevNodes.size() > 0) {
            return null;
        }
        JSONObject nodeData = (JSONObject) diyNode.getData();
        String tableName = nodeData.getString("tableName");
        if (StringUtils.isBlank(tableName)) {
            throw new Exception("表名不存在");
        }
        String alias = nodeData.getString("alias");
        if (StringUtils.isBlank(alias)) {
            throw new Exception("表别名不存在");
        }
        String sql = "";
        String whereSql = DiyTools.getRuleSql(diyProcess, diyNode);
        if (StringUtils.isNotBlank(whereSql)) {
            sql += " (SELECT * FROM " + tableName + whereSql + ") AS " + alias;
        } else {
            sql += tableName + " AS " + alias;
        }
        return sql;
    }
}
