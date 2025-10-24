package com.cb.worksituation.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.worksituation.domain.BusDepReviewHeader;
import com.cb.worksituation.service.IBusDepReviewHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 部门评分-头Controller
 *
 * @author ruoyi
 * @date 2025-10-11
 */
@RestController
@RequestMapping("/business/header")
public class BusDepReviewHeaderController extends BaseController {
    @Autowired
    private IBusDepReviewHeaderService busDepReviewHeaderService;

    /**
     * 查询部门评分-头列表
     */
    //  @PreAuthorize("@ss.hasPermi('system:HEADER:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusDepReviewHeader busDepReviewHeader) {
        startPage();
        List<BusDepReviewHeader> list = busDepReviewHeaderService.selectBusDepReviewHeaderList(busDepReviewHeader);
        return getDataTable(list);
    }

    /**
     * 导出部门评分-头列表
     */
    //  @PreAuthorize("@ss.hasPermi('system:HEADER:export')")
    @Log(title = "部门评分-头", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusDepReviewHeader busDepReviewHeader) {
        List<BusDepReviewHeader> list = busDepReviewHeaderService.selectBusDepReviewHeaderList(busDepReviewHeader);
        ExcelUtil<BusDepReviewHeader> util = new ExcelUtil<BusDepReviewHeader>(BusDepReviewHeader.class);
        return util.exportExcel(list, "HEADER");
    }

    /**
     * 获取部门评分-头详细信息
     */
    //  @PreAuthorize("@ss.hasPermi('system:HEADER:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(busDepReviewHeaderService.selectBusDepReviewHeaderById(id));
    }

    /**
     * 新增部门评分-头
     */
    // @PreAuthorize("@ss.hasPermi('system:HEADER:add')")
    @Log(title = "部门评分-头", businessType = BusinessType.INSERT)
    @PostMapping
//    public AjaxResult add(@RequestBody BusDepReviewHeader busDepReviewHeader) {
//        BusDepReviewHeader depReviewHeader = new BusDepReviewHeader();
//        depReviewHeader.setHeadCode(busDepReviewHeader.getHeadCode());
//        List<BusDepReviewHeader> list = busDepReviewHeaderService.selectBusDepReviewHeaderList(depReviewHeader);
//        if (!CollectionUtils.isEmpty(list)) {
//            return AjaxResult.error("编码不能重复");
//        }
//        return toAjax(busDepReviewHeaderService.insertBusDepReviewHeader(busDepReviewHeader));
//    }
    public AjaxResult add(@RequestBody List<BusDepReviewHeader> busDepReviewHeaders) {
        if (CollectionUtils.isEmpty(busDepReviewHeaders)) {
            return AjaxResult.error("新增数据不能为空");
        }

        // 校验传入数据中是否存在重复编码
        Set<String> headCodeSet = new HashSet<>();
        for (BusDepReviewHeader busDepReviewHeader : busDepReviewHeaders) {
            if (busDepReviewHeader == null || StringUtils.isBlank(busDepReviewHeader.getHeadCode())) {
                continue;
            }
            if (!headCodeSet.add(busDepReviewHeader.getHeadCode())) {
                return AjaxResult.error("编码不能重复");
            }
        }

        // 先删除相同业务考评ID下的历史数据，实现覆盖保存
        Set<String> reviewIds = busDepReviewHeaders.stream()
                .filter(Objects::nonNull)
                .map(BusDepReviewHeader::getBusDepReviewId)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toSet());
        for (String reviewId : reviewIds) {
            busDepReviewHeaderService.deleteBusDepReviewHeaderByBusDepReviewId(reviewId);
        }

        // 校验数据库中是否存在重复编码
        for (BusDepReviewHeader busDepReviewHeader : busDepReviewHeaders) {
            if (busDepReviewHeader == null || StringUtils.isBlank(busDepReviewHeader.getHeadCode())) {
                continue;
            }
            BusDepReviewHeader depReviewHeader = new BusDepReviewHeader();
            depReviewHeader.setHeadCode(busDepReviewHeader.getHeadCode());
            List<BusDepReviewHeader> list = busDepReviewHeaderService.selectBusDepReviewHeaderList(depReviewHeader);
            if (!CollectionUtils.isEmpty(list)) {
                return AjaxResult.error("编码不能重复");
            }
        }

        return toAjax(busDepReviewHeaderService.insertBatch(busDepReviewHeaders));
    }

    /**
     * 修改部门评分-头
     */
    //  @PreAuthorize("@ss.hasPermi('system:HEADER:edit')")
    @Log(title = "部门评分-头", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusDepReviewHeader busDepReviewHeader) {
        BusDepReviewHeader depReviewHeader = new BusDepReviewHeader();
        depReviewHeader.setHeadCode(busDepReviewHeader.getHeadCode());
        List<BusDepReviewHeader> list = busDepReviewHeaderService.selectBusDepReviewHeaderList(depReviewHeader);
        if (!CollectionUtils.isEmpty(list)) {
            List<BusDepReviewHeader> busDepReviewHeaderList = list.stream().filter(busDepReviewHead -> busDepReviewHead.getHeadCode().equals(busDepReviewHeader.getHeadCode())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(busDepReviewHeaderList)) {
                return AjaxResult.error("编码不能重复");
            }
        }
        return toAjax(busDepReviewHeaderService.updateBusDepReviewHeader(busDepReviewHeader));
    }

    /**
     * 删除部门评分-头
     */
    //  @PreAuthorize("@ss.hasPermi('system:HEADER:remove')")
    @Log(title = "部门评分-头", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(busDepReviewHeaderService.deleteBusDepReviewHeaderByIds(ids));
    }
}
