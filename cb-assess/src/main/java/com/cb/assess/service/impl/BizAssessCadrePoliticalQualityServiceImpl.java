package com.cb.assess.service.impl;

import java.util.List;

import com.cb.assess.domain.BizAssessCadrePoliticalQuality;
import com.cb.assess.domain.vo.BizAssessCadrePoliticalQualityVo;
import com.cb.assess.mapper.BizAssessCadrePoliticalQualityMapper;
import com.cb.assess.service.IBizAssessCadrePoliticalQualityService;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * xxxx年处级领导干部政治素质年度考察测评Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-16
 */
@Service
public class BizAssessCadrePoliticalQualityServiceImpl implements IBizAssessCadrePoliticalQualityService
{
    @Autowired
    private BizAssessCadrePoliticalQualityMapper bizAssessCadrePoliticalQualityMapper;

    /**
     * 查询xxxx年处级领导干部政治素质年度考察测评
     * 
     * @param id xxxx年处级领导干部政治素质年度考察测评ID
     * @return xxxx年处级领导干部政治素质年度考察测评
     */
    @Override
    public BizAssessCadrePoliticalQualityVo selectBizAssessCadrePoliticalQualityById(String id)
    {
        return bizAssessCadrePoliticalQualityMapper.selectBizAssessCadrePoliticalQualityById(id);
    }

    /**
     * 查询xxxx年处级领导干部政治素质年度考察测评列表
     * 
     * @param bizAssessCadrePoliticalQuality xxxx年处级领导干部政治素质年度考察测评
     * @return xxxx年处级领导干部政治素质年度考察测评
     */
    @Override
    public List<BizAssessCadrePoliticalQualityVo> selectBizAssessCadrePoliticalQualityList(BizAssessCadrePoliticalQualityVo bizAssessCadrePoliticalQuality)
    {
        return bizAssessCadrePoliticalQualityMapper.selectBizAssessCadrePoliticalQualityList(bizAssessCadrePoliticalQuality);
    }
    @Override
    public List<BizAssessCadrePoliticalQualityVo> selectStatistics(BizAssessCadrePoliticalQualityVo bizAssessCadrePoliticalQuality)
    {
        return bizAssessCadrePoliticalQualityMapper.selectStatistics(bizAssessCadrePoliticalQuality);
    }
    @Override
    public List<BizAssessCadrePoliticalQualityVo> selectYears(){
        return bizAssessCadrePoliticalQualityMapper.selectYears();
    }
    /**
     * 新增xxxx年处级领导干部政治素质年度考察测评
     * 
     * @param bizAssessCadrePoliticalQuality xxxx年处级领导干部政治素质年度考察测评
     * @return 结果
     */
    @Override
    public int insertBizAssessCadrePoliticalQuality(BizAssessCadrePoliticalQuality bizAssessCadrePoliticalQuality)
    {
        bizAssessCadrePoliticalQuality.setId(UUID.randomUUID().toString());
        bizAssessCadrePoliticalQuality.setCreateTime(DateUtils.getNowDate());
        bizAssessCadrePoliticalQuality.setCreateBy(SecurityUtils.getLoginUser().getUsername());
        return bizAssessCadrePoliticalQualityMapper.insertBizAssessCadrePoliticalQuality(bizAssessCadrePoliticalQuality);
    }

    /**
     * 修改xxxx年处级领导干部政治素质年度考察测评
     * 
     * @param bizAssessCadrePoliticalQuality xxxx年处级领导干部政治素质年度考察测评
     * @return 结果
     */
    @Override
    public int updateBizAssessCadrePoliticalQuality(BizAssessCadrePoliticalQuality bizAssessCadrePoliticalQuality)
    {
        bizAssessCadrePoliticalQuality.setUpdateBy(SecurityUtils.getLoginUser().getUsername());
        bizAssessCadrePoliticalQuality.setUpdateTime(DateUtils.getNowDate());
        return bizAssessCadrePoliticalQualityMapper.updateBizAssessCadrePoliticalQuality(bizAssessCadrePoliticalQuality);
    }

    /**
     * 批量删除xxxx年处级领导干部政治素质年度考察测评
     * 
     * @param ids 需要删除的xxxx年处级领导干部政治素质年度考察测评ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessCadrePoliticalQualityByIds(String[] ids)
    {
        return bizAssessCadrePoliticalQualityMapper.deleteBizAssessCadrePoliticalQualityByIds(ids);
    }

    /**
     * 删除xxxx年处级领导干部政治素质年度考察测评信息
     * 
     * @param id xxxx年处级领导干部政治素质年度考察测评ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessCadrePoliticalQualityById(String id)
    {
        return bizAssessCadrePoliticalQualityMapper.deleteBizAssessCadrePoliticalQualityById(id);
    }
    @Override
    public int deleteByUserIdAndYear(Long userId,String year)
    {
        return bizAssessCadrePoliticalQualityMapper.deleteByUserIdAndYear(userId,year);
    }
}
