package com.cb.adopt.service.impl;

import java.util.List;
import java.util.Date;

import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.adopt.mapper.BizAdoptScoreRuleMapper;
import com.cb.adopt.domain.BizAdoptScoreRule;
import com.cb.adopt.service.IBizAdoptScoreRuleService;

/**
 * 得分规则Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
@Service
public class BizAdoptScoreRuleServiceImpl implements IBizAdoptScoreRuleService 
{
    @Autowired
    private BizAdoptScoreRuleMapper bizAdoptScoreRuleMapper;

    /**
     * 查询得分规则
     * 
     * @param id 得分规则ID
     * @return 得分规则
     */
    @Override
    public BizAdoptScoreRule selectBizAdoptScoreRuleById(Integer id)
    {
        return bizAdoptScoreRuleMapper.selectBizAdoptScoreRuleById(id);
    }

    /**
     * 查询得分规则列表
     * 
     * @param bizAdoptScoreRule 得分规则
     * @return 得分规则
     */
    @Override
    public List<BizAdoptScoreRule> selectBizAdoptScoreRuleList(BizAdoptScoreRule bizAdoptScoreRule)
    {
        return bizAdoptScoreRuleMapper.selectBizAdoptScoreRuleList(bizAdoptScoreRule);
    }

    /**
     * 新增得分规则
     * 
     * @param bizAdoptScoreRule 得分规则
     * @return 结果
     */
    @Override
    public int insertBizAdoptScoreRule(BizAdoptScoreRule bizAdoptScoreRule)
    {
//        if(StringUtils.isBlank(bizAdoptScoreRule.getId().toString())) {
//            bizAdoptScoreRule.setId(IdUtils.randomUUID());
//        }
        return bizAdoptScoreRuleMapper.insertBizAdoptScoreRule(bizAdoptScoreRule);
    }


    /**
     * 批量新增得分规则
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizAdoptScoreRule> entities) {
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
//            item.setId(IdUtils.randomUUID());
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
            totalInserted +=  bizAdoptScoreRuleMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改得分规则
     * 
     * @param bizAdoptScoreRule 得分规则
     * @return 结果
     */
    @Override
    public int updateBizAdoptScoreRule(BizAdoptScoreRule bizAdoptScoreRule)
    {
        return bizAdoptScoreRuleMapper.updateBizAdoptScoreRule(bizAdoptScoreRule);
    }

    /**
     * 批量删除得分规则
     * 
     * @param ids 需要删除的得分规则ID
     * @return 结果
     */
    @Override
    public int deleteBizAdoptScoreRuleByIds(Integer[] ids)
    {
        return bizAdoptScoreRuleMapper.deleteBizAdoptScoreRuleByIds(ids);
    }

    /**
     * 删除得分规则信息
     * 
     * @param id 得分规则ID
     * @return 结果
     */
    @Override
    public int deleteBizAdoptScoreRuleById(Integer id)
    {
        return bizAdoptScoreRuleMapper.deleteBizAdoptScoreRuleById(id);
    }
}
