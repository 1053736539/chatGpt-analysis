package com.cb.probity.service;

import java.util.List;

import com.cb.probity.domain.BizProbityEnterprise;

/**
 * 廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况Service接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface IBizProbityEnterpriseService {
    /**
     * 查询廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况
     *
     * @param id 廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况ID
     * @return 廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况
     */
    public BizProbityEnterprise selectBizProbityEnterpriseById(String id);

    /**
     * 查询廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况列表
     *
     * @param bizProbityEnterprise 廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况
     * @return 廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况集合
     */
    public List<BizProbityEnterprise> selectBizProbityEnterpriseList(BizProbityEnterprise bizProbityEnterprise);

    /**
     * 新增廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况
     *
     * @param bizProbityEnterprise 廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况
     * @return 结果
     */
    public int insertBizProbityEnterprise(BizProbityEnterprise bizProbityEnterprise);

    /**
     * 批量新增廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况
     *
     * @param entities
     * @return
     */
    public int insertBatch(List<BizProbityEnterprise> entities);

    /**
     * 修改廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况
     *
     * @param bizProbityEnterprise 廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况
     * @return 结果
     */
    public int updateBizProbityEnterprise(BizProbityEnterprise bizProbityEnterprise);

    /**
     * 批量删除廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况
     *
     * @param ids 需要删除的廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况ID
     * @return 结果
     */
    public int deleteBizProbityEnterpriseByIds(String[] ids);

    /**
     * 删除廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况信息
     *
     * @param id 廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况ID
     * @return 结果
     */
    public int deleteBizProbityEnterpriseById(String id);
}
