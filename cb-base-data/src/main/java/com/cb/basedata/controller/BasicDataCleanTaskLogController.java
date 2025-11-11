package com.cb.basedata.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.enums.BusinessType;
import com.cb.basedata.domain.BasicDataCleanTaskLog;
import com.cb.basedata.service.IBasicDataCleanTaskLogService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 数据清洗任务日志Controller
 *
 * @author ouyang
 * @date 2024-11-01
 */
@RestController
@RequestMapping("/basedata/tasklog")
public class BasicDataCleanTaskLogController extends BaseController {
    @Autowired
    private IBasicDataCleanTaskLogService basicDataCleanTaskLogService;

    /**
     * 查询数据清洗任务日志列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BasicDataCleanTaskLog basicDataCleanTaskLog) {
        startPage();
        List<BasicDataCleanTaskLog> list = basicDataCleanTaskLogService.selectBasicDataCleanTaskLogList(basicDataCleanTaskLog);
        return getDataTable(list);
    }

    /**
     * 导出数据清洗任务日志列表
     */
    @Log(title = "数据清洗任务日志", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicDataCleanTaskLog basicDataCleanTaskLog) {
        List<BasicDataCleanTaskLog> list = basicDataCleanTaskLogService.selectBasicDataCleanTaskLogList(basicDataCleanTaskLog);
        ExcelUtil<BasicDataCleanTaskLog> util = new ExcelUtil<BasicDataCleanTaskLog>(BasicDataCleanTaskLog.class);
        return util.exportExcel(list, "tasklog");
    }

    /**
     * 获取数据清洗任务日志详细信息
     */
    @GetMapping(value = "/{logId}")
    public AjaxResult getInfo(@PathVariable("logId") String logId) {
        return AjaxResult.success(basicDataCleanTaskLogService.selectBasicDataCleanTaskLogById(logId));
    }

    /**
     * 删除数据清洗任务日志
     */
    @PreAuthorize("@ss.hasPermi('basedata:tasklog:remove')")
    @Log(title = "数据清洗任务日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{logIds}")
    public AjaxResult remove(@PathVariable String[] logIds) {
        return toAjax(basicDataCleanTaskLogService.deleteBasicDataCleanTaskLogByIds(logIds));
    }
}
