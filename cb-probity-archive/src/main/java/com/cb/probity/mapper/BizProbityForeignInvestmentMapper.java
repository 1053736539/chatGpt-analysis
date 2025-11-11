package com.cb.probity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.cb.probity.domain.BizProbityForeignInvestment;

/**
 * 廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况Mapper接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface BizProbityForeignInvestmentMapper {
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
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<BizProbityForeignInvestment> entities);

    /**
     * 修改廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     *
     * @param bizProbityForeignInvestment 廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     * @return 结果
     */
    public int updateBizProbityForeignInvestment(BizProbityForeignInvestment bizProbityForeignInvestment);

    /**
     * 删除廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     *
     * @param id 廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况ID
     * @return 结果
     */
    public int deleteBizProbityForeignInvestmentById(String id);

    /**
     * 批量删除廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizProbityForeignInvestmentByIds(String[] ids);
}
