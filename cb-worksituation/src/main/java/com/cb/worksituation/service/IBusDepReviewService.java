package com.cb.worksituation.service;


import com.cb.common.core.domain.AjaxResult;
import com.cb.worksituation.domain.BusDepReview;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * 部门评分Service接口
 *
 * @author ruoyi
 * @date 2025-10-11
 */
public interface IBusDepReviewService {
    /**
     * 查询部门评分
     *
     * @param id 部门评分ID
     * @return 部门评分
     */
    public BusDepReview selectBusDepReviewById(String id);

    /**
     * 查询部门评分列表
     *
     * @param busDepReview 部门评分
     * @return 部门评分集合
     */
    public List<BusDepReview> selectBusDepReviewList(BusDepReview busDepReview);

    /**
     * 查询部门评分列表
     *
     * @param id 部门评分id
     * @return 部门评分集合
     */
    public BusDepReview getDisplayHeaderList(String id);

    /**
     * 新增部门评分
     *
     * @param busDepReview 部门评分
     * @return 结果
     */
    public int insertBusDepReview(BusDepReview busDepReview);

    /**
     * 批量新增部门评分
     *
     * @param entities
     * @return
     */
    public int insertBatch(List<BusDepReview> entities);

    /**
     * 修改部门评分
     *
     * @param busDepReview 部门评分
     * @return 结果
     */
    public AjaxResult updateBusDepReview(BusDepReview busDepReview);

    /**
     * 批量删除部门评分
     *
     * @param ids 需要删除的部门评分ID
     * @return 结果
     */
    public AjaxResult deleteBusDepReviewByIds(String[] ids);

    /**
     * 删除部门评分信息
     *
     * @param id 部门评分ID
     * @return 结果
     */
    public AjaxResult deleteBusDepReviewById(String id);

    /**
     * 获取评分表表格配置信息
     *
     * @param id 评分表ID
     * @return 包含表头和数据的评分表
     */
    public BusDepReview getReviewTableConfig(String id, Boolean sign);

    /**
     * 查询当前登录用户可操作的部门评分列表
     *
     * @param busDepReview 部门评分查询条件
     * @return 部门评分集合
     */
    public List<BusDepReview> selectBusDepReviewListForCurrentUser(BusDepReview busDepReview);

    /**
     * 判断评分表是否配置了表头数据
     *
     * @param reviewId 评分表ID
     * @return true 表示存在表头数据
     */
    boolean existsReviewHeaders(String reviewId);

    /**
     * 判断评分表是否配置了表头数据
     *
     * @param reviewId 评分表ID
     * @return true 表示存在表头数据
     */
    boolean loginUserAuth();

}
