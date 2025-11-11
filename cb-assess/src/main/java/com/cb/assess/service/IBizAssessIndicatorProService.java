package com.cb.assess.service;

import com.cb.assess.domain.BizAssessIndicatorPro;

import java.util.List;

/**
 * 考核方案Service接口
 *
 * @author ouyang
 * @date 2023-11-01
 */
public interface IBizAssessIndicatorProService {
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
     * 批量删除考核方案
     *
     * @param proIds 需要删除的考核方案ID
     * @return 结果
     */
    public int deleteBizAssessIndicatorProByIds(String[] proIds);

    /**
     * 删除考核方案信息
     *
     * @param proId 考核方案ID
     * @return 结果
     */
    public int deleteBizAssessIndicatorProById(String proId);



    /**
     * 批量删除考核方案
     *
     * @param proIds 需要删除的考核方案ID
     * @return 结果
     */
    public int logicDeleteBizAssessIndicatorProByIds(String[] proIds);

    /**
     * 删除考核方案信息
     *
     * @param proId 考核方案ID
     * @return 结果
     */
    public int logicDeleteBizAssessIndicatorProById(String proId);


    public int changeStatus(BizAssessIndicatorPro pro);

    /**
     * 获取选择组件的数据
     * @return
     */

    public List<BizAssessIndicatorPro> querySelectorList(BizAssessIndicatorPro pro);

    /**
     * 审核并保存
     *
     * @param bizAssessIndicatorPro 考核方案
     * @return 结果
     */
    public int auditAndSave(BizAssessIndicatorPro bizAssessIndicatorPro);
}
