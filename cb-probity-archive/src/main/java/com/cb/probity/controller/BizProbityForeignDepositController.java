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
import com.cb.probity.domain.BizProbityForeignDeposit;
import com.cb.probity.service.IBizProbityForeignDepositService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况Controller
 *
 * @author ruoyi
 * @date 2025-03-18
 */
@RestController
@RequestMapping("/probity/probityForeignDeposit")
public class BizProbityForeignDepositController extends BaseController {
    @Autowired
    private IBizProbityForeignDepositService bizProbityForeignDepositService;

    /**
     * 查询廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityForeignDeposit:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizProbityForeignDeposit bizProbityForeignDeposit) {
        startPage();
        List<BizProbityForeignDeposit> list = bizProbityForeignDepositService.selectBizProbityForeignDepositList(bizProbityForeignDeposit);
        return getDataTable(list);
    }

    /**
     * 导出廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityForeignDeposit:export')")
    @Log(title = "廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizProbityForeignDeposit bizProbityForeignDeposit) {
        List<BizProbityForeignDeposit> list = bizProbityForeignDepositService.selectBizProbityForeignDepositList(bizProbityForeignDeposit);
        ExcelUtil<BizProbityForeignDeposit> util = new ExcelUtil<BizProbityForeignDeposit>(BizProbityForeignDeposit.class);
        return util.exportExcel(list, "probityForeignDeposit");
    }

    /**
     * 获取廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('probity:probityForeignDeposit:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizProbityForeignDepositService.selectBizProbityForeignDepositById(id));
    }

    /**
     * 新增廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityForeignDeposit:add')")
    @Log(title = "廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizProbityForeignDeposit bizProbityForeignDeposit) {
        return toAjax(bizProbityForeignDepositService.insertBizProbityForeignDeposit(bizProbityForeignDeposit));
    }

    /**
     * 修改廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityForeignDeposit:edit')")
    @Log(title = "廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizProbityForeignDeposit bizProbityForeignDeposit) {
        return toAjax(bizProbityForeignDepositService.updateBizProbityForeignDeposit(bizProbityForeignDeposit));
    }

    /**
     * 删除廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityForeignDeposit:remove')")
    @Log(title = "廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizProbityForeignDepositService.deleteBizProbityForeignDepositByIds(ids));
    }
}
