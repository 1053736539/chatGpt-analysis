package com.cb.exam.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.exam.domain.ExamLabel;
import com.cb.exam.service.IExamLabelService;
import com.cb.exam.vo.ExamBankQuestionCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标签Controller
 * 
 * @author hu
 * @date 2023-11-07
 */
@RestController
@RequestMapping("/exam/label")
public class ExamLabelController extends BaseController
{
    @Autowired
    private IExamLabelService examLabelService;

    /**
     * 查询标签列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ExamBankQuestionCountVo examLabel)
    {
        startPage();
        //List<ExamLabel> list = examLabelService.selectExamLabelList(examLabel);
        List<ExamBankQuestionCountVo> list = examLabelService.selectExamLabelQuestionCount(examLabel);
        return getDataTable(list);
    }

    /**
     * 查询题库列表
     */
    @GetMapping("/listByQuestionId/{questionId}")
    public TableDataInfo list(@PathVariable("questionId") Long questionId)
    {
        startPage();
        List<ExamLabel> list = examLabelService.selectExamLabelByQuestionIdList(questionId);
        return getDataTable(list);
    }


    /**
     * 导出标签列表
     */
    @Log(title = "标签", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ExamLabel examLabel)
    {
        List<ExamLabel> list = examLabelService.selectExamLabelList(examLabel);
        ExcelUtil<ExamLabel> util = new ExcelUtil<ExamLabel>(ExamLabel.class);
        return util.exportExcel(list, "标签数据");
    }

    /**
     * 获取标签详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(examLabelService.selectExamLabelById(id));
    }

    /**
     * 新增标签
     */
    @Log(title = "标签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExamLabel examLabel)
    {
        return toAjax(examLabelService.insertExamLabel(examLabel));
    }

    /**
     * 修改标签
     */
    @Log(title = "标签", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExamLabel examLabel)
    {
        return toAjax(examLabelService.updateExamLabel(examLabel));
    }

    /**
     * 删除标签
     */
    @Log(title = "标签", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(examLabelService.deleteExamLabelByIds(ids));
    }
}
