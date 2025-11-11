package com.cb.oa.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.StringUtils;
import com.cb.oa.domain.SysUserOut;
import com.cb.oa.domain.vo.SysUserOutVo;
import com.cb.oa.service.ISysUserOutService;
import com.cb.system.service.ISysUserService;
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

import javax.annotation.Resource;

/**
 * oa用户Controller
 * 
 * @author ruoyi
 * @date 2023-12-01
 */
@RestController
@RequestMapping("/system/userOut")
public class SysUserOutController extends BaseController
{
    @Autowired
    private ISysUserOutService sysUserOutService;
    @Resource
    private ISysUserService sysUserService;


    /**
     * 查询oa用户列表
     */
    //@PreAuthorize("@ss.hasPermi('system:userOut:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUserOutVo sysUserOut)
    {
        startPage();
        List<SysUserOutVo> list = sysUserOutService.selectSysUserOutVoList(sysUserOut);
        return getDataTable(list);
    }
    @GetMapping("/syncSysUserOutList")
    public AjaxResult syncSysUserOutList()
    {
        SysUserOut sysUserOut = new SysUserOut();
        List<SysUserOut> sysUserOuts = sysUserOutService.selectSysUserOutList(sysUserOut);
        Map<String, SysUserOut> sysUserOutMap = sysUserOuts.stream().filter(e -> StringUtils.isNoneBlank(e.getId())).collect(Collectors.toMap(SysUserOut::getId, o -> o));
        SysUser sysUser = new SysUser();
        List<SysUser> sysUsers = sysUserService.selectUserList(sysUser);
        //过滤掉系统内重名和电话重复的人
        Map<String, Long> sysUsersPhoneCount = sysUsers.stream().filter(e -> StringUtils.isNoneBlank(e.getPhonenumber())).collect(Collectors.groupingBy(SysUser::getPhonenumber, Collectors.counting()));
        Map<String, SysUser> phoneMap = sysUsers.stream().filter(e -> StringUtils.isNoneBlank(e.getPhonenumber())).filter(e->sysUsersPhoneCount.get(e.getPhonenumber())<2).collect(Collectors.toMap(SysUser::getPhonenumber, o -> o));
        Map<String, Long> nameCountMap = sysUsers.stream().filter(e -> StringUtils.isNoneBlank(e.getName())).collect(Collectors.groupingBy(SysUser::getName, Collectors.counting()));
        Map<String, SysUser> nameMap = sysUsers.stream().filter(e -> StringUtils.isNoneBlank(e.getName())).filter(e->nameCountMap.get(e.getName())<2).collect(Collectors.toMap(SysUser::getName, o -> o));
        Integer count=0;
        Integer page=1;
        Integer pageSize=50;
        Integer integer = sysUserOutService.syncSysUserOutList(count, page, pageSize, phoneMap, nameMap, sysUserOutMap);
        return success(integer.toString());
    }

    /**
     * 导出oa用户列表
     */
    //@PreAuthorize("@ss.hasPermi('system:userOut:export')")
    @Log(title = "oa用户", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysUserOut sysUserOut)
    {
        List<SysUserOut> list = sysUserOutService.selectSysUserOutList(sysUserOut);
        ExcelUtil<SysUserOut> util = new ExcelUtil<SysUserOut>(SysUserOut.class);
        return util.exportExcel(list, "out");
    }

    /**
     * 获取oa用户详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:userOut:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysUserOutService.selectSysUserOutById(id));
    }

    /**
     * 新增oa用户
     */
    //@PreAuthorize("@ss.hasPermi('system:userOut:add')")
    @Log(title = "oa用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUserOut sysUserOut)
    {
        return toAjax(sysUserOutService.insertSysUserOut(sysUserOut));
    }

    /**
     * 修改oa用户
     */
    //@PreAuthorize("@ss.hasPermi('system:userOut:edit')")
    @Log(title = "oa用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUserOut sysUserOut)
    {
        return toAjax(sysUserOutService.updateSysUserOut(sysUserOut));
    }

    /**
     * 删除oa用户
     */
   // @PreAuthorize("@ss.hasPermi('system:userOut:remove')")
    @Log(title = "oa用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(sysUserOutService.deleteSysUserOutByIds(ids));
    }
}
