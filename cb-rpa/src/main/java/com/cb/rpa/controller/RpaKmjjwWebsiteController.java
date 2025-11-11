package com.cb.rpa.controller;

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
import com.cb.rpa.domain.RpaKmjjwWebsite;
import com.cb.rpa.service.IRpaKmjjwWebsiteService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 昆明市纪监委网站Controller
 *
 * @author ouyang
 * @date 2024-10-22
 */
@RestController
@RequestMapping("/rpa/kmjjw")
public class RpaKmjjwWebsiteController extends BaseController {
    @Autowired
    private IRpaKmjjwWebsiteService rpaKmjjwWebsiteService;

    /**
     * 查询昆明市纪监委网站列表
     */
    @PreAuthorize("@ss.hasPermi('rpa:kmjjw:list')")
    @GetMapping("/list")
    public TableDataInfo list(RpaKmjjwWebsite rpaKmjjwWebsite) {
        startPage();
        List<RpaKmjjwWebsite> list = rpaKmjjwWebsiteService.selectRpaKmjjwWebsiteList(rpaKmjjwWebsite);
        return getDataTable(list);
    }

    /**
     * 导出昆明市纪监委网站列表
     */
    @PreAuthorize("@ss.hasPermi('rpa:kmjjw:export')")
    @Log(title = "昆明市纪监委网站", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(RpaKmjjwWebsite rpaKmjjwWebsite) {
        List<RpaKmjjwWebsite> list = rpaKmjjwWebsiteService.selectRpaKmjjwWebsiteList(rpaKmjjwWebsite);
        ExcelUtil<RpaKmjjwWebsite> util = new ExcelUtil<RpaKmjjwWebsite>(RpaKmjjwWebsite.class);
        return util.exportExcel(list, "kmjjw");
    }

    /**
     * 获取昆明市纪监委网站详细信息
     */
    @PreAuthorize("@ss.hasPermi('rpa:kmjjw:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(rpaKmjjwWebsiteService.selectRpaKmjjwWebsiteById(id));
    }

    /**
     * 新增昆明市纪监委网站
     */
    @PreAuthorize("@ss.hasPermi('rpa:kmjjw:add')")
    @Log(title = "昆明市纪监委网站", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RpaKmjjwWebsite rpaKmjjwWebsite) {
        return toAjax(rpaKmjjwWebsiteService.insertRpaKmjjwWebsite(rpaKmjjwWebsite));
    }

    /**
     * 修改昆明市纪监委网站
     */
    @PreAuthorize("@ss.hasPermi('rpa:kmjjw:edit')")
    @Log(title = "昆明市纪监委网站", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RpaKmjjwWebsite rpaKmjjwWebsite) {
        return toAjax(rpaKmjjwWebsiteService.updateRpaKmjjwWebsite(rpaKmjjwWebsite));
    }

    /**
     * 删除昆明市纪监委网站
     */
    @PreAuthorize("@ss.hasPermi('rpa:kmjjw:remove')")
    @Log(title = "昆明市纪监委网站", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(rpaKmjjwWebsiteService.deleteRpaKmjjwWebsiteByIds(ids));
    }

    @Log(title = "昆明市纪-推送至AI知识库", businessType = BusinessType.OTHER)
    @PutMapping("/push2Knowledge")
    public AjaxResult push2Knowledge(@RequestBody RpaKmjjwWebsite website){
        return toAjax(rpaKmjjwWebsiteService.push2AiKnowledge(website));
    }

}
