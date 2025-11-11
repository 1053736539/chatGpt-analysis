package com.cb.assess.service;

import java.util.List;

import com.cb.assess.domain.BizAssessIndicatorIsm;
import com.cb.assess.domain.BizAssessIndicatorIsmConfig;

/**
 * 指标配置Service接口
 *
 * @author ouyang
 * @date 2023-11-04
 */
public interface IBizAssessIndicatorIsmConfigService {
    public List<BizAssessIndicatorIsmConfig> selectListByIsmId(String ismId);

    public void batchInsertIndicatorIsmConfig(BizAssessIndicatorIsm ism);

    public int deleteIndicatorIsmConfigById(String ismId);

    public int deleteIndicatorIsmConfigByIds(String[] ismIds);

    /**
     * 同步指标数据到indicatorSnapshot 字段
     * @param list
     * @return
     */
    public int syncIndicatorSnapshot(List<BizAssessIndicatorIsmConfig> list);
}
