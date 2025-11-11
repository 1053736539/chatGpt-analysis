package com.cb.basedata.controller;

import java.util.List;

import com.cb.basedata.domain.vo.CleanSourceDataParam;
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
import com.cb.basedata.domain.BasicDataCleanTask;
import com.cb.basedata.service.IBasicDataCleanTaskService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 数据清洗任务Controller
 * 
 * @author ouyang
 * @date 2024-11-01
 */
@RestController
@RequestMapping("/basedata/cleanTask")
public class BasicDataCleanTaskController extends BaseController
{
    @Autowired
    private IBasicDataCleanTaskService basicDataCleanTaskService;

    /**
     * 查询数据清洗任务列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BasicDataCleanTask basicDataCleanTask)
    {
        startPage();
        List<BasicDataCleanTask> list = basicDataCleanTaskService.selectBasicDataCleanTaskList(basicDataCleanTask);
        return getDataTable(list);
    }

    /**
     * 导出数据清洗任务列表
     */
    @Log(title = "数据清洗任务", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicDataCleanTask basicDataCleanTask)
    {
        List<BasicDataCleanTask> list = basicDataCleanTaskService.selectBasicDataCleanTaskList(basicDataCleanTask);
        ExcelUtil<BasicDataCleanTask> util = new ExcelUtil<BasicDataCleanTask>(BasicDataCleanTask.class);
        return util.exportExcel(list, "cleanTask");
    }

    /**
     * 获取数据清洗任务详细信息
     */
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") String taskId)
    {
        return AjaxResult.success(basicDataCleanTaskService.selectBasicDataCleanTaskById(taskId));
    }

    /**
     * 新增数据清洗任务
     */
    @Log(title = "数据清洗任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasicDataCleanTask basicDataCleanTask)
    {
        return toAjax(basicDataCleanTaskService.insertBasicDataCleanTask(basicDataCleanTask));
    }

    /**
     * 修改数据清洗任务
     */
    @Log(title = "数据清洗任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasicDataCleanTask basicDataCleanTask)
    {
        return toAjax(basicDataCleanTaskService.updateBasicDataCleanTask(basicDataCleanTask));
    }

    /**
     * 删除数据清洗任务
     */
    @Log(title = "数据清洗任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable String[] taskIds)
    {
        return toAjax(basicDataCleanTaskService.deleteBasicDataCleanTaskByIds(taskIds));
    }

    @Log(title = "执行数据清洗任务", businessType = BusinessType.OTHER)
    @PostMapping(value = "/dataCleaning")
    public AjaxResult dataCleaning(@RequestBody CleanSourceDataParam param) {
        int i = basicDataCleanTaskService.dataCleaning(param);
        return AjaxResult.success(i);
    }

    /**
     * 下载导入模板
     * @param taskId
     * @return
     */
    @GetMapping(value = "/importTemplate/{taskId}")
    public AjaxResult importTemplate(@PathVariable("taskId")String taskId) {
        return basicDataCleanTaskService.importTemplate(taskId);
    }
}
