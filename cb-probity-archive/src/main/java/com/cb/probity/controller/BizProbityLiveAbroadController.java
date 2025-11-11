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
import com.cb.probity.domain.BizProbityLiveAbroad;
import com.cb.probity.service.IBizProbityLiveAbroadService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况Controller
 *
 * @author ruoyi
 * @date 2025-03-18
 */
@RestController
@RequestMapping("/probity/probityLiveAbroad")
public class BizProbityLiveAbroadController extends BaseController {
    @Autowired
    private IBizProbityLiveAbroadService bizProbityLiveAbroadService;

    /**
     * 查询廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityLiveAbroad:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizProbityLiveAbroad bizProbityLiveAbroad) {
        startPage();
        List<BizProbityLiveAbroad> list = bizProbityLiveAbroadService.selectBizProbityLiveAbroadList(bizProbityLiveAbroad);
        return getDataTable(list);
    }

    /**
     * 导出廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityLiveAbroad:export')")
    @Log(title = "廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizProbityLiveAbroad bizProbityLiveAbroad) {
        List<BizProbityLiveAbroad> list = bizProbityLiveAbroadService.selectBizProbityLiveAbroadList(bizProbityLiveAbroad);
        ExcelUtil<BizProbityLiveAbroad> util = new ExcelUtil<BizProbityLiveAbroad>(BizProbityLiveAbroad.class);
        return util.exportExcel(list, "probityLiveAbroad");
    }

    /**
     * 获取廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('probity:probityLiveAbroad:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizProbityLiveAbroadService.selectBizProbityLiveAbroadById(id));
    }

    /**
     * 新增廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityLiveAbroad:add')")
    @Log(title = "廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizProbityLiveAbroad bizProbityLiveAbroad) {
        return toAjax(bizProbityLiveAbroadService.insertBizProbityLiveAbroad(bizProbityLiveAbroad));
    }

    /**
     * 修改廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityLiveAbroad:edit')")
    @Log(title = "廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizProbityLiveAbroad bizProbityLiveAbroad) {
        return toAjax(bizProbityLiveAbroadService.updateBizProbityLiveAbroad(bizProbityLiveAbroad));
    }

    /**
     * 删除廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     */
    @PreAuthorize("@ss.hasPermi('probity:probityLiveAbroad:remove')")
    @Log(title = "廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizProbityLiveAbroadService.deleteBizProbityLiveAbroadByIds(ids));
    }
}
