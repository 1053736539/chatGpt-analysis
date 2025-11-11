package com.cb.basedata.service;

import java.util.List;

import com.cb.basedata.domain.BasWaterRent;
import com.cb.basedata.domain.vo.LivingExpensesSourceVo;

/**
 * 水费Service接口
 *
 * @author ouyang
 * @date 2024-10-25
 */
public interface IBasWaterRentService {
    /**
     * 查询水费
     *
     * @param id 水费ID
     * @return 水费
     */
    public BasWaterRent selectBasWaterRentById(String id);

    public BasWaterRent selectBasWaterRentBySourceId(String sourceId);

    public Boolean existBasWaterRentBySourceId(String sourceId);

    /**
     * 查询水费列表
     *
     * @param basWaterRent 水费
     * @return 水费集合
     */
    public List<BasWaterRent> selectBasWaterRentList(BasWaterRent basWaterRent);

    public List<BasWaterRent> selectAllBasWaterRentList();

    /**
     * 新增水费
     *
     * @param basWaterRent 水费
     * @return 结果
     */
    public int insertBasWaterRent(BasWaterRent basWaterRent);

    /**
     * 修改水费
     *
     * @param basWaterRent 水费
     * @return 结果
     */
    public int updateBasWaterRent(BasWaterRent basWaterRent);

    /**
     * 批量删除水费
     *
     * @param ids 需要删除的水费ID
     * @return 结果
     */
    public int deleteBasWaterRentByIds(String[] ids);

    /**
     * 删除水费信息
     *
     * @param id 水费ID
     * @return 结果
     */
    public int deleteBasWaterRentById(String id);


    @Deprecated
    public String importBasWaterRent(List<BasWaterRent> list, Boolean isUpdateSupport);

}
