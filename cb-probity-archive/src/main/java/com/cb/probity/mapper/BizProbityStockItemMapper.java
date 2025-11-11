package com.cb.probity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.cb.probity.domain.BizProbityStockItem;

/**
 * 廉政档案-13.2持有股票记录信息项Mapper接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface BizProbityStockItemMapper {
    /**
     * 查询廉政档案-13.2持有股票记录信息项
     *
     * @param id 廉政档案-13.2持有股票记录信息项ID
     * @return 廉政档案-13.2持有股票记录信息项
     */
    public BizProbityStockItem selectBizProbityStockItemById(String id);

    /**
     * 查询廉政档案-13.2持有股票记录信息项列表
     *
     * @param bizProbityStockItem 廉政档案-13.2持有股票记录信息项
     * @return 廉政档案-13.2持有股票记录信息项集合
     */
    public List<BizProbityStockItem> selectBizProbityStockItemList(BizProbityStockItem bizProbityStockItem);

    /**
     * 新增廉政档案-13.2持有股票记录信息项
     *
     * @param bizProbityStockItem 廉政档案-13.2持有股票记录信息项
     * @return 结果
     */
    public int insertBizProbityStockItem(BizProbityStockItem bizProbityStockItem);


    /**
     * 批量新增廉政档案-13.2持有股票记录信息项
     *
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<BizProbityStockItem> entities);

    /**
     * 修改廉政档案-13.2持有股票记录信息项
     *
     * @param bizProbityStockItem 廉政档案-13.2持有股票记录信息项
     * @return 结果
     */
    public int updateBizProbityStockItem(BizProbityStockItem bizProbityStockItem);

    /**
     * 删除廉政档案-13.2持有股票记录信息项
     *
     * @param id 廉政档案-13.2持有股票记录信息项ID
     * @return 结果
     */
    public int deleteBizProbityStockItemById(String id);

    /**
     * 批量删除廉政档案-13.2持有股票记录信息项
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizProbityStockItemByIds(String[] ids);
}
