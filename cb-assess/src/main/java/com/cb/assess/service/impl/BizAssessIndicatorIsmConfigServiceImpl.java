package com.cb.assess.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.cb.assess.domain.BizAssessIndicatorIsm;
import com.cb.assess.domain.BizAssessIndicatorIsmConfig;
import com.cb.assess.domain.BizAssessIndicatorPro;
import com.cb.assess.domain.BizAssessIndicatorProRatGroup;
import com.cb.assess.mapper.BizAssessIndicatorIsmConfigMapper;
import com.cb.assess.service.IBizAssessIndicatorIsmConfigService;
import com.cb.common.exception.CustomException;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * 指标配置Service业务层处理
 *
 * @author ouyang
 * @date 2023-11-04
 */
@Service
public class BizAssessIndicatorIsmConfigServiceImpl implements IBizAssessIndicatorIsmConfigService {
    @Autowired
    private BizAssessIndicatorIsmConfigMapper configMapper;


    @Override
    public List<BizAssessIndicatorIsmConfig> selectListByIsmId(String ismId) {
        return configMapper.selectListByIsmId(ismId);
    }

    @Override
    public int deleteIndicatorIsmConfigById(String ismId) {
        return configMapper.deleteIndicatorIsmConfigById(ismId);
    }

    @Override
    public int deleteIndicatorIsmConfigByIds(String[] ismIds) {
        return configMapper.deleteIndicatorIsmConfigByIds(ismIds);
    }

    @Override
    public int syncIndicatorSnapshot(List<BizAssessIndicatorIsmConfig> list) {
        list.forEach(o->{
            configMapper.updateIndicatorIsmConfig(o.getConfigId(),o.getIndicatorSnapshot());
        });
        return 1;
    }


    public void batchInsertIndicatorIsmConfig(BizAssessIndicatorIsm ism) {
        List<BizAssessIndicatorIsmConfig> configList = ism.getConfigList();
        checkConfig(ism);
        String ismId = ism.getIsmId();
        if (StringUtils.isNotNull(configList)) {
            List<BizAssessIndicatorIsmConfig> list = new ArrayList<>();
            for (BizAssessIndicatorIsmConfig config : configList) {
                config.setConfigId(IdUtils.randomUUID());
                config.setIsmId(ismId);
                list.add(config);
            }
            if (list.size() > 0) {
                configMapper.batchInsertIndicatorIsmConfig(list);
            }
        }
    }
    private void checkConfig(BizAssessIndicatorIsm ism) {
        List<BizAssessIndicatorIsmConfig> configList = ism.getConfigList();
        if(CollectionUtils.isEmpty(configList)){
            throw new CustomException("您未设置指标配置！");
        }
        List<String> collect = configList.stream().map(BizAssessIndicatorIsmConfig::getIndicatorType).distinct().collect(Collectors.toList());
        if (configList.size() > collect.size())
            throw new CustomException("指标分类配置有重复项");

        int sum = configList.stream().mapToInt(BizAssessIndicatorIsmConfig::getScoreWeight).reduce(0, Integer::sum);
        if (sum > 100)
            throw new CustomException("分值权重配置项超过100%，请重新配置！");
    }
}
