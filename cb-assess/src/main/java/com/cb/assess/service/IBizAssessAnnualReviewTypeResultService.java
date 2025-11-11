package com.cb.assess.service;

import com.cb.assess.domain.BizAssessAnnualReviewTypeResult;
import com.cb.assess.domain.vo.BizAssessAnnualReviewTypeResultVo;

import java.util.List;

/**
 * AB类评分结果，Service接口
 * 
 * @author ruoyi
 * @date 2023-12-16
 */
public interface IBizAssessAnnualReviewTypeResultService 
{
    /**
     * 查询AB类评分结果，
     * 
     * @param id AB类评分结果，ID
     * @return AB类评分结果，
     */
    public BizAssessAnnualReviewTypeResult selectBizAssessAnnualReviewTypeResultById(String id);

    /**
     * 查询AB类评分结果，列表
     * 
     * @param bizAssessAnnualReviewTypeResult AB类评分结果，
     * @return AB类评分结果，集合
     */
    public List<BizAssessAnnualReviewTypeResultVo> selectBizAssessAnnualReviewTypeResultList(BizAssessAnnualReviewTypeResultVo bizAssessAnnualReviewTypeResult);

    /**
     * 设置序号
     * @param list
     * @return
     */
    public List<BizAssessAnnualReviewTypeResultVo> setIndex(List<BizAssessAnnualReviewTypeResultVo> list);

    /**
     * 这只平时考核
     * @param list
     * @return
     */
    public List<BizAssessAnnualReviewTypeResultVo> setRegularAssessment(List<BizAssessAnnualReviewTypeResultVo> list);

    /**
     * 新增AB类评分结果，
     * 
     * @param bizAssessAnnualReviewTypeResult AB类评分结果，
     * @return 结果
     */
    public int insertBizAssessAnnualReviewTypeResult(BizAssessAnnualReviewTypeResult bizAssessAnnualReviewTypeResult);

    /**
     * 修改AB类评分结果，
     * 
     * @param bizAssessAnnualReviewTypeResult AB类评分结果，
     * @return 结果
     */
    public int updateBizAssessAnnualReviewTypeResult(BizAssessAnnualReviewTypeResult bizAssessAnnualReviewTypeResult);

    /**
     * 批量删除AB类评分结果，
     * 
     * @param ids 需要删除的AB类评分结果，ID
     * @return 结果
     */
    public int deleteBizAssessAnnualReviewTypeResultByIds(String[] ids);

    /**
     * 删除AB类评分结果，信息
     * 
     * @param id AB类评分结果，ID
     * @return 结果
     */
    public int deleteBizAssessAnnualReviewTypeResultById(String id);

    //公示年度优秀名单
    public void publicityAnnualReviewTypeResult(String year);
}
