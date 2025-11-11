package com.cb.task.controller;

import cn.hutool.core.date.DateUtil;
import com.cb.common.annotation.Log;
import com.cb.common.annotation.RepeatSubmit;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.task.domain.BizTaskInfo;
import com.cb.task.service.IBizTaskInfoService;
import com.cb.task.vo.TaskInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 任务信息Controller
 * 
 * @author yangxin
 * @date 2023-10-30
 */
@RestController
@RequestMapping("/task/taskInfo")
public class BizTaskInfoController extends BaseController
{
    @Autowired
    private IBizTaskInfoService bizTaskInfoService;

    /**
     * 查询任务信息列表
     */
    @PreAuthorize("@ss.hasPermi('task:taskInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizTaskInfo bizTaskInfo)
    {
        startPage();
        List<BizTaskInfo> list = bizTaskInfoService.selectBizTaskInfoList(bizTaskInfo);
        return getDataTable(list);
    }

    /**
     * 查询我创建的任务列表
     */
    @PreAuthorize("@ss.hasPermi('task:taskInfo:list')")
    @GetMapping("/listMyCreate")
    public TableDataInfo listMyCreate(BizTaskInfo bizTaskInfo)
    {
        String username = SecurityUtils.getUsername();
        bizTaskInfo.setCreateBy(username);
        startPage();
        List<BizTaskInfo> list = bizTaskInfoService.selectBizTaskInfoList(bizTaskInfo);
        return getDataTable(list);
    }

    /**
     * 查询部门的任务（待办/超时/已完结）
     * @param bizTaskInfo
     * @param expireQuery 是否查询超时任务
     * @return
     */
    @PreAuthorize("@ss.hasPermi('task:taskInfo:list')")
    @GetMapping("/listByDept")
    public TableDataInfo listByDept(BizTaskInfo bizTaskInfo, boolean expireQuery)
    {
        Long deptId = SecurityUtils.getOnlineDept().getDeptId();
        bizTaskInfo.setCreateDept(deptId);
        startPage();
        List<BizTaskInfo> list = bizTaskInfoService.listByDept(bizTaskInfo, expireQuery);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('task:taskInfo:list')")
    @GetMapping("/listMyAudit")
    public TableDataInfo listMyAudit(BizTaskInfo bizTaskInfo){
        String username = SecurityUtils.getUsername();
        //TODO 各部门审核自己的,当前用户是领导时查询自己部门下待审核的
        Long leaderDeptId = null;

        startPage();
        List<BizTaskInfo> list = bizTaskInfoService.listMyAudit(bizTaskInfo,leaderDeptId);
        return getDataTable(list);
    }

    /**
     * 导出任务信息列表
     */
    @PreAuthorize("@ss.hasPermi('task:taskInfo:export')")
    @Log(title = "任务信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizTaskInfo bizTaskInfo)
    {
        String username = SecurityUtils.getUsername();
        if(!"admin".equals(username)){
            bizTaskInfo.setCreateBy(username);
        }
        List<BizTaskInfo> list = bizTaskInfoService.selectBizTaskInfoList(bizTaskInfo);
        ExcelUtil<BizTaskInfo> util = new ExcelUtil<BizTaskInfo>(BizTaskInfo.class);
        return util.exportExcel(list, "taskInfo");
    }

    /**
     * 获取任务信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('task:taskInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(bizTaskInfoService.selectBizTaskInfoById(id));
    }

    /**
     * 新增任务信息
     */
    @PreAuthorize("@ss.hasPermi('task:taskInfo:add')")
    @Log(title = "任务信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizTaskInfo bizTaskInfo)
    {
        return toAjax(bizTaskInfoService.insertBizTaskInfo(bizTaskInfo));
    }

    /**
     * 修改任务信息
     */
    @PreAuthorize("@ss.hasPermi('task:taskInfo:edit')")
    @Log(title = "任务信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizTaskInfo bizTaskInfo)
    {
        return toAjax(bizTaskInfoService.updateBizTaskInfo(bizTaskInfo));
    }

    /**
     * 删除任务信息
     */
    @PreAuthorize("@ss.hasPermi('task:taskInfo:remove')")
    @Log(title = "任务信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bizTaskInfoService.deleteBizTaskInfoByIds(ids));
    }

    /**
     * OA交办
     * @param req
     * @return
     */
    @PostMapping("fromOA")
    public AjaxResult fromOA(@Valid @RequestBody TaskInfoVo.FormOAReq req){
        try{
            String assignTime = req.getAssignTime();
            if(StringUtils.isBlank(assignTime)){
                req.setAssignTime(DateUtil.now());
            } else {
                if(assignTime.length() > 19){
                    req.setAssignTime(assignTime.substring(0,19));
                } else {
                    String s = DateUtil.formatDateTime(DateUtil.parse(assignTime));
                    req.setAssignTime(s);
                }
            }
            String registerTime = req.getRegisterTime();
            if(StringUtils.isBlank(registerTime)){
                req.setRegisterTime(DateUtil.now());
            } else {
                if(registerTime.length() > 19){
                    req.setRegisterTime(registerTime.substring(0,19));
                } else {
                    String s = DateUtil.formatDateTime(DateUtil.parse(assignTime));
                    req.setRegisterTime(s);
                }
            }

            bizTaskInfoService.fromOA(req);
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }

        return AjaxResult.success("任务交办成功");
    }

    /**
     * 创建任务
     * @param req
     * @return
     */
    @PostMapping("create")
    @RepeatSubmit
    public AjaxResult create(@Valid @RequestBody TaskInfoVo.CreateReq req){
        bizTaskInfoService.create(req);
        return AjaxResult.success("任务创建成功");
    }

    /**
     * 审核
     * @param req
     * @return
     */
    @PostMapping("audit")
    public AjaxResult audit(@Valid @RequestBody TaskInfoVo.AuditReq req){
        bizTaskInfoService.audit(req);
        return AjaxResult.success("审核成功");
    }

    /**
     * 任务详情处进度更新
     * @param req
     * @return
     */
    @PostMapping("submitComment")
    public AjaxResult submitComment(@Valid @RequestBody TaskInfoVo.SubmitCommentReq req){
        bizTaskInfoService.submitComment(req);
        return AjaxResult.success("进度更新成功");
    }

    /**
     * 办结
     * @param taskId
     * @return
     */
    @PostMapping("/complete/{taskId}")
    public AjaxResult complete(@PathVariable("taskId") String taskId){
        bizTaskInfoService.complete(taskId);
        return AjaxResult.success("办结成功");
    }

    /**
     * 获取用于评价参考的任务处理情况信息
     * @param req
     * @return
     */
    @PostMapping("getUserHandle4Evaluation")
    public AjaxResult getUserHandle4Evaluation(@Valid @RequestBody TaskInfoVo.UserHandle4EvaluationReq req){
        TaskInfoVo.UserHandle4EvaluationResp result = bizTaskInfoService.getUserHandle4Evaluation(req);
        return AjaxResult.success(result);
    }

}
