package com.cb.assess.controller;

import com.cb.assess.domain.BizAssessPersonalSummary;
import com.cb.assess.domain.vo.BizAssessPersonalSummaryVo;
import com.cb.assess.service.IBizAssessPersonalSummaryService;
import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人总结Controller
 *
 * @author ruoyi
 * @date 2023-12-12
 */
@RestController
@RequestMapping("/assess/personalSummary")
public class BizAssessPersonalSummaryController extends BaseController {
    @Autowired
    private IBizAssessPersonalSummaryService bizAssessPersonalSummaryService;

    /**
     * 查询个人总结列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BizAssessPersonalSummaryVo bizAssessPersonalSummary) {
        startPage();
        if (StringUtils.isBlank(bizAssessPersonalSummary.getDataType())) {
            return getDataTable(new ArrayList<>());
        } else if ("1".equals(bizAssessPersonalSummary.getDataType())) {
            bizAssessPersonalSummary.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        } else if ("2".equals(bizAssessPersonalSummary.getDataType())) {
            //查已上报是1的
            bizAssessPersonalSummary.setIsReport("1");
        } else if ("3".equals(bizAssessPersonalSummary.getDataType())) {
            bizAssessPersonalSummary.setDeptId(SecurityUtils.getLoginUser().getUser().getDeptId());
        } else {
            return getDataTable(new ArrayList<>());
        }
        List<BizAssessPersonalSummaryVo> list = bizAssessPersonalSummaryService.selectBizAssessPersonalSummaryList(bizAssessPersonalSummary);
        return getDataTable(list);
    }

    @GetMapping("/listByNotice")
    public AjaxResult listByNotice(String year) {
        BizAssessPersonalSummaryVo bizAssessPersonalSummaryVo = new BizAssessPersonalSummaryVo();
        bizAssessPersonalSummaryVo.setAssessmentYear(year);
        bizAssessPersonalSummaryVo.setAuditFlag("2");//只取通过的
        List<BizAssessPersonalSummaryVo> list = bizAssessPersonalSummaryService.selectBizAssessPersonalSummaryList(bizAssessPersonalSummaryVo);
        return AjaxResult.success(list);
    }

    @GetMapping("/selectYears")
    public AjaxResult selectYears() {
        List<BizAssessPersonalSummaryVo> list = bizAssessPersonalSummaryService.selectYears();
        return AjaxResult.success(list);
    }

    /**
     * 导出个人总结列表
     */
//    @PreAuthorize("@ss.hasPermi('assess:personalSummary:export')")
    @Log(title = "个人总结", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizAssessPersonalSummaryVo bizAssessPersonalSummary) {
        List<BizAssessPersonalSummaryVo> list = bizAssessPersonalSummaryService.selectBizAssessPersonalSummaryList(bizAssessPersonalSummary);
        ExcelUtil<BizAssessPersonalSummaryVo> util = new ExcelUtil<BizAssessPersonalSummaryVo>(BizAssessPersonalSummaryVo.class);
        return util.exportExcel(list, "summary");
    }

    /**
     * 获取个人总结详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizAssessPersonalSummaryService.selectBizAssessPersonalSummaryById(id));
    }

    /**
     * 新增个人总结
     */
    @Log(title = "个人总结", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAssessPersonalSummary bizAssessPersonalSummary) {
        return toAjax(bizAssessPersonalSummaryService.insertBizAssessPersonalSummary(bizAssessPersonalSummary));
    }

    /**
     * 修改个人总结
     */
    @Log(title = "个人总结", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAssessPersonalSummary bizAssessPersonalSummary) {
        return toAjax(bizAssessPersonalSummaryService.updateBizAssessPersonalSummary(bizAssessPersonalSummary));
    }

    @PostMapping("/publishPersonalSummary2Notice")
    public AjaxResult publishPersonalSummary2Notice(@RequestBody BizAssessPersonalSummary bizAssessPersonalSummary) {
        bizAssessPersonalSummaryService.publishPersonalSummary2Notice(bizAssessPersonalSummary.getAssessmentYear());
        return success();
    }

    /**
     * 删除个人总结
     */
//    @PreAuthorize("@ss.hasPermi('assess:personalSummary:remove')")
    @Log(title = "个人总结", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizAssessPersonalSummaryService.deleteBizAssessPersonalSummaryByIds(ids));
    }
}
