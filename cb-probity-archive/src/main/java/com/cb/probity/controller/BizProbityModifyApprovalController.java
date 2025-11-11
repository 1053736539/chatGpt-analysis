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
import com.cb.probity.domain.BizProbityModifyApproval;
import com.cb.probity.service.IBizProbityModifyApprovalService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 申请修改廉政档案的记录Controller
 *
 * @author ruoyi
 * @date 2025-03-18
 */
@RestController
@RequestMapping("/probity/probityModifyApproval")
public class BizProbityModifyApprovalController extends BaseController {
    @Autowired
    private IBizProbityModifyApprovalService bizProbityModifyApprovalService;

    /**
     * 查询申请修改廉政档案的记录列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityModifyApproval:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizProbityModifyApproval bizProbityModifyApproval) {
        startPage();
        List<BizProbityModifyApproval> list = bizProbityModifyApprovalService.selectBizProbityModifyApprovalList(bizProbityModifyApproval);
        return getDataTable(list);
    }

    /**
     * 导出申请修改廉政档案的记录列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityModifyApproval:export')")
    @Log(title = "申请修改廉政档案的记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizProbityModifyApproval bizProbityModifyApproval) {
        List<BizProbityModifyApproval> list = bizProbityModifyApprovalService.selectBizProbityModifyApprovalList(bizProbityModifyApproval);
        ExcelUtil<BizProbityModifyApproval> util = new ExcelUtil<BizProbityModifyApproval>(BizProbityModifyApproval.class);
        return util.exportExcel(list, "probityModifyApproval");
    }

    /**
     * 获取申请修改廉政档案的记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('probity:probityModifyApproval:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizProbityModifyApprovalService.selectBizProbityModifyApprovalById(id));
    }

    /**
     * 新增申请修改廉政档案的记录
     */
    @PreAuthorize("@ss.hasPermi('probity:probityModifyApproval:add')")
    @Log(title = "申请修改廉政档案的记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizProbityModifyApproval bizProbityModifyApproval) {
        return toAjax(bizProbityModifyApprovalService.insertBizProbityModifyApproval(bizProbityModifyApproval));
    }

    /**
     * 修改申请修改廉政档案的记录
     */
    @PreAuthorize("@ss.hasPermi('probity:probityModifyApproval:edit')")
    @Log(title = "申请修改廉政档案的记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizProbityModifyApproval bizProbityModifyApproval) {
        return toAjax(bizProbityModifyApprovalService.updateBizProbityModifyApproval(bizProbityModifyApproval));
    }

    /**
     * 删除申请修改廉政档案的记录
     */
    @PreAuthorize("@ss.hasPermi('probity:probityModifyApproval:remove')")
    @Log(title = "申请修改廉政档案的记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizProbityModifyApprovalService.deleteBizProbityModifyApprovalByIds(ids));
    }
}
