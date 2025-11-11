package com.cb.task.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.task.domain.BizTaskDocType;
import com.cb.task.service.IBizTaskDocTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 任务公文类型Controller
 * 
 * @author yangxin
 * @date 2023-11-11
 */
@RestController
@RequestMapping("/task/taskDocType")
public class BizTaskDocTypeController extends BaseController
{
    @Autowired
    private IBizTaskDocTypeService bizTaskDocTypeService;

    /**
     * 查询任务公文类型列表
     */
    @PreAuthorize("@ss.hasPermi('task:taskDocType:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizTaskDocType bizTaskDocType)
    {
        startPage();
        List<BizTaskDocType> list = bizTaskDocTypeService.selectBizTaskDocTypeList(bizTaskDocType);
        return getDataTable(list);
    }

    /**
     * 查询我可见的任务公文类型列表
     */
    @PreAuthorize("@ss.hasPermi('task:taskDocType:list')")
    @GetMapping("/listMy")
    public TableDataInfo listMy(BizTaskDocType bizTaskDocType){
        Long deptId = SecurityUtils.getOnlineDept().getDeptId();

        startPage();
        List<BizTaskDocType> list = bizTaskDocTypeService.listMy(bizTaskDocType,deptId);
        return getDataTable(list);
    }

    /**
     * 查询所有类型
     * @return
     */
    @GetMapping("/listAll")
    public AjaxResult listAll(){
        BizTaskDocType bizTaskDocType = new BizTaskDocType();
        List<BizTaskDocType> list = bizTaskDocTypeService.selectBizTaskDocTypeList(bizTaskDocType);
        return AjaxResult.success(list);
    }

    /**
     * 导出任务公文类型列表
     */
    @PreAuthorize("@ss.hasPermi('task:taskDocType:export')")
    @Log(title = "任务公文类型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizTaskDocType bizTaskDocType)
    {
        List<BizTaskDocType> list = bizTaskDocTypeService.selectBizTaskDocTypeList(bizTaskDocType);
        ExcelUtil<BizTaskDocType> util = new ExcelUtil<BizTaskDocType>(BizTaskDocType.class);
        return util.exportExcel(list, "taskDocType");
    }

    /**
     * 获取任务公文类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('task:taskDocType:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bizTaskDocTypeService.selectBizTaskDocTypeById(id));
    }

    /**
     * 新增任务公文类型
     */
    @PreAuthorize("@ss.hasPermi('task:taskDocType:add')")
    @Log(title = "任务公文类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizTaskDocType bizTaskDocType)
    {
        return toAjax(bizTaskDocTypeService.insertBizTaskDocType(bizTaskDocType));
    }

    /**
     * 部门新增任务公文类型
     */
    @PreAuthorize("@ss.hasPermi('task:taskDocType:add')")
    @Log(title = "任务公文类型", businessType = BusinessType.INSERT)
    @PostMapping("addByDept")
    public AjaxResult addByDept(@RequestBody BizTaskDocType bizTaskDocType)
    {
        bizTaskDocType.setModuleType("dept");
        Long deptId = SecurityUtils.getOnlineDept().getDeptId();
        bizTaskDocType.setDeptId(deptId);
        bizTaskDocType.setCode(IdUtils.randomUUID());
        return toAjax(bizTaskDocTypeService.insertBizTaskDocType(bizTaskDocType));
    }

    /**
     * 修改任务公文类型
     */
    @PreAuthorize("@ss.hasPermi('task:taskDocType:edit')")
    @Log(title = "任务公文类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizTaskDocType bizTaskDocType)
    {
        return toAjax(bizTaskDocTypeService.updateBizTaskDocType(bizTaskDocType));
    }

    /**
     * 删除任务公文类型
     */
    @PreAuthorize("@ss.hasPermi('task:taskDocType:remove')")
    @Log(title = "任务公文类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bizTaskDocTypeService.deleteBizTaskDocTypeByIds(ids));
    }
}
