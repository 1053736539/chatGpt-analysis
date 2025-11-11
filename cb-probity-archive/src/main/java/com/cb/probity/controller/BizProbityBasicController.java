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
import com.cb.probity.domain.BizProbityBasic;
import com.cb.probity.service.IBizProbityBasicService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 廉政档案-1.报告人基本情况Controller
 *
 * @author ruoyi
 * @date 2025-03-18
 */
@RestController
@RequestMapping("/probity/probityBasic")
public class BizProbityBasicController extends BaseController {
    @Autowired
    private IBizProbityBasicService bizProbityBasicService;

    /**
     * 查询廉政档案-1.报告人基本情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityBasic:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizProbityBasic bizProbityBasic) {
        startPage();
        List<BizProbityBasic> list = bizProbityBasicService.selectBizProbityBasicList(bizProbityBasic);
        return getDataTable(list);
    }

    /**
     * 导出廉政档案-1.报告人基本情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityBasic:export')")
    @Log(title = "廉政档案-1.报告人基本情况", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizProbityBasic bizProbityBasic) {
        List<BizProbityBasic> list = bizProbityBasicService.selectBizProbityBasicList(bizProbityBasic);
        ExcelUtil<BizProbityBasic> util = new ExcelUtil<BizProbityBasic>(BizProbityBasic.class);
        return util.exportExcel(list, "probityBasic");
    }

    /**
     * 获取廉政档案-1.报告人基本情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('probity:probityBasic:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizProbityBasicService.selectBizProbityBasicById(id));
    }

    /**
     * 新增廉政档案-1.报告人基本情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityBasic:add')")
    @Log(title = "廉政档案-1.报告人基本情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizProbityBasic bizProbityBasic) {
        return toAjax(bizProbityBasicService.insertBizProbityBasic(bizProbityBasic));
    }

    /**
     * 修改廉政档案-1.报告人基本情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityBasic:edit')")
    @Log(title = "廉政档案-1.报告人基本情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizProbityBasic bizProbityBasic) {
        return toAjax(bizProbityBasicService.updateBizProbityBasic(bizProbityBasic));
    }

    /**
     * 删除廉政档案-1.报告人基本情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityBasic:remove')")
    @Log(title = "廉政档案-1.报告人基本情况", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizProbityBasicService.deleteBizProbityBasicByIds(ids));
    }
}
