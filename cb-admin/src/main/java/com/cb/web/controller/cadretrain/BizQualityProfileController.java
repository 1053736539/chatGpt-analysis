package com.cb.web.controller.cadretrain;

import java.util.List;

import com.cb.cadretrain.domain.BizQualityProfile;
import com.cb.cadretrain.service.IBizQualityProfileService;
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
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 干部政治素质档案Controller
 * 
 * @author lennon
 * @date 2023-11-01
 */
@RestController
@RequestMapping("/biz/profile")
public class BizQualityProfileController extends BaseController
{
    @Autowired
    private IBizQualityProfileService bizQualityProfileService;

    /**
     * 查询干部政治素质档案列表
     */
    @PreAuthorize("@ss.hasPermi('biz:profile:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizQualityProfile bizQualityProfile)
    {
        startPage();
        List<BizQualityProfile> list = bizQualityProfileService.selectBizQualityProfileList(bizQualityProfile);
        return getDataTable(list);
    }

    /**
     * 导出干部政治素质档案列表
     */
    @PreAuthorize("@ss.hasPermi('biz:profile:export')")
    @Log(title = "干部政治素质档案", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizQualityProfile bizQualityProfile)
    {
        List<BizQualityProfile> list = bizQualityProfileService.selectBizQualityProfileList(bizQualityProfile);
        ExcelUtil<BizQualityProfile> util = new ExcelUtil<BizQualityProfile>(BizQualityProfile.class);
        return util.exportExcel(list, "profile");
    }

    /**
     * 获取干部政治素质档案详细信息
     */
    @PreAuthorize("@ss.hasPermi('biz:profile:query')")
    @GetMapping(value = "/{profileId}")
    public AjaxResult getInfo(@PathVariable("profileId") String profileId)
    {
        return AjaxResult.success(bizQualityProfileService.selectBizQualityProfileById(profileId));
    }

    /**
     * 新增干部政治素质档案
     */
    @PreAuthorize("@ss.hasPermi('biz:profile:add')")
    @Log(title = "干部政治素质档案", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizQualityProfile bizQualityProfile)
    {
        return toAjax(bizQualityProfileService.insertBizQualityProfile(bizQualityProfile));
    }

    /**
     * 修改干部政治素质档案
     */
    @PreAuthorize("@ss.hasPermi('biz:profile:edit')")
    @Log(title = "干部政治素质档案", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizQualityProfile bizQualityProfile)
    {
        return toAjax(bizQualityProfileService.updateBizQualityProfile(bizQualityProfile));
    }

    /**
     * 删除干部政治素质档案
     */
    @PreAuthorize("@ss.hasPermi('biz:profile:remove')")
    @Log(title = "干部政治素质档案", businessType = BusinessType.DELETE)
	@DeleteMapping("/{profileIds}")
    public AjaxResult remove(@PathVariable String[] profileIds)
    {
        return toAjax(bizQualityProfileService.deleteBizQualityProfileByIds(profileIds));
    }
}
