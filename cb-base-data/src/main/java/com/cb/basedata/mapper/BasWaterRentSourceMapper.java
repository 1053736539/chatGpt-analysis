package com.cb.basedata.mapper;

import com.cb.basedata.domain.BasWaterRentSource;

import java.util.List;

/**
 * 水费-原始数据Mapper接口
 *
 * @author ouyang
 * @date 2024-10-29
 */
public interface BasWaterRentSourceMapper {
    /**
     * 查询水费-原始数据
     *
     * @param sourceId 水费-原始数据ID
     * @return 水费-原始数据
     */
    public BasWaterRentSource selectBasWaterRentSourceById(String sourceId);

    /**
     * 查询水费-原始数据列表
     *
     * @param basWaterRentSource 水费-原始数据
     * @return 水费-原始数据集合
     */
    public List<BasWaterRentSource> selectBasWaterRentSourceList(BasWaterRentSource basWaterRentSource);

    /**
     * 新增水费-原始数据
     *
     * @param basWaterRentSource 水费-原始数据
     * @return 结果
     */
    public int insertBasWaterRentSource(BasWaterRentSource basWaterRentSource);

    /**
     * 修改水费-原始数据
     *
     * @param basWaterRentSource 水费-原始数据
     * @return 结果
     */
    public int updateBasWaterRentSource(BasWaterRentSource basWaterRentSource);

    /**
     * 删除水费-原始数据
     *
     * @param sourceId 水费-原始数据ID
     * @return 结果
     */
    public int deleteBasWaterRentSourceById(String sourceId);

    /**
     * 批量删除水费-原始数据
     *
     * @param sourceIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasWaterRentSourceByIds(String[] sourceIds);


    public BasWaterRentSource checkExistence(BasWaterRentSource source);
}
