package com.cb.assess.mapper;

import com.cb.assess.domain.BizAssessAnnualReviewTypeResult;
import com.cb.assess.domain.vo.BizAssessAnnualReviewTypeResultVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * AB类评分结果，Mapper接口
 * 
 * @author ruoyi
 * @date 2023-12-16
 */
public interface BizAssessAnnualReviewTypeResultMapper 
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
    public int updateToPublicity(@Param("isPublicity") String isPublicity ,@Param("year") String year);
    public int updateToCannotEdit(@Param("year") String year);

    /**
     * 删除AB类评分结果，
     * 
     * @param id AB类评分结果，ID
     * @return 结果
     */
    public int deleteBizAssessAnnualReviewTypeResultById(String id);

    /**
     * 批量删除AB类评分结果，
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAssessAnnualReviewTypeResultByIds(String[] ids);
}
