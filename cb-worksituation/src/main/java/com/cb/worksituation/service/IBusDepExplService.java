package com.cb.worksituation.service;


import com.cb.worksituation.domain.BusDepExpl;

import java.util.List;

/**
 * 评分说明Service接口
 *
 * @author ruoyi
 * @date 2025-10-11
 */
public interface IBusDepExplService
{
    /**
     * 查询评分说明
     *
     * @param id 评分说明ID
     * @return 评分说明
     */
    public BusDepExpl selectBusDepExplById(String id);

    /**
     * 查询评分说明列表
     *
     * @param busDepExpl 评分说明
     * @return 评分说明集合
     */
    public List<BusDepExpl> selectBusDepExplList(BusDepExpl busDepExpl);

    /**
     * 新增评分说明
     *
     * @param busDepExpl 评分说明
     * @return 结果
     */
    public int insertBusDepExpl(BusDepExpl busDepExpl);

    /**
     * 批量新增评分说明
     * @param entities
     * @return
     */
    public int insertBatch(List<BusDepExpl> entities);

    /**
     * 修改评分说明
     *
     * @param busDepExpl 评分说明
     * @return 结果
     */
    public int updateBusDepExpl(BusDepExpl busDepExpl);

    /**
     * 批量删除评分说明
     *
     * @param ids 需要删除的评分说明ID
     * @return 结果
     */
    public int deleteBusDepExplByIds(String[] ids);

    /**
     * 删除评分说明信息
     *
     * @param id 评分说明ID
     * @return 结果
     */
    public int deleteBusDepExplById(String id);
}
