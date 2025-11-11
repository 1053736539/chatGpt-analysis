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
import com.cb.probity.domain.BizProbityHouse;
import com.cb.probity.service.IBizProbityHouseService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况Controller
 *
 * @author ruoyi
 * @date 2025-03-18
 */
@RestController
@RequestMapping("/probity/probityHouse")
public class BizProbityHouseController extends BaseController {
    @Autowired
    private IBizProbityHouseService bizProbityHouseService;

    /**
     * 查询廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityHouse:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizProbityHouse bizProbityHouse) {
        startPage();
        List<BizProbityHouse> list = bizProbityHouseService.selectBizProbityHouseList(bizProbityHouse);
        return getDataTable(list);
    }

    /**
     * 导出廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityHouse:export')")
    @Log(title = "廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizProbityHouse bizProbityHouse) {
        List<BizProbityHouse> list = bizProbityHouseService.selectBizProbityHouseList(bizProbityHouse);
        ExcelUtil<BizProbityHouse> util = new ExcelUtil<BizProbityHouse>(BizProbityHouse.class);
        return util.exportExcel(list, "probityHouse");
    }

    /**
     * 获取廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('probity:probityHouse:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizProbityHouseService.selectBizProbityHouseById(id));
    }

    /**
     * 新增廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityHouse:add')")
    @Log(title = "廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizProbityHouse bizProbityHouse) {
        return toAjax(bizProbityHouseService.insertBizProbityHouse(bizProbityHouse));
    }

    /**
     * 修改廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityHouse:edit')")
    @Log(title = "廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizProbityHouse bizProbityHouse) {
        return toAjax(bizProbityHouseService.updateBizProbityHouse(bizProbityHouse));
    }

    /**
     * 删除廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityHouse:remove')")
    @Log(title = "廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizProbityHouseService.deleteBizProbityHouseByIds(ids));
    }
}
