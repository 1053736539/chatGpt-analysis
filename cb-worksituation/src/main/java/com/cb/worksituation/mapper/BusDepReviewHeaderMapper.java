package com.cb.worksituation.mapper;

import com.cb.worksituation.domain.BusDepReviewHeader;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门评分-头Mapper接口
 *
 * @author ruoyi
 * @date 2025-10-11
 */
@Mapper
public interface BusDepReviewHeaderMapper
{
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
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<BusDepReviewHeader> entities);

    /**
     * 修改部门评分-头
     *
     * @param busDepReviewHeader 部门评分-头
     * @return 结果
     */
    public int updateBusDepReviewHeader(BusDepReviewHeader busDepReviewHeader);

    /**
     * 删除部门评分-头
     *
     * @param id 部门评分-头ID
     * @return 结果
     */
    public int deleteBusDepReviewHeaderById(String id);

    /**
     * 批量删除部门评分-头
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBusDepReviewHeaderByIds(String[] ids);

    /**
     * 根据评分表ID删除数据ss
     * @param busDepReviewId
     * @return
     */
    int deleteBusDepReviewHeaderByBusDepReviewId(String busDepReviewId);
}
