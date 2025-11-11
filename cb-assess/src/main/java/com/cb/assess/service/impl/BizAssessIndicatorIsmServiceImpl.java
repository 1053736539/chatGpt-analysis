package com.cb.assess.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.cb.assess.domain.BizAssessIndicator;
import com.cb.assess.domain.BizAssessIndicatorIsmConfig;
import com.cb.assess.domain.BizAssessIndicatorPro;
import com.cb.assess.domain.vo.IndicatorIsmConfigVo;
import com.cb.assess.mapper.BizAssessIndicatorIsmMapper;
import com.cb.assess.mapper.BizAssessIndicatorMapper;
import com.cb.assess.service.IBizAssessIndicatorIsmConfigService;
import com.cb.assess.service.IBizAssessIndicatorIsmService;
import com.cb.common.utils.DateUtils;
import com.cb.assess.domain.BizAssessIndicatorIsm;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 指标体系Service业务层处理
 *
 * @author ouyang
 * @date 2023-10-27
 */
@Service
public class BizAssessIndicatorIsmServiceImpl implements IBizAssessIndicatorIsmService {
    @Autowired
    private BizAssessIndicatorIsmMapper bizAssessIndicatorIsmMapper;
    @Autowired
    private BizAssessIndicatorMapper indicatorMapper;
    @Autowired
    private IBizAssessIndicatorIsmConfigService configService;

    /**
     * 查询指标体系
     *
     * @param ismId 指标体系ID
     * @return 指标体系
     */
    @Override
    public BizAssessIndicatorIsm selectBizAssessIndicatorIsmById(String ismId) {
        BizAssessIndicatorIsm ism = bizAssessIndicatorIsmMapper.selectBizAssessIndicatorIsmById(ismId);
        int count = bizAssessIndicatorIsmMapper.selectProUseCount(ismId);
        if(count > 0){
            ism.setEnableEdit(false);
        }
        return ism;
    }

