package com.cb.assess.controller;

import com.cb.assess.domain.BizAssessEvaluationGrade;
import com.cb.assess.domain.BizAssessPromulgate;
import com.cb.assess.domain.BizAssessRegularInfo;
import com.cb.assess.domain.VEvaluationGrade;
import com.cb.assess.service.VEvaluationGradeService;
import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/assess/VEvaluation")
public class VEvaluationGradeController extends BaseController {
    @Autowired
    private VEvaluationGradeService evaluationGradeService;

    @GetMapping("/list")
    public TableDataInfo list(VEvaluationGrade evaluationGrade) {
        startPage();
        List<VEvaluationGrade> list = evaluationGradeService.selectVEvaluationGradeList(evaluationGrade);
        return getDataTable(list);
    }



    @GetMapping("/gradeList")
    public TableDataInfo gradeList(VEvaluationGrade.Grade grade) {
        startPage();
        List<VEvaluationGrade.Grade> grades = evaluationGradeService.selectGradeList(grade);
        return getDataTable(grades);
    }

    /**
     * 导出部门上报考核数据
     */
    @Log(title = "导出考核数据-部门上报", businessType = BusinessType.EXPORT)
    @GetMapping("/exportGrade")
    public AjaxResult export(VEvaluationGrade.ExportLevelGrade grade) {
        List<VEvaluationGrade.ExportLevelGrade> grades = evaluationGradeService.selectExportGradeList(grade);
        ExcelUtil<VEvaluationGrade.ExportLevelGrade> util = new ExcelUtil<>(VEvaluationGrade.ExportLevelGrade.class);
        return util.exportExcel(grades, grade.getTaskName());
    }

    @GetMapping("/regularInfo/{taskId}/{userId}")
    public AjaxResult getRegularInfo(@PathVariable("taskId") String taskId, @PathVariable("userId") Long userId) {
        if (StringUtils.isBlank(taskId) || null == userId) return AjaxResult.error("参数异常");
        BizAssessRegularInfo regular = evaluationGradeService.selectRegularInfo(taskId, userId);
        if (null == regular) {
            return AjaxResult.error("未填报个人总结");
        }
        return AjaxResult.success(regular);
    }

    /**
     * 综合得分及建议评定等次表接口
     *
     * @return String personnelType,String cycleDesc
     */


    @GetMapping("/composite")
    public AjaxResult selectComposite(VEvaluationGrade.Composite composite) {
        List<VEvaluationGrade.Composite> composites = evaluationGradeService.selectCompositeList(composite);
        return AjaxResult.success(composites);
    }

    /**
     * 获取个人评分明细
     *
     * @param
     * @return
     */
    @GetMapping("/voterDetail")
    public AjaxResult selectVoterDetail(VEvaluationGrade.Voter voter) {
        VEvaluationGrade.PersonalScore personalScore = evaluationGradeService.selectVoterDetailList(voter);
        return AjaxResult.success(personalScore);
    }

    /**
     * 保存得分以及建议等次表
     *
     * @param param
     * @return
     */
    @Log(title = "保存得分以及建议等次表", businessType = BusinessType.INSERT)
    @PostMapping("/saveComprehensive")
    public AjaxResult saveComprehensive(@RequestBody VEvaluationGrade.ScoreLevelTempParam param) {
        return AjaxResult.success(evaluationGradeService.saveComprehensive(param));
    }

    /**
     * 获取纪委评分的抽象季度和专项考核任务列表
     * @param evaluationGrade
     * @return
     */
    @GetMapping("/listDiscipline")
    public TableDataInfo listDiscipline(VEvaluationGrade evaluationGrade) {
        startPage();
        List<VEvaluationGrade> list = evaluationGradeService.selectDisciplineVEvaluationGradeList(evaluationGrade);
        return getDataTable(list);
    }

    @GetMapping("/toBeEvaluatedList/{isScoring}")
    public TableDataInfo toBeEvaluatedList(VEvaluationGrade.Grade grade,@PathVariable Boolean isScoring) {
        startPage();
        List<VEvaluationGrade.Grade> list = evaluationGradeService.selectToBeEvaluatedList(grade,isScoring);
        return getDataTable(list);
    }

