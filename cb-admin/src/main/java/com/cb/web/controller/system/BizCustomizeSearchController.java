package com.cb.web.controller.system;

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
import com.cb.system.domain.BizCustomizeSearch;
import com.cb.system.service.IBizCustomizeSearchService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 自定义查询方案Controller
 *
 * @author hujilie
 * @date 2023-11-27
 */
@RestController
@RequestMapping("/system/search")
public class BizCustomizeSearchController extends BaseController
{
    @Autowired
    private IBizCustomizeSearchService bizCustomizeSearchService;

    /**
     * 查询自定义查询方案列表
     */
    @PreAuthorize("@ss.hasPermi('system:search:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizCustomizeSearch bizCustomizeSearch)
    {
        startPage();
        List<BizCustomizeSearch> list = bizCustomizeSearchService.selectBizCustomizeSearchList(bizCustomizeSearch);
        return getDataTable(list);
    }

    /**
     * 导出自定义查询方案列表
     */
    @PreAuthorize("@ss.hasPermi('system:search:export')")
    @Log(title = "自定义查询方案", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizCustomizeSearch bizCustomizeSearch)
    {
        List<BizCustomizeSearch> list = bizCustomizeSearchService.selectBizCustomizeSearchList(bizCustomizeSearch);
        ExcelUtil<BizCustomizeSearch> util = new ExcelUtil<BizCustomizeSearch>(BizCustomizeSearch.class);
        return util.exportExcel(list, "search");
    }

    /**
     * 获取自定义查询方案详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:search:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(bizCustomizeSearchService.selectBizCustomizeSearchById(id));
    }

    /**
     * 新增自定义查询方案
     */
    @PreAuthorize("@ss.hasPermi('system:search:add')")
    @Log(title = "自定义查询方案", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizCustomizeSearch bizCustomizeSearch)
    {
        return toAjax(bizCustomizeSearchService.insertBizCustomizeSearch(bizCustomizeSearch));
    }

    /**
     * 修改自定义查询方案
     */
    @PreAuthorize("@ss.hasPermi('system:search:edit')")
    @Log(title = "自定义查询方案", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizCustomizeSearch bizCustomizeSearch)
    {
        return toAjax(bizCustomizeSearchService.updateBizCustomizeSearch(bizCustomizeSearch));
    }

    /**
     * 删除自定义查询方案
     */
    @PreAuthorize("@ss.hasPermi('system:search:remove')")
    @Log(title = "自定义查询方案", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(bizCustomizeSearchService.deleteBizCustomizeSearchByIds(ids));
    }
}
