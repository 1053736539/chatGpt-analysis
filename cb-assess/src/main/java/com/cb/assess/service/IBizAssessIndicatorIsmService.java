package com.cb.assess.service;


import com.cb.assess.domain.BizAssessIndicatorIsm;
import com.cb.assess.domain.vo.IndicatorIsmConfigVo;

import java.util.List;
import java.util.Map;

/**
 * 指标体系Service接口
 *
 * @author ouyang
 * @date 2023-10-27
 */
public interface IBizAssessIndicatorIsmService {
    /**
     * 查询指标体系
     *
     * @param ismId 指标体系ID
     * @return 指标体系
     */
    public BizAssessIndicatorIsm selectBizAssessIndicatorIsmById(String ismId);

    /**
     * 查询指标体系列表
     *
     * @param bizAssessIndicatorIsm 指标体系
     * @return 指标体系集合
     */
    public List<BizAssessIndicatorIsm> selectBizAssessIndicatorIsmList(BizAssessIndicatorIsm bizAssessIndicatorIsm);

    public List<BizAssessIndicatorIsm> selectAllIndicatorIsmList(BizAssessIndicatorIsm bizAssessIndicatorIsm);

    /**
     * 新增指标体系
     *
     * @param bizAssessIndicatorIsm 指标体系
     * @return 结果
     */
    public int insertBizAssessIndicatorIsm(BizAssessIndicatorIsm bizAssessIndicatorIsm);

    /**
     * 修改指标体系
     *
     * @param bizAssessIndicatorIsm 指标体系
     * @return 结果
     */
    public int updateBizAssessIndicatorIsm(BizAssessIndicatorIsm bizAssessIndicatorIsm);

    /**
     * 批量删除指标体系
     *
     * @param ismIds 需要删除的指标体系ID
     * @return 结果
     */
    public int deleteBizAssessIndicatorIsmByIds(String[] ismIds);

    /**
     * 删除指标体系信息
     *
     * @param ismId 指标体系ID
     * @return 结果
     */
    public int deleteBizAssessIndicatorIsmById(String ismId);

    public int logicDeleteBizAssessIndicatorIsmByIds(String[] ismIds);


    public int logicDeleteBizAssessIndicatorIsmById(String ismId);

    public Map<String,Object> selectIndicatorIsmConfigVoMapByIsmId(String ismId);

    public List<IndicatorIsmConfigVo> selectIndicatorIsmConfigVoListByIsmId(String ismId);

    public int syncProIndicatorSnapshot(String ismId);
}
