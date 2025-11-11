package com.cb.assess.controller;

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
import com.cb.assess.domain.BizAssessIndicatorProRatGroup;
import com.cb.assess.service.IBizAssessIndicatorProRatGroupService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 考核方案关联评分组Controller
 *
 * @author ouyang
 * @date 2023-11-01
 */
@RestController
@RequestMapping("/proGroup/assess")
public class BizAssessIndicatorProRatGroupController extends BaseController {
    @Autowired
    private IBizAssessIndicatorProRatGroupService bizAssessIndicatorProRatGroupService;


}
