package com.cb.basedata.mapper;

import java.util.List;

import com.cb.basedata.domain.BasGasCostSource;

/**
 * 燃气费-原始数据Mapper接口
 *
 * @author ouyang
 * @date 2024-10-29
 */
public interface BasGasCostSourceMapper {
    /**
     * 查询燃气费-原始数据
     *
     * @param sourceId 燃气费-原始数据ID
     * @return 燃气费-原始数据
     */
    public BasGasCostSource selectBasGasCostSourceById(String sourceId);

    /**
     * 查询燃气费-原始数据列表
     *
     * @param basGasCostSource 燃气费-原始数据
     * @return 燃气费-原始数据集合
     */
    public List<BasGasCostSource> selectBasGasCostSourceList(BasGasCostSource basGasCostSource);

    /**
     * 新增燃气费-原始数据
     *
     * @param basGasCostSource 燃气费-原始数据
     * @return 结果
     */
    public int insertBasGasCostSource(BasGasCostSource basGasCostSource);

    /**
     * 修改燃气费-原始数据
     *
     * @param basGasCostSource 燃气费-原始数据
     * @return 结果
     */
    public int updateBasGasCostSource(BasGasCostSource basGasCostSource);

    /**
     * 删除燃气费-原始数据
     *
     * @param sourceId 燃气费-原始数据ID
     * @return 结果
     */
    public int deleteBasGasCostSourceById(String sourceId);

    /**
     * 批量删除燃气费-原始数据
     *
     * @param sourceIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasGasCostSourceByIds(String[] sourceIds);


    public BasGasCostSource checkExistence(BasGasCostSource source);
}
