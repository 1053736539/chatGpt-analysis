package com.cb.assess.controller;

import com.cb.assess.domain.VEvaluateScore;
import com.cb.assess.domain.VEvaluationGrade;
import com.cb.assess.domain.vo.BizAssessTaskVo;
import com.cb.assess.domain.vo.EscalationVo;
import com.cb.assess.domain.vo.EvaluationGradeVo;
import com.cb.assess.service.VEvaluateScoreService;
import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/assess/VEvaluate")
public class VEvaluateScoreController extends BaseController {

    @Autowired
    private VEvaluateScoreService evaluateScoreService;


    @GetMapping("/taskList")
    public TableDataInfo taskList(BizAssessTaskVo vo){
        startPage();
        List<BizAssessTaskVo> list = evaluateScoreService.selectTaskList(vo);
        return getDataTable(list);
    }
    @GetMapping("/listEvaluateScore/{taskId}")
    public AjaxResult listEvaluateScore(@PathVariable("taskId") String taskId) {
        List<Map<String, Object>> list = evaluateScoreService.selectAggregateEvaluate(taskId);
        return AjaxResult.success(list);
    }

    @GetMapping("/previewEvaluateScore")
    public AjaxResult previewEvaluateScore(EvaluationGradeVo vo) {
        List<EvaluationGradeVo> list = evaluateScoreService.selectPreviewEvaluateScore(vo);
        return AjaxResult.success(list);
    }

    @GetMapping("/listComprehensiveEvaluate/{taskId}")
    public AjaxResult listComprehensiveEvaluate(@PathVariable("taskId") String taskId) {
        List<VEvaluateScore.Comprehensive> list = evaluateScoreService.selectComprehensiveEvaluate(taskId);
        return AjaxResult.success(list);
    }

    /**
     * 整合后的考核任务数据
     * @param evaluationGrade
     * @return
     */
    @GetMapping("/escalationList")
    public TableDataInfo escalationList(VEvaluationGrade evaluationGrade){
        startPage();
        List<VEvaluationGrade> vEvaluationGrades = evaluateScoreService.selectEscalationList(evaluationGrade);
        return getDataTable(vEvaluationGrades);
    }

    @GetMapping("/listAggregateEvaluate")
    public AjaxResult aggregateEvaluate(BizAssessTaskVo.EvaluateLevelTempReq req){
        List<Map<String, Object>> list = evaluateScoreService.selectTempEvaluateScoreList(req);
        return AjaxResult.success(list);
    }

    @GetMapping("/listTempCompositeEvaluate")
    public AjaxResult listTempCompositeEvaluate(BizAssessTaskVo.EvaluateLevelTempReq req) {
        List<BizAssessTaskVo.EvaluateLevelTempResp> list = evaluateScoreService.selectEvaluateLevelTempList(req);
        return AjaxResult.success(list);
    }

    @Log(title = "导出平时考核综合得分表", businessType = BusinessType.EXPORT)
    @GetMapping("/exportEvaluateComposite")
    public void exportEvaluateComposite(BizAssessTaskVo.EvaluateLevelTempReq req, HttpServletResponse response) {
        evaluateScoreService.exportComposite(req,response);
    }
}
