package com.cb.probity.service.impl;

import java.util.List;
import java.util.Date;
import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.probity.mapper.BizProbityStockItemMapper;
import com.cb.probity.domain.BizProbityStockItem;
import com.cb.probity.service.IBizProbityStockItemService;

/**
 * 廉政档案-13.2持有股票记录信息项Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-18
 */
@Service
public class BizProbityStockItemServiceImpl implements IBizProbityStockItemService 
{
    @Autowired
    private BizProbityStockItemMapper bizProbityStockItemMapper;

    /**
     * 查询廉政档案-13.2持有股票记录信息项
     * 
     * @param id 廉政档案-13.2持有股票记录信息项ID
     * @return 廉政档案-13.2持有股票记录信息项
     */
    @Override
    public BizProbityStockItem selectBizProbityStockItemById(String id)
    {
        return bizProbityStockItemMapper.selectBizProbityStockItemById(id);
    }

    /**
     * 查询廉政档案-13.2持有股票记录信息项列表
     * 
     * @param bizProbityStockItem 廉政档案-13.2持有股票记录信息项
     * @return 廉政档案-13.2持有股票记录信息项
     */
    @Override
    public List<BizProbityStockItem> selectBizProbityStockItemList(BizProbityStockItem bizProbityStockItem)
    {
        return bizProbityStockItemMapper.selectBizProbityStockItemList(bizProbityStockItem);
    }

    /**
     * 新增廉政档案-13.2持有股票记录信息项
     * 
     * @param bizProbityStockItem 廉政档案-13.2持有股票记录信息项
     * @return 结果
     */
    @Override
    public int insertBizProbityStockItem(BizProbityStockItem bizProbityStockItem)
    {
        if(StringUtils.isBlank(bizProbityStockItem.getId())) {
            bizProbityStockItem.setId(IdUtils.randomUUID());
        }
        try{
            bizProbityStockItem.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityStockItem.setCreateTime(DateUtils.getNowDate());
        return bizProbityStockItemMapper.insertBizProbityStockItem(bizProbityStockItem);
    }


    /**
     * 批量新增廉政档案-13.2持有股票记录信息项
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizProbityStockItem> entities) {
        if(null == entities || entities.isEmpty()){
            return 0;
        }
        String userName = null;
        try{
            userName = SecurityUtils.getUsername();
        } catch (Exception e){}
        Date nowDate = DateUtils.getNowDate();
        String finalUserName = userName;
        entities.parallelStream().forEach(item -> {
            item.setId(IdUtils.randomUUID());
            item.setCreateBy(finalUserName);
            item.setCreateTime(nowDate);
        });
        // 定义每批次的大小
        int batchSize = 500;
        int totalInserted = 0;
        int num = entities.size() / batchSize + (entities.size() % batchSize == 0 ? 0 : 1);
        for (int i = 0; i < num; i++) {
            int start = i * batchSize;
            int end = Math.min(start + batchSize, entities.size());
            totalInserted +=  bizProbityStockItemMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改廉政档案-13.2持有股票记录信息项
     * 
     * @param bizProbityStockItem 廉政档案-13.2持有股票记录信息项
     * @return 结果
     */
    @Override
    public int updateBizProbityStockItem(BizProbityStockItem bizProbityStockItem)
    {
        try{
            bizProbityStockItem.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityStockItem.setUpdateTime(DateUtils.getNowDate());
        return bizProbityStockItemMapper.updateBizProbityStockItem(bizProbityStockItem);
    }

    /**
     * 批量删除廉政档案-13.2持有股票记录信息项
     * 
     * @param ids 需要删除的廉政档案-13.2持有股票记录信息项ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityStockItemByIds(String[] ids)
    {
        return bizProbityStockItemMapper.deleteBizProbityStockItemByIds(ids);
    }

    /**
     * 删除廉政档案-13.2持有股票记录信息项信息
     * 
     * @param id 廉政档案-13.2持有股票记录信息项ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityStockItemById(String id)
    {
        return bizProbityStockItemMapper.deleteBizProbityStockItemById(id);
    }
}
