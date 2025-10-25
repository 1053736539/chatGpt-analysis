package com.cb.diy.service;

import com.cb.diy.domain.DiyModel;

import java.util.List;

/**
 * DIY模型
 * @author xiehong
 */
public interface DiyModelService {
    /**
     * 查询DIY模型
     * @param modelId DIY模型ID
     * @return DIY模型
     */
    DiyModel selectDiyModelById(Long modelId);

    /**
     * 查询DIY模型列表
     * @param diyModel DIY模型
     * @return DIY模型集合
     */
    List<DiyModel> selectDiyModelList(DiyModel diyModel);

    /**
     * 新增DIY模型
     * @param diyModel DIY模型
     * @return 结果
     */
    int insertDiyModel(DiyModel diyModel);

    /**
     * 修改DIY模型
     * @param diyModel DIY模型
     * @return 结果
     */
    int updateDiyModel(DiyModel diyModel);

    /**
     * 批量删除DIY模型
     * @param modelIds 需要删除的DIY模型ID
     * @return 结果
     */
    int deleteDiyModelByIds(Long[] modelIds);

    /**
     * 删除DIY模型信息
     * @param modelId DIY模型ID
     * @return 结果
     */
    int deleteDiyModelById(Long modelId);
}
