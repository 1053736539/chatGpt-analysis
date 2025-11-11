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
import com.cb.probity.domain.BizProbityFamilyMember;
import com.cb.probity.service.IBizProbityFamilyMemberService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）Controller
 *
 * @author ruoyi
 * @date 2025-03-18
 */
@RestController
@RequestMapping("/probity/probityFamilyMember")
public class BizProbityFamilyMemberController extends BaseController {
    @Autowired
    private IBizProbityFamilyMemberService bizProbityFamilyMemberService;

    /**
     * 查询廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityFamilyMember:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizProbityFamilyMember bizProbityFamilyMember) {
        startPage();
        List<BizProbityFamilyMember> list = bizProbityFamilyMemberService.selectBizProbityFamilyMemberList(bizProbityFamilyMember);
        return getDataTable(list);
    }

    /**
     * 导出廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityFamilyMember:export')")
    @Log(title = "廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizProbityFamilyMember bizProbityFamilyMember) {
        List<BizProbityFamilyMember> list = bizProbityFamilyMemberService.selectBizProbityFamilyMemberList(bizProbityFamilyMember);
        ExcelUtil<BizProbityFamilyMember> util = new ExcelUtil<BizProbityFamilyMember>(BizProbityFamilyMember.class);
        return util.exportExcel(list, "probityFamilyMember");
    }

    /**
     * 获取廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）详细信息
     */
    @PreAuthorize("@ss.hasPermi('probity:probityFamilyMember:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizProbityFamilyMemberService.selectBizProbityFamilyMemberById(id));
    }

    /**
     * 新增廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）
     */
    @PreAuthorize("@ss.hasPermi('probity:probityFamilyMember:add')")
    @Log(title = "廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizProbityFamilyMember bizProbityFamilyMember) {
        return toAjax(bizProbityFamilyMemberService.insertBizProbityFamilyMember(bizProbityFamilyMember));
    }

    /**
     * 修改廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）
     */
    @PreAuthorize("@ss.hasPermi('probity:probityFamilyMember:edit')")
    @Log(title = "廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizProbityFamilyMember bizProbityFamilyMember) {
        return toAjax(bizProbityFamilyMemberService.updateBizProbityFamilyMember(bizProbityFamilyMember));
    }

    /**
     * 删除廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）
     */
    @PreAuthorize("@ss.hasPermi('probity:probityFamilyMember:remove')")
    @Log(title = "廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizProbityFamilyMemberService.deleteBizProbityFamilyMemberByIds(ids));
    }
}
