package com.cb.assess.controller;

import com.cb.assess.domain.BizAssessCadreDemocraticTask;
import com.cb.assess.service.IBizAssessCadreDemocraticTaskService;
import com.cb.assess.vo.CadreDemocraticTaskVo;
import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 年度处级干部民主测评任务Controller
 * 
 * @author yangixn
 * @date 2023-11-25
 */
@RestController
@RequestMapping("/assess/cadreDemocraticTask")
public class BizAssessCadreDemocraticTaskController extends BaseController
{
    @Autowired
    private IBizAssessCadreDemocraticTaskService bizAssessCadreDemocraticTaskService;

    /**
     * 查询年度处级干部民主测评任务列表
     */
//    @PreAuthorize("@ss.hasPermi('assess:cadreDemocraticTask:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizAssessCadreDemocraticTask bizAssessCadreDemocraticTask)
    {
        startPage();
        List<BizAssessCadreDemocraticTask> list = bizAssessCadreDemocraticTaskService.selectBizAssessCadreDemocraticTaskList(bizAssessCadreDemocraticTask);
        return getDataTable(list);
    }

    /**
     * 查询当前用户创建的任务列表
     * @param bizAssessCadreDemocraticTask
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('assess:cadreDemocraticTask:list')")
    @GetMapping("/listMy")
    public TableDataInfo listMy(BizAssessCadreDemocraticTask bizAssessCadreDemocraticTask)
    {

        SysDept sysDept = SecurityUtils.getOnlineDept();
        if(sysDept == null && null != sysDept.getDeptId()){
            //创建人为当前用户
            bizAssessCadreDemocraticTask.setCreateBy(SecurityUtils.getUsername());
        } else {
            bizAssessCadreDemocraticTask.setCreateDept(sysDept.getDeptId());
        }

        startPage();
        List<BizAssessCadreDemocraticTask> list = bizAssessCadreDemocraticTaskService.selectBizAssessCadreDemocraticTaskList(bizAssessCadreDemocraticTask);
        return getDataTable(list);
    }

    /**
     * 查询列表（人事处）
     * @param bizAssessCadreDemocraticTask
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('assess:cadreDemocraticTask:list')")
    @GetMapping("/listByRSC")
    public TableDataInfo listByRSC(BizAssessCadreDemocraticTask bizAssessCadreDemocraticTask)
    {
        //创建人为当前用户
        bizAssessCadreDemocraticTask.setReportFlag("1");
        startPage();
        List<BizAssessCadreDemocraticTask> list = bizAssessCadreDemocraticTaskService.selectBizAssessCadreDemocraticTaskList(bizAssessCadreDemocraticTask);
        return getDataTable(list);
    }

    /**
     * 导出年度处级干部民主测评任务列表
     */
//    @PreAuthorize("@ss.hasPermi('assess:cadreDemocraticTask:export')")
    @Log(title = "年度处级干部民主测评任务", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizAssessCadreDemocraticTask bizAssessCadreDemocraticTask)
    {
        List<BizAssessCadreDemocraticTask> list = bizAssessCadreDemocraticTaskService.selectBizAssessCadreDemocraticTaskList(bizAssessCadreDemocraticTask);
        ExcelUtil<BizAssessCadreDemocraticTask> util = new ExcelUtil<BizAssessCadreDemocraticTask>(BizAssessCadreDemocraticTask.class);
        return util.exportExcel(list, "cadreDemocraticTask");
    }

    /**
     * 获取年度处级干部民主测评任务详细信息
     */
//    @PreAuthorize("@ss.hasPermi('assess:cadreDemocraticTask:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(bizAssessCadreDemocraticTaskService.selectBizAssessCadreDemocraticTaskById(id));
    }

    /**
     * 导出word
     * @param taskId
     * @param userId
     */
    @GetMapping(value = "/exportWord")
    public void exportTaskInfoWord(HttpServletResponse response, @Valid @NotBlank String taskId, @Valid @NotNull Long userId){
        bizAssessCadreDemocraticTaskService.exportTaskInfoWord(response,taskId,userId);
    }

    /**
     * 新增年度处级干部民主测评任务
     */
//    @PreAuthorize("@ss.hasPermi('assess:cadreDemocraticTask:add')")
    @Log(title = "年度处级干部民主测评任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAssessCadreDemocraticTask bizAssessCadreDemocraticTask)
    {
        return toAjax(bizAssessCadreDemocraticTaskService.insertBizAssessCadreDemocraticTask(bizAssessCadreDemocraticTask));
    }

    /**
     * 修改年度处级干部民主测评任务
     */
//    @PreAuthorize("@ss.hasPermi('assess:cadreDemocraticTask:edit')")
    @Log(title = "年度处级干部民主测评任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAssessCadreDemocraticTask bizAssessCadreDemocraticTask)
    {
        return toAjax(bizAssessCadreDemocraticTaskService.updateBizAssessCadreDemocraticTask(bizAssessCadreDemocraticTask));
    }

    /**
     * 删除年度处级干部民主测评任务
     */
//    @PreAuthorize("@ss.hasPermi('assess:cadreDemocraticTask:remove')")
    @Log(title = "年度处级干部民主测评任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bizAssessCadreDemocraticTaskService.deleteBizAssessCadreDemocraticTaskByIds(ids));
    }

    /**
     * 获取年度评议处级干部民主评议的被评价人
     * @return
     */
    @PostMapping("getAssessedUserIds")
    public AjaxResult getAssessedUserIds(){
        return AjaxResult.success(bizAssessCadreDemocraticTaskService.getAssessedUserIds());
    }

    /**
     * 创建
     * @param req
     * @return
     */
    @PostMapping("create")
    public AjaxResult create(@Valid @RequestBody CadreDemocraticTaskVo.CreateReq req){
        bizAssessCadreDemocraticTaskService.create(req);
        return AjaxResult.success("创建成功");
    }

    /**
     * 上报到人事处
     * @param taskId
     * @return
     */
    @PostMapping("report2RSC/{taskId}")
    public AjaxResult report2RSC(@PathVariable("taskId") String taskId){
        bizAssessCadreDemocraticTaskService.report2RSC(taskId);
        return AjaxResult.success("提交成功");
    }

}
