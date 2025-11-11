package com.cb.probity.controller;

import java.util.List;

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
import com.cb.probity.domain.BizProbityCertificate;
import com.cb.probity.service.IBizProbityCertificateService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 廉政档案-6.1本人持有护照、往来港澳台证件情况Controller
 *
 * @author ruoyi
 * @date 2025-03-18
 */
@RestController
@RequestMapping("/probity/probityCertificate")
public class BizProbityCertificateController extends BaseController {
    @Autowired
    private IBizProbityCertificateService bizProbityCertificateService;

    /**
     * 查询廉政档案-6.1本人持有护照、往来港澳台证件情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityCertificate:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizProbityCertificate bizProbityCertificate) {
        startPage();
        List<BizProbityCertificate> list = bizProbityCertificateService.selectBizProbityCertificateList(bizProbityCertificate);
        return getDataTable(list);
    }

    /**
     * 导出廉政档案-6.1本人持有护照、往来港澳台证件情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityCertificate:export')")
    @Log(title = "廉政档案-6.1本人持有护照、往来港澳台证件情况", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizProbityCertificate bizProbityCertificate) {
        List<BizProbityCertificate> list = bizProbityCertificateService.selectBizProbityCertificateList(bizProbityCertificate);
        ExcelUtil<BizProbityCertificate> util = new ExcelUtil<BizProbityCertificate>(BizProbityCertificate.class);
        return util.exportExcel(list, "probityCertificate");
    }

    /**
     * 获取廉政档案-6.1本人持有护照、往来港澳台证件情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('probity:probityCertificate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizProbityCertificateService.selectBizProbityCertificateById(id));
    }

    /**
     * 新增廉政档案-6.1本人持有护照、往来港澳台证件情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityCertificate:add')")
    @Log(title = "廉政档案-6.1本人持有护照、往来港澳台证件情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizProbityCertificate bizProbityCertificate) {
        return toAjax(bizProbityCertificateService.insertBizProbityCertificate(bizProbityCertificate));
    }

    /**
     * 修改廉政档案-6.1本人持有护照、往来港澳台证件情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityCertificate:edit')")
    @Log(title = "廉政档案-6.1本人持有护照、往来港澳台证件情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizProbityCertificate bizProbityCertificate) {
        return toAjax(bizProbityCertificateService.updateBizProbityCertificate(bizProbityCertificate));
    }

    /**
     * 删除廉政档案-6.1本人持有护照、往来港澳台证件情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityCertificate:remove')")
    @Log(title = "廉政档案-6.1本人持有护照、往来港澳台证件情况", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizProbityCertificateService.deleteBizProbityCertificateByIds(ids));
    }
}
