package com.cb.adopt.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.cb.adopt.domain.BizAdoptScoreRule;

/**
 * 得分规则Mapper接口
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
public interface BizAdoptScoreRuleMapper 
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
     *
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<BizAdoptScoreRule> entities);

    /**
     * 修改得分规则
     * 
     * @param bizAdoptScoreRule 得分规则
     * @return 结果
     */
    public int updateBizAdoptScoreRule(BizAdoptScoreRule bizAdoptScoreRule);

    /**
     * 删除得分规则
     * 
     * @param id 得分规则ID
     * @return 结果
     */
    public int deleteBizAdoptScoreRuleById(Integer id);

    /**
     * 批量删除得分规则
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAdoptScoreRuleByIds(Integer[] ids);
}
