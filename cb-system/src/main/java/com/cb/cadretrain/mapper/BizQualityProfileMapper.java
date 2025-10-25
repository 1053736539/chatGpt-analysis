package com.cb.cadretrain.mapper;

import com.cb.cadretrain.domain.BizQualityProfile;

import java.util.List;

/**
 * 干部政治素质档案Mapper接口
 * 
 * @author lennon
 * @date 2023-11-01
 */
public interface BizQualityProfileMapper 
{
    /**
     * 查询干部政治素质档案
     * 
     * @param profileId 干部政治素质档案ID
     * @return 干部政治素质档案
     */
    public BizQualityProfile selectBizQualityProfileById(String profileId);

    /**
     * 查询干部政治素质档案列表
     * 
     * @param bizQualityProfile 干部政治素质档案
     * @return 干部政治素质档案集合
     */
    public List<BizQualityProfile> selectBizQualityProfileList(BizQualityProfile bizQualityProfile);

    /**
     * 新增干部政治素质档案
     * 
     * @param bizQualityProfile 干部政治素质档案
     * @return 结果
     */
    public int insertBizQualityProfile(BizQualityProfile bizQualityProfile);

    /**
     * 修改干部政治素质档案
     * 
     * @param bizQualityProfile 干部政治素质档案
     * @return 结果
     */
    public int updateBizQualityProfile(BizQualityProfile bizQualityProfile);

    /**
     * 删除干部政治素质档案
     * 
     * @param profileId 干部政治素质档案ID
     * @return 结果
     */
    public int deleteBizQualityProfileById(String profileId);

    /**
     * 批量删除干部政治素质档案
     * 
     * @param profileIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizQualityProfileByIds(String[] profileIds);
}
