package com.cb.probity.service.impl;

import java.util.List;
import java.util.Date;
import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.probity.mapper.BizProbityMaritalMapper;
import com.cb.probity.domain.BizProbityMarital;
import com.cb.probity.service.IBizProbityMaritalService;

/**
 * 廉政档案-4.本人婚姻状况及紧急联系人情况Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-18
 */
@Service
public class BizProbityMaritalServiceImpl implements IBizProbityMaritalService 
{
    @Autowired
    private BizProbityMaritalMapper bizProbityMaritalMapper;

    /**
     * 查询廉政档案-4.本人婚姻状况及紧急联系人情况
     * 
     * @param id 廉政档案-4.本人婚姻状况及紧急联系人情况ID
     * @return 廉政档案-4.本人婚姻状况及紧急联系人情况
     */
    @Override
    public BizProbityMarital selectBizProbityMaritalById(String id)
    {
        return bizProbityMaritalMapper.selectBizProbityMaritalById(id);
    }

    /**
     * 查询廉政档案-4.本人婚姻状况及紧急联系人情况列表
     * 
     * @param bizProbityMarital 廉政档案-4.本人婚姻状况及紧急联系人情况
     * @return 廉政档案-4.本人婚姻状况及紧急联系人情况
     */
    @Override
    public List<BizProbityMarital> selectBizProbityMaritalList(BizProbityMarital bizProbityMarital)
    {
        return bizProbityMaritalMapper.selectBizProbityMaritalList(bizProbityMarital);
    }

    /**
     * 新增廉政档案-4.本人婚姻状况及紧急联系人情况
     * 
     * @param bizProbityMarital 廉政档案-4.本人婚姻状况及紧急联系人情况
     * @return 结果
     */
    @Override
    public int insertBizProbityMarital(BizProbityMarital bizProbityMarital)
    {
        if(StringUtils.isBlank(bizProbityMarital.getId())) {
            bizProbityMarital.setId(IdUtils.randomUUID());
        }
        try{
            bizProbityMarital.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityMarital.setCreateTime(DateUtils.getNowDate());
        return bizProbityMaritalMapper.insertBizProbityMarital(bizProbityMarital);
    }


    /**
     * 批量新增廉政档案-4.本人婚姻状况及紧急联系人情况
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizProbityMarital> entities) {
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
            totalInserted +=  bizProbityMaritalMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改廉政档案-4.本人婚姻状况及紧急联系人情况
     * 
     * @param bizProbityMarital 廉政档案-4.本人婚姻状况及紧急联系人情况
     * @return 结果
     */
    @Override
    public int updateBizProbityMarital(BizProbityMarital bizProbityMarital)
    {
        try{
            bizProbityMarital.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityMarital.setUpdateTime(DateUtils.getNowDate());
        return bizProbityMaritalMapper.updateBizProbityMarital(bizProbityMarital);
    }

    /**
     * 批量删除廉政档案-4.本人婚姻状况及紧急联系人情况
     * 
     * @param ids 需要删除的廉政档案-4.本人婚姻状况及紧急联系人情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityMaritalByIds(String[] ids)
    {
        return bizProbityMaritalMapper.deleteBizProbityMaritalByIds(ids);
    }

    /**
     * 删除廉政档案-4.本人婚姻状况及紧急联系人情况信息
     * 
     * @param id 廉政档案-4.本人婚姻状况及紧急联系人情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityMaritalById(String id)
    {
        return bizProbityMaritalMapper.deleteBizProbityMaritalById(id);
    }
}
