package com.cb.exam.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.exam.domain.ExamPaperDetail;
import com.cb.exam.domain.ExamQuestions;
import com.cb.exam.dto.ExamPaperDetailDto;
import com.cb.exam.dto.ExamQuestionsDto;
import com.cb.exam.service.IExamQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 试题Controller
 *
 * @author hu
 * @date 2023-11-07
 */
@RestController
@RequestMapping("/exam/questions")
public class ExamQuestionsController extends BaseController {
    @Autowired
    private IExamQuestionsService examQuestionsService;


    /**
     * 查询试题列表
     */
    @PreAuthorize("@ss.hasPermi('exam:questions:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExamQuestions examQuestions) {
        startPage();
        List<ExamQuestions> list = examQuestionsService.selectExamQuestionsList(examQuestions);
        return getDataTable(list);
    }

    /**
     * 查询试题列表
     */
    @PreAuthorize("@ss.hasPermi('exam:questions:list')")
    @GetMapping("/listAndOptions")
    public TableDataInfo listAndOptions(ExamQuestions examQuestions) {
        startPage();
        return examQuestionsService.selectExamQuestionAndOptionsByLabelsBanks(examQuestions);
    }

    /**
     * 查询试题列表
     */
    @PreAuthorize("@ss.hasPermi('exam:questions:list')")
    @PostMapping("/getCountByType")
    public AjaxResult getCountByType(@RequestBody ExamPaperDetailDto.SelectQuestionTypeCount.Input paperDetail) {
        ExamPaperDetail detail = examQuestionsService.selectExamQuestionCountByType(paperDetail);
        return AjaxResult.success(detail);
    }

    /**
     * 导出试题列表
     */
    @PreAuthorize("@ss.hasPermi('exam:questions:export')")
    @Log(title = "试题", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ExamQuestions examQuestions) {
        List<ExamQuestions> list = examQuestionsService.selectExamQuestionsList(examQuestions);
        ExcelUtil<ExamQuestions> util = new ExcelUtil<ExamQuestions>(ExamQuestions.class);
        return util.exportExcel(list, "试题数据");
    }

    /**
     * 获取试题详细信息
     */
    @PreAuthorize("@ss.hasPermi('exam:questions:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(examQuestionsService.selectExamQuestionsById(id));
    }

    /**
     * 新增试题
     */
    @PreAuthorize("@ss.hasPermi('exam:questions:add')")
    @Log(title = "试题", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExamQuestions examQuestions) {
        return toAjax(examQuestionsService.insertExamQuestions(examQuestions));
    }

    /***
     * @Summary
     * @Param:
     * @Return:
     * @Author: hu
     * @Date: 2023/11/08
     * @Description 新增试题
     */
    @PreAuthorize("@ss.hasPermi('exam:questions:add')")
    @Log(title = "试题", businessType = BusinessType.INSERT)
    @PostMapping(value = "/insert")
    public AjaxResult insertBatch(@RequestBody ExamQuestionsDto.Add.Input examQuestions) {
        return toAjax(examQuestionsService.addExamQuestions(examQuestions));
    }

    /***
     * @Summary
     * @Param:
     * @Return:
     * @Author: hu
     * @Date: 2023/11/08
     * @Description 新增试题
     */
    @Log(title = "试题", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/updateExamQuestions")
    public AjaxResult updateExamQuestions(@RequestBody ExamQuestionsDto.Add.Input examQuestions) {
        return toAjax(examQuestionsService.updateExamQuestions(examQuestions));
    }

    /***
     * @Summary
     * @Param:
     * @Return:
     * @Author: hu
     * @Date: 2023/11/08
     * @Description 批量新增试题
     */
    @PreAuthorize("@ss.hasPermi('exam:questions:add')")
    @Log(title = "试题", businessType = BusinessType.INSERT)
    @PostMapping(value = "/insertBatch")
    public AjaxResult insert(@RequestBody ExamQuestionsDto.AddBatch.Input examQuestions) {
        examQuestions.getList().stream().forEach(item -> {
            examQuestionsService.addExamQuestions(item);
        });
        return toAjax(1);
    }


    @Log(title = "试题批量导入", businessType = BusinessType.INSERT)
    @PostMapping(value = "/upload")
    public AjaxResult upload(MultipartFile file) throws IOException {
        return AjaxResult.success(examQuestionsService.uploadExamQuestions(file));
    }


    /**
     * 修改试题
     */
    @PreAuthorize("@ss.hasPermi('exam:questions:edit')")
    @Log(title = "试题", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExamQuestions examQuestions) {
        return toAjax(examQuestionsService.updateExamQuestions(examQuestions));
    }

    /**
     * 删除试题
     */
    @PreAuthorize("@ss.hasPermi('exam:questions:remove')")
    @Log(title = "试题", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(examQuestionsService.deleteExamQuestionsByIds(ids));
    }

    /**
     * 删除试题
     */
    @PreAuthorize("@ss.hasPermi('exam:questions:remove')")
    @Log(title = "试题", businessType = BusinessType.DELETE)
    @DeleteMapping("/byId/{id}")
    public AjaxResult removebyId(@PathVariable Long id) {
        return toAjax(examQuestionsService.deleteExamQuestionsById(id));
    }

}
