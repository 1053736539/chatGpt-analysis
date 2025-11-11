package com.cb.probity.service.impl;

import java.util.List;
import java.util.Date;
import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.probity.mapper.BizProbityForeignInvestmentMapper;
import com.cb.probity.domain.BizProbityForeignInvestment;
import com.cb.probity.service.IBizProbityForeignInvestmentService;

/**
 * 廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-18
 */
@Service
public class BizProbityForeignInvestmentServiceImpl implements IBizProbityForeignInvestmentService 
{
    @Autowired
    private BizProbityForeignInvestmentMapper bizProbityForeignInvestmentMapper;

    /**
     * 查询廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     * 
     * @param id 廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况ID
     * @return 廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     */
    @Override
    public BizProbityForeignInvestment selectBizProbityForeignInvestmentById(String id)
    {
        return bizProbityForeignInvestmentMapper.selectBizProbityForeignInvestmentById(id);
    }

    /**
     * 查询廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况列表
     * 
     * @param bizProbityForeignInvestment 廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     * @return 廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     */
    @Override
    public List<BizProbityForeignInvestment> selectBizProbityForeignInvestmentList(BizProbityForeignInvestment bizProbityForeignInvestment)
    {
        return bizProbityForeignInvestmentMapper.selectBizProbityForeignInvestmentList(bizProbityForeignInvestment);
    }

    /**
     * 新增廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     * 
     * @param bizProbityForeignInvestment 廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     * @return 结果
     */
    @Override
    public int insertBizProbityForeignInvestment(BizProbityForeignInvestment bizProbityForeignInvestment)
    {
        if(StringUtils.isBlank(bizProbityForeignInvestment.getId())) {
            bizProbityForeignInvestment.setId(IdUtils.randomUUID());
        }
        try{
            bizProbityForeignInvestment.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityForeignInvestment.setCreateTime(DateUtils.getNowDate());
        return bizProbityForeignInvestmentMapper.insertBizProbityForeignInvestment(bizProbityForeignInvestment);
    }


    /**
     * 批量新增廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizProbityForeignInvestment> entities) {
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
            totalInserted +=  bizProbityForeignInvestmentMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     * 
     * @param bizProbityForeignInvestment 廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     * @return 结果
     */
    @Override
    public int updateBizProbityForeignInvestment(BizProbityForeignInvestment bizProbityForeignInvestment)
    {
        try{
            bizProbityForeignInvestment.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityForeignInvestment.setUpdateTime(DateUtils.getNowDate());
        return bizProbityForeignInvestmentMapper.updateBizProbityForeignInvestment(bizProbityForeignInvestment);
    }

    /**
     * 批量删除廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     * 
     * @param ids 需要删除的廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityForeignInvestmentByIds(String[] ids)
    {
        return bizProbityForeignInvestmentMapper.deleteBizProbityForeignInvestmentByIds(ids);
    }

    /**
     * 删除廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况信息
     * 
     * @param id 廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityForeignInvestmentById(String id)
    {
        return bizProbityForeignInvestmentMapper.deleteBizProbityForeignInvestmentById(id);
    }
}
