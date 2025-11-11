package com.cb.assess.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cb.common.core.controller.BaseController;
import com.cb.assess.service.IBizAssessIndicatorProProcedureService;

/**
 * 考核方法程序Controller
 *
 * @author ouyang
 * @date 2023-11-03
 */
@RestController
@RequestMapping("/assess/procedure")
public class BizAssessIndicatorProProcedureController extends BaseController {
    @Autowired
    private IBizAssessIndicatorProProcedureService bizAssessIndicatorProProcedureService;


}
