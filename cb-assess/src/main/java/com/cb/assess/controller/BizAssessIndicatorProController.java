package com.cb.assess.controller;

import com.cb.assess.domain.BizAssessIndicatorPro;
import com.cb.assess.service.IBizAssessIndicatorProService;
import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 考核方案Controller
 *
 * @author ouyang
 * @date 2023-11-01
 */
@RestController
@RequestMapping("/assess/pro")
public class BizAssessIndicatorProController extends BaseController {
    @Autowired
    private IBizAssessIndicatorProService bizAssessIndicatorProService;

    /**
     * 查询考核方案列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BizAssessIndicatorPro bizAssessIndicatorPro) {
        startPage();
        List<BizAssessIndicatorPro> list = bizAssessIndicatorProService.selectBizAssessIndicatorProList(bizAssessIndicatorPro);
        return getDataTable(list);
    }

    /**
     * 导出考核方案列表
     */
    @Log(title = "考核方案", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizAssessIndicatorPro bizAssessIndicatorPro) {
        List<BizAssessIndicatorPro> list = bizAssessIndicatorProService.selectBizAssessIndicatorProList(bizAssessIndicatorPro);
        ExcelUtil<BizAssessIndicatorPro> util = new ExcelUtil<BizAssessIndicatorPro>(BizAssessIndicatorPro.class);
        return util.exportExcel(list, "pro");
    }

    /**
     * 获取考核方案详细信息
     */
    @GetMapping(value = "/{proId}")
    public AjaxResult getInfo(@PathVariable("proId") String proId) {
        BizAssessIndicatorPro pro = bizAssessIndicatorProService.selectBizAssessIndicatorProById(proId);
        return AjaxResult.success(pro);
    }

    /**
     * 新增考核方案
     */
    @Log(title = "考核方案", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAssessIndicatorPro bizAssessIndicatorPro) {
        return toAjax(bizAssessIndicatorProService.insertBizAssessIndicatorPro(bizAssessIndicatorPro));
    }

    /**
     * 修改考核方案
     */
    @Log(title = "考核方案", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAssessIndicatorPro bizAssessIndicatorPro) {
        return toAjax(bizAssessIndicatorProService.updateBizAssessIndicatorPro(bizAssessIndicatorPro));
    }

    /**
     * 删除考核方案
     */
    @Log(title = "考核方案", businessType = BusinessType.DELETE)
    @DeleteMapping("/{proIds}")
    public AjaxResult remove(@PathVariable String[] proIds) {
        return toAjax(bizAssessIndicatorProService.logicDeleteBizAssessIndicatorProByIds(proIds));
    }

    @Log(title = "考核方案状态修改", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody BizAssessIndicatorPro pro) {
        return toAjax(bizAssessIndicatorProService.changeStatus(pro));
    }

    @GetMapping("/listSelector")
    public AjaxResult listSelector(BizAssessIndicatorPro pro) {
        List<BizAssessIndicatorPro> list = bizAssessIndicatorProService.querySelectorList(pro);
        return AjaxResult.success(list);
    }

    @Log(title = "考核方案状态修改", businessType = BusinessType.UPDATE)
    @PostMapping("/auditAndSave")
    public AjaxResult auditAndSave(@RequestBody BizAssessIndicatorPro pro) {
        return toAjax(bizAssessIndicatorProService.auditAndSave(pro));
    }

}
