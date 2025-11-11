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
import com.cb.worksituation.service.IBusDepReviewService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
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
        writeToExcel(busDepReview, list, response);
        // 设置响应头
    }

    public static void writeToExcel(BusDepReview busDepReview, List<BusDepReviewData> busDepReviewDatas, HttpServletResponse response) throws UnsupportedEncodingException {
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
        String dataJson = busDepReviewData.getDataJson();
        JSONArray jsonArray = JSONArray.parseArray(dataJson);

        // 解析数据
        List<BusDepReviewHeader> busDepReviewHeaders = JSONArray.parseArray(dataJson, BusDepReviewHeader.class);

        List<BusDepReviewHeader> busDepRevieStr = busDepReviewHeaders.stream().filter(busDepReviewHeader -> busDepReviewHeader.getHeadType() !=null && busDepReviewHeader.getHeadType().equals("2")).collect(Collectors.toList());

        List<BusDepReviewHeader> qualitativeHeaders = busDepReviewHeaders.stream().filter(busDepReviewHeader -> busDepReviewHeader.getHeadType() !=null &&  busDepReviewHeader.getHeadType().equals("3")).collect(Collectors.toList());

        List<BusDepReviewHeader> bonusHeaders = busDepReviewHeaders.stream().filter(busDepReviewHeader -> busDepReviewHeader.getHeadType() !=null &&  busDepReviewHeader.getHeadType().equals("4")).collect(Collectors.toList());

        List<BusDepReviewHeader> deductHeaders = busDepReviewHeaders.stream().filter(busDepReviewHeader ->busDepReviewHeader.getHeadType() !=null &&  busDepReviewHeader.getHeadType().equals("5")).collect(Collectors.toList());

        List<BusDepReviewHeader> partyUnitHeaders = busDepReviewHeaders.stream().filter(busDepReviewHeader ->busDepReviewHeader.getHeadType() !=null &&  busDepReviewHeader.getHeadType().equals("6")).collect(Collectors.toList());

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

            // 合并两个扣分单元格（第1-2行）
            sheet.addMergedRegion(new CellRangeAddress(1, 2, currentCol, currentCol));
            currentCol++;
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
            // 扣分不显示小计列
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
                dataCol = processDataRowWithoutSubtotal(dataRow, jsonArrayData, deductHeaders, dataCol);
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
                    return Double.parseDouble(depReviewData.getSubtotalQuanScore());
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        } else if ("4".equals(headType)) {
            // 加分小计
            if (depReviewData.getBonusSubtotal() != null && !depReviewData.getBonusSubtotal().isEmpty()) {
                try {
                    return Double.parseDouble(depReviewData.getBonusSubtotal());
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

    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "[\n" + "            {\n" + "                \"searchValue\": null,\n" + "                \"createBy\": null,\n" + "                \"createTime\": null,\n" + "                \"updateBy\": \"admin\",\n" + "                \"updateTime\": \"2025-11-06 17:01:17\",\n" + "                \"remark\": null,\n" + "                \"params\": {},\n" + "                \"id\": \"41efcfbf-8362-467e-a5ff-9a307788\",\n" + "                \"busDepReviewId\": \"802f058d-1198-45a5-b845-eb98824f\",\n" + "                \"evaluatTarget\": \"盘龙区纪委监委\",\n" + "                \"rolePosit\": \"1\",\n" + "                \"dataJson\": \"[{\\\"headName\\\":\\\"问题线索处置及案件查办\\\",\\\"multDeptScore\\\":2,\\\"instructions\\\":\\\"\\\",\\\"signFilePath\\\":\\\"/profile/upload/2025/11/06/f6fbe997-bde1-44d8-a2ad-73e0f80bf98d.pdf\\\",\\\"headType\\\":\\\"2\\\",\\\"headScore\\\":3,\\\"signAttachId\\\":\\\"183df597-d5a8-4345-b75d-f1fd59b74f6a\\\",\\\"busDepExplId\\\":\\\"903e0425-5439-468b-95f2-929602ad\\\",\\\"busDepExpl\\\":{\\\"score\\\":3,\\\"createBy\\\":\\\"admin\\\",\\\"createTime\\\":\\\"2025-10-27 16:09:26\\\",\\\"divisionDept\\\":\\\"1\\\",\\\"id\\\":\\\"903e0425-5439-468b-95f2-929602ad\\\",\\\"params\\\":{},\\\"firstLevel\\\":\\\"问题线索处置及案件查办\\\",\\\"twoLevel\\\":\\\"问题线索处置及案件查办\\\",\\\"evaluationDept\\\":\\\"案件监督管理室\\\"},\\\"headCode\\\":\\\"ss\\\",\\\"busDepReviewId\\\":\\\"802f058d-1198-45a5-b845-eb98824f\\\",\\\"id\\\":\\\"413facd3-4564-499b-96c2-92b5335d\\\"},{\\\"headName\\\":\\\"监督检查工作\\\",\\\"multDeptScore\\\":2,\\\"instructions\\\":\\\"\\\",\\\"signFilePath\\\":\\\"/profile/upload/2025/11/06/fb9a8666-f091-4150-93ce-cb00e3ee005f.pdf\\\",\\\"headType\\\":\\\"2\\\",\\\"headScore\\\":4,\\\"signAttachId\\\":\\\"b142d39c-178f-4883-8d8b-334fd631807e\\\",\\\"busDepExplId\\\":\\\"9e5dc88a-200d-4212-aa7e-ebc87327\\\",\\\"busDepExpl\\\":{\\\"score\\\":4,\\\"createBy\\\":\\\"admin\\\",\\\"createTime\\\":\\\"2025-10-27 16:09:54\\\",\\\"divisionDept\\\":\\\"1\\\",\\\"id\\\":\\\"9e5dc88a-200d-4212-aa7e-ebc87327\\\",\\\"params\\\":{},\\\"firstLevel\\\":\\\"监督检查工作\\\",\\\"twoLevel\\\":\\\"\\\",\\\"evaluationDept\\\":\\\"党风室\\\"},\\\"headCode\\\":\\\"sdsds\\\",\\\"busDepReviewId\\\":\\\"802f058d-1198-45a5-b845-eb98824f\\\",\\\"id\\\":\\\"cba3f061-9367-4fd2-a634-be19c384\\\"},{\\\"headName\\\":\\\"信访举报工作\\\",\\\"multDeptScore\\\":2,\\\"instructions\\\":\\\"\\\",\\\"signFilePath\\\":\\\"/profile/upload/2025/11/06/7f94925c-07c7-4619-890c-bd5106905259.pdf\\\",\\\"headType\\\":\\\"2\\\",\\\"headScore\\\":4,\\\"signAttachId\\\":\\\"30b6e88b-178c-40b9-a691-723f64e57f73\\\",\\\"busDepExplId\\\":\\\"9e5dc88a-200d-4212-aa7e-ebc87327\\\",\\\"busDepExpl\\\":{\\\"score\\\":4,\\\"createBy\\\":\\\"admin\\\",\\\"createTime\\\":\\\"2025-10-27 16:09:54\\\",\\\"divisionDept\\\":\\\"1\\\",\\\"id\\\":\\\"9e5dc88a-200d-4212-aa7e-ebc87327\\\",\\\"params\\\":{},\\\"firstLevel\\\":\\\"监督检查工作\\\",\\\"twoLevel\\\":\\\"\\\",\\\"evaluationDept\\\":\\\"党风室\\\"},\\\"headCode\\\":\\\"dsss\\\",\\\"busDepReviewId\\\":\\\"802f058d-1198-45a5-b845-eb98824f\\\",\\\"id\\\":\\\"ad3a7998-ceab-4c72-a51c-d05c14fa\\\"},{\\\"headName\\\":\\\"案件定性和质量\\\",\\\"multDeptScore\\\":2,\\\"instructions\\\":\\\"\\\",\\\"signFilePath\\\":\\\"/profile/upload/2025/11/06/99e16ff4-304e-4dcd-9ee1-43ef667b5471.pdf\\\",\\\"headType\\\":\\\"2\\\",\\\"headScore\\\":5,\\\"signAttachId\\\":\\\"2b8598c0-88e9-4e96-996d-84f519a1dec5\\\",\\\"busDepExplId\\\":\\\"2631b9e9-913f-45ca-b2f3-3359ef5f\\\",\\\"busDepExpl\\\":{\\\"score\\\":0,\\\"createBy\\\":\\\"admin\\\",\\\"createTime\\\":\\\"2025-10-27 16:10:11\\\",\\\"divisionDept\\\":\\\"1\\\",\\\"id\\\":\\\"2631b9e9-913f-45ca-b2f3-3359ef5f\\\",\\\"params\\\":{},\\\"firstLevel\\\":\\\"信访举报工作\\\",\\\"twoLevel\\\":\\\"\\\",\\\"evaluationDept\\\":\\\"信访室\\\"},\\\"headCode\\\":\\\"sss\\\",\\\"busDepReviewId\\\":\\\"802f058d-1198-45a5-b845-eb98824f\\\",\\\"id\\\":\\\"534ec510-c59b-4543-b448-a88f97c2\\\"},{\\\"headName\\\":\\\"定性评价得分\\\",\\\"multDeptScore\\\":2,\\\"instructions\\\":\\\"\\\",\\\"signFilePath\\\":\\\"/profile/upload/2025/11/06/7e280142-da88-490d-9583-878c9a1416fb.pdf\\\",\\\"headType\\\":\\\"3\\\",\\\"headScore\\\":5,\\\"signAttachId\\\":\\\"c7bb03cd-ea8c-459b-82bb-b278172b0fe3\\\",\\\"busDepExplId\\\":\\\"ce5902e5-ed73-4bce-ac3e-fa5c51b4\\\",\\\"busDepExpl\\\":{\\\"score\\\":0,\\\"createBy\\\":\\\"admin\\\",\\\"createTime\\\":\\\"2025-10-27 16:12:15\\\",\\\"divisionDept\\\":\\\"1\\\",\\\"id\\\":\\\"ce5902e5-ed73-4bce-ac3e-fa5c51b4\\\",\\\"params\\\":{},\\\"firstLevel\\\":\\\"定性评价得分（30分）\\\",\\\"twoLevel\\\":\\\"\\\",\\\"evaluationDept\\\":\\\"办公室\\\"},\\\"headCode\\\":\\\"qualitative_evaluation_score\\\",\\\"busDepReviewId\\\":\\\"802f058d-1198-45a5-b845-eb98824f\\\",\\\"id\\\":\\\"55a6defe-8f94-420d-b753-df1515dc\\\"},{\\\"headName\\\":\\\"经验做法推广\\\",\\\"multDeptScore\\\":2,\\\"instructions\\\":\\\"\\\",\\\"signFilePath\\\":\\\"/profile/upload/2025/11/06/14874662-58a8-40b4-b79c-22767280b1d7.pdf\\\",\\\"headType\\\":\\\"4\\\",\\\"headScore\\\":6,\\\"signAttachId\\\":\\\"17f94056-f393-46ca-b508-d399807da408\\\",\\\"busDepExplId\\\":\\\"f63924a7-21f3-48c5-ad82-f7cf8dfa\\\",\\\"busDepExpl\\\":{\\\"score\\\":0,\\\"createBy\\\":\\\"admin\\\",\\\"createTime\\\":\\\"2025-10-27 16:12:28\\\",\\\"divisionDept\\\":\\\"1\\\",\\\"id\\\":\\\"f63924a7-21f3-48c5-ad82-f7cf8dfa\\\",\\\"params\\\":{},\\\"firstLevel\\\":\\\"经验做法推广\\\",\\\"twoLevel\\\":\\\"\\\",\\\"evaluationDept\\\":\\\"办公室\\\"},\\\"headCode\\\":\\\"sdsdsd\\\",\\\"busDepReviewId\\\":\\\"802f058d-1198-45a5-b845-eb98824f\\\",\\\"id\\\":\\\"fbdf11de-44d3-40cb-bc14-b4b59e26\\\"},{\\\"instructions\\\":\\\"集中测专题测shuom\\\",\\\"daadList\\\":[{\\\"instructions\\\":\\\"集中测专题测shuom\\\",\\\"deptName\\\":\\\"中共昆明市纪委昆明市监委\\\",\\\"headType\\\":\\\"4\\\",\\\"busDepExplId\\\":\\\"fb981eea-2f14-4510-aa0f-ce5b3ebd\\\",\\\"busDepExpl\\\":{\\\"score\\\":0,\\\"createBy\\\":\\\"admin\\\",\\\"createTime\\\":\\\"2025-10-27 16:17:36\\\",\\\"divisionDept\\\":\\\"1\\\",\\\"id\\\":\\\"fb981eea-2f14-4510-aa0f-ce5b3ebd\\\",\\\"params\\\":{},\\\"firstLevel\\\":\\\"集中测专题测\\\",\\\"twoLevel\\\":\\\"\\\",\\\"evaluationDept\\\":\\\"法规室,案审室\\\"},\\\"busDepReviewId\\\":\\\"802f058d-1198-45a5-b845-eb98824f\\\",\\\"headName\\\":\\\"集中测专题测\\\",\\\"multDeptScore\\\":1,\\\"signFilePath\\\":\\\"/profile/upload/2025/11/06/0394a3a5-514f-4325-8e04-0ceed82ac00c.pdf\\\",\\\"headScore\\\":5,\\\"signAttachId\\\":\\\"13384071-0d3c-487b-8f23-8a66da333e64\\\",\\\"headCode\\\":\\\"daad\\\",\\\"id\\\":\\\"f1ff0b7e-1ab4-416d-9da9-3edc9095\\\"}],\\\"headType\\\":\\\"4\\\",\\\"busDepExplId\\\":\\\"fb981eea-2f14-4510-aa0f-ce5b3ebd\\\",\\\"busDepExpl\\\":{\\\"score\\\":0,\\\"createBy\\\":\\\"admin\\\",\\\"createTime\\\":\\\"2025-10-27 16:17:36\\\",\\\"divisionDept\\\":\\\"1\\\",\\\"id\\\":\\\"fb981eea-2f14-4510-aa0f-ce5b3ebd\\\",\\\"params\\\":{},\\\"firstLevel\\\":\\\"集中测专题测\\\",\\\"twoLevel\\\":\\\"\\\",\\\"evaluationDept\\\":\\\"法规室,案审室\\\"},\\\"busDepReviewId\\\":\\\"802f058d-1198-45a5-b845-eb98824f\\\",\\\"headName\\\":\\\"集中测专题测\\\",\\\"multDeptScore\\\":1,\\\"signFilePath\\\":\\\"/profile/upload/2025/11/06/0394a3a5-514f-4325-8e04-0ceed82ac00c.pdf\\\",\\\"headScore\\\":5,\\\"signAttachId\\\":\\\"13384071-0d3c-487b-8f23-8a66da333e64\\\",\\\"headCode\\\":\\\"daad\\\",\\\"id\\\":\\\"f1ff0b7e-1ab4-416d-9da9-3edc9095\\\"},{\\\"instructions\\\":\\\"信息技术比赛sdsds\\\",\\\"headType\\\":\\\"4\\\",\\\"dsdsdsList\\\":[{\\\"instructions\\\":\\\"信息技术比赛sdsds\\\",\\\"deptName\\\":\\\"中共昆明市纪委昆明市监委\\\",\\\"headType\\\":\\\"4\\\",\\\"busDepExplId\\\":\\\"cfc4d70f-54d1-4b26-a3e1-cd081249\\\",\\\"busDepExpl\\\":{\\\"score\\\":0,\\\"createBy\\\":\\\"admin\\\",\\\"createTime\\\":\\\"2025-10-27 16:18:13\\\",\\\"updateBy\\\":\\\"dyd\\\",\\\"divisionDept\\\":\\\"1\\\",\\\"updateTime\\\":\\\"2025-11-03 15:05:01\\\",\\\"id\\\":\\\"cfc4d70f-54d1-4b26-a3e1-cd081249\\\",\\\"params\\\":{},\\\"firstLevel\\\":\\\"信息技术比赛\\\",\\\"twoLevel\\\":\\\"\\\",\\\"evaluationDept\\\":\\\"信保室,数字室,宣传部\\\"},\\\"busDepReviewId\\\":\\\"802f058d-1198-45a5-b845-eb98824f\\\",\\\"headName\\\":\\\"信息技术比赛\\\",\\\"multDeptScore\\\":1,\\\"signFilePath\\\":\\\"/profile/upload/2025/11/06/feee942b-a334-44c0-a26c-2f88595ba02b.pdf\\\",\\\"headScore\\\":6.1,\\\"signAttachId\\\":\\\"70e9d6db-1082-4289-b815-be27dd6bbe34\\\",\\\"headCode\\\":\\\"dsdsds\\\",\\\"id\\\":\\\"714f8f51-fcb2-4f2e-9521-580ed062\\\"}],\\\"busDepExplId\\\":\\\"cfc4d70f-54d1-4b26-a3e1-cd081249\\\",\\\"busDepExpl\\\":{\\\"score\\\":0,\\\"createBy\\\":\\\"admin\\\",\\\"createTime\\\":\\\"2025-10-27 16:18:13\\\",\\\"updateBy\\\":\\\"dyd\\\",\\\"divisionDept\\\":\\\"1\\\",\\\"updateTime\\\":\\\"2025-11-03 15:05:01\\\",\\\"id\\\":\\\"cfc4d70f-54d1-4b26-a3e1-cd081249\\\",\\\"params\\\":{},\\\"firstLevel\\\":\\\"信息技术比赛\\\",\\\"twoLevel\\\":\\\"\\\",\\\"evaluationDept\\\":\\\"信保室,数字室,宣传部\\\"},\\\"busDepReviewId\\\":\\\"802f058d-1198-45a5-b845-eb98824f\\\",\\\"headName\\\":\\\"信息技术比赛\\\",\\\"multDeptScore\\\":1,\\\"signFilePath\\\":\\\"/profile/upload/2025/11/06/feee942b-a334-44c0-a26c-2f88595ba02b.pdf\\\",\\\"headScore\\\":6.1,\\\"signAttachId\\\":\\\"70e9d6db-1082-4289-b815-be27dd6bbe34\\\",\\\"headCode\\\":\\\"dsdsds\\\",\\\"id\\\":\\\"714f8f51-fcb2-4f2e-9521-580ed062\\\"},{\\\"instructions\\\":\\\"\\\",\\\"headType\\\":\\\"5\\\",\\\"busDepExplId\\\":\\\"48ee5c8a-3072-4f3f-8d9d-3c6f4154\\\",\\\"busDepExpl\\\":{\\\"score\\\":0,\\\"createBy\\\":\\\"admin\\\",\\\"createTime\\\":\\\"2025-10-27 16:55:16\\\",\\\"divisionDept\\\":\\\"1\\\",\\\"id\\\":\\\"48ee5c8a-3072-4f3f-8d9d-3c6f4154\\\",\\\"params\\\":{},\\\"firstLevel\\\":\\\"扣分\\\",\\\"twoLevel\\\":\\\"\\\",\\\"evaluationDept\\\":\\\"办公室,法规室,信访室,干监室\\\"},\\\"busDepReviewId\\\":\\\"802f058d-1198-45a5-b845-eb98824f\\\",\\\"headName\\\":\\\"扣分\\\",\\\"multDeptScore\\\":1,\\\"signFilePath\\\":\\\"/profile/upload/2025/11/06/f0b68400-453a-4799-9509-497e9ed633e8.pdf\\\",\\\"headScore\\\":6.2,\\\"signAttachId\\\":\\\"f1c0aac5-ec0a-4328-9c3e-3087071801d2\\\",\\\"deduct_pointsList\\\":[{\\\"instructions\\\":\\\"\\\",\\\"deptName\\\":\\\"中共昆明市纪委昆明市监委\\\",\\\"headType\\\":\\\"5\\\",\\\"busDepExplId\\\":\\\"48ee5c8a-3072-4f3f-8d9d-3c6f4154\\\",\\\"busDepExpl\\\":{\\\"score\\\":0,\\\"createBy\\\":\\\"admin\\\",\\\"createTime\\\":\\\"2025-10-27 16:55:16\\\",\\\"divisionDept\\\":\\\"1\\\",\\\"id\\\":\\\"48ee5c8a-3072-4f3f-8d9d-3c6f4154\\\",\\\"params\\\":{},\\\"firstLevel\\\":\\\"扣分\\\",\\\"twoLevel\\\":\\\"\\\",\\\"evaluationDept\\\":\\\"办公室,法规室,信访室,干监室\\\"},\\\"busDepReviewId\\\":\\\"802f058d-1198-45a5-b845-eb98824f\\\",\\\"headName\\\":\\\"扣分\\\",\\\"multDeptScore\\\":1,\\\"signFilePath\\\":\\\"/profile/upload/2025/11/06/f0b68400-453a-4799-9509-497e9ed633e8.pdf\\\",\\\"headScore\\\":6.2,\\\"signAttachId\\\":\\\"f1c0aac5-ec0a-4328-9c3e-3087071801d2\\\",\\\"headCode\\\":\\\"deduct_points\\\",\\\"id\\\":\\\"4629a7f1-dd8e-47cc-bff6-196075b9\\\"}],\\\"headCode\\\":\\\"deduct_points\\\",\\\"id\\\":\\\"4629a7f1-dd8e-47cc-bff6-196075b9\\\"}]\",\n" + "                \"reviewScore\": null,\n" + "                \"busUnitScore\": null,\n" + "                \"dataStatus\": \"2\",\n" + "                \"filePath\": null,\n" + "                \"attachId\": null\n" + "            },\n" + "            {\n" + "                \"searchValue\": null,\n" + "                \"createBy\": null,\n" + "                \"createTime\": null,\n" + "                \"updateBy\": \"admin\",\n" + "                \"updateTime\": \"2025-11-06 17:43:16\",\n" + "                \"remark\": null,\n" + "                \"params\": {},\n" + "                \"id\": \"4c77ef24-10c8-4206-9d45-8214bd92\",\n" + "                \"busDepReviewId\": \"802f058d-1198-45a5-b845-eb98824f\",\n" + "                \"evaluatTarget\": \"五华区纪委监委\",\n" + "                \"rolePosit\": \"1\",\n" + "                \"dataJson\": \"[{\\\"headName\\\":\\\"问题线索处置及案件查办\\\",\\\"multDeptScore\\\":2,\\\"instructions\\\":\\\"\\\",\\\"signFilePath\\\":\\\"/profile/upload/2025/11/06/eb8cce45-9b5e-4538-bb8c-b6b9c5ecec5b.pdf\\\",\\\"headType\\\":\\\"2\\\",\\\"headScore\\\":3,\\\"signAttachId\\\":\\\"d6a43c1b-e912-4292-87c2-ae17de8ceb0c\\\",\\\"busDepExplId\\\":\\\"903e0425-5439-468b-95f2-929602ad\\\",\\\"busDepExpl\\\":{\\\"score\\\":3,\\\"createBy\\\":\\\"admin\\\",\\\"createTime\\\":\\\"2025-10-27 16:09:26\\\",\\\"divisionDept\\\":\\\"1\\\",\\\"id\\\":\\\"903e0425-5439-468b-95f2-929602ad\\\",\\\"params\\\":{},\\\"firstLevel\\\":\\\"问题线索处置及案件查办\\\",\\\"twoLevel\\\":\\\"问题线索处置及案件查办\\\",\\\"evaluationDept\\\":\\\"案件监督管理室\\\"},\\\"headCode\\\":\\\"ss\\\",\\\"busDepReviewId\\\":\\\"802f058d-1198-45a5-b845-eb98824f\\\",\\\"id\\\":\\\"413facd3-4564-499b-96c2-92b5335d\\\"},{\\\"headName\\\":\\\"监督检查工作\\\",\\\"multDeptScore\\\":2,\\\"instructions\\\":\\\"\\\",\\\"signFilePath\\\":\\\"/profile/upload/2025/11/06/21e56adf-1733-45d9-9882-e17a2e8b7d23.pdf\\\",\\\"headType\\\":\\\"2\\\",\\\"headScore\\\":4,\\\"signAttachId\\\":\\\"0324979d-3879-4d8c-ae68-e6f493f43eef\\\",\\\"busDepExplId\\\":\\\"9e5dc88a-200d-4212-aa7e-ebc87327\\\",\\\"busDepExpl\\\":{\\\"score\\\":4,\\\"createBy\\\":\\\"admin\\\",\\\"createTime\\\":\\\"2025-10-27 16:09:54\\\",\\\"divisionDept\\\":\\\"1\\\",\\\"id\\\":\\\"9e5dc88a-200d-4212-aa7e-ebc87327\\\",\\\"params\\\":{},\\\"firstLevel\\\":\\\"监督检查工作\\\",\\\"twoLevel\\\":\\\"\\\",\\\"evaluationDept\\\":\\\"党风室\\\"},\\\"headCode\\\":\\\"sdsds\\\",\\\"busDepReviewId\\\":\\\"802f058d-1198-45a5-b845-eb98824f\\\",\\\"id\\\":\\\"cba3f061-9367-4fd2-a634-be19c384\\\"},{\\\"headName\\\":\\\"信访举报工作\\\",\\\"multDeptScore\\\":2,\\\"instructions\\\":\\\"\\\",\\\"signFilePath\\\":\\\"/profile/upload/2025/11/06/5244abac-38a4-4753-809c-3adf21c77f01.pdf\\\",\\\"headType\\\":\\\"2\\\",\\\"headScore\\\":3.2,\\\"signAttachId\\\":\\\"c0e0280f-ba2c-446c-8e53-3376941e97d5\\\",\\\"busDepExplId\\\":\\\"9e5dc88a-200d-4212-aa7e-ebc87327\\\",\\\"busDepExpl\\\":{\\\"score\\\":4,\\\"createBy\\\":\\\"admin\\\",\\\"createTime\\\":\\\"2025-10-27 16:09:54\\\",\\\"divisionDept\\\":\\\"1\\\",\\\"id\\\":\\\"9e5dc88a-200d-4212-aa7e-ebc87327\\\",\\\"params\\\":{},\\\"firstLevel\\\":\\\"监督检查工作\\\",\\\"twoLevel\\\":\\\"\\\",\\\"evaluationDept\\\":\\\"党风室\\\"},\\\"headCode\\\":\\\"dsss\\\",\\\"busDepReviewId\\\":\\\"802f058d-1198-45a5-b845-eb98824f\\\",\\\"id\\\":\\\"ad3a7998-ceab-4c72-a51c-d05c14fa\\\"},{\\\"headName\\\":\\\"案件定性和质量\\\",\\\"multDeptScore\\\":2,\\\"instructions\\\":\\\"\\\",\\\"signFilePath\\\":\\\"/profile/upload/2025/11/06/5f1b06a6-36f0-4306-a713-d048fe7d4251.pdf\\\",\\\"headType\\\":\\\"2\\\",\\\"headScore\\\":3.2,\\\"signAttachId\\\":\\\"74d9cdbd-c0d8-4213-b4e7-83b9c76347bf\\\",\\\"busDepExplId\\\":\\\"2631b9e9-913f-45ca-b2f3-3359ef5f\\\",\\\"busDepExpl\\\":{\\\"score\\\":0,\\\"createBy\\\":\\\"admin\\\",\\\"createTime\\\":\\\"2025-10-27 16:10:11\\\",\\\"divisionDept\\\":\\\"1\\\",\\\"id\\\":\\\"2631b9e9-913f-45ca-b2f3-3359ef5f\\\",\\\"params\\\":{},\\\"firstLevel\\\":\\\"信访举报工作\\\",\\\"twoLevel\\\":\\\"\\\",\\\"evaluationDept\\\":\\\"信访室\\\"},\\\"headCode\\\":\\\"sss\\\",\\\"busDepReviewId\\\":\\\"802f058d-1198-45a5-b845-eb98824f\\\",\\\"id\\\":\\\"534ec510-c59b-4543-b448-a88f97c2\\\"},{\\\"headName\\\":\\\"定性评价得分\\\",\\\"multDeptScore\\\":2,\\\"instructions\\\":\\\"\\\",\\\"signFilePath\\\":\\\"/profile/upload/2025/11/06/4328f473-5ea0-40e6-b43d-d3a4637aea4e.pdf\\\",\\\"headType\\\":\\\"3\\\",\\\"headScore\\\":3.2,\\\"signAttachId\\\":\\\"d199e230-7b6a-4cbb-b355-96eaddb3f12e\\\",\\\"busDepExplId\\\":\\\"ce5902e5-ed73-4bce-ac3e-fa5c51b4\\\",\\\"busDepExpl\\\":{\\\"score\\\":0,\\\"createBy\\\":\\\"admin\\\",\\\"createTime\\\":\\\"2025-10-27 16:12:15\\\",\\\"divisionDept\\\":\\\"1\\\",\\\"id\\\":\\\"ce5902e5-ed73-4bce-ac3e-fa5c51b4\\\",\\\"params\\\":{},\\\"firstLevel\\\":\\\"定性评价得分（30分）\\\",\\\"twoLevel\\\":\\\"\\\",\\\"evaluationDept\\\":\\\"办公室\\\"},\\\"headCode\\\":\\\"qualitative_evaluation_score\\\",\\\"busDepReviewId\\\":\\\"802f058d-1198-45a5-b845-eb98824f\\\",\\\"id\\\":\\\"55a6defe-8f94-420d-b753-df1515dc\\\"},{\\\"instructions\\\":\\\"\\\",\\\"headType\\\":\\\"4\\\",\\\"filePath\\\":\\\"/profile/upload/2025/11/06/45f3ff51-054f-4b79-bc0c-eef7356d4190.docx\\\",\\\"busDepExplId\\\":\\\"f63924a7-21f3-48c5-ad82-f7cf8dfa\\\",\\\"busDepExpl\\\":{\\\"score\\\":0,\\\"createBy\\\":\\\"admin\\\",\\\"createTime\\\":\\\"2025-10-27 16:12:28\\\",\\\"divisionDept\\\":\\\"1\\\",\\\"id\\\":\\\"f63924a7-21f3-48c5-ad82-f7cf8dfa\\\",\\\"params\\\":{},\\\"firstLevel\\\":\\\"经验做法推广\\\",\\\"twoLevel\\\":\\\"\\\",\\\"evaluationDept\\\":\\\"办公室\\\"},\\\"busDepReviewId\\\":\\\"802f058d-1198-45a5-b845-eb98824f\\\",\\\"headName\\\":\\\"经验做法推广\\\",\\\"multDeptScore\\\":2,\\\"signFilePath\\\":\\\"/profile/upload/2025/11/06/92da2e6b-dd4c-4850-9aa5-d16f5a560e1f.pdf\\\",\\\"headScore\\\":6,\\\"signAttachId\\\":\\\"ba4e895c-e56d-4ff3-9c98-25bb5603244e\\\",\\\"headCode\\\":\\\"sdsdsd\\\",\\\"id\\\":\\\"fbdf11de-44d3-40cb-bc14-b4b59e26\\\",\\\"attachId\\\":\\\"0cb551f0-8817-4bee-a88a-2d21ed7e257f\\\"},{\\\"instructions\\\":\\\"集中测专题测shuom\\\",\\\"daadList\\\":[{\\\"instructions\\\":\\\"集中测专题测shuom\\\",\\\"deptName\\\":\\\"中共昆明市纪委昆明市监委\\\",\\\"headType\\\":\\\"4\\\",\\\"busDepExplId\\\":\\\"fb981eea-2f14-4510-aa0f-ce5b3ebd\\\",\\\"busDepExpl\\\":{\\\"score\\\":0,\\\"createBy\\\":\\\"admin\\\",\\\"createTime\\\":\\\"2025-10-27 16:17:36\\\",\\\"divisionDept\\\":\\\"1\\\",\\\"id\\\":\\\"fb981eea-2f14-4510-aa0f-ce5b3ebd\\\",\\\"params\\\":{},\\\"firstLevel\\\":\\\"集中测专题测\\\",\\\"twoLevel\\\":\\\"\\\",\\\"evaluationDept\\\":\\\"法规室,案审室\\\"},\\\"busDepReviewId\\\":\\\"802f058d-1198-45a5-b845-eb98824f\\\",\\\"headName\\\":\\\"集中测专题测\\\",\\\"multDeptScore\\\":1,\\\"signFilePath\\\":\\\"/profile/upload/2025/11/06/91d28df1-b130-45da-a36e-51679624a601.pdf\\\",\\\"headScore\\\":3.2,\\\"signAttachId\\\":\\\"8255935b-b8fa-44ce-aace-f1e9ceb9152b\\\",\\\"headCode\\\":\\\"daad\\\",\\\"id\\\":\\\"f1ff0b7e-1ab4-416d-9da9-3edc9095\\\"}],\\\"headType\\\":\\\"4\\\",\\\"busDepExplId\\\":\\\"fb981eea-2f14-4510-aa0f-ce5b3ebd\\\",\\\"busDepExpl\\\":{\\\"params\\\":{},\\\"firstLevel\\\":\\\"集中测专题测\\\",\\\"twoLevel\\\":\\\"\\\",\\\"score\\\":0,\\\"createBy\\\":\\\"admin\\\",\\\"createTime\\\":\\\"2025-10-27 16:17:36\\\",\\\"divisionDept\\\":\\\"1\\\",\\\"id\\\":\\\"fb981eea-2f14-4510-aa0f-ce5b3ebd\\\",\\\"evaluationDept\\\":\\\"法规室,案审室\\\"},\\\"busDepReviewId\\\":\\\"802f058d-1198-45a5-b845-eb98824f\\\",\\\"headName\\\":\\\"集中测专题测\\\",\\\"multDeptScore\\\":1,\\\"signFilePath\\\":\\\"/profile/upload/2025/11/06/91d28df1-b130-45da-a36e-51679624a601.pdf\\\",\\\"headScore\\\":3.2,\\\"signAttachId\\\":\\\"8255935b-b8fa-44ce-aace-f1e9ceb9152b\\\",\\\"headCode\\\":\\\"daad\\\",\\\"id\\\":\\\"f1ff0b7e-1ab4-416d-9da9-3edc9095\\\"},{\\\"instructions\\\":\\\"信息技术比赛sdsds\\\",\\\"headType\\\":\\\"4\\\",\\\"dsdsdsList\\\":[{\\\"instructions\\\":\\\"信息技术比赛sdsds\\\",\\\"deptName\\\":\\\"中共昆明市纪委昆明市监委\\\",\\\"headType\\\":\\\"4\\\",\\\"busDepExplId\\\":\\\"cfc4d70f-54d1-4b26-a3e1-cd081249\\\",\\\"busDepExpl\\\":{\\\"score\\\":0,\\\"createBy\\\":\\\"admin\\\",\\\"createTime\\\":\\\"2025-10-27 16:18:13\\\",\\\"updateBy\\\":\\\"dyd\\\",\\\"divisionDept\\\":\\\"1\\\",\\\"updateTime\\\":\\\"2025-11-03 15:05:01\\\",\\\"id\\\":\\\"cfc4d70f-54d1-4b26-a3e1-cd081249\\\",\\\"params\\\":{},\\\"firstLevel\\\":\\\"信息技术比赛\\\",\\\"twoLevel\\\":\\\"\\\",\\\"evaluationDept\\\":\\\"信保室,数字室,宣传部\\\"},\\\"busDepReviewId\\\":\\\"802f058d-1198-45a5-b845-eb98824f\\\",\\\"headName\\\":\\\"信息技术比赛\\\",\\\"multDeptScore\\\":1,\\\"signFilePath\\\":\\\"/profile/upload/2025/11/06/6422468d-f033-409a-80c2-1ff4c8c4d8dc.pdf\\\",\\\"headScore\\\":3.2,\\\"signAttachId\\\":\\\"d3cfc6a6-34cc-446d-92a7-2e903bbdc3e0\\\",\\\"headCode\\\":\\\"dsdsds\\\",\\\"id\\\":\\\"714f8f51-fcb2-4f2e-9521-580ed062\\\"}],\\\"busDepExplId\\\":\\\"cfc4d70f-54d1-4b26-a3e1-cd081249\\\",\\\"busDepExpl\\\":{\\\"updateTime\\\":\\\"2025-11-03 15:05:01\\\",\\\"params\\\":{},\\\"firstLevel\\\":\\\"信息技术比赛\\\",\\\"twoLevel\\\":\\\"\\\",\\\"score\\\":0,\\\"createBy\\\":\\\"admin\\\",\\\"createTime\\\":\\\"2025-10-27 16:18:13\\\",\\\"updateBy\\\":\\\"dyd\\\",\\\"divisionDept\\\":\\\"1\\\",\\\"id\\\":\\\"cfc4d70f-54d1-4b26-a3e1-cd081249\\\",\\\"evaluationDept\\\":\\\"信保室,数字室,宣传部\\\"},\\\"busDepReviewId\\\":\\\"802f058d-1198-45a5-b845-eb98824f\\\",\\\"headName\\\":\\\"信息技术比赛\\\",\\\"multDeptScore\\\":1,\\\"signFilePath\\\":\\\"/profile/upload/2025/11/06/6422468d-f033-409a-80c2-1ff4c8c4d8dc.pdf\\\",\\\"headScore\\\":3.2,\\\"signAttachId\\\":\\\"d3cfc6a6-34cc-446d-92a7-2e903bbdc3e0\\\",\\\"headCode\\\":\\\"dsdsds\\\",\\\"id\\\":\\\"714f8f51-fcb2-4f2e-9521-580ed062\\\"},{\\\"instructions\\\":\\\"\\\",\\\"headType\\\":\\\"5\\\",\\\"busDepExplId\\\":\\\"48ee5c8a-3072-4f3f-8d9d-3c6f4154\\\",\\\"busDepExpl\\\":{\\\"params\\\":{},\\\"firstLevel\\\":\\\"扣分\\\",\\\"twoLevel\\\":\\\"\\\",\\\"score\\\":0,\\\"createBy\\\":\\\"admin\\\",\\\"createTime\\\":\\\"2025-10-27 16:55:16\\\",\\\"divisionDept\\\":\\\"1\\\",\\\"id\\\":\\\"48ee5c8a-3072-4f3f-8d9d-3c6f4154\\\",\\\"evaluationDept\\\":\\\"办公室,法规室,信访室,干监室\\\"},\\\"busDepReviewId\\\":\\\"802f058d-1198-45a5-b845-eb98824f\\\",\\\"headName\\\":\\\"扣分\\\",\\\"multDeptScore\\\":1,\\\"signFilePath\\\":\\\"/profile/upload/2025/11/06/ef3fae06-8139-405d-8535-129e95557ea1.pdf\\\",\\\"headScore\\\":3.2,\\\"signAttachId\\\":\\\"694783b1-1b54-4b1a-84dd-e432d1ff1c73\\\",\\\"deduct_pointsList\\\":[{\\\"instructions\\\":\\\"\\\",\\\"deptName\\\":\\\"中共昆明市纪委昆明市监委\\\",\\\"headType\\\":\\\"5\\\",\\\"busDepExplId\\\":\\\"48ee5c8a-3072-4f3f-8d9d-3c6f4154\\\",\\\"busDepExpl\\\":{\\\"score\\\":0,\\\"createBy\\\":\\\"admin\\\",\\\"createTime\\\":\\\"2025-10-27 16:55:16\\\",\\\"divisionDept\\\":\\\"1\\\",\\\"id\\\":\\\"48ee5c8a-3072-4f3f-8d9d-3c6f4154\\\",\\\"params\\\":{},\\\"firstLevel\\\":\\\"扣分\\\",\\\"twoLevel\\\":\\\"\\\",\\\"evaluationDept\\\":\\\"办公室,法规室,信访室,干监室\\\"},\\\"busDepReviewId\\\":\\\"802f058d-1198-45a5-b845-eb98824f\\\",\\\"headName\\\":\\\"扣分\\\",\\\"multDeptScore\\\":1,\\\"signFilePath\\\":\\\"/profile/upload/2025/11/06/ef3fae06-8139-405d-8535-129e95557ea1.pdf\\\",\\\"headScore\\\":3.2,\\\"signAttachId\\\":\\\"694783b1-1b54-4b1a-84dd-e432d1ff1c73\\\",\\\"headCode\\\":\\\"deduct_points\\\",\\\"id\\\":\\\"4629a7f1-dd8e-47cc-bff6-196075b9\\\"}],\\\"headCode\\\":\\\"deduct_points\\\",\\\"id\\\":\\\"4629a7f1-dd8e-47cc-bff6-196075b9\\\"}]\",\n" + "                \"reviewScore\": null,\n" + "                \"busUnitScore\": null,\n" + "                \"dataStatus\": \"1\",\n" + "                \"filePath\": null,\n" + "                \"attachId\": null\n" + "            }\n" + "        ]";

        String s1 = "{\n" + "            \"searchValue\": null,\n" + "            \"createBy\": \"admin\",\n" + "            \"createTime\": \"2025-10-11 14:56:29\",\n" + "            \"updateBy\": \"dyd\",\n" + "            \"updateTime\": \"2025-11-03 17:56:18\",\n" + "            \"remark\": null,\n" + "            \"params\": {},\n" + "            \"id\": \"802f058d-1198-45a5-b845-eb98824f\",\n" + "            \"busYear\": \"2025\",\n" + "            \"busName\": \"昆明市纪委监委机关2025年度业务评价评分汇总表县（市）区纪委监委\",\n" + "            \"divisionDept\": \"1\",\n" + "            \"evaluationTarget\": \"五华区纪委监委\",\n" + "            \"busStatus\": \"1\",\n" + "            \"remarks\": \"业务评价得分=定量评价小计+定性评价得分+加分小计-扣分\",\n" + "            \"dataStatus\": \"2\",\n" + "            \"busDepReviewHeaderList\": null,\n" + "            \"busDepReviewDataList\": null\n" + "        }";

        BusDepReview busDepReview = JSONObject.parseObject(s1, BusDepReview.class);
        List<BusDepReviewData> busDepReviewDatas = JSONArray.parseArray(s, BusDepReviewData.class);
        writeToExcel(busDepReview, busDepReviewDatas, null);

    }
}
