package com.cb.assess.controller;

import com.cb.assess.domain.BizAssessEvaluationGrade;
import com.cb.assess.domain.BizAssessEvaluationGradeSaveMark;
import com.cb.assess.domain.vo.EscalationVo;
import com.cb.assess.service.EscalationService;
import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assess/escalation")
public class BizAssessEscalationController extends BaseController {

    @Autowired
    private EscalationService escalationService;

    @GetMapping("/list")
    public TableDataInfo list(EscalationVo vo) {
        startPage();
        List<EscalationVo> list = escalationService.selectEscalationList(vo);
        return getDataTable(list);
    }

    @GetMapping("/listExamine")
    public TableDataInfo listExamine(EscalationVo vo) {
        startPage();
        List<EscalationVo> list = escalationService.selectExamineEscalationList(vo);
        return getDataTable(list);
    }


    /**
     * 获取统计数据
     * @param voter
     * @return
     */
    @GetMapping("/census")
    public AjaxResult census(EscalationVo.Voter voter) {
        List<EscalationVo.ReportedEvaluate> evaluates = escalationService.selectCensusList(voter);
        return AjaxResult.success(evaluates);
    }

    @GetMapping("/checkReported")
    public AjaxResult checkReported(EscalationVo.EvaluateCensus evaluateCensus) {
        Boolean aBoolean = escalationService.checkReported(evaluateCensus);
        return AjaxResult.success(aBoolean);
    }

    @Log(title = "部门上报考核数据", businessType = BusinessType.INSERT)
    @PostMapping("/saveEvaluateCensus")
    public AjaxResult saveEvaluateCensus(@RequestBody EscalationVo.EvaluateCensus evaluateCensus) {
        return AjaxResult.success(escalationService.saveEvaluateCensus(evaluateCensus));
    }


    @GetMapping("/listPreviewEvaluateCensus")
    public AjaxResult listPreviewEvaluateCensus(EscalationVo.ReportedEvaluate reported) {
        List<EscalationVo.ReportedEvaluate> list = escalationService.selectReportedList(reported);
        return AjaxResult.success(list);
    }

    /**
     * 部门上报的考核数据审核
     * @param evaluateCensus
     * @return
     */
    @GetMapping("/listGradeSaveMark")
    public TableDataInfo listGradeSaveMark(EscalationVo.EvaluateCensus evaluateCensus) {
        startPage();
        List<BizAssessEvaluationGradeSaveMark> list = escalationService.selectEvaluationGradeSaveMark(evaluateCensus);
        return getDataTable(list);
    }

    /**
     * 获取考核的上报详情数据
     * @param
     * @return
     */
    @GetMapping("/listEvaluationGrade/{escalationId}")
    public AjaxResult listEvaluationGrade(@PathVariable String escalationId) {
        List<BizAssessEvaluationGrade> list = escalationService.selectBizAssessEvaluationGrade(escalationId);
        return AjaxResult.success(list);
    }

    @Log(title = "部门上报数据审核", businessType = BusinessType.UPDATE)
    @PutMapping("/evaluationGradeAudit")
    public AjaxResult evaluationGradeAudit(@RequestBody BizAssessEvaluationGradeSaveMark mark) {
        if(StringUtils.isBlank(mark.getId()) || StringUtils.isBlank(mark.getEscalationFlag())){
            return AjaxResult.error("参数异常");
        }
        return AjaxResult.success(escalationService.evaluationGradeAudit(mark));
    }
}
