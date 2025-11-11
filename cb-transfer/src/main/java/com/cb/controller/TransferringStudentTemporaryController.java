package com.cb.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.domain.TransferringStudentTemporary;
import com.cb.service.ITransferringStudentTemporaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 选调生锻炼培养-挂职Controller
 * 
 * @author yangxin
 * @date 2024-08-28
 */
@RestController
@RequestMapping("/transfer/temporary")
public class TransferringStudentTemporaryController extends BaseController
{
    @Autowired
    private ITransferringStudentTemporaryService transferringStudentTemporaryService;

    /**
     * 查询选调生锻炼培养-挂职列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TransferringStudentTemporary transferringStudentTemporary)
    {
        startPage();
        List<TransferringStudentTemporary> list = transferringStudentTemporaryService.selectTransferringStudentTemporaryList(transferringStudentTemporary);
        return getDataTable(list);
    }

    /**
     * 导出选调生锻炼培养-挂职列表
     */
    @Log(title = "选调生锻炼培养-挂职", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TransferringStudentTemporary transferringStudentTemporary)
    {
        List<TransferringStudentTemporary> list = transferringStudentTemporaryService.selectTransferringStudentTemporaryList(transferringStudentTemporary);
        ExcelUtil<TransferringStudentTemporary> util = new ExcelUtil<TransferringStudentTemporary>(TransferringStudentTemporary.class);
        return util.exportExcel(list, "temporary");
    }

    /**
     * 获取选调生锻炼培养-挂职详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(transferringStudentTemporaryService.selectTransferringStudentTemporaryById(id));
    }

    /**
     * 新增选调生锻炼培养-挂职
     */
    @Log(title = "选调生锻炼培养-挂职", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TransferringStudentTemporary transferringStudentTemporary)
    {
        return toAjax(transferringStudentTemporaryService.insertTransferringStudentTemporary(transferringStudentTemporary));
    }

    /**
     * 修改选调生锻炼培养-挂职
     */
    @Log(title = "选调生锻炼培养-挂职", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TransferringStudentTemporary transferringStudentTemporary)
    {
        return toAjax(transferringStudentTemporaryService.updateTransferringStudentTemporary(transferringStudentTemporary));
    }

    /**
     * 删除选调生锻炼培养-挂职
     */
    @Log(title = "选调生锻炼培养-挂职", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(transferringStudentTemporaryService.deleteTransferringStudentTemporaryByIds(ids));
    }

    /**
     * 获取工作岗位字典
     * @return
     */
    @GetMapping("/getWorkPostDict")
    public AjaxResult getWorkPostDict(){
        return AjaxResult.success(transferringStudentTemporaryService.getWorkPostDict());
    }

}
