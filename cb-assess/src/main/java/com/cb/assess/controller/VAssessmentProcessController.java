package com.cb.assess.controller;

import com.cb.assess.domain.*;
import com.cb.assess.service.VAssessmentProcessService;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/assess/process")
public class VAssessmentProcessController extends BaseController {
    @Autowired
    private VAssessmentProcessService processService;

    @GetMapping("/list")
    public TableDataInfo list(VAssessmentProcess.ProcessVo processVo) {
        startPage();
        List<VAssessmentProcess> list = processService.selectProcessList(processVo);
        return getDataTable(list);
    }

    @GetMapping("/confirmList")
    public TableDataInfo confirmList(VOrdinaryAssessTask vOrdinaryAssessTask) {
        startPage();
        List<VOrdinaryAssessTask> list = processService.selectNeedConfirmList(vOrdinaryAssessTask);
        return getDataTable(list);
    }

    @GetMapping("/evaluateToDoList")
    public TableDataInfo evaluateToDoList(BizIndividualAssessmentTask task) {
        startPage();
        List<BizIndividualAssessmentTask> list = processService.evaluateToDoList(task);
        return getDataTable(list);
    }

    /**
     * 获取用户评分
     * @param req
     * @return
     */
    @GetMapping("/evaluationScoreList")
    public AjaxResult evaluationScoreList(VAssessmentProcess.EvaluationScoreReq req){
        List<VAssessmentProcess.EvaluationScoreResp> list = processService.selectEvaluationScore(req);
        return AjaxResult.success(list);
    }
    /**
     * 获取总结审定列表
     * @param processVo
     * @return
     */
    @GetMapping("/needApprovalSummaryList")
    public TableDataInfo selectNeedApprovalSummaryList(VAssessmentProcess.ProcessVo processVo){
        startPage();
        List<BizAssessRegularInfo> list = processService.selectNeedApprovalSummaryList(processVo);
        return getDataTable(list);
    }

    @GetMapping("/needAuditSummaryList")
    public TableDataInfo selectNeedAuditSummaryList(VAssessmentProcess.ProcessVo processVo){
        startPage();
        List<BizAssessRegularInfo> list = processService.selectNeedAuditSummaryList(processVo);
        return getDataTable(list);
    }

    /**
     * 党组会建议等次列表查询
     * @param processVo
     * @return
     */
    @GetMapping("/dzhGradeList")
    public TableDataInfo dzhGradeList(VAssessmentProcess.ProcessVo processVo) {
        startPage();
        List<VEvaluationGrade.Grade> grades = processService.selectDzhGradeList(processVo);
        return getDataTable(grades);
    }

}
