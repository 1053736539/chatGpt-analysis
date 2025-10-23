package com.cb.worksituation.mapper;

import com.cb.worksituation.domain.BusDepReviewData;
import com.cb.worksituation.domain.BusDepReviewMemberUnit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评分数据Mapper接口
 *
 * @author ruoyi
 * @date 2025-10-11
 */
@Mapper
public interface BusDepReviewDataMapper {
    /**
     * 查询评分数据
     *
     * @param id 评分数据ID
     * @return 评分数据
     */
    public BusDepReviewData selectBusDepReviewDataById(String id);

    /**
     * 获取评分数据中的业务评分
     */
    public List<BusDepReviewData> selectBusDepReviewMemberUnits(@Param("busDepReviewId") String busDepReviewId, @Param("evaluatTargets") List<String> evaluatTargets);

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
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<BusDepReviewData> entities);

    /**
     * 修改评分数据
     *
     * @param busDepReviewData 评分数据
     * @return 结果
     */
    public int updateBusDepReviewData(BusDepReviewData busDepReviewData);

    /**
     * 删除评分数据
     *
     * @param id 评分数据ID
     * @return 结果
     */
    public int deleteBusDepReviewDataById(String id);

    /**
     * 批量删除评分数据
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBusDepReviewDataByIds(String[] ids);
}
