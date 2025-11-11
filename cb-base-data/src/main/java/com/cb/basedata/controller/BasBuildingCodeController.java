package com.cb.basedata.controller;

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
import com.cb.basedata.domain.BasBuildingCode;
import com.cb.basedata.service.IBasBuildingCodeService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 楼盘编码Controller
 * 
 * @author ouyan
 * @date 2024-10-25
 */
@RestController
@RequestMapping("/basedata/buildingCode")
public class BasBuildingCodeController extends BaseController
{
    @Autowired
    private IBasBuildingCodeService basBuildingCodeService;

    /**
     * 查询楼盘编码列表
     */
    @PreAuthorize("@ss.hasPermi('basedata:buildingCode:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasBuildingCode basBuildingCode)
    {
        startPage();
        List<BasBuildingCode> list = basBuildingCodeService.selectBasBuildingCodeList(basBuildingCode);
        return getDataTable(list);
    }
    @GetMapping("/listAll")
    public AjaxResult listAll(BasBuildingCode basBuildingCode)
    {
        List<BasBuildingCode> list = basBuildingCodeService.selectBasBuildingCodeList(basBuildingCode);
        return AjaxResult.success(list);
    }

    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<BasBuildingCode> util = new ExcelUtil<>(BasBuildingCode.class);
        return util.importTemplateExcel("楼盘数据");
    }

    @Log(title = "楼盘编码导入", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('basedata:buildingCode:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<BasBuildingCode> util = new ExcelUtil<>(BasBuildingCode.class);
        List<BasBuildingCode> list = util.importExcel(file.getInputStream());
        String message = basBuildingCodeService.importBasBuildingCode(list, updateSupport);
        return success(message);
    }
    /**
     * 导出楼盘编码列表
     */
    @PreAuthorize("@ss.hasPermi('basedata:buildingCode:export')")
    @Log(title = "楼盘编码", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasBuildingCode basBuildingCode)
    {
        List<BasBuildingCode> list = basBuildingCodeService.selectBasBuildingCodeList(basBuildingCode);
        ExcelUtil<BasBuildingCode> util = new ExcelUtil<BasBuildingCode>(BasBuildingCode.class);
        return util.exportExcel(list, "buildingCode");
    }

    /**
     * 获取楼盘编码详细信息
     */
    @PreAuthorize("@ss.hasPermi('basedata:buildingCode:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(basBuildingCodeService.selectBasBuildingCodeById(id));
    }

    /**
     * 新增楼盘编码
     */
    @PreAuthorize("@ss.hasPermi('basedata:buildingCode:add')")
    @Log(title = "楼盘编码", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasBuildingCode basBuildingCode)
    {
        return toAjax(basBuildingCodeService.insertBasBuildingCode(basBuildingCode));
    }

    /**
     * 修改楼盘编码
     */
    @PreAuthorize("@ss.hasPermi('basedata:buildingCode:edit')")
    @Log(title = "楼盘编码", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasBuildingCode basBuildingCode)
    {
        return toAjax(basBuildingCodeService.updateBasBuildingCode(basBuildingCode));
    }

    /**
     * 删除楼盘编码
     */
    @PreAuthorize("@ss.hasPermi('basedata:buildingCode:remove')")
    @Log(title = "楼盘编码", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(basBuildingCodeService.deleteBasBuildingCodeByIds(ids));
    }
}
