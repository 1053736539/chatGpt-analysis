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
import com.cb.probity.domain.BizProbityInvestigate;
import com.cb.probity.service.IBizProbityInvestigateService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录Controller
 *
 * @author ruoyi
 * @date 2025-03-18
 */
@RestController
@RequestMapping("/probity/probityInvestigate")
public class BizProbityInvestigateController extends BaseController {
    @Autowired
    private IBizProbityInvestigateService bizProbityInvestigateService;

    /**
     * 查询廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityInvestigate:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizProbityInvestigate bizProbityInvestigate) {
        startPage();
        List<BizProbityInvestigate> list = bizProbityInvestigateService.selectBizProbityInvestigateList(bizProbityInvestigate);
        return getDataTable(list);
    }

    /**
     * 导出廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityInvestigate:export')")
    @Log(title = "廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizProbityInvestigate bizProbityInvestigate) {
        List<BizProbityInvestigate> list = bizProbityInvestigateService.selectBizProbityInvestigateList(bizProbityInvestigate);
        ExcelUtil<BizProbityInvestigate> util = new ExcelUtil<BizProbityInvestigate>(BizProbityInvestigate.class);
        return util.exportExcel(list, "probityInvestigate");
    }

    /**
     * 获取廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('probity:probityInvestigate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizProbityInvestigateService.selectBizProbityInvestigateById(id));
    }

    /**
     * 新增廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录
     */
    @PreAuthorize("@ss.hasPermi('probity:probityInvestigate:add')")
    @Log(title = "廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizProbityInvestigate bizProbityInvestigate) {
        return toAjax(bizProbityInvestigateService.insertBizProbityInvestigate(bizProbityInvestigate));
    }

    /**
     * 修改廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录
     */
    @PreAuthorize("@ss.hasPermi('probity:probityInvestigate:edit')")
    @Log(title = "廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizProbityInvestigate bizProbityInvestigate) {
        return toAjax(bizProbityInvestigateService.updateBizProbityInvestigate(bizProbityInvestigate));
    }

    /**
     * 删除廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录
     */
    @PreAuthorize("@ss.hasPermi('probity:probityInvestigate:remove')")
    @Log(title = "廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizProbityInvestigateService.deleteBizProbityInvestigateByIds(ids));
    }
}
