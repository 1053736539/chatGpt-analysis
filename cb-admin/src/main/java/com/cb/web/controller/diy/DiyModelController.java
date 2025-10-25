package com.cb.web.controller.diy;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.diy.domain.DiyModel;
import com.cb.diy.service.DiyEngineService;
import com.cb.diy.service.DiyModelService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DIY模型Controller
 * @author xiehong
 */
@RestController
@RequestMapping("/diy/model")
public class DiyModelController extends BaseController {
    private final DiyModelService diyModelService;

    public DiyModelController(DiyModelService diyModelService, DiyEngineService modelService) {
        this.diyModelService = diyModelService;
    }

    /**
     * 查询DIY模型列表
     */
//    @PreAuthorize("@ss.hasPermi('diy:model:list')")
    @GetMapping("/list")
    public TableDataInfo list(DiyModel diyModel) {
        startPage();
        List<DiyModel> list = diyModelService.selectDiyModelList(diyModel);
        return getDataTable(list);
    }

    /**
     * 导出DIY模型列表
     */
//    @PreAuthorize("@ss.hasPermi('diy:model:export')")
    @Log(title = "DIY模型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DiyModel diyModel) {
        List<DiyModel> list = diyModelService.selectDiyModelList(diyModel);
        ExcelUtil<DiyModel> util = new ExcelUtil<>(DiyModel.class);
        return util.exportExcel(list, "model");
    }

    /**
     * 获取DIY模型详细信息
     */
//    @PreAuthorize("@ss.hasPermi('diy:model:query')")
    @GetMapping(value = "/{modelId}")
    public AjaxResult getInfo(@PathVariable("modelId") Long modelId) {
        return AjaxResult.success(diyModelService.selectDiyModelById(modelId));
    }

    /**
     * 新增DIY模型
     */
//    @PreAuthorize("@ss.hasPermi('diy:model:add')")
    @Log(title = "DIY模型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DiyModel diyModel) {
        return toAjax(diyModelService.insertDiyModel(diyModel));
    }

    /**
     * 修改DIY模型
     */
//    @PreAuthorize("@ss.hasPermi('diy:model:edit')")
    @Log(title = "DIY模型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DiyModel diyModel) {
        return toAjax(diyModelService.updateDiyModel(diyModel));
    }

    /**
     * 删除DIY模型
     */
//    @PreAuthorize("@ss.hasPermi('diy:model:remove')")
    @Log(title = "DIY模型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{modelIds}")
    public AjaxResult remove(@PathVariable Long[] modelIds) {
        return toAjax(diyModelService.deleteDiyModelByIds(modelIds));
    }

    /**
     * @Auther: yangcd
     * @Date: 2024/10/31 17:04
     * @param modelId
     * @Description: 根据modelId删除DIY模型
     */
    @Log(title = "DIY模型", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{modelId}")
    public AjaxResult delete(@PathVariable Long modelId) {
        return toAjax(diyModelService.deleteDiyModelById(modelId));
    }

}
