package com.cb.assess.service;

import com.cb.assess.domain.BizAssessCadrePoliticalQualityIdentify;
import com.cb.assess.domain.vo.BizAssessCadrePoliticalQualityIdentifyVo;

import java.util.List;

/**
 * 政治素质鉴定Service接口
 * 
 * @author ruoyi
 * @date 2023-12-22
 */
public interface IBizAssessCadrePoliticalQualityIdentifyService 
{
    /**
     * 查询政治素质鉴定
     * 
     * @param id 政治素质鉴定ID
     * @return 政治素质鉴定
     */
    public BizAssessCadrePoliticalQualityIdentifyVo selectBizAssessCadrePoliticalQualityIdentifyById(String id);

    BizAssessCadrePoliticalQualityIdentify selectStaticsByUser(String userId, String assessmentYear);

    /**
     * 查询政治素质鉴定列表
     * 
     * @param bizAssessCadrePoliticalQualityIdentify 政治素质鉴定
     * @return 政治素质鉴定集合
     */
    public List<BizAssessCadrePoliticalQualityIdentifyVo> selectBizAssessCadrePoliticalQualityIdentifyList(BizAssessCadrePoliticalQualityIdentifyVo bizAssessCadrePoliticalQualityIdentify);

    /**
     * 获取主管分管领导要看的
     * @param bizAssessCadrePoliticalQualityIdentify
     * @return
     */
    List<BizAssessCadrePoliticalQualityIdentifyVo> listByManagerUser(BizAssessCadrePoliticalQualityIdentifyVo bizAssessCadrePoliticalQualityIdentify);

    List<BizAssessCadrePoliticalQualityIdentifyVo> selectYears();

    /**
     * 新增政治素质鉴定
     * 
     * @param bizAssessCadrePoliticalQualityIdentify 政治素质鉴定
     * @return 结果
     */
    public int insertBizAssessCadrePoliticalQualityIdentify(BizAssessCadrePoliticalQualityIdentify bizAssessCadrePoliticalQualityIdentify);

    /**
     * 修改政治素质鉴定
     * 
     * @param bizAssessCadrePoliticalQualityIdentify 政治素质鉴定
     * @return 结果
     */
    public int updateBizAssessCadrePoliticalQualityIdentify(BizAssessCadrePoliticalQualityIdentify bizAssessCadrePoliticalQualityIdentify);

    int updateSetIsReport(BizAssessCadrePoliticalQualityIdentify bizAssessCadrePoliticalQualityIdentify);

    /**
     * 批量删除政治素质鉴定
     * 
     * @param ids 需要删除的政治素质鉴定ID
     * @return 结果
     */
    public int deleteBizAssessCadrePoliticalQualityIdentifyByIds(String[] ids);

    /**
     * 删除政治素质鉴定信息
     * 
     * @param id 政治素质鉴定ID
     * @return 结果
     */
    public int deleteBizAssessCadrePoliticalQualityIdentifyById(String id);
}
