package com.cb.assess.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.cb.assess.domain.BizAssessAnnualReviewTypeB;
import com.cb.assess.domain.vo.BizAssessAnnualReviewTypeVo;
import com.cb.assess.domain.vo.RegularAssessmentVo;
import com.cb.assess.mapper.BizAssessAnnualReviewStatisticsMapper;
import com.cb.assess.mapper.BizAssessAnnualReviewTypeBMapper;
import com.cb.assess.service.BizAssessAnnualReviewStatisticsService;
import com.cb.assess.service.IBizAssessAnnualReviewTypeBService;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 省统计局二级巡视员、总师、处（室）负责人年度考核评议B类Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-24
 */
@Service
public class BizAssessAnnualReviewTypeBServiceImpl implements IBizAssessAnnualReviewTypeBService
{
    @Autowired
    private BizAssessAnnualReviewTypeBMapper bizAssessAnnualReviewTypeBMapper;
    @Resource
    private BizAssessAnnualReviewStatisticsService bizAssessAnnualReviewStatisticsService;
    /**
     * 查询省统计局二级巡视员、总师、处（室）负责人年度考核评议B类
     * 
     * @param id 省统计局二级巡视员、总师、处（室）负责人年度考核评议B类ID
     * @return 省统计局二级巡视员、总师、处（室）负责人年度考核评议B类
     */
    @Override
    public BizAssessAnnualReviewTypeB selectBizAssessAnnualReviewTypeBById(String id)
    {
        return bizAssessAnnualReviewTypeBMapper.selectBizAssessAnnualReviewTypeBById(id);
    }

    /**
     * 查询省统计局二级巡视员、总师、处（室）负责人年度考核评议B类列表
     * 
     * @param bizAssessAnnualReviewTypeB 省统计局二级巡视员、总师、处（室）负责人年度考核评议B类
     * @return 省统计局二级巡视员、总师、处（室）负责人年度考核评议B类
     */
    @Override
    public List<BizAssessAnnualReviewTypeVo> selectBizAssessAnnualReviewTypeBList(BizAssessAnnualReviewTypeVo bizAssessAnnualReviewTypeVo)
    {
        List<BizAssessAnnualReviewTypeVo> bizAssessAnnualReviewTypeVos = bizAssessAnnualReviewTypeBMapper.selectBizAssessAnnualReviewTypeBList(bizAssessAnnualReviewTypeVo);
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
     * 新增省统计局二级巡视员、总师、处（室）负责人年度考核评议B类
     * 
     * @param bizAssessAnnualReviewTypeB 省统计局二级巡视员、总师、处（室）负责人年度考核评议B类
     * @return 结果
     */
    @Override
    public int insertBizAssessAnnualReviewTypeB(BizAssessAnnualReviewTypeB bizAssessAnnualReviewTypeB)
    {
        bizAssessAnnualReviewTypeB.setId(UUID.randomUUID().toString());
        bizAssessAnnualReviewTypeB.setCreateBy(SecurityUtils.getUsername());
        bizAssessAnnualReviewTypeB.setCreateTime(DateUtils.getNowDate());
        bizAssessAnnualReviewTypeB.setDelFlag("0");
        bizAssessAnnualReviewTypeB.setStatus("0");
        return bizAssessAnnualReviewTypeBMapper.insertBizAssessAnnualReviewTypeB(bizAssessAnnualReviewTypeB);
    }

    /**
     * 修改省统计局二级巡视员、总师、处（室）负责人年度考核评议B类
     * 
     * @param bizAssessAnnualReviewTypeB 省统计局二级巡视员、总师、处（室）负责人年度考核评议B类
     * @return 结果
     */
    @Override
    public int updateBizAssessAnnualReviewTypeB(BizAssessAnnualReviewTypeB bizAssessAnnualReviewTypeB)
    {
        bizAssessAnnualReviewTypeB.setUpdateTime(DateUtils.getNowDate());
        return bizAssessAnnualReviewTypeBMapper.updateBizAssessAnnualReviewTypeB(bizAssessAnnualReviewTypeB);
    }

    /**
     * 批量删除省统计局二级巡视员、总师、处（室）负责人年度考核评议B类
     * 
     * @param ids 需要删除的省统计局二级巡视员、总师、处（室）负责人年度考核评议B类ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessAnnualReviewTypeBByIds(String[] ids)
    {
        return bizAssessAnnualReviewTypeBMapper.deleteBizAssessAnnualReviewTypeBByIds(ids);
    }

    /**
     * 删除省统计局二级巡视员、总师、处（室）负责人年度考核评议B类信息
     * 
     * @param id 省统计局二级巡视员、总师、处（室）负责人年度考核评议B类ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessAnnualReviewTypeBById(String id)
    {
        return bizAssessAnnualReviewTypeBMapper.deleteBizAssessAnnualReviewTypeBById(id);
    }
}
