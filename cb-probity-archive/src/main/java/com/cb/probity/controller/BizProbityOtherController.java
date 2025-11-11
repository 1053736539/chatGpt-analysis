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
import com.cb.probity.domain.BizProbityOther;
import com.cb.probity.service.IBizProbityOtherService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 廉政档案-20.个人认为需要报告的其他事项Controller
 *
 * @author ruoyi
 * @date 2025-03-18
 */
@RestController
@RequestMapping("/probity/probityOther")
public class BizProbityOtherController extends BaseController {
    @Autowired
    private IBizProbityOtherService bizProbityOtherService;

    /**
     * 查询廉政档案-20.个人认为需要报告的其他事项列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityOther:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizProbityOther bizProbityOther) {
        startPage();
        List<BizProbityOther> list = bizProbityOtherService.selectBizProbityOtherList(bizProbityOther);
        return getDataTable(list);
    }

    /**
     * 导出廉政档案-20.个人认为需要报告的其他事项列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityOther:export')")
    @Log(title = "廉政档案-20.个人认为需要报告的其他事项", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizProbityOther bizProbityOther) {
        List<BizProbityOther> list = bizProbityOtherService.selectBizProbityOtherList(bizProbityOther);
        ExcelUtil<BizProbityOther> util = new ExcelUtil<BizProbityOther>(BizProbityOther.class);
        return util.exportExcel(list, "probityOther");
    }

    /**
     * 获取廉政档案-20.个人认为需要报告的其他事项详细信息
     */
    @PreAuthorize("@ss.hasPermi('probity:probityOther:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizProbityOtherService.selectBizProbityOtherById(id));
    }

    /**
     * 新增廉政档案-20.个人认为需要报告的其他事项
     */
    @PreAuthorize("@ss.hasPermi('probity:probityOther:add')")
    @Log(title = "廉政档案-20.个人认为需要报告的其他事项", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizProbityOther bizProbityOther) {
        return toAjax(bizProbityOtherService.insertBizProbityOther(bizProbityOther));
    }

    /**
     * 修改廉政档案-20.个人认为需要报告的其他事项
     */
    @PreAuthorize("@ss.hasPermi('probity:probityOther:edit')")
    @Log(title = "廉政档案-20.个人认为需要报告的其他事项", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizProbityOther bizProbityOther) {
        return toAjax(bizProbityOtherService.updateBizProbityOther(bizProbityOther));
    }

    /**
     * 删除廉政档案-20.个人认为需要报告的其他事项
     */
    @PreAuthorize("@ss.hasPermi('probity:probityOther:remove')")
    @Log(title = "廉政档案-20.个人认为需要报告的其他事项", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizProbityOtherService.deleteBizProbityOtherByIds(ids));
    }
}
