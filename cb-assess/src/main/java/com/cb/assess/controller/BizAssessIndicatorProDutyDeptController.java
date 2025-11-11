package com.cb.assess.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cb.common.core.controller.BaseController;
import com.cb.assess.service.IBizAssessIndicatorProDutyService;

/**
 * 被考核部门Controller
 *
 * @author ouyang
 * @date 2023-11-01
 */
@RestController
@RequestMapping("/assess/dutyDept")
public class BizAssessIndicatorProDutyDeptController extends BaseController {
    @Autowired
    private IBizAssessIndicatorProDutyService bizAssessIndicatorProDutyDeptService;

}
