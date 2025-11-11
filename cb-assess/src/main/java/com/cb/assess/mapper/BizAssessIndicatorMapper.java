package com.cb.assess.mapper;

import java.util.List;

import com.cb.assess.domain.BizAssessIndicator;

/**
 * 考核指标体系Mapper接口
 *
 * @author ouyang
 * @date 2023-10-26
 */
public interface BizAssessIndicatorMapper {
    /**
     * 查询考核指标体系
     *
     * @param indId 考核指标体系ID
     * @return 考核指标体系
     */
    public BizAssessIndicator selectBizAssessIndicatorById(String indId);

    /**
     * 查询考核指标体系列表
     *
     * @param bizAssessIndicator 考核指标体系
     * @return 考核指标体系集合
     */
    public List<BizAssessIndicator> selectBizAssessIndicatorList(BizAssessIndicator bizAssessIndicator);


    public List<BizAssessIndicator> selectAllIndicator();


    /**
     * 新增考核指标体系
     *
     * @param bizAssessIndicator 考核指标体系
     * @return 结果
     */
    public int insertBizAssessIndicator(BizAssessIndicator bizAssessIndicator);

    /**
     * 修改考核指标体系
     *
     * @param bizAssessIndicator 考核指标体系
     * @return 结果
     */
    public int updateBizAssessIndicator(BizAssessIndicator bizAssessIndicator);

    /**
     * 删除考核指标体系
     *
     * @param indId 考核指标体系ID
     * @return 结果
     */
    public int deleteBizAssessIndicatorById(String indId);

    /**
     * 批量删除考核指标体系
     *
     * @param indIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAssessIndicatorByIds(String[] indIds);

    public int changeStatus(BizAssessIndicator bizAssessIndicator);


    public int logicDeleteBizAssessIndicatorById(String indId);

    public int logicDeleteBizAssessIndicatorByIds(String[] indIds);

    public List<BizAssessIndicator> selectIndicatorListByIds(List<String> list);
}
