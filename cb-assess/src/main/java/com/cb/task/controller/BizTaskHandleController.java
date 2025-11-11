package com.cb.task.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.task.domain.BizTaskHandle;
import com.cb.task.service.IBizTaskHandleService;
import com.cb.task.vo.TaskHandleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 任务分配的执行人/部门Controller
 * 
 * @author yangxin
 * @date 2023-10-30
 */
@RestController
@RequestMapping("/task/taskHandle")
public class BizTaskHandleController extends BaseController
{
    @Autowired
    private IBizTaskHandleService bizTaskHandleService;

    /**
     * 查询任务分配的执行人/部门列表
     */
    @PreAuthorize("@ss.hasPermi('task:taskHandle:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizTaskHandle bizTaskHandle)
    {
        startPage();
        List<BizTaskHandle> list = bizTaskHandleService.selectBizTaskHandleList(bizTaskHandle);
        return getDataTable(list);
    }

    /**
     * 导出任务分配的执行人/部门列表
     */
    @PreAuthorize("@ss.hasPermi('task:taskHandle:export')")
    @Log(title = "任务分配的执行人/部门", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizTaskHandle bizTaskHandle)
    {
        List<BizTaskHandle> list = bizTaskHandleService.selectBizTaskHandleList(bizTaskHandle);
        ExcelUtil<BizTaskHandle> util = new ExcelUtil<BizTaskHandle>(BizTaskHandle.class);
        return util.exportExcel(list, "taskHandle");
    }

    /**
     * 获取任务分配的执行人/部门详细信息
     */
    @PreAuthorize("@ss.hasPermi('task:taskHandle:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(bizTaskHandleService.selectBizTaskHandleById(id));
    }

    /**
     * 新增任务分配的执行人/部门
     */
    @PreAuthorize("@ss.hasPermi('task:taskHandle:add')")
    @Log(title = "任务分配的执行人/部门", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizTaskHandle bizTaskHandle)
    {
        return toAjax(bizTaskHandleService.insertBizTaskHandle(bizTaskHandle));
    }

    /**
     * 修改任务分配的执行人/部门
     */
    @PreAuthorize("@ss.hasPermi('task:taskHandle:edit')")
    @Log(title = "任务分配的执行人/部门", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizTaskHandle bizTaskHandle)
    {
        return toAjax(bizTaskHandleService.updateBizTaskHandle(bizTaskHandle));
    }

    /**
     * 删除任务分配的执行人/部门
     */
    @PreAuthorize("@ss.hasPermi('task:taskHandle:remove')")
    @Log(title = "任务分配的执行人/部门", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bizTaskHandleService.deleteBizTaskHandleByIds(ids));
    }

    /**
     * 查询我的待认领列表
     * @param bizTaskHandle
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('task:taskHandle:list')")
    @GetMapping("/listMyUnClaimList")
    public TableDataInfo listMyUnClaimList(BizTaskHandle bizTaskHandle){
        String username = SecurityUtils.getUsername();
        startPage();
        List<BizTaskHandle> list = bizTaskHandleService.listMyUnClaimList(bizTaskHandle, username);
        return getDataTable(list);
    }

    /**
     * 查询我的已阅列表
     * @param bizTaskHandle
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('task:taskHandle:list')")
    @GetMapping("/listMyReadList")
    public TableDataInfo listMyReadList(BizTaskHandle bizTaskHandle){
        String username = SecurityUtils.getUsername();
        startPage();
        List<BizTaskHandle> list = bizTaskHandleService.listMyReadList(bizTaskHandle, username);
        return getDataTable(list);
    }

    /**
     * 查询我的待办列表(个人待确认、执行中 + 部门未指派)
     * @param bizTaskHandle
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('task:taskHandle:list')")
    @GetMapping("/listMyTodoList")
    public TableDataInfo listMyTodoList(BizTaskHandle bizTaskHandle){
        SysDept dept = SecurityUtils.getOnlineDept();
        Long leaderDeptId = null;
        String username = SecurityUtils.getUsername();
        if(username.equals(dept.getLeader())){
            leaderDeptId = dept.getDeptId();
        }

        startPage();
        List<BizTaskHandle> list = bizTaskHandleService.listMyTodoList(bizTaskHandle,username,leaderDeptId);
        return getDataTable(list);
    }

    /**
     * 查询已完成的列表（我个人已完成 + 当前为部门负责人时的部门已完成任务）
     * @param bizTaskHandle
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('task:taskHandle:list')")
    @GetMapping("/listMyCompleteList")
    public TableDataInfo listMyCompleteList(BizTaskHandle bizTaskHandle){
        SysDept dept = SecurityUtils.getOnlineDept();
        Long leaderDeptId = null;
        String username = SecurityUtils.getUsername();
        if(username.equals(dept.getLeader())){
            leaderDeptId = dept.getDeptId();
        }

        startPage();
        List<BizTaskHandle> list = bizTaskHandleService.listMyCompleteList(bizTaskHandle,username,leaderDeptId);
        return getDataTable(list);
    }

    /**
     * 查询我的超时任务
     * @param bizTaskHandle
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('task:taskHandle:list')")
    @GetMapping("/listMyExpireList")
    public TableDataInfo listMyExpireList(BizTaskHandle bizTaskHandle){
        SysDept dept = SecurityUtils.getOnlineDept();
        Long leaderDeptId = null;
        String username = SecurityUtils.getUsername();

        startPage();
        List<BizTaskHandle> list = bizTaskHandleService.listMyExpireList(bizTaskHandle,username);
        return getDataTable(list);
    }

    /**
     * TODO 弃用，当前业务直接指到人，且默认为执行中
     * 部门负责人指派部门处理记录的执行人
     * @param req
     * @return
     */
    @Deprecated
    @PostMapping("/assign")
    public AjaxResult assign(@Valid @RequestBody TaskHandleVo.AssignReq req){
        try{
            bizTaskHandleService.assign(req);
        } catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }

