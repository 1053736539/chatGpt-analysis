package com.cb.assess.controller;

import java.util.List;

import com.cb.assess.domain.BizAssessIndicatorIsm;
import com.cb.assess.domain.vo.IndicatorIsmConfigVo;
import com.cb.assess.service.IBizAssessIndicatorIsmService;
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
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 指标体系Controller
 *
 * @author ouyang
 * @date 2023-10-27
 */
@RestController
@RequestMapping("/indicator/ism")
public class BizAssessIndicatorIsmController extends BaseController {
    @Autowired
    private IBizAssessIndicatorIsmService bizAssessIndicatorIsmService;

    /**
     * 查询指标体系列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BizAssessIndicatorIsm bizAssessIndicatorIsm) {
        startPage();
        List<BizAssessIndicatorIsm> list = bizAssessIndicatorIsmService.selectBizAssessIndicatorIsmList(bizAssessIndicatorIsm);
        return getDataTable(list);
    }

    @GetMapping("/listAll")
    public AjaxResult listAll(BizAssessIndicatorIsm bizAssessIndicatorIsm) {
        List<BizAssessIndicatorIsm> list = bizAssessIndicatorIsmService.selectAllIndicatorIsmList(bizAssessIndicatorIsm);
        return AjaxResult.success(list);
    }

    /**
     * 导出指标体系列表
     */
    @Log(title = "指标体系", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizAssessIndicatorIsm bizAssessIndicatorIsm) {
        List<BizAssessIndicatorIsm> list = bizAssessIndicatorIsmService.selectBizAssessIndicatorIsmList(bizAssessIndicatorIsm);
        ExcelUtil<BizAssessIndicatorIsm> util = new ExcelUtil<BizAssessIndicatorIsm>(BizAssessIndicatorIsm.class);
        return util.exportExcel(list, "ism");
    }

    /**
     * 获取指标体系详细信息
     */
    @GetMapping(value = "/{ismId}")
    public AjaxResult getInfo(@PathVariable("ismId") String ismId) {
        return AjaxResult.success(bizAssessIndicatorIsmService.selectBizAssessIndicatorIsmById(ismId));
    }

    /**
     * 新增指标体系
     */
    @Log(title = "指标体系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAssessIndicatorIsm bizAssessIndicatorIsm) {
        return toAjax(bizAssessIndicatorIsmService.insertBizAssessIndicatorIsm(bizAssessIndicatorIsm));
    }

    /**
     * 修改指标体系
     */
    @Log(title = "指标体系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAssessIndicatorIsm bizAssessIndicatorIsm) {
        return toAjax(bizAssessIndicatorIsmService.updateBizAssessIndicatorIsm(bizAssessIndicatorIsm));
    }

    /**
     * 删除指标体系
     */
    @Log(title = "指标体系", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ismIds}")
    public AjaxResult remove(@PathVariable String[] ismIds) {
        return toAjax(bizAssessIndicatorIsmService.logicDeleteBizAssessIndicatorIsmByIds(ismIds));
    }

    @GetMapping(value = "/detail/{ismId}")
    public AjaxResult detail(@PathVariable("ismId") String ismId){
        return AjaxResult.success(bizAssessIndicatorIsmService.selectIndicatorIsmConfigVoMapByIsmId(ismId));
    }

    @GetMapping(value = "/syncProIndicatorSnapshot/{ismId}")
    public AjaxResult syncProIndicatorSnapshot(@PathVariable("ismId") String ismId){
        return AjaxResult.success(bizAssessIndicatorIsmService.syncProIndicatorSnapshot(ismId));
    }
}
