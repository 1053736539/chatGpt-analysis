package com.cb.assess.service.impl;

import java.util.List;

import com.cb.assess.domain.BizAssessQuotaAllocationUser;
import com.cb.assess.mapper.BizAssessQuotaAllocationUserMapper;
import com.cb.assess.service.IBizAssessQuotaAllocationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 优秀名额分配单位人员名单Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-16
 */
@Service
public class BizAssessQuotaAllocationUserServiceImpl implements IBizAssessQuotaAllocationUserService
{
    @Autowired
    private BizAssessQuotaAllocationUserMapper bizAssessQuotaAllocationUserMapper;

    /**
     * 查询优秀名额分配单位人员名单
     * 
     * @param userId 优秀名额分配单位人员名单ID
     * @return 优秀名额分配单位人员名单
     */
    @Override
    public BizAssessQuotaAllocationUser selectBizAssessQuotaAllocationUserById(String userId)
    {
        return bizAssessQuotaAllocationUserMapper.selectBizAssessQuotaAllocationUserById(userId);
    }

    /**
     * 查询优秀名额分配单位人员名单列表
     * 
     * @param bizAssessQuotaAllocationUser 优秀名额分配单位人员名单
     * @return 优秀名额分配单位人员名单
     */
    @Override
    public List<BizAssessQuotaAllocationUser> selectBizAssessQuotaAllocationUserList(BizAssessQuotaAllocationUser bizAssessQuotaAllocationUser)
    {
        return bizAssessQuotaAllocationUserMapper.selectBizAssessQuotaAllocationUserList(bizAssessQuotaAllocationUser);
    }

    /**
     * 新增优秀名额分配单位人员名单
     * 
     * @param bizAssessQuotaAllocationUser 优秀名额分配单位人员名单
     * @return 结果
     */
    @Override
    public int insertBizAssessQuotaAllocationUser(BizAssessQuotaAllocationUser bizAssessQuotaAllocationUser)
    {
        return bizAssessQuotaAllocationUserMapper.insertBizAssessQuotaAllocationUser(bizAssessQuotaAllocationUser);
    }

    /**
     * 修改优秀名额分配单位人员名单
     * 
     * @param bizAssessQuotaAllocationUser 优秀名额分配单位人员名单
     * @return 结果
     */
    @Override
    public int updateBizAssessQuotaAllocationUser(BizAssessQuotaAllocationUser bizAssessQuotaAllocationUser)
    {
        return bizAssessQuotaAllocationUserMapper.updateBizAssessQuotaAllocationUser(bizAssessQuotaAllocationUser);
    }

    /**
     * 批量删除优秀名额分配单位人员名单
     * 
     * @param userIds 需要删除的优秀名额分配单位人员名单ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessQuotaAllocationUserByIds(String[] userIds)
    {
        return bizAssessQuotaAllocationUserMapper.deleteBizAssessQuotaAllocationUserByIds(userIds);
    }

    /**
     * 删除优秀名额分配单位人员名单信息
     * 
     * @param userId 优秀名额分配单位人员名单ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessQuotaAllocationUserById(String userId)
    {
        return bizAssessQuotaAllocationUserMapper.deleteBizAssessQuotaAllocationUserById(userId);
    }

    @Override
    public int deleteByQuery(BizAssessQuotaAllocationUser bizAssessQuotaAllocationUser) {
        return bizAssessQuotaAllocationUserMapper.deleteByQuery(bizAssessQuotaAllocationUser);
    }
}