    /**
     * 获取分管部门主要负责人（包括：二级巡视员、总师、督查员）获取纪委评分的抽象季度和专项考核任务列表
     * @param evaluationGrade
     * @return
     */
    @GetMapping("/listMainCharge")
    public TableDataInfo listMainCharge(VEvaluationGrade evaluationGrade) {
        startPage();
        List<VEvaluationGrade> list = evaluationGradeService.selectMainChargeVEvaluationGradeList(evaluationGrade);
        return getDataTable(list);
    }

    @GetMapping("/toBeOrderGradeList/{isScoring}")
    public AjaxResult toBeOrderGradeList(VEvaluationGrade.Grade grade,@PathVariable Boolean isScoring) {
        List<VEvaluationGrade.Grade> list = evaluationGradeService.toBeOrderGradeList(grade,isScoring);
        return AjaxResult.success(list);
    }

    /**
     * 获取机关党委（人事处）审定意见列表
     * @param evaluationGrade
     * @return
     */
    @GetMapping("/listOrganTask")
    public TableDataInfo listOrganTask(VEvaluationGrade evaluationGrade) {
        startPage();
        List<VEvaluationGrade> list = evaluationGradeService.selectOrganVEvaluationGradeList(evaluationGrade);
        return getDataTable(list);
    }

    @GetMapping("/toBeOrganGradeList/{isScoring}")
    public TableDataInfo toBeOrganGradeList(VEvaluationGrade.Grade grade,@PathVariable Boolean isScoring) {
        startPage();
        List<VEvaluationGrade.Grade> list = evaluationGradeService.selectToBeOrganGradeList(grade,isScoring);
        return getDataTable(list);
    }

    @GetMapping("/exportRegular2Word/{taskId}/{userId}")
    public void exportRegular2Word(@PathVariable("taskId") String taskId, @PathVariable("userId") Long userId,
                                         HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (StringUtils.isBlank(taskId) || null == userId) throw new RuntimeException("参数异常");
        evaluationGradeService.exportRegular2Word(taskId, userId, request, response);
    }

    @Log(title = "导出平时考核综合得分及建议评定等次表", businessType = BusinessType.EXPORT)
    @GetMapping("/exportEvaluationCompositeLevel")
    public void exportEvaluationCompositeLevel(VEvaluationGrade.Composite composite,HttpServletResponse response) {
        evaluationGradeService.exportEvaluationCompositeLevel(composite,response);
    }

    @Log(title = "保存党组会建议等次", businessType = BusinessType.UPDATE)
    @PutMapping("/saveDzhOpinion")
    public AjaxResult saveDzhOpinion(@RequestBody BizAssessEvaluationGrade grade) {
        evaluationGradeService.saveDzhOpinion(grade);
        return AjaxResult.success();
    }

    @Log(title = "批量保存党组会建议等次", businessType = BusinessType.UPDATE)
    @PutMapping("/batchSaveDzhOpinion")
    public AjaxResult batchSaveDzhOpinion(@RequestBody List<BizAssessEvaluationGrade> gradeList) {
        evaluationGradeService.batchSaveDzhOpinion(gradeList);
        return AjaxResult.success();
    }

    @Log(title = "考核结果公示", businessType = BusinessType.INSERT)
    @PostMapping("/publicity")
    public AjaxResult publicity(@RequestBody BizAssessPromulgate promulgate) {
        evaluationGradeService.publicity(promulgate);
        return AjaxResult.success();
    }


    @GetMapping("/listPublicity")
    public AjaxResult listPublicity( VEvaluationGrade.Publicity publicity) {
        List<VEvaluationGrade.Publicity> list = evaluationGradeService.selectPublicityList(publicity);
        return AjaxResult.success(list);
    }

    @GetMapping("/getPromulgate")
    public AjaxResult getPromulgate( VEvaluationGrade.Publicity publicity) {
        VEvaluationGrade.PromulgateResult promulgateResult = evaluationGradeService.selectPromulgateResult(publicity);
        return AjaxResult.success(promulgateResult);
    }

    /**
     * 导出申请请示word文档
     * @param composite
     */
    @PostMapping("/exportInstructionsWord")
    public void exportInstructionsWord(HttpServletRequest request,HttpServletResponse response, @RequestBody VEvaluationGrade.Composite composite){
        if(!"0".equals(composite.getSpecial()) || StringUtils.isBlank(composite.getCycleDesc())){
            throw new IllegalArgumentException("参数错误，季度考核才允许导出，且需要提供对应的季度！");
        }
        evaluationGradeService.exportInstructionWord(response, composite);
    }

}
