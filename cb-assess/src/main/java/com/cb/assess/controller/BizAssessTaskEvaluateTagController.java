package com.cb.assess.controller;

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
import com.cb.assess.domain.BizAssessTaskEvaluateTag;
import com.cb.assess.service.IBizAssessTaskEvaluateTagService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 任务用户互评标记Controller
 *
 * @author ouyang
 * @date 2023-11-17
 */
@RestController
@RequestMapping("/assess/evaluateTag")
public class BizAssessTaskEvaluateTagController extends BaseController {
    @Autowired
    private IBizAssessTaskEvaluateTagService bizAssessTaskEvaluateTagService;

    /**
     * 查询任务用户互评标记列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BizAssessTaskEvaluateTag bizAssessTaskEvaluateTag) {
        startPage();
        List<BizAssessTaskEvaluateTag> list = bizAssessTaskEvaluateTagService.selectBizAssessTaskEvaluateTagList(bizAssessTaskEvaluateTag);
        return getDataTable(list);
    }

    /**
     * 导出任务用户互评标记列表
     */
    @Log(title = "任务用户互评标记", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizAssessTaskEvaluateTag bizAssessTaskEvaluateTag) {
        List<BizAssessTaskEvaluateTag> list = bizAssessTaskEvaluateTagService.selectBizAssessTaskEvaluateTagList(bizAssessTaskEvaluateTag);
        ExcelUtil<BizAssessTaskEvaluateTag> util = new ExcelUtil<BizAssessTaskEvaluateTag>(BizAssessTaskEvaluateTag.class);
        return util.exportExcel(list, "evaluateTag");
    }

    /**
     * 获取任务用户互评标记详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizAssessTaskEvaluateTagService.selectBizAssessTaskEvaluateTagById(id));
    }

    /**
     * 新增任务用户互评标记
     */
    @Log(title = "任务用户互评标记", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAssessTaskEvaluateTag bizAssessTaskEvaluateTag) {
        return toAjax(bizAssessTaskEvaluateTagService.insertBizAssessTaskEvaluateTag(bizAssessTaskEvaluateTag));
    }

    /**
     * 修改任务用户互评标记
     */
    @Log(title = "任务用户互评标记", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAssessTaskEvaluateTag bizAssessTaskEvaluateTag) {
        return toAjax(bizAssessTaskEvaluateTagService.updateBizAssessTaskEvaluateTag(bizAssessTaskEvaluateTag));
    }

    /**
     * 删除任务用户互评标记
     */
    @Log(title = "任务用户互评标记", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizAssessTaskEvaluateTagService.deleteBizAssessTaskEvaluateTagByIds(ids));
    }
}
