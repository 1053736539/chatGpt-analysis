package com.cb.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.domain.TransferringStudentHealth;
import com.cb.service.ITransferringStudentHealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 监督管理-身心健康情况Controller
 *
 * @author yangxin
 * @date 2024-08-27
 */
@RestController
@RequestMapping("/transfer/health")
public class TransferringStudentHealthController extends BaseController
{
    @Autowired
    private ITransferringStudentHealthService transferringStudentHealthService;

    /**
     * 查询监督管理-身心健康情况列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TransferringStudentHealth transferringStudentHealth)
    {
        startPage();
        List<TransferringStudentHealth> list = transferringStudentHealthService.selectTransferringStudentHealthList(transferringStudentHealth);
        return getDataTable(list);
    }

    /**
     * 导出监督管理-身心健康情况列表
     */
    @Log(title = "监督管理-身心健康情况", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TransferringStudentHealth transferringStudentHealth)
    {
        List<TransferringStudentHealth> list = transferringStudentHealthService.selectTransferringStudentHealthList(transferringStudentHealth);
        ExcelUtil<TransferringStudentHealth> util = new ExcelUtil<TransferringStudentHealth>(TransferringStudentHealth.class);
        return util.exportExcel(list, "health");
    }

    /**
     * 获取监督管理-身心健康情况详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(transferringStudentHealthService.selectTransferringStudentHealthById(id));
    }

    /**
     * 新增监督管理-身心健康情况
     */
    @Log(title = "监督管理-身心健康情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TransferringStudentHealth transferringStudentHealth)
    {
        return toAjax(transferringStudentHealthService.insertTransferringStudentHealth(transferringStudentHealth));
    }

    /**
     * 修改监督管理-身心健康情况
     */
    @Log(title = "监督管理-身心健康情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TransferringStudentHealth transferringStudentHealth)
    {
        return toAjax(transferringStudentHealthService.updateTransferringStudentHealth(transferringStudentHealth));
    }

    /**
     * 删除监督管理-身心健康情况
     */
    @Log(title = "监督管理-身心健康情况", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(transferringStudentHealthService.deleteTransferringStudentHealthByIds(ids));
    }
}