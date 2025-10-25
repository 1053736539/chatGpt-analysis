package com.cb.diy.service;

import com.cb.diy.domain.DiyModelIndicator;

import java.util.List;

/**
 * DIY模型指标Service接口
 * @author xiehong
 */
public interface DiyModelIndicatorService {
    /**
     * 查询DIY模型指标
     * @param id DIY模型指标ID
     * @return DIY模型指标
     */
    DiyModelIndicator selectDiyModelIndicatorById(Long id);

    /**
     * 查询DIY模型指标列表
     * @param diyModelIndicator DIY模型指标
     * @return DIY模型指标集合
     */
    List<DiyModelIndicator> selectDiyModelIndicatorList(DiyModelIndicator diyModelIndicator);

    /**
     * 新增DIY模型指标
     * @param diyModelIndicator DIY模型指标
     * @return 结果
     */
    int insertDiyModelIndicator(DiyModelIndicator diyModelIndicator);

    /**
     * 修改DIY模型指标
     * @param diyModelIndicator DIY模型指标
     * @return 结果
     */
    int updateDiyModelIndicator(DiyModelIndicator diyModelIndicator);

    /**
     * 批量删除DIY模型指标
     * @param ids 需要删除的DIY模型指标ID
     * @return 结果
     */
    int deleteDiyModelIndicatorByIds(Long[] ids);

    /**
     * 删除DIY模型指标信息
     * @param id DIY模型指标ID
     * @return 结果
     */
    int deleteDiyModelIndicatorById(Long id);
}
