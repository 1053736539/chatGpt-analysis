package com.cb.basedata.controller;

import java.util.List;
import java.util.Map;

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
import com.cb.basedata.domain.BasGasCostSource;
import com.cb.basedata.service.IBasGasCostSourceService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 燃气费-原始数据Controller
 *
 * @author ouyang
 * @date 2024-10-29
 */
@RestController
@RequestMapping("/basedata/gasCostSource")
public class BasGasCostSourceController extends BaseController {
    @Autowired
    private IBasGasCostSourceService basGasCostSourceService;

    /**
     * 查询燃气费-原始数据列表
     */
    @PreAuthorize("@ss.hasPermi('basedata:gasCostSource:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasGasCostSource basGasCostSource) {
        startPage();
        List<BasGasCostSource> list = basGasCostSourceService.selectBasGasCostSourceList(basGasCostSource);
        return getDataTable(list);
    }

    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<BasGasCostSource> util = new ExcelUtil<>(BasGasCostSource.class);
        return util.importTemplateExcel("燃气缴费源数据");
    }

    @Log(title = "燃气-原始数据导入", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<BasGasCostSource> util = new ExcelUtil<>(BasGasCostSource.class);
        List<BasGasCostSource> list = util.importExcel(file.getInputStream());
        String message = basGasCostSourceService.importData(list, updateSupport);
        return success(message);
    }

    /**
     * 导出燃气费-原始数据列表
     */
    @PreAuthorize("@ss.hasPermi('basedata:gasCostSource:export')")
    @Log(title = "燃气费-原始数据", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasGasCostSource basGasCostSource) {
        List<BasGasCostSource> list = basGasCostSourceService.selectBasGasCostSourceList(basGasCostSource);
        ExcelUtil<BasGasCostSource> util = new ExcelUtil<BasGasCostSource>(BasGasCostSource.class);
        return util.exportExcel(list, "gasCostSource");
    }

    /**
     * 获取燃气费-原始数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('basedata:gasCostSource:query')")
    @GetMapping(value = "/{sourceId}")
    public AjaxResult getInfo(@PathVariable("sourceId") String sourceId) {
        return AjaxResult.success(basGasCostSourceService.selectBasGasCostSourceById(sourceId));
    }

    /**
     * 新增燃气费-原始数据
     */
    @PreAuthorize("@ss.hasPermi('basedata:gasCostSource:add')")
    @Log(title = "燃气费-原始数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasGasCostSource basGasCostSource) {
        return toAjax(basGasCostSourceService.insertBasGasCostSource(basGasCostSource));
    }

    /**
     * 修改燃气费-原始数据
     */
    @PreAuthorize("@ss.hasPermi('basedata:gasCostSource:edit')")
    @Log(title = "燃气费-原始数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasGasCostSource basGasCostSource) {
        return toAjax(basGasCostSourceService.updateBasGasCostSource(basGasCostSource));
    }

    /**
     * 删除燃气费-原始数据
     */
    @PreAuthorize("@ss.hasPermi('basedata:gasCostSource:remove')")
    @Log(title = "燃气费-原始数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{sourceIds}")
    public AjaxResult remove(@PathVariable String[] sourceIds) {
        return toAjax(basGasCostSourceService.deleteBasGasCostSourceByIds(sourceIds));
    }


    @GetMapping(value = "/dataComparison/{sourceId}")
    public AjaxResult dataComparison(@PathVariable("sourceId") String sourceId){
        Map<String, Object> map = basGasCostSourceService.dataComparison(sourceId);
        return AjaxResult.success(map);
    }
}
