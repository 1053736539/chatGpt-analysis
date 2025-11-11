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
import com.cb.probity.domain.BizProbityEnterprise;
import com.cb.probity.service.IBizProbityEnterpriseService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况Controller
 *
 * @author ruoyi
 * @date 2025-03-18
 */
@RestController
@RequestMapping("/probity/probityEnterprise")
public class BizProbityEnterpriseController extends BaseController {
    @Autowired
    private IBizProbityEnterpriseService bizProbityEnterpriseService;

    /**
     * 查询廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityEnterprise:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizProbityEnterprise bizProbityEnterprise) {
        startPage();
        List<BizProbityEnterprise> list = bizProbityEnterpriseService.selectBizProbityEnterpriseList(bizProbityEnterprise);
        return getDataTable(list);
    }

    /**
     * 导出廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityEnterprise:export')")
    @Log(title = "廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizProbityEnterprise bizProbityEnterprise) {
        List<BizProbityEnterprise> list = bizProbityEnterpriseService.selectBizProbityEnterpriseList(bizProbityEnterprise);
        ExcelUtil<BizProbityEnterprise> util = new ExcelUtil<BizProbityEnterprise>(BizProbityEnterprise.class);
        return util.exportExcel(list, "probityEnterprise");
    }

    /**
     * 获取廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('probity:probityEnterprise:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizProbityEnterpriseService.selectBizProbityEnterpriseById(id));
    }

    /**
     * 新增廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityEnterprise:add')")
    @Log(title = "廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizProbityEnterprise bizProbityEnterprise) {
        return toAjax(bizProbityEnterpriseService.insertBizProbityEnterprise(bizProbityEnterprise));
    }

    /**
     * 修改廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityEnterprise:edit')")
    @Log(title = "廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizProbityEnterprise bizProbityEnterprise) {
        return toAjax(bizProbityEnterpriseService.updateBizProbityEnterprise(bizProbityEnterprise));
    }

    /**
     * 删除廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityEnterprise:remove')")
    @Log(title = "廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizProbityEnterpriseService.deleteBizProbityEnterpriseByIds(ids));
    }
}
