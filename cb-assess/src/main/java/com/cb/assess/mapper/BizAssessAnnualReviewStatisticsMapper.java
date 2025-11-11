package com.cb.assess.mapper;


import com.cb.assess.domain.vo.BizAssessAnnualReviewStatisticsVo;
import com.cb.assess.domain.vo.BizAssessAnnualReviewTypeResultVo;
import com.cb.assess.domain.vo.RegularAssessmentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 省统计局二级巡视员、总师、处（室）负责人年度考核评议A类Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-24
 */
public interface BizAssessAnnualReviewStatisticsMapper
{
    /**
     * 查询省统计局二级巡视员、总师、处（室）负责人年度考核评议A类
     * 
     * @param id 省统计局二级巡视员、总师、处（室）负责人年度考核评议A类ID
     * @return 省统计局二级巡视员、总师、处（室）负责人年度考核评议A类
     */
    List<BizAssessAnnualReviewTypeResultVo> selectStatistics(BizAssessAnnualReviewTypeResultVo bizAssessAnnualReviewTypeResultVo);
    List<BizAssessAnnualReviewStatisticsVo> selectYears();

    /**
     * 查找平时考核
     * @param userIds
     * @param year
     * @return
     */
    List<RegularAssessmentVo> regularAssessment(@Param("userIds") List<Long> userIds,@Param("year")String year);

    List<RegularAssessmentVo> regularAssessmentListById(@Param("userId") Long userId,@Param("year")String year);
}