    /**
     * TODO 弃用，当前业务直接指到人，且默认为执行中
     * 执行人确认任务
     * @param handleId
     * @return
     */
    @Deprecated
    @GetMapping("/confirm/{handleId}")
    public AjaxResult confirm(@PathVariable String handleId){
        bizTaskHandleService.confirm(handleId);
        return AjaxResult.success();
    }

    /**
     * 转办
     * @param req
     * @return
     */
    @PostMapping("/transfer")
    public AjaxResult transfer(@Valid @RequestBody TaskHandleVo.TransferReq req){
        bizTaskHandleService.transfer(req);
        return AjaxResult.success();
    }

    /**
     * 更新进度
     * @param req
     * @return
     */
    @PostMapping("/progress")
    public AjaxResult progress(@Valid @RequestBody TaskHandleVo.ProgressReq req){
        bizTaskHandleService.progess(req);
        return AjaxResult.success();
    }

    /**
     * 处理完成
     * @param req
     * @return
     */
    @PostMapping("/complete")
    public AjaxResult complete(@Valid @RequestBody TaskHandleVo.CompleteReq req){
        bizTaskHandleService.complete(req);
        return AjaxResult.success();
    }

    /**
     * 一键办结（认领+处理完成）
     * @param req
     * @return
     */
    @PostMapping("/claimComplete")
    public AjaxResult claimComplete(@Valid @RequestBody TaskHandleVo.CompleteReq req){
        bizTaskHandleService.claim(req.getHandleId());
        bizTaskHandleService.complete(req);
        return AjaxResult.success();
    }

    /**
     * 认领办理
     * @param id
     * @return
     */
    @PostMapping("/claim/{id}")
    public AjaxResult claim(@PathVariable("id") String id){
        bizTaskHandleService.claim(id);
        return AjaxResult.success();
    }

    /**
     * 已阅
     * @param id
     * @return
     */
    @PostMapping("/read/{id}")
    public AjaxResult read(@PathVariable("id") String id){
        bizTaskHandleService.read(id);
        return AjaxResult.success();
    }

    /**
     * 催办
     * @param id
     * @return
     */
    @PostMapping("/urge/{id}")
    public AjaxResult urge(@PathVariable("id") String id,String msg){
        bizTaskHandleService.urge(id,msg);
        return AjaxResult.success();
    }

    /**
     * 罗列出用于年度考核和季度考核的一键引用工作任务信息
     * @param req
     * @return
     */
    @PostMapping("/list4Assess")
    public TableDataInfo list4Assess(@Validated @RequestBody TaskHandleVo.List4AssessReq req){
        DateTime baseDate = DateUtil.date(req.getYear());
        Integer quarter = req.getQuarter();
        String begin;
        String end;
        if(null == quarter){
            begin = DateUtil.formatDateTime(DateUtil.beginOfYear(baseDate));
            end = DateUtil.formatDateTime(DateUtil.endOfYear(baseDate));
        } else {
            int month = (quarter - 1) * 3;
            DateTime dateTime = DateUtil.offsetMonth(baseDate, month);
            begin = DateUtil.formatDateTime(DateUtil.beginOfQuarter(dateTime));
            end = DateUtil.formatDateTime(DateUtil.endOfQuarter(dateTime));
        }
        String username = SecurityUtils.getUsername();

        startPage();
        req.setUserName(username);
        req.setBegin(begin);
        req.setEnd(end);
        List<TaskHandleVo.List4AssessResp> list = bizTaskHandleService.list4Assess(req);

        return getDataTable(list);
    }

}
