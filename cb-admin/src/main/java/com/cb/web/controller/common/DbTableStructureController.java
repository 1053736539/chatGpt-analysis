package com.cb.web.controller.common;

import java.util.List;

import com.cb.table.domain.DbTableStructure;
import com.cb.table.service.IDbTableStructureService;
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

/**
 * online表单Controller
 *
 * @author ouyang
 * @date 2024-11-06
 */
@RestController
@RequestMapping("/system/online")
public class DbTableStructureController extends BaseController {
    @Autowired
    private IDbTableStructureService dbTableStructureService;

    /**
     * 查询online表单列表
     */
    @GetMapping("/list")
    public TableDataInfo list(DbTableStructure dbTableStructure) {
        startPage();
        List<DbTableStructure> list = dbTableStructureService.selectDbTableStructureList(dbTableStructure);
        return getDataTable(list);
    }

    /**
     * 导出online表单列表
     */
    @Log(title = "online表单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DbTableStructure dbTableStructure) {
        List<DbTableStructure> list = dbTableStructureService.selectDbTableStructureList(dbTableStructure);
        ExcelUtil<DbTableStructure> util = new ExcelUtil<DbTableStructure>(DbTableStructure.class);
        return util.exportExcel(list, "online");
    }

    /**
     * 获取online表单详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(dbTableStructureService.selectDbTableStructureById(id));
    }

    /**
     * 新增online表单
     */
    @Log(title = "online表单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DbTableStructure dbTableStructure) {
        return toAjax(dbTableStructureService.insertDbTableStructure(dbTableStructure));
    }

    /**
     * 修改online表单
     */
    @Log(title = "online表单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DbTableStructure dbTableStructure) {
        return toAjax(dbTableStructureService.updateDbTableStructure(dbTableStructure));
    }

    /**
     * 删除online表单
     */
    @Log(title = "online表单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(dbTableStructureService.deleteDbTableStructureByIds(ids));
    }

    /**
     * 同步至数据库
     * @param id
     * @return
     */
    @GetMapping(value = "/asyncStructure/{id}")
    public AjaxResult asyncStructure2Db(@PathVariable("id") String id) {
        dbTableStructureService.asyncStructure2Db(id);
        return AjaxResult.success();
    }
}
