package com.cb.assess.mapper;

import com.cb.assess.domain.BizAssessQuotaAllocationUser;

import java.util.List;

/**
 * 优秀名额分配单位人员名单Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-16
 */
public interface BizAssessQuotaAllocationUserMapper 
{
    /**
     * 查询优秀名额分配单位人员名单
     * 
     * @param userId 优秀名额分配单位人员名单ID
     * @return 优秀名额分配单位人员名单
     */
    public BizAssessQuotaAllocationUser selectBizAssessQuotaAllocationUserById(String userId);

    /**
     * 查询优秀名额分配单位人员名单列表
     * 
     * @param bizAssessQuotaAllocationUser 优秀名额分配单位人员名单
     * @return 优秀名额分配单位人员名单集合
     */
    public List<BizAssessQuotaAllocationUser> selectBizAssessQuotaAllocationUserList(BizAssessQuotaAllocationUser bizAssessQuotaAllocationUser);

    /**
     * 新增优秀名额分配单位人员名单
     * 
     * @param bizAssessQuotaAllocationUser 优秀名额分配单位人员名单
     * @return 结果
     */
    public int insertBizAssessQuotaAllocationUser(BizAssessQuotaAllocationUser bizAssessQuotaAllocationUser);

    /**
     * 修改优秀名额分配单位人员名单
     * 
     * @param bizAssessQuotaAllocationUser 优秀名额分配单位人员名单
     * @return 结果
     */
    public int updateBizAssessQuotaAllocationUser(BizAssessQuotaAllocationUser bizAssessQuotaAllocationUser);

    /**
     * 删除优秀名额分配单位人员名单
     * 
     * @param userId 优秀名额分配单位人员名单ID
     * @return 结果
     */
    public int deleteBizAssessQuotaAllocationUserById(String userId);

    /**
     * 批量删除优秀名额分配单位人员名单
     * 
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAssessQuotaAllocationUserByIds(String[] userIds);
    public int deleteByQuery(BizAssessQuotaAllocationUser bizAssessQuotaAllocationUser);
}
