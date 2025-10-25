package com.cb.diy.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ReUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cb.common.utils.StringUtils;
import com.cb.diy.converter.AbstractSqlConverter;
import com.cb.diy.domain.DiyModel;
import com.cb.diy.domain.DiyModelIndicator;
import com.cb.diy.mapper.DiyModelIndicatorMapper;
import com.cb.diy.mapper.DiyModelMapper;
import com.cb.diy.model.DiyColumns;
import com.cb.diy.model.DiyNode;
import com.cb.diy.model.DiyProcess;
import com.cb.diy.model.SqlNode;
import com.cb.diy.model.resp.IndicatorData;
import com.cb.diy.model.resp.WarningData;
import com.cb.diy.service.DiyEngineService;
import com.cb.table.mapper.TableMapper;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * DIY引擎
 * @author xiehong
 */
@Service
public class DiyEngineServiceImpl implements DiyEngineService {
    private final DiyModelIndicatorMapper modelIndicatorMapper;
    private final DiyModelMapper modelMapper;
    private final List<AbstractSqlConverter> sqlConverters;
    private final TableMapper tableMapper;

    public DiyEngineServiceImpl(DiyModelIndicatorMapper modelIndicatorMapper, DiyModelMapper modelMapper, List<AbstractSqlConverter> sqlConverters, TableMapper tableMapper) {
        this.modelIndicatorMapper = modelIndicatorMapper;
        this.modelMapper = modelMapper;
        this.sqlConverters = sqlConverters;
        this.tableMapper = tableMapper;
    }

    /**
     * 运行模型指标流程
     * @param id 指标ID
     */
    @Override
    public List<WarningData> runModel(Long id, Map<Long, Map<String, Object>> params) throws Exception {
        if(id == null) {
            throw new Exception("模型ID不存在");
        }
        List<WarningData> warningDatas = new ArrayList<>();
        DiyModelIndicator modelIndicator = new DiyModelIndicator();
        modelIndicator.setModelId(id);
        List<DiyModelIndicator> modelIndicators = modelIndicatorMapper.selectDiyModelIndicatorList(modelIndicator);
        if (modelIndicators != null && modelIndicators.size() > 0) {
            for (DiyModelIndicator indicator : modelIndicators) {
                Map<String, Object> variables = params.get(indicator.getId());
                WarningData warningData = new WarningData();
                warningData.setId(indicator.getId());
                warningData.setName(indicator.getName());
                warningData.setTotal(0);
                DiyProcess diyProcess = DiyProcess.newInstance(indicator.getProcess());
                DiyNode startNode = diyProcess.getStartNode();
                if (startNode == null) {
                    warningDatas.add(warningData);
                    continue;
                }
                List<SqlNode> sqlNodes = getSqlNodes(diyProcess, startNode, null);
                if (sqlNodes != null && sqlNodes.size() > 0) {
                    StringBuilder sql = new StringBuilder();
                    String whereSql = null;
                    for (SqlNode sqlNode : sqlNodes) {
                        String sqlItem = sqlNode.getSql();
                        if (sqlNode.getShape().equals("execute-node")) {
                            whereSql = sqlItem;
                            break;
                        }
                        if (StringUtils.isNotBlank(sqlItem)) {
                            sql.append(sqlItem);
                        }
                    }
                    if (StringUtils.isNotBlank(sql)) {
                        String sqlStr = sql.toString();
                        if (variables != null && variables.size() > 0) {
                            for (Map.Entry<String, Object> entry : variables.entrySet()) {
                                String val;
                                if (entry.getValue() instanceof Date) {
                                    val = DateUtil.format((Date) entry.getValue(), "yyyy-MM-dd HH:mm:ss");
                                } else if (entry.getValue() instanceof Integer || entry.getValue() instanceof Long) {
                                    val = String.valueOf(entry.getValue());
                                } else {
                                    val = "'" + entry.getValue() + "'";
                                }
                                if (ReUtil.contains("^'?[\\d]{4}-[\\d]{1,2}-[\\d]{1,2}T[\\d]{1,2}:[\\d]{1,2}:[\\d]{1,2}.[\\d]{1,4}Z'?$", val)) {
                                    val = val.replace("T", " ").replace(".000Z", "");
                                }
                                sqlStr = sqlStr.replace("${" + entry.getKey() + "}", val);
                                if (whereSql != null) {
                                    whereSql = whereSql.replace("${" + entry.getKey() + "}", val);
                                }
                            }
                        }
                        Integer total = tableMapper.selectDiyCount(sqlStr, whereSql);
                        warningData.setTotal(total);
                    }
                }
                warningDatas.add(warningData);
            }
        }
        return warningDatas;
    }

    /**
     * 运行模型指标流程
     * @param id 指标ID
     */
    @Override
    public IndicatorData runIndicator(Long id, Map<String, Object> variables) throws Exception {
        DiyModelIndicator modelIndicator = modelIndicatorMapper.selectDiyModelIndicatorById(id);
        return runIndicator(modelIndicator.getName(), modelIndicator.getProcess(), variables);
    }


