package com.cb.probity.service;

import java.util.List;

import com.cb.probity.domain.BizProbityGift;

/**
 * 廉政档案-3.本人拒收或上交礼金、礼品情况登记Service接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface IBizProbityGiftService {
    /**
     * 查询廉政档案-3.本人拒收或上交礼金、礼品情况登记
     *
     * @param id 廉政档案-3.本人拒收或上交礼金、礼品情况登记ID
     * @return 廉政档案-3.本人拒收或上交礼金、礼品情况登记
     */
    public BizProbityGift selectBizProbityGiftById(String id);

    /**
     * 查询廉政档案-3.本人拒收或上交礼金、礼品情况登记列表
     *
     * @param bizProbityGift 廉政档案-3.本人拒收或上交礼金、礼品情况登记
     * @return 廉政档案-3.本人拒收或上交礼金、礼品情况登记集合
     */
    public List<BizProbityGift> selectBizProbityGiftList(BizProbityGift bizProbityGift);

    /**
     * 新增廉政档案-3.本人拒收或上交礼金、礼品情况登记
     *
     * @param bizProbityGift 廉政档案-3.本人拒收或上交礼金、礼品情况登记
     * @return 结果
     */
    public int insertBizProbityGift(BizProbityGift bizProbityGift);

    /**
     * 批量新增廉政档案-3.本人拒收或上交礼金、礼品情况登记
     *
     * @param entities
     * @return
     */
    public int insertBatch(List<BizProbityGift> entities);

    /**
     * 修改廉政档案-3.本人拒收或上交礼金、礼品情况登记
     *
     * @param bizProbityGift 廉政档案-3.本人拒收或上交礼金、礼品情况登记
     * @return 结果
     */
    public int updateBizProbityGift(BizProbityGift bizProbityGift);

    /**
     * 批量删除廉政档案-3.本人拒收或上交礼金、礼品情况登记
     *
     * @param ids 需要删除的廉政档案-3.本人拒收或上交礼金、礼品情况登记ID
     * @return 结果
     */
    public int deleteBizProbityGiftByIds(String[] ids);

    /**
     * 删除廉政档案-3.本人拒收或上交礼金、礼品情况登记信息
     *
     * @param id 廉政档案-3.本人拒收或上交礼金、礼品情况登记ID
     * @return 结果
     */
    public int deleteBizProbityGiftById(String id);
}
