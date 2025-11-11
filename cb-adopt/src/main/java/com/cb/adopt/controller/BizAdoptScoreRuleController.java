package com.cb.adopt.controller;

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
import com.cb.adopt.domain.BizAdoptScoreRule;
import com.cb.adopt.service.IBizAdoptScoreRuleService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 得分规则Controller
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
@RestController
@RequestMapping("/adopt/adoptScoreRule")
public class BizAdoptScoreRuleController extends BaseController
{
    @Autowired
    private IBizAdoptScoreRuleService bizAdoptScoreRuleService;

    /**
     * 查询得分规则列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:adoptScoreRule:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizAdoptScoreRule bizAdoptScoreRule)
    {
        startPage();
        List<BizAdoptScoreRule> list = bizAdoptScoreRuleService.selectBizAdoptScoreRuleList(bizAdoptScoreRule);
        return getDataTable(list);
    }

    /**
     * 导出得分规则列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:adoptScoreRule:export')")
    @Log(title = "得分规则", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizAdoptScoreRule bizAdoptScoreRule)
    {
        List<BizAdoptScoreRule> list = bizAdoptScoreRuleService.selectBizAdoptScoreRuleList(bizAdoptScoreRule);
        ExcelUtil<BizAdoptScoreRule> util = new ExcelUtil<BizAdoptScoreRule>(BizAdoptScoreRule.class);
        return util.exportExcel(list, "adoptScoreRule");
    }

    /**
     * 获取得分规则详细信息
     */
    @PreAuthorize("@ss.hasPermi('adopt:adoptScoreRule:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(bizAdoptScoreRuleService.selectBizAdoptScoreRuleById(id));
    }

    /**
     * 新增得分规则
     */
    @PreAuthorize("@ss.hasPermi('adopt:adoptScoreRule:add')")
    @Log(title = "得分规则", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAdoptScoreRule bizAdoptScoreRule)
    {
        return toAjax(bizAdoptScoreRuleService.insertBizAdoptScoreRule(bizAdoptScoreRule));
    }

    /**
     * 修改得分规则
     */
    @PreAuthorize("@ss.hasPermi('adopt:adoptScoreRule:edit')")
    @Log(title = "得分规则", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAdoptScoreRule bizAdoptScoreRule)
    {
        return toAjax(bizAdoptScoreRuleService.updateBizAdoptScoreRule(bizAdoptScoreRule));
    }

    /**
     * 删除得分规则
     */
    @PreAuthorize("@ss.hasPermi('adopt:adoptScoreRule:remove')")
    @Log(title = "得分规则", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(bizAdoptScoreRuleService.deleteBizAdoptScoreRuleByIds(ids));
    }
}
