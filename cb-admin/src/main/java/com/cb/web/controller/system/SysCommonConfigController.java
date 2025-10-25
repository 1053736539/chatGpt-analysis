package com.cb.web.controller.system;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.system.domain.SysCommonConfig;
import com.cb.system.service.ISysCommonConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统配置Controller
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
@RestController
@RequestMapping("/system/commonConfig")
public class SysCommonConfigController extends BaseController
{
    @Autowired
    private ISysCommonConfigService commonConfigService;

    /**
     * 查询系统配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:commonConfig:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysCommonConfig config)
    {
        startPage();
        List<SysCommonConfig> list = commonConfigService.selectSysCommonConfigList(config);
        return getDataTable(list);
    }

    /**
     * 导出系统配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:commonConfig:export')")
    @Log(title = "系统配置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysCommonConfig config)
    {
        List<SysCommonConfig> list = commonConfigService.selectSysCommonConfigList(config);
        ExcelUtil<SysCommonConfig> util = new ExcelUtil<>(SysCommonConfig.class);
        return util.exportExcel(list, "config");
    }

    /**
     * 获取系统配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:commonConfig:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(commonConfigService.selectSysCommonConfigById(id));
    }

    /**
     * 新增系统配置
     */
    @PreAuthorize("@ss.hasPermi('system:commonConfig:add')")
    @Log(title = "系统配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCommonConfig config)
    {
        return toAjax(commonConfigService.insertSysCommonConfig(config));
    }
    /**
     * 修改系统配置
     */
    @PreAuthorize("@ss.hasPermi('system:commonConfig:edit')")
    @Log(title = "系统配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCommonConfig config)
    {
        return toAjax(commonConfigService.updateSysCommonConfig(config));
    }

    /**
     * 获取密码策略
     */
    @GetMapping("/getConfig")
    public AjaxResult getConfig() {
        SysCommonConfig config = commonConfigService.selectOneSysCommonConfigById();
        AjaxResult ajax = AjaxResult.success();
        ajax.put("config", config);
        return ajax;
    }
}
