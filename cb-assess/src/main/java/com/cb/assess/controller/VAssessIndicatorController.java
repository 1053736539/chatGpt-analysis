package com.cb.assess.controller;

import com.cb.assess.domain.BizAssessIndicator;
import com.cb.assess.domain.VAssessIndicator;
import com.cb.assess.service.IVAssessIndicatorService;
import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 考核指标体系Controller
 *
 * @author ouyang
 * @date 2023-10-26
 */
@RestController
@RequestMapping("/assess/vIndicator")
public class VAssessIndicatorController extends BaseController {
    @Autowired
    private IVAssessIndicatorService vAssessIndicatorService;


    @GetMapping("/selectorList")
    public TableDataInfo selectorList(VAssessIndicator assessIndicator) {
        startPage();
        List<VAssessIndicator> list = vAssessIndicatorService.selectAssessIndicatorSelectorList(assessIndicator);
        return getDataTable(list);
    }

    @GetMapping("/selectorAllList")
    public AjaxResult selectorAllList(VAssessIndicator assessIndicator) {
        List<VAssessIndicator> list = vAssessIndicatorService.selectAllAssessIndicatorSelectorList(assessIndicator);
        return AjaxResult.success(list);
    }
}
