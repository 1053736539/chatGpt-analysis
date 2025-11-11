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
import com.cb.probity.domain.BizProbityMarital;
import com.cb.probity.service.IBizProbityMaritalService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 廉政档案-4.本人婚姻状况及紧急联系人情况Controller
 *
 * @author ruoyi
 * @date 2025-03-18
 */
@RestController
@RequestMapping("/probity/probityMarital")
public class BizProbityMaritalController extends BaseController {
    @Autowired
    private IBizProbityMaritalService bizProbityMaritalService;

    /**
     * 查询廉政档案-4.本人婚姻状况及紧急联系人情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityMarital:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizProbityMarital bizProbityMarital) {
        startPage();
        List<BizProbityMarital> list = bizProbityMaritalService.selectBizProbityMaritalList(bizProbityMarital);
        return getDataTable(list);
    }

    /**
     * 导出廉政档案-4.本人婚姻状况及紧急联系人情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityMarital:export')")
    @Log(title = "廉政档案-4.本人婚姻状况及紧急联系人情况", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizProbityMarital bizProbityMarital) {
        List<BizProbityMarital> list = bizProbityMaritalService.selectBizProbityMaritalList(bizProbityMarital);
        ExcelUtil<BizProbityMarital> util = new ExcelUtil<BizProbityMarital>(BizProbityMarital.class);
        return util.exportExcel(list, "probityMarital");
    }

    /**
     * 获取廉政档案-4.本人婚姻状况及紧急联系人情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('probity:probityMarital:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizProbityMaritalService.selectBizProbityMaritalById(id));
    }

    /**
     * 新增廉政档案-4.本人婚姻状况及紧急联系人情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityMarital:add')")
    @Log(title = "廉政档案-4.本人婚姻状况及紧急联系人情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizProbityMarital bizProbityMarital) {
        return toAjax(bizProbityMaritalService.insertBizProbityMarital(bizProbityMarital));
    }

    /**
     * 修改廉政档案-4.本人婚姻状况及紧急联系人情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityMarital:edit')")
    @Log(title = "廉政档案-4.本人婚姻状况及紧急联系人情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizProbityMarital bizProbityMarital) {
        return toAjax(bizProbityMaritalService.updateBizProbityMarital(bizProbityMarital));
    }

    /**
     * 删除廉政档案-4.本人婚姻状况及紧急联系人情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityMarital:remove')")
    @Log(title = "廉政档案-4.本人婚姻状况及紧急联系人情况", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizProbityMaritalService.deleteBizProbityMaritalByIds(ids));
    }
}
