package com.cb.web.controller.system;

import java.util.List;

import com.cb.system.service.ISysUserAbilityLabelService;
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
import com.cb.common.core.domain.entity.SysUserAbilityLabel;

import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 干部能力标签Controller
 * 
 * @author ruoyi
 * @date 2025-02-22
 */
@RestController
@RequestMapping("/system/abilityLabel")
public class SysUserAbilityLabelController extends BaseController
{
    @Autowired
    private ISysUserAbilityLabelService sysUserAbilityLabelService;

    /**
     * 查询干部能力标签列表
     */
    @PreAuthorize("@ss.hasPermi('system:abilityLabel:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUserAbilityLabel sysUserAbilityLabel)
    {
        startPage();
        List<SysUserAbilityLabel> list = sysUserAbilityLabelService.selectSysUserAbilityLabelList(sysUserAbilityLabel);
        return getDataTable(list);
    }

    /**
     * 查询干部能力标签下拉列表
     */
//    @PreAuthorize("@ss.hasPermi('system:abilityLabel:list')")
    @GetMapping("/abilityLabelList")
    public AjaxResult abilityLabelList(SysUserAbilityLabel sysUserAbilityLabel)
    {
        List<SysUserAbilityLabel> list = sysUserAbilityLabelService.selectAbilityLabelList(sysUserAbilityLabel);
        return AjaxResult.success(list);
    }

    /**
     * 导出干部能力标签列表
     */
    @PreAuthorize("@ss.hasPermi('system:abilityLabel:export')")
    @Log(title = "干部能力标签", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysUserAbilityLabel sysUserAbilityLabel)
    {
        List<SysUserAbilityLabel> list = sysUserAbilityLabelService.selectSysUserAbilityLabelList(sysUserAbilityLabel);
        ExcelUtil<SysUserAbilityLabel> util = new ExcelUtil<SysUserAbilityLabel>(SysUserAbilityLabel.class);
        return util.exportExcel(list, "abilityLabel");
    }

    /**
     * 获取干部能力标签详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:abilityLabel:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(sysUserAbilityLabelService.selectSysUserAbilityLabelById(id));
    }

    /**
     * 新增干部能力标签
     */
    @PreAuthorize("@ss.hasPermi('system:abilityLabel:add')")
    @Log(title = "干部能力标签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUserAbilityLabel sysUserAbilityLabel)
    {
        return toAjax(sysUserAbilityLabelService.insertSysUserAbilityLabel(sysUserAbilityLabel));
    }

    /**
     * 修改干部能力标签
     */
    @PreAuthorize("@ss.hasPermi('system:abilityLabel:edit')")
    @Log(title = "干部能力标签", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUserAbilityLabel sysUserAbilityLabel)
    {
        return toAjax(sysUserAbilityLabelService.updateSysUserAbilityLabel(sysUserAbilityLabel));
    }

    /**
     * 删除干部能力标签
     */
    @PreAuthorize("@ss.hasPermi('system:abilityLabel:remove')")
    @Log(title = "干部能力标签", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(sysUserAbilityLabelService.deleteSysUserAbilityLabelByIds(ids));
    }
}
