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
import com.cb.probity.domain.BizProbityModifyRecord;
import com.cb.probity.service.IBizProbityModifyRecordService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 修改廉政档案记录Controller
 *
 * @author ruoyi
 * @date 2025-03-26
 */
@RestController
@RequestMapping("/probity/probityModifyRecord")
public class BizProbityModifyRecordController extends BaseController {
    @Autowired
    private IBizProbityModifyRecordService bizProbityModifyRecordService;

    /**
     * 查询修改廉政档案记录列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityModifyRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizProbityModifyRecord bizProbityModifyRecord) {
        startPage();
        List<BizProbityModifyRecord> list = bizProbityModifyRecordService.selectBizProbityModifyRecordList(bizProbityModifyRecord);
        return getDataTable(list);
    }

    /**
     * 导出修改廉政档案记录列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityModifyRecord:export')")
    @Log(title = "修改廉政档案记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizProbityModifyRecord bizProbityModifyRecord) {
        List<BizProbityModifyRecord> list = bizProbityModifyRecordService.selectBizProbityModifyRecordList(bizProbityModifyRecord);
        ExcelUtil<BizProbityModifyRecord> util = new ExcelUtil<BizProbityModifyRecord>(BizProbityModifyRecord.class);
        return util.exportExcel(list, "probityModifyRecord");
    }

    /**
     * 获取修改廉政档案记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('probity:probityModifyRecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizProbityModifyRecordService.selectBizProbityModifyRecordById(id));
    }

    /**
     * 新增修改廉政档案记录
     */
    @PreAuthorize("@ss.hasPermi('probity:probityModifyRecord:add')")
    @Log(title = "修改廉政档案记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizProbityModifyRecord bizProbityModifyRecord) {
        return toAjax(bizProbityModifyRecordService.insertBizProbityModifyRecord(bizProbityModifyRecord));
    }

    /**
     * 修改修改廉政档案记录
     */
    @PreAuthorize("@ss.hasPermi('probity:probityModifyRecord:edit')")
    @Log(title = "修改廉政档案记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizProbityModifyRecord bizProbityModifyRecord) {
        return toAjax(bizProbityModifyRecordService.updateBizProbityModifyRecord(bizProbityModifyRecord));
    }

    /**
     * 删除修改廉政档案记录
     */
    @PreAuthorize("@ss.hasPermi('probity:probityModifyRecord:remove')")
    @Log(title = "修改廉政档案记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizProbityModifyRecordService.deleteBizProbityModifyRecordByIds(ids));
    }
}
