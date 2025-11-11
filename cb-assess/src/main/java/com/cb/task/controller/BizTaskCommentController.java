package com.cb.task.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.task.domain.BizTaskComment;
import com.cb.task.service.IBizTaskCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 任务评论Controller
 * 
 * @author yangxin
 * @date 2023-11-11
 */
@RestController
@RequestMapping("/task/taskComment")
public class BizTaskCommentController extends BaseController
{
    @Autowired
    private IBizTaskCommentService bizTaskCommentService;

    /**
     * 查询任务评论列表
     */
    @PreAuthorize("@ss.hasPermi('task:taskComment:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizTaskComment bizTaskComment)
    {
        startPage();
        List<BizTaskComment> list = bizTaskCommentService.selectBizTaskCommentList(bizTaskComment);
        return getDataTable(list);
    }

    /**
     * 导出任务评论列表
     */
    @PreAuthorize("@ss.hasPermi('task:taskComment:export')")
    @Log(title = "任务评论", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizTaskComment bizTaskComment)
    {
        List<BizTaskComment> list = bizTaskCommentService.selectBizTaskCommentList(bizTaskComment);
        ExcelUtil<BizTaskComment> util = new ExcelUtil<BizTaskComment>(BizTaskComment.class);
        return util.exportExcel(list, "taskComment");
    }

    /**
     * 获取任务评论详细信息
     */
    @PreAuthorize("@ss.hasPermi('task:taskComment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(bizTaskCommentService.selectBizTaskCommentById(id));
    }

    /**
     * 新增任务评论
     */
//    @PreAuthorize("@ss.hasPermi('task:taskComment:add')")
    @Log(title = "任务评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizTaskComment bizTaskComment)
    {
        return toAjax(bizTaskCommentService.insertBizTaskComment(bizTaskComment));
    }

    /**
     * 修改任务评论
     */
    @PreAuthorize("@ss.hasPermi('task:taskComment:edit')")
    @Log(title = "任务评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizTaskComment bizTaskComment)
    {
        return toAjax(bizTaskCommentService.updateBizTaskComment(bizTaskComment));
    }

    /**
     * 删除任务评论
     */
    @PreAuthorize("@ss.hasPermi('task:taskComment:remove')")
    @Log(title = "任务评论", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bizTaskCommentService.deleteBizTaskCommentByIds(ids));
    }
}
