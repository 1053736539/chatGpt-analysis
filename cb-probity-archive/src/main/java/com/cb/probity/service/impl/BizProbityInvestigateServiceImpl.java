package com.cb.probity.service.impl;

import java.util.List;
import java.util.Date;
import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.probity.mapper.BizProbityInvestigateMapper;
import com.cb.probity.domain.BizProbityInvestigate;
import com.cb.probity.service.IBizProbityInvestigateService;

/**
 * 廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-18
 */
@Service
public class BizProbityInvestigateServiceImpl implements IBizProbityInvestigateService 
{
    @Autowired
    private BizProbityInvestigateMapper bizProbityInvestigateMapper;

    /**
     * 查询廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录
     * 
     * @param id 廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录ID
     * @return 廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录
     */
    @Override
    public BizProbityInvestigate selectBizProbityInvestigateById(String id)
    {
        return bizProbityInvestigateMapper.selectBizProbityInvestigateById(id);
    }

    /**
     * 查询廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录列表
     * 
     * @param bizProbityInvestigate 廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录
     * @return 廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录
     */
    @Override
    public List<BizProbityInvestigate> selectBizProbityInvestigateList(BizProbityInvestigate bizProbityInvestigate)
    {
        return bizProbityInvestigateMapper.selectBizProbityInvestigateList(bizProbityInvestigate);
    }

    /**
     * 新增廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录
     * 
     * @param bizProbityInvestigate 廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录
     * @return 结果
     */
    @Override
    public int insertBizProbityInvestigate(BizProbityInvestigate bizProbityInvestigate)
    {
        if(StringUtils.isBlank(bizProbityInvestigate.getId())) {
            bizProbityInvestigate.setId(IdUtils.randomUUID());
        }
        try{
            bizProbityInvestigate.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityInvestigate.setCreateTime(DateUtils.getNowDate());
        return bizProbityInvestigateMapper.insertBizProbityInvestigate(bizProbityInvestigate);
    }


    /**
     * 批量新增廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizProbityInvestigate> entities) {
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
            totalInserted +=  bizProbityInvestigateMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录
     * 
     * @param bizProbityInvestigate 廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录
     * @return 结果
     */
    @Override
    public int updateBizProbityInvestigate(BizProbityInvestigate bizProbityInvestigate)
    {
        try{
            bizProbityInvestigate.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityInvestigate.setUpdateTime(DateUtils.getNowDate());
        return bizProbityInvestigateMapper.updateBizProbityInvestigate(bizProbityInvestigate);
    }

    /**
     * 批量删除廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录
     * 
     * @param ids 需要删除的廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityInvestigateByIds(String[] ids)
    {
        return bizProbityInvestigateMapper.deleteBizProbityInvestigateByIds(ids);
    }

    /**
     * 删除廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录信息
     * 
     * @param id 廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityInvestigateById(String id)
    {
        return bizProbityInvestigateMapper.deleteBizProbityInvestigateById(id);
    }
}
