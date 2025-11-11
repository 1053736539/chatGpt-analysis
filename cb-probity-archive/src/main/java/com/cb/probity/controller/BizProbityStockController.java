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
import com.cb.probity.domain.BizProbityStock;
import com.cb.probity.service.IBizProbityStockService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况Controller
 *
 * @author ruoyi
 * @date 2025-03-18
 */
@RestController
@RequestMapping("/probity/probityStock")
public class BizProbityStockController extends BaseController {
    @Autowired
    private IBizProbityStockService bizProbityStockService;

    /**
     * 查询廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityStock:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizProbityStock bizProbityStock) {
        startPage();
        List<BizProbityStock> list = bizProbityStockService.selectBizProbityStockList(bizProbityStock);
        return getDataTable(list);
    }

    /**
     * 导出廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityStock:export')")
    @Log(title = "廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizProbityStock bizProbityStock) {
        List<BizProbityStock> list = bizProbityStockService.selectBizProbityStockList(bizProbityStock);
        ExcelUtil<BizProbityStock> util = new ExcelUtil<BizProbityStock>(BizProbityStock.class);
        return util.exportExcel(list, "probityStock");
    }

    /**
     * 获取廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('probity:probityStock:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizProbityStockService.selectBizProbityStockById(id));
    }

    /**
     * 新增廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityStock:add')")
    @Log(title = "廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizProbityStock bizProbityStock) {
        return toAjax(bizProbityStockService.insertBizProbityStock(bizProbityStock));
    }

    /**
     * 修改廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityStock:edit')")
    @Log(title = "廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizProbityStock bizProbityStock) {
        return toAjax(bizProbityStockService.updateBizProbityStock(bizProbityStock));
    }

    /**
     * 删除廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityStock:remove')")
    @Log(title = "廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizProbityStockService.deleteBizProbityStockByIds(ids));
    }
}
