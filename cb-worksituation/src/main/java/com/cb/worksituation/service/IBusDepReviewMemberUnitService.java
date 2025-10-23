package com.cb.worksituation.service;

import com.cb.worksituation.domain.BusDepReviewMemberUnit;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2025-10-15
 */
public interface IBusDepReviewMemberUnitService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public BusDepReviewMemberUnit selectBusDepReviewMemberUnitById(String id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param busDepReviewMemberUnit 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<BusDepReviewMemberUnit> selectBusDepReviewMemberUnitList(BusDepReviewMemberUnit busDepReviewMemberUnit);

    /**
     * 新增【请填写功能名称】
     *
     * @param busDepReviewMemberUnit 【请填写功能名称】
     * @return 结果
     */
    public int insertBusDepReviewMemberUnit(BusDepReviewMemberUnit busDepReviewMemberUnit);

    /**
     * 批量新增【请填写功能名称】
     * @param entities
     * @return
     */
    public int insertBatch(List<BusDepReviewMemberUnit> entities);

    /**
     * 修改【请填写功能名称】
     *
     * @param busDepReviewMemberUnit 【请填写功能名称】
     * @return 结果
     */
    public int updateBusDepReviewMemberUnit(BusDepReviewMemberUnit busDepReviewMemberUnit);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteBusDepReviewMemberUnitByIds(String[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteBusDepReviewMemberUnitById(String id);
}
