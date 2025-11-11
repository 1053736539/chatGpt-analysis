package com.cb.basedata.controller;

import java.util.List;

import com.cb.basedata.domain.vo.LivingExpensesSourceVo;
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
import com.cb.basedata.domain.BasGasCost;
import com.cb.basedata.service.IBasGasCostService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 燃气费Controller
 * 
 * @author ouyang
 * @date 2024-10-25
 */
@RestController
@RequestMapping("/basedata/gasCost")
public class BasGasCostController extends BaseController
{
    @Autowired
    private IBasGasCostService basGasCostService;

    /**
     * 查询燃气费列表
     */
    @PreAuthorize("@ss.hasPermi('basedata:gasCost:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasGasCost basGasCost)
    {
        startPage();
        List<BasGasCost> list = basGasCostService.selectBasGasCostList(basGasCost);
        return getDataTable(list);
    }

    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<BasGasCost> util = new ExcelUtil<>(BasGasCost.class);
        return util.importTemplateExcel("燃气缴费数据");
    }

    @Log(title = "燃气缴费数据导入", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('basedata:gasCost:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<BasGasCost> util = new ExcelUtil<>(BasGasCost.class);
        List<BasGasCost> list = util.importExcel(file.getInputStream());
        String message = basGasCostService.importBasGasCost(list, updateSupport);
        return success(message);
    }
    /**
     * 导出燃气费列表
     */
    @PreAuthorize("@ss.hasPermi('basedata:gasCost:export')")
    @Log(title = "燃气费", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasGasCost basGasCost)
    {
        List<BasGasCost> list = basGasCostService.selectBasGasCostList(basGasCost);
        ExcelUtil<BasGasCost> util = new ExcelUtil<BasGasCost>(BasGasCost.class);
        return util.exportExcel(list, "gasCost");
    }

    /**
     * 获取燃气费详细信息
     */
    @PreAuthorize("@ss.hasPermi('basedata:gasCost:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(basGasCostService.selectBasGasCostById(id));
    }

    /**
     * 新增燃气费
     */
    @PreAuthorize("@ss.hasPermi('basedata:gasCost:add')")
    @Log(title = "燃气费", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasGasCost basGasCost)
    {
        return toAjax(basGasCostService.insertBasGasCost(basGasCost));
    }

    /**
     * 修改燃气费
     */
    @PreAuthorize("@ss.hasPermi('basedata:gasCost:edit')")
    @Log(title = "燃气费", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasGasCost basGasCost)
    {
        return toAjax(basGasCostService.updateBasGasCost(basGasCost));
    }

    /**
     * 删除燃气费
     */
    @PreAuthorize("@ss.hasPermi('basedata:gasCost:remove')")
    @Log(title = "燃气费", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(basGasCostService.deleteBasGasCostByIds(ids));
    }
}
