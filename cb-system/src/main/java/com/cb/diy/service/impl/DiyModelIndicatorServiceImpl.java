package com.cb.diy.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.diy.domain.DiyModelIndicator;
import com.cb.diy.mapper.DiyModelIndicatorMapper;
import com.cb.diy.service.DiyModelIndicatorService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DIY模型指标Service业务层处理
 * @author xiehong
 */
@Service
public class DiyModelIndicatorServiceImpl implements DiyModelIndicatorService {
    private final DiyModelIndicatorMapper diyModelIndicatorMapper;

    public DiyModelIndicatorServiceImpl(DiyModelIndicatorMapper diyModelIndicatorMapper) {
        this.diyModelIndicatorMapper = diyModelIndicatorMapper;
    }

    /**
     * 查询DIY模型指标
     * @param id DIY模型指标ID
     * @return DIY模型指标
     */
    @Override
    public DiyModelIndicator selectDiyModelIndicatorById(Long id) {
        return diyModelIndicatorMapper.selectDiyModelIndicatorById(id);
    }

    /**
     * 查询DIY模型指标列表
     * @param diyModelIndicator DIY模型指标
     * @return DIY模型指标
     */
    @Override
    public List<DiyModelIndicator> selectDiyModelIndicatorList(DiyModelIndicator diyModelIndicator) {
        return diyModelIndicatorMapper.selectDiyModelIndicatorList(diyModelIndicator);
    }

    /**
     * 新增DIY模型指标
     * @param diyModelIndicator DIY模型指标
     * @return 结果
     */
    @Override
    public int insertDiyModelIndicator(DiyModelIndicator diyModelIndicator) {
        diyModelIndicator.setCreateTime(DateUtils.getNowDate());
        return diyModelIndicatorMapper.insertDiyModelIndicator(diyModelIndicator);
    }

    /**
     * 修改DIY模型指标
     * @param diyModelIndicator DIY模型指标
     * @return 结果
     */
    @Override
    public int updateDiyModelIndicator(DiyModelIndicator diyModelIndicator) {
        diyModelIndicator.setUpdateTime(DateUtils.getNowDate());
        return diyModelIndicatorMapper.updateDiyModelIndicator(diyModelIndicator);
    }

    /**
     * 批量删除DIY模型指标
     * @param ids 需要删除的DIY模型指标ID
     * @return 结果
     */
    @Override
    public int deleteDiyModelIndicatorByIds(Long[] ids) {
        return diyModelIndicatorMapper.deleteDiyModelIndicatorByIds(ids);
    }

    /**
     * 删除DIY模型指标信息
     * @param id DIY模型指标ID
     * @return 结果
     */
    @Override
    public int deleteDiyModelIndicatorById(Long id) {
        return diyModelIndicatorMapper.deleteDiyModelIndicatorById(id);
    }
}
