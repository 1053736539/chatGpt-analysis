package com.cb.adopt.service;

import java.util.List;
import com.cb.adopt.domain.BizAdoptScoreRule;

/**
 * 得分规则Service接口
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
public interface IBizAdoptScoreRuleService 
{
    /**
     * 查询得分规则
     * 
     * @param id 得分规则ID
     * @return 得分规则
     */
    public BizAdoptScoreRule selectBizAdoptScoreRuleById(Integer id);

    /**
     * 查询得分规则列表
     * 
     * @param bizAdoptScoreRule 得分规则
     * @return 得分规则集合
     */
    public List<BizAdoptScoreRule> selectBizAdoptScoreRuleList(BizAdoptScoreRule bizAdoptScoreRule);

    /**
     * 新增得分规则
     * 
     * @param bizAdoptScoreRule 得分规则
     * @return 结果
     */
    public int insertBizAdoptScoreRule(BizAdoptScoreRule bizAdoptScoreRule);

    /**
     * 批量新增得分规则
     * @param entities
     * @return
     */
    public int insertBatch(List<BizAdoptScoreRule> entities);

    /**
     * 修改得分规则
     * 
     * @param bizAdoptScoreRule 得分规则
     * @return 结果
     */
    public int updateBizAdoptScoreRule(BizAdoptScoreRule bizAdoptScoreRule);

    /**
     * 批量删除得分规则
     * 
     * @param ids 需要删除的得分规则ID
     * @return 结果
     */
    public int deleteBizAdoptScoreRuleByIds(Integer[] ids);

    /**
     * 删除得分规则信息
     * 
     * @param id 得分规则ID
     * @return 结果
     */
    public int deleteBizAdoptScoreRuleById(Integer id);
}
