package com.cb.basedata.mapper;

import com.cb.basedata.domain.BasElectricityFeesSource;

import java.util.List;


/**
 * 电费-原始数据Mapper接口
 *
 * @author ouyang
 * @date 2024-10-29
 */
public interface BasElectricityFeesSourceMapper {
    /**
     * 查询电费-原始数据
     *
     * @param sourceId 电费-原始数据ID
     * @return 电费-原始数据
     */
    public BasElectricityFeesSource selectBasElectricityFeesSourceById(String sourceId);

    /**
     * 查询电费-原始数据列表
     *
     * @param basElectricityFeesSource 电费-原始数据
     * @return 电费-原始数据集合
     */
    public List<BasElectricityFeesSource> selectBasElectricityFeesSourceList(BasElectricityFeesSource basElectricityFeesSource);

    /**
     * 新增电费-原始数据
     *
     * @param basElectricityFeesSource 电费-原始数据
     * @return 结果
     */
    public int insertBasElectricityFeesSource(BasElectricityFeesSource basElectricityFeesSource);

    /**
     * 修改电费-原始数据
     *
     * @param basElectricityFeesSource 电费-原始数据
     * @return 结果
     */
    public int updateBasElectricityFeesSource(BasElectricityFeesSource basElectricityFeesSource);

    /**
     * 删除电费-原始数据
     *
     * @param sourceId 电费-原始数据ID
     * @return 结果
     */
    public int deleteBasElectricityFeesSourceById(String sourceId);

    /**
     * 批量删除电费-原始数据
     *
     * @param sourceIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasElectricityFeesSourceByIds(String[] sourceIds);

    public BasElectricityFeesSource checkExistence(BasElectricityFeesSource source);
}
