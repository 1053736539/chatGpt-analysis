package com.cb.assess.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.cb.assess.domain.BizAssessAnnualReviewTypeA;
import com.cb.assess.domain.vo.BizAssessAnnualReviewTypeVo;
import com.cb.assess.domain.vo.RegularAssessmentVo;
import com.cb.assess.mapper.BizAssessAnnualReviewStatisticsMapper;
import com.cb.assess.mapper.BizAssessAnnualReviewTypeAMapper;
import com.cb.assess.service.BizAssessAnnualReviewStatisticsService;
import com.cb.assess.service.IBizAssessAnnualReviewTypeAService;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 省统计局二级巡视员、总师、处（室）负责人年度考核评议A类Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-24
 */
@Service
public class BizAssessAnnualReviewTypeAServiceImpl implements IBizAssessAnnualReviewTypeAService
{
    @Autowired
    private BizAssessAnnualReviewTypeAMapper bizAssessAnnualReviewTypeAMapper;
    @Resource
    private BizAssessAnnualReviewStatisticsService bizAssessAnnualReviewStatisticsService;
    /**
     * 查询省统计局二级巡视员、总师、处（室）负责人年度考核评议A类
     * 
     * @param id 省统计局二级巡视员、总师、处（室）负责人年度考核评议A类ID
     * @return 省统计局二级巡视员、总师、处（室）负责人年度考核评议A类
     */
    @Override
    public BizAssessAnnualReviewTypeA selectBizAssessAnnualReviewTypeAById(String id)
    {
        return bizAssessAnnualReviewTypeAMapper.selectBizAssessAnnualReviewTypeAById(id);
    }

    /**
     * 查询省统计局二级巡视员、总师、处（室）负责人年度考核评议A类列表
     * 
     * @param bizAssessAnnualReviewTypeA 省统计局二级巡视员、总师、处（室）负责人年度考核评议A类
     * @return 省统计局二级巡视员、总师、处（室）负责人年度考核评议A类
     */
    @Override
    public List<BizAssessAnnualReviewTypeVo> selectBizAssessAnnualReviewTypeAList(BizAssessAnnualReviewTypeVo bizAssessAnnualReviewTypeVo)
    {
        List<BizAssessAnnualReviewTypeVo> bizAssessAnnualReviewTypeVos = bizAssessAnnualReviewTypeAMapper.selectBizAssessAnnualReviewTypeAList(bizAssessAnnualReviewTypeVo);
        //拿平时考核
        List<Long> collect = bizAssessAnnualReviewTypeVos.stream().map(e -> e.getUserId()).collect(Collectors.toList());
        List<RegularAssessmentVo> regularAssessmentVos = bizAssessAnnualReviewStatisticsService.regularAssessment(collect,bizAssessAnnualReviewTypeVo.getAssessmentYear());
        //设置平时考核

        for (BizAssessAnnualReviewTypeVo assessAnnualReviewTypeVo : bizAssessAnnualReviewTypeVos) {
            List<RegularAssessmentVo> regularAssessmentVos1 = new ArrayList<>();
            for (RegularAssessmentVo regularAssessmentVo : regularAssessmentVos) {
                if (assessAnnualReviewTypeVo.getUserId().equals(regularAssessmentVo.getUserId())&&assessAnnualReviewTypeVo.getAssessmentYear().equals(regularAssessmentVo.getYear()))
                    regularAssessmentVos1.add(regularAssessmentVo);
            }
            assessAnnualReviewTypeVo.setRegularAssessmentVos(regularAssessmentVos1);
        }


        return bizAssessAnnualReviewTypeVos;
    }

    /**
     * 新增省统计局二级巡视员、总师、处（室）负责人年度考核评议A类
     * 
     * @param bizAssessAnnualReviewTypeA 省统计局二级巡视员、总师、处（室）负责人年度考核评议A类
     * @return 结果
     */
    @Override
    public int insertBizAssessAnnualReviewTypeA(BizAssessAnnualReviewTypeA bizAssessAnnualReviewTypeA)
    {
        bizAssessAnnualReviewTypeA.setId(UUID.randomUUID().toString());
        bizAssessAnnualReviewTypeA.setCreateBy(SecurityUtils.getUsername());
        bizAssessAnnualReviewTypeA.setCreateTime(DateUtils.getNowDate());
        bizAssessAnnualReviewTypeA.setDelFlag("0");
        bizAssessAnnualReviewTypeA.setStatus("0");
        return bizAssessAnnualReviewTypeAMapper.insertBizAssessAnnualReviewTypeA(bizAssessAnnualReviewTypeA);
    }

    /**
     * 修改省统计局二级巡视员、总师、处（室）负责人年度考核评议A类
     * 
     * @param bizAssessAnnualReviewTypeA 省统计局二级巡视员、总师、处（室）负责人年度考核评议A类
     * @return 结果
     */
    @Override
    public int updateBizAssessAnnualReviewTypeA(BizAssessAnnualReviewTypeA bizAssessAnnualReviewTypeA)
    {
        bizAssessAnnualReviewTypeA.setUpdateTime(DateUtils.getNowDate());
        bizAssessAnnualReviewTypeA.setUpdateBy(SecurityUtils.getUsername());
        return bizAssessAnnualReviewTypeAMapper.updateBizAssessAnnualReviewTypeA(bizAssessAnnualReviewTypeA);
    }

    /**
     * 批量删除省统计局二级巡视员、总师、处（室）负责人年度考核评议A类
     * 
     * @param ids 需要删除的省统计局二级巡视员、总师、处（室）负责人年度考核评议A类ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessAnnualReviewTypeAByIds(String[] ids)
    {
        return bizAssessAnnualReviewTypeAMapper.deleteBizAssessAnnualReviewTypeAByIds(ids);
    }

    /**
     * 删除省统计局二级巡视员、总师、处（室）负责人年度考核评议A类信息
     * 
     * @param id 省统计局二级巡视员、总师、处（室）负责人年度考核评议A类ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessAnnualReviewTypeAById(String id)
    {
        return bizAssessAnnualReviewTypeAMapper.deleteBizAssessAnnualReviewTypeAById(id);
    }
}
