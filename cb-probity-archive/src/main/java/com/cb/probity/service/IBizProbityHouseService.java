package com.cb.probity.service;

import java.util.List;

import com.cb.probity.domain.BizProbityHouse;

/**
 * 廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况Service接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface IBizProbityHouseService {
    /**
     * 查询廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况
     *
     * @param id 廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况ID
     * @return 廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况
     */
    public BizProbityHouse selectBizProbityHouseById(String id);

    /**
     * 查询廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况列表
     *
     * @param bizProbityHouse 廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况
     * @return 廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况集合
     */
    public List<BizProbityHouse> selectBizProbityHouseList(BizProbityHouse bizProbityHouse);

    /**
     * 新增廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况
     *
     * @param bizProbityHouse 廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况
     * @return 结果
     */
    public int insertBizProbityHouse(BizProbityHouse bizProbityHouse);

    /**
     * 批量新增廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况
     *
     * @param entities
     * @return
     */
    public int insertBatch(List<BizProbityHouse> entities);

    /**
     * 修改廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况
     *
     * @param bizProbityHouse 廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况
     * @return 结果
     */
    public int updateBizProbityHouse(BizProbityHouse bizProbityHouse);

    /**
     * 批量删除廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况
     *
     * @param ids 需要删除的廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况ID
     * @return 结果
     */
    public int deleteBizProbityHouseByIds(String[] ids);

    /**
     * 删除廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况信息
     *
     * @param id 廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况ID
     * @return 结果
     */
    public int deleteBizProbityHouseById(String id);
}
