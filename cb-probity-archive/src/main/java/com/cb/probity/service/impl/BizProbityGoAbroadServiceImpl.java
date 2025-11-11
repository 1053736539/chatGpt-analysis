package com.cb.probity.service.impl;

import java.util.List;
import java.util.Date;
import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.probity.mapper.BizProbityGoAbroadMapper;
import com.cb.probity.domain.BizProbityGoAbroad;
import com.cb.probity.service.IBizProbityGoAbroadService;

/**
 * 廉政档案-6.2本人因私出国（境）及往来港澳台情况Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-18
 */
@Service
public class BizProbityGoAbroadServiceImpl implements IBizProbityGoAbroadService 
{
    @Autowired
    private BizProbityGoAbroadMapper bizProbityGoAbroadMapper;

    /**
     * 查询廉政档案-6.2本人因私出国（境）及往来港澳台情况
     * 
     * @param id 廉政档案-6.2本人因私出国（境）及往来港澳台情况ID
     * @return 廉政档案-6.2本人因私出国（境）及往来港澳台情况
     */
    @Override
    public BizProbityGoAbroad selectBizProbityGoAbroadById(String id)
    {
        return bizProbityGoAbroadMapper.selectBizProbityGoAbroadById(id);
    }

    /**
     * 查询廉政档案-6.2本人因私出国（境）及往来港澳台情况列表
     * 
     * @param bizProbityGoAbroad 廉政档案-6.2本人因私出国（境）及往来港澳台情况
     * @return 廉政档案-6.2本人因私出国（境）及往来港澳台情况
     */
    @Override
    public List<BizProbityGoAbroad> selectBizProbityGoAbroadList(BizProbityGoAbroad bizProbityGoAbroad)
    {
        return bizProbityGoAbroadMapper.selectBizProbityGoAbroadList(bizProbityGoAbroad);
    }

    /**
     * 新增廉政档案-6.2本人因私出国（境）及往来港澳台情况
     * 
     * @param bizProbityGoAbroad 廉政档案-6.2本人因私出国（境）及往来港澳台情况
     * @return 结果
     */
    @Override
    public int insertBizProbityGoAbroad(BizProbityGoAbroad bizProbityGoAbroad)
    {
        if(StringUtils.isBlank(bizProbityGoAbroad.getId())) {
            bizProbityGoAbroad.setId(IdUtils.randomUUID());
        }
        try{
            bizProbityGoAbroad.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityGoAbroad.setCreateTime(DateUtils.getNowDate());
        return bizProbityGoAbroadMapper.insertBizProbityGoAbroad(bizProbityGoAbroad);
    }


    /**
     * 批量新增廉政档案-6.2本人因私出国（境）及往来港澳台情况
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizProbityGoAbroad> entities) {
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
            totalInserted +=  bizProbityGoAbroadMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改廉政档案-6.2本人因私出国（境）及往来港澳台情况
     * 
     * @param bizProbityGoAbroad 廉政档案-6.2本人因私出国（境）及往来港澳台情况
     * @return 结果
     */
    @Override
    public int updateBizProbityGoAbroad(BizProbityGoAbroad bizProbityGoAbroad)
    {
        try{
            bizProbityGoAbroad.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityGoAbroad.setUpdateTime(DateUtils.getNowDate());
        return bizProbityGoAbroadMapper.updateBizProbityGoAbroad(bizProbityGoAbroad);
    }

    /**
     * 批量删除廉政档案-6.2本人因私出国（境）及往来港澳台情况
     * 
     * @param ids 需要删除的廉政档案-6.2本人因私出国（境）及往来港澳台情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityGoAbroadByIds(String[] ids)
    {
        return bizProbityGoAbroadMapper.deleteBizProbityGoAbroadByIds(ids);
    }

    /**
     * 删除廉政档案-6.2本人因私出国（境）及往来港澳台情况信息
     * 
     * @param id 廉政档案-6.2本人因私出国（境）及往来港澳台情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityGoAbroadById(String id)
    {
        return bizProbityGoAbroadMapper.deleteBizProbityGoAbroadById(id);
    }
}
