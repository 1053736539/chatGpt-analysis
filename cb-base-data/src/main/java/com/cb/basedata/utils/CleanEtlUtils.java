package com.cb.basedata.utils;

import com.alibaba.fastjson.JSONObject;
import com.cb.ai.client.AIClient;
import com.cb.ai.domain.AiSkillModel;
import com.cb.basedata.domain.BasicDataCleanTask;
import com.cb.basedata.domain.BasicDataCleanTaskRelation;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.spring.SpringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.table.domain.Columns;
import com.cb.table.domain.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 数据清洗工具类
 */
public class CleanEtlUtils {
    private static final Logger log = LoggerFactory.getLogger(CleanEtlUtils.class);
    public static final String DEFAULT_AI_MODEL_CODE = "source_field_fill"; // 默认AI model编码
    public static final String DEFAULT_PRIMARY_KEY_COLUMN = "id"; // 默认主键
    public static final String[] BASE_ENTITY_COLUMNS = {"create_by", "create_time", "update_by", "update_time", "remark"};
    private static AIClient aiClient;

    static {
        aiClient = SpringUtils.getBean(AIClient.class);
    }


    /**
     * 获取字段
     *
     * @param table
     * @return
     */
    public static List<String> buildTableColumns(Table table) {
        List<Columns> tableColumns = table.getColumns();
        List<String> columnNameList = tableColumns.stream()
                .map(columns -> columns.getColumnName().toLowerCase()).collect(Collectors.toList());
        return columnNameList;
    }

    /**
     * @param dataMap            源数据
     * @param targetTableColumns 目标数据表Columns
     * @param isUpdate           是否更新数据
     * @return
     */
    public static Map<String, Object> buildData(Map<String, Object> dataMap, List<String> targetTableColumns, Boolean isUpdate, SysUser user) {
        HashMap<String, Object> resultMap = new HashMap<>();
        for (String column : targetTableColumns) {
            if (isUpdate) {
                if (Arrays.asList(BASE_ENTITY_COLUMNS).contains(column)) {
                    switch (column) {
                        case "update_by":
                            if (isUpdate) {
                                resultMap.put(column, user.getUserName());
                            }
                            break;
                        case "update_time":
                            if (isUpdate) {
                                resultMap.put(column, DateUtils.getNowDate());
                            }
                            break;
                        default:
                            if (isUpdate) {
                                String.format("%s 更新该条数据", DateUtils.getNowDate());
                            }
                    }
                } else {
                    Object value = dataMap.get(column);
                    resultMap.put(column, value);
                }
            } else {
                if (column.equals(DEFAULT_PRIMARY_KEY_COLUMN)||column.toLowerCase().equals(DEFAULT_PRIMARY_KEY_COLUMN)) {
                    resultMap.put(column, IdUtils.randomUUID());
                } else if (Arrays.asList(BASE_ENTITY_COLUMNS).contains(column)||Arrays.asList(BASE_ENTITY_COLUMNS).contains(column.toLowerCase())) {
                    switch (column) {
                        case "create_by":
                            resultMap.put(column, user.getUserName());
                            break;
                        case "create_time":
                            resultMap.put(column, DateUtils.getNowDate());
                            break;
                        default:
                    }
                } else {
                    Object value = dataMap.get(column);
                    resultMap.put(column, value);
                }
            }
        }
        return resultMap;
    }

    /**
     * 构建清洗数据
     *
     * @param task
     * @param sourceDataMap
     * @param targetDataMap
     */
    public static void buildCleanData(BasicDataCleanTask task, Map<String, Object> sourceDataMap, Map<String, Object> targetDataMap, List<AiSkillModel> skillModelList) {
        List<BasicDataCleanTaskRelation> relationList = task.getBasicDataCleanTaskRelationList();
        for (BasicDataCleanTaskRelation relation : relationList) {
            String aiModeCode = relation.getAiModeCode();

            String sourceColumn = relation.getSourceColumn();
            Object sourceColumnValue = sourceDataMap.get(sourceColumn);

            String targetColumn = relation.getTargetColumn();
            if (DEFAULT_AI_MODEL_CODE.equals(aiModeCode) && StringUtils.isNotBlank(targetColumn)) {
                String lowerCaseColumn = targetColumn.toLowerCase();
                targetDataMap.put(lowerCaseColumn,sourceColumnValue);
            } else {
                AiSkillModel model = skillModelList.stream().filter(m -> aiModeCode.equals(m.getModelCode())).findFirst().orElse(null);
                if (model == null) {
                    throw new IllegalArgumentException("未查询到配置的AI技能模型！！");
                }
                String sourceColumnData = String.valueOf(sourceColumnValue);
                if (sourceColumnValue != null) {
                    String tipWords = buildAiSkillTipWords(model, sourceColumnData);
                    JSONObject json = aiClient.generalDataCleanByText(tipWords, model.getOutValueScheme());
                    JSONObject outValueScheme = json.getJSONObject(sourceColumnData);
                    // 获取目标表字段 目前是json
                    JSONObject targetJson = JSONObject.parseObject(targetColumn);
                    for (String s : targetJson.keySet()) {
                        if(StringUtils.isNotBlank(s)){
                            String column = String.valueOf(targetJson.get(s)).toLowerCase();
                            targetDataMap.put(column, outValueScheme.getString(s));
                        }
                    }
                }
            }
        }
    }

    /**
     * 构建AI 技能模型提示词
     *
     * @param model
     * @return
     */
    public static String buildAiSkillTipWords(AiSkillModel model, String text) {
        String tipWords = model.getTipWords();
        String outValueScheme = model.getOutValueScheme();
        tipWords = tipWords.replace("{out_value_scheme}", outValueScheme).replace("{input}", text);
        return tipWords;
    }


    /**
     * 根据表名获取主键
     *
     * @param primaryKeys
     * @return
     */
    public static List<String> getTablePrimaryKeyColumn(String primaryKeys) {
        ArrayList<String> primaryKeyList = new ArrayList<>();
        if (StringUtils.isNotBlank(primaryKeys)) {
            if (primaryKeys.contains(",")) {
                String[] split = primaryKeys.split(",");
                List<String> list = Arrays.asList(split).stream()
                        .map(o ->o.toLowerCase()).collect(Collectors.toList());
                primaryKeyList.addAll(list);
            } else {
                primaryKeyList.add(primaryKeys);
            }
        }
        return primaryKeyList;
    }


    /**
     * 构建查询条件
     *
     * @param condition
     * @return
     */
    public static String buildConditions(String condition) {
        String defaultCondition = "1=1 ";
        return StringUtils.isEmpty(condition) ? defaultCondition : defaultCondition + condition;
    }

    /**
     * 否见主键查询条件
     *
     * @param primaryKeyColumn
     * @param dataMap
     * @return
     */
    public static String buildPrimaryKeyConditions(List<String> primaryKeyColumn, Map<String, Object> dataMap) {
        List<String> conditionList = new ArrayList<>();
        for (String con : primaryKeyColumn) {
            String condition = String.format("and %s='%s'", con, dataMap.get(con));
            conditionList.add(condition);
        }
        return String.join(" ", conditionList);
    }
}
