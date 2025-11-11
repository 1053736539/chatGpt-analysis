package com.cb.rpa.controller;

import java.util.List;

import com.cb.rpa.domain.RpaCcdiWebsite;
import com.cb.rpa.service.IRpaCcdiWebsiteService;
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
 * 中纪委Controller
 *
 * @author ouyang
 * @date 2024-11-13
 */
@RestController
@RequestMapping("/rpa/ccdi")
public class RpaCcdiWebsiteController extends BaseController {
    @Autowired
    private IRpaCcdiWebsiteService rpaCcdiWebsiteService;

    /**
     * 查询中纪委列表
     */
    @GetMapping("/list")
    public TableDataInfo list(RpaCcdiWebsite rpaCcdiWebsite) {
        startPage();
        List<RpaCcdiWebsite> list = rpaCcdiWebsiteService.selectRpaCcdiWebsiteList(rpaCcdiWebsite);
        return getDataTable(list);
    }

    /**
     * 导出中纪委列表
     */
    @Log(title = "中纪委", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(RpaCcdiWebsite rpaCcdiWebsite) {
        List<RpaCcdiWebsite> list = rpaCcdiWebsiteService.selectRpaCcdiWebsiteList(rpaCcdiWebsite);
        ExcelUtil<RpaCcdiWebsite> util = new ExcelUtil<RpaCcdiWebsite>(RpaCcdiWebsite.class);
        return util.exportExcel(list, "ccdi");
    }

    /**
     * 获取中纪委详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(rpaCcdiWebsiteService.selectRpaCcdiWebsiteById(id));
    }

    /**
     * 新增中纪委
     */
    @Log(title = "中纪委", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RpaCcdiWebsite rpaCcdiWebsite) {
        return toAjax(rpaCcdiWebsiteService.insertRpaCcdiWebsite(rpaCcdiWebsite));
    }

    /**
     * 修改中纪委
     */
    @Log(title = "中纪委", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RpaCcdiWebsite rpaCcdiWebsite) {
        return toAjax(rpaCcdiWebsiteService.updateRpaCcdiWebsite(rpaCcdiWebsite));
    }

    /**
     * 删除中纪委
     */
    @Log(title = "中纪委", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(rpaCcdiWebsiteService.deleteRpaCcdiWebsiteByIds(ids));
    }

    @Log(title = "中纪委-推送至AI知识库", businessType = BusinessType.OTHER)
    @PutMapping("/push2Knowledge")
    public AjaxResult push2Knowledge(@RequestBody RpaCcdiWebsite website) {
        return toAjax(rpaCcdiWebsiteService.push2AiKnowledge(website));
    }
}
