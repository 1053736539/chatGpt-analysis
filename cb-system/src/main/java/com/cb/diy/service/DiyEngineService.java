package com.cb.diy.service;

import com.alibaba.fastjson.JSONArray;
import com.cb.diy.model.resp.IndicatorData;
import com.cb.diy.model.resp.WarningData;

import java.util.List;
import java.util.Map;

/**
 * DIY引擎
 * @author xiehong
 */
public interface DiyEngineService {

    /**
     * 获取模型启动参数
     * @param id 模型ID
     */
    List<Map<String, Object>> getModelStartParams(Long id);

    /**
     * 获取指标启动参数
     * @param id 指标ID
     */
    JSONArray getStartParams(Long id);

    /**
     * 运行模块流程
     */
    List<WarningData> runModel(Long id, Map<Long, Map<String, Object>> params) throws Exception;

    /**
     * 运行指标流程
     * @param id        指标ID
     * @param variables 流程变量
     */
    IndicatorData runIndicator(Long id, Map<String, Object> variables) throws Exception;

    /**
     * 运行指标
     * @param name      指标名称
     * @param process   流程
     * @param variables 变量
     */
    IndicatorData runIndicator(String name, String process, Map<String, Object> variables) throws Exception;
}
