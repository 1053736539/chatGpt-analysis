package com.cb.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.domain.TransferringStudent;
import com.cb.service.ITransferringStudentService;
import com.cb.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 选调生Controller
 * 
 * @author ruoyi
 * @date 2023-10-31
 */
@RestController
@RequestMapping("/transfer/student")
public class TransferringStudentController extends BaseController
{
    @Autowired
    private ITransferringStudentService transferringStudentService;

    /**
     * 查询选调生列表
     */
//    @PreAuthorize("@ss.hasPermi('transfer:student:list')")
    @GetMapping("/list")
    public TableDataInfo list(StudentVo transferringStudent)
    {
        startPage();
        List<StudentVo> list = transferringStudentService.selectTransferringStudentList(transferringStudent);
        return getDataTable(list);
    }

    /**
     * 导出选调生列表
     */
//    @PreAuthorize("@ss.hasPermi('transfer:student:export')")
    @Log(title = "选调生", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(StudentVo transferringStudent)
    {
        List<StudentVo> list = transferringStudentService.selectTransferringStudentList(transferringStudent);
        ExcelUtil<StudentVo> util = new ExcelUtil<StudentVo>(StudentVo.class);
        return util.exportExcel(list, "student");
    }

    /**
     * 获取选调生详细信息
     */
//    @PreAuthorize("@ss.hasPermi('transfer:student:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") String userId)
    {
        return AjaxResult.success(transferringStudentService.selectTransferringStudentById(userId));
    }

    /**
     * 新增选调生
     */
//    @PreAuthorize("@ss.hasPermi('transfer:student:add')")
    @Log(title = "选调生", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TransferringStudent transferringStudent)
    {
        return toAjax(transferringStudentService.updateTransferringStudent(transferringStudent));
    }

    /**
     * 修改选调生
     */
//    @PreAuthorize("@ss.hasPermi('transfer:student:edit')")
    @Log(title = "选调生", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TransferringStudent transferringStudent)
    {
        return toAjax(transferringStudentService.updateTransferringStudent(transferringStudent));
    }

    /**
     * 删除选调生
     */
//    @PreAuthorize("@ss.hasPermi('transfer:student:remove')")
    @Log(title = "选调生", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable String[] userIds)
    {
        return toAjax(transferringStudentService.deleteTransferringStudentByIds(userIds));
    }
}
