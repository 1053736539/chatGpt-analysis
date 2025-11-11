package com.cb.adopt.controller;

import java.util.List;

import com.cb.adopt.vo.AdoptVo;
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
import com.cb.adopt.domain.BizAdoptRecordScore;
import com.cb.adopt.service.IBizAdoptRecordScoreService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 信息采用记录的相关单位及分值记录Controller
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
@RestController
@RequestMapping("/adopt/adoptRecordScore")
public class BizAdoptRecordScoreController extends BaseController
{
    @Autowired
    private IBizAdoptRecordScoreService bizAdoptRecordScoreService;

    /**
     * 查询信息采用记录的相关单位及分值记录列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:adoptRecordScore:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizAdoptRecordScore bizAdoptRecordScore)
    {
        startPage();
        List<BizAdoptRecordScore> list = bizAdoptRecordScoreService.selectBizAdoptRecordScoreList(bizAdoptRecordScore);
        return getDataTable(list);
    }

    /**
     * 导出信息采用记录的相关单位及分值记录列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:adoptRecordScore:export')")
    @Log(title = "信息采用记录的相关单位及分值记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizAdoptRecordScore bizAdoptRecordScore)
    {
        List<BizAdoptRecordScore> list = bizAdoptRecordScoreService.selectBizAdoptRecordScoreList(bizAdoptRecordScore);
        ExcelUtil<BizAdoptRecordScore> util = new ExcelUtil<BizAdoptRecordScore>(BizAdoptRecordScore.class);
        return util.exportExcel(list, "adoptRecordScore");
    }

    /**
     * 获取信息采用记录的相关单位及分值记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('adopt:adoptRecordScore:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(bizAdoptRecordScoreService.selectBizAdoptRecordScoreById(id));
    }

    /**
     * 新增信息采用记录的相关单位及分值记录
     */
    @PreAuthorize("@ss.hasPermi('adopt:adoptRecordScore:add')")
    @Log(title = "信息采用记录的相关单位及分值记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAdoptRecordScore bizAdoptRecordScore)
    {
        return toAjax(bizAdoptRecordScoreService.insertBizAdoptRecordScore(bizAdoptRecordScore));
    }

    /**
     * 修改信息采用记录的相关单位及分值记录
     */
    @PreAuthorize("@ss.hasPermi('adopt:adoptRecordScore:edit')")
    @Log(title = "信息采用记录的相关单位及分值记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAdoptRecordScore bizAdoptRecordScore)
    {
        return toAjax(bizAdoptRecordScoreService.updateBizAdoptRecordScore(bizAdoptRecordScore));
    }

    /**
     * 删除信息采用记录的相关单位及分值记录
     */
    @PreAuthorize("@ss.hasPermi('adopt:adoptRecordScore:remove')")
    @Log(title = "信息采用记录的相关单位及分值记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bizAdoptRecordScoreService.deleteBizAdoptRecordScoreByIds(ids));
    }

    /**
     * 导出统计信息word
     * @param request
     * @param response
     * @param req
     */
    @PostMapping("/exportStatisticWord")
    public void exportStatisticWord(HttpServletRequest request, HttpServletResponse response, @RequestBody @Valid AdoptVo.StatisticReq req){
        bizAdoptRecordScoreService.exportStatisticWord(response, req);
    }

    /**
     * 导出载体记录信息word
     * @param request
     * @param response
     * @param req
     */
    @PostMapping("/exportCarrierRecordWord")
    public void exportCarrierRecordWord(HttpServletRequest request, HttpServletResponse response, @RequestBody @Valid AdoptVo.StatisticReq req){
        bizAdoptRecordScoreService.exportCarrierRecordWord(response, req);
    }

}
