package com.cb.assess.controller;

import java.util.List;

import com.cb.assess.domain.BizAssessTaskManage;
import com.cb.assess.service.IBizAssessTaskManageService;
import com.cb.common.annotation.RepeatSubmit;
import com.cb.common.exception.CustomException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 考核任务管理Controller
 *
 * @author ouyang
 * @date 2023-11-04
 */
@RestController
@RequestMapping("/assess/taskManage")
public class BizAssessTaskManageController extends BaseController {
    @Autowired
    private IBizAssessTaskManageService bizAssessTaskManageService;

    /**
     * 查询考核任务管理列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BizAssessTaskManage bizAssessTaskManage) {
        startPage();
        List<BizAssessTaskManage> list = bizAssessTaskManageService.selectBizAssessTaskManageList(bizAssessTaskManage);
        return getDataTable(list);
    }

    /**
     * 导出考核任务管理列表
     */
    @Log(title = "考核任务管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizAssessTaskManage bizAssessTaskManage) {
        List<BizAssessTaskManage> list = bizAssessTaskManageService.selectBizAssessTaskManageList(bizAssessTaskManage);
        ExcelUtil<BizAssessTaskManage> util = new ExcelUtil<BizAssessTaskManage>(BizAssessTaskManage.class);
        return util.exportExcel(list, "taskManage");
    }

    /**
     * 获取考核任务管理详细信息
     */
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") String taskId) {
        return AjaxResult.success(bizAssessTaskManageService.selectBizAssessTaskManageById(taskId));
    }

    /**
     * 新增考核任务管理
     */
    @Log(title = "考核任务管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAssessTaskManage bizAssessTaskManage) {
       /* if(bizAssessTaskManageService.checkTaskExist(bizAssessTaskManage)){
            return AjaxResult.error("存在重复的考核方案,请检查!");
        }*/
        bizAssessTaskManageService.checkTaskIsSameYear(bizAssessTaskManage);
        bizAssessTaskManageService.checkTaskQuarter(bizAssessTaskManage);
        return toAjax(bizAssessTaskManageService.insertBizAssessTaskManage(bizAssessTaskManage));
    }

    /**
     * 修改考核任务管理
     */
    @Log(title = "考核任务管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAssessTaskManage bizAssessTaskManage) {
        /*if(bizAssessTaskManageService.checkTaskExist(bizAssessTaskManage)){
            return AjaxResult.error("存在重复的考核方案,请检查!");
        }*/
        bizAssessTaskManageService.checkTaskIsSameYear(bizAssessTaskManage);
        bizAssessTaskManageService.checkTaskQuarter(bizAssessTaskManage);
        return toAjax(bizAssessTaskManageService.updateBizAssessTaskManage(bizAssessTaskManage));
    }

    /**
     * 删除考核任务管理
     */
    @Log(title = "考核任务管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable String[] taskIds) {
        return toAjax(bizAssessTaskManageService.logicDeleteBizAssessTaskManageByIds(taskIds));
    }


    /**
     * 发布前展示各部门考核对象确认信息
     * @param manage
     * @return
     */
    @PostMapping("/reveal")
    public AjaxResult revealConfirmList(@RequestBody BizAssessTaskManage manage){
        return AjaxResult.success(bizAssessTaskManageService.confirmList(manage));
    }


    @Log(title = "考核任务管理-下发确认", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping("/distribute")
    public AjaxResult distribute(@RequestBody BizAssessTaskManage manage){
        return AjaxResult.success(bizAssessTaskManageService.distribute(manage));
    }


    @Log(title = "考核任务管理-发布", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping("/release")
    public AjaxResult release(@RequestBody BizAssessTaskManage manage){
        return AjaxResult.success(bizAssessTaskManageService.release(manage));
    }

    @Log(title = "考核任务管理-撤销发布", businessType = BusinessType.UPDATE)
    @PutMapping("/revoke")
    public AjaxResult revoke(@RequestBody BizAssessTaskManage manage){
        return AjaxResult.success(bizAssessTaskManageService.revoke(manage));
    }
}
