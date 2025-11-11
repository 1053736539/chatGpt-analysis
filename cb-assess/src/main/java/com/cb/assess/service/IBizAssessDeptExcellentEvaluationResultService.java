package com.cb.assess.service;

import com.cb.assess.domain.BizAssessDeptExcellentEvaluationResult;
import com.cb.assess.domain.vo.BizAssessDeptExcellentEvaluationResultVo;

import java.util.List;

/**
 * 年度处室（单位）年度考核优秀评议(最终部门上报结果)Service接口
 * 
 * @author ruoyi
 * @date 2023-12-11
 */
public interface IBizAssessDeptExcellentEvaluationResultService 
{
    /**
     * 查询年度处室（单位）年度考核优秀评议(最终部门上报结果)
     * 
     * @param id 年度处室（单位）年度考核优秀评议(最终部门上报结果)ID
     * @return 年度处室（单位）年度考核优秀评议(最终部门上报结果)
     */
    public BizAssessDeptExcellentEvaluationResult selectBizAssessDeptExcellentEvaluationResultById(String id);

    /**
     * 查询年度处室（单位）年度考核优秀评议(最终部门上报结果)列表
     * 
     * @param bizAssessDeptExcellentEvaluationResult 年度处室（单位）年度考核优秀评议(最终部门上报结果)
     * @return 年度处室（单位）年度考核优秀评议(最终部门上报结果)集合
     */
    public List<BizAssessDeptExcellentEvaluationResultVo> selectBizAssessDeptExcellentEvaluationResultList(BizAssessDeptExcellentEvaluationResultVo bizAssessDeptExcellentEvaluationResultVo);

    void push2Publicity(String year, String type);

    public List<BizAssessDeptExcellentEvaluationResultVo> selectYears();

    /**
     * 新增年度处室（单位）年度考核优秀评议(最终部门上报结果)
     * 
     * @param bizAssessDeptExcellentEvaluationResult 年度处室（单位）年度考核优秀评议(最终部门上报结果)
     * @return 结果
     */
    public int insertBizAssessDeptExcellentEvaluationResult(BizAssessDeptExcellentEvaluationResult bizAssessDeptExcellentEvaluationResult);

    /**
     * 修改年度处室（单位）年度考核优秀评议(最终部门上报结果)
     * 
     * @param bizAssessDeptExcellentEvaluationResult 年度处室（单位）年度考核优秀评议(最终部门上报结果)
     * @return 结果
     */
    public int updateBizAssessDeptExcellentEvaluationResult(BizAssessDeptExcellentEvaluationResult bizAssessDeptExcellentEvaluationResult);

    /**
     * 批量删除年度处室（单位）年度考核优秀评议(最终部门上报结果)
     * 
     * @param ids 需要删除的年度处室（单位）年度考核优秀评议(最终部门上报结果)ID
     * @return 结果
     */
    public int deleteBizAssessDeptExcellentEvaluationResultByIds(String[] ids);

    /**
     * 删除年度处室（单位）年度考核优秀评议(最终部门上报结果)信息
     * 
     * @param id 年度处室（单位）年度考核优秀评议(最终部门上报结果)ID
     * @return 结果
     */
    public int deleteBizAssessDeptExcellentEvaluationResultById(String id);

}
