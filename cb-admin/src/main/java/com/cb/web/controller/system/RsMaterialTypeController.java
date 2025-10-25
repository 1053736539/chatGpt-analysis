package com.cb.web.controller.system;

import java.util.List;

import com.cb.common.core.domain.entity.RsMaterialType;
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
import com.cb.system.service.IRsMaterialTypeService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 材料类型Controller
 * 
 * @author ruoyi
 * @date 2025-02-25
 */
@RestController
@RequestMapping("/system/rsMaterialType")
public class RsMaterialTypeController extends BaseController
{
    @Autowired
    private IRsMaterialTypeService rsMaterialTypeService;

    /**
     * 查询材料类型列表
     */
    @PreAuthorize("@ss.hasPermi('system:rsMaterialType:list')")
    @GetMapping("/list")
    public TableDataInfo list(RsMaterialType rsMaterialType)
    {
        startPage();
        List<RsMaterialType> list = rsMaterialTypeService.selectRsMaterialTypeList(rsMaterialType);
        return getDataTable(list);
    }

    /**
     * 导出材料类型列表
     */
    @PreAuthorize("@ss.hasPermi('system:rsMaterialType:export')")
    @Log(title = "材料类型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(RsMaterialType rsMaterialType)
    {
        List<RsMaterialType> list = rsMaterialTypeService.selectRsMaterialTypeList(rsMaterialType);
        ExcelUtil<RsMaterialType> util = new ExcelUtil<RsMaterialType>(RsMaterialType.class);
        return util.exportExcel(list, "rsMaterialType");
    }

    /**
     * 获取材料类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:rsMaterialType:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(rsMaterialTypeService.selectRsMaterialTypeById(id));
    }

    /**
     * 新增材料类型
     */
    @PreAuthorize("@ss.hasPermi('system:rsMaterialType:add')")
    @Log(title = "材料类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RsMaterialType rsMaterialType)
    {
        return toAjax(rsMaterialTypeService.insertRsMaterialType(rsMaterialType));
    }

    /**
     * 修改材料类型
     */
    @PreAuthorize("@ss.hasPermi('system:rsMaterialType:edit')")
    @Log(title = "材料类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RsMaterialType rsMaterialType)
    {
        return toAjax(rsMaterialTypeService.updateRsMaterialType(rsMaterialType));
    }

    /**
     * 删除材料类型
     */
    @PreAuthorize("@ss.hasPermi('system:rsMaterialType:remove')")
    @Log(title = "材料类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(rsMaterialTypeService.deleteRsMaterialTypeByIds(ids));
    }

    /**
     * 获取档案下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(RsMaterialType rsMaterialType)
    {
        List<RsMaterialType> rsMaterialTypes = rsMaterialTypeService.selectRsMaterialTypeParentList(rsMaterialType);
        return AjaxResult.success(rsMaterialTypeService.buildMaterialTypeTreeSelect(rsMaterialTypes));
    }
}
