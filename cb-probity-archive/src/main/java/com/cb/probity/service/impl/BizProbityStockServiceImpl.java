package com.cb.probity.service.impl;

import java.util.List;
import java.util.Date;
import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.probity.mapper.BizProbityStockMapper;
import com.cb.probity.domain.BizProbityStock;
import com.cb.probity.service.IBizProbityStockService;

/**
 * 廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-18
 */
@Service
public class BizProbityStockServiceImpl implements IBizProbityStockService 
{
    @Autowired
    private BizProbityStockMapper bizProbityStockMapper;

    /**
     * 查询廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况
     * 
     * @param id 廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况ID
     * @return 廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况
     */
    @Override
    public BizProbityStock selectBizProbityStockById(String id)
    {
        return bizProbityStockMapper.selectBizProbityStockById(id);
    }

    /**
     * 查询廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况列表
     * 
     * @param bizProbityStock 廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况
     * @return 廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况
     */
    @Override
    public List<BizProbityStock> selectBizProbityStockList(BizProbityStock bizProbityStock)
    {
        return bizProbityStockMapper.selectBizProbityStockList(bizProbityStock);
    }

    /**
     * 新增廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况
     * 
     * @param bizProbityStock 廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况
     * @return 结果
     */
    @Override
    public int insertBizProbityStock(BizProbityStock bizProbityStock)
    {
        if(StringUtils.isBlank(bizProbityStock.getId())) {
            bizProbityStock.setId(IdUtils.randomUUID());
        }
        try{
            bizProbityStock.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityStock.setCreateTime(DateUtils.getNowDate());
        return bizProbityStockMapper.insertBizProbityStock(bizProbityStock);
    }


    /**
     * 批量新增廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizProbityStock> entities) {
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
            totalInserted +=  bizProbityStockMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况
     * 
     * @param bizProbityStock 廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况
     * @return 结果
     */
    @Override
    public int updateBizProbityStock(BizProbityStock bizProbityStock)
    {
        try{
            bizProbityStock.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityStock.setUpdateTime(DateUtils.getNowDate());
        return bizProbityStockMapper.updateBizProbityStock(bizProbityStock);
    }

    /**
     * 批量删除廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况
     * 
     * @param ids 需要删除的廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityStockByIds(String[] ids)
    {
        return bizProbityStockMapper.deleteBizProbityStockByIds(ids);
    }

    /**
     * 删除廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况信息
     * 
     * @param id 廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityStockById(String id)
    {
        return bizProbityStockMapper.deleteBizProbityStockById(id);
    }
}
