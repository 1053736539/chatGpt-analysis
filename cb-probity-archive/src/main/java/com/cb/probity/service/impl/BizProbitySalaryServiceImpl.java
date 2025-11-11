package com.cb.probity.service.impl;

import java.util.List;
import java.util.Date;
import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.probity.mapper.BizProbitySalaryMapper;
import com.cb.probity.domain.BizProbitySalary;
import com.cb.probity.service.IBizProbitySalaryService;

/**
 * 廉政档案-10.本人工资及各类奖金、津贴、补贴情况Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-18
 */
@Service
public class BizProbitySalaryServiceImpl implements IBizProbitySalaryService 
{
    @Autowired
    private BizProbitySalaryMapper bizProbitySalaryMapper;

    /**
     * 查询廉政档案-10.本人工资及各类奖金、津贴、补贴情况
     * 
     * @param id 廉政档案-10.本人工资及各类奖金、津贴、补贴情况ID
     * @return 廉政档案-10.本人工资及各类奖金、津贴、补贴情况
     */
    @Override
    public BizProbitySalary selectBizProbitySalaryById(String id)
    {
        return bizProbitySalaryMapper.selectBizProbitySalaryById(id);
    }

    /**
     * 查询廉政档案-10.本人工资及各类奖金、津贴、补贴情况列表
     * 
     * @param bizProbitySalary 廉政档案-10.本人工资及各类奖金、津贴、补贴情况
     * @return 廉政档案-10.本人工资及各类奖金、津贴、补贴情况
     */
    @Override
    public List<BizProbitySalary> selectBizProbitySalaryList(BizProbitySalary bizProbitySalary)
    {
        return bizProbitySalaryMapper.selectBizProbitySalaryList(bizProbitySalary);
    }

    /**
     * 新增廉政档案-10.本人工资及各类奖金、津贴、补贴情况
     * 
     * @param bizProbitySalary 廉政档案-10.本人工资及各类奖金、津贴、补贴情况
     * @return 结果
     */
    @Override
    public int insertBizProbitySalary(BizProbitySalary bizProbitySalary)
    {
        if(StringUtils.isBlank(bizProbitySalary.getId())) {
            bizProbitySalary.setId(IdUtils.randomUUID());
        }
        try{
            bizProbitySalary.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbitySalary.setCreateTime(DateUtils.getNowDate());
        return bizProbitySalaryMapper.insertBizProbitySalary(bizProbitySalary);
    }


    /**
     * 批量新增廉政档案-10.本人工资及各类奖金、津贴、补贴情况
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizProbitySalary> entities) {
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
            totalInserted +=  bizProbitySalaryMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改廉政档案-10.本人工资及各类奖金、津贴、补贴情况
     * 
     * @param bizProbitySalary 廉政档案-10.本人工资及各类奖金、津贴、补贴情况
     * @return 结果
     */
    @Override
    public int updateBizProbitySalary(BizProbitySalary bizProbitySalary)
    {
        try{
            bizProbitySalary.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbitySalary.setUpdateTime(DateUtils.getNowDate());
        return bizProbitySalaryMapper.updateBizProbitySalary(bizProbitySalary);
    }

    /**
     * 批量删除廉政档案-10.本人工资及各类奖金、津贴、补贴情况
     * 
     * @param ids 需要删除的廉政档案-10.本人工资及各类奖金、津贴、补贴情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbitySalaryByIds(String[] ids)
    {
        return bizProbitySalaryMapper.deleteBizProbitySalaryByIds(ids);
    }

    /**
     * 删除廉政档案-10.本人工资及各类奖金、津贴、补贴情况信息
     * 
     * @param id 廉政档案-10.本人工资及各类奖金、津贴、补贴情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbitySalaryById(String id)
    {
        return bizProbitySalaryMapper.deleteBizProbitySalaryById(id);
    }
}
