package com.cb.assess.mapper;

import com.cb.assess.domain.BizAssessCadrePoliticalQuality;
import com.cb.assess.domain.vo.BizAssessCadrePoliticalQualityVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * xxxx年处级领导干部政治素质年度考察测评Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-16
 */
public interface BizAssessCadrePoliticalQualityMapper 
{
    /**
     * 查询xxxx年处级领导干部政治素质年度考察测评
     * 
     * @param id xxxx年处级领导干部政治素质年度考察测评ID
     * @return xxxx年处级领导干部政治素质年度考察测评
     */
    public BizAssessCadrePoliticalQualityVo selectBizAssessCadrePoliticalQualityById(String id);

    /**
     * 查询xxxx年处级领导干部政治素质年度考察测评列表
     * 
     * @param bizAssessCadrePoliticalQuality xxxx年处级领导干部政治素质年度考察测评
     * @return xxxx年处级领导干部政治素质年度考察测评集合
     */
    public List<BizAssessCadrePoliticalQualityVo> selectBizAssessCadrePoliticalQualityList(BizAssessCadrePoliticalQualityVo bizAssessCadrePoliticalQualityVo);

    /**
     * 查询自己的待填政治素质考察表
     * @param reviewerUserId
     * @return
     */
    public BizAssessCadrePoliticalQualityVo selectCountMyTask(Long reviewerUserId);
    public List<BizAssessCadrePoliticalQualityVo> selectYears();
    //统计每个人的列表
    public List<BizAssessCadrePoliticalQualityVo> selectStatistics(BizAssessCadrePoliticalQualityVo bizAssessCadrePoliticalQualityVo);

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
     * 删除xxxx年处级领导干部政治素质年度考察测评
     * 
     * @param id xxxx年处级领导干部政治素质年度考察测评ID
     * @return 结果
     */
    public int deleteBizAssessCadrePoliticalQualityById(String id);

    /**
     * 批量删除xxxx年处级领导干部政治素质年度考察测评
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAssessCadrePoliticalQualityByIds(String[] ids);
    public int deleteByUserIdAndYear(@Param("userId") Long userId,@Param("year")  String year);
}
