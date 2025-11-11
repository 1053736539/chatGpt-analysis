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
import com.cb.probity.domain.BizProbityMigrate;
import com.cb.probity.service.IBizProbityMigrateService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况Controller
 *
 * @author ruoyi
 * @date 2025-03-18
 */
@RestController
@RequestMapping("/probity/probityMigrate")
public class BizProbityMigrateController extends BaseController {
    @Autowired
    private IBizProbityMigrateService bizProbityMigrateService;

    /**
     * 查询廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityMigrate:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizProbityMigrate bizProbityMigrate) {
        startPage();
        List<BizProbityMigrate> list = bizProbityMigrateService.selectBizProbityMigrateList(bizProbityMigrate);
        return getDataTable(list);
    }

    /**
     * 导出廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityMigrate:export')")
    @Log(title = "廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizProbityMigrate bizProbityMigrate) {
        List<BizProbityMigrate> list = bizProbityMigrateService.selectBizProbityMigrateList(bizProbityMigrate);
        ExcelUtil<BizProbityMigrate> util = new ExcelUtil<BizProbityMigrate>(BizProbityMigrate.class);
        return util.exportExcel(list, "probityMigrate");
    }

    /**
     * 获取廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('probity:probityMigrate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizProbityMigrateService.selectBizProbityMigrateById(id));
    }

    /**
     * 新增廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityMigrate:add')")
    @Log(title = "廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizProbityMigrate bizProbityMigrate) {
        return toAjax(bizProbityMigrateService.insertBizProbityMigrate(bizProbityMigrate));
    }

    /**
     * 修改廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityMigrate:edit')")
    @Log(title = "廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizProbityMigrate bizProbityMigrate) {
        return toAjax(bizProbityMigrateService.updateBizProbityMigrate(bizProbityMigrate));
    }

    /**
     * 删除廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityMigrate:remove')")
    @Log(title = "廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizProbityMigrateService.deleteBizProbityMigrateByIds(ids));
    }
}
