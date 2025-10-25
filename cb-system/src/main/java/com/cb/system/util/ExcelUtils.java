package com.cb.system.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Excel工具类
 */
public class ExcelUtils {
    protected static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);
    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";
    private static final String SPLIT = ".";

    // 获取Excel表信息
    public static Workbook getWorkbook(MultipartFile file) {
        Workbook workbook = null;
        try {
            // 获取Excel后缀名
            String fileName = file.getOriginalFilename();
            if (StringUtils.isEmpty(fileName) || fileName.lastIndexOf(SPLIT) < 0) {
                logger.warn("解析Excel失败，因为获取到的Excel文件名非法！");
                return null;
            }
            String fileType = fileName.substring(fileName.lastIndexOf(SPLIT) + 1);
            // 获取Excel工作簿
            if (fileType.equalsIgnoreCase(XLS)) {
                workbook = new HSSFWorkbook(file.getInputStream());
            } else if (fileType.equalsIgnoreCase(XLSX)) {
                workbook = new XSSFWorkbook(file.getInputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }

    // 获取Excel表头信息
    public static List<String> getSheetTitles(Workbook workbook) {
        // 拿第一个sheet表
        Sheet sheet = workbook.getSheetAt(0);
        // 校验sheet是否合法
        if (Objects.isNull(sheet)) {
            return null;
        }
        // 获取第一行数据（假如第一行就是列名）
        Row sheetTitleRow = sheet.getRow(sheet.getFirstRowNum());
        // 取出最后一列
        short lastCellNum = sheetTitleRow.getLastCellNum();
        List<String> sheetTitleList = new LinkedList<>();
        for (int i = 0; i < lastCellNum; i++) {
            // 取出每一列的名
            String cellValue = sheetTitleRow.getCell(i).getStringCellValue();
            sheetTitleList.add(cellValue);
        }
        return sheetTitleList;
    }

    public static String getCellValue(Row row, int cellIndex) {
        if (row != null && cellIndex >= 0) {
            Cell cell = row.getCell(cellIndex);
            if (Objects.isNull(cell) || Objects.isNull(cell.getCellType())) {
                return "";
            }
            CellType cellType = cell.getCellType();
            switch (cellType) {
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        return getCellDateString(cell);
                    } else {
                        return new DecimalFormat("0.####################").format(cell.getNumericCellValue());
                    }
                case STRING:
                    return cell.getRichStringCellValue().getString().trim();
                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                case BLANK:
                    return "";
                case ERROR:
                    return FormulaError.forInt(cell.getErrorCellValue()).getString();
                default:
                    return new DataFormatter().formatCellValue(cell);
            }
        }
        return "";

    }
    /**
     * 日期单元格解析（格式统一按照国人的习惯显示，即 yyyy-MM-dd HH:mm:ss）
     *
     * @param cell 单元格对象
     * @return 字符串时间
     */
    public static String getCellDateString(Cell cell) {
        String dataFormatString = cell.getCellStyle().getDataFormatString();
        Date date = cell.getDateCellValue();
        String formatStr;
        if (dataFormatString.contains("y") && dataFormatString.contains("d")) {
            if (dataFormatString.contains("h")) {
                formatStr = "yyyy-MM-dd HH:mm:ss";
            } else {
                formatStr = "yyyy-MM-dd";
            }
        } else {
            formatStr = dataFormatString;
        }
        return new SimpleDateFormat(formatStr).format(date);
    }

}