package com.cb.diy.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.diy.domain.DiyModel;
import com.cb.diy.mapper.DiyModelMapper;
import com.cb.diy.service.DiyModelService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DIY模型
 * @author xiehong
 */
@Service
public class DiyModelServiceImpl implements DiyModelService {
    private final DiyModelMapper diyModelMapper;

    public DiyModelServiceImpl(DiyModelMapper diyModelMapper) {
        this.diyModelMapper = diyModelMapper;
    }

    /**
     * 查询DIY模型
     * @param modelId DIY模型ID
     * @return DIY模型
     */
    @Override
    public DiyModel selectDiyModelById(Long modelId) {
        return diyModelMapper.selectDiyModelById(modelId);
    }

    /**
     * 查询DIY模型列表
     * @param diyModel DIY模型
     * @return DIY模型
     */
    @Override
    public List<DiyModel> selectDiyModelList(DiyModel diyModel) {
        return diyModelMapper.selectDiyModelList(diyModel);
    }

    /**
     * 新增DIY模型
     * @param diyModel DIY模型
     * @return 结果
     */
    @Override
    public int insertDiyModel(DiyModel diyModel) {
        diyModel.setCreateTime(DateUtils.getNowDate());
        return diyModelMapper.insertDiyModel(diyModel);
    }

    /**
     * 修改DIY模型
     * @param diyModel DIY模型
     * @return 结果
     */
    @Override
    public int updateDiyModel(DiyModel diyModel) {
        diyModel.setUpdateTime(DateUtils.getNowDate());
        return diyModelMapper.updateDiyModel(diyModel);
    }

    /**
     * 批量删除DIY模型
     * @param modelIds 需要删除的DIY模型ID
     * @return 结果
     */
    @Override
    public int deleteDiyModelByIds(Long[] modelIds) {
        return diyModelMapper.deleteDiyModelByIds(modelIds);
    }

    /**
     * 删除DIY模型信息
     * @param modelId DIY模型ID
     * @return 结果
     */
    @Override
    public int deleteDiyModelById(Long modelId) {
        return diyModelMapper.deleteDiyModelById(modelId);
    }
}
