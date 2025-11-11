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
import com.cb.probity.domain.BizProbityLecture;
import com.cb.probity.service.IBizProbityLectureService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得Controller
 *
 * @author ruoyi
 * @date 2025-03-18
 */
@RestController
@RequestMapping("/probity/probityLecture")
public class BizProbityLectureController extends BaseController {
    @Autowired
    private IBizProbityLectureService bizProbityLectureService;

    /**
     * 查询廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityLecture:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizProbityLecture bizProbityLecture) {
        startPage();
        List<BizProbityLecture> list = bizProbityLectureService.selectBizProbityLectureList(bizProbityLecture);
        return getDataTable(list);
    }

    /**
     * 导出廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probityLecture:export')")
    @Log(title = "廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizProbityLecture bizProbityLecture) {
        List<BizProbityLecture> list = bizProbityLectureService.selectBizProbityLectureList(bizProbityLecture);
        ExcelUtil<BizProbityLecture> util = new ExcelUtil<BizProbityLecture>(BizProbityLecture.class);
        return util.exportExcel(list, "probityLecture");
    }

    /**
     * 获取廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得详细信息
     */
    @PreAuthorize("@ss.hasPermi('probity:probityLecture:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizProbityLectureService.selectBizProbityLectureById(id));
    }

    /**
     * 新增廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得
     */
    @PreAuthorize("@ss.hasPermi('probity:probityLecture:add')")
    @Log(title = "廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizProbityLecture bizProbityLecture) {
        return toAjax(bizProbityLectureService.insertBizProbityLecture(bizProbityLecture));
    }

    /**
     * 修改廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得
     */
    @PreAuthorize("@ss.hasPermi('probity:probityLecture:edit')")
    @Log(title = "廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizProbityLecture bizProbityLecture) {
        return toAjax(bizProbityLectureService.updateBizProbityLecture(bizProbityLecture));
    }

    /**
     * 删除廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得
     */
    @PreAuthorize("@ss.hasPermi('probity:probityLecture:remove')")
    @Log(title = "廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizProbityLectureService.deleteBizProbityLectureByIds(ids));
    }
}
