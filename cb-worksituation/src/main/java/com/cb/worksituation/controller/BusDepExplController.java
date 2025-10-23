package com.cb.worksituation.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.worksituation.domain.BusDepExpl;
import com.cb.worksituation.service.IBusDepExplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评分说明Controller
 *
 * @author ruoyi
 * @date 2025-10-11
 */
@RestController
@RequestMapping("/business/expl")
public class BusDepExplController extends BaseController {
    @Autowired
    private IBusDepExplService busDepExplService;

    /**
     * 查询评分说明列表
     */
    // @PreAuthorize("@ss.hasPermi('system:EXPL:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusDepExpl busDepExpl) {
        startPage();
        List<BusDepExpl> list = busDepExplService.selectBusDepExplList(busDepExpl);
        return getDataTable(list);
    }

    /**
     * 导出评分说明列表
     */
    //  @PreAuthorize("@ss.hasPermi('system:EXPL:export')")
    @Log(title = "评分说明", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusDepExpl busDepExpl) {
        List<BusDepExpl> list = busDepExplService.selectBusDepExplList(busDepExpl);
        ExcelUtil<BusDepExpl> util = new ExcelUtil<BusDepExpl>(BusDepExpl.class);
        return util.exportExcel(list, "EXPL");
    }

    /**
     * 获取评分说明详细信息
     */
    //  @PreAuthorize("@ss.hasPermi('system:EXPL:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(busDepExplService.selectBusDepExplById(id));
    }

    /**
     * 新增评分说明
     */
    //  @PreAuthorize("@ss.hasPermi('system:EXPL:add')")
    @Log(title = "评分说明", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusDepExpl busDepExpl) {
        return toAjax(busDepExplService.insertBusDepExpl(busDepExpl));
    }

    /**
     * 修改评分说明
     */
    // @PreAuthorize("@ss.hasPermi('system:EXPL:edit')")
    @Log(title = "评分说明", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusDepExpl busDepExpl) {
        return toAjax(busDepExplService.updateBusDepExpl(busDepExpl));
    }

    /**
     * 删除评分说明
     */
    //  @PreAuthorize("@ss.hasPermi('system:EXPL:remove')")
    @Log(title = "评分说明", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(busDepExplService.deleteBusDepExplByIds(ids));
    }
}
