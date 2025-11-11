package com.cb.basedata.controller;

import java.util.List;
import java.util.Map;

import com.cb.basedata.domain.BasWaterRentSource;
import com.cb.basedata.service.IBasWaterRentSourceService;
import com.cb.common.utils.poi.ExcelUtil;
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
import com.cb.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 水费-原始数据Controller
 *
 * @author ouyang
 * @date 2024-10-29
 */
@RestController
@RequestMapping("/basedata/waterRentSource")
public class BasWaterRentSourceController extends BaseController {
    @Autowired
    private IBasWaterRentSourceService basWaterRentSourceService;

    /**
     * 查询水费-原始数据列表
     */
    @PreAuthorize("@ss.hasPermi('basedata:waterRentSource:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasWaterRentSource basWaterRentSource) {
        startPage();
        List<BasWaterRentSource> list = basWaterRentSourceService.selectBasWaterRentSourceList(basWaterRentSource);
        return getDataTable(list);
    }

    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<BasWaterRentSource> util = new ExcelUtil<>(BasWaterRentSource.class);
        return util.importTemplateExcel("用水缴费源数据");
    }

    @Log(title = "水费-原始数据导入", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<BasWaterRentSource> util = new ExcelUtil<>(BasWaterRentSource.class);
        List<BasWaterRentSource> list = util.importExcel(file.getInputStream());
        String message = basWaterRentSourceService.importData(list, updateSupport);
        return success(message);
    }

    /**
     * 导出水费-原始数据列表
     */
    @PreAuthorize("@ss.hasPermi('basedata:waterRentSource:export')")
    @Log(title = "水费-原始数据", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasWaterRentSource basWaterRentSource) {
        List<BasWaterRentSource> list = basWaterRentSourceService.selectBasWaterRentSourceList(basWaterRentSource);
        ExcelUtil<BasWaterRentSource> util = new ExcelUtil<BasWaterRentSource>(BasWaterRentSource.class);
        return util.exportExcel(list, "waterRentSource");
    }

    /**
     * 获取水费-原始数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('basedata:waterRentSource:query')")
    @GetMapping(value = "/{sourceId}")
    public AjaxResult getInfo(@PathVariable("sourceId") String sourceId) {
        return AjaxResult.success(basWaterRentSourceService.selectBasWaterRentSourceById(sourceId));
    }

    /**
     * 新增水费-原始数据
     */
    @PreAuthorize("@ss.hasPermi('basedata:waterRentSource:add')")
    @Log(title = "水费-原始数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasWaterRentSource basWaterRentSource) {
        return toAjax(basWaterRentSourceService.insertBasWaterRentSource(basWaterRentSource));
    }

    /**
     * 修改水费-原始数据
     */
    @PreAuthorize("@ss.hasPermi('basedata:waterRentSource:edit')")
    @Log(title = "水费-原始数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasWaterRentSource basWaterRentSource) {
        return toAjax(basWaterRentSourceService.updateBasWaterRentSource(basWaterRentSource));
    }

    /**
     * 删除水费-原始数据
     */
    @PreAuthorize("@ss.hasPermi('basedata:waterRentSource:remove')")
    @Log(title = "水费-原始数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{sourceIds}")
    public AjaxResult remove(@PathVariable String[] sourceIds) {
        return toAjax(basWaterRentSourceService.deleteBasWaterRentSourceByIds(sourceIds));
    }

    @GetMapping(value = "/dataComparison/{sourceId}")
    public AjaxResult dataComparison(@PathVariable("sourceId") String sourceId){
        Map<String, Object> map = basWaterRentSourceService.dataComparison(sourceId);
        return AjaxResult.success(map);
    }

}
