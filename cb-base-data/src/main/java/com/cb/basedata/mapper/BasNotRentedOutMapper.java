package com.cb.basedata.mapper;

import com.cb.basedata.domain.BasNotRentedOut;

import java.util.List;


/**
 * 代码-昆明市主城区未配租数据Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-25
 */
public interface BasNotRentedOutMapper 
{
    /**
     * 查询代码-昆明市主城区未配租数据
     * 
     * @param id 代码-昆明市主城区未配租数据ID
     * @return 代码-昆明市主城区未配租数据
     */
    public BasNotRentedOut selectBasNotRentedOutById(String id);
    public BasNotRentedOut selectBasNotRentedOutBySourceId(String sourceId);

    /**
     * 查询代码-昆明市主城区未配租数据列表
     * 
     * @param basNotRentedOut 代码-昆明市主城区未配租数据
     * @return 代码-昆明市主城区未配租数据集合
     */
    public List<BasNotRentedOut> selectBasNotRentedOutList(BasNotRentedOut basNotRentedOut);

    /**
     * 新增代码-昆明市主城区未配租数据
     * 
     * @param basNotRentedOut 代码-昆明市主城区未配租数据
     * @return 结果
     */
    public int insertBasNotRentedOut(BasNotRentedOut basNotRentedOut);

    /**
     * 修改代码-昆明市主城区未配租数据
     * 
     * @param basNotRentedOut 代码-昆明市主城区未配租数据
     * @return 结果
     */
    public int updateBasNotRentedOut(BasNotRentedOut basNotRentedOut);

    /**
     * 删除代码-昆明市主城区未配租数据
     * 
     * @param id 代码-昆明市主城区未配租数据ID
     * @return 结果
     */
    public int deleteBasNotRentedOutById(String id);

    /**
     * 批量删除代码-昆明市主城区未配租数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasNotRentedOutByIds(String[] ids);
}
