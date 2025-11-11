package com.cb.leave.controller;

import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.leave.domain.vo.LeaveCensusVo;
import com.cb.leave.service.LeaveCensusService;
import com.cb.leave.vo.LeaveChartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 请休统计Controller
 *
 * @author ouyang
 * @date 2025-01-11
 */
@RestController
@RequestMapping("/leave/census")
public class LeaveCensusController extends BaseController {
    @Autowired
    private LeaveCensusService leaveCensusService;

    /**
     * 获取请假情况树形结构数据
     * 个人只能查看个人的请销假情况，部门领导可以查看整个部门的情况,其他返回空数据
     * @param vo
     * @return
     */
    @GetMapping("/leaveSituationList")
    public TableDataInfo leaveSituationList(LeaveCensusVo vo) {
//        startPage();
        List<LeaveCensusVo> list = leaveCensusService.selectLeaveSituationList(vo);
        return getDataTable(list);
    }

    /**
     * 首页chart数据
     * @return
     */
    @PostMapping("/homeChartData")
    public AjaxResult homeChartData(@Valid @RequestBody LeaveChartVo.HomeChartReq req){
        LeaveChartVo.HomeChartResp resp = leaveCensusService.listHomeChartData(req);
        return AjaxResult.success(resp);
    }

    /**
     * 导出公休率表格
     * @param req
     * @return
     */
    @PostMapping("/exportHomeChartData")
    public AjaxResult exportHomeBarData(@Valid @RequestBody LeaveChartVo.HomeChartReq req){
        LeaveChartVo.HomeChartResp resp = leaveCensusService.listHomeChartData(req);
        List<LeaveChartVo.ExportBarDataItem> list = resp.getItemList().stream()
                .map(LeaveChartVo.ExportBarDataItem::new).collect(Collectors.toList());
        ExcelUtil<LeaveChartVo.ExportBarDataItem> excelUtil = new ExcelUtil<>(LeaveChartVo.ExportBarDataItem.class);
        return excelUtil.exportExcel(list, "各部门公休率情况");
    }
}
