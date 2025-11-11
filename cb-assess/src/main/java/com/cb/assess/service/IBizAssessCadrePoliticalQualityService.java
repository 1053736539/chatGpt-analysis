package com.cb.assess.service;

import com.cb.assess.domain.BizAssessCadrePoliticalQuality;
import com.cb.assess.domain.vo.BizAssessCadrePoliticalQualityVo;

import java.util.List;

/**
 * xxxx年处级领导干部政治素质年度考察测评Service接口
 * 
 * @author ruoyi
 * @date 2023-11-16
 */
public interface IBizAssessCadrePoliticalQualityService 
{
    /**
     * 查询xxxx年处级领导干部政治素质年度考察测评
     * 
     * @param id xxxx年处级领导干部政治素质年度考察测评ID
     * @return xxxx年处级领导干部政治素质年度考察测评
     */
    public BizAssessCadrePoliticalQuality selectBizAssessCadrePoliticalQualityById(String id);

    /**
     * 查询xxxx年处级领导干部政治素质年度考察测评列表
     * 
     * @param bizAssessCadrePoliticalQuality xxxx年处级领导干部政治素质年度考察测评
     * @return xxxx年处级领导干部政治素质年度考察测评集合
     */
    public List<BizAssessCadrePoliticalQualityVo> selectBizAssessCadrePoliticalQualityList(BizAssessCadrePoliticalQualityVo cadrePoliticalQualityVo);
    public List<BizAssessCadrePoliticalQualityVo> selectStatistics(BizAssessCadrePoliticalQualityVo cadrePoliticalQualityVo);

    List<BizAssessCadrePoliticalQualityVo> selectYears();

    /**
     * 新增xxxx年处级领导干部政治素质年度考察测评
     * 
     * @param bizAssessCadrePoliticalQuality xxxx年处级领导干部政治素质年度考察测评
     * @return 结果
     */
    public int insertBizAssessCadrePoliticalQuality(BizAssessCadrePoliticalQuality bizAssessCadrePoliticalQuality);

    /**
     * 修改xxxx年处级领导干部政治素质年度考察测评
     * 
     * @param bizAssessCadrePoliticalQuality xxxx年处级领导干部政治素质年度考察测评
     * @return 结果
     */
    public int updateBizAssessCadrePoliticalQuality(BizAssessCadrePoliticalQuality bizAssessCadrePoliticalQuality);

    /**
     * 批量删除xxxx年处级领导干部政治素质年度考察测评
     * 
     * @param ids 需要删除的xxxx年处级领导干部政治素质年度考察测评ID
     * @return 结果
     */
    public int deleteBizAssessCadrePoliticalQualityByIds(String[] ids);

    /**
     * 删除xxxx年处级领导干部政治素质年度考察测评信息
     * 
     * @param id xxxx年处级领导干部政治素质年度考察测评ID
     * @return 结果
     */
    public int deleteBizAssessCadrePoliticalQualityById(String id);

    int deleteByUserIdAndYear(Long userId,String year);
}
