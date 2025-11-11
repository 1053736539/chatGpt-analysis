package com.cb.probity.controller;

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
import com.cb.probity.domain.BizProbityGoAbroad;
import com.cb.probity.service.IBizProbityGoAbroadService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 廉政档案-6.2本人因私出国（境）及往来港澳台情况Controller
 *
 * @author ruoyi
 * @date 2025-03-18
 */
@RestController
@RequestMapping("/probity/probityGoAbroad")
public class BizProbityGoAbroadController extends BaseController {
    @Autowired
    private IBizProbityGoAbroadService bizProbityGoAbroadService;

    /**
     * 查询廉政档案-6.2本人因私出国（境）及往来港澳台情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityGoAbroad:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizProbityGoAbroad bizProbityGoAbroad) {
        startPage();
        List<BizProbityGoAbroad> list = bizProbityGoAbroadService.selectBizProbityGoAbroadList(bizProbityGoAbroad);
        return getDataTable(list);
    }

    /**
     * 导出廉政档案-6.2本人因私出国（境）及往来港澳台情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityGoAbroad:export')")
    @Log(title = "廉政档案-6.2本人因私出国（境）及往来港澳台情况", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizProbityGoAbroad bizProbityGoAbroad) {
        List<BizProbityGoAbroad> list = bizProbityGoAbroadService.selectBizProbityGoAbroadList(bizProbityGoAbroad);
        ExcelUtil<BizProbityGoAbroad> util = new ExcelUtil<BizProbityGoAbroad>(BizProbityGoAbroad.class);
        return util.exportExcel(list, "probityGoAbroad");
    }

    /**
     * 获取廉政档案-6.2本人因私出国（境）及往来港澳台情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('probity:probityGoAbroad:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizProbityGoAbroadService.selectBizProbityGoAbroadById(id));
    }

    /**
     * 新增廉政档案-6.2本人因私出国（境）及往来港澳台情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityGoAbroad:add')")
    @Log(title = "廉政档案-6.2本人因私出国（境）及往来港澳台情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizProbityGoAbroad bizProbityGoAbroad) {
        return toAjax(bizProbityGoAbroadService.insertBizProbityGoAbroad(bizProbityGoAbroad));
    }

    /**
     * 修改廉政档案-6.2本人因私出国（境）及往来港澳台情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityGoAbroad:edit')")
    @Log(title = "廉政档案-6.2本人因私出国（境）及往来港澳台情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizProbityGoAbroad bizProbityGoAbroad) {
        return toAjax(bizProbityGoAbroadService.updateBizProbityGoAbroad(bizProbityGoAbroad));
    }

    /**
     * 删除廉政档案-6.2本人因私出国（境）及往来港澳台情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityGoAbroad:remove')")
    @Log(title = "廉政档案-6.2本人因私出国（境）及往来港澳台情况", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizProbityGoAbroadService.deleteBizProbityGoAbroadByIds(ids));
    }
}
