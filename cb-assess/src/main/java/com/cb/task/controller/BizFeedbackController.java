package com.cb.task.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.task.domain.BizFeedback;
import com.cb.task.service.IBizFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 使用反馈Controller
 * 
 * @author yangxin
 * @date 2024-01-09
 */
@RestController
@RequestMapping("/task/feedback")
public class BizFeedbackController extends BaseController
{
    @Autowired
    private IBizFeedbackService bizFeedbackService;

    /**
     * 查询使用反馈列表
     */
//    @PreAuthorize("@ss.hasPermi('task:feedback:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizFeedback bizFeedback)
    {
        startPage();
        List<BizFeedback> list = bizFeedbackService.selectBizFeedbackList(bizFeedback);
        return getDataTable(list);
    }

    /**
     * 查询我的列表
     * @param bizFeedback
     * @return
     */
    @GetMapping("/listMy")
    public TableDataInfo listMy(BizFeedback bizFeedback)
    {
        bizFeedback.setCreateBy(SecurityUtils.getUsername());
        startPage();
        List<BizFeedback> list = bizFeedbackService.selectBizFeedbackList(bizFeedback);
        return getDataTable(list);
    }

    /**
     * 导出使用反馈列表
     */
//    @PreAuthorize("@ss.hasPermi('task:feedback:export')")
    @Log(title = "使用反馈", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizFeedback bizFeedback)
    {
        List<BizFeedback> list = bizFeedbackService.selectBizFeedbackList(bizFeedback);
        ExcelUtil<BizFeedback> util = new ExcelUtil<BizFeedback>(BizFeedback.class);
        return util.exportExcel(list, "feedback");
    }

    /**
     * 获取使用反馈详细信息
     */
//    @PreAuthorize("@ss.hasPermi('task:feedback:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(bizFeedbackService.selectBizFeedbackById(id));
    }

    /**
     * 新增使用反馈
     */
//    @PreAuthorize("@ss.hasPermi('task:feedback:add')")
    @Log(title = "使用反馈", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizFeedback bizFeedback)
    {
        return toAjax(bizFeedbackService.insertBizFeedback(bizFeedback));
    }

    /**
     * 修改使用反馈
     */
//    @PreAuthorize("@ss.hasPermi('task:feedback:edit')")
    @Log(title = "使用反馈", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizFeedback bizFeedback)
    {
        return toAjax(bizFeedbackService.updateBizFeedback(bizFeedback));
    }

    /**
     * 删除使用反馈
     */
//    @PreAuthorize("@ss.hasPermi('task:feedback:remove')")
    @Log(title = "使用反馈", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(bizFeedbackService.deleteBizFeedbackByIds(ids));
    }
}
