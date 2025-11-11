package com.cb.basedata.mapper;

import java.util.List;
import com.cb.basedata.domain.BasElectricityFees;

/**
 * 电费Mapper接口
 * 
 * @author ouyang
 * @date 2024-10-25
 */
public interface BasElectricityFeesMapper 
{
    /**
     * 查询电费
     * 
     * @param id 电费ID
     * @return 电费
     */
    public BasElectricityFees selectBasElectricityFeesById(String id);
    public BasElectricityFees selectBasElectricityFeesBySourceId(String sourceId);
    public boolean existBasElectricityFeesBySourceId(String sourceId);

    /**
     * 查询电费列表
     * 
     * @param basElectricityFees 电费
     * @return 电费集合
     */
    public List<BasElectricityFees> selectBasElectricityFeesList(BasElectricityFees basElectricityFees);
    public List<BasElectricityFees> selectAllBasElectricityFeesList();
    /**
     * 新增电费
     * 
     * @param basElectricityFees 电费
     * @return 结果
     */
    public int insertBasElectricityFees(BasElectricityFees basElectricityFees);

    /**
     * 修改电费
     * 
     * @param basElectricityFees 电费
     * @return 结果
     */
    public int updateBasElectricityFees(BasElectricityFees basElectricityFees);

    /**
     * 删除电费
     * 
     * @param id 电费ID
     * @return 结果
     */
    public int deleteBasElectricityFeesById(String id);

    /**
     * 批量删除电费
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasElectricityFeesByIds(String[] ids);

    public BasElectricityFees checkExistence(BasElectricityFees fees);
}
