package com.cb.assess.controller;

import java.util.List;

import com.cb.assess.domain.BizAssessAnnualReviewTypeResult;
import com.cb.assess.domain.vo.BizAssessAnnualReviewTypeResultVo;
import com.cb.assess.service.IBizAssessAnnualReviewTypeResultService;
import com.cb.common.utils.StringUtils;
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
 * AB类评分结果，Controller
 * 
 * @author ruoyi
 * @date 2023-12-16
 */
@RestController
@RequestMapping("/assess/AnnualReviewResult")
public class BizAssessAnnualReviewTypeResultController extends BaseController
{
    @Autowired
    private IBizAssessAnnualReviewTypeResultService bizAssessAnnualReviewTypeResultService;

//    /**
//     * 查询AB类评分结果，列表
//     */
//    @PreAuthorize("@ss.hasPermi('system:result:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizAssessAnnualReviewTypeResultVo bizAssessAnnualReviewTypeResult)
    {
        List<BizAssessAnnualReviewTypeResultVo> list = bizAssessAnnualReviewTypeResultService.selectBizAssessAnnualReviewTypeResultList(bizAssessAnnualReviewTypeResult);
        return getDataTable(list);
    }
//
//    /**
//     * 导出AB类评分结果，列表
//     */
//    @PreAuthorize("@ss.hasPermi('system:result:export')")
//    @Log(title = "AB类评分结果，", businessType = BusinessType.EXPORT)
//    @GetMapping("/export")
//    public AjaxResult export(BizAssessAnnualReviewTypeResult bizAssessAnnualReviewTypeResult)
//    {
//        List<BizAssessAnnualReviewTypeResult> list = bizAssessAnnualReviewTypeResultService.selectBizAssessAnnualReviewTypeResultList(bizAssessAnnualReviewTypeResult);
//        ExcelUtil<BizAssessAnnualReviewTypeResult> util = new ExcelUtil<BizAssessAnnualReviewTypeResult>(BizAssessAnnualReviewTypeResult.class);
//        return util.exportExcel(list, "result");
//    }

    /**
     * 获取AB类评分结果，详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:result:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(bizAssessAnnualReviewTypeResultService.selectBizAssessAnnualReviewTypeResultById(id));
    }

    /**
     * 新增AB类评分结果，
     */
    @Log(title = "AB类评分结果，", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<BizAssessAnnualReviewTypeResult> list)
    {
        for (BizAssessAnnualReviewTypeResult bizAssessAnnualReviewTypeResult : list) {
            if (StringUtils.isNoneBlank(bizAssessAnnualReviewTypeResult.getId())){
                bizAssessAnnualReviewTypeResultService.updateBizAssessAnnualReviewTypeResult(bizAssessAnnualReviewTypeResult);
            }else {
                bizAssessAnnualReviewTypeResultService.insertBizAssessAnnualReviewTypeResult(bizAssessAnnualReviewTypeResult);
            }
        }
        return success();
    }
    @PostMapping("/publicityAnnualReviewTypeResult")
    public AjaxResult  publicityAnnualReviewTypeResult(@RequestBody BizAssessAnnualReviewTypeResult bizAssessAnnualReviewTypeResult){
        bizAssessAnnualReviewTypeResultService.publicityAnnualReviewTypeResult(bizAssessAnnualReviewTypeResult.getAssessmentYear());
        return success();
    }
//    /**
//     * 修改AB类评分结果，
//     */
//    @PreAuthorize("@ss.hasPermi('system:result:edit')")
//    @Log(title = "AB类评分结果，", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody BizAssessAnnualReviewTypeResult bizAssessAnnualReviewTypeResult)
//    {
//        return toAjax(bizAssessAnnualReviewTypeResultService.updateBizAssessAnnualReviewTypeResult(bizAssessAnnualReviewTypeResult));
//    }
//
//    /**
//     * 删除AB类评分结果，
//     */
//    @PreAuthorize("@ss.hasPermi('system:result:remove')")
//    @Log(title = "AB类评分结果，", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable String[] ids)
//    {
//        return toAjax(bizAssessAnnualReviewTypeResultService.deleteBizAssessAnnualReviewTypeResultByIds(ids));
//    }
}
