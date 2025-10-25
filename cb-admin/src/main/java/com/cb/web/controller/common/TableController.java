package com.cb.web.controller.common;

import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.table.domain.Table;
import com.cb.table.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/table/describe")
public class TableController extends BaseController {
    @Autowired
    private ITableService tableService;
    @GetMapping("/listAll")
    public AjaxResult listAll() {
        List<Table> list = tableService.selectTableListAll();
        return AjaxResult.success(list);
    }

    @GetMapping("/listBase")
    public AjaxResult listBase() {
        List<Table> list = tableService.selectTableListPrefix("bas_");
        return AjaxResult.success(list);
    }

    /**
     * 获取基础数据-源数据表描述
     * @return
     */
    @GetMapping("/listBaseSource")
    public AjaxResult listBaseSource() {
        List<Table> list = tableService.selectBaseDataSourceTableList();
        return AjaxResult.success(list);
    }

    /**
     * 获取基础数据-清洗后数据表描述
     * @return
     */
    @GetMapping("/listBaseCleaned")
    public AjaxResult listBaseCleaned() {
        List<Table> list = tableService.selectBaseDataCleanTableList();
        return AjaxResult.success(list);
    }

    /**
     * 指定表面获取
     * @param tableName
     * @return
     */
    @GetMapping("/getTable/{tableName}")
    public AjaxResult getTable(@PathVariable("tableName") String tableName) {
        Table table = tableService.selectTableListByTableName(tableName);
        return AjaxResult.success(table);
    }


}
