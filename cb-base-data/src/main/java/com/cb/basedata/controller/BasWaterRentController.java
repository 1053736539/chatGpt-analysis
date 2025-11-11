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
import com.cb.basedata.domain.BasWaterRent;
import com.cb.basedata.service.IBasWaterRentService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 水费Controller
 *
 * @author ouyang
 * @date 2024-10-25
 */
@RestController
@RequestMapping("/basedata/waterRent")
public class BasWaterRentController extends BaseController {
    @Autowired
    private IBasWaterRentService basWaterRentService;

    /**
     * 查询水费列表
     */
    @PreAuthorize("@ss.hasPermi('basedata:waterRent:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasWaterRent basWaterRent) {
        startPage();
        List<BasWaterRent> list = basWaterRentService.selectBasWaterRentList(basWaterRent);
        return getDataTable(list);
    }

    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<BasWaterRent> util = new ExcelUtil<>(BasWaterRent.class);
        return util.importTemplateExcel("用水缴费数据");
    }

    @Log(title = "用水缴费数据导入", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('basedata:waterRent:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<BasWaterRent> util = new ExcelUtil<>(BasWaterRent.class);
        List<BasWaterRent> list = util.importExcel(file.getInputStream());
        String message = basWaterRentService.importBasWaterRent(list, updateSupport);
        return success(message);
    }

    /**
     * 导出水费列表
     */
    @PreAuthorize("@ss.hasPermi('basedata:waterRent:export')")
    @Log(title = "水费", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasWaterRent basWaterRent) {
        List<BasWaterRent> list = basWaterRentService.selectBasWaterRentList(basWaterRent);
        ExcelUtil<BasWaterRent> util = new ExcelUtil<BasWaterRent>(BasWaterRent.class);
        return util.exportExcel(list, "waterRent");
    }

    /**
     * 获取水费详细信息
     */
    @PreAuthorize("@ss.hasPermi('basedata:waterRent:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(basWaterRentService.selectBasWaterRentById(id));
    }

    /**
     * 新增水费
     */
    @PreAuthorize("@ss.hasPermi('basedata:waterRent:add')")
    @Log(title = "水费", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasWaterRent basWaterRent) {
        return toAjax(basWaterRentService.insertBasWaterRent(basWaterRent));
    }

    /**
     * 修改水费
     */
    @PreAuthorize("@ss.hasPermi('basedata:waterRent:edit')")
    @Log(title = "水费", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasWaterRent basWaterRent) {
        return toAjax(basWaterRentService.updateBasWaterRent(basWaterRent));
    }

    /**
     * 删除水费
     */
    @PreAuthorize("@ss.hasPermi('basedata:waterRent:remove')")
    @Log(title = "水费", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(basWaterRentService.deleteBasWaterRentByIds(ids));
    }
}
