package com.cb.worksituation.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.worksituation.domain.BusDepExpl;
import com.cb.worksituation.domain.BusDepReview;
import com.cb.worksituation.service.IBusDepReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门评分Controller
 *
 * @author ruoyi
 * @date 2025-10-11
 */
@RestController
@RequestMapping("/business/review")
public class BusDepReviewController extends BaseController
{
    @Autowired
    private IBusDepReviewService busDepReviewService;

    /**
     * 查询部门评分列表
     */
    //  @PreAuthorize("@ss.hasPermi('system:REVIEW:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusDepReview busDepReview)
    {
        startPage();
        List<BusDepReview> list = busDepReviewService.selectBusDepReviewList(busDepReview);
        return getDataTable(list);
    }

    /**
     * 查询表列展示
     */
    //  @PreAuthorize("@ss.hasPermi('system:EXPL:list')")
    @GetMapping("/getDisplayHeaderList")
    public AjaxResult getDisplayHeaderList(String id) {
        BusDepReview busDepReview = busDepReviewService.getDisplayHeaderList(id);
        return AjaxResult.success(busDepReview);
    }

    /**
     * 导出部门评分列表
     */
    //  @PreAuthorize("@ss.hasPermi('system:REVIEW:export')")
    @Log(title = "部门评分", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusDepReview busDepReview)
    {
        List<BusDepReview> list = busDepReviewService.selectBusDepReviewList(busDepReview);
        ExcelUtil<BusDepReview> util = new ExcelUtil<BusDepReview>(BusDepReview.class);
        return util.exportExcel(list, "REVIEW");
    }

    /**
     * 获取部门评分详细信息
     */
    //  @PreAuthorize("@ss.hasPermi('system:REVIEW:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(busDepReviewService.selectBusDepReviewById(id));
    }

    /**
     * 新增部门评分
     */
    //  @PreAuthorize("@ss.hasPermi('system:REVIEW:add')")
    @Log(title = "部门评分", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusDepReview busDepReview)
    {
        return toAjax(busDepReviewService.insertBusDepReview(busDepReview));
    }

    /**
     * 修改部门评分
     */
    //  @PreAuthorize("@ss.hasPermi('system:REVIEW:edit')")
    @Log(title = "部门评分", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusDepReview busDepReview)
    {
        return toAjax(busDepReviewService.updateBusDepReview(busDepReview));
    }

    /**
     * 删除部门评分
     */
    //  @PreAuthorize("@ss.hasPermi('system:REVIEW:remove')")
    @Log(title = "部门评分", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(busDepReviewService.deleteBusDepReviewByIds(ids));
    }
}
