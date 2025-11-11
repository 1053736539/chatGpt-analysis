package com.cb.probity.service.impl;

import java.util.List;
import java.util.Date;
import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.probity.mapper.BizProbityFundMapper;
import com.cb.probity.domain.BizProbityFund;
import com.cb.probity.service.IBizProbityFundService;

/**
 * 廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-18
 */
@Service
public class BizProbityFundServiceImpl implements IBizProbityFundService 
{
    @Autowired
    private BizProbityFundMapper bizProbityFundMapper;

    /**
     * 查询廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况
     * 
     * @param id 廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况ID
     * @return 廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况
     */
    @Override
    public BizProbityFund selectBizProbityFundById(String id)
    {
        return bizProbityFundMapper.selectBizProbityFundById(id);
    }

    /**
     * 查询廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况列表
     * 
     * @param bizProbityFund 廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况
     * @return 廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况
     */
    @Override
    public List<BizProbityFund> selectBizProbityFundList(BizProbityFund bizProbityFund)
    {
        return bizProbityFundMapper.selectBizProbityFundList(bizProbityFund);
    }

    /**
     * 新增廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况
     * 
     * @param bizProbityFund 廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况
     * @return 结果
     */
    @Override
    public int insertBizProbityFund(BizProbityFund bizProbityFund)
    {
        if(StringUtils.isBlank(bizProbityFund.getId())) {
            bizProbityFund.setId(IdUtils.randomUUID());
        }
        try{
            bizProbityFund.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityFund.setCreateTime(DateUtils.getNowDate());
        return bizProbityFundMapper.insertBizProbityFund(bizProbityFund);
    }


    /**
     * 批量新增廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizProbityFund> entities) {
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
            totalInserted +=  bizProbityFundMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况
     * 
     * @param bizProbityFund 廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况
     * @return 结果
     */
    @Override
    public int updateBizProbityFund(BizProbityFund bizProbityFund)
    {
        try{
            bizProbityFund.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityFund.setUpdateTime(DateUtils.getNowDate());
        return bizProbityFundMapper.updateBizProbityFund(bizProbityFund);
    }

    /**
     * 批量删除廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况
     * 
     * @param ids 需要删除的廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityFundByIds(String[] ids)
    {
        return bizProbityFundMapper.deleteBizProbityFundByIds(ids);
    }

    /**
     * 删除廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况信息
     * 
     * @param id 廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityFundById(String id)
    {
        return bizProbityFundMapper.deleteBizProbityFundById(id);
    }
}
