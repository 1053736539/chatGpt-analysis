package com.cb.probity.controller;

import java.util.List;

import com.cb.common.utils.SecurityUtils;
import com.cb.probity.vo.ProbityInfoVo;
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
import com.cb.probity.domain.BizProbity;
import com.cb.probity.service.IBizProbityService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 廉政档案记录Controller
 *
 * @author ruoyi
 * @date 2025-03-13
 */
@RestController
@RequestMapping("/probity/probity")
public class BizProbityController extends BaseController {
    @Autowired
    private IBizProbityService bizProbityService;

    /**
     * 查询廉政档案记录列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probity:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizProbity bizProbity) {
        startPage();
        List<BizProbity> list = bizProbityService.selectBizProbityList(bizProbity);
        return getDataTable(list);
    }

    @GetMapping("/search")
    public TableDataInfo search(BizProbity bizProbity) {
        String deptName = SecurityUtils.getOnlineDept().getDeptName();
        bizProbity.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        startPage();
        List<BizProbity> list = bizProbityService.selectBizProbityList(bizProbity);
        return getDataTable(list);
    }

    @GetMapping("/searchInfoById/{id}")
    public AjaxResult searchInfoById(@PathVariable String id) {
        ProbityInfoVo vo = bizProbityService.searchInfoById(id);
        return AjaxResult.success(vo);
    }

    @PostMapping("/updateProbityInfo")
    public AjaxResult updateProbityInfo(@RequestBody ProbityInfoVo vo) {
        String info = bizProbityService.updateProbityInfo(vo);
        return AjaxResult.success(info);
    }

    /**
     * 导出廉政档案记录列表
     */
    @PreAuthorize("@ss.hasPermi('probity:probity:export')")
    @Log(title = "廉政档案记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizProbity bizProbity) {
        List<BizProbity> list = bizProbityService.selectBizProbityList(bizProbity);
        ExcelUtil<BizProbity> util = new ExcelUtil<BizProbity>(BizProbity.class);
        return util.exportExcel(list, "probity");
    }

    /**
     * 获取廉政档案记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('probity:probity:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizProbityService.selectBizProbityById(id));
    }

    /**
     * 新增廉政档案记录
     */
    @PreAuthorize("@ss.hasPermi('probity:probity:add')")
    @Log(title = "廉政档案记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizProbity bizProbity) {
        return AjaxResult.success(bizProbityService.insertBizProbity(bizProbity));
    }

    /**
     * 修改廉政档案记录
     */
    @PreAuthorize("@ss.hasPermi('probity:probity:edit')")
    @Log(title = "廉政档案记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizProbity bizProbity) {
        return toAjax(bizProbityService.updateBizProbity(bizProbity));
    }

    /**
     * 确定廉政档案记录
     */
    @PreAuthorize("@ss.hasPermi('probity:probity:edit')")
    @Log(title = "廉政档案记录", businessType = BusinessType.UPDATE)
    @PostMapping("/verify/{ids}")
    public AjaxResult verify(@PathVariable String[] ids) {
        return toAjax(bizProbityService.verifyBizProbity(ids));
    }

    /**
     * 申请修改廉政档案记录
     */
    @PreAuthorize("@ss.hasPermi('probity:probity:edit')")
    @Log(title = "廉政档案记录", businessType = BusinessType.UPDATE)
    @PostMapping("/apply/{ids}")
    public AjaxResult apply(@PathVariable String[] ids) {
        return toAjax(bizProbityService.applyModifyProbity(ids));
    }

    /**
     * 删除廉政档案记录
     */
    @PreAuthorize("@ss.hasPermi('probity:probity:remove')")
    @Log(title = "廉政档案记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizProbityService.deleteBizProbityByIds(ids));
    }
}
