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
import com.cb.probity.domain.BizProbityOperate;
import com.cb.probity.service.IBizProbityOperateService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 廉政档案-19.本人或本人子女操办婚丧嫁娶情况Controller
 *
 * @author ruoyi
 * @date 2025-03-18
 */
@RestController
@RequestMapping("/probity/ProbityOperate")
public class BizProbityOperateController extends BaseController {
    @Autowired
    private IBizProbityOperateService bizProbityOperateService;

    /**
     * 查询廉政档案-19.本人或本人子女操办婚丧嫁娶情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:ProbityOperate:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizProbityOperate bizProbityOperate) {
        startPage();
        List<BizProbityOperate> list = bizProbityOperateService.selectBizProbityOperateList(bizProbityOperate);
        return getDataTable(list);
    }

    /**
     * 导出廉政档案-19.本人或本人子女操办婚丧嫁娶情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:ProbityOperate:export')")
    @Log(title = "廉政档案-19.本人或本人子女操办婚丧嫁娶情况", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizProbityOperate bizProbityOperate) {
        List<BizProbityOperate> list = bizProbityOperateService.selectBizProbityOperateList(bizProbityOperate);
        ExcelUtil<BizProbityOperate> util = new ExcelUtil<BizProbityOperate>(BizProbityOperate.class);
        return util.exportExcel(list, "ProbityOperate");
    }

    /**
     * 获取廉政档案-19.本人或本人子女操办婚丧嫁娶情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('probity:ProbityOperate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizProbityOperateService.selectBizProbityOperateById(id));
    }

    /**
     * 新增廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     */
    @PreAuthorize("@ss.hasPermi('probity:ProbityOperate:add')")
    @Log(title = "廉政档案-19.本人或本人子女操办婚丧嫁娶情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizProbityOperate bizProbityOperate) {
        return toAjax(bizProbityOperateService.insertBizProbityOperate(bizProbityOperate));
    }

    /**
     * 修改廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     */
    @PreAuthorize("@ss.hasPermi('probity:ProbityOperate:edit')")
    @Log(title = "廉政档案-19.本人或本人子女操办婚丧嫁娶情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizProbityOperate bizProbityOperate) {
        return toAjax(bizProbityOperateService.updateBizProbityOperate(bizProbityOperate));
    }

    /**
     * 删除廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     */
    @PreAuthorize("@ss.hasPermi('probity:ProbityOperate:remove')")
    @Log(title = "廉政档案-19.本人或本人子女操办婚丧嫁娶情况", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizProbityOperateService.deleteBizProbityOperateByIds(ids));
    }
}
