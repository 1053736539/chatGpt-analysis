package com.cb.conductInfo.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.conductInfo.domain.ConductInfoDetail;
import com.cb.conductInfo.service.IConductInfoDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 普查员宣传资料库-详情Controller
 * 
 * @author yangxin
 * @date 2023-10-20
 */
@RestController
@RequestMapping("/census/conductInfoDetail")
public class ConductInfoDetailController extends BaseController
{
    @Autowired
    private IConductInfoDetailService conductInfoDetailService;

    /**
     * 查询普查员宣传资料库-详情列表
     */
    @PreAuthorize("@ss.hasPermi('conductInfo:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(ConductInfoDetail conductInfoDetail)
    {
        startPage();
        List<ConductInfoDetail> list = conductInfoDetailService.selectConductInfoDetailList(conductInfoDetail);
        return getDataTable(list);
    }

    /**
     * 导出普查员宣传资料库-详情列表
     */
    @PreAuthorize("@ss.hasPermi('conductInfo:detail:export')")
    @Log(title = "普查员宣传资料库-详情", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ConductInfoDetail conductInfoDetail)
    {
        List<ConductInfoDetail> list = conductInfoDetailService.selectConductInfoDetailList(conductInfoDetail);
        ExcelUtil<ConductInfoDetail> util = new ExcelUtil<ConductInfoDetail>(ConductInfoDetail.class);
        return util.exportExcel(list, "detail");
    }

    /**
     * 获取普查员宣传资料库-详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('conductInfo:detail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(conductInfoDetailService.selectConductInfoDetailById(id));
    }

    /**
     * 新增普查员宣传资料库-详情
     */
    @PreAuthorize("@ss.hasPermi('conductInfo:detail:add')")
    @Log(title = "普查员宣传资料库-详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ConductInfoDetail conductInfoDetail)
    {
        return toAjax(conductInfoDetailService.insertConductInfoDetail(conductInfoDetail));
    }

    /**
     * 修改普查员宣传资料库-详情
     */
    @PreAuthorize("@ss.hasPermi('conductInfo:detail:edit')")
    @Log(title = "普查员宣传资料库-详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ConductInfoDetail conductInfoDetail)
    {
        return toAjax(conductInfoDetailService.updateConductInfoDetail(conductInfoDetail));
    }

    /**
     * 删除普查员宣传资料库-详情
     */
    @PreAuthorize("@ss.hasPermi('conductInfo:detail:remove')")
    @Log(title = "普查员宣传资料库-详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(conductInfoDetailService.deleteConductInfoDetailByIds(ids));
    }
}
