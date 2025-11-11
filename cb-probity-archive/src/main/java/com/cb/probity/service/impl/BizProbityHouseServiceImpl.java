package com.cb.probity.service.impl;

import java.util.List;
import java.util.Date;
import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.probity.mapper.BizProbityHouseMapper;
import com.cb.probity.domain.BizProbityHouse;
import com.cb.probity.service.IBizProbityHouseService;

/**
 * 廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-18
 */
@Service
public class BizProbityHouseServiceImpl implements IBizProbityHouseService 
{
    @Autowired
    private BizProbityHouseMapper bizProbityHouseMapper;

    /**
     * 查询廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况
     * 
     * @param id 廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况ID
     * @return 廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况
     */
    @Override
    public BizProbityHouse selectBizProbityHouseById(String id)
    {
        return bizProbityHouseMapper.selectBizProbityHouseById(id);
    }

    /**
     * 查询廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况列表
     * 
     * @param bizProbityHouse 廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况
     * @return 廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况
     */
    @Override
    public List<BizProbityHouse> selectBizProbityHouseList(BizProbityHouse bizProbityHouse)
    {
        return bizProbityHouseMapper.selectBizProbityHouseList(bizProbityHouse);
    }

    /**
     * 新增廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况
     * 
     * @param bizProbityHouse 廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况
     * @return 结果
     */
    @Override
    public int insertBizProbityHouse(BizProbityHouse bizProbityHouse)
    {
        if(StringUtils.isBlank(bizProbityHouse.getId())) {
            bizProbityHouse.setId(IdUtils.randomUUID());
        }
        try{
            bizProbityHouse.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityHouse.setCreateTime(DateUtils.getNowDate());
        return bizProbityHouseMapper.insertBizProbityHouse(bizProbityHouse);
    }


    /**
     * 批量新增廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizProbityHouse> entities) {
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
            totalInserted +=  bizProbityHouseMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况
     * 
     * @param bizProbityHouse 廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况
     * @return 结果
     */
    @Override
    public int updateBizProbityHouse(BizProbityHouse bizProbityHouse)
    {
        try{
            bizProbityHouse.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityHouse.setUpdateTime(DateUtils.getNowDate());
        return bizProbityHouseMapper.updateBizProbityHouse(bizProbityHouse);
    }

    /**
     * 批量删除廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况
     * 
     * @param ids 需要删除的廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityHouseByIds(String[] ids)
    {
        return bizProbityHouseMapper.deleteBizProbityHouseByIds(ids);
    }

    /**
     * 删除廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况信息
     * 
     * @param id 廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityHouseById(String id)
    {
        return bizProbityHouseMapper.deleteBizProbityHouseById(id);
    }
}
