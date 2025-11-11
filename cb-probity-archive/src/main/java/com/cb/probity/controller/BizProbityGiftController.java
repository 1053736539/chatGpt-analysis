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
import com.cb.probity.domain.BizProbityGift;
import com.cb.probity.service.IBizProbityGiftService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 廉政档案-3.本人拒收或上交礼金、礼品情况登记Controller
 *
 * @author ruoyi
 * @date 2025-03-18
 */
@RestController
@RequestMapping("/probity/probityGift")
public class BizProbityGiftController extends BaseController {
    @Autowired
    private IBizProbityGiftService bizProbityGiftService;

    /**
     * 查询廉政档案-3.本人拒收或上交礼金、礼品情况登记列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityGift:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizProbityGift bizProbityGift) {
        startPage();
        List<BizProbityGift> list = bizProbityGiftService.selectBizProbityGiftList(bizProbityGift);
        return getDataTable(list);
    }

    /**
     * 导出廉政档案-3.本人拒收或上交礼金、礼品情况登记列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityGift:export')")
    @Log(title = "廉政档案-3.本人拒收或上交礼金、礼品情况登记", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizProbityGift bizProbityGift) {
        List<BizProbityGift> list = bizProbityGiftService.selectBizProbityGiftList(bizProbityGift);
        ExcelUtil<BizProbityGift> util = new ExcelUtil<BizProbityGift>(BizProbityGift.class);
        return util.exportExcel(list, "probityGift");
    }

    /**
     * 获取廉政档案-3.本人拒收或上交礼金、礼品情况登记详细信息
     */
    @PreAuthorize("@ss.hasPermi('probity:probityGift:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizProbityGiftService.selectBizProbityGiftById(id));
    }

    /**
     * 新增廉政档案-3.本人拒收或上交礼金、礼品情况登记
     */
    @PreAuthorize("@ss.hasPermi('probity:probityGift:add')")
    @Log(title = "廉政档案-3.本人拒收或上交礼金、礼品情况登记", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizProbityGift bizProbityGift) {
        return toAjax(bizProbityGiftService.insertBizProbityGift(bizProbityGift));
    }

    /**
     * 修改廉政档案-3.本人拒收或上交礼金、礼品情况登记
     */
    @PreAuthorize("@ss.hasPermi('probity:probityGift:edit')")
    @Log(title = "廉政档案-3.本人拒收或上交礼金、礼品情况登记", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizProbityGift bizProbityGift) {
        return toAjax(bizProbityGiftService.updateBizProbityGift(bizProbityGift));
    }

    /**
     * 删除廉政档案-3.本人拒收或上交礼金、礼品情况登记
     */
    @PreAuthorize("@ss.hasPermi('probity:probityGift:remove')")
    @Log(title = "廉政档案-3.本人拒收或上交礼金、礼品情况登记", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizProbityGiftService.deleteBizProbityGiftByIds(ids));
    }
}
