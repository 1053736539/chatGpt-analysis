package com.cb.web.controller.system;

import java.util.List;
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
import com.cb.system.domain.SysUserSupervisor;
import com.cb.system.service.ISysUserSupervisorService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 用户负责人关系Controller
 * 
 * @author yangcd
 * @date 2024-09-12
 */
@RestController
@RequestMapping("/system/supervisor")
public class SysUserSupervisorController extends BaseController
{
    @Autowired
    private ISysUserSupervisorService sysUserSupervisorService;

    /**
     * 查询用户负责人关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:supervisor:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUserSupervisor sysUserSupervisor)
    {
        startPage();
        List<SysUserSupervisor> list = sysUserSupervisorService.selectSysUserSupervisorList(sysUserSupervisor);
        return getDataTable(list);
    }

    /**
     * 导出用户负责人关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:supervisor:export')")
    @Log(title = "用户负责人关系", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysUserSupervisor sysUserSupervisor)
    {
        List<SysUserSupervisor> list = sysUserSupervisorService.selectSysUserSupervisorList(sysUserSupervisor);
        ExcelUtil<SysUserSupervisor> util = new ExcelUtil<SysUserSupervisor>(SysUserSupervisor.class);
        return util.exportExcel(list, "supervisor");
    }

    /**
     * 获取用户负责人关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:supervisor:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Integer userId)
    {
        return AjaxResult.success(sysUserSupervisorService.selectSysUserSupervisorById(userId));
    }

    /**
     * 新增用户负责人关系
     */
    @PreAuthorize("@ss.hasPermi('system:supervisor:add')")
    @Log(title = "用户负责人关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUserSupervisor sysUserSupervisor)
    {
        return toAjax(sysUserSupervisorService.insertSysUserSupervisor(sysUserSupervisor));
    }

    /**
     * 修改用户负责人关系
     */
    @PreAuthorize("@ss.hasPermi('system:supervisor:edit')")
    @Log(title = "用户负责人关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUserSupervisor sysUserSupervisor)
    {
        return toAjax(sysUserSupervisorService.updateSysUserSupervisor(sysUserSupervisor));
    }

    /**
     * 删除用户负责人关系
     */
    @PreAuthorize("@ss.hasPermi('system:supervisor:remove')")
    @Log(title = "用户负责人关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(sysUserSupervisorService.deleteSysUserSupervisorByIds(ids));
    }
}
