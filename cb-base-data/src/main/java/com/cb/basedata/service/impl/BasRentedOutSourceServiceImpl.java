package com.cb.basedata.service.impl;

import java.util.List;

import com.cb.basedata.domain.BasRentedOutSource;
import com.cb.basedata.mapper.BasRentedOutSourceMapper;
import com.cb.basedata.service.IBasRentedOutSourceService;
import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 代码-昆明市主城区已配租数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-30
 */
@Service
public class BasRentedOutSourceServiceImpl implements IBasRentedOutSourceService
{
    @Autowired
    private BasRentedOutSourceMapper basRentedOutSourceMapper;

    /**
     * 查询代码-昆明市主城区已配租数据
     * 
     * @param sourceId 代码-昆明市主城区已配租数据ID
     * @return 代码-昆明市主城区已配租数据
     */
    @Override
    public BasRentedOutSource selectBasRentedOutSourceById(String sourceId)
    {
        return basRentedOutSourceMapper.selectBasRentedOutSourceById(sourceId);
    }

    /**
     * 查询代码-昆明市主城区已配租数据列表
     * 
     * @param basRentedOutSource 代码-昆明市主城区已配租数据
     * @return 代码-昆明市主城区已配租数据
     */
    @Override
    public List<BasRentedOutSource> selectBasRentedOutSourceList(BasRentedOutSource basRentedOutSource)
    {
        return basRentedOutSourceMapper.selectBasRentedOutSourceList(basRentedOutSource);
    }

    /**
     * 新增代码-昆明市主城区已配租数据
     * 
     * @param basRentedOutSource 代码-昆明市主城区已配租数据
     * @return 结果
     */
    @Override
    public int insertBasRentedOutSource(BasRentedOutSource basRentedOutSource)
    {
        basRentedOutSource.setCreateTime(DateUtils.getNowDate());
        return basRentedOutSourceMapper.insertBasRentedOutSource(basRentedOutSource);
    }
    @Override
    public int insertBasRentedOutSourceBatch(List<BasRentedOutSource> basRentedOutSources)
    {
        return basRentedOutSourceMapper.insertBasRentedOutSourceBatch(basRentedOutSources);
    }

    /**
     * 修改代码-昆明市主城区已配租数据
     * 
     * @param basRentedOutSource 代码-昆明市主城区已配租数据
     * @return 结果
     */
    @Override
    public int updateBasRentedOutSource(BasRentedOutSource basRentedOutSource)
    {
        basRentedOutSource.setUpdateTime(DateUtils.getNowDate());
        return basRentedOutSourceMapper.updateBasRentedOutSource(basRentedOutSource);
    }

    /**
     * 批量删除代码-昆明市主城区已配租数据
     * 
     * @param sourceIds 需要删除的代码-昆明市主城区已配租数据ID
     * @return 结果
     */
    @Override
    public int deleteBasRentedOutSourceByIds(String[] sourceIds)
    {
        return basRentedOutSourceMapper.deleteBasRentedOutSourceByIds(sourceIds);
    }

    /**
     * 删除代码-昆明市主城区已配租数据信息
     * 
     * @param sourceId 代码-昆明市主城区已配租数据ID
     * @return 结果
     */
    @Override
    public int deleteBasRentedOutSourceById(String sourceId)
    {
        return basRentedOutSourceMapper.deleteBasRentedOutSourceById(sourceId);
    }
}
