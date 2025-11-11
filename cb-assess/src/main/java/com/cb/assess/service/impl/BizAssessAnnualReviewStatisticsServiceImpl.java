package com.cb.assess.service.impl;

import com.cb.assess.domain.vo.BizAssessAnnualReviewStatisticsVo;
import com.cb.assess.domain.vo.BizAssessAnnualReviewTypeResultVo;
import com.cb.assess.domain.vo.RegularAssessmentVo;
import com.cb.assess.mapper.BizAssessAnnualReviewStatisticsMapper;
import com.cb.assess.service.BizAssessAnnualReviewStatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BizAssessAnnualReviewStatisticsServiceImpl implements BizAssessAnnualReviewStatisticsService {
    @Resource
    private BizAssessAnnualReviewStatisticsMapper bizAssessAnnualReviewStatisticsMapper;
    @Override
    public List<BizAssessAnnualReviewTypeResultVo> selectStatistics(BizAssessAnnualReviewTypeResultVo bizAssessAnnualReviewStatisticsVo) {
        //进行总分计算
        List<BizAssessAnnualReviewTypeResultVo> bizAssessAnnualReviewStatisticsVos = bizAssessAnnualReviewStatisticsMapper.selectStatistics(bizAssessAnnualReviewStatisticsVo);
        //拿平时考核
        List<Long> collect = bizAssessAnnualReviewStatisticsVos.stream().map(e -> e.getUserId()).collect(Collectors.toList());
        List<RegularAssessmentVo> regularAssessmentVos = this.regularAssessment(collect,bizAssessAnnualReviewStatisticsVo.getAssessmentYear());
        for (BizAssessAnnualReviewTypeResultVo assessAnnualReviewStatisticsVo : bizAssessAnnualReviewStatisticsVos) {
            //设置平时考核
            List<RegularAssessmentVo> regularAssessmentVos1 = new ArrayList<>();
            for (RegularAssessmentVo regularAssessmentVo : regularAssessmentVos) {
                if (assessAnnualReviewStatisticsVo.getUserId().equals(regularAssessmentVo.getUserId())&&assessAnnualReviewStatisticsVo.getAssessmentYear().equals(regularAssessmentVo.getYear()))
                    regularAssessmentVos1.add(regularAssessmentVo);
            }
            assessAnnualReviewStatisticsVo.setRegularAssessmentVos(regularAssessmentVos1);
            //计算分数
            if (null==assessAnnualReviewStatisticsVo.getaTotalNum()||assessAnnualReviewStatisticsVo.getaTotalNum().equals(0)){
                assessAnnualReviewStatisticsVo.setaTypeScore(0.00F);
            }else {
                assessAnnualReviewStatisticsVo.setaTypeScore((float) (((float) assessAnnualReviewStatisticsVo.getaTypeVotes() / assessAnnualReviewStatisticsVo.getaTotalNum())*0.4*100));
            }
            if(null==assessAnnualReviewStatisticsVo.getbTotalNum()||assessAnnualReviewStatisticsVo.getbTotalNum().equals(0)){
                assessAnnualReviewStatisticsVo.setbTypeScore(0.00F);
            }else {
                assessAnnualReviewStatisticsVo.setbTypeScore((float) (((float) assessAnnualReviewStatisticsVo.getbTypeVotes() / assessAnnualReviewStatisticsVo.getbTotalNum())*0.6*100));
            }
            assessAnnualReviewStatisticsVo.setTotalScore(assessAnnualReviewStatisticsVo.getaTypeScore()+assessAnnualReviewStatisticsVo.getbTypeScore());
        }
        return bizAssessAnnualReviewStatisticsVos;
    }

    @Override
    public List<BizAssessAnnualReviewStatisticsVo> selectYears(){
        return  bizAssessAnnualReviewStatisticsMapper.selectYears();
    }

    /**
     * 查找平时考核
     * @param userIds
     * @param year
     * @return
     */
    @Override
    public List<RegularAssessmentVo> regularAssessment(List<Long> userIds,String year){
        if (userIds.isEmpty())return new ArrayList<>();
       return bizAssessAnnualReviewStatisticsMapper.regularAssessment(userIds,year);
    }

    @Override
    public List<RegularAssessmentVo> regularAssessmentListById(Long userId,String year){
        return bizAssessAnnualReviewStatisticsMapper.regularAssessmentListById(userId,year);
    }
}