    /**
     * 查询指标体系列表
     *
     * @param bizAssessIndicatorIsm 指标体系
     * @return 指标体系
     */
    @Override
    public List<BizAssessIndicatorIsm> selectBizAssessIndicatorIsmList(BizAssessIndicatorIsm bizAssessIndicatorIsm) {
        List<BizAssessIndicatorIsm> list = bizAssessIndicatorIsmMapper.selectBizAssessIndicatorIsmList(bizAssessIndicatorIsm);
        List<String> ismIds = list.stream().map(BizAssessIndicatorIsm::getIsmId).distinct().collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(ismIds)){
            List<BizAssessIndicatorPro> pros = bizAssessIndicatorIsmMapper.selectProUseByIds(ismIds);
            list.forEach(o->{
                String ismId = o.getIsmId();
                long count = pros.stream().filter(k -> k.getProIsmId().equals(ismId)).count();
                if(count > 0){
                    o.setEnableEdit(false);
                }
            });
        }
        return list;
    }

    @Override
    public List<BizAssessIndicatorIsm> selectAllIndicatorIsmList(BizAssessIndicatorIsm bizAssessIndicatorIsm) {
        return bizAssessIndicatorIsmMapper.selectAllIndicatorIsmList(bizAssessIndicatorIsm);
    }

    /**
     * 新增指标体系
     *
     * @param bizAssessIndicatorIsm 指标体系
     * @return 结果
     */
    @Override
    @Transactional
    public int insertBizAssessIndicatorIsm(BizAssessIndicatorIsm bizAssessIndicatorIsm) {
        bizAssessIndicatorIsm.setIsmId(IdUtils.randomUUID());
        bizAssessIndicatorIsm.setCreateBy(SecurityUtils.getUsername());
        bizAssessIndicatorIsm.setCreateTime(DateUtils.getNowDate());
        int row = bizAssessIndicatorIsmMapper.insertBizAssessIndicatorIsm(bizAssessIndicatorIsm);
        configService.batchInsertIndicatorIsmConfig(bizAssessIndicatorIsm);
        return row;
    }

    /**
     * 修改指标体系
     *
     * @param bizAssessIndicatorIsm 指标体系
     * @return 结果
     */
    @Override
    @Transactional
    public int updateBizAssessIndicatorIsm(BizAssessIndicatorIsm bizAssessIndicatorIsm) {
        bizAssessIndicatorIsm.setUpdateTime(DateUtils.getNowDate());
        bizAssessIndicatorIsm.setUpdateBy(SecurityUtils.getUsername());
        int row = bizAssessIndicatorIsmMapper.updateBizAssessIndicatorIsm(bizAssessIndicatorIsm);

        // 当考核体系enableEdit为true 时，才能操作指标体系的指标配置
        if(bizAssessIndicatorIsm.getEnableEdit()){
            configService.deleteIndicatorIsmConfigById(bizAssessIndicatorIsm.getIsmId());
            configService.batchInsertIndicatorIsmConfig(bizAssessIndicatorIsm);
        }
        return row;
    }

    /**
     * 批量删除指标体系
     *
     * @param ismIds 需要删除的指标体系ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessIndicatorIsmByIds(String[] ismIds) {
        return bizAssessIndicatorIsmMapper.deleteBizAssessIndicatorIsmByIds(ismIds);
    }

    /**
     * 删除指标体系信息
     *
     * @param ismId 指标体系ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessIndicatorIsmById(String ismId) {
        return bizAssessIndicatorIsmMapper.deleteBizAssessIndicatorIsmById(ismId);
    }

    @Override
    public int logicDeleteBizAssessIndicatorIsmByIds(String[] ismIds) {
        return bizAssessIndicatorIsmMapper.logicDeleteBizAssessIndicatorIsmByIds(ismIds);
    }

    @Override
    public int logicDeleteBizAssessIndicatorIsmById(String ismId) {
        return bizAssessIndicatorIsmMapper.logicDeleteBizAssessIndicatorIsmById(ismId);
    }

    @Override
    public Map<String, Object> selectIndicatorIsmConfigVoMapByIsmId(String ismId) {
        List<BizAssessIndicatorIsmConfig> configList = configService.selectListByIsmId(ismId);
        List<IndicatorIsmConfigVo> voList = configList.stream().map(item -> {
            IndicatorIsmConfigVo vo = new IndicatorIsmConfigVo();
            vo.setConfigId(item.getConfigId());
            vo.setIndicatorType(item.getIndicatorType());
            String content = item.getContent();
            String snapshot = item.getIndicatorSnapshot();
            if(StringUtils.isNotBlank(snapshot)){
                List<BizAssessIndicator> indicators = JSON.parseArray(snapshot, BizAssessIndicator.class);
                vo.setIndicatorList(indicators);
            }else{
                 if (StringUtils.isNotEmpty(content)) {
                    List<String> indIds = JSON.parseArray(content, String.class);
                    List<BizAssessIndicator> list = indicatorMapper.selectIndicatorListByIds(indIds);
                    vo.setIndicatorList(list);
                }
            }
            vo.setScoreWeight(item.getScoreWeight());
            return vo;
        }).collect(Collectors.toList());
        Map<String, Object> map = new HashMap<>();
        map.put("configVoList", voList);
        return map;
    }

    @Override
    public List<IndicatorIsmConfigVo> selectIndicatorIsmConfigVoListByIsmId(String ismId) {
        List<BizAssessIndicatorIsmConfig> configList = configService.selectListByIsmId(ismId);
        List<IndicatorIsmConfigVo> voList = configList.stream().map(item -> {
            IndicatorIsmConfigVo vo = new IndicatorIsmConfigVo();
            vo.setConfigId(item.getConfigId());
            vo.setIndicatorType(item.getIndicatorType());
            String content = item.getContent();
            String snapshot = item.getIndicatorSnapshot();
            if(StringUtils.isNotBlank(snapshot)){
                List<BizAssessIndicator> indicators = JSON.parseArray(snapshot, BizAssessIndicator.class);
                vo.setIndicatorList(indicators);
            }else{
                if (StringUtils.isNotEmpty(content)) {
                    List<String> indIds = JSON.parseArray(content, String.class);
                    List<BizAssessIndicator> list = indicatorMapper.selectIndicatorListByIds(indIds);
                    vo.setIndicatorList(list);
                }
            }
            vo.setScoreWeight(item.getScoreWeight());
            return vo;
        }).collect(Collectors.toList());
        return voList;
    }

    @Override
    public int syncProIndicatorSnapshot(String ismId) {
        List<BizAssessIndicatorIsmConfig> list = configService.selectListByIsmId(ismId);
        list.forEach(o->{
            String content = o.getContent();
            if(StringUtils.isNotBlank(content)){
                List<String> indIds = JSON.parseArray(content, String.class);
                List<BizAssessIndicator> indList = indicatorMapper.selectIndicatorListByIds(indIds);
                o.setIndicatorSnapshot(JSON.toJSONString(indList));
            }
        });
        return configService.syncIndicatorSnapshot(list);
    }
}
