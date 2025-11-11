package com.cb.assess.mapper;

import java.util.List;

import com.cb.assess.domain.BizAssessIndicatorPro;

/**
 * 考核方案Mapper接口
 *
 * @author ouyang
 * @date 2023-11-01
 */
public interface BizAssessIndicatorProMapper {
    /**
     * 查询考核方案
     *
     * @param proId 考核方案ID
     * @return 考核方案
     */
    public BizAssessIndicatorPro selectBizAssessIndicatorProById(String proId);

    /**
     * 查询考核方案列表
     *
     * @param bizAssessIndicatorPro 考核方案
     * @return 考核方案集合
     */
    public List<BizAssessIndicatorPro> selectBizAssessIndicatorProList(BizAssessIndicatorPro bizAssessIndicatorPro);


    public List<BizAssessIndicatorPro> selectAllProList();



    public List<BizAssessIndicatorPro> querySelectorList(BizAssessIndicatorPro pro);
    /**
     * 新增考核方案
     *
     * @param bizAssessIndicatorPro 考核方案
     * @return 结果
     */
    public int insertBizAssessIndicatorPro(BizAssessIndicatorPro bizAssessIndicatorPro);

    /**
     * 修改考核方案
     *
     * @param bizAssessIndicatorPro 考核方案
     * @return 结果
     */
    public int updateBizAssessIndicatorPro(BizAssessIndicatorPro bizAssessIndicatorPro);

    /**
     * 删除考核方案
     *
     * @param proId 考核方案ID
     * @return 结果
     */
    public int deleteBizAssessIndicatorProById(String proId);

    /**
     * 批量删除考核方案
     *
     * @param proIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAssessIndicatorProByIds(String[] proIds);


    public int logicDeleteBizAssessIndicatorProByIds(String[] proIds);

    public int logicDeleteBizAssessIndicatorProById(String proId);

    public int changeStatus(BizAssessIndicatorPro pro);
}
