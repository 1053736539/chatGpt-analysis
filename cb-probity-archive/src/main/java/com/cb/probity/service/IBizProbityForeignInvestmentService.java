package com.cb.probity.service;

import java.util.List;

import com.cb.probity.domain.BizProbityForeignInvestment;

/**
 * 廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况Service接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface IBizProbityForeignInvestmentService {
    /**
     * 查询廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     *
     * @param id 廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况ID
     * @return 廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     */
    public BizProbityForeignInvestment selectBizProbityForeignInvestmentById(String id);

    /**
     * 查询廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况列表
     *
     * @param bizProbityForeignInvestment 廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     * @return 廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况集合
     */
    public List<BizProbityForeignInvestment> selectBizProbityForeignInvestmentList(BizProbityForeignInvestment bizProbityForeignInvestment);

    /**
     * 新增廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     *
     * @param bizProbityForeignInvestment 廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     * @return 结果
     */
    public int insertBizProbityForeignInvestment(BizProbityForeignInvestment bizProbityForeignInvestment);

    /**
     * 批量新增廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     *
     * @param entities
     * @return
     */
    public int insertBatch(List<BizProbityForeignInvestment> entities);

    /**
     * 修改廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     *
     * @param bizProbityForeignInvestment 廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     * @return 结果
     */
    public int updateBizProbityForeignInvestment(BizProbityForeignInvestment bizProbityForeignInvestment);

    /**
     * 批量删除廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     *
     * @param ids 需要删除的廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况ID
     * @return 结果
     */
    public int deleteBizProbityForeignInvestmentByIds(String[] ids);

    /**
     * 删除廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况信息
     *
     * @param id 廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况ID
     * @return 结果
     */
    public int deleteBizProbityForeignInvestmentById(String id);
}
