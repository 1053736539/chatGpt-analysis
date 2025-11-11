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
import com.cb.adopt.domain.BizAdoptDept;
import com.cb.adopt.service.IBizAdoptDeptService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 报送单位信息Controller
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
@RestController
@RequestMapping("/adopt/adoptDept")
public class BizAdoptDeptController extends BaseController
{
    @Autowired
    private IBizAdoptDeptService bizAdoptDeptService;

    /**
     * 查询报送单位信息列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:adoptDept:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizAdoptDept bizAdoptDept)
    {
        startPage();
        List<BizAdoptDept> list = bizAdoptDeptService.selectBizAdoptDeptList(bizAdoptDept);
        return getDataTable(list);
    }

    /**
     * 查询报送单位信息列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:adoptDept:list')")
    @GetMapping("/deptList")
    public AjaxResult deptList(BizAdoptDept bizAdoptDept)
    {
//        startPage();
        List<BizAdoptDept> list = bizAdoptDeptService.selectBizAdoptDeptList(bizAdoptDept);
        return AjaxResult.success(list);
    }

    /**
     * 导出报送单位信息列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:adoptDept:export')")
    @Log(title = "报送单位信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizAdoptDept bizAdoptDept)
    {
        List<BizAdoptDept> list = bizAdoptDeptService.selectBizAdoptDeptList(bizAdoptDept);
        ExcelUtil<BizAdoptDept> util = new ExcelUtil<BizAdoptDept>(BizAdoptDept.class);
        return util.exportExcel(list, "adoptDept");
    }

    /**
     * 获取报送单位信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('adopt:adoptDept:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(bizAdoptDeptService.selectBizAdoptDeptById(id));
    }

    /**
     * 新增报送单位信息
     */
    @PreAuthorize("@ss.hasPermi('adopt:adoptDept:add')")
    @Log(title = "报送单位信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAdoptDept bizAdoptDept)
    {
        return toAjax(bizAdoptDeptService.insertBizAdoptDept(bizAdoptDept));
    }

    /**
     * 修改报送单位信息
     */
    @PreAuthorize("@ss.hasPermi('adopt:adoptDept:edit')")
    @Log(title = "报送单位信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAdoptDept bizAdoptDept)
    {
        return toAjax(bizAdoptDeptService.updateBizAdoptDept(bizAdoptDept));
    }

    /**
     * 删除报送单位信息
     */
    @PreAuthorize("@ss.hasPermi('adopt:adoptDept:remove')")
    @Log(title = "报送单位信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(bizAdoptDeptService.deleteBizAdoptDeptByIds(ids));
    }
}
