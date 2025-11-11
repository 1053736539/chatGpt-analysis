package com.cb.assess.service;

import com.cb.assess.domain.BizAssessAnnualReviewTypeA;
import com.cb.assess.domain.vo.BizAssessAnnualReviewTypeVo;

import java.util.List;

/**
 * 省统计局二级巡视员、总师、处（室）负责人年度考核评议A类Service接口
 * 
 * @author ruoyi
 * @date 2023-11-24
 */
public interface IBizAssessAnnualReviewTypeAService 
{
    /**
     * 查询省统计局二级巡视员、总师、处（室）负责人年度考核评议A类
     * 
     * @param id 省统计局二级巡视员、总师、处（室）负责人年度考核评议A类ID
     * @return 省统计局二级巡视员、总师、处（室）负责人年度考核评议A类
     */
    public BizAssessAnnualReviewTypeA selectBizAssessAnnualReviewTypeAById(String id);

    /**
     * 查询省统计局二级巡视员、总师、处（室）负责人年度考核评议A类列表
     * 
     * @param bizAssessAnnualReviewTypeA 省统计局二级巡视员、总师、处（室）负责人年度考核评议A类
     * @return 省统计局二级巡视员、总师、处（室）负责人年度考核评议A类集合
     */
    public List<BizAssessAnnualReviewTypeVo> selectBizAssessAnnualReviewTypeAList(BizAssessAnnualReviewTypeVo bizAssessAnnualReviewTypeVo);

    /**
     * 新增省统计局二级巡视员、总师、处（室）负责人年度考核评议A类
     * 
     * @param bizAssessAnnualReviewTypeA 省统计局二级巡视员、总师、处（室）负责人年度考核评议A类
     * @return 结果
     */
    public int insertBizAssessAnnualReviewTypeA(BizAssessAnnualReviewTypeA bizAssessAnnualReviewTypeA);

    /**
     * 修改省统计局二级巡视员、总师、处（室）负责人年度考核评议A类
     * 
     * @param bizAssessAnnualReviewTypeA 省统计局二级巡视员、总师、处（室）负责人年度考核评议A类
     * @return 结果
     */
    public int updateBizAssessAnnualReviewTypeA(BizAssessAnnualReviewTypeA bizAssessAnnualReviewTypeA);

    /**
     * 批量删除省统计局二级巡视员、总师、处（室）负责人年度考核评议A类
     * 
     * @param ids 需要删除的省统计局二级巡视员、总师、处（室）负责人年度考核评议A类ID
     * @return 结果
     */
    public int deleteBizAssessAnnualReviewTypeAByIds(String[] ids);

    /**
     * 删除省统计局二级巡视员、总师、处（室）负责人年度考核评议A类信息
     * 
     * @param id 省统计局二级巡视员、总师、处（室）负责人年度考核评议A类ID
     * @return 结果
     */
    public int deleteBizAssessAnnualReviewTypeAById(String id);
}
