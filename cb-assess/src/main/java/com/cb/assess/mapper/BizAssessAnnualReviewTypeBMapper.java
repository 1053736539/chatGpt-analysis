package com.cb.assess.mapper;

import com.cb.assess.domain.BizAssessAnnualReviewTypeB;
import com.cb.assess.domain.vo.BizAssessAnnualReviewTypeVo;

import java.util.List;


/**
 * 省统计局二级巡视员、总师、处（室）负责人年度考核评议B类Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-24
 */
public interface BizAssessAnnualReviewTypeBMapper 
{
    /**
     * 查询省统计局二级巡视员、总师、处（室）负责人年度考核评议B类
     * 
     * @param id 省统计局二级巡视员、总师、处（室）负责人年度考核评议B类ID
     * @return 省统计局二级巡视员、总师、处（室）负责人年度考核评议B类
     */
    public BizAssessAnnualReviewTypeB selectBizAssessAnnualReviewTypeBById(String id);

    /**
     * 查询省统计局二级巡视员、总师、处（室）负责人年度考核评议B类列表
     * 
     * @param bizAssessAnnualReviewTypeB 省统计局二级巡视员、总师、处（室）负责人年度考核评议B类
     * @return 省统计局二级巡视员、总师、处（室）负责人年度考核评议B类集合
     */
    public List<BizAssessAnnualReviewTypeVo> selectBizAssessAnnualReviewTypeBList(BizAssessAnnualReviewTypeVo bizAssessAnnualReviewTypeVo);
    /**
     * 查询自己的A类表待填统计
     * @param discussantUserId
     * @return
     */
    public BizAssessAnnualReviewTypeVo selectAnnualReviewTypeBCount(Long discussantUserId);
    /**
     * 新增省统计局二级巡视员、总师、处（室）负责人年度考核评议B类
     * 
     * @param bizAssessAnnualReviewTypeB 省统计局二级巡视员、总师、处（室）负责人年度考核评议B类
     * @return 结果
     */
    public int insertBizAssessAnnualReviewTypeB(BizAssessAnnualReviewTypeB bizAssessAnnualReviewTypeB);

    /**
     * 修改省统计局二级巡视员、总师、处（室）负责人年度考核评议B类
     * 
     * @param bizAssessAnnualReviewTypeB 省统计局二级巡视员、总师、处（室）负责人年度考核评议B类
     * @return 结果
     */
    public int updateBizAssessAnnualReviewTypeB(BizAssessAnnualReviewTypeB bizAssessAnnualReviewTypeB);

    /**
     * 删除省统计局二级巡视员、总师、处（室）负责人年度考核评议B类
     * 
     * @param id 省统计局二级巡视员、总师、处（室）负责人年度考核评议B类ID
     * @return 结果
     */
    public int deleteBizAssessAnnualReviewTypeBById(String id);

    /**
     * 批量删除省统计局二级巡视员、总师、处（室）负责人年度考核评议B类
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAssessAnnualReviewTypeBByIds(String[] ids);
}
