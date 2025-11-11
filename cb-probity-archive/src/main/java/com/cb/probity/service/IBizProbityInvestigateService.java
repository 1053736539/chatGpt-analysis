package com.cb.probity.service;

import java.util.List;

import com.cb.probity.domain.BizProbityInvestigate;

/**
 * 廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录Service接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface IBizProbityInvestigateService {
    /**
     * 查询廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录
     *
     * @param id 廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录ID
     * @return 廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录
     */
    public BizProbityInvestigate selectBizProbityInvestigateById(String id);

    /**
     * 查询廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录列表
     *
     * @param bizProbityInvestigate 廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录
     * @return 廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录集合
     */
    public List<BizProbityInvestigate> selectBizProbityInvestigateList(BizProbityInvestigate bizProbityInvestigate);

    /**
     * 新增廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录
     *
     * @param bizProbityInvestigate 廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录
     * @return 结果
     */
    public int insertBizProbityInvestigate(BizProbityInvestigate bizProbityInvestigate);

    /**
     * 批量新增廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录
     *
     * @param entities
     * @return
     */
    public int insertBatch(List<BizProbityInvestigate> entities);

    /**
     * 修改廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录
     *
     * @param bizProbityInvestigate 廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录
     * @return 结果
     */
    public int updateBizProbityInvestigate(BizProbityInvestigate bizProbityInvestigate);

    /**
     * 批量删除廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录
     *
     * @param ids 需要删除的廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录ID
     * @return 结果
     */
    public int deleteBizProbityInvestigateByIds(String[] ids);

    /**
     * 删除廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录信息
     *
     * @param id 廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录ID
     * @return 结果
     */
    public int deleteBizProbityInvestigateById(String id);
}
