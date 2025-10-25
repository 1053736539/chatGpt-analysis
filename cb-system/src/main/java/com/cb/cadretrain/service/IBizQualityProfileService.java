package com.cb.cadretrain.service;

import java.util.List;
import com.cb.cadretrain.domain.BizQualityProfile;

/**
 * 干部政治素质档案Service接口
 * 
 * @author lennon
 * @date 2023-11-01
 */
public interface IBizQualityProfileService 
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
     * 批量删除干部政治素质档案
     * 
     * @param profileIds 需要删除的干部政治素质档案ID
     * @return 结果
     */
    public int deleteBizQualityProfileByIds(String[] profileIds);

    /**
     * 删除干部政治素质档案信息
     * 
     * @param profileId 干部政治素质档案ID
     * @return 结果
     */
    public int deleteBizQualityProfileById(String profileId);
}
