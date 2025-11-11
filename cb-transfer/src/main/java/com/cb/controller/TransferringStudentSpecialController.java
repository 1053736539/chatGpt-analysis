package com.cb.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.domain.TransferringStudentSpecial;
import com.cb.service.ITransferringStudentSpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 选调生锻炼培养-参与专项任务Controller
 * 
 * @author yangxin
 * @date 2024-08-28
 */
@RestController
@RequestMapping("/transfer/special")
public class TransferringStudentSpecialController extends BaseController
{
    @Autowired
    private ITransferringStudentSpecialService transferringStudentSpecialService;

    /**
     * 查询选调生锻炼培养-参与专项任务列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TransferringStudentSpecial transferringStudentSpecial)
    {
        startPage();
        List<TransferringStudentSpecial> list = transferringStudentSpecialService.selectTransferringStudentSpecialList(transferringStudentSpecial);
        return getDataTable(list);
    }

    /**
     * 导出选调生锻炼培养-参与专项任务列表
     */
    @Log(title = "选调生锻炼培养-参与专项任务", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TransferringStudentSpecial transferringStudentSpecial)
    {
        List<TransferringStudentSpecial> list = transferringStudentSpecialService.selectTransferringStudentSpecialList(transferringStudentSpecial);
        ExcelUtil<TransferringStudentSpecial> util = new ExcelUtil<TransferringStudentSpecial>(TransferringStudentSpecial.class);
        return util.exportExcel(list, "special");
    }

    /**
     * 获取选调生锻炼培养-参与专项任务详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(transferringStudentSpecialService.selectTransferringStudentSpecialById(id));
    }

    /**
     * 新增选调生锻炼培养-参与专项任务
     */
    @Log(title = "选调生锻炼培养-参与专项任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TransferringStudentSpecial transferringStudentSpecial)
    {
        return toAjax(transferringStudentSpecialService.insertTransferringStudentSpecial(transferringStudentSpecial));
    }

    /**
     * 修改选调生锻炼培养-参与专项任务
     */
    @Log(title = "选调生锻炼培养-参与专项任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TransferringStudentSpecial transferringStudentSpecial)
    {
        return toAjax(transferringStudentSpecialService.updateTransferringStudentSpecial(transferringStudentSpecial));
    }

    /**
     * 删除选调生锻炼培养-参与专项任务
     */
    @Log(title = "选调生锻炼培养-参与专项任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(transferringStudentSpecialService.deleteTransferringStudentSpecialByIds(ids));
    }
}
