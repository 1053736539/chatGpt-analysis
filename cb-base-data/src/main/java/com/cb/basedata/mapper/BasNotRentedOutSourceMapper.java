package com.cb.basedata.mapper;

import com.cb.basedata.domain.BasNotRentedOutSource;

import java.util.List;

/**
 * 代码-昆明市主城区未配租数据Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-30
 */
public interface BasNotRentedOutSourceMapper 
{
    /**
     * 查询代码-昆明市主城区未配租数据
     * 
     * @param sourceId 代码-昆明市主城区未配租数据ID
     * @return 代码-昆明市主城区未配租数据
     */
    public BasNotRentedOutSource selectBasNotRentedOutSourceById(String sourceId);

    /**
     * 查询代码-昆明市主城区未配租数据列表
     * 
     * @param basNotRentedOutSource 代码-昆明市主城区未配租数据
     * @return 代码-昆明市主城区未配租数据集合
     */
    public List<BasNotRentedOutSource> selectBasNotRentedOutSourceList(BasNotRentedOutSource basNotRentedOutSource);

    /**
     * 新增代码-昆明市主城区未配租数据
     * 
     * @param basNotRentedOutSource 代码-昆明市主城区未配租数据
     * @return 结果
     */
    public int insertBasNotRentedOutSource(BasNotRentedOutSource basNotRentedOutSource);
    public int insertBasNotRentedOutSourceBatch(List<BasNotRentedOutSource> basNotRentedOutSource);

    /**
     * 修改代码-昆明市主城区未配租数据
     * 
     * @param basNotRentedOutSource 代码-昆明市主城区未配租数据
     * @return 结果
     */
    public int updateBasNotRentedOutSource(BasNotRentedOutSource basNotRentedOutSource);

    /**
     * 删除代码-昆明市主城区未配租数据
     * 
     * @param sourceId 代码-昆明市主城区未配租数据ID
     * @return 结果
     */
    public int deleteBasNotRentedOutSourceById(String sourceId);

    /**
     * 批量删除代码-昆明市主城区未配租数据
     * 
     * @param sourceIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasNotRentedOutSourceByIds(String[] sourceIds);
}
