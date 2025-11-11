package com.cb.assess.mapper;

import com.cb.assess.domain.BizAssessDeptExcellentEvaluation;
import com.cb.assess.domain.vo.BizAssessDeptExcellentEvaluationVo;

import java.util.List;

/**
 * 年度处室（单位）年度考核优秀评议Mapper接口
 * 
 * @author ruoyi
 * @date 2023-12-09
 */
public interface BizAssessDeptExcellentEvaluationMapper 
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

    /**
     * 统计自己的优秀推荐互评待办
     * @param discussantUserId
     * @return
     */
    public BizAssessDeptExcellentEvaluationVo selectMyExcellentEvaluationCount(Long discussantUserId);
    public List<BizAssessDeptExcellentEvaluationVo> selectStatisticsByDept(BizAssessDeptExcellentEvaluationVo bizAssessDeptExcellentEvaluationVo);
    public List<BizAssessDeptExcellentEvaluationVo> selectYears();

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
     * 删除年度处室（单位）年度考核优秀评议
     * 
     * @param id 年度处室（单位）年度考核优秀评议ID
     * @return 结果
     */
    public int deleteBizAssessDeptExcellentEvaluationById(String id);

    /**
     * 批量删除年度处室（单位）年度考核优秀评议
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAssessDeptExcellentEvaluationByIds(String[] ids);
}
