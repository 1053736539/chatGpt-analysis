package com.cb.assess.controller;

import java.util.List;

import com.cb.assess.domain.vo.AssessRegularParam;
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
import com.cb.assess.domain.BizAssessRegularInfo;
import com.cb.assess.service.IBizAssessRegularInfoService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 平时考核登记Controller
 *
 * @author ouyang
 * @date 2023-11-09
 */
@RestController
@RequestMapping("/assess/regularInfo")
public class BizAssessRegularInfoController extends BaseController {
    @Autowired
    private IBizAssessRegularInfoService bizAssessRegularInfoService;

    /**
     * 查询平时考核登记列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BizAssessRegularInfo bizAssessRegularInfo) {
        startPage();
        List<BizAssessRegularInfo> list = bizAssessRegularInfoService.selectBizAssessRegularInfoList();
        return getDataTable(list);
    }


    /**
     * 获取平时考核登记详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizAssessRegularInfoService.selectBizAssessRegularInfoById(id));
    }

    @GetMapping(value = "/getInfoByUserTagId/{userTagId}")
    public AjaxResult getInfoByUserTagId(@PathVariable("userTagId") String userTagId) {
        return AjaxResult.success(bizAssessRegularInfoService.selectBizAssessRegularInfoByUserTagId(userTagId));
    }

    /**
     * 新增平时考核登记
     */
    @Log(title = "平时考核登记", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAssessRegularInfo bizAssessRegularInfo) {
        return toAjax(bizAssessRegularInfoService.insertBizAssessRegularInfo(bizAssessRegularInfo));
    }

    /**
     * 修改平时考核登记
     */
    @Log(title = "平时考核登记", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAssessRegularInfo bizAssessRegularInfo) {
        return toAjax(bizAssessRegularInfoService.updateBizAssessRegularInfo(bizAssessRegularInfo));
    }


    /**
     * 获取需要审核的个人总结
     * @return
     */
    @GetMapping("/needLeaderAuditList")
    public TableDataInfo needLeaderAuditList(BizAssessRegularInfo regular) {
        startPage();
        List<BizAssessRegularInfo> list = bizAssessRegularInfoService.selectNeedLeaderAuditList(regular);
        return getDataTable(list);
    }

    /**+
     * 获取需要机关党委人事处审审定的个人总结
     * @param regular
     * @return
     */
    @GetMapping("/needOrganList")
    public TableDataInfo needOrganList(BizAssessRegularInfo regular) {
        startPage();
        List<BizAssessRegularInfo> list = bizAssessRegularInfoService.selectNeedOrganList(regular);
        return getDataTable(list);
    }


    @Log(title = "填写评定意见", businessType = BusinessType.UPDATE)
    @PutMapping("/fillInComments")
    public AjaxResult fillInComments(@RequestBody BizAssessRegularInfo regular) {
        int i = bizAssessRegularInfoService.fillInComments(regular);
        return AjaxResult.success(i);
    }

    @Log(title = "批量填写评定意见", businessType = BusinessType.UPDATE)
    @PutMapping("/batchFillComments")
    public AjaxResult batchFillComments(@RequestBody AssessRegularParam param) {
        int i = bizAssessRegularInfoService.batchFillInComments(param);
        return AjaxResult.success(i);
    }
}
