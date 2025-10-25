package com.cb.diy.mapper;

import com.cb.diy.domain.DiyModelIndicator;

import java.util.List;

/**
 * DIY模型指标
 * @author xiehong
 */
public interface DiyModelIndicatorMapper {
    /**
     * 查询【请填写功能名称】
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    DiyModelIndicator selectDiyModelIndicatorById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * @param diyModelIndicator 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    List<DiyModelIndicator> selectDiyModelIndicatorList(DiyModelIndicator diyModelIndicator);

    /**
     * 新增【请填写功能名称】
     * @param diyModelIndicator 【请填写功能名称】
     * @return 结果
     */
    int insertDiyModelIndicator(DiyModelIndicator diyModelIndicator);

    /**
     * 修改【请填写功能名称】
     * @param diyModelIndicator 【请填写功能名称】
     * @return 结果
     */
    int updateDiyModelIndicator(DiyModelIndicator diyModelIndicator);

    /**
     * 删除【请填写功能名称】
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    int deleteDiyModelIndicatorById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteDiyModelIndicatorByIds(Long[] ids);
}
