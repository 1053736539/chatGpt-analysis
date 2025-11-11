package com.cb.probity.service.impl;

import java.util.List;
import java.util.Date;
import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.probity.mapper.BizProbityForeignDepositMapper;
import com.cb.probity.domain.BizProbityForeignDeposit;
import com.cb.probity.service.IBizProbityForeignDepositService;

/**
 * 廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-18
 */
@Service
public class BizProbityForeignDepositServiceImpl implements IBizProbityForeignDepositService 
{
    @Autowired
    private BizProbityForeignDepositMapper bizProbityForeignDepositMapper;

    /**
     * 查询廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况
     * 
     * @param id 廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况ID
     * @return 廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况
     */
    @Override
    public BizProbityForeignDeposit selectBizProbityForeignDepositById(String id)
    {
        return bizProbityForeignDepositMapper.selectBizProbityForeignDepositById(id);
    }

    /**
     * 查询廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况列表
     * 
     * @param bizProbityForeignDeposit 廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况
     * @return 廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况
     */
    @Override
    public List<BizProbityForeignDeposit> selectBizProbityForeignDepositList(BizProbityForeignDeposit bizProbityForeignDeposit)
    {
        return bizProbityForeignDepositMapper.selectBizProbityForeignDepositList(bizProbityForeignDeposit);
    }

    /**
     * 新增廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况
     * 
     * @param bizProbityForeignDeposit 廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况
     * @return 结果
     */
    @Override
    public int insertBizProbityForeignDeposit(BizProbityForeignDeposit bizProbityForeignDeposit)
    {
        if(StringUtils.isBlank(bizProbityForeignDeposit.getId())) {
            bizProbityForeignDeposit.setId(IdUtils.randomUUID());
        }
        try{
            bizProbityForeignDeposit.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityForeignDeposit.setCreateTime(DateUtils.getNowDate());
        return bizProbityForeignDepositMapper.insertBizProbityForeignDeposit(bizProbityForeignDeposit);
    }


    /**
     * 批量新增廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizProbityForeignDeposit> entities) {
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
            totalInserted +=  bizProbityForeignDepositMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况
     * 
     * @param bizProbityForeignDeposit 廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况
     * @return 结果
     */
    @Override
    public int updateBizProbityForeignDeposit(BizProbityForeignDeposit bizProbityForeignDeposit)
    {
        try{
            bizProbityForeignDeposit.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityForeignDeposit.setUpdateTime(DateUtils.getNowDate());
        return bizProbityForeignDepositMapper.updateBizProbityForeignDeposit(bizProbityForeignDeposit);
    }

    /**
     * 批量删除廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况
     * 
     * @param ids 需要删除的廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityForeignDepositByIds(String[] ids)
    {
        return bizProbityForeignDepositMapper.deleteBizProbityForeignDepositByIds(ids);
    }

    /**
     * 删除廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况信息
     * 
     * @param id 廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityForeignDepositById(String id)
    {
        return bizProbityForeignDepositMapper.deleteBizProbityForeignDepositById(id);
    }
}
