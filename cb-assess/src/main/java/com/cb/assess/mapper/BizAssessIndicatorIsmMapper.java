package com.cb.assess.mapper;

import com.cb.assess.domain.BizAssessIndicatorIsm;
import com.cb.assess.domain.BizAssessIndicatorPro;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 指标体系Mapper接口
 *
 * @author ouyang
 * @date 2023-10-27
 */
public interface BizAssessIndicatorIsmMapper {
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
     * 删除指标体系
     *
     * @param ismId 指标体系ID
     * @return 结果
     */
    public int deleteBizAssessIndicatorIsmById(String ismId);

    /**
     * 批量删除指标体系
     *
     * @param ismIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAssessIndicatorIsmByIds(String[] ismIds);

    /**
     * 删除指标体系
     *
     * @param ismId 指标体系ID
     * @return 结果
     */
    public int logicDeleteBizAssessIndicatorIsmById(String ismId);

    /**
     * 批量删除指标体系
     *
     * @param ismIds 需要删除的数据ID
     * @return 结果
     */
    public int logicDeleteBizAssessIndicatorIsmByIds(String[] ismIds);

    /**
     * 查询方案（已审核通过的）使用关联该体系数量，
     * @param ismId
     * @return
     */
    public int selectProUseCount(String ismId);

    public List<BizAssessIndicatorPro> selectProUseByIds(@Param("ids") List<String> ismIds);


}
