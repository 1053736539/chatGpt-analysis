package com.cb.worksituation.controller;

import java.util.List;

import com.cb.worksituation.domain.BusDepReviewMemberUnit;
import com.cb.worksituation.domain.BusDepReviewUnitName;
import com.cb.worksituation.service.IBusDepReviewUnitNameService;
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
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2025-10-15
 */
@RestController
@RequestMapping("/business/unit")
public class BusDepReviewUnitNameController extends BaseController
{
    @Autowired
    private IBusDepReviewUnitNameService busDepReviewUnitNameService;
    /**
     * 获取党建业务协作单元
     */
    @GetMapping("/getBusinessCollaborationUnit")
    public List<BusDepReviewUnitName> getBusinessCollaborationUnit(String busDepReviewId)
    {
        List<BusDepReviewUnitName> list = busDepReviewUnitNameService.getBusinessCollaborationUnit(busDepReviewId);;
        return list;
    }
}
