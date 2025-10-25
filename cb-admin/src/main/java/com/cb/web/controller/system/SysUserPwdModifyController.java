package com.cb.web.controller.system;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.system.domain.SysUserPwdModify;
import com.cb.system.service.ISysUserPwdModifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 密码修改记录Controller
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
@RestController
@RequestMapping("/system/pwdModify")
public class SysUserPwdModifyController extends BaseController
{
    @Autowired
    private ISysUserPwdModifyService sysUserPwdModifyService;

    /**
     * 查询密码修改记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:pwdModify:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUserPwdModify sysUserPwdModify)
    {
        startPage();
        List<SysUserPwdModify> list = sysUserPwdModifyService.selectSysUserPwdModifyList(sysUserPwdModify);
        return getDataTable(list);
    }

    /**
     * 导出密码修改记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:pwdModify:export')")
    @Log(title = "密码修改记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysUserPwdModify sysUserPwdModify)
    {
        List<SysUserPwdModify> list = sysUserPwdModifyService.selectSysUserPwdModifyList(sysUserPwdModify);
        ExcelUtil<SysUserPwdModify> util = new ExcelUtil<SysUserPwdModify>(SysUserPwdModify.class);
        return util.exportExcel(list, "pwdModify");
    }

    /**
     * 获取密码修改记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:pwdModify:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysUserPwdModifyService.selectSysUserPwdModifyById(id));
    }

    /**
     * 新增密码修改记录
     */
    @PreAuthorize("@ss.hasPermi('system:pwdModify:add')")
    @Log(title = "密码修改记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUserPwdModify sysUserPwdModify)
    {
        return toAjax(sysUserPwdModifyService.insertSysUserPwdModify(sysUserPwdModify));
    }

    /**
     * 修改密码修改记录
     */
    @PreAuthorize("@ss.hasPermi('system:pwdModify:edit')")
    @Log(title = "密码修改记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUserPwdModify sysUserPwdModify)
    {
        return toAjax(sysUserPwdModifyService.updateSysUserPwdModify(sysUserPwdModify));
    }

    /**
     * 删除密码修改记录
     */
    @PreAuthorize("@ss.hasPermi('system:pwdModify:remove')")
    @Log(title = "密码修改记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysUserPwdModifyService.deleteSysUserPwdModifyByIds(ids));
    }
}
