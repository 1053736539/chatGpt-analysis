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
import com.cb.probity.domain.BizProbityChildSpouse;
import com.cb.probity.service.IBizProbityChildSpouseService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况Controller
 *
 * @author ruoyi
 * @date 2025-03-18
 */
@RestController
@RequestMapping("/probity/probityChildSpouse")
public class BizProbityChildSpouseController extends BaseController {
    @Autowired
    private IBizProbityChildSpouseService bizProbityChildSpouseService;

    /**
     * 查询廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityChildSpouse:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizProbityChildSpouse bizProbityChildSpouse) {
        startPage();
        List<BizProbityChildSpouse> list = bizProbityChildSpouseService.selectBizProbityChildSpouseList(bizProbityChildSpouse);
        return getDataTable(list);
    }

    /**
     * 导出廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityChildSpouse:export')")
    @Log(title = "廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizProbityChildSpouse bizProbityChildSpouse) {
        List<BizProbityChildSpouse> list = bizProbityChildSpouseService.selectBizProbityChildSpouseList(bizProbityChildSpouse);
        ExcelUtil<BizProbityChildSpouse> util = new ExcelUtil<BizProbityChildSpouse>(BizProbityChildSpouse.class);
        return util.exportExcel(list, "probityChildSpouse");
    }

    /**
     * 获取廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('probity:probityChildSpouse:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizProbityChildSpouseService.selectBizProbityChildSpouseById(id));
    }

    /**
     * 新增廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityChildSpouse:add')")
    @Log(title = "廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizProbityChildSpouse bizProbityChildSpouse) {
        return toAjax(bizProbityChildSpouseService.insertBizProbityChildSpouse(bizProbityChildSpouse));
    }

    /**
     * 修改廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityChildSpouse:edit')")
    @Log(title = "廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizProbityChildSpouse bizProbityChildSpouse) {
        return toAjax(bizProbityChildSpouseService.updateBizProbityChildSpouse(bizProbityChildSpouse));
    }

    /**
     * 删除廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityChildSpouse:remove')")
    @Log(title = "廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizProbityChildSpouseService.deleteBizProbityChildSpouseByIds(ids));
    }
}
