package com.cb.utils;

import com.alibaba.fastjson.JSONObject;
import com.cb.vo.EsField;
import lombok.extern.slf4j.Slf4j;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * es工具类型
 *
 * @author lsj
 * @date 2021/11/3 15:17
 */
@Slf4j
public class ElasticUtils {

    /**
     * 创建搜索条件
     *
     * @param conditionFileds
     * @return
     */
    public static SearchSourceBuilder buildSearchSourceBuilder(List<EsField> conditionFileds) {
        // 指定DSL
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        //构建条件
        if (!CollectionUtils.isEmpty(conditionFileds)) {
            for (EsField condFiled :
                    conditionFileds) {
                switch (condFiled.getFieldTypeEnum()) {
                    case ORDER_ASC:
                        //升序
                        searchSourceBuilder.sort(condFiled.getField(), SortOrder.ASC);
                        break;
                    case ORDER_ESC:
                        //降序
                        searchSourceBuilder.sort(condFiled.getField(), SortOrder.DESC);
                        break;
                    case VAGUE_QUERY:
                        // 模糊查询
                        boolQuery.should(QueryBuilders.matchQuery(condFiled.getField(), condFiled.getValue()).operator(Operator.OR));
                        break;
                    case PRECISE_QUERY:
                        boolQuery.should(QueryBuilders.matchQuery(condFiled.getField(), condFiled.getValue()).operator(Operator.AND));
                        break;
                    default:
                        //默认精准查询
                        boolQuery.should(QueryBuilders.matchQuery(condFiled.getField(), condFiled.getValue()).operator(Operator.AND));
                        break;
                }
            }
        }
        searchSourceBuilder.query(boolQuery);
        return searchSourceBuilder;
    }


    /**
     * 构建删除条件
     *
     * @param conditionFileds
     * @param request
     */
    public static void setDeletCondition(List<EsField> conditionFileds, DeleteByQueryRequest request) {
        if (!CollectionUtils.isEmpty(conditionFileds)) {
            for (EsField condFiled :
                    conditionFileds) {
                switch (condFiled.getFieldTypeEnum()) {
                    case VAGUE_QUERY:
//                           模糊查询
                        request.setQuery(QueryBuilders.matchQuery(condFiled.getField(), condFiled.getValue()).fuzziness(Fuzziness.AUTO));
                        break;
                    case PRECISE_QUERY:
                        request.setQuery(QueryBuilders.matchQuery(condFiled.getField(), condFiled.getValue()));
                        break;
                    default:
                        log.error("field type error ,only supprt VAGUE_QUERY and PRECISE_QUERY");
                        throw new RuntimeException(" field type error ,only supprt VAGUE_QUERY and PRECISE_QUERY");
                }
            }
        }
    }

    /**
     * 构建修改条件
     *
     * @param conditionFileds
     * @param request
     */
    public static void setUpdateConfition(List<EsField> conditionFileds, UpdateByQueryRequest request) {
        if (!CollectionUtils.isEmpty(conditionFileds)) {
            for (EsField condFiled :
                    conditionFileds) {
                switch (condFiled.getFieldTypeEnum()) {
                    case VAGUE_QUERY:
//                           模糊查询
                        request.setQuery(QueryBuilders.matchQuery(condFiled.getField(), condFiled.getValue()).fuzziness(Fuzziness.AUTO));
                        break;
                    case PRECISE_QUERY:
                        request.setQuery(QueryBuilders.matchQuery(condFiled.getField(), condFiled.getValue()));
                        break;
                    default:
                        log.error("field type error ,only supprt VAGUE_QUERY and PRECISE_QUERY");
                        throw new RuntimeException(" field type error ,only supprt VAGUE_QUERY and PRECISE_QUERY");
                }
            }
        }
    }


    /**
     * 创建修改script内容
     *
     * @param data
     * @return
     */
    public static StringBuffer buildScriptContext(Object data) {
        //准备文档
        String jsonString = JSONObject.toJSONString(data);
        Map<String, String> jsonMap = JSONObject.parseObject(jsonString, Map.class);
        String tem = "ctx._source['key']='value'";
        StringBuffer scriptContext = new StringBuffer();
        for (String key :
                jsonMap.keySet()) {
            String value = jsonMap.get(key);
            scriptContext.append(tem.replace("key", key).replace("value", value))
                    .append(";");

        }
        return scriptContext;
    }
}