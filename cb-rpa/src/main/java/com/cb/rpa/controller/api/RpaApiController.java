package com.cb.rpa.controller.api;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.rpa.domain.RpaCcdiWebsite;
import com.cb.rpa.domain.RpaKmjjwWebsite;
import com.cb.rpa.service.IRpaCcdiWebsiteService;
import com.cb.rpa.service.IRpaKmjjwWebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 昆明市纪监委网站Controller
 *
 * @author ouyang
 * @date 2024-10-22
 */
@RestController
@RequestMapping("/rpa/api/v1")
public class RpaApiController extends BaseController {
    @Autowired
    private IRpaKmjjwWebsiteService kmjjwWebsiteService;

    @Autowired
    private IRpaCcdiWebsiteService ccdiWebsiteService;

    /**
     * 新增昆明市纪监委网站
     */
    @Log(title = "昆明市纪监委网站-RPA 远端调用", businessType = BusinessType.INSERT)
    @PostMapping("/kmjjw/collect")
    public AjaxResult collectKmjjwWebsite(@RequestBody RpaKmjjwWebsite rpaKmjjwWebsite) {
        return toAjax(kmjjwWebsiteService.collectWebsiteInfo(rpaKmjjwWebsite));
    }

    @Log(title = "中纪委网站-RPA 远端调用", businessType = BusinessType.INSERT)
    @PostMapping("/ccdi/collect")
    public AjaxResult collectCcdiWebsite(@RequestBody RpaCcdiWebsite ccdiWebsite) {
        return toAjax(ccdiWebsiteService.collectWebsiteInfo(ccdiWebsite));
    }
}
