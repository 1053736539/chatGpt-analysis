package com.cb.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.domain.TransferringStudentSupervise;
import com.cb.service.ITransferringStudentSuperviseService;
import com.cb.vo.StudentSuperviseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 选调生教育监督Controller
 * 
 * @author ruoyi
 * @date 2023-10-27
 */
@RestController
@RequestMapping("/transfer/supervise")
public class TransferringStudentSuperviseController extends BaseController
{
    @Autowired
    private ITransferringStudentSuperviseService transferringStudentSuperviseService;

    /**
     * 查询选调生教育监督列表
     */
//    @PreAuthorize("@ss.hasPermi('transfer:supervise:list')")
    @GetMapping("/list")
    public TableDataInfo list(StudentSuperviseVo transferringStudentSupervise)
    {
        startPage();
        List<StudentSuperviseVo> list = transferringStudentSuperviseService.selectTransferringStudentSuperviseList(transferringStudentSupervise);
        return getDataTable(list);
    }

    /**
     * 导出选调生教育监督列表
     */
//    @PreAuthorize("@ss.hasPermi('transfer:supervise:export')")
    @Log(title = "选调生教育监督", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(StudentSuperviseVo transferringStudentSupervise)
    {
        List<StudentSuperviseVo> list = transferringStudentSuperviseService.selectTransferringStudentSuperviseList(transferringStudentSupervise);
        ExcelUtil<StudentSuperviseVo> util = new ExcelUtil<StudentSuperviseVo>(StudentSuperviseVo.class);
        return util.exportExcel(list, "supervise");
    }

    /**
     * 获取选调生教育监督详细信息
     */
//    @PreAuthorize("@ss.hasPermi('transfer:supervise:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(transferringStudentSuperviseService.selectTransferringStudentSuperviseById(id));
    }

    /**
     * 新增选调生教育监督
     */
//    @PreAuthorize("@ss.hasPermi('transfer:supervise:add')")
    @Log(title = "选调生教育监督", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TransferringStudentSupervise transferringStudentSupervise)
    {
        return toAjax(transferringStudentSuperviseService.insertTransferringStudentSupervise(transferringStudentSupervise));
    }

    /**
     * 修改选调生教育监督
     */
//    @PreAuthorize("@ss.hasPermi('transfer:supervise:edit')")
    @Log(title = "选调生教育监督", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TransferringStudentSupervise transferringStudentSupervise)
    {
        return toAjax(transferringStudentSuperviseService.updateTransferringStudentSupervise(transferringStudentSupervise));
    }

    /**
     * 删除选调生教育监督
     */
//    @PreAuthorize("@ss.hasPermi('transfer:supervise:remove')")
    @Log(title = "选调生教育监督", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(transferringStudentSuperviseService.deleteTransferringStudentSuperviseByIds(ids));
    }
}
