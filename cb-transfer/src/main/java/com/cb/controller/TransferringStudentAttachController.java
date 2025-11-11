package com.cb.controller;

import java.util.List;

import com.cb.domain.TransferringStudentAttach;
import com.cb.service.ITransferringStudentAttachService;
import com.cb.vo.AttachVo;
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

/**
 * 选调生其他信息Controller
 * 
 * @author ruoyi
 * @date 2023-10-31
 */
@RestController
@RequestMapping("/transfer/attach")
public class TransferringStudentAttachController extends BaseController
{
    @Autowired
    private ITransferringStudentAttachService transferringStudentAttachService;

    /**
     * 查询选调生其他信息列表
     */
//    @PreAuthorize("@ss.hasPermi('transfer:attach:list')")
    @GetMapping("/list")
    public TableDataInfo list(AttachVo transferringStudentAttach)
    {
        startPage();
        List<AttachVo> list = transferringStudentAttachService.selectTransferringStudentAttachList(transferringStudentAttach);
        return getDataTable(list);
    }

    /**
     * 导出选调生其他信息列表
     */
//    @PreAuthorize("@ss.hasPermi('transfer:attach:export')")
    @Log(title = "选调生其他信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AttachVo transferringStudentAttach)
    {
        List<AttachVo> list = transferringStudentAttachService.selectTransferringStudentAttachList(transferringStudentAttach);
        ExcelUtil<AttachVo> util = new ExcelUtil<AttachVo>(AttachVo.class);
        return util.exportExcel(list, "attach");
    }

    /**
     * 获取选调生其他信息详细信息
     */
//    @PreAuthorize("@ss.hasPermi('transfer:attach:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(transferringStudentAttachService.selectTransferringStudentAttachById(id));
    }

    /**
     * 新增选调生其他信息
     */
//    @PreAuthorize("@ss.hasPermi('transfer:attach:add')")
    @Log(title = "选调生其他信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TransferringStudentAttach transferringStudentAttach)
    {
        return toAjax(transferringStudentAttachService.insertTransferringStudentAttach(transferringStudentAttach));
    }

    /**
     * 修改选调生其他信息
     */
//    @PreAuthorize("@ss.hasPermi('transfer:attach:edit')")
    @Log(title = "选调生其他信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TransferringStudentAttach transferringStudentAttach)
    {
        return toAjax(transferringStudentAttachService.updateTransferringStudentAttach(transferringStudentAttach));
    }

    /**
     * 删除选调生其他信息
     */
//    @PreAuthorize("@ss.hasPermi('transfer:attach:remove')")
    @Log(title = "选调生其他信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(transferringStudentAttachService.deleteTransferringStudentAttachByIds(ids));
    }
}
