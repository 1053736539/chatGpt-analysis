package com.cb.assess.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.enums.BusinessType;
import com.cb.assess.domain.BizAssessEvaluationGrade;
import com.cb.assess.service.IBizAssessEvaluationGradeService;
import com.cb.common.core.page.TableDataInfo;

/**
 * 考核评定等次存储Controller
 *
 * @author ouyang
 * @date 2023-11-24
 */
@RestController
@RequestMapping("/assess/grade")
public class BizAssessEvaluationGradeController extends BaseController {
    @Autowired
    private IBizAssessEvaluationGradeService bizAssessEvaluationGradeService;

    /**
     * 查询考核评定等次存储列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BizAssessEvaluationGrade bizAssessEvaluationGrade) {
        startPage();
        List<BizAssessEvaluationGrade> list = bizAssessEvaluationGradeService.selectBizAssessEvaluationGradeList(bizAssessEvaluationGrade);
        return getDataTable(list);
    }

    /**
     * 获取考核评定等次存储详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizAssessEvaluationGradeService.selectBizAssessEvaluationGradeById(id));
    }

    /**
     * 新增考核评定等次存储
     */
    @Log(title = "考核评定等次存储", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAssessEvaluationGrade bizAssessEvaluationGrade) {
        return toAjax(bizAssessEvaluationGradeService.insertBizAssessEvaluationGrade(bizAssessEvaluationGrade));
    }

    /**
     * 修改考核评定等次存储
     */
    @Log(title = "考核评定等次存储", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAssessEvaluationGrade bizAssessEvaluationGrade) {
        return toAjax(bizAssessEvaluationGradeService.updateBizAssessEvaluationGrade(bizAssessEvaluationGrade));
    }
}
