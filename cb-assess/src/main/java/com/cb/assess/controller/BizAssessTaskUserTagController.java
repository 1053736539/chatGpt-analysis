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
import com.cb.assess.domain.BizAssessTaskUserTag;
import com.cb.assess.service.IBizAssessTaskUserTagService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 任务与用户关联Controller
 *
 * @author ouyang
 * @date 2023-11-08
 */
@RestController
@RequestMapping("/assess/userTag")
public class BizAssessTaskUserTagController extends BaseController {
    @Autowired
    private IBizAssessTaskUserTagService bizAssessTaskUserTagService;

    /**
     * 查询任务与用户关联列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BizAssessTaskUserTag bizAssessTaskUserTag) {
        startPage();
        List<BizAssessTaskUserTag> list = bizAssessTaskUserTagService.selectBizAssessTaskUserTagList(bizAssessTaskUserTag);
        return getDataTable(list);
    }

    /**
     * 导出任务与用户关联列表
     */
    @Log(title = "任务与用户关联", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizAssessTaskUserTag bizAssessTaskUserTag) {
        List<BizAssessTaskUserTag> list = bizAssessTaskUserTagService.selectBizAssessTaskUserTagList(bizAssessTaskUserTag);
        ExcelUtil<BizAssessTaskUserTag> util = new ExcelUtil<BizAssessTaskUserTag>(BizAssessTaskUserTag.class);
        return util.exportExcel(list, "userTag");
    }

    /**
     * 获取任务与用户关联详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizAssessTaskUserTagService.selectBizAssessTaskUserTagById(id));
    }

    /**
     * 新增任务与用户关联
     */
    @Log(title = "任务与用户关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAssessTaskUserTag bizAssessTaskUserTag) {
        return toAjax(bizAssessTaskUserTagService.insertBizAssessTaskUserTag(bizAssessTaskUserTag));
    }

    /**
     * 修改任务与用户关联
     */
    @Log(title = "任务与用户关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAssessTaskUserTag bizAssessTaskUserTag) {
        return toAjax(bizAssessTaskUserTagService.updateBizAssessTaskUserTag(bizAssessTaskUserTag));
    }

    /**
     * 删除任务与用户关联
     */
    @Log(title = "任务与用户关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizAssessTaskUserTagService.deleteBizAssessTaskUserTagByIds(ids));
    }
}
