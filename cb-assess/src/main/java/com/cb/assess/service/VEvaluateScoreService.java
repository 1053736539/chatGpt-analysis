package com.cb.assess.service;

import com.cb.assess.domain.VEvaluateScore;
import com.cb.assess.domain.VEvaluationGrade;
import com.cb.assess.domain.vo.BizAssessTaskVo;
import com.cb.assess.domain.vo.EvaluationGradeVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface VEvaluateScoreService {


    /**
     * 任务列表接口
     *
     * @param vo
     * @return
     */
    @Deprecated
    public List<BizAssessTaskVo> selectTaskList(BizAssessTaskVo vo);

    @Deprecated
    public List<VEvaluateScore> selectEvaluateScoreList(String taskId);

    /**
     * 评分表接口
     *
     * @param vo
     * @return
     */
    public List<EvaluationGradeVo> selectPreviewEvaluateScore(EvaluationGradeVo vo);


    /**
     * 得分汇总接口
     *
     * @param taskId
     * @return
     */
    @Deprecated
    public List<Map<String, Object>> selectAggregateEvaluate(String taskId);

    /**
     * 综合得分接口
     *
     * @param taskId
     * @return
     */
    @Deprecated
    public List<VEvaluateScore.Comprehensive> selectComprehensiveEvaluate(String taskId);


    public List<VEvaluationGrade> selectEscalationList(VEvaluationGrade evaluationGrade);


    public List<BizAssessTaskVo.EvaluateLevelTempResp> selectEvaluateLevelTempList(BizAssessTaskVo.EvaluateLevelTempReq req);

    @Deprecated
    public List<Map<String, Object>> selectTempEvaluateScoreList(BizAssessTaskVo.EvaluateLevelTempReq req);

    @Deprecated
    public List<VEvaluateScore.Comprehensive> listTempCompositeEvaluate(BizAssessTaskVo.EvaluateLevelTempReq req);

    public void exportComposite(BizAssessTaskVo.EvaluateLevelTempReq req, HttpServletResponse response);
}
