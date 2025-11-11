package com.cb.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.domain.TransferringStudentExamine;
import com.cb.service.ITransferringStudentExamineService;
import com.cb.vo.StudentExamineVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 选调生考核评价Controller
 * 
 * @author ruoyi
 * @date 2023-10-27
 */
@RestController
@RequestMapping("/transfer/examine")
public class TransferringStudentExamineController extends BaseController
{
    @Autowired
    private ITransferringStudentExamineService transferringStudentExamineService;

    /**
     * 查询选调生考核评价列表
     */
//    @PreAuthorize("@ss.hasPermi('transfer:examine:list')")
    @GetMapping("/list")
    public TableDataInfo list(StudentExamineVo transferringStudentExamine)
    {
        startPage();
        List<StudentExamineVo> list = transferringStudentExamineService.selectTransferringStudentExamineList(transferringStudentExamine);
        return getDataTable(list);
    }

    /**
     * 导出选调生考核评价列表
     */
//    @PreAuthorize("@ss.hasPermi('transfer:examine:export')")
    @Log(title = "选调生考核评价", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(StudentExamineVo transferringStudentExamine)
    {
        List<StudentExamineVo> list = transferringStudentExamineService.selectTransferringStudentExamineList(transferringStudentExamine);
        ExcelUtil<StudentExamineVo> util = new ExcelUtil<StudentExamineVo>(StudentExamineVo.class);
        return util.exportExcel(list, "examine");
    }

    /**
     * 获取选调生考核评价详细信息
     */
//    @PreAuthorize("@ss.hasPermi('transfer:examine:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(transferringStudentExamineService.selectTransferringStudentExamineById(id));
    }

    /**
     * 新增选调生考核评价
     */
//    @PreAuthorize("@ss.hasPermi('transfer:examine:add')")
    @Log(title = "选调生考核评价", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TransferringStudentExamine transferringStudentExamine)
    {
        return toAjax(transferringStudentExamineService.insertTransferringStudentExamine(transferringStudentExamine));
    }

    /**
     * 修改选调生考核评价
     */
//    @PreAuthorize("@ss.hasPermi('transfer:examine:edit')")
    @Log(title = "选调生考核评价", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TransferringStudentExamine transferringStudentExamine)
    {
        return toAjax(transferringStudentExamineService.updateTransferringStudentExamine(transferringStudentExamine));
    }

    /**
     * 删除选调生考核评价
     */
//    @PreAuthorize("@ss.hasPermi('transfer:examine:remove')")
    @Log(title = "选调生考核评价", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(transferringStudentExamineService.deleteTransferringStudentExamineByIds(ids));
    }
}
