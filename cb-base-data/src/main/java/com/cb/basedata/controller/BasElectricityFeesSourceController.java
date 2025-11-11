package com.cb.basedata.controller;

import java.util.List;
import java.util.Map;

import com.cb.basedata.domain.BasElectricityFeesSource;
import com.cb.basedata.service.IBasElectricityFeesSourceService;
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
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 电费-原始数据Controller
 * 
 * @author ouyang
 * @date 2024-10-29
 */
@RestController
@RequestMapping("/basedata/electricityFeesSource")
public class BasElectricityFeesSourceController extends BaseController
{
    @Autowired
    private IBasElectricityFeesSourceService basElectricityFeesSourceService;

    /**
     * 查询电费-原始数据列表
     */
    @PreAuthorize("@ss.hasPermi('basedata:electricityFeesSource:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasElectricityFeesSource basElectricityFeesSource)
    {
        startPage();
        List<BasElectricityFeesSource> list = basElectricityFeesSourceService.selectBasElectricityFeesSourceList(basElectricityFeesSource);
        return getDataTable(list);
    }

    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<BasElectricityFeesSource> util = new ExcelUtil<>(BasElectricityFeesSource.class);
        return util.importTemplateExcel("电费源数据");
    }

    @Log(title = "燃气-原始数据导入", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<BasElectricityFeesSource> util = new ExcelUtil<>(BasElectricityFeesSource.class);
        List<BasElectricityFeesSource> list = util.importExcel(file.getInputStream());
        String message = basElectricityFeesSourceService.importData(list, updateSupport);
        return success(message);
    }

    /**
     * 导出电费-原始数据列表
     */
    @PreAuthorize("@ss.hasPermi('basedata:electricityFeesSource:export')")
    @Log(title = "电费-原始数据", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasElectricityFeesSource basElectricityFeesSource)
    {
        List<BasElectricityFeesSource> list = basElectricityFeesSourceService.selectBasElectricityFeesSourceList(basElectricityFeesSource);
        ExcelUtil<BasElectricityFeesSource> util = new ExcelUtil<BasElectricityFeesSource>(BasElectricityFeesSource.class);
        return util.exportExcel(list, "electricityFeesSource");
    }

    /**
     * 获取电费-原始数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('basedata:electricityFeesSource:query')")
    @GetMapping(value = "/{sourceId}")
    public AjaxResult getInfo(@PathVariable("sourceId") String sourceId)
    {
        return AjaxResult.success(basElectricityFeesSourceService.selectBasElectricityFeesSourceById(sourceId));
    }

    /**
     * 新增电费-原始数据
     */
    @PreAuthorize("@ss.hasPermi('basedata:electricityFeesSource:add')")
    @Log(title = "电费-原始数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasElectricityFeesSource basElectricityFeesSource)
    {
        return toAjax(basElectricityFeesSourceService.insertBasElectricityFeesSource(basElectricityFeesSource));
    }

    /**
     * 修改电费-原始数据
     */
    @PreAuthorize("@ss.hasPermi('basedata:electricityFeesSource:edit')")
    @Log(title = "电费-原始数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasElectricityFeesSource basElectricityFeesSource)
    {
        return toAjax(basElectricityFeesSourceService.updateBasElectricityFeesSource(basElectricityFeesSource));
    }

    /**
     * 删除电费-原始数据
     */
    @PreAuthorize("@ss.hasPermi('basedata:electricityFeesSource:remove')")
    @Log(title = "电费-原始数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sourceIds}")
    public AjaxResult remove(@PathVariable String[] sourceIds)
    {
        return toAjax(basElectricityFeesSourceService.deleteBasElectricityFeesSourceByIds(sourceIds));
    }

    @GetMapping(value = "/dataComparison/{sourceId}")
    public AjaxResult dataComparison(@PathVariable("sourceId") String sourceId){
        Map<String, Object> map = basElectricityFeesSourceService.dataComparison(sourceId);
        return AjaxResult.success(map);
    }
}
