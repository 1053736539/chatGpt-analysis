package com.cb.basedata.service;

import java.util.List;

import com.cb.basedata.domain.BasGasCost;
import com.cb.basedata.domain.vo.LivingExpensesSourceVo;

/**
 * 燃气费Service接口
 * 
 * @author ouyang
 * @date 2024-10-25
 */
public interface IBasGasCostService 
{
    /**
     * 查询燃气费
     * 
     * @param id 燃气费ID
     * @return 燃气费
     */
    public BasGasCost selectBasGasCostById(String id);

    public BasGasCost selectBasGasCostBySourceId(String sourceId);

    public Boolean existBasGasCostBySourceId(String sourceId);


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
     * 批量删除燃气费
     * 
     * @param ids 需要删除的燃气费ID
     * @return 结果
     */
    public int deleteBasGasCostByIds(String[] ids);

    /**
     * 删除燃气费信息
     * 
     * @param id 燃气费ID
     * @return 结果
     */
    public int deleteBasGasCostById(String id);


    @Deprecated
    public String importBasGasCost(List<BasGasCost> list, Boolean isUpdateSupport);

}
