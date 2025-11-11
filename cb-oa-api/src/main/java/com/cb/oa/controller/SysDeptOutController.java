package com.cb.oa.controller;

import java.util.List;

import com.cb.oa.domain.SysDeptOut;
import com.cb.oa.service.ISysDeptOutService;
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
 * oa系统的部门列Controller
 * 
 * @author ruoyi
 * @date 2023-12-01
 */
@RestController
@RequestMapping("/system/deptOut")
public class SysDeptOutController extends BaseController
{
    @Autowired
    private ISysDeptOutService sysDeptOutService;

    /**
     * 查询oa系统的部门列列表
     */
    //@PreAuthorize("@ss.hasPermi('system:deptOut:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysDeptOut sysDeptOut)
    {
        startPage();
        List<SysDeptOut> list = sysDeptOutService.selectSysDeptOutList(sysDeptOut);
        return getDataTable(list);
    }
    @GetMapping("/syncSysDeptOutList")
    public AjaxResult syncSysDeptOutList()
    {
        sysDeptOutService.syncSysDeptOutList();
        return success();
    }

    /**
     * 导出oa系统的部门列列表
     */
    //@PreAuthorize("@ss.hasPermi('system:deptOut:export')")
    @Log(title = "oa系统的部门列", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysDeptOut sysDeptOut)
    {
        List<SysDeptOut> list = sysDeptOutService.selectSysDeptOutList(sysDeptOut);
        ExcelUtil<SysDeptOut> util = new ExcelUtil<SysDeptOut>(SysDeptOut.class);
        return util.exportExcel(list, "out");
    }

    /**
     * 获取oa系统的部门列详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:deptOut:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysDeptOutService.selectSysDeptOutById(id));
    }

    /**
     * 新增oa系统的部门列
     */
    //@PreAuthorize("@ss.hasPermi('system:deptOut:add')")
    @Log(title = "oa系统的部门列", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysDeptOut sysDeptOut)
    {
        return toAjax(sysDeptOutService.insertSysDeptOut(sysDeptOut));
    }

    /**
     * 修改oa系统的部门列
     */
    //@PreAuthorize("@ss.hasPermi('system:deptOut:edit')")
    @Log(title = "oa系统的部门列", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysDeptOut sysDeptOut)
    {
        return toAjax(sysDeptOutService.updateSysDeptOut(sysDeptOut));
    }

    /**
     * 删除oa系统的部门列
     */
    //@PreAuthorize("@ss.hasPermi('system:deptOut:remove')")
    @Log(title = "oa系统的部门列", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(sysDeptOutService.deleteSysDeptOutByIds(ids));
    }
}
