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
import com.cb.common.core.domain.entity.RsArchInfo;
import com.cb.system.service.IRsArchInfoService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 干部档案目录Controller
 * 
 * @author ruoyi
 * @date 2025-02-26
 */
@RestController
@RequestMapping("/system/rsArchInfo")
public class RsArchInfoController extends BaseController
{
    @Autowired
    private IRsArchInfoService rsArchInfoService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询干部档案目录列表
     */
    @PreAuthorize("@ss.hasPermi('system:rsArchInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(RsArchInfo rsArchInfo)
    {
        startPage();
        List<RsArchInfo> list = rsArchInfoService.selectRsArchInfoList(rsArchInfo);
        return getDataTable(list);
    }

    /**
     * 导出干部档案目录列表
     */
    @PreAuthorize("@ss.hasPermi('system:rsArchInfo:export')")
    @Log(title = "干部档案目录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(RsArchInfo rsArchInfo)
    {
        List<RsArchInfo> list = rsArchInfoService.selectRsArchInfoList(rsArchInfo);
        ExcelUtil<RsArchInfo> util = new ExcelUtil<RsArchInfo>(RsArchInfo.class);
        return util.exportExcel(list, "rsArchInfo");
    }

    /**
     * 获取干部档案目录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:rsArchInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(rsArchInfoService.selectRsArchInfoById(id));
    }

    /**
     * 新增干部档案目录
     */
    @PreAuthorize("@ss.hasPermi('system:rsArchInfo:add')")
    @Log(title = "干部档案目录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RsArchInfo rsArchInfo)
    {
        return toAjax(rsArchInfoService.insertRsArchInfo(rsArchInfo));
    }

    /**
     * 修改干部档案目录
     */
    @PreAuthorize("@ss.hasPermi('system:rsArchInfo:edit')")
    @Log(title = "干部档案目录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RsArchInfo rsArchInfo)
    {
        return toAjax(rsArchInfoService.updateRsArchInfo(rsArchInfo));
    }

    /**
     * 删除干部档案目录
     */
    @PreAuthorize("@ss.hasPermi('system:rsArchInfo:remove')")
    @Log(title = "干部档案目录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(rsArchInfoService.deleteRsArchInfoByIds(ids));
    }

    /**
     * 干部档案目录导入
     * @param file
     * @return
     * @throws Exception
     */
    @Log(title = "干部档案目录", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:rsArchInfo:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<RsArchInfo> util = new ExcelUtil<RsArchInfo>(RsArchInfo.class);
        List<RsArchInfo> rsArchInfoList = util.importExcel(file.getInputStream());
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String operName = loginUser.getUsername();
        String message = rsArchInfoService.importRsArchInfo(rsArchInfoList, operName);
        return AjaxResult.success(message);
    }
    //干部档案目录导入模板下载
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<RsArchInfo> util = new ExcelUtil<RsArchInfo>(RsArchInfo.class);
        return util.importTemplateExcel("干部档案目录数据");
    }
    /**
     * 查询干部档案目录信息
     */
    @PreAuthorize("@ss.hasPermi('system:rsArchInfo:catalogList')")
    @GetMapping("/catalogList")
    public AjaxResult archInfolist(RsArchInfo rsArchInfo)
    {
        List<RsArchInfo> list = rsArchInfoService.selectArchInfoCatalogList(rsArchInfo);
        return AjaxResult.success(list);
    }
}
