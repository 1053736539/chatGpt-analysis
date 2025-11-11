package com.cb.exam.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.exam.domain.ExamOption;
import com.cb.exam.service.IExamOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 试题选项Controller
 *
 * @author hu
 * @date 2023-11-08
 */
@RestController
@RequestMapping("/exam/option")
public class ExamOptionController extends BaseController {
    @Autowired
    private IExamOptionService examOptionService;

    /**
     * 查询试题选项列表
     */
    @PreAuthorize("@ss.hasPermi('exam:option:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExamOption examOption) {
        startPage();
        List<ExamOption> list = examOptionService.selectExamOptionList(examOption);
        return getDataTable(list);
    }

    /**
     * 导出试题选项列表
     */
    @PreAuthorize("@ss.hasPermi('exam:option:export')")
    @Log(title = "试题选项", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ExamOption examOption) {
        List<ExamOption> list = examOptionService.selectExamOptionList(examOption);
        ExcelUtil<ExamOption> util = new ExcelUtil<ExamOption>(ExamOption.class);
        return util.exportExcel(list, "试题选项数据");
    }

    /**
     * 获取试题选项详细信息
     */
    @PreAuthorize("@ss.hasPermi('exam:option:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(examOptionService.selectExamOptionById(id));
    }

    /**
     * 新增试题选项
     */
    @PreAuthorize("@ss.hasPermi('exam:option:add')")
    @Log(title = "试题选项", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExamOption examOption) {
        return toAjax(examOptionService.insertExamOption(examOption));
    }

    /**
     * 修改试题选项
     */
    @PreAuthorize("@ss.hasPermi('exam:option:edit')")
    @Log(title = "试题选项", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExamOption examOption) {
        return toAjax(examOptionService.updateExamOption(examOption));
    }

    /**
     * 删除试题选项
     */
    @PreAuthorize("@ss.hasPermi('exam:option:remove')")
    @Log(title = "试题选项", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(examOptionService.deleteExamOptionByIds(ids));
    }
}
