package com.cb.basedata.mapper;

import com.cb.basedata.domain.BasRentedOut;

import java.util.List;

/**
 * 代码-昆明市主城区已配租数据Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-25
 */
public interface BasRentedOutMapper 
{
    /**
     * 查询代码-昆明市主城区已配租数据
     * 
     * @param id 代码-昆明市主城区已配租数据ID
     * @return 代码-昆明市主城区已配租数据
     */
    public BasRentedOut selectBasRentedOutById(String id);
    public BasRentedOut selectBasNotRentedOutBySourceId(String sourceId);

    /**
     * 查询代码-昆明市主城区已配租数据列表
     * 
     * @param basRentedOut 代码-昆明市主城区已配租数据
     * @return 代码-昆明市主城区已配租数据集合
     */
    public List<BasRentedOut> selectBasRentedOutList(BasRentedOut basRentedOut);

    /**
     * 新增代码-昆明市主城区已配租数据
     * 
     * @param basRentedOut 代码-昆明市主城区已配租数据
     * @return 结果
     */
    public int insertBasRentedOut(BasRentedOut basRentedOut);

    /**
     * 修改代码-昆明市主城区已配租数据
     * 
     * @param basRentedOut 代码-昆明市主城区已配租数据
     * @return 结果
     */
    public int updateBasRentedOut(BasRentedOut basRentedOut);

    /**
     * 删除代码-昆明市主城区已配租数据
     * 
     * @param id 代码-昆明市主城区已配租数据ID
     * @return 结果
     */
    public int deleteBasRentedOutById(String id);

    /**
     * 批量删除代码-昆明市主城区已配租数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasRentedOutByIds(String[] ids);
}
