package com.cb.system.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.SecurityUtils;
import com.cb.leave.service.ILeaveBalancesService;
import com.cb.system.service.ISysUserService;
import com.cb.system.vo.SysUserInfoChangeApplyVo;
import lombok.extern.slf4j.Slf4j;
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
import com.cb.system.domain.SysUserInfoChangeApply;
import com.cb.system.service.ISysUserInfoChangeApplyService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

import javax.validation.Valid;

/**
 * 用户信息修改申请Controller
 *
 * @author ruoyi
 * @date 2025-05-15
 */
@Slf4j
@RestController
@RequestMapping("/system/userInfoChangeApply")
public class SysUserInfoChangeApplyController extends BaseController
{
    @Autowired
    private ISysUserInfoChangeApplyService sysUserInfoChangeApplyService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ILeaveBalancesService leaveBalancesService;

    /**
     * 查询用户信息修改申请列表
     */
//    @PreAuthorize("@ss.hasPermi('system:userInfoChangeApply:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUserInfoChangeApply sysUserInfoChangeApply)
    {
        startPage();
        List<SysUserInfoChangeApply> list = sysUserInfoChangeApplyService.selectSysUserInfoChangeApplyList(sysUserInfoChangeApply);
        return getDataTable(list);
    }

    /**
     * 查询用户自己的列表
     * @param sysUserInfoChangeApply
     * @return
     */
    @GetMapping("/listBySelf")
    public TableDataInfo listBySelf(SysUserInfoChangeApply sysUserInfoChangeApply)
    {
        String username = SecurityUtils.getUsername();
        sysUserInfoChangeApply.setCreateBy(username);
        startPage();
        List<SysUserInfoChangeApply> list = sysUserInfoChangeApplyService.selectSysUserInfoChangeApplyList(sysUserInfoChangeApply);
        return getDataTable(list);
    }

    /**
     * 导出用户信息修改申请列表
     */
//    @PreAuthorize("@ss.hasPermi('system:userInfoChangeApply:export')")
    @Log(title = "用户信息修改申请", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysUserInfoChangeApply sysUserInfoChangeApply)
    {
        List<SysUserInfoChangeApply> list = sysUserInfoChangeApplyService.selectSysUserInfoChangeApplyList(sysUserInfoChangeApply);
        ExcelUtil<SysUserInfoChangeApply> util = new ExcelUtil<SysUserInfoChangeApply>(SysUserInfoChangeApply.class);
        return util.exportExcel(list, "userInfoChangeApply");
    }

    /**
     * 获取用户信息修改申请详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:userInfoChangeApply:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysUserInfoChangeApplyService.selectSysUserInfoChangeApplyById(id));
    }

    /**
     * 新增用户信息修改申请
     */
//    @PreAuthorize("@ss.hasPermi('system:userInfoChangeApply:add')")
    @Log(title = "用户信息修改申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUserInfoChangeApply sysUserInfoChangeApply)
    {
        return toAjax(sysUserInfoChangeApplyService.insertSysUserInfoChangeApply(sysUserInfoChangeApply));
    }

    /**
     * 修改用户信息修改申请
     */
//    @PreAuthorize("@ss.hasPermi('system:userInfoChangeApply:edit')")
    @Log(title = "用户信息修改申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUserInfoChangeApply sysUserInfoChangeApply)
    {
        return toAjax(sysUserInfoChangeApplyService.updateSysUserInfoChangeApply(sysUserInfoChangeApply));
    }

    /**
     * 删除用户信息修改申请
     */
//    @PreAuthorize("@ss.hasPermi('system:userInfoChangeApply:remove')")
    @Log(title = "用户信息修改申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids, Boolean mgrFlag)
    {
        if(null != mgrFlag && mgrFlag.booleanValue()){
            sysUserInfoChangeApplyService.deleteSysUserInfoChangeApplyByIds(ids);
        } else {
            sysUserInfoChangeApplyService.deleteSysUserInfoChangeApplyByIdsExcludePass(ids);
        }
        return AjaxResult.success();
    }

    /**
     * 审批操作
     * @param req
     * @return
     */
    @PostMapping("/approval")
    public AjaxResult approval(@RequestBody @Valid SysUserInfoChangeApplyVo.ApprovalReq req){
        //先修改申请记录的状态
        sysUserInfoChangeApplyService.approval(req);
        //审批通过要将数据更新到用户表
        if("2".equals(req.getStatus())){
            List<SysUserInfoChangeApply> list = sysUserInfoChangeApplyService.selectByIds(req.getIds());
            list.forEach(item -> {
                String userName = item.getUserName();
                try {
                    String afterData = item.getAfterData();
                    SysUser sysUser = JSONObject.parseObject(afterData, SysUser.class);
                    //更新用户信息
                    sysUserService.updateByUserInfoApply(sysUser);
                    //更新年休假
                    leaveBalancesService.addAnnualLeaveForUser(sysUser);
                }catch (Exception e){
                    log.error("用户信息修改审批通过时更新用户表失败!用户名：{} 原因：{}", userName, e.getMessage());
                }
            });
        }
        return AjaxResult.success();
    }

    /**
     * 重新发起申请
     * @param req
     * @return
     */
    @PostMapping("/reApply")
    public AjaxResult reApply(@RequestBody @Valid SysUserInfoChangeApplyVo.ReApplyReq req){
        sysUserInfoChangeApplyService.reApply(req);
        return AjaxResult.success();
    }

    /**
     * 获取待审批数量
     * @return
     */
    @GetMapping("/countPending")
    public AjaxResult countPending(){
        long count = sysUserInfoChangeApplyService.countPending();
        return AjaxResult.success(count);
    }

}
