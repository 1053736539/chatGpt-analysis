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
import com.cb.probity.domain.BizProbityInsurance;
import com.cb.probity.service.IBizProbityInsuranceService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况Controller
 *
 * @author ruoyi
 * @date 2025-03-18
 */
@RestController
@RequestMapping("/probity/probityInsurance")
public class BizProbityInsuranceController extends BaseController {
    @Autowired
    private IBizProbityInsuranceService bizProbityInsuranceService;

    /**
     * 查询廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityInsurance:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizProbityInsurance bizProbityInsurance) {
        startPage();
        List<BizProbityInsurance> list = bizProbityInsuranceService.selectBizProbityInsuranceList(bizProbityInsurance);
        return getDataTable(list);
    }

    /**
     * 导出廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityInsurance:export')")
    @Log(title = "廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizProbityInsurance bizProbityInsurance) {
        List<BizProbityInsurance> list = bizProbityInsuranceService.selectBizProbityInsuranceList(bizProbityInsurance);
        ExcelUtil<BizProbityInsurance> util = new ExcelUtil<BizProbityInsurance>(BizProbityInsurance.class);
        return util.exportExcel(list, "probityInsurance");
    }

    /**
     * 获取廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('probity:probityInsurance:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizProbityInsuranceService.selectBizProbityInsuranceById(id));
    }

    /**
     * 新增廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityInsurance:add')")
    @Log(title = "廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizProbityInsurance bizProbityInsurance) {
        return toAjax(bizProbityInsuranceService.insertBizProbityInsurance(bizProbityInsurance));
    }

    /**
     * 修改廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityInsurance:edit')")
    @Log(title = "廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizProbityInsurance bizProbityInsurance) {
        return toAjax(bizProbityInsuranceService.updateBizProbityInsurance(bizProbityInsurance));
    }

    /**
     * 删除廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityInsurance:remove')")
    @Log(title = "廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizProbityInsuranceService.deleteBizProbityInsuranceByIds(ids));
    }
}
