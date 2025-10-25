package com.cb.web.controller.system;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.domain.entity.SysUserSecondmentWorkInfo;
import com.cb.common.core.domain.vo.SysUserSecondmentWorkInfoVo;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.system.service.ISysUserSecondmentWorkInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 人员借调工作信息Controller
 *
 * @author huhaojie
 * @date 2023-12-21
 */
@RestController
@RequestMapping("/system/secondmentWorkInfo")
public class SysUserSecondmentWorkInfoController extends BaseController {
    @Autowired
    private ISysUserSecondmentWorkInfoService sysUserSecondmentWorkInfoService;

    /**
     * 查询人员借调工作信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:secondmentWorkInfo:list')")
    @GetMapping("/list")
    /*public TableDataInfo list(SysUserSecondmentWorkInfo sysUserSecondmentWorkInfo) {
        startPage();
        List<SysUserSecondmentWorkInfo> list = sysUserSecondmentWorkInfoService.selectSysUserSecondmentWorkInfoList(sysUserSecondmentWorkInfo);
        return getDataTable(list);
    }*/
    public TableDataInfo list(SysUserSecondmentWorkInfoVo sysUserSecondmentWorkInfoVo) {
        startPage();
        List<SysUserSecondmentWorkInfoVo> list = sysUserSecondmentWorkInfoService.selectSysUserSecondmentWorkInfoVoList(sysUserSecondmentWorkInfoVo);
        return getDataTable(list);
    }

    /**
     * 导出人员借调工作信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:secondmentWorkInfo:export')")
    @Log(title = "人员借调工作信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysUserSecondmentWorkInfo sysUserSecondmentWorkInfo) {
        List<SysUserSecondmentWorkInfo> list = sysUserSecondmentWorkInfoService.selectSysUserSecondmentWorkInfoList(sysUserSecondmentWorkInfo);
        ExcelUtil<SysUserSecondmentWorkInfo> util = new ExcelUtil<SysUserSecondmentWorkInfo>(SysUserSecondmentWorkInfo.class);
        return util.exportExcel(list, "UserSecondmentWorkInfo");
    }

    /**
     * 获取人员借调工作信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:secondmentWorkInfo:query')")
    @GetMapping(value = "/{secondmentWorkInfoId}")
    public AjaxResult getInfo(@PathVariable("secondmentWorkInfoId") String secondmentWorkInfoId) {
        return AjaxResult.success(sysUserSecondmentWorkInfoService.selectSysUserSecondmentWorkInfoById(secondmentWorkInfoId));
    }

    /**
     * 新增人员借调工作信息
     */
    @PreAuthorize("@ss.hasPermi('system:secondmentWorkInfo:add')")
    @Log(title = "人员借调工作信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUserSecondmentWorkInfo sysUserSecondmentWorkInfo) {
        return toAjax(sysUserSecondmentWorkInfoService.insertSysUserSecondmentWorkInfo(sysUserSecondmentWorkInfo));
    }

    /**
     * 修改人员借调工作信息
     */
    @PreAuthorize("@ss.hasPermi('system:secondmentWorkInfo:edit')")
    @Log(title = "人员借调工作信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUserSecondmentWorkInfo sysUserSecondmentWorkInfo) {
        return toAjax(sysUserSecondmentWorkInfoService.updateSysUserSecondmentWorkInfo(sysUserSecondmentWorkInfo));
    }

    /**
     * 删除人员借调工作信息
     */
    @PreAuthorize("@ss.hasPermi('system:secondmentWorkInfo:remove')")
    @Log(title = "人员借调工作信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{secondmentWorkInfoIds}")
    public AjaxResult remove(@PathVariable String[] secondmentWorkInfoIds) {
        return toAjax(sysUserSecondmentWorkInfoService.deleteSysUserSecondmentWorkInfoByIds(secondmentWorkInfoIds));
    }
}
