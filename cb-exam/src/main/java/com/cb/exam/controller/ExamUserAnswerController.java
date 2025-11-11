package com.cb.exam.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.exam.domain.ExamTask;
import com.cb.exam.domain.ExamUserAnswer;
import com.cb.exam.domain.VExamUserAnswer;
import com.cb.exam.dto.ExamUserAnswerDto;
import com.cb.exam.service.IExamTaskService;
import com.cb.exam.service.IExamUserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hu
 * @date 2023/11/08
 * 用户历史答题功能
 */
@RestController
@RequestMapping("/exam/userAnswer")
public class ExamUserAnswerController extends BaseController {

    @Autowired
    private IExamUserAnswerService userAnswerService;

    @Autowired
    private IExamTaskService examTaskService;

    @PostMapping("/addUserAnswer")
    public AjaxResult addPaperQuestion(@RequestBody ExamUserAnswerDto.Add.Input paperQuestionsDto) {
        Long taskId = paperQuestionsDto.getTaskId().longValue();
        ExamTask task = examTaskService.selectExamTaskById(taskId);
        Long count = task.getCount();
        VExamUserAnswer query = new VExamUserAnswer();
        query.setTaskId(taskId);
        query.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        List<VExamUserAnswer> userAnswerList = userAnswerService.selectExamUserAnswerList(query);
        if(userAnswerList.size() >= count){
            return AjaxResult.error("答题次数已用完");
        }
        paperQuestionsDto.setUserId(getDeptId());
        paperQuestionsDto.setCreateUser(SecurityUtils.getLoginUser().getUsername());
        paperQuestionsDto.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        return AjaxResult.success(userAnswerService.insertExamUserAnswer(paperQuestionsDto));
    }

    /**
     * 查询用户历史答题列表
     */
    @PostMapping("/getUserAnswer")
    public AjaxResult getUserAnswer(@RequestBody Long id) {
        return AjaxResult.success(userAnswerService.selectExamUserAnswerByPaperIdList(id));
    }

    /**
     * 查询用户答题列表
     */
    @GetMapping("/list")
    /*public TableDataInfo list(ExamUserAnswer examUserAnswer) {
        startPage();
        List<ExamUserAnswerVo> list = userAnswerService.selectExamUserAnswerList(examUserAnswer);
        return getDataTable(list);
    }*/
    public TableDataInfo list(VExamUserAnswer examUserAnswer) {
        startPage();
        List<VExamUserAnswer> list = userAnswerService.selectExamUserAnswerList(examUserAnswer);
        return getDataTable(list);
    }

    /**
     * 导出用户答题列表
     */
    @Log(title = "用户答题", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(VExamUserAnswer examUserAnswer) {
        List<VExamUserAnswer> list = userAnswerService.selectExamUserAnswerList(examUserAnswer);
        ExcelUtil<VExamUserAnswer> util = new ExcelUtil<VExamUserAnswer>(VExamUserAnswer.class);
        return util.exportExcel(list, "用户答题数据");
    }

    /**
     * 获取用户答题详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(userAnswerService.selectExamUserAnswerById(id));
    }

    /**
     * 新增用户答题
     */
    @Log(title = "用户答题", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExamUserAnswer examUserAnswer) {
        return toAjax(userAnswerService.insertExamUserAnswer(examUserAnswer));
    }

    /**
     * 修改用户答题
     */
    @Log(title = "用户答题", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExamUserAnswer examUserAnswer) {
        return toAjax(userAnswerService.updateExamUserAnswer(examUserAnswer));
    }

    /**
     * 删除用户答题
     */
    @Log(title = "用户答题", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(userAnswerService.deleteExamUserAnswerByIds(ids));
    }

}
