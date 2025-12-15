package com.cb.worksituation.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.worksituation.domain.BusDepReview;
import com.cb.worksituation.domain.BusDepReviewData;
import com.cb.worksituation.domain.BusDepReviewHeader;
import com.cb.worksituation.mapper.BusDepReviewHeaderMapper;
import com.cb.worksituation.service.IBusDepReviewDataService;
import com.cb.worksituation.service.IBusDepReviewHeaderService;
import com.cb.worksituation.service.IBusDepReviewService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

    @Autowired
    private IBusDepReviewService busDepReviewService;

    @Autowired
    private IBusDepReviewHeaderService busDepReviewHeaderService;

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
    public void export(BusDepReviewData busDepReviewData, HttpServletResponse response) throws UnsupportedEncodingException {
        List<BusDepReviewData> list = busDepReviewDataService.selectBusDepReviewDataList(busDepReviewData);
        String busDepReviewId = busDepReviewData.getBusDepReviewId();
        BusDepReview busDepReview = busDepReviewService.selectBusDepReviewById(busDepReviewId);
        // 解析数据
        BusDepReviewHeader headerQuery = new BusDepReviewHeader();
        headerQuery.setBusDepReviewId(busDepReview.getId());
        List<BusDepReviewHeader> busDepReviewHeaders = busDepReviewHeaderService.selectBusDepReviewHeaderList(headerQuery);
        writeToExcel(busDepReview, busDepReviewHeaders, list, response);
        // 设置响应头
    }

    public static void writeToExcel(BusDepReview busDepReview, List<BusDepReviewHeader> busDepReviewHeaders, List<BusDepReviewData> busDepReviewDatas, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(busDepReview.getBusName(), "utf-8") + ".xlsx");
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(busDepReview.getDivisionDept());
        sheet.setDefaultRowHeightInPoints(20);
        sheet.setDefaultColumnWidth(25);

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
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // 创建标题行
        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(busDepReview.getBusName());
        titleCell.setCellStyle(titleStyle);

        BusDepReviewData busDepReviewData = busDepReviewDatas.get(0);

        List<BusDepReviewHeader> busDepRevieStr = busDepReviewHeaders.stream().filter(busDepReviewHeader -> busDepReviewHeader.getHeadType() != null && busDepReviewHeader.getHeadType().equals("2")).collect(Collectors.toList());

        List<BusDepReviewHeader> qualitativeHeaders = busDepReviewHeaders.stream().filter(busDepReviewHeader -> busDepReviewHeader.getHeadType() != null && busDepReviewHeader.getHeadType().equals("3")).collect(Collectors.toList());

        List<BusDepReviewHeader> bonusHeaders = busDepReviewHeaders.stream().filter(busDepReviewHeader -> busDepReviewHeader.getHeadType() != null && busDepReviewHeader.getHeadType().equals("4")).collect(Collectors.toList());

        List<BusDepReviewHeader> deductHeaders = busDepReviewHeaders.stream().filter(busDepReviewHeader -> busDepReviewHeader.getHeadType() != null && busDepReviewHeader.getHeadType().equals("5")).collect(Collectors.toList());

        List<BusDepReviewHeader> partyUnitHeaders = busDepReviewHeaders.stream().filter(busDepReviewHeader -> busDepReviewHeader.getHeadType() != null && busDepReviewHeader.getHeadType().equals("6")).collect(Collectors.toList());

        boolean hasRolePosit = !StringUtils.isEmpty(busDepReviewData.getRolePosit());

        // 创建第一行（合并行）
        Row headerRowMerge = sheet.createRow(1);

        // 评价对象单元格（合并第1-2行，第0列）
        Cell evalHeaderCell = headerRowMerge.createCell(0);
        evalHeaderCell.setCellValue("评价对象");
        evalHeaderCell.setCellStyle(headerStyle);
        sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));

        int currentCol = 1; // 当前列索引

        if (hasRolePosit) {
            // 角色定位单元格（合并第1-2行，第1列）
            Cell roleHeaderCell = headerRowMerge.createCell(currentCol);
            roleHeaderCell.setCellValue("角色定位");
            roleHeaderCell.setCellStyle(headerStyle);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, currentCol, currentCol));
            currentCol++;
        }

        // 计算各部分的列数
        int quantitativeCols = Math.max(busDepRevieStr.size(), 1);
        int qualitativeCols = Math.max(qualitativeHeaders.size(), 1);
        int bonusCols = Math.max(bonusHeaders.size(), 1);
        int deductCols = Math.max(deductHeaders.size(), 1);
        int partyUnitCols = Math.max(partyUnitHeaders.size(), 0);

        // 定量评价得分（70分） + 小计
        if (quantitativeCols > 0) {
            Cell quantitativeCell = headerRowMerge.createCell(currentCol);
            quantitativeCell.setCellValue("定量评价得分（70分）");
            quantitativeCell.setCellStyle(headerStyle);

            // 合并定量评价得分和小计列
            int quantitativeEndCol = currentCol + quantitativeCols; // 包含小计列
            if (quantitativeEndCol > currentCol) {
                sheet.addMergedRegion(new CellRangeAddress(1, 1, currentCol, quantitativeEndCol));
            }
            currentCol = quantitativeEndCol + 1;
        }

        // 定性评价得分（30分） - 合并为一行
        if (qualitativeCols > 0) {
            Cell qualitativeCell = headerRowMerge.createCell(currentCol);
            qualitativeCell.setCellValue("定性评价得分（30分）");
            qualitativeCell.setCellStyle(headerStyle);

            // 合并定性评价的所有列
            int qualitativeEndCol = currentCol + qualitativeCols - 1;
            sheet.addMergedRegion(new CellRangeAddress(1, 2, currentCol, qualitativeEndCol));
            currentCol = qualitativeEndCol + 1;
        }

        // 加分 + 小计
        if (bonusCols > 0) {
            Cell bonusCell = headerRowMerge.createCell(currentCol);
            bonusCell.setCellValue("加分");
            bonusCell.setCellStyle(headerStyle);

            // 合并加分和小计列
            int bonusEndCol = currentCol + bonusCols; // 包含小计列
            if (bonusEndCol > currentCol) {
                sheet.addMergedRegion(new CellRangeAddress(1, 1, currentCol, bonusEndCol));
            }
            currentCol = bonusEndCol + 1;
        }

        // 扣分 - 合并两个扣分单元格
        if (deductCols > 0) {
            Cell deductCell = headerRowMerge.createCell(currentCol);
            deductCell.setCellValue("扣分");
            deductCell.setCellStyle(headerStyle);
            // 合并两个扣分
            int deductColsEndCol = currentCol + deductCols; // 包含小计列
            if (deductColsEndCol > currentCol) {
                sheet.addMergedRegion(new CellRangeAddress(1, 1, currentCol, deductColsEndCol));
            }
            currentCol = deductColsEndCol + 1;
        }

        // 党建业务协作单元加分 - 可能不存在，如果存在则显示
        if (partyUnitCols > 0) {
            Cell partyUnitCell = headerRowMerge.createCell(currentCol);
            partyUnitCell.setCellValue("党建业务协作单元加分");
            partyUnitCell.setCellStyle(headerStyle);

            // 合并党建业务协作单元加分单元格（第1-2行）
            sheet.addMergedRegion(new CellRangeAddress(1, 2, currentCol, currentCol));
            currentCol++;
        }

        // 业务评价得分
        Cell totalCell = headerRowMerge.createCell(currentCol);
        totalCell.setCellValue("业务评价得分");
        totalCell.setCellStyle(headerStyle);
        sheet.addMergedRegion(new CellRangeAddress(1, 2, currentCol, currentCol));

        // 主标题合并
        int lastColumn = currentCol;
        if (lastColumn > 0) {
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, lastColumn));
        }

        // 创建第二行（具体指标行）
        Row headerRow = sheet.createRow(2);

        // 重置列索引
        currentCol = hasRolePosit ? 2 : 1;

        // 添加定量评价的具体指标 + 小计
        if (!busDepRevieStr.isEmpty()) {
            for (BusDepReviewHeader header : busDepRevieStr) {
                Cell cell = headerRow.createCell(currentCol);
                cell.setCellValue(header.getHeadName());
                cell.setCellStyle(headerStyle);
                currentCol++;
            }
            // 定量评价小计
            Cell subtotalCell1 = headerRow.createCell(currentCol);
            subtotalCell1.setCellValue("小计");
            subtotalCell1.setCellStyle(headerStyle);
            currentCol++;
        }

        // 添加定性评价的具体指标（不显示小计，因为已经合并为一行）
        if (!qualitativeHeaders.isEmpty()) {
            for (BusDepReviewHeader header : qualitativeHeaders) {
                Cell cell = headerRow.createCell(currentCol);
                cell.setCellValue(header.getHeadName());
                cell.setCellStyle(headerStyle);
                currentCol++;
            }
            // 定性评价不显示小计列
        }

        // 添加加分的具体指标 + 小计
        if (!bonusHeaders.isEmpty()) {
            for (BusDepReviewHeader header : bonusHeaders) {
                Cell cell = headerRow.createCell(currentCol);
                cell.setCellValue(header.getHeadName());
                cell.setCellStyle(headerStyle);
                currentCol++;
            }
            // 加分小计
            Cell subtotalCell3 = headerRow.createCell(currentCol);
            subtotalCell3.setCellValue("小计");
            subtotalCell3.setCellStyle(headerStyle);
            currentCol++;
        }

        // 添加扣分的具体指标（不显示小计，因为已经合并为一行）
        if (!deductHeaders.isEmpty()) {
            for (BusDepReviewHeader header : deductHeaders) {
                Cell cell = headerRow.createCell(currentCol);
                cell.setCellValue(header.getHeadName());
                cell.setCellStyle(headerStyle);
                currentCol++;
            }
            // 加分小计
            Cell subtotalCell4 = headerRow.createCell(currentCol);
            subtotalCell4.setCellValue("小计");
            subtotalCell4.setCellStyle(headerStyle);
            currentCol++;
        }

        // 添加数据行
        for (int i = 0; i < busDepReviewDatas.size(); i++) {
            Row dataRow = sheet.createRow(i + 3);
            BusDepReviewData depReviewData = busDepReviewDatas.get(i);
            String reviewDataDataJson = depReviewData.getDataJson();
            JSONArray jsonArrayData = JSONArray.parseArray(reviewDataDataJson);

            // 评价对象
            Cell evalCell = dataRow.createCell(0);
            evalCell.setCellValue(depReviewData.getEvaluatTarget());

            int dataCol = 1; // 数据列索引

            if (hasRolePosit) {
                // 角色定位
                Cell roleCell = dataRow.createCell(dataCol);
                String posit = depReviewData.getRolePosit();
                if (!StringUtils.isEmpty(posit)) {
                    switch (posit) {
                        case "1":
                            roleCell.setCellValue("领跑者");
                            break;
                        case "2":
                            roleCell.setCellValue("开拓者");
                            break;
                        case "3":
                            roleCell.setCellValue("奋进者");
                            break;
                        case "4":
                            roleCell.setCellValue("奋进者");
                            break;
                        default:
                            roleCell.setCellValue("领跑者");
                            break;
                    }
                }

                dataCol++;
            }

            // 处理定量评价数据 + 小计
            if (!busDepRevieStr.isEmpty()) {
                dataCol = processDataRowWithSubtotalFromObject(dataRow, jsonArrayData, busDepRevieStr, dataCol, depReviewData);
            }

            // 处理定性评价数据（无小计）
            if (!qualitativeHeaders.isEmpty()) {
                dataCol = processDataRowWithoutSubtotal(dataRow, jsonArrayData, qualitativeHeaders, dataCol);
            }

            // 处理加分数据 + 小计
            if (!bonusHeaders.isEmpty()) {
                dataCol = processDataRowWithSubtotalFromObject(dataRow, jsonArrayData, bonusHeaders, dataCol, depReviewData);
            }

            // 处理扣分数据（无小计）
            if (!deductHeaders.isEmpty()) {
                dataCol = processDataRowWithSubtotalFromObject(dataRow, jsonArrayData, deductHeaders, dataCol, depReviewData);
            }
            // 处理党建业务协作单元加分（从BusDepReviewData获取busUnitScore）
            if (partyUnitCols > 0) {
                Cell partyUnitScoreCell = dataRow.createCell(dataCol);
                if (depReviewData.getBusUnitScore() != null) {
                    partyUnitScoreCell.setCellValue(depReviewData.getBusUnitScore().doubleValue());
                } else {
                    partyUnitScoreCell.setCellValue(""); // 为空时显示空字符串
                }
                dataCol++;
            }

            // 设置业务评价得分（从BusDepReviewData获取，即使为空也显示空）
            Cell totalScoreCell = dataRow.createCell(dataCol);
            if (depReviewData.getReviewScore() != null) {
                totalScoreCell.setCellValue(depReviewData.getReviewScore().doubleValue());
            } else {
                totalScoreCell.setCellValue(""); // 为空时显示空字符串
            }
        }


        try {
            // 将Workbook写入响应输出流
            workbook.write(response.getOutputStream());
            System.out.println("Excel文件生成成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭资源
                workbook.close();
                response.getOutputStream().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理数据行（包含小计）- 直接从BusDepReviewData对象获取小计，不重新计算
     */
    private static int processDataRowWithSubtotalFromObject(Row dataRow, JSONArray jsonArrayData, List<BusDepReviewHeader> headers, int startCol, BusDepReviewData depReviewData) {
        if (headers.isEmpty()) return startCol;

        int currentCol = startCol;

        // 填充具体分数
        for (BusDepReviewHeader header : headers) {
            Cell cell = dataRow.createCell(currentCol);
            Double score = findScoreByHeadName(jsonArrayData, header.getHeadName());
            if (score != null) {
                cell.setCellValue(score);
            } else {
                cell.setCellValue("");
            }
            currentCol++;
        }

        // 设置小计 - 直接从BusDepReviewData对象获取，不重新计算
        Cell subtotalCell = dataRow.createCell(currentCol);
        Double subtotal = getSubtotalFromObject(headers, depReviewData);
        if (subtotal != null) {
            subtotalCell.setCellValue(subtotal);
        } else {
            subtotalCell.setCellValue(""); // 为空时显示空字符串
        }

        return currentCol + 1;
    }

    /**
     * 从BusDepReviewData对象获取小计
     */
    private static Double getSubtotalFromObject(List<BusDepReviewHeader> headers, BusDepReviewData depReviewData) {
        if (headers.isEmpty()) return null;

        // 判断是定量评价还是加分
        String headType = headers.get(0).getHeadType();

        if ("2".equals(headType)) {
            // 定量评价小计
            if (depReviewData.getSubtotalQuanScore() != null && !depReviewData.getSubtotalQuanScore().isEmpty()) {
                try {
                    if (StringUtils.isEmpty(depReviewData.getSubtotalQuanScore())) {
                        return null;
                    } else {
                        return Double.valueOf(depReviewData.getSubtotalQuanScore());
                    }
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        } else if ("4".equals(headType)) {
            // 加分小计
            if (depReviewData.getBonusSubtotal() != null && !depReviewData.getBonusSubtotal().isEmpty()) {
                try {
                    if (StringUtils.isEmpty(depReviewData.getBonusSubtotal())) {
                        return null;
                    } else {
                        return Double.valueOf(depReviewData.getBonusSubtotal());
                    }
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        } else if ("5".equals(headType)) {
            // 加分小计
            if (depReviewData.getBonusSubtotal() != null && !depReviewData.getBonusSubtotal().isEmpty()) {
                try {
                    if (StringUtils.isEmpty(depReviewData.getDeductPoints())) {
                        return null;
                    } else {
                        return Double.valueOf(depReviewData.getDeductPoints());
                    }
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        }

        return null;
    }

    /**
     * 处理数据行（不包含小计）
     */
    private static int processDataRowWithoutSubtotal(Row dataRow, JSONArray jsonArrayData, List<BusDepReviewHeader> headers, int startCol) {
        if (headers.isEmpty()) return startCol;

        int currentCol = startCol;

        // 填充具体分数
        for (BusDepReviewHeader header : headers) {
            Cell cell = dataRow.createCell(currentCol);
            Double score = findScoreByHeadName(jsonArrayData, header.getHeadName());
            if (score != null) {
                cell.setCellValue(score);
            } else {
                cell.setCellValue("");
            }
            currentCol++;
        }

        return currentCol;
    }

    /**
     * 根据指标名称查找分数
     */
    private static Double findScoreByHeadName(JSONArray jsonArrayData, String headName) {
        if (jsonArrayData == null || jsonArrayData.isEmpty()) return null;
        for (int i = 0; i < jsonArrayData.size(); i++) {
            JSONObject jsonObject = jsonArrayData.getJSONObject(i);
            if (jsonObject != null && headName.equals(jsonObject.getString("headName"))) {
                return jsonObject.getDouble("headScore");
            }
        }
        return null;
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
        if (CollectionUtils.isEmpty(busDepReviewDatas)) {
            return AjaxResult.error("新增数据不能为空");
        }
        BusDepReviewData first = busDepReviewDatas.get(0);
        if (first == null || StringUtils.isEmpty(first.getBusDepReviewId())) {
            return AjaxResult.error("评分表ID不能为空");
        }
        for (BusDepReviewData busDepReviewData : busDepReviewDatas) {
            if (busDepReviewData == null || StringUtils.isEmpty(busDepReviewData.getEvaluatTarget())) {
                return AjaxResult.error("评价对象不能为空");
            }
            if (!first.getBusDepReviewId().equals(busDepReviewData.getBusDepReviewId())) {
                return AjaxResult.error("评分表ID不一致");
            }
        }
        int rows = busDepReviewDataService.replaceReviewDataForCurrentUser(first.getBusDepReviewId(), busDepReviewDatas);
        return toAjax(rows);
    }

    /**
     * 修改评分数据
     */
    //  @PreAuthorize("@ss.hasPermi('system:DATA:edit')")
    @Log(title = "评分数据", businessType = BusinessType.UPDATE)
    @PutMapping("/saveBusDepReviewData")
    public AjaxResult saveBusDepReviewData(@RequestBody BusDepReviewData busDepReviewData) {
        if (StringUtils.isEmpty(busDepReviewData.getEvaluatTarget())) {
            return AjaxResult.error("评价对象不能为空");
        }
        return toAjax(busDepReviewDataService.saveBusDepReviewData(busDepReviewData));
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
     * 提交指定评分表的评分数据
     */
    @Log(title = "评分数据", businessType = BusinessType.UPDATE)
    @GetMapping("/submit/{reviewId}")
    public AjaxResult submit(@PathVariable("reviewId") String reviewId) {
        if (StringUtils.isEmpty(reviewId)) {
            return AjaxResult.error("评分表ID不能为空");
        }
        return toAjax(busDepReviewDataService.submitReviewData(reviewId));
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
