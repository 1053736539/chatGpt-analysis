package com.cb.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.domain.TransferringStudentAppoint;
import com.cb.framework.web.service.TokenService;
import com.cb.service.ITransferringStudentAppointService;
import com.cb.vo.StudentAppointVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 选调生选拔任用Controller
 * 
 * @author ruoyi
 * @date 2023-10-27
 */
@RestController
@RequestMapping("/transfer/appoint")
public class TransferringStudentAppointController extends BaseController
{
    @Autowired
    private ITransferringStudentAppointService transferringStudentAppointService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询选调生选拔任用列表
     */
//    @PreAuthorize("@ss.hasPermi('transfer:appoint:list')")
    @GetMapping("/list")
    public TableDataInfo list(StudentAppointVo studentAppointVo)
    {
        Map<String, Object> params = studentAppointVo.getParams();
        if(null != params){
            params.entrySet().forEach(entry -> {
                if("null".equals(entry.getValue())){
                    entry.setValue(null);
                }
            });
        }
        startPage();
        List<StudentAppointVo> list = transferringStudentAppointService.selectTransferringStudentAppointList(studentAppointVo);
        return getDataTable(list);
    }

    /**
     * 导出选调生选拔任用列表
     */
//    @PreAuthorize("@ss.hasPermi('transfer:appoint:export')")
    @Log(title = "选调生选拔任用", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(StudentAppointVo transferringStudentAppoint)
    {
        List<StudentAppointVo> list = transferringStudentAppointService.selectTransferringStudentAppointList(transferringStudentAppoint);
        ExcelUtil<StudentAppointVo> util = new ExcelUtil<StudentAppointVo>(StudentAppointVo.class);
        return util.exportExcel(list, "appoint");
    }

    /**
     * 获取选调生选拔任用详细信息
     */
//    @PreAuthorize("@ss.hasPermi('transfer:appoint:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(transferringStudentAppointService.selectTransferringStudentAppointById(id));
    }

    /**
     * 新增选调生选拔任用
     */
//    @PreAuthorize("@ss.hasPermi('transfer:appoint:add')")
    @Log(title = "选调生选拔任用", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TransferringStudentAppoint transferringStudentAppoint)
    {
        return toAjax(transferringStudentAppointService.insertTransferringStudentAppoint(transferringStudentAppoint));
    }

    /**
     * 修改选调生选拔任用
     */
//    @PreAuthorize("@ss.hasPermi('transfer:appoint:edit')")
    @Log(title = "选调生选拔任用", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TransferringStudentAppoint transferringStudentAppoint)
    {
        return toAjax(transferringStudentAppointService.updateTransferringStudentAppoint(transferringStudentAppoint));
    }

    /**
     * 删除选调生选拔任用
     */
//    @PreAuthorize("@ss.hasPermi('transfer:appoint:remove')")
    @Log(title = "选调生选拔任用", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(transferringStudentAppointService.deleteTransferringStudentAppointByIds(ids));
    }
}
