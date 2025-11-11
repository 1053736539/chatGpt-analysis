package com.cb.probity.service;

import java.util.List;

import com.cb.probity.domain.BizProbityMarital;

/**
 * 廉政档案-4.本人婚姻状况及紧急联系人情况Service接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface IBizProbityMaritalService {
    /**
     * 查询廉政档案-4.本人婚姻状况及紧急联系人情况
     *
     * @param id 廉政档案-4.本人婚姻状况及紧急联系人情况ID
     * @return 廉政档案-4.本人婚姻状况及紧急联系人情况
     */
    public BizProbityMarital selectBizProbityMaritalById(String id);

    /**
     * 查询廉政档案-4.本人婚姻状况及紧急联系人情况列表
     *
     * @param bizProbityMarital 廉政档案-4.本人婚姻状况及紧急联系人情况
     * @return 廉政档案-4.本人婚姻状况及紧急联系人情况集合
     */
    public List<BizProbityMarital> selectBizProbityMaritalList(BizProbityMarital bizProbityMarital);

    /**
     * 新增廉政档案-4.本人婚姻状况及紧急联系人情况
     *
     * @param bizProbityMarital 廉政档案-4.本人婚姻状况及紧急联系人情况
     * @return 结果
     */
    public int insertBizProbityMarital(BizProbityMarital bizProbityMarital);

    /**
     * 批量新增廉政档案-4.本人婚姻状况及紧急联系人情况
     *
     * @param entities
     * @return
     */
    public int insertBatch(List<BizProbityMarital> entities);

    /**
     * 修改廉政档案-4.本人婚姻状况及紧急联系人情况
     *
     * @param bizProbityMarital 廉政档案-4.本人婚姻状况及紧急联系人情况
     * @return 结果
     */
    public int updateBizProbityMarital(BizProbityMarital bizProbityMarital);

    /**
     * 批量删除廉政档案-4.本人婚姻状况及紧急联系人情况
     *
     * @param ids 需要删除的廉政档案-4.本人婚姻状况及紧急联系人情况ID
     * @return 结果
     */
    public int deleteBizProbityMaritalByIds(String[] ids);

    /**
     * 删除廉政档案-4.本人婚姻状况及紧急联系人情况信息
     *
     * @param id 廉政档案-4.本人婚姻状况及紧急联系人情况ID
     * @return 结果
     */
    public int deleteBizProbityMaritalById(String id);
}
