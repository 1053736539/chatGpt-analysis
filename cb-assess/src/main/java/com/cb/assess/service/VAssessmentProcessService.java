package com.cb.assess.service;

import com.cb.assess.domain.*;

import java.util.List;


public interface VAssessmentProcessService {


    /**
     * 获取待办抽象列表
     * @param processVo
     * @return
     */
    List<VAssessmentProcess> selectProcessList(VAssessmentProcess.ProcessVo processVo);

    /**
     * 获取带考核用户任务列表
     * @param vOrdinaryAssessTask
     * @return
     */
    public List<VOrdinaryAssessTask> selectNeedConfirmList(VOrdinaryAssessTask vOrdinaryAssessTask);

    /**
     * 获取考核待办评分任务列表
     * @param task
     * @return
     */
    public List<BizIndividualAssessmentTask> evaluateToDoList(BizIndividualAssessmentTask task);

    /**
     * 总结待审定列表
     * @param
     * @return
     */
    public  List<BizAssessRegularInfo> selectNeedApprovalSummaryList(VAssessmentProcess.ProcessVo processVo);

    /**
     * 获取待审核总结列表
     * @param processVo
     * @return
     */
    public  List<BizAssessRegularInfo> selectNeedAuditSummaryList(VAssessmentProcess.ProcessVo processVo);


    public List<VAssessmentProcess.EvaluationScoreResp> selectEvaluationScore(VAssessmentProcess.EvaluationScoreReq req);

    /**
     * 党组会建议等次列表查询
     * @param processVo
     * @return
     */
    public List<VEvaluationGrade.Grade> selectDzhGradeList(VAssessmentProcess.ProcessVo processVo);
}
