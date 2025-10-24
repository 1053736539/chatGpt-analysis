package com.cb.worksituation.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.worksituation.domain.BusDepReview;
import com.cb.worksituation.domain.BusDepReviewData;
import com.cb.worksituation.domain.BusDepReviewHeader;
import com.cb.worksituation.service.IBusDepReviewDataService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 评分数据Controller
 *
 * @author ruoyi
 * @date 2025-10-11
 */
@RestController
@RequestMapping("/business/data")
public class BusDepReviewDataController extends BaseController {
    @Autowired
    private IBusDepReviewDataService busDepReviewDataService;

    /**
     * 查询评分数据列表
     */
    // @PreAuthorize("@ss.hasPermi('system:DATA:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusDepReviewData busDepReviewData) {
        startPage();
        List<BusDepReviewData> list = busDepReviewDataService.selectBusDepReviewDataList(busDepReviewData);
        return getDataTable(list);
    }

    /**
     * 导出评分数据列表
     */
    // @PreAuthorize("@ss.hasPermi('system:DATA:export')")
    @Log(title = "评分数据", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusDepReviewData busDepReviewData) {
        List<BusDepReviewData> list = busDepReviewDataService.selectBusDepReviewDataList(busDepReviewData);

        ExcelUtil<BusDepReviewData> util = new ExcelUtil<BusDepReviewData>(BusDepReviewData.class);
        return util.exportExcel(list, "DATA");
    }

    public static void writeToExcel(BusDepReview busDepReview, List<BusDepReviewData> busDepReviewDatas) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(busDepReview.getDivisionDept());
        // 设置标题行样式
        CellStyle titleStyle = workbook.createCellStyle();
        Font titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 16);
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        // 设置表头样式
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //  创建标题行
        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(busDepReview.getBusName());
        titleCell.setCellStyle(titleStyle);
        BusDepReviewData busDepReviewData = busDepReviewDatas.get(0);
        String dataJson = busDepReviewData.getDataJson();
        JSONArray jsonArray = JSONArray.parseArray(dataJson);
        Row headerRowMerge = sheet.createRow(1);
        Cell headerRowMergeCell = headerRowMerge.createCell(0);
        headerRowMergeCell.setCellValue("定量评价得分（70分）");
        headerRowMergeCell.setCellStyle(headerStyle);
        List<BusDepReviewHeader> busDepReviewHeaders = JSONArray.parseArray(dataJson, BusDepReviewHeader.class);
        List<BusDepReviewHeader> busDepRevieStr = busDepReviewHeaders.stream().filter(busDepReviewHeader -> busDepReviewHeader.getHeadType().equals("1")).collect(Collectors.toList());
        List<BusDepReviewHeader> busDepRevi = busDepReviewHeaders.stream().filter(busDepReviewHeader -> busDepReviewHeader.getHeadType().equals("2")).collect(Collectors.toList());
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, jsonArray.size() + 2));
        if (!StringUtils.isEmpty(busDepReviewData.getRolePosit())) {
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, jsonArray.size() + 2));
        } else {
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, jsonArray.size() + 1));
        }


        //  表头和说明行
        Row headerRow = sheet.createRow(2);
        Row descRow = sheet.createRow(3);

        // 第一列 评价对象
        Cell firstCell = headerRow.createCell(0);
        firstCell.setCellValue("评价对象");
        firstCell.setCellStyle(headerStyle);
        sheet.autoSizeColumn(0);
        Cell firstDescRow = descRow.createCell(0);
        firstDescRow.setCellValue("");
        firstDescRow.setCellStyle(headerStyle);
        sheet.autoSizeColumn(0);
        // 第二列 角色定位
        Cell twoCell = headerRow.createCell(1);
        twoCell.setCellValue("角色定位");
        twoCell.setCellStyle(headerStyle);
        sheet.autoSizeColumn(1);
        if (!StringUtils.isEmpty(busDepReviewData.getRolePosit())) {
            Cell twoDescRow = descRow.createCell(2);
            twoDescRow.setCellValue("");
            twoDescRow.setCellStyle(headerStyle);
            sheet.autoSizeColumn(1);
            for (int i = 2; i < jsonArray.size() + 3; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i - 2);
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(jsonObject.getString("headName"));
                cell.setCellStyle(headerStyle);
                sheet.autoSizeColumn(i);
                Cell descRowCell = descRow.createCell(i);
                descRowCell.setCellValue(jsonObject.getString("instructions"));
                descRowCell.setCellStyle(headerStyle);
                sheet.autoSizeColumn(i);
                // 自动调整列宽
            }
        } else {
            for (int i = 1; i < jsonArray.size() + 2; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i - 1);
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(jsonObject.getString("headName"));
                cell.setCellStyle(headerStyle);
                sheet.autoSizeColumn(i);
                Cell descRowCell = descRow.createCell(i);
                descRowCell.setCellValue(jsonObject.getString("instructions"));
                descRowCell.setCellStyle(headerStyle);
                sheet.autoSizeColumn(i);
            }
        }


        for (int i = 0; i < busDepReviewDatas.size(); i++) {
            Row dataRow = sheet.createRow(i + 4);
            BusDepReviewData depReviewData = busDepReviewDatas.get(i);
            String reviewDataDataJson = depReviewData.getDataJson();
            JSONArray jsonArrayData = JSONArray.parseArray(reviewDataDataJson);
            Cell twoCellR = dataRow.createCell(1);
            twoCellR.setCellValue(depReviewData.getEvaluatTarget());
            twoCellR.setCellStyle(headerStyle);
            sheet.autoSizeColumn(1);
            if (!StringUtils.isEmpty(busDepReviewData.getRolePosit())) {
                // 第二列 角色定位
                Cell twoDescRow = dataRow.createCell(2);
                twoDescRow.setCellValue(depReviewData.getRolePosit());
                twoDescRow.setCellStyle(headerStyle);
                sheet.autoSizeColumn(1);
                for (int j = 2; j < jsonArrayData.size(); j++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i - 2);
                    Cell cell = dataRow.createCell(i);
                    cell.setCellValue(jsonObject.getBigDecimal("headScore").toString());
                    cell.setCellStyle(headerStyle);
                    sheet.autoSizeColumn(i);
                }
            } else {
                for (int j = 1; j < jsonArrayData.size(); j++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i - 1);
                    Cell cell = dataRow.createCell(i);
                    cell.setCellValue(jsonObject.getBigDecimal("headScore").toString());
                    cell.setCellStyle(headerStyle);
                    sheet.autoSizeColumn(i);
                }
            }
        }
        try (FileOutputStream outputStream = new FileOutputStream("员工信息.xlsx")) {
            workbook.write(outputStream);
            System.out.println("Excel文件生成成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取评分数据详细信息
     */
    // @PreAuthorize("@ss.hasPermi('system:DATA:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(busDepReviewDataService.selectBusDepReviewDataById(id));
    }

    /**
     * 新增评分数据
     */
    // @PreAuthorize("@ss.hasPermi('system:DATA:add')")
    @Log(title = "评分数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<BusDepReviewData> busDepReviewDatas) {
        return null;
//        return toAjax(busDepReviewDataService.insertBusDepReviewData(busDepReviewDatas));
    }

    /**
     * 修改评分数据
     */
    //  @PreAuthorize("@ss.hasPermi('system:DATA:edit')")
    @Log(title = "评分数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusDepReviewData busDepReviewData) {
        if (StringUtils.isEmpty(busDepReviewData.getEvaluatTarget())) {
            return AjaxResult.error("评价对象不能为空");
        }
        return toAjax(busDepReviewDataService.updateBusDepReviewData(busDepReviewData));
    }

    /**
     * 提交打分
     */
    //  @PreAuthorize("@ss.hasPermi('system:DATA:edit')")
    @Log(title = "评分数据", businessType = BusinessType.UPDATE)
    @PostMapping("/submitGrading")
    public AjaxResult submitGrading(@RequestBody BusDepReviewData busDepReviewData) {
        return toAjax(busDepReviewDataService.submitGrading(busDepReviewData));
    }

    /**
     * 删除评分数据
     */
    //  @PreAuthorize("@ss.hasPermi('system:DATA:remove')")
    @Log(title = "评分数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(busDepReviewDataService.deleteBusDepReviewDataByIds(ids));
    }
}
