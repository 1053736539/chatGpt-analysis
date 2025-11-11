package com.cb.assess.controller;

import com.cb.assess.domain.BizAssessTaskAssessUserConfirm;
import com.cb.assess.domain.VRegularFillInfo;
import com.cb.assess.domain.vo.AssessTaskProcessStatisticsVo;
import com.cb.assess.service.AssessTaskProcessStatisticsService;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/assess/process/statistics")
public class AssessTaskProcessStatisticsController extends BaseController {
    @Autowired
    private AssessTaskProcessStatisticsService statisticsService;

    @GetMapping("/list")
    public AjaxResult list(AssessTaskProcessStatisticsVo vo){
        List<AssessTaskProcessStatisticsVo> vos = statisticsService.selectAssessTaskProcessCount(vo);
        return AjaxResult.success(vos);
    }

    @GetMapping("/userConfirmList")
    public AjaxResult userConfirmList(AssessTaskProcessStatisticsVo vo){
        List<BizAssessTaskAssessUserConfirm> confirms = statisticsService.selectUserConfirmList(vo);
        return AjaxResult.success(confirms);
    }

    @GetMapping("/regularFillList")
    public TableDataInfo regularFillList(VRegularFillInfo info){
        startPage();
        List<VRegularFillInfo> list = statisticsService.selectVRegularFillInfoList(info);
        return getDataTable(list);
    }

    @GetMapping("/evaluateReportList")
    public TableDataInfo evaluateReportList(AssessTaskProcessStatisticsVo.EvaluateReport report){
        startPage();
        List<AssessTaskProcessStatisticsVo.EvaluateReport> list = statisticsService.selectEvaluateReportList(report);
        return getDataTable(list);
    }

    @GetMapping("/evaluateReportAuditList")
    public TableDataInfo evaluateReportAuditList(AssessTaskProcessStatisticsVo.EvaluateReportAudit audit){
        startPage();
        List<AssessTaskProcessStatisticsVo.EvaluateReportAudit> list = statisticsService.selectEvaluateReportAuditList(audit);
        return getDataTable(list);
    }

    @GetMapping("/disciplineList")
    public TableDataInfo disciplineList(AssessTaskProcessStatisticsVo.Discipline discipline){
        startPage();
        List<AssessTaskProcessStatisticsVo.Discipline> list = statisticsService.selectDisciplineList(discipline);
        return getDataTable(list);
    }

    @GetMapping("/mainLevelGradeList")
    public TableDataInfo mainLevelGradeList(AssessTaskProcessStatisticsVo.MainLevelGrade grade){
        startPage();
        List<AssessTaskProcessStatisticsVo.MainLevelGrade> list = statisticsService.selectMainLevelGradeList(grade);
        return getDataTable(list);
    }

    @GetMapping("/rscOpinionList")
    public TableDataInfo rscOpinionList(AssessTaskProcessStatisticsVo.Grade grade){
        startPage();
        List<AssessTaskProcessStatisticsVo.Grade> list = statisticsService.selectRscOpinionList(grade);
        return getDataTable(list);
    }

    @GetMapping("/dzhOpinionList")
    public TableDataInfo dzhOpinionList(AssessTaskProcessStatisticsVo.Grade grade){
        startPage();
        List<AssessTaskProcessStatisticsVo.Grade> list = statisticsService.selectDzhOpinionList(grade);
        return getDataTable(list);
    }

    @GetMapping("/promulgateList")
    public TableDataInfo promulgateList(AssessTaskProcessStatisticsVo.Promulgate promulgate){
        startPage();
        List<AssessTaskProcessStatisticsVo.Promulgate> list = statisticsService.selectPromulgateList(promulgate);
        return getDataTable(list);
    }

}
