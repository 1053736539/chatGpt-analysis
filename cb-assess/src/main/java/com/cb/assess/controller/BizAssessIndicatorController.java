package com.cb.assess.controller;

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
import com.cb.assess.domain.BizAssessIndicator;
import com.cb.assess.service.IBizAssessIndicatorService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 考核指标体系Controller
 *
 * @author ouyang
 * @date 2023-10-26
 */
@RestController
@RequestMapping("/assess/indicator")
public class BizAssessIndicatorController extends BaseController {
    @Autowired
    private IBizAssessIndicatorService bizAssessIndicatorService;

    /**
     * 查询考核指标体系列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BizAssessIndicator bizAssessIndicator) {
        startPage();
        List<BizAssessIndicator> list = bizAssessIndicatorService.selectBizAssessIndicatorList(bizAssessIndicator);
        return getDataTable(list);
    }

    /**
     * 导出考核指标体系列表
     */
    @Log(title = "考核指标体系", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizAssessIndicator bizAssessIndicator) {
        List<BizAssessIndicator> list = bizAssessIndicatorService.selectBizAssessIndicatorList(bizAssessIndicator);
        ExcelUtil<BizAssessIndicator> util = new ExcelUtil<BizAssessIndicator>(BizAssessIndicator.class);
        return util.exportExcel(list, "indicator");
    }

    /**
     * 获取考核指标体系详细信息
     */
    @GetMapping(value = "/{indId}")
    public AjaxResult getInfo(@PathVariable("indId") String indId) {
        return AjaxResult.success(bizAssessIndicatorService.selectBizAssessIndicatorById(indId));
    }

    /**
     * 新增考核指标体系
     */
    @Log(title = "考核指标体系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAssessIndicator bizAssessIndicator) {
        return toAjax(bizAssessIndicatorService.insertBizAssessIndicator(bizAssessIndicator));
    }

    /**
     * 修改考核指标体系
     */
    @Log(title = "考核指标体系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAssessIndicator bizAssessIndicator) {
        return toAjax(bizAssessIndicatorService.updateBizAssessIndicator(bizAssessIndicator));
    }

    /**
     * 删除考核指标体系
     */
    @Log(title = "考核指标体系", businessType = BusinessType.DELETE)
    @DeleteMapping("/{indIds}")
    public AjaxResult remove(@PathVariable String[] indIds) {
        return toAjax(bizAssessIndicatorService.logicDeleteBizAssessIndicatorByIds(indIds));
    }

    /**
     * 考核指标体系状态
     */
    @Log(title = "考核指标体系", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody BizAssessIndicator bizAssessIndicator) {
        return toAjax(bizAssessIndicatorService.changeStatus(bizAssessIndicator));
    }
}
