package com.cb.web.controller.system;

import java.util.List;

import com.cb.common.core.domain.model.LoginUser;
import com.cb.common.utils.ServletUtils;
import com.cb.framework.web.service.TokenService;
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
import com.cb.common.core.domain.entity.RsInfo;
import com.cb.system.service.IRsInfoService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 档案系统用户信息Controller
 * 
 * @author ruoyi
 * @date 2025-02-26
 */
@RestController
@RequestMapping("/system/rsInfo")
public class RsInfoController extends BaseController
{
    @Autowired
    private IRsInfoService rsInfoService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询档案系统用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:rsInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(RsInfo rsInfo)
    {
        startPage();
        List<RsInfo> list = rsInfoService.selectRsInfoList(rsInfo);
        return getDataTable(list);
    }

    /**
     * 导出档案系统用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:rsInfo:export')")
    @Log(title = "档案系统用户信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(RsInfo rsInfo)
    {
        List<RsInfo> list = rsInfoService.selectRsInfoList(rsInfo);
        ExcelUtil<RsInfo> util = new ExcelUtil<RsInfo>(RsInfo.class);
        return util.exportExcel(list, "rsInfo");
    }

    /**
     * 获取档案系统用户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:rsInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(rsInfoService.selectRsInfoById(id));
    }

    /**
     * 新增档案系统用户信息
     */
    @PreAuthorize("@ss.hasPermi('system:rsInfo:add')")
    @Log(title = "档案系统用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RsInfo rsInfo)
    {
        return toAjax(rsInfoService.insertRsInfo(rsInfo));
    }

    /**
     * 修改档案系统用户信息
     */
    @PreAuthorize("@ss.hasPermi('system:rsInfo:edit')")
    @Log(title = "档案系统用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RsInfo rsInfo)
    {
        return toAjax(rsInfoService.updateRsInfo(rsInfo));
    }

    /**
     * 删除档案系统用户信息
     */
    @PreAuthorize("@ss.hasPermi('system:rsInfo:remove')")
    @Log(title = "档案系统用户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(rsInfoService.deleteRsInfoByIds(ids));
    }

    /**
     * 档案系统用户信息导入
     * @param file
     * @return
     * @throws Exception
     */
    @Log(title = "干部档案系统用户信息导入", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:rsInfo:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<RsInfo> util = new ExcelUtil<RsInfo>(RsInfo.class);
        List<RsInfo> rsInfoList = util.importExcel(file.getInputStream());
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String operName = loginUser.getUsername();
        String message = rsInfoService.importRsInfo(rsInfoList, operName);
        return AjaxResult.success(message);
    }
    //干部档案用户信息导入模板下载
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<RsInfo> util = new ExcelUtil<RsInfo>(RsInfo.class);
        return util.importTemplateExcel("干部档案用户数据");
    }
}