    /**
     * 运行指标
     * @param name      指标名称
     * @param process   流程
     * @param variables 变量
     */
    @Override
    public IndicatorData runIndicator(String name, String process, Map<String, Object> variables) throws Exception {
        DiyProcess diyProcess = DiyProcess.newInstance(process);
        DiyNode startNode = diyProcess.getStartNode();
        List<SqlNode> sqlNodes = getSqlNodes(diyProcess, startNode, null);
        if (sqlNodes != null && sqlNodes.size() > 0) {
            StringBuilder sql = new StringBuilder();
            String whereSql = null;
            List<JSONObject> tables = new ArrayList<>();
            for (SqlNode sqlNode : sqlNodes) {
                String sqlItem = sqlNode.getSql();
                if (sqlNode.getShape().equals("table-node")) {
                    tables.add((JSONObject) sqlNode.getData());
                }
                if (sqlNode.getShape().equals("execute-node")) {
                    whereSql = sqlItem;
                    break;
                }
                if (StringUtils.isNotBlank(sqlItem)) {
                    sql.append(sqlItem);
                }
            }
            if (StringUtils.isNotBlank(sql)) {
                String sqlStr = sql.toString();
                if (variables != null && variables.size() > 0) {
                    for (Map.Entry<String, Object> entry : variables.entrySet()) {
                        String val;
                        if (entry.getValue() instanceof Date) {
                            val = DateUtil.format((Date) entry.getValue(), "yyyy-MM-dd HH:mm:ss");
                        } else if (entry.getValue() instanceof Integer || entry.getValue() instanceof Long) {
                            val = String.valueOf(entry.getValue());
                        } else {
                            val = "'" + entry.getValue() + "'";
                        }
                        if (ReUtil.contains("^'?[\\d]{4}-[\\d]{1,2}-[\\d]{1,2}T[\\d]{1,2}:[\\d]{1,2}:[\\d]{1,2}.[\\d]{1,4}Z'?$", val)) {
                            val = val.replace("T", " ").replace(".000Z", "");
                        }
                        sqlStr = sqlStr.replace("${" + entry.getKey() + "}", val);
                        if (whereSql != null) {
                            whereSql = whereSql.replace("${" + entry.getKey() + "}", val);
                        }
                    }
                }
                IndicatorData indicatorData = new IndicatorData();
                indicatorData.setName(name);
                indicatorData.setItems(tableMapper.selectDiyList(sqlStr, whereSql));
                indicatorData.setTables(tables);
                List<DiyColumns> columns = new ArrayList<>();
                for (JSONObject jsonObject : tables) {
                    JSONArray array = jsonObject.getJSONArray("columns");
                    for (int i = 0; i < array.size(); i++) {
                        DiyColumns diyColumns = array.getObject(i, DiyColumns.class);
                        columns.add(diyColumns);
                    }
                }
                indicatorData.setColumns(columns);
                return indicatorData;
            }
        }
        return null;
    }

    /**
     * 获取模型启动参数
     * @param id 模型ID
     */
    @Override
    public List<Map<String, Object>> getModelStartParams(Long id) {
        DiyModel model = modelMapper.selectDiyModelById(id);
        DiyModelIndicator modelIndicator = new DiyModelIndicator();
        modelIndicator.setModelId(model.getModelId());
        List<DiyModelIndicator> modelIndicators = modelIndicatorMapper.selectDiyModelIndicatorList(modelIndicator);
        List<Map<String, Object>> listData = new ArrayList<>();
        if (modelIndicators != null && modelIndicators.size() > 0) {
            for (DiyModelIndicator indicator : modelIndicators) {
                Map<String, Object> mapData = new HashMap<>();
                mapData.put("id", indicator.getId());
                mapData.put("name", indicator.getName());
                DiyProcess diyProcess = DiyProcess.newInstance(indicator.getProcess());
                DiyNode startNode = diyProcess.getStartNode();
                if (startNode != null) {
                    JSONObject jsonObject = (JSONObject) startNode.getData();
                    mapData.put("params", jsonObject.getJSONArray("items"));
                }
                listData.add(mapData);
            }
        }
        return listData;
    }

    /**
     * 获取启动参数
     * @param id 指标ID
     */
    @Override
    public JSONArray getStartParams(Long id) {
        DiyModelIndicator modelIndicator = modelIndicatorMapper.selectDiyModelIndicatorById(id);
        DiyProcess diyProcess = DiyProcess.newInstance(modelIndicator.getProcess());
        DiyNode startNode = diyProcess.getStartNode();
        if (startNode != null) {
            JSONObject jsonObject = (JSONObject) startNode.getData();
            return jsonObject.getJSONArray("items");
        }
        return null;
    }

    private List<SqlNode> getSqlNodes(DiyProcess diyProcess, DiyNode diyNode, List<SqlNode> sqlNodes) throws Exception {
        if (sqlNodes == null) {
            sqlNodes = new ArrayList<>();
        }
        List<DiyNode> nodes = diyProcess.getNextNodes(diyNode);
        if (nodes != null && nodes.size() > 0) {
            for (DiyNode node : nodes) {
                for (AbstractSqlConverter sqlConverter : sqlConverters) {
                    if (sqlConverter.getShape().equals(node.getShape())) {
                        String sql = sqlConverter.getSql(diyProcess, node);
                        SqlNode sqlNode = new SqlNode();
                        sqlNode.setId(node.getId());
                        sqlNode.setShape(node.getShape());
                        sqlNode.setData(node.getData());
                        sqlNode.setSql(sql);
                        sqlNodes.add(sqlNode);
                        getSqlNodes(diyProcess, node, sqlNodes);
                    }
                }
            }
            if (sqlNodes.size() > 0) {
                return sqlNodes;
            }
        }
        return null;
    }
}
