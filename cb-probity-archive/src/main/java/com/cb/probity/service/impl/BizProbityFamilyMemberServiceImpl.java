package com.cb.probity.service.impl;

import java.util.List;
import java.util.Date;
import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.probity.mapper.BizProbityFamilyMemberMapper;
import com.cb.probity.domain.BizProbityFamilyMember;
import com.cb.probity.service.IBizProbityFamilyMemberService;

/**
 * 廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-18
 */
@Service
public class BizProbityFamilyMemberServiceImpl implements IBizProbityFamilyMemberService 
{
    @Autowired
    private BizProbityFamilyMemberMapper bizProbityFamilyMemberMapper;

    /**
     * 查询廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）
     * 
     * @param id 廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）ID
     * @return 廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）
     */
    @Override
    public BizProbityFamilyMember selectBizProbityFamilyMemberById(String id)
    {
        return bizProbityFamilyMemberMapper.selectBizProbityFamilyMemberById(id);
    }

    /**
     * 查询廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）列表
     * 
     * @param bizProbityFamilyMember 廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）
     * @return 廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）
     */
    @Override
    public List<BizProbityFamilyMember> selectBizProbityFamilyMemberList(BizProbityFamilyMember bizProbityFamilyMember)
    {
        return bizProbityFamilyMemberMapper.selectBizProbityFamilyMemberList(bizProbityFamilyMember);
    }

    /**
     * 新增廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）
     * 
     * @param bizProbityFamilyMember 廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）
     * @return 结果
     */
    @Override
    public int insertBizProbityFamilyMember(BizProbityFamilyMember bizProbityFamilyMember)
    {
        if(StringUtils.isBlank(bizProbityFamilyMember.getId())) {
            bizProbityFamilyMember.setId(IdUtils.randomUUID());
        }
        try{
            bizProbityFamilyMember.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityFamilyMember.setCreateTime(DateUtils.getNowDate());
        return bizProbityFamilyMemberMapper.insertBizProbityFamilyMember(bizProbityFamilyMember);
    }


    /**
     * 批量新增廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizProbityFamilyMember> entities) {
        if(null == entities || entities.isEmpty()){
            return 0;
        }
        String userName = null;
        try{
            userName = SecurityUtils.getUsername();
        } catch (Exception e){}
        Date nowDate = DateUtils.getNowDate();
        String finalUserName = userName;
        entities.parallelStream().forEach(item -> {
            item.setId(IdUtils.randomUUID());
            item.setCreateBy(finalUserName);
            item.setCreateTime(nowDate);
        });
        // 定义每批次的大小
        int batchSize = 500;
        int totalInserted = 0;
        int num = entities.size() / batchSize + (entities.size() % batchSize == 0 ? 0 : 1);
        for (int i = 0; i < num; i++) {
            int start = i * batchSize;
            int end = Math.min(start + batchSize, entities.size());
            totalInserted +=  bizProbityFamilyMemberMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）
     * 
     * @param bizProbityFamilyMember 廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）
     * @return 结果
     */
    @Override
    public int updateBizProbityFamilyMember(BizProbityFamilyMember bizProbityFamilyMember)
    {
        try{
            bizProbityFamilyMember.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityFamilyMember.setUpdateTime(DateUtils.getNowDate());
        return bizProbityFamilyMemberMapper.updateBizProbityFamilyMember(bizProbityFamilyMember);
    }

    /**
     * 批量删除廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）
     * 
     * @param ids 需要删除的廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityFamilyMemberByIds(String[] ids)
    {
        return bizProbityFamilyMemberMapper.deleteBizProbityFamilyMemberByIds(ids);
    }

    /**
     * 删除廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）信息
     * 
     * @param id 廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityFamilyMemberById(String id)
    {
        return bizProbityFamilyMemberMapper.deleteBizProbityFamilyMemberById(id);
    }
}
