package com.cb.assess.controller;

import com.cb.assess.service.IVYearAssessHandleService;
import com.cb.assess.vo.VYearAssessHandleVo;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 年度考核相关任务合并查询
 */
@RestController
@RequestMapping("/assess/vYearAssessHandle")
public class VYearAssessHandleController extends BaseController {

    @Autowired
    private IVYearAssessHandleService vYearAssessHandleService;

    @GetMapping("/list")
    public TableDataInfo list(VYearAssessHandleVo.Req req) {
        req.setVoterId(SecurityUtils.getLoginUser().getUser().getUserId());
        startPage();
        List<VYearAssessHandleVo.HandleInfo> list = vYearAssessHandleService.selectList(req);
        return getDataTable(list);
    }

}
