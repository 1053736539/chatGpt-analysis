package com.cb.worksituation.service;


import com.cb.worksituation.domain.BusDepReviewData;

import java.util.List;

/**
 * 评分数据Service接口
 *
 * @author ruoyi
 * @date 2025-10-11
 */
public interface IBusDepReviewDataService {
    /**
     * 查询评分数据
     *
     * @param id 评分数据ID
     * @return 评分数据
     */
    public BusDepReviewData selectBusDepReviewDataById(String id);

    /**
     * 查询评分数据列表
     *
     * @param busDepReviewData 评分数据
     * @return 评分数据集合
     */
    public List<BusDepReviewData> selectBusDepReviewDataList(BusDepReviewData busDepReviewData);

    /**
     * 新增评分数据
     *
     * @param busDepReviewData 评分数据
     * @return 结果
     */
    public int insertBusDepReviewData(BusDepReviewData busDepReviewData);

    /**
     * 批量新增评分数据
     *
     * @param entities
     * @return
     */
    public int insertBatch(List<BusDepReviewData> entities);

    /**
     * 修改评分数据
     *
     * @param busDepReviewData 评分数据
     * @return 结果
     */
    public int updateBusDepReviewData(BusDepReviewData busDepReviewData);

    /**
     * 提交数据
     *
     * @param busDepReviewData 评分数据
     * @return 结果
     */
    public int submitGrading(BusDepReviewData busDepReviewData);

    /**
     * 批量删除评分数据
     *
     * @param ids 需要删除的评分数据ID
     * @return 结果
     */
    public int deleteBusDepReviewDataByIds(String[] ids);

    /**
     * 删除评分数据信息
     *
     * @param id 评分数据ID
     * @return 结果
     */
    public int deleteBusDepReviewDataById(String id);
}
