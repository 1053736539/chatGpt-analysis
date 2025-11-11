package com.cb.assess.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.cb.assess.domain.BizAssessRegularInfo;
import com.cb.assess.domain.BizIndividualAssessmentTask;
import com.cb.assess.domain.vo.*;
import com.cb.assess.service.IBizIndividualAssessmentTaskService;
import com.cb.common.annotation.Log;
import com.cb.common.annotation.RepeatSubmit;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.StringUtils;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/assess/individual/task")
@Validated
public class BizIndividualAssessmentTaskController extends BaseController {

    @Autowired
    private IBizIndividualAssessmentTaskService individualTaskService;

    @GetMapping("/toDoList")
    public TableDataInfo toDoList(BizIndividualAssessmentTask task) {
        startPage();
        List<BizIndividualAssessmentTask> list = individualTaskService.selectAllToDoList(task);
        return getDataTable(list);
    }

    @GetMapping("/procedure/{taskId}/{proId}")
    public AjaxResult initProcedure(@PathVariable("taskId") String taskId, @PathVariable("proId") String proId) {
        ProcedureVo procedureVo = individualTaskService.initProcedure(taskId, proId);
        return AjaxResult.success(procedureVo);
    }


    @GetMapping("/evaluateList")
    public TableDataInfo evaluateList(BizIndividualAssessmentTask task) {
        startPage();
        List<EvaluateVo> evaluateVos = individualTaskService.selectEvaluateList(task);
        return getDataTable(evaluateVos);
    }

    /**
     * 获取已评
     *
     * @param task
     * @return
     */
    @GetMapping("/completeEvaluateList")
    public TableDataInfo completeEvaluateList(BizIndividualAssessmentTask task) {
        startPage();
        List<EvaluateVo> evaluateVos = individualTaskService.selectCompleteEvaluateList(task);
        return getDataTable(evaluateVos);
    }


    @GetMapping("/initEvaluateItem")
    public AjaxResult initEvaluateItem(EvaluateItemParamVo paramVo) {
        EvaluateItemVo vo = individualTaskService.initEvaluateItemVo(paramVo);
        return AjaxResult.success(vo);
    }

    @GetMapping("/initRegularTask/{taskId}")
    public AjaxResult initRegularTask(@PathVariable("taskId") String taskId) {
        BizAssessRegularInfo regular = individualTaskService.initRegularTask(taskId);
        return AjaxResult.success(regular);
    }

    /**
     * 测评打分接口
     *
     * @return
     */
    @Log(title = "测评打分", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/evaluate/scoring")
    public AjaxResult scoring(@RequestBody @Valid EvaluateRecordParamVo param) {
        if (StringUtils.isEmpty(param.getEvaluateTagId()) || StringUtils.isEmpty(param.getEvaluateList())) {
            return AjaxResult.error("参数异常！");
        }
        int scoring = individualTaskService.scoring(param);
        return AjaxResult.success(scoring);
    }

    /**
     * 测评打分接口
     *
     * @return
     */
    @Log(title = "测评打分—批量打分", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/evaluate/batchScoring")
    public AjaxResult batchScoring(@RequestBody EvaluateBatchParamVo param) {
        if (StringUtils.isBlank(param.getTaskId()) || StringUtils.isBlank(param.getProId())
                || CollectionUtil.isEmpty(param.getEvaluateList())) {
            return AjaxResult.error("参数异常！");
        }
        int scoring = individualTaskService.batchScoring(param);
        return AjaxResult.success(scoring);
    }

    @GetMapping("/listTaskGrade")
    public TableDataInfo listTaskGrade(BizIndividualAssessmentTask task) {
        startPage();
        List<BizIndividualAssessmentTask> list = individualTaskService.listTaskGrade(task);
        return getDataTable(list);
    }


    /**
     * APP 端接口
     *
     * @param task
     * @return
     */
    @GetMapping("/app/toDoList")
    public AjaxResult appToDoList(BizIndividualAssessmentTask task) {
        List<BizIndividualAssessmentTask> list = individualTaskService.selectAllToDoList(task);
        return AjaxResult.success(list);
    }

    /**
     * APP端调用接口
     *
     * @param task
     * @return
     */
    @GetMapping("/app/evaluateList")
    public AjaxResult appEvaluateList(BizIndividualAssessmentTask task) {
        List<EvaluateVo> evaluateVos = individualTaskService.selectEvaluateList(task);
        return AjaxResult.success(evaluateVos);
    }

}
