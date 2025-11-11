package com.cb.service;

import com.cb.common.core.domain.EsPageResult;
import com.cb.vo.EsField;

import java.util.List;

public interface EsService {
    public <T> T selectDataById(String indexName, String id, Class<T> c);

    public <T> List<T> selectDataList(String indexName, List<EsField> conditionFileds, Class<T> c);

    public <T> EsPageResult<T> selectDataPageList(String indexName, List<EsField> conditionFileds, Class<T> c);

}
