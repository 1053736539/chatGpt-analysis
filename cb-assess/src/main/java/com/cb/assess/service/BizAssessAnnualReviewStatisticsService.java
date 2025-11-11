package com.cb.assess.service;

import com.cb.assess.domain.vo.BizAssessAnnualReviewStatisticsVo;
import com.cb.assess.domain.vo.BizAssessAnnualReviewTypeResultVo;
import com.cb.assess.domain.vo.RegularAssessmentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BizAssessAnnualReviewStatisticsService {
    List<BizAssessAnnualReviewTypeResultVo> selectStatistics(BizAssessAnnualReviewTypeResultVo bizAssessAnnualReviewStatisticsVo);

    List<BizAssessAnnualReviewStatisticsVo> selectYears();

    List<RegularAssessmentVo> regularAssessment(List<Long> userIds, String year);

    List<RegularAssessmentVo> regularAssessmentListById(Long userId, String year);
}
