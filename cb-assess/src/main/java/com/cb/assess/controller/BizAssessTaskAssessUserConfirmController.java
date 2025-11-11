package com.cb.assess.controller;


import com.cb.assess.domain.BizAssessTaskAssessUserConfirm;
import com.cb.assess.domain.BizAssessTaskManage;
import com.cb.assess.domain.VOrdinaryAssessTask;
import com.cb.assess.service.IBizAssessTaskAssessUserConfirmService;
import com.cb.assess.service.IBizAssessTaskManageService;
import com.cb.common.annotation.Log;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cb.common.core.controller.BaseController;

import java.util.List;

/**
 * 考核任务下发确认Controller
 *
 * @author ouyang
 * @date 2024-05-31
 */
@RestController
@RequestMapping("/assess/confirm")
public class BizAssessTaskAssessUserConfirmController extends BaseController {
    @Autowired
    private IBizAssessTaskAssessUserConfirmService confirmService;


    @GetMapping("/confirmList")
    public TableDataInfo list(VOrdinaryAssessTask task) {
        startPage();
        List<VOrdinaryAssessTask> list = confirmService.selectTaskManageNeedConfirmList(task);
        return getDataTable(list);
    }


    @PostMapping("/getAssessUser")
    public AjaxResult getAssessUser(@RequestBody BizAssessTaskManage manage) {
        return AjaxResult.success(confirmService.selectAssessUser(manage));
    }


    @Log(title = "考核任务人员保存", businessType = BusinessType.INSERT)
    @PostMapping("/saveOrUpdate")
    public AjaxResult saveOrUpdate(@RequestBody BizAssessTaskAssessUserConfirm confirm) {
        return toAjax(confirmService.saveOrUpdate(confirm));
    }

    @Log(title = "考核任务人员驳回", businessType = BusinessType.UPDATE)
    @PostMapping("/rejection")
    public AjaxResult rejection(@RequestBody BizAssessTaskAssessUserConfirm confirm) {
        return AjaxResult.success(confirmService.rejection(confirm));
    }

    @GetMapping("/getChargeAssessUser")
    public AjaxResult getChargeAssessUser(String taskId,String proId,Long  deptId) {
        return AjaxResult.success(confirmService.selectChargeAssessUser(taskId,proId,deptId));
    }

    @GetMapping("/getConfirmInfo/{id}")
    public AjaxResult getConfirmInfo(@PathVariable("id")String id){
       return AjaxResult.success(confirmService.selectConfirmInfoById(id));
    }

    @Log(title = "管理员更新用户确认信息", businessType = BusinessType.UPDATE)
    @PutMapping("/updateConfirmInfo")
    public AjaxResult updateConfirmInfo(@RequestBody BizAssessTaskAssessUserConfirm confirm){
        return AjaxResult.success(confirmService.updateConfirmInfo(confirm));
    }
}
