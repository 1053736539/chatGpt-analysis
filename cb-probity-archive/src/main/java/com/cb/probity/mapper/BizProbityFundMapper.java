package com.cb.probity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.cb.probity.domain.BizProbityFund;

/**
 * 廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况Mapper接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface BizProbityFundMapper {
    /**
     * 查询廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况
     *
     * @param id 廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况ID
     * @return 廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况
     */
    public BizProbityFund selectBizProbityFundById(String id);

    /**
     * 查询廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况列表
     *
     * @param bizProbityFund 廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况
     * @return 廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况集合
     */
    public List<BizProbityFund> selectBizProbityFundList(BizProbityFund bizProbityFund);

    /**
     * 新增廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况
     *
     * @param bizProbityFund 廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况
     * @return 结果
     */
    public int insertBizProbityFund(BizProbityFund bizProbityFund);


    /**
     * 批量新增廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况
     *
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<BizProbityFund> entities);

    /**
     * 修改廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况
     *
     * @param bizProbityFund 廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况
     * @return 结果
     */
    public int updateBizProbityFund(BizProbityFund bizProbityFund);

    /**
     * 删除廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况
     *
     * @param id 廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况ID
     * @return 结果
     */
    public int deleteBizProbityFundById(String id);

    /**
     * 批量删除廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizProbityFundByIds(String[] ids);
}
