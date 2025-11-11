package com.cb.assess.service;

import com.cb.assess.domain.BizAssessPersonalSummary;
import com.cb.assess.domain.vo.BizAssessPersonalSummaryVo;

import java.util.List;

/**
 * 个人总结Service接口
 * 
 * @author ruoyi
 * @date 2023-12-12
 */
public interface IBizAssessPersonalSummaryService 
{
    /**
     * 查询个人总结
     * 
     * @param id 个人总结ID
     * @return 个人总结
     */
    public BizAssessPersonalSummary selectBizAssessPersonalSummaryById(String id);

    /**
     * 查询个人总结列表
     * 
     * @param bizAssessPersonalSummary 个人总结
     * @return 个人总结集合
     */
    public List<BizAssessPersonalSummaryVo> selectBizAssessPersonalSummaryList(BizAssessPersonalSummaryVo bizAssessPersonalSummary);

    List<BizAssessPersonalSummaryVo> selectYears();

    /**
     * 新增个人总结
     * 
     * @param bizAssessPersonalSummary 个人总结
     * @return 结果
     */
    public int insertBizAssessPersonalSummary(BizAssessPersonalSummary bizAssessPersonalSummary);

    /**
     * 修改个人总结
     * 
     * @param bizAssessPersonalSummary 个人总结
     * @return 结果
     */
    public int updateBizAssessPersonalSummary(BizAssessPersonalSummary bizAssessPersonalSummary);

    /**
     * 批量删除个人总结
     * 
     * @param ids 需要删除的个人总结ID
     * @return 结果
     */
    public int deleteBizAssessPersonalSummaryByIds(String[] ids);

    /**
     * 删除个人总结信息
     * 
     * @param id 个人总结ID
     * @return 结果
     */
    public int deleteBizAssessPersonalSummaryById(String id);

    void publishPersonalSummary2Notice(String year);
}
