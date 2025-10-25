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
import com.cb.common.core.domain.entity.RsDescript;
import com.cb.system.service.IRsDescriptService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 干部档案附件信息Controller
 * 
 * @author ruoyi
 * @date 2025-02-25
 */
@RestController
@RequestMapping("/system/descript")
public class RsDescriptController extends BaseController
{
    @Autowired
    private IRsDescriptService rsDescriptService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询干部档案附件信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:descript:list')")
    @GetMapping("/list")
    public TableDataInfo list(RsDescript rsDescript)
    {
        startPage();
        List<RsDescript> list = rsDescriptService.selectRsDescriptList(rsDescript);
        return getDataTable(list);
    }

    /**
     * 导出干部档案附件信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:descript:export')")
    @Log(title = "干部档案附件信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(RsDescript rsDescript)
    {
        List<RsDescript> list = rsDescriptService.selectRsDescriptList(rsDescript);
        ExcelUtil<RsDescript> util = new ExcelUtil<RsDescript>(RsDescript.class);
        return util.exportExcel(list, "descript");
    }

    /**
     * 获取干部档案附件信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:descript:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(rsDescriptService.selectRsDescriptById(id));
    }

    /**
     * 新增干部档案附件信息
     */
    @PreAuthorize("@ss.hasPermi('system:descript:add')")
    @Log(title = "干部档案附件信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RsDescript rsDescript)
    {
        return toAjax(rsDescriptService.insertRsDescript(rsDescript));
    }

    /**
     * 修改干部档案附件信息
     */
    @PreAuthorize("@ss.hasPermi('system:descript:edit')")
    @Log(title = "干部档案附件信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RsDescript rsDescript)
    {
        return toAjax(rsDescriptService.updateRsDescript(rsDescript));
    }

    /**
     * 删除干部档案附件信息
     */
    @PreAuthorize("@ss.hasPermi('system:descript:remove')")
    @Log(title = "干部档案附件信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(rsDescriptService.deleteRsDescriptByIds(ids));
    }
    /**
     * 干部档案附件信息导入
     * @param file
     * @return
     * @throws Exception
     */
    @Log(title = "干部档案附件", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:descript:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<RsDescript> util = new ExcelUtil<RsDescript>(RsDescript.class);
        List<RsDescript> rsDescriptList = util.importExcel(file.getInputStream());
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String operName = loginUser.getUsername();
        String message = rsDescriptService.importRsDescript(rsDescriptList, operName);
        return AjaxResult.success(message);
    }
    /**
     * 干部档案附件导入模板下载
     */
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<RsDescript> util = new ExcelUtil<RsDescript>(RsDescript.class);
        return util.importTemplateExcel("干部档案附件数据");
    }
}
