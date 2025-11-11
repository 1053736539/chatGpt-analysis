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
import com.cb.assess.domain.BizAssessPromulgate;
import com.cb.assess.service.IBizAssessPromulgateService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 考核公示Controller
 *
 * @author ouyang
 * @date 2023-12-29
 */
@RestController
@RequestMapping("/assess/promulgate")
public class BizAssessPromulgateController extends BaseController {
    @Autowired
    private IBizAssessPromulgateService bizAssessPromulgateService;

    /**
     * 查询考核公示列表
     */
    @PreAuthorize("@ss.hasPermi('assess:promulgate:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizAssessPromulgate bizAssessPromulgate) {
        startPage();
        List<BizAssessPromulgate> list = bizAssessPromulgateService.selectBizAssessPromulgateList(bizAssessPromulgate);
        return getDataTable(list);
    }

    /**
     * 导出考核公示列表
     */
    @PreAuthorize("@ss.hasPermi('assess:promulgate:export')")
    @Log(title = "考核公示", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizAssessPromulgate bizAssessPromulgate) {
        List<BizAssessPromulgate> list = bizAssessPromulgateService.selectBizAssessPromulgateList(bizAssessPromulgate);
        ExcelUtil<BizAssessPromulgate> util = new ExcelUtil<BizAssessPromulgate>(BizAssessPromulgate.class);
        return util.exportExcel(list, "promulgate");
    }

    /**
     * 获取考核公示详细信息
     */
    @PreAuthorize("@ss.hasPermi('assess:promulgate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizAssessPromulgateService.selectBizAssessPromulgateById(id));
    }

    /**
     * 新增考核公示
     */
    @PreAuthorize("@ss.hasPermi('assess:promulgate:add')")
    @Log(title = "考核公示", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAssessPromulgate bizAssessPromulgate) {
        return toAjax(bizAssessPromulgateService.insertBizAssessPromulgate(bizAssessPromulgate));
    }

    /**
     * 修改考核公示
     */
    @PreAuthorize("@ss.hasPermi('assess:promulgate:edit')")
    @Log(title = "考核公示", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAssessPromulgate bizAssessPromulgate) {
        return toAjax(bizAssessPromulgateService.updateBizAssessPromulgate(bizAssessPromulgate));
    }

    /**
     * 删除考核公示
     */
    @PreAuthorize("@ss.hasPermi('assess:promulgate:remove')")
    @Log(title = "考核公示", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizAssessPromulgateService.deleteBizAssessPromulgateByIds(ids));
    }
}
