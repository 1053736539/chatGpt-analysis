package com.cb.basedata.mapper;

import com.cb.basedata.domain.BasRentedOutSource;

import java.util.List;

/**
 * 代码-昆明市主城区已配租数据Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-30
 */
public interface BasRentedOutSourceMapper 
{
    /**
     * 查询代码-昆明市主城区已配租数据
     * 
     * @param sourceId 代码-昆明市主城区已配租数据ID
     * @return 代码-昆明市主城区已配租数据
     */
    public BasRentedOutSource selectBasRentedOutSourceById(String sourceId);

    /**
     * 查询代码-昆明市主城区已配租数据列表
     * 
     * @param basRentedOutSource 代码-昆明市主城区已配租数据
     * @return 代码-昆明市主城区已配租数据集合
     */
    public List<BasRentedOutSource> selectBasRentedOutSourceList(BasRentedOutSource basRentedOutSource);

    /**
     * 新增代码-昆明市主城区已配租数据
     * 
     * @param basRentedOutSource 代码-昆明市主城区已配租数据
     * @return 结果
     */
    public int insertBasRentedOutSource(BasRentedOutSource basRentedOutSource);
    public int insertBasRentedOutSourceBatch(List<BasRentedOutSource> basRentedOutSource);

    /**
     * 修改代码-昆明市主城区已配租数据
     * 
     * @param basRentedOutSource 代码-昆明市主城区已配租数据
     * @return 结果
     */
    public int updateBasRentedOutSource(BasRentedOutSource basRentedOutSource);

    /**
     * 删除代码-昆明市主城区已配租数据
     * 
     * @param sourceId 代码-昆明市主城区已配租数据ID
     * @return 结果
     */
    public int deleteBasRentedOutSourceById(String sourceId);

    /**
     * 批量删除代码-昆明市主城区已配租数据
     * 
     * @param sourceIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasRentedOutSourceByIds(String[] sourceIds);
}
