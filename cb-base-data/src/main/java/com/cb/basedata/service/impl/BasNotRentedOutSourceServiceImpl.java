package com.cb.basedata.service.impl;

import java.util.List;

import com.cb.basedata.domain.BasNotRentedOutSource;
import com.cb.basedata.mapper.BasNotRentedOutSourceMapper;
import com.cb.basedata.service.IBasNotRentedOutSourceService;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.uuid.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 代码-昆明市主城区未配租数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-30
 */
@Service
public class BasNotRentedOutSourceServiceImpl implements IBasNotRentedOutSourceService
{
    @Autowired
    private BasNotRentedOutSourceMapper basNotRentedOutSourceMapper;

    /**
     * 查询代码-昆明市主城区未配租数据
     * 
     * @param sourceId 代码-昆明市主城区未配租数据ID
     * @return 代码-昆明市主城区未配租数据
     */
    @Override
    public BasNotRentedOutSource selectBasNotRentedOutSourceById(String sourceId)
    {
        return basNotRentedOutSourceMapper.selectBasNotRentedOutSourceById(sourceId);
    }

    /**
     * 查询代码-昆明市主城区未配租数据列表
     * 
     * @param basNotRentedOutSource 代码-昆明市主城区未配租数据
     * @return 代码-昆明市主城区未配租数据
     */
    @Override
    public List<BasNotRentedOutSource> selectBasNotRentedOutSourceList(BasNotRentedOutSource basNotRentedOutSource)
    {
        return basNotRentedOutSourceMapper.selectBasNotRentedOutSourceList(basNotRentedOutSource);
    }

    /**
     * 新增代码-昆明市主城区未配租数据
     * 
     * @param basNotRentedOutSource 代码-昆明市主城区未配租数据
     * @return 结果
     */
    @Override
    public int insertBasNotRentedOutSource(BasNotRentedOutSource basNotRentedOutSource)
    {
        basNotRentedOutSource.setSourceId(UUID.randomUUID().toString());
        basNotRentedOutSource.setCreateTime(DateUtils.getNowDate());
        basNotRentedOutSource.setImportTime(DateUtils.getNowDate());
        return basNotRentedOutSourceMapper.insertBasNotRentedOutSource(basNotRentedOutSource);
    }
    @Override
    public int insertBasNotRentedOutSourceBatch(List<BasNotRentedOutSource> basNotRentedOutSources)
    {
        return basNotRentedOutSourceMapper.insertBasNotRentedOutSourceBatch(basNotRentedOutSources);
    }

    /**
     * 修改代码-昆明市主城区未配租数据
     * 
     * @param basNotRentedOutSource 代码-昆明市主城区未配租数据
     * @return 结果
     */
    @Override
    public int updateBasNotRentedOutSource(BasNotRentedOutSource basNotRentedOutSource)
    {
        basNotRentedOutSource.setUpdateTime(DateUtils.getNowDate());
        return basNotRentedOutSourceMapper.updateBasNotRentedOutSource(basNotRentedOutSource);
    }

    /**
     * 批量删除代码-昆明市主城区未配租数据
     * 
     * @param sourceIds 需要删除的代码-昆明市主城区未配租数据ID
     * @return 结果
     */
    @Override
    public int deleteBasNotRentedOutSourceByIds(String[] sourceIds)
    {
        return basNotRentedOutSourceMapper.deleteBasNotRentedOutSourceByIds(sourceIds);
    }

    /**
     * 删除代码-昆明市主城区未配租数据信息
     * 
     * @param sourceId 代码-昆明市主城区未配租数据ID
     * @return 结果
     */
    @Override
    public int deleteBasNotRentedOutSourceById(String sourceId)
    {
        return basNotRentedOutSourceMapper.deleteBasNotRentedOutSourceById(sourceId);
    }
}
