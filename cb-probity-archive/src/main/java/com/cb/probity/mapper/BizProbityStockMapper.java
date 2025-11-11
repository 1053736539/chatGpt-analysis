package com.cb.probity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.cb.probity.domain.BizProbityStock;

/**
 * 廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况Mapper接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface BizProbityStockMapper {
    /**
     * 查询廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况
     *
     * @param id 廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况ID
     * @return 廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况
     */
    public BizProbityStock selectBizProbityStockById(String id);

    /**
     * 查询廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况列表
     *
     * @param bizProbityStock 廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况
     * @return 廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况集合
     */
    public List<BizProbityStock> selectBizProbityStockList(BizProbityStock bizProbityStock);

    /**
     * 新增廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况
     *
     * @param bizProbityStock 廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况
     * @return 结果
     */
    public int insertBizProbityStock(BizProbityStock bizProbityStock);


    /**
     * 批量新增廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况
     *
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<BizProbityStock> entities);

    /**
     * 修改廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况
     *
     * @param bizProbityStock 廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况
     * @return 结果
     */
    public int updateBizProbityStock(BizProbityStock bizProbityStock);

    /**
     * 删除廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况
     *
     * @param id 廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况ID
     * @return 结果
     */
    public int deleteBizProbityStockById(String id);

    /**
     * 批量删除廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizProbityStockByIds(String[] ids);
}
