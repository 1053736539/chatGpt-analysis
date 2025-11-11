package com.cb.exam.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.exam.domain.ExamPaper;
import com.cb.exam.domain.ExamTask;
import com.cb.exam.dto.ExamPaperDto;
import com.cb.exam.service.IExamPaperQuestionsService;
import com.cb.exam.service.IExamPaperService;
import com.cb.exam.service.IExamTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 试卷Controller
 *
 * @author hu
 * @date 2023-11-08
 */
@RestController
@RequestMapping("/exam/paper")
public class ExamPaperController extends BaseController {
    @Autowired
    private IExamPaperService examPaperService;

    @Autowired
    private IExamPaperQuestionsService paperQuestionsService;
    @Autowired
    private IExamTaskService taskService;

    /**
     * 查询试卷列表
     */
    @PreAuthorize("@ss.hasPermi('exam:paper:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExamPaper examPaper) {
        startPage();
        List<ExamPaper> list = examPaperService.selectExamPaperList(examPaper);
        return getDataTable(list);
    }

    /**
     * 查询试卷 开始考试的试卷
     */
    @GetMapping("/paperQuestionList")
    public AjaxResult selectPaperQuestionByPaperList(Long paperId) {
//        startPage();
        return AjaxResult.success(paperQuestionsService.selectPaperQuestionByPaperList(paperId));
    }

    /**
     * 导出试卷列表
     */
    @PreAuthorize("@ss.hasPermi('exam:paper:export')")
    @Log(title = "试卷", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ExamPaper examPaper) {
        List<ExamPaper> list = examPaperService.selectExamPaperList(examPaper);
        ExcelUtil<ExamPaper> util = new ExcelUtil<ExamPaper>(ExamPaper.class);
        return util.exportExcel(list, "试卷数据");
    }

    /**
     * 获取试卷详细信息
     */
    @PreAuthorize("@ss.hasPermi('exam:paper:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        ExamPaper examPaper = examPaperService.selectExamPaperById(id);
        Long paperId = id;
        ExamTask query = new ExamTask();
        query.setExaminationPaperId(paperId);
        List<ExamTask> taskList = taskService.selectExamTaskList(query);
        if(!taskList.isEmpty()){
            HashMap<String, Object> map = new HashMap<>();
            map.put("useByTask",true);
            examPaper.setParams(map);
        }
        return AjaxResult.success(examPaper);
    }

    /**
     * 新增试卷
     */
    @PreAuthorize("@ss.hasPermi('exam:paper:add')")
    @Log(title = "试卷", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExamPaper examPaper) {
        return toAjax(examPaperService.insertExamPaper(examPaper));
    }

    /**
     * 新增试卷
     */
    @PreAuthorize("@ss.hasPermi('exam:paper:add')")
    @Log(title = "试卷", businessType = BusinessType.INSERT)
    @PostMapping(value = "/insert")
    public AjaxResult insert(@RequestBody ExamPaperDto.Add.Input examPaper) {
        examPaper.setCreateUser(getUsername());
        return toAjax(examPaperService.addExamPaper(examPaper));
    }

    /**
     * 修改试卷
     */
    @PreAuthorize("@ss.hasPermi('exam:paper:edit')")
    @Log(title = "试卷", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExamPaper examPaper) {
        return toAjax(examPaperService.updateExamPaper(examPaper));
    }

    /**
     * 删除试卷
     */
    @PreAuthorize("@ss.hasPermi('exam:paper:remove')")
    @Log(title = "试卷", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) throws Exception {
        if(ids.length == 1){
            Long paperId = ids[0];
            ExamTask query = new ExamTask();
            query.setExaminationPaperId(paperId);
            List<ExamTask> taskList = taskService.selectExamTaskList(query);
            if(!taskList.isEmpty()){
                throw new RuntimeException("试卷已被任务引用，不允许修改及删除!");
            }
        }
        return toAjax(examPaperService.deleteExamPaperByIds(ids));
    }
}
