package com.cb.probity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.cb.probity.domain.BizProbityForeignDeposit;

/**
 * 廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况Mapper接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface BizProbityForeignDepositMapper {
    /**
     * 查询廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况
     *
     * @param id 廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况ID
     * @return 廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况
     */
    public BizProbityForeignDeposit selectBizProbityForeignDepositById(String id);

    /**
     * 查询廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况列表
     *
     * @param bizProbityForeignDeposit 廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况
     * @return 廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况集合
     */
    public List<BizProbityForeignDeposit> selectBizProbityForeignDepositList(BizProbityForeignDeposit bizProbityForeignDeposit);

    /**
     * 新增廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况
     *
     * @param bizProbityForeignDeposit 廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况
     * @return 结果
     */
    public int insertBizProbityForeignDeposit(BizProbityForeignDeposit bizProbityForeignDeposit);


    /**
     * 批量新增廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况
     *
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<BizProbityForeignDeposit> entities);

    /**
     * 修改廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况
     *
     * @param bizProbityForeignDeposit 廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况
     * @return 结果
     */
    public int updateBizProbityForeignDeposit(BizProbityForeignDeposit bizProbityForeignDeposit);

    /**
     * 删除廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况
     *
     * @param id 廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况ID
     * @return 结果
     */
    public int deleteBizProbityForeignDepositById(String id);

    /**
     * 批量删除廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizProbityForeignDepositByIds(String[] ids);
}
