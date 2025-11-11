package com.cb.assess.service;

import com.cb.assess.domain.BizAssessDeptExcellentEvaluation;
import com.cb.assess.domain.vo.BizAssessDeptExcellentEvaluationVo;

import java.util.List;

/**
 * 年度处室（单位）年度考核优秀评议Service接口
 * 
 * @author ruoyi
 * @date 2023-12-09
 */
public interface IBizAssessDeptExcellentEvaluationService 
{
    /**
     * 查询年度处室（单位）年度考核优秀评议
     * 
     * @param id 年度处室（单位）年度考核优秀评议ID
     * @return 年度处室（单位）年度考核优秀评议
     */
    public BizAssessDeptExcellentEvaluation selectBizAssessDeptExcellentEvaluationById(String id);

    /**
     * 查询年度处室（单位）年度考核优秀评议列表
     * 
     * @param bizAssessDeptExcellentEvaluation 年度处室（单位）年度考核优秀评议
     * @return 年度处室（单位）年度考核优秀评议集合
     */
    public List<BizAssessDeptExcellentEvaluationVo> selectBizAssessDeptExcellentEvaluationList(BizAssessDeptExcellentEvaluationVo bizAssessDeptExcellentEvaluationVo);

    List<BizAssessDeptExcellentEvaluationVo> selectStatisticsByDept(BizAssessDeptExcellentEvaluationVo bizAssessDeptExcellentEvaluation);

    List<BizAssessDeptExcellentEvaluationVo> selectYears();

    /**
     * 新增年度处室（单位）年度考核优秀评议
     * 
     * @param bizAssessDeptExcellentEvaluation 年度处室（单位）年度考核优秀评议
     * @return 结果
     */
    public int insertBizAssessDeptExcellentEvaluation(BizAssessDeptExcellentEvaluation bizAssessDeptExcellentEvaluation);

    /**
     * 修改年度处室（单位）年度考核优秀评议
     * 
     * @param bizAssessDeptExcellentEvaluation 年度处室（单位）年度考核优秀评议
     * @return 结果
     */
    public int updateBizAssessDeptExcellentEvaluation(BizAssessDeptExcellentEvaluation bizAssessDeptExcellentEvaluation);

    /**
     * 批量删除年度处室（单位）年度考核优秀评议
     * 
     * @param ids 需要删除的年度处室（单位）年度考核优秀评议ID
     * @return 结果
     */
    public int deleteBizAssessDeptExcellentEvaluationByIds(String[] ids);

    /**
     * 删除年度处室（单位）年度考核优秀评议信息
     * 
     * @param id 年度处室（单位）年度考核优秀评议ID
     * @return 结果
     */
    public int deleteBizAssessDeptExcellentEvaluationById(String id);
}
