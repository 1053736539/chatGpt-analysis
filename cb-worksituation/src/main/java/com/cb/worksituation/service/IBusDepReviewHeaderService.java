package com.cb.worksituation.service;


import com.cb.worksituation.domain.BusDepReviewHeader;

import java.util.List;

/**
 * 部门评分-头Service接口
 *
 * @author ruoyi
 * @date 2025-10-11
 */
public interface IBusDepReviewHeaderService {
    /**
     * 查询部门评分-头
     *
     * @param id 部门评分-头ID
     * @return 部门评分-头
     */
    public BusDepReviewHeader selectBusDepReviewHeaderById(String id);

    /**
     * 查询部门评分-头列表
     *
     * @param busDepReviewHeader 部门评分-头
     * @return 部门评分-头集合
     */
    public List<BusDepReviewHeader> selectBusDepReviewHeaderList(BusDepReviewHeader busDepReviewHeader);

    /**
     * 新增部门评分-头
     *
     * @param busDepReviewHeader 部门评分-头
     * @return 结果
     */
    public int insertBusDepReviewHeader(BusDepReviewHeader busDepReviewHeader);

    /**
     * 批量新增部门评分-头
     *
     * @param entities
     * @return
     */
    public int insertBatch(List<BusDepReviewHeader> entities);

    /**
     * 修改部门评分-头
     *
     * @param busDepReviewHeader 部门评分-头
     * @return 结果
     */
    public int updateBusDepReviewHeader(BusDepReviewHeader busDepReviewHeader);

    /**
     * 批量删除部门评分-头
     *
     * @param ids 需要删除的部门评分-头ID
     * @return 结果
     */
    public int deleteBusDepReviewHeaderByIds(String[] ids);

    /**
     * 删除部门评分-头信息
     *
     * @param id 部门评分-头ID
     * @return 结果
     */
    public int deleteBusDepReviewHeaderById(String id);
}
