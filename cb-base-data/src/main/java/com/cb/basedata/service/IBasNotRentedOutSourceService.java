package com.cb.basedata.service;

import com.cb.basedata.domain.BasNotRentedOutSource;

import java.util.List;

/**
 * 代码-昆明市主城区未配租数据Service接口
 * 
 * @author ruoyi
 * @date 2024-10-30
 */
public interface IBasNotRentedOutSourceService 
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

    int insertBasNotRentedOutSourceBatch(List<BasNotRentedOutSource> basNotRentedOutSources);

    /**
     * 修改代码-昆明市主城区未配租数据
     * 
     * @param basNotRentedOutSource 代码-昆明市主城区未配租数据
     * @return 结果
     */
    public int updateBasNotRentedOutSource(BasNotRentedOutSource basNotRentedOutSource);

    /**
     * 批量删除代码-昆明市主城区未配租数据
     * 
     * @param sourceIds 需要删除的代码-昆明市主城区未配租数据ID
     * @return 结果
     */
    public int deleteBasNotRentedOutSourceByIds(String[] sourceIds);

    /**
     * 删除代码-昆明市主城区未配租数据信息
     * 
     * @param sourceId 代码-昆明市主城区未配租数据ID
     * @return 结果
     */
    public int deleteBasNotRentedOutSourceById(String sourceId);
}
