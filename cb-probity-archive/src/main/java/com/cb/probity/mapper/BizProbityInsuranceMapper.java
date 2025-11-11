package com.cb.probity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.cb.probity.domain.BizProbityInsurance;

/**
 * 廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况Mapper接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface BizProbityInsuranceMapper {
    /**
     * 查询廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况
     *
     * @param id 廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况ID
     * @return 廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况
     */
    public BizProbityInsurance selectBizProbityInsuranceById(String id);

    /**
     * 查询廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况列表
     *
     * @param bizProbityInsurance 廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况
     * @return 廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况集合
     */
    public List<BizProbityInsurance> selectBizProbityInsuranceList(BizProbityInsurance bizProbityInsurance);

    /**
     * 新增廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况
     *
     * @param bizProbityInsurance 廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况
     * @return 结果
     */
    public int insertBizProbityInsurance(BizProbityInsurance bizProbityInsurance);


    /**
     * 批量新增廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况
     *
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<BizProbityInsurance> entities);

    /**
     * 修改廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况
     *
     * @param bizProbityInsurance 廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况
     * @return 结果
     */
    public int updateBizProbityInsurance(BizProbityInsurance bizProbityInsurance);

    /**
     * 删除廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况
     *
     * @param id 廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况ID
     * @return 结果
     */
    public int deleteBizProbityInsuranceById(String id);

    /**
     * 批量删除廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizProbityInsuranceByIds(String[] ids);
}
