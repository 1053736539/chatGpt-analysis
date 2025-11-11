package com.cb.conductInfo.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.conductInfo.domain.ConductInfoType;
import com.cb.conductInfo.service.IConductInfoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 普查员宣传资料库-类型Controller
 * 
 * @author yangxin
 * @date 2023-10-20
 */
@RestController
@RequestMapping("/census/conductInfoType")
public class ConductInfoTypeController extends BaseController
{
    @Autowired
    private IConductInfoTypeService conductInfoTypeService;

    /**
     * 查询普查员宣传资料库-类型列表
     */
    @PreAuthorize("@ss.hasPermi('conductInfo:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(ConductInfoType conductInfoType)
    {
        startPage();
        List<ConductInfoType> list = conductInfoTypeService.selectConductInfoTypeList(conductInfoType);
        return getDataTable(list);
    }

    /**
     * 导出普查员宣传资料库-类型列表
     */
    @PreAuthorize("@ss.hasPermi('conductInfo:type:export')")
    @Log(title = "普查员宣传资料库-类型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ConductInfoType conductInfoType)
    {
        List<ConductInfoType> list = conductInfoTypeService.selectConductInfoTypeList(conductInfoType);
        ExcelUtil<ConductInfoType> util = new ExcelUtil<ConductInfoType>(ConductInfoType.class);
        return util.exportExcel(list, "type");
    }

    /**
     * 获取普查员宣传资料库-类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('conductInfo:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(conductInfoTypeService.selectConductInfoTypeById(id));
    }

    /**
     * 新增普查员宣传资料库-类型
     */
    @PreAuthorize("@ss.hasPermi('conductInfo:type:add')")
    @Log(title = "普查员宣传资料库-类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ConductInfoType conductInfoType)
    {
        return toAjax(conductInfoTypeService.insertConductInfoType(conductInfoType));
    }

    /**
     * 修改普查员宣传资料库-类型
     */
    @PreAuthorize("@ss.hasPermi('conductInfo:type:edit')")
    @Log(title = "普查员宣传资料库-类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ConductInfoType conductInfoType)
    {
        return toAjax(conductInfoTypeService.updateConductInfoType(conductInfoType));
    }

    /**
     * 删除普查员宣传资料库-类型
     */
    @PreAuthorize("@ss.hasPermi('conductInfo:type:remove')")
    @Log(title = "普查员宣传资料库-类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(conductInfoTypeService.deleteConductInfoTypeByIds(ids));
    }
}
