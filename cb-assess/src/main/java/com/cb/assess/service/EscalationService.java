package com.cb.assess.service;

import com.cb.assess.domain.BizAssessEvaluationGrade;
import com.cb.assess.domain.BizAssessEvaluationGradeSaveMark;
import com.cb.assess.domain.vo.EscalationVo;

import java.util.List;

public interface EscalationService {

    public List<EscalationVo> selectEscalationList(EscalationVo vo);
    public List<EscalationVo> selectExamineEscalationList(EscalationVo vo);

    public List<EscalationVo.ReportedEvaluate> selectCensusList(EscalationVo.Voter voter);
    public List<EscalationVo.ReportedEvaluate> selectVoterListByKeys(String special,List<String> taskIds,List<Long> userIds);

    public Boolean checkReported(EscalationVo.EvaluateCensus evaluateCensus);
    public int saveEvaluateCensus(EscalationVo.EvaluateCensus evaluateCensus);

    public List<EscalationVo.ReportedEvaluate> selectReportedList(EscalationVo.ReportedEvaluate reported);

    public List<BizAssessEvaluationGradeSaveMark> selectEvaluationGradeSaveMark(EscalationVo.EvaluateCensus evaluateCensus);

    public List<BizAssessEvaluationGrade> selectBizAssessEvaluationGrade(String escalationId);

    public int evaluationGradeAudit(BizAssessEvaluationGradeSaveMark mark);
}
