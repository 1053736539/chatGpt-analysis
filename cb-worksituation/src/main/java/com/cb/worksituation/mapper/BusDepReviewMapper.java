package com.cb.worksituation.mapper;

import com.cb.worksituation.domain.BusDepReview;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门评分Mapper接口
 *
 * @author ruoyi
 * @date 2025-10-11
 */
@Mapper
public interface BusDepReviewMapper
{
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
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<BusDepReview> entities);

    /**
     * 修改部门评分
     *
     * @param busDepReview 部门评分
     * @return 结果
     */
    public int updateBusDepReview(BusDepReview busDepReview);

    /**
     * 删除部门评分
     *
     * @param id 部门评分ID
     * @return 结果
     */
    public int deleteBusDepReviewById(String id);

    /**
     * 批量删除部门评分
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBusDepReviewByIds(String[] ids);


    /**
     * 根据评价对象筛选部门评分列表
     *
     * @param busDepReview   部门评分查询条件
     * @param evaluatTargets 评价对象集合
     * @return 部门评分集合
     */
    public List<BusDepReview> selectBusDepReviewListByEvaluatTargets(@Param("busDepReview") BusDepReview busDepReview, @Param("evaluatTargets") List<String> evaluatTargets);


    /**
     * 统计评分表的表头数量
     *
     * @param reviewId 评分表ID
     * @return 表头数量
     */
    int countHeadersByReviewId(@Param("reviewId") String reviewId);

}
