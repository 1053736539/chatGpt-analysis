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
import com.cb.probity.domain.BizProbityStockItem;
import com.cb.probity.service.IBizProbityStockItemService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 廉政档案-13.2持有股票记录信息项Controller
 *
 * @author ruoyi
 * @date 2025-03-18
 */
@RestController
@RequestMapping("/probity/probityStockItem")
public class BizProbityStockItemController extends BaseController {
    @Autowired
    private IBizProbityStockItemService bizProbityStockItemService;

    /**
     * 查询廉政档案-13.2持有股票记录信息项列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityStockItem:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizProbityStockItem bizProbityStockItem) {
        startPage();
        List<BizProbityStockItem> list = bizProbityStockItemService.selectBizProbityStockItemList(bizProbityStockItem);
        return getDataTable(list);
    }

    /**
     * 导出廉政档案-13.2持有股票记录信息项列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityStockItem:export')")
    @Log(title = "廉政档案-13.2持有股票记录信息项", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizProbityStockItem bizProbityStockItem) {
        List<BizProbityStockItem> list = bizProbityStockItemService.selectBizProbityStockItemList(bizProbityStockItem);
        ExcelUtil<BizProbityStockItem> util = new ExcelUtil<BizProbityStockItem>(BizProbityStockItem.class);
        return util.exportExcel(list, "probityStockItem");
    }

    /**
     * 获取廉政档案-13.2持有股票记录信息项详细信息
     */
    @PreAuthorize("@ss.hasPermi('probity:probityStockItem:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizProbityStockItemService.selectBizProbityStockItemById(id));
    }

    /**
     * 新增廉政档案-13.2持有股票记录信息项
     */
    @PreAuthorize("@ss.hasPermi('probity:probityStockItem:add')")
    @Log(title = "廉政档案-13.2持有股票记录信息项", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizProbityStockItem bizProbityStockItem) {
        return toAjax(bizProbityStockItemService.insertBizProbityStockItem(bizProbityStockItem));
    }

    /**
     * 修改廉政档案-13.2持有股票记录信息项
     */
    @PreAuthorize("@ss.hasPermi('probity:probityStockItem:edit')")
    @Log(title = "廉政档案-13.2持有股票记录信息项", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizProbityStockItem bizProbityStockItem) {
        return toAjax(bizProbityStockItemService.updateBizProbityStockItem(bizProbityStockItem));
    }

    /**
     * 删除廉政档案-13.2持有股票记录信息项
     */
    @PreAuthorize("@ss.hasPermi('probity:probityStockItem:remove')")
    @Log(title = "廉政档案-13.2持有股票记录信息项", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizProbityStockItemService.deleteBizProbityStockItemByIds(ids));
    }
}
