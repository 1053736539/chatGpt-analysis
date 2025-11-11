package com.cb.probity.service.impl;

import java.util.List;
import java.util.Date;
import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.probity.mapper.BizProbityGiftMapper;
import com.cb.probity.domain.BizProbityGift;
import com.cb.probity.service.IBizProbityGiftService;

/**
 * 廉政档案-3.本人拒收或上交礼金、礼品情况登记Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-18
 */
@Service
public class BizProbityGiftServiceImpl implements IBizProbityGiftService 
{
    @Autowired
    private BizProbityGiftMapper bizProbityGiftMapper;

    /**
     * 查询廉政档案-3.本人拒收或上交礼金、礼品情况登记
     * 
     * @param id 廉政档案-3.本人拒收或上交礼金、礼品情况登记ID
     * @return 廉政档案-3.本人拒收或上交礼金、礼品情况登记
     */
    @Override
    public BizProbityGift selectBizProbityGiftById(String id)
    {
        return bizProbityGiftMapper.selectBizProbityGiftById(id);
    }

    /**
     * 查询廉政档案-3.本人拒收或上交礼金、礼品情况登记列表
     * 
     * @param bizProbityGift 廉政档案-3.本人拒收或上交礼金、礼品情况登记
     * @return 廉政档案-3.本人拒收或上交礼金、礼品情况登记
     */
    @Override
    public List<BizProbityGift> selectBizProbityGiftList(BizProbityGift bizProbityGift)
    {
        return bizProbityGiftMapper.selectBizProbityGiftList(bizProbityGift);
    }

    /**
     * 新增廉政档案-3.本人拒收或上交礼金、礼品情况登记
     * 
     * @param bizProbityGift 廉政档案-3.本人拒收或上交礼金、礼品情况登记
     * @return 结果
     */
    @Override
    public int insertBizProbityGift(BizProbityGift bizProbityGift)
    {
        if(StringUtils.isBlank(bizProbityGift.getId())) {
            bizProbityGift.setId(IdUtils.randomUUID());
        }
        try{
            bizProbityGift.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityGift.setCreateTime(DateUtils.getNowDate());
        return bizProbityGiftMapper.insertBizProbityGift(bizProbityGift);
    }


    /**
     * 批量新增廉政档案-3.本人拒收或上交礼金、礼品情况登记
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizProbityGift> entities) {
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
            totalInserted +=  bizProbityGiftMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改廉政档案-3.本人拒收或上交礼金、礼品情况登记
     * 
     * @param bizProbityGift 廉政档案-3.本人拒收或上交礼金、礼品情况登记
     * @return 结果
     */
    @Override
    public int updateBizProbityGift(BizProbityGift bizProbityGift)
    {
        try{
            bizProbityGift.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityGift.setUpdateTime(DateUtils.getNowDate());
        return bizProbityGiftMapper.updateBizProbityGift(bizProbityGift);
    }

    /**
     * 批量删除廉政档案-3.本人拒收或上交礼金、礼品情况登记
     * 
     * @param ids 需要删除的廉政档案-3.本人拒收或上交礼金、礼品情况登记ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityGiftByIds(String[] ids)
    {
        return bizProbityGiftMapper.deleteBizProbityGiftByIds(ids);
    }

    /**
     * 删除廉政档案-3.本人拒收或上交礼金、礼品情况登记信息
     * 
     * @param id 廉政档案-3.本人拒收或上交礼金、礼品情况登记ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityGiftById(String id)
    {
        return bizProbityGiftMapper.deleteBizProbityGiftById(id);
    }
}
