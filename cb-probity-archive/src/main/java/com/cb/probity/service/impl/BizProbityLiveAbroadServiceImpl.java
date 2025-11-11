package com.cb.probity.service.impl;

import java.util.List;
import java.util.Date;
import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.probity.mapper.BizProbityLiveAbroadMapper;
import com.cb.probity.domain.BizProbityLiveAbroad;
import com.cb.probity.service.IBizProbityLiveAbroadService;

/**
 * 廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-18
 */
@Service
public class BizProbityLiveAbroadServiceImpl implements IBizProbityLiveAbroadService 
{
    @Autowired
    private BizProbityLiveAbroadMapper bizProbityLiveAbroadMapper;

    /**
     * 查询廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     * 
     * @param id 廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况ID
     * @return 廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     */
    @Override
    public BizProbityLiveAbroad selectBizProbityLiveAbroadById(String id)
    {
        return bizProbityLiveAbroadMapper.selectBizProbityLiveAbroadById(id);
    }

    /**
     * 查询廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况列表
     * 
     * @param bizProbityLiveAbroad 廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     * @return 廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     */
    @Override
    public List<BizProbityLiveAbroad> selectBizProbityLiveAbroadList(BizProbityLiveAbroad bizProbityLiveAbroad)
    {
        return bizProbityLiveAbroadMapper.selectBizProbityLiveAbroadList(bizProbityLiveAbroad);
    }

    /**
     * 新增廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     * 
     * @param bizProbityLiveAbroad 廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     * @return 结果
     */
    @Override
    public int insertBizProbityLiveAbroad(BizProbityLiveAbroad bizProbityLiveAbroad)
    {
        if(StringUtils.isBlank(bizProbityLiveAbroad.getId())) {
            bizProbityLiveAbroad.setId(IdUtils.randomUUID());
        }
        try{
            bizProbityLiveAbroad.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityLiveAbroad.setCreateTime(DateUtils.getNowDate());
        return bizProbityLiveAbroadMapper.insertBizProbityLiveAbroad(bizProbityLiveAbroad);
    }


    /**
     * 批量新增廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizProbityLiveAbroad> entities) {
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
            totalInserted +=  bizProbityLiveAbroadMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     * 
     * @param bizProbityLiveAbroad 廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     * @return 结果
     */
    @Override
    public int updateBizProbityLiveAbroad(BizProbityLiveAbroad bizProbityLiveAbroad)
    {
        try{
            bizProbityLiveAbroad.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityLiveAbroad.setUpdateTime(DateUtils.getNowDate());
        return bizProbityLiveAbroadMapper.updateBizProbityLiveAbroad(bizProbityLiveAbroad);
    }

    /**
     * 批量删除廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     * 
     * @param ids 需要删除的廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityLiveAbroadByIds(String[] ids)
    {
        return bizProbityLiveAbroadMapper.deleteBizProbityLiveAbroadByIds(ids);
    }

    /**
     * 删除廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况信息
     * 
     * @param id 廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityLiveAbroadById(String id)
    {
        return bizProbityLiveAbroadMapper.deleteBizProbityLiveAbroadById(id);
    }
}
