package com.cb.web.controller.message;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.message.domain.SysAnnouncement;
import com.cb.message.service.ISysAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统消息Controller
 * 
 * @author ouyang
 * @date 2023-07-12
 */
@RestController
@RequestMapping("/system/message")
public class SysAnnouncementController extends BaseController
{
    @Autowired
    private ISysAnnouncementService sysAnnouncementService;

    /**
     * 查询系统消息列表
     */
    @PreAuthorize("@ss.hasPermi('system:message:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysAnnouncement sysAnnouncement)
    {
        sysAnnouncement.setCreateBy(SecurityUtils.getUsername());
        startPage();
        List<SysAnnouncement> list = sysAnnouncementService.selectSysAnnouncementList(sysAnnouncement);
        return getDataTable(list);
    }

    /**
     * 导出系统消息列表
     */
    @PreAuthorize("@ss.hasPermi('system:message:export')")
    @Log(title = "系统消息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysAnnouncement sysAnnouncement)
    {
        List<SysAnnouncement> list = sysAnnouncementService.selectSysAnnouncementList(sysAnnouncement);
        ExcelUtil<SysAnnouncement> util = new ExcelUtil<SysAnnouncement>(SysAnnouncement.class);
        return util.exportExcel(list, "message");
    }

    /**
     * 获取系统消息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:message:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysAnnouncementService.selectSysAnnouncementById(id));
    }

    /**
     * 新增系统消息
     */
    @PreAuthorize("@ss.hasPermi('system:message:add')")
    @Log(title = "系统消息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysAnnouncement sysAnnouncement)
    {
        return toAjax(sysAnnouncementService.insertSysAnnouncement(sysAnnouncement));
    }

    /**
     * 修改系统消息
     */
    @PreAuthorize("@ss.hasPermi('system:message:edit')")
    @Log(title = "系统消息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysAnnouncement sysAnnouncement)
    {
        return toAjax(sysAnnouncementService.updateSysAnnouncement(sysAnnouncement));
    }

    /**
     * 删除系统消息
     */
    @PreAuthorize("@ss.hasPermi('system:message:remove')")
    @Log(title = "系统消息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(sysAnnouncementService.deleteSysAnnouncementByIds(ids));
    }

    /**
     * 消息发布
     */
    @PreAuthorize("@ss.hasPermi('system:message:release')")
    @Log(title = "系统消息发布", businessType = BusinessType.UPDATE)
    @PutMapping("/release/{id}")
    public AjaxResult release(@PathVariable("id")String id)
    {
        return toAjax(sysAnnouncementService.releaseAnnouncement(id));
    }

    /**
     * 消息撤销
     * @param id
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:message:cancellation')")
    @Log(title = "系统消息撤销", businessType = BusinessType.UPDATE)
    @PutMapping("/cancellation/{id}")
    public AjaxResult cancellation(@PathVariable("id")String id)
    {
        return toAjax(sysAnnouncementService.cancellationAnnouncement(id));
    }


}
