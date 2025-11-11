package com.cb.leave.controller;

import java.util.List;

import com.cb.leave.domain.vo.UserLeaveVo;
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
import com.cb.leave.domain.LeaveTypes;
import com.cb.leave.service.ILeaveTypesService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 请休假类型Controller
 * 
 * @author ruoyi
 * @date 2024-09-09
 */
@RestController
@RequestMapping("/leave/types")
public class LeaveTypesController extends BaseController
{
    @Autowired
    private ILeaveTypesService leaveTypesService;

    /**
     * 查询请休假类型列表
     */
//    @PreAuthorize("@ss.hasPermi('leave:types:list')")
    @GetMapping("/list")
    public TableDataInfo list(LeaveTypes leaveTypes)
    {
        startPage();
        List<LeaveTypes> list = leaveTypesService.selectLeaveTypesList(leaveTypes);
        return getDataTable(list);
    }

    /**
     * 导出请休假类型列表
     */
    @PreAuthorize("@ss.hasPermi('leave:types:export')")
    @Log(title = "请休假类型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LeaveTypes leaveTypes)
    {
        List<LeaveTypes> list = leaveTypesService.selectLeaveTypesList(leaveTypes);
        ExcelUtil<LeaveTypes> util = new ExcelUtil<LeaveTypes>(LeaveTypes.class);
        return util.exportExcel(list, "types");
    }

    /**
     * 获取请休假类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('leave:types:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(leaveTypesService.selectLeaveTypesById(id));
    }

    /**
     * 新增请休假类型
     */
    @PreAuthorize("@ss.hasPermi('leave:types:add')")
    @Log(title = "请休假类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LeaveTypes leaveTypes)
    {
        return toAjax(leaveTypesService.insertLeaveTypes(leaveTypes));
    }

    /**
     * 修改请休假类型
     */
    @PreAuthorize("@ss.hasPermi('leave:types:edit')")
    @Log(title = "请休假类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LeaveTypes leaveTypes)
    {
        return toAjax(leaveTypesService.updateLeaveTypes(leaveTypes));
    }

    /**
     * 删除请休假类型
     */
    @PreAuthorize("@ss.hasPermi('leave:types:remove')")
    @Log(title = "请休假类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(leaveTypesService.deleteLeaveTypesByIds(ids));
    }

    /**
     * 获取用户请休假类型和剩余天数
     * @param vo
     * @return
     */

    @GetMapping("/userLeaveVoList")
    public AjaxResult queryUserLeaveVoList(UserLeaveVo vo) {
        List<UserLeaveVo> list = leaveTypesService.selectUserLeaveVoList(vo);
        return AjaxResult.success(list);
    }
}
