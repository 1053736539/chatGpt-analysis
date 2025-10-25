package com.cb.web.controller.system;

import java.util.List;

import com.cb.common.core.domain.entity.SysUserEmploy;
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
import com.cb.system.service.ISysUserEmployService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 任免和聘用信息Controller
 *
 * @author ruoyi
 * @date 2023-11-09
 */
@RestController
@RequestMapping("/system/employ")
public class SysUserEmployController extends BaseController
{
    @Autowired
    private ISysUserEmployService sysUserEmployService;

    /**
     * 查询任免和聘用信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysUserEmploy sysUserEmploy)
    {
        startPage();
        List<SysUserEmploy> list = sysUserEmployService.selectSysUserEmployList(sysUserEmploy);
        return getDataTable(list);
    }

    /**
     * 导出任免和聘用信息列表
     */
    @Log(title = "任免和聘用信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysUserEmploy sysUserEmploy)
    {
        List<SysUserEmploy> list = sysUserEmployService.selectSysUserEmployList(sysUserEmploy);
        ExcelUtil<SysUserEmploy> util = new ExcelUtil<SysUserEmploy>(SysUserEmploy.class);
        return util.exportExcel(list, "employ");
    }

    /**
     * 获取任免和聘用信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysUserEmployService.selectSysUserEmployById(id));
    }

    /**
     * 新增任免和聘用信息
     */
    @Log(title = "任免和聘用信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUserEmploy sysUserEmploy)
    {
        return toAjax(sysUserEmployService.insertSysUserEmploy(sysUserEmploy));
    }

    /**
     * 修改任免和聘用信息
     */
    @Log(title = "任免和聘用信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUserEmploy sysUserEmploy)
    {
        return toAjax(sysUserEmployService.updateSysUserEmploy(sysUserEmploy));
    }

    /**
     * 删除任免和聘用信息
     */
    @Log(title = "任免和聘用信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(sysUserEmployService.deleteSysUserEmployByIds(ids));
    }

    @Log(title = "卸任职务", businessType = BusinessType.UPDATE)
	@PostMapping("/disable/{id}")
    public AjaxResult disable(@PathVariable String id)
    {
        return toAjax(sysUserEmployService.disableEmployById(id));
    }
}
