package com.cb.basedata.mapper;

import java.util.List;

import com.cb.basedata.domain.BasElectricityFees;
import com.cb.basedata.domain.BasGasCost;

/**
 * 燃气费Mapper接口
 * 
 * @author ouyang
 * @date 2024-10-25
 */
public interface BasGasCostMapper 
{
    /**
     * 查询燃气费
     * 
     * @param id 燃气费ID
     * @return 燃气费
     */
    public BasGasCost selectBasGasCostById(String id);
    public BasGasCost selectBasGasCostBySourceId(String sourceId);
    public boolean existBasGasCostBySourceId(String sourceId);

    /**
     * 查询燃气费列表
     * 
     * @param basGasCost 燃气费
     * @return 燃气费集合
     */
    public List<BasGasCost> selectBasGasCostList(BasGasCost basGasCost);
    public List<BasGasCost> selectAllBasGasCostList();
    /**
     * 新增燃气费
     * 
     * @param basGasCost 燃气费
     * @return 结果
     */
    public int insertBasGasCost(BasGasCost basGasCost);

    /**
     * 修改燃气费
     * 
     * @param basGasCost 燃气费
     * @return 结果
     */
    public int updateBasGasCost(BasGasCost basGasCost);

    /**
     * 删除燃气费
     * 
     * @param id 燃气费ID
     * @return 结果
     */
    public int deleteBasGasCostById(String id);

    /**
     * 批量删除燃气费
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasGasCostByIds(String[] ids);

    public BasGasCost checkExistence(BasGasCost cost);
}
