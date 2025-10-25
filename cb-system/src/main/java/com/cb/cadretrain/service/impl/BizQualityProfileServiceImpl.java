package com.cb.cadretrain.service.impl;

import java.util.List;

import com.cb.cadretrain.domain.BizQualityProfile;
import com.cb.cadretrain.mapper.BizQualityProfileMapper;
import com.cb.cadretrain.service.IBizQualityProfileService;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 干部政治素质档案Service业务层处理
 * 
 * @author lennon
 * @date 2023-11-01
 */
@Service
public class BizQualityProfileServiceImpl implements IBizQualityProfileService
{
    @Autowired
    private BizQualityProfileMapper bizQualityProfileMapper;

    /**
     * 查询干部政治素质档案
     * 
     * @param profileId 干部政治素质档案ID
     * @return 干部政治素质档案
     */
    @Override
    public BizQualityProfile selectBizQualityProfileById(String profileId)
    {
        return bizQualityProfileMapper.selectBizQualityProfileById(profileId);
    }

    /**
     * 查询干部政治素质档案列表
     * 
     * @param bizQualityProfile 干部政治素质档案
     * @return 干部政治素质档案
     */
    @Override
    public List<BizQualityProfile> selectBizQualityProfileList(BizQualityProfile bizQualityProfile)
    {
//        bizQualityProfile.setUsername(SecurityUtils.getUsername());
        bizQualityProfile.setCreateBy(SecurityUtils.getUsername());
        return bizQualityProfileMapper.selectBizQualityProfileList(bizQualityProfile);
    }

    /**
     * 新增干部政治素质档案
     * 
     * @param bizQualityProfile 干部政治素质档案
     * @return 结果
     */
    @Override
    public int insertBizQualityProfile(BizQualityProfile bizQualityProfile)
    {
        bizQualityProfile.setCreateTime(DateUtils.getNowDate());
        bizQualityProfile.setProfileId(IdUtils.randomUUID());
        bizQualityProfile.setCreateBy(SecurityUtils.getUsername());
        return bizQualityProfileMapper.insertBizQualityProfile(bizQualityProfile);
    }

    /**
     * 修改干部政治素质档案
     * 
     * @param bizQualityProfile 干部政治素质档案
     * @return 结果
     */
    @Override
    public int updateBizQualityProfile(BizQualityProfile bizQualityProfile)
    {
        bizQualityProfile.setUpdateTime(DateUtils.getNowDate());
        return bizQualityProfileMapper.updateBizQualityProfile(bizQualityProfile);
    }

    /**
     * 批量删除干部政治素质档案
     * 
     * @param profileIds 需要删除的干部政治素质档案ID
     * @return 结果
     */
    @Override
    public int deleteBizQualityProfileByIds(String[] profileIds)
    {
        return bizQualityProfileMapper.deleteBizQualityProfileByIds(profileIds);
    }

    /**
     * 删除干部政治素质档案信息
     * 
     * @param profileId 干部政治素质档案ID
     * @return 结果
     */
    @Override
    public int deleteBizQualityProfileById(String profileId)
    {
        return bizQualityProfileMapper.deleteBizQualityProfileById(profileId);
    }
}
