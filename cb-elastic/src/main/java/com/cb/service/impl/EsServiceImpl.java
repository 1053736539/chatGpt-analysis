package com.cb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cb.common.core.domain.EsPageResult;
import com.cb.common.core.page.TableSupport;
import com.cb.common.utils.ServletUtils;
import com.cb.service.EsService;
import com.cb.utils.ElasticUtils;
import com.cb.vo.EsField;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EsServiceImpl implements EsService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;


    @Override
    public <T> T selectDataById(String indexName, String id, Class<T> c) {
        Assert.hasLength(indexName, "Elasticsearch exception indexName null");
        Assert.hasLength(id, "Elasticsearch exception id null");
        GetResponse response = null;
        try {
            //设置查询的索引、文档
            GetRequest indexRequest = new GetRequest(indexName, id);
            response = restHighLevelClient.get(indexRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("elasticsearch selectDataById error , meassage = {}", e.getMessage());
            //打印轨迹
            log.error(e.getMessage(), e);
            throw new RuntimeException("elasticsearch selectDataById error , meassage=" + e.getMessage());
        }
        String res = response.getSourceAsString();
        return JSONObject.parseObject(res, c);
    }

    @Override
    public <T> List<T> selectDataList(String indexName, List<EsField> conditionFileds, Class<T> c) {
        Assert.hasLength(indexName, "Elasticsearch exception indexName null");
        Assert.notNull(c, "Class<T>  is null ");
        List<T> res = null;
        try {
            // 创建检索请求
            SearchRequest searchRequest = new SearchRequest();
            // 指定索引
            searchRequest.indices(indexName);
            SearchSourceBuilder searchSourceBuilder = ElasticUtils.buildSearchSourceBuilder(conditionFileds);
            searchRequest.source(searchSourceBuilder);
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            //分析结果
            SearchHit[] hits = searchResponse.getHits().getHits();
            res = new ArrayList<>();
            for (SearchHit hit : hits
            ) {
                String data = hit.getSourceAsString();
                T t = JSONObject.parseObject(data, c);
                log.info("data={}", data);
                res.add(t);
            }
        } catch (Exception e) {
            log.error("elasticsearch selectDataList error , meassage = {}", e.getMessage());
            //打印轨迹
            log.error(e.getMessage(), e);
            throw new RuntimeException("elasticsearch selectDataList error , meassage=" + e.getMessage());
        }
        return res;
    }

    @Override
    public <T> EsPageResult<T> selectDataPageList(String indexName, List<EsField> conditionFileds, Class<T> c) {
        Assert.hasLength(indexName, "Elasticsearch exception indexName null");
        Assert.notNull(c, "Class<T>  is null ");
        Integer pageNum = ServletUtils.getParameterToInt(TableSupport.PAGE_NUM);
        Integer pageSize = ServletUtils.getParameterToInt(TableSupport.PAGE_SIZE);
        if(pageNum ==null || pageSize ==null){
            throw new RuntimeException("分页参数异常");
        }
        List<T> res = null;
        //总记录数
        Integer total = 0;
        try {
            // 创建检索请求
            SearchRequest searchRequest = new SearchRequest();
            // 指定索引
            searchRequest.indices(indexName);
            SearchSourceBuilder searchSourceBuilder = ElasticUtils.buildSearchSourceBuilder(conditionFileds);
            conditionFileds.forEach(f -> {
                // 设置高亮
                HighlightBuilder highlightBuilder = new HighlightBuilder();
                highlightBuilder.preTags("<font color='red'>");//设置标签前缀
                highlightBuilder.postTags("</font>");//设置标签后缀
                highlightBuilder.field(f.getField());
                searchSourceBuilder.highlighter(highlightBuilder);
            });
            //设置分页
            searchSourceBuilder.from((pageNum - 1) * pageSize).size(pageSize);
            searchRequest.source(searchSourceBuilder);
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            //分析结果
            SearchHit[] hits = searchResponse.getHits().getHits();
            total = new Long(searchResponse.getHits().getTotalHits().value).intValue();
            res = new ArrayList<>();
            for (SearchHit hit : hits
            ) {
                String data = hit.getSourceAsString();
                T t = JSONObject.parseObject(data, c);
                log.info("data={}", data);
                res.add(t);
            }
        } catch (Exception e) {
            log.error("elasticsearch selectDataPage error , meassage = {}", e.getMessage());
            //打印轨迹
            log.error(e.getMessage(), e);
            throw new RuntimeException("elasticsearch selectDataPage error , meassage=" + e.getMessage());
        }
        return new EsPageResult<>(total, res);
    }
}
