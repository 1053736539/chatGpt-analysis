package com.cb.assess.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.cb.assess.domain.BizAssessDeptExcellentEvaluation;
import com.cb.assess.domain.vo.BizAssessDeptExcellentEvaluationVo;
import com.cb.assess.domain.vo.RegularAssessmentVo;
import com.cb.assess.mapper.BizAssessDeptExcellentEvaluationMapper;
import com.cb.assess.service.BizAssessAnnualReviewStatisticsService;
import com.cb.assess.service.IBizAssessDeptExcellentEvaluationService;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 年度处室（单位）年度考核优秀评议Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-12-09
 */
@Service
public class BizAssessDeptExcellentEvaluationServiceImpl implements IBizAssessDeptExcellentEvaluationService
{
    @Autowired
    private BizAssessDeptExcellentEvaluationMapper bizAssessDeptExcellentEvaluationMapper;
    @Resource
    private BizAssessAnnualReviewStatisticsService bizAssessAnnualReviewStatisticsService;
    /**
     * 查询年度处室（单位）年度考核优秀评议
     * 
     * @param id 年度处室（单位）年度考核优秀评议ID
     * @return 年度处室（单位）年度考核优秀评议
     */
    @Override
    public BizAssessDeptExcellentEvaluation selectBizAssessDeptExcellentEvaluationById(String id)
    {
        return bizAssessDeptExcellentEvaluationMapper.selectBizAssessDeptExcellentEvaluationById(id);
    }

    /**
     * 查询年度处室（单位）年度考核优秀评议列表
     * 
     * @param bizAssessDeptExcellentEvaluation 年度处室（单位）年度考核优秀评议
     * @return 年度处室（单位）年度考核优秀评议
     */
    @Override
    public List<BizAssessDeptExcellentEvaluationVo> selectBizAssessDeptExcellentEvaluationList(BizAssessDeptExcellentEvaluationVo bizAssessDeptExcellentEvaluation)
    {
        List<BizAssessDeptExcellentEvaluationVo> bizAssessDeptExcellentEvaluationVos = bizAssessDeptExcellentEvaluationMapper.selectBizAssessDeptExcellentEvaluationList(bizAssessDeptExcellentEvaluation);
        //拿平时考核
        setRegular(bizAssessDeptExcellentEvaluation, bizAssessDeptExcellentEvaluationVos);
        return bizAssessDeptExcellentEvaluationVos;
    }
    @Override
    public List<BizAssessDeptExcellentEvaluationVo> selectStatisticsByDept(BizAssessDeptExcellentEvaluationVo bizAssessDeptExcellentEvaluation)
    {
        List<BizAssessDeptExcellentEvaluationVo> bizAssessDeptExcellentEvaluationVos = bizAssessDeptExcellentEvaluationMapper.selectStatisticsByDept(bizAssessDeptExcellentEvaluation);
        //拿平时考核
        setRegular(bizAssessDeptExcellentEvaluation, bizAssessDeptExcellentEvaluationVos);
        return bizAssessDeptExcellentEvaluationVos;
    }

    //设置平时考核
    private void setRegular(BizAssessDeptExcellentEvaluationVo bizAssessDeptExcellentEvaluation, List<BizAssessDeptExcellentEvaluationVo> bizAssessDeptExcellentEvaluationVos) {
        //拿平时考核
        List<Long> collect = bizAssessDeptExcellentEvaluationVos.stream().map(e -> e.getUserId()).collect(Collectors.toList());
        List<RegularAssessmentVo> regularAssessmentVos = bizAssessAnnualReviewStatisticsService.regularAssessment(collect, bizAssessDeptExcellentEvaluation.getAssessmentYear());
        //设置平时考核

        for (BizAssessDeptExcellentEvaluationVo assessDeptExcellentEvaluationVo : bizAssessDeptExcellentEvaluationVos) {
            List<RegularAssessmentVo> regularAssessmentVos1 = new ArrayList<>();
            for (RegularAssessmentVo regularAssessmentVo : regularAssessmentVos) {
                if (assessDeptExcellentEvaluationVo.getUserId().equals(regularAssessmentVo.getUserId())&&assessDeptExcellentEvaluationVo.getAssessmentYear().equals(regularAssessmentVo.getYear()))
                    regularAssessmentVos1.add(regularAssessmentVo);
            }
            assessDeptExcellentEvaluationVo.setRegularAssessmentVos(regularAssessmentVos1);
        }
    }

    @Override
    public List<BizAssessDeptExcellentEvaluationVo> selectYears()
    {
        return bizAssessDeptExcellentEvaluationMapper.selectYears();
    }

    /**
     * 新增年度处室（单位）年度考核优秀评议
     * 
     * @param bizAssessDeptExcellentEvaluation 年度处室（单位）年度考核优秀评议
     * @return 结果
     */
    @Override
    public int insertBizAssessDeptExcellentEvaluation(BizAssessDeptExcellentEvaluation bizAssessDeptExcellentEvaluation)
    {
        bizAssessDeptExcellentEvaluation.setCreateTime(DateUtils.getNowDate());
        bizAssessDeptExcellentEvaluation.setStatus("0");
        bizAssessDeptExcellentEvaluation.setDelFlag("0");
        bizAssessDeptExcellentEvaluation.setEscalation("0");
        bizAssessDeptExcellentEvaluation.setId(UUID.randomUUID().toString());
        bizAssessDeptExcellentEvaluation.setCreateBy(SecurityUtils.getLoginUser().getUsername());
        return bizAssessDeptExcellentEvaluationMapper.insertBizAssessDeptExcellentEvaluation(bizAssessDeptExcellentEvaluation);
    }

    /**
     * 修改年度处室（单位）年度考核优秀评议
     * 
     * @param bizAssessDeptExcellentEvaluation 年度处室（单位）年度考核优秀评议
     * @return 结果
     */
    @Override
    public int updateBizAssessDeptExcellentEvaluation(BizAssessDeptExcellentEvaluation bizAssessDeptExcellentEvaluation)
    {
        bizAssessDeptExcellentEvaluation.setUpdateTime(DateUtils.getNowDate());
        bizAssessDeptExcellentEvaluation.setUpdateBy(SecurityUtils.getLoginUser().getUsername());
        return bizAssessDeptExcellentEvaluationMapper.updateBizAssessDeptExcellentEvaluation(bizAssessDeptExcellentEvaluation);
    }

    /**
     * 批量删除年度处室（单位）年度考核优秀评议
     * 
     * @param ids 需要删除的年度处室（单位）年度考核优秀评议ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessDeptExcellentEvaluationByIds(String[] ids)
    {
        return bizAssessDeptExcellentEvaluationMapper.deleteBizAssessDeptExcellentEvaluationByIds(ids);
    }

    /**
     * 删除年度处室（单位）年度考核优秀评议信息
     * 
     * @param id 年度处室（单位）年度考核优秀评议ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessDeptExcellentEvaluationById(String id)
    {
        return bizAssessDeptExcellentEvaluationMapper.deleteBizAssessDeptExcellentEvaluationById(id);
    }
}
