package com.cb.worksituation.service;

import com.cb.worksituation.domain.BusDepReviewUnitName;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2025-10-15
 */
public interface IBusDepReviewUnitNameService
{

    List<BusDepReviewUnitName> getBusinessCollaborationUnit(String busDepReviewId);
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public BusDepReviewUnitName selectBusDepReviewUnitNameById(String id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param busDepReviewUnitName 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<BusDepReviewUnitName> selectBusDepReviewUnitNameList(BusDepReviewUnitName busDepReviewUnitName);

    /**
     * 新增【请填写功能名称】
     *
     * @param busDepReviewUnitName 【请填写功能名称】
     * @return 结果
     */
    public int insertBusDepReviewUnitName(BusDepReviewUnitName busDepReviewUnitName);

    /**
     * 批量新增【请填写功能名称】
     * @param entities
     * @return
     */
    public int insertBatch(List<BusDepReviewUnitName> entities);

    /**
     * 修改【请填写功能名称】
     *
     * @param busDepReviewUnitName 【请填写功能名称】
     * @return 结果
     */
    public int updateBusDepReviewUnitName(BusDepReviewUnitName busDepReviewUnitName);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteBusDepReviewUnitNameByIds(String[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteBusDepReviewUnitNameById(String id);
}
