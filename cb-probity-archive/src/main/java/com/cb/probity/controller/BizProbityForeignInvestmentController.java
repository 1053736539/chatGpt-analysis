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
import com.cb.probity.domain.BizProbityForeignInvestment;
import com.cb.probity.service.IBizProbityForeignInvestmentService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况Controller
 *
 * @author ruoyi
 * @date 2025-03-18
 */
@RestController
@RequestMapping("/probity/probityForeignInvestment")
public class BizProbityForeignInvestmentController extends BaseController {
    @Autowired
    private IBizProbityForeignInvestmentService bizProbityForeignInvestmentService;

    /**
     * 查询廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityForeignInvestment:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizProbityForeignInvestment bizProbityForeignInvestment) {
        startPage();
        List<BizProbityForeignInvestment> list = bizProbityForeignInvestmentService.selectBizProbityForeignInvestmentList(bizProbityForeignInvestment);
        return getDataTable(list);
    }

    /**
     * 导出廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityForeignInvestment:export')")
    @Log(title = "廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizProbityForeignInvestment bizProbityForeignInvestment) {
        List<BizProbityForeignInvestment> list = bizProbityForeignInvestmentService.selectBizProbityForeignInvestmentList(bizProbityForeignInvestment);
        ExcelUtil<BizProbityForeignInvestment> util = new ExcelUtil<BizProbityForeignInvestment>(BizProbityForeignInvestment.class);
        return util.exportExcel(list, "probityForeignInvestment");
    }

    /**
     * 获取廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('probity:probityForeignInvestment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizProbityForeignInvestmentService.selectBizProbityForeignInvestmentById(id));
    }

    /**
     * 新增廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityForeignInvestment:add')")
    @Log(title = "廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizProbityForeignInvestment bizProbityForeignInvestment) {
        return toAjax(bizProbityForeignInvestmentService.insertBizProbityForeignInvestment(bizProbityForeignInvestment));
    }

    /**
     * 修改廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityForeignInvestment:edit')")
    @Log(title = "廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizProbityForeignInvestment bizProbityForeignInvestment) {
        return toAjax(bizProbityForeignInvestmentService.updateBizProbityForeignInvestment(bizProbityForeignInvestment));
    }

    /**
     * 删除廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityForeignInvestment:remove')")
    @Log(title = "廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizProbityForeignInvestmentService.deleteBizProbityForeignInvestmentByIds(ids));
    }
}
