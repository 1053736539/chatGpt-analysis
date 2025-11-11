package com.cb.exam.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.exam.domain.ExamTask;
import com.cb.exam.service.IExamTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 考试任务Controller
 *
 * @author hu
 * @date 2023-11-08
 */
@RestController
@RequestMapping("/exam/task")
public class ExamTaskController extends BaseController {
    @Autowired
    private IExamTaskService examTaskService;

    /**
     * 查询考试任务列表
     */
    @PreAuthorize("@ss.hasPermi('exam:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExamTask examTask) {
        startPage();
        List<ExamTask> list = examTaskService.selectExamTaskList(examTask);
        return getDataTable(list);
    }

    /**
     * 导出考试任务列表
     */
    @PreAuthorize("@ss.hasPermi('exam:task:export')")
    @Log(title = "考试任务", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ExamTask examTask) {
        List<ExamTask> list = examTaskService.selectExamTaskList(examTask);
        ExcelUtil<ExamTask> util = new ExcelUtil<ExamTask>(ExamTask.class);
        return util.exportExcel(list, "考试任务数据");
    }

    /**
     * 获取考试任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('exam:task:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(examTaskService.selectExamTaskById(id));
    }

    /**
     * 新增考试任务
     */
    @PreAuthorize("@ss.hasPermi('exam:task:add')")
    @Log(title = "考试任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExamTask examTask) {
        return toAjax(examTaskService.insertExamTask(examTask));
    }

    /**
     * 修改考试任务
     */
    @PreAuthorize("@ss.hasPermi('exam:task:edit')")
    @Log(title = "考试任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExamTask examTask) {
        return toAjax(examTaskService.updateExamTask(examTask));
    }

    /**
     * 删除考试任务
     */
    @PreAuthorize("@ss.hasPermi('exam:task:remove')")
    @Log(title = "考试任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) throws Exception {
        return toAjax(examTaskService.deleteExamTaskByIds(ids));
    }
}
