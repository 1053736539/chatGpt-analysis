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
import com.cb.basedata.domain.BasElectricityFees;
import com.cb.basedata.service.IBasElectricityFeesService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 电费Controller
 *
 * @author ouyang
 * @date 2024-10-25
 */
@RestController
@RequestMapping("/basedata/electricityFees")
public class BasElectricityFeesController extends BaseController {
    @Autowired
    private IBasElectricityFeesService basElectricityFeesService;

    /**
     * 查询电费列表
     */
    @PreAuthorize("@ss.hasPermi('basedata:electricityFees:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasElectricityFees basElectricityFees) {
        startPage();
        List<BasElectricityFees> list = basElectricityFeesService.selectBasElectricityFeesList(basElectricityFees);
        return getDataTable(list);
    }

    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<BasElectricityFees> util = new ExcelUtil<>(BasElectricityFees.class);
        return util.importTemplateExcel("用电缴费数据");
    }

    @Log(title = "用电缴费数据导入", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('basedata:electricityFees:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<BasElectricityFees> util = new ExcelUtil<>(BasElectricityFees.class);
        List<BasElectricityFees> list = util.importExcel(file.getInputStream());
        String message = basElectricityFeesService.importBasElectricityFees(list, updateSupport);
        return success(message);
    }

    /**
     * 导出电费列表
     */
    @PreAuthorize("@ss.hasPermi('basedata:electricityFees:export')")
    @Log(title = "电费", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasElectricityFees basElectricityFees) {
        List<BasElectricityFees> list = basElectricityFeesService.selectBasElectricityFeesList(basElectricityFees);
        ExcelUtil<BasElectricityFees> util = new ExcelUtil<BasElectricityFees>(BasElectricityFees.class);
        return util.exportExcel(list, "electricityFees");
    }

    /**
     * 获取电费详细信息
     */
    @PreAuthorize("@ss.hasPermi('basedata:electricityFees:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(basElectricityFeesService.selectBasElectricityFeesById(id));
    }

    /**
     * 新增电费
     */
    @PreAuthorize("@ss.hasPermi('basedata:electricityFees:add')")
    @Log(title = "电费", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasElectricityFees basElectricityFees) {
        return toAjax(basElectricityFeesService.insertBasElectricityFees(basElectricityFees));
    }

    /**
     * 修改电费
     */
    @PreAuthorize("@ss.hasPermi('basedata:electricityFees:edit')")
    @Log(title = "电费", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasElectricityFees basElectricityFees) {
        return toAjax(basElectricityFeesService.updateBasElectricityFees(basElectricityFees));
    }

    /**
     * 删除电费
     */
    @PreAuthorize("@ss.hasPermi('basedata:electricityFees:remove')")
    @Log(title = "电费", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(basElectricityFeesService.deleteBasElectricityFeesByIds(ids));
    }

}
