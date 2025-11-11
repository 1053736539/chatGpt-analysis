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
import com.cb.probity.domain.BizProbitySalary;
import com.cb.probity.service.IBizProbitySalaryService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 廉政档案-10.本人工资及各类奖金、津贴、补贴情况Controller
 *
 * @author ruoyi
 * @date 2025-03-18
 */
@RestController
@RequestMapping("/probity/probitySalary")
public class BizProbitySalaryController extends BaseController {
    @Autowired
    private IBizProbitySalaryService bizProbitySalaryService;

    /**
     * 查询廉政档案-10.本人工资及各类奖金、津贴、补贴情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probitySalary:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizProbitySalary bizProbitySalary) {
        startPage();
        List<BizProbitySalary> list = bizProbitySalaryService.selectBizProbitySalaryList(bizProbitySalary);
        return getDataTable(list);
    }

    /**
     * 导出廉政档案-10.本人工资及各类奖金、津贴、补贴情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probitySalary:export')")
    @Log(title = "廉政档案-10.本人工资及各类奖金、津贴、补贴情况", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizProbitySalary bizProbitySalary) {
        List<BizProbitySalary> list = bizProbitySalaryService.selectBizProbitySalaryList(bizProbitySalary);
        ExcelUtil<BizProbitySalary> util = new ExcelUtil<BizProbitySalary>(BizProbitySalary.class);
        return util.exportExcel(list, "probitySalary");
    }

    /**
     * 获取廉政档案-10.本人工资及各类奖金、津贴、补贴情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('probity:probitySalary:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizProbitySalaryService.selectBizProbitySalaryById(id));
    }

    /**
     * 新增廉政档案-10.本人工资及各类奖金、津贴、补贴情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probitySalary:add')")
    @Log(title = "廉政档案-10.本人工资及各类奖金、津贴、补贴情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizProbitySalary bizProbitySalary) {
        return toAjax(bizProbitySalaryService.insertBizProbitySalary(bizProbitySalary));
    }

    /**
     * 修改廉政档案-10.本人工资及各类奖金、津贴、补贴情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probitySalary:edit')")
    @Log(title = "廉政档案-10.本人工资及各类奖金、津贴、补贴情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizProbitySalary bizProbitySalary) {
        return toAjax(bizProbitySalaryService.updateBizProbitySalary(bizProbitySalary));
    }

    /**
     * 删除廉政档案-10.本人工资及各类奖金、津贴、补贴情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probitySalary:remove')")
    @Log(title = "廉政档案-10.本人工资及各类奖金、津贴、补贴情况", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizProbitySalaryService.deleteBizProbitySalaryByIds(ids));
    }
}
