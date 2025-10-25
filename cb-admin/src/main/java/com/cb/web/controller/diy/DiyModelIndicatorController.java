package com.cb.web.controller.diy;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.diy.domain.DiyModelIndicator;
import com.cb.diy.service.DiyEngineService;
import com.cb.diy.service.DiyModelIndicatorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * DIY模型指标Controller
 * @author xiehong
 */
@RestController
@RequestMapping("/diy/model/indicator")
public class DiyModelIndicatorController extends BaseController {
    private final DiyModelIndicatorService diyModelIndicatorService;
    // DiyEngineService
    private final DiyEngineService modelService;

    public DiyModelIndicatorController(DiyModelIndicatorService diyModelIndicatorService, DiyEngineService modelService) {
        this.diyModelIndicatorService = diyModelIndicatorService;
        this.modelService = modelService;
    }

    /**
     * 查询DIY模型指标列表
     */
//    @PreAuthorize("@ss.hasPermi('system:indicator:list')")
    @GetMapping("/list")
    public TableDataInfo list(DiyModelIndicator diyModelIndicator) {
        startPage();
        List<DiyModelIndicator> list = diyModelIndicatorService.selectDiyModelIndicatorList(diyModelIndicator);
        return getDataTable(list);
    }

    /**
     * 导出DIY模型指标列表
     */
//    @PreAuthorize("@ss.hasPermi('system:indicator:export')")
    @Log(title = "DIY模型指标", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DiyModelIndicator diyModelIndicator) {
        List<DiyModelIndicator> list = diyModelIndicatorService.selectDiyModelIndicatorList(diyModelIndicator);
        ExcelUtil<DiyModelIndicator> util = new ExcelUtil<DiyModelIndicator>(DiyModelIndicator.class);
        return util.exportExcel(list, "indicator");
    }

    /**
     * 获取DIY模型指标详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:indicator:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(diyModelIndicatorService.selectDiyModelIndicatorById(id));
    }

    /**
     * 新增DIY模型指标
     */
//    @PreAuthorize("@ss.hasPermi('system:indicator:add')")
    @Log(title = "DIY模型指标", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DiyModelIndicator diyModelIndicator) {
        return toAjax(diyModelIndicatorService.insertDiyModelIndicator(diyModelIndicator));
    }

    /**
     * 修改DIY模型指标
     */
//    @PreAuthorize("@ss.hasPermi('system:indicator:edit')")
    @Log(title = "DIY模型指标", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DiyModelIndicator diyModelIndicator) {
        return toAjax(diyModelIndicatorService.updateDiyModelIndicator(diyModelIndicator));
    }

    /**
     * 删除DIY模型指标
     */
//    @PreAuthorize("@ss.hasPermi('system:indicator:remove')")
    @Log(title = "DIY模型指标", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(diyModelIndicatorService.deleteDiyModelIndicatorByIds(ids));
    }
}
