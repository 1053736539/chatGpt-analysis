package com.cb.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.domain.TransferringStudentCulture;
import com.cb.service.ITransferringStudentCultureService;
import com.cb.vo.StudentCultureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 选调生培养Controller
 * 
 * @author ruoyi
 * @date 2023-10-27
 */
@RestController
@RequestMapping("/transfer/culture")
public class TransferringStudentCultureController extends BaseController
{
    @Autowired
    private ITransferringStudentCultureService transferringStudentCultureService;

    /**
     * 查询选调生培养列表
     */
//    @PreAuthorize("@ss.hasPermi('transfer:culture:list')")
    @GetMapping("/list")
    public TableDataInfo list(StudentCultureVo studentCultureVo)
    {
        startPage();
        List<StudentCultureVo> list = transferringStudentCultureService.selectTransferringStudentCultureList(studentCultureVo);
        return getDataTable(list);
    }

    /**
     * 导出选调生培养列表
     */
//    @PreAuthorize("@ss.hasPermi('transfer:culture:export')")
    @Log(title = "选调生培养", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(StudentCultureVo studentCultureVo)
    {
        List<StudentCultureVo> list = transferringStudentCultureService.selectTransferringStudentCultureList(studentCultureVo);
        ExcelUtil<StudentCultureVo> util = new ExcelUtil<StudentCultureVo>(StudentCultureVo.class);
        return util.exportExcel(list, "culture");
    }

    /**
     * 获取选调生培养详细信息
     */
//    @PreAuthorize("@ss.hasPermi('transfer:culture:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(transferringStudentCultureService.selectTransferringStudentCultureById(id));
    }

    /**
     * 新增选调生培养
     */
//    @PreAuthorize("@ss.hasPermi('transfer:culture:add')")
    @Log(title = "选调生培养", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TransferringStudentCulture transferringStudentCulture)
    {
        return toAjax(transferringStudentCultureService.insertTransferringStudentCulture(transferringStudentCulture));
    }

    /**
     * 修改选调生培养
     */
//    @PreAuthorize("@ss.hasPermi('transfer:culture:edit')")
    @Log(title = "选调生培养", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TransferringStudentCulture transferringStudentCulture)
    {
        return toAjax(transferringStudentCultureService.updateTransferringStudentCulture(transferringStudentCulture));
    }

    /**
     * 删除选调生培养
     */
//    @PreAuthorize("@ss.hasPermi('transfer:culture:remove')")
    @Log(title = "选调生培养", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(transferringStudentCultureService.deleteTransferringStudentCultureByIds(ids));
    }
}
