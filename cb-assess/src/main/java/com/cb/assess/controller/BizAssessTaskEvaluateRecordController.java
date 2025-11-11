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
import com.cb.assess.domain.BizAssessTaskEvaluateRecord;
import com.cb.assess.service.IBizAssessTaskEvaluateRecordService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 考核评议记录Controller
 *
 * @author ouyang
 * @date 2023-11-20
 */
@RestController
@RequestMapping("/assess/evaluateRecord")
public class BizAssessTaskEvaluateRecordController extends BaseController {
    @Autowired
    private IBizAssessTaskEvaluateRecordService bizAssessTaskEvaluateRecordService;

    /**
     * 查询考核评议记录列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BizAssessTaskEvaluateRecord bizAssessTaskEvaluateRecord) {
        startPage();
        List<BizAssessTaskEvaluateRecord> list = bizAssessTaskEvaluateRecordService.selectBizAssessTaskEvaluateRecordList(bizAssessTaskEvaluateRecord);
        return getDataTable(list);
    }

    /**
     * 导出考核评议记录列表
     */
    @Log(title = "考核评议记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizAssessTaskEvaluateRecord bizAssessTaskEvaluateRecord) {
        List<BizAssessTaskEvaluateRecord> list = bizAssessTaskEvaluateRecordService.selectBizAssessTaskEvaluateRecordList(bizAssessTaskEvaluateRecord);
        ExcelUtil<BizAssessTaskEvaluateRecord> util = new ExcelUtil<BizAssessTaskEvaluateRecord>(BizAssessTaskEvaluateRecord.class);
        return util.exportExcel(list, "evaluateRecord");
    }

    /**
     * 获取考核评议记录详细信息
     */
    @GetMapping(value = "/{evaluateId}")
    public AjaxResult getInfo(@PathVariable("evaluateId") String evaluateId) {
        return AjaxResult.success(bizAssessTaskEvaluateRecordService.selectBizAssessTaskEvaluateRecordById(evaluateId));
    }

    /**
     * 新增考核评议记录
     */
    @Log(title = "考核评议记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAssessTaskEvaluateRecord bizAssessTaskEvaluateRecord) {
        return toAjax(bizAssessTaskEvaluateRecordService.insertBizAssessTaskEvaluateRecord(bizAssessTaskEvaluateRecord));
    }

    /**
     * 修改考核评议记录
     */
    @Log(title = "考核评议记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAssessTaskEvaluateRecord bizAssessTaskEvaluateRecord) {
        return toAjax(bizAssessTaskEvaluateRecordService.updateBizAssessTaskEvaluateRecord(bizAssessTaskEvaluateRecord));
    }

    /**
     * 删除考核评议记录
     */
    @Log(title = "考核评议记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{evaluateIds}")
    public AjaxResult remove(@PathVariable String[] evaluateIds) {
        return toAjax(bizAssessTaskEvaluateRecordService.deleteBizAssessTaskEvaluateRecordByIds(evaluateIds));
    }
}
