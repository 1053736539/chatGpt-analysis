package com.cb.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.domain.TransferringStudentVillage;
import com.cb.service.ITransferringStudentVillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 选调生锻炼培养-驻村Controller
 * 
 * @author ruoyi
 * @date 2024-08-28
 */
@RestController
@RequestMapping("/transfer/village")
public class TransferringStudentVillageController extends BaseController
{
    @Autowired
    private ITransferringStudentVillageService transferringStudentVillageService;

    /**
     * 查询选调生锻炼培养-驻村列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TransferringStudentVillage transferringStudentVillage)
    {
        startPage();
        List<TransferringStudentVillage> list = transferringStudentVillageService.selectTransferringStudentVillageList(transferringStudentVillage);
        return getDataTable(list);
    }

    /**
     * 导出选调生锻炼培养-驻村列表
     */
    @Log(title = "选调生锻炼培养-驻村", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TransferringStudentVillage transferringStudentVillage)
    {
        List<TransferringStudentVillage> list = transferringStudentVillageService.selectTransferringStudentVillageList(transferringStudentVillage);
        ExcelUtil<TransferringStudentVillage> util = new ExcelUtil<TransferringStudentVillage>(TransferringStudentVillage.class);
        return util.exportExcel(list, "village");
    }

    /**
     * 获取选调生锻炼培养-驻村详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(transferringStudentVillageService.selectTransferringStudentVillageById(id));
    }

    /**
     * 新增选调生锻炼培养-驻村
     */
    @Log(title = "选调生锻炼培养-驻村", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TransferringStudentVillage transferringStudentVillage)
    {
        return toAjax(transferringStudentVillageService.insertTransferringStudentVillage(transferringStudentVillage));
    }

    /**
     * 修改选调生锻炼培养-驻村
     */
    @Log(title = "选调生锻炼培养-驻村", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TransferringStudentVillage transferringStudentVillage)
    {
        return toAjax(transferringStudentVillageService.updateTransferringStudentVillage(transferringStudentVillage));
    }

    /**
     * 删除选调生锻炼培养-驻村
     */
    @Log(title = "选调生锻炼培养-驻村", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(transferringStudentVillageService.deleteTransferringStudentVillageByIds(ids));
    }
}
