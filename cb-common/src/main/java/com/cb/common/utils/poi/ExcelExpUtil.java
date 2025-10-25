package com.cb.common.utils.poi;

import com.cb.common.annotation.Excel;
import com.cb.common.annotation.Excel.ColumnType;
import com.cb.common.annotation.Excel.Type;
import com.cb.common.annotation.Excels;
import com.cb.common.config.RuoYiConfig;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.text.Convert;
import com.cb.common.exception.CustomException;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.file.FileTypeUtils;
import com.cb.common.utils.file.ImageUtils;
import com.cb.common.utils.reflect.ReflectUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Excel导出 扩展类 目前支持冻结行、列；设置整行整列的不可编辑
 * @param <T>
 */
public class ExcelExpUtil<T> {

    private static final Logger log = LoggerFactory.getLogger(ExcelExpUtil.class);

    /**
     * Excel sheet最大行数，默认65536
     */
    public static final int sheetSize = 65536;
    /**
     * Excel 设置了保护后（不可编辑）的密码
     */
    public static final String PROJECT_PASSWORD = "123456";

    /**
     * 工作表名称
     */
    private String sheetName;

    /**
     * 导出类型（EXPORT:导出数据；IMPORT：导入模板）
     */
    private Type type;

    /**
     * 工作薄对象
     */
    private Workbook wb;

    /**
     * 工作表对象
     */
    private Sheet sheet;

    /**
     * 样式列表
     */
    private Map<String, CellStyle> styles;

    /**
     * 导入导出数据列表
     */
    private List<T> list;

    /**
     * 注解列表
     */
    private List<Object[]> fields;

    /**
     * 当前行号
     */
    private int rownum;

    /**
     * 标题
     */
    private String title;

    /**
     * 最大高度
     */
    private short maxHeight;

    /**
     * 统计列表
     */
    private Map<Integer, Double> statistics = new HashMap<Integer, Double>();

    /**
     * 数字格式
     */
    private static final DecimalFormat DOUBLE_FORMAT = new DecimalFormat("######0.00");

    //转换表达式
    private Map<String,String> expMap= new HashMap();

    /**
     * 实体对象
     */
    public Class<T> clazz;

    public ExcelExpUtil(Class<T> clazz) {
        this.clazz = clazz;
    }

    public void init(List<T> list, String sheetName, String title, Type type) {
        if (list == null) {
            list = new ArrayList<T>();
        }
        this.list = list;
        this.sheetName = sheetName;
        this.type = type;
        this.title = title;
        createExcelField();
        createWorkbook();
        createTitle();
    }

    public void init(List<T> list, String sheetName, String title, Type type, int colSplit, int rowSplit, int leftmostColumn, int topRow) {
        if (list == null) {
            list = new ArrayList<T>();
        }
        this.list = list;
        this.sheetName = sheetName;
        this.type = type;
        this.title = title;
        createExcelField();
        createWorkbook(colSplit, rowSplit, leftmostColumn, topRow);
        createTitle();
    }

    /**
     * 创建一个工作簿
     */
    public void createWorkbook(int colSplit, int rowSplit, int leftmostColumn, int topRow) {
        this.wb = new SXSSFWorkbook(500);
        this.sheet = wb.createSheet();
        this.sheet.createFreezePane(colSplit, rowSplit, leftmostColumn, topRow);
        wb.setSheetName(0, sheetName);
        this.styles = createStyles(wb);
    }

    /**
     * 对list数据源将其里面的数据导入到excel表单
     *
     * @param response 返回数据
     * @param list 导出数据集合
     * @param sheetName 工作表的名称
     * @param  colSplit 表示要冻结的列数，从0开始，这里的0表示不设置冻结。
     * @param  rowSplit 表示要冻结的行数，从0开始，这里的0表示不设置冻结。
     * @param leftmostColumn 表示在右侧窗格中可以看见的冻结列数，从1开始。
     * @param topRow 表示在底部窗格中可以看见的冻结行数，从1开始。
     * @return 结果
     * @throws IOException
     */
    public AjaxResult exportExcelSupportFreeze(HttpServletResponse response, List<T> list, String sheetName, int colSplit, int rowSplit, int leftmostColumn, int topRow) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        this.init(list, sheetName, title, Type.EXPORT, colSplit, rowSplit, leftmostColumn, topRow);
        return exportExcel(response.getOutputStream());
    }

    /**
     * 对list数据源将其里面的数据导入到excel表单,支持设置整行整列的不可编辑
     *
     * @param response 返回数据
     * @param list 导出数据集合
     * @param sheetName 工作表的名称
     * @param rowNums 行号的数组
     * @param columnNums 列号的数组
     * @return 结果
     * @throws IOException
     */
    public void exportExcelSupportLock(HttpServletResponse response, List<T> list, String sheetName, Integer[] rowNums, Integer[] columnNums) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        this.init(list, sheetName, title, Type.EXPORT);
        exportExcel(response.getOutputStream(), rowNums, columnNums);
    }

    /**
     * 对list数据源将其里面的数据导入到excel表单
     *
     * @return 结果
     */
    public void exportExcel(OutputStream out, Integer[] rowNums, Integer[] columnNums) {
        try {
            writeSheet(rowNums, columnNums);
            wb.write(out);
        } catch (Exception e) {
            log.error("导出Excel异常{}", e.getMessage());
        } finally {
            IOUtils.closeQuietly(wb);
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 创建写入数据到Sheet
     */
    public void writeSheet(Integer[] rowNums, Integer[] columnNums) {
        // 取出一共有多少个sheet.
        int sheetNo = Math.max(1, (int) Math.ceil(list.size() * 1.0 / sheetSize));
        for (int index = 0; index < sheetNo; index++) {
            createSheet(sheetNo, index);
            // 产生一行
            Row row = sheet.createRow(rownum);
            int column = 0;
            // 写入各个字段的列头名称
            for (Object[] os : fields) {
                Excel excel = (Excel) os[1];
                this.createCell(excel, row, column++);
            }
            if (Type.EXPORT.equals(type)) {
                fillExcelData(index, row, rowNums, columnNums);
                addStatisticsRow();
            }
            sheet.protectSheet(PROJECT_PASSWORD);
        }
    }

    /**
     * 填充excel数据
     *
     * @param index 序号
     * @param row 单元格行
     */
    public void fillExcelData(int index, Row row, Integer[] rowNums, Integer[] columnNums) {
        int startNo = index * sheetSize;
        int endNo = Math.min(startNo + sheetSize, list.size());
        for (int i = startNo; i < endNo; i++) {
            row = sheet.createRow(i + 1 + rownum - startNo);
            // 得到导出对象.
            T vo = (T) list.get(i);
            int column = 0;
            for (Object[] os : fields) {
                Field field = (Field) os[0];
                Excel excel = (Excel) os[1];
                this.addCell(excel, row, vo, field, column++, rowNums, columnNums);
            }
        }
    }

    /**
     * 添加单元格,支持整行整列的不可编辑
     */
    public Cell addCell(Excel attr, Row row, T vo, Field field, int column, Integer[] rowNums, Integer[] columnNums) {
        Cell cell = null;
        // 单元格样式锁定 不可编辑
        CellStyle lockStyle = this.wb.createCellStyle();
        lockStyle.setLocked(true);
        CellStyle unlockStyle = this.wb.createCellStyle();
        unlockStyle.setLocked(false);
        try {
            // 设置行高
            row.setHeight(maxHeight);
            // 根据Excel中设置情况决定是否导出,有些情况需要保持为空,希望用户填写这一列.
            if (attr.isExport()) {
                // 创建cell
                cell = row.createCell(column);
                /*int align = attr.align().value();
                cell.setCellStyle(styles.get("data" + (align >= 1 && align <= 3 ? align : "")));*/
                cell.setCellStyle(styles.get(StringUtils.format("data_{}_{}_{}", attr.align()/*, attr.color(), attr.backgroundColor()*/)));
                cell.setCellStyle(unlockStyle);  //   这里很重要 ，记得先把不需要保护的列设置为不保护
                if ((rowNums != null && Arrays.asList(rowNums).contains(row.getRowNum())) || (columnNums != null && Arrays.asList(columnNums).contains(column))) {
                    // 单元格设置为不可编辑
                    cell.setCellStyle(lockStyle);
                }
                // 用于读取对象中的属性
                Object value = getTargetValue(vo, field, attr);
                String dateFormat = attr.dateFormat();
                String readConverterExp = attr.readConverterExp();
                String separator = attr.separator();
                if (StringUtils.isNotEmpty(dateFormat) && StringUtils.isNotNull(value)) {
                    cell.setCellValue(DateUtils.parseDateToStr(dateFormat, (Date) value));
                } else if (StringUtils.isNotEmpty(readConverterExp) && StringUtils.isNotNull(value)) {
                    cell.setCellValue(convertByExp(Convert.toStr(value), readConverterExp, separator));
                } else if (value instanceof BigDecimal && -1 != attr.scale()) {
                    cell.setCellValue((((BigDecimal) value).setScale(attr.scale(), attr.roundingMode())).toString());
                } else if (!attr.handler().equals(ExcelHandlerAdapter.class)) {
                    cell.setCellValue(dataFormatHandlerAdapter(value, attr));
                } else if (StringUtils.isNotEmpty(expMap.get(attr.name())) && StringUtils.isNotNull(value)) {

                    cell.setCellValue(convertByExp(String.valueOf(value), expMap.get(attr.name()), separator));
                } else {
                    // 设置列类型
                    setCellVo(value, attr, cell);
                }
                addStatisticsData(column, Convert.toStr(value), attr);
            }
        } catch (Exception e) {
            log.error("导出Excel失败{}", e);
        }
        return cell;
    }

    /**
     * 创建excel第一行标题
     */
    public void createTitle() {
        if (StringUtils.isNotEmpty(title)) {
            Row titleRow = sheet.createRow(rownum == 0 ? rownum++ : 0);
            titleRow.setHeightInPoints(30);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellStyle(styles.get("title"));
            titleCell.setCellValue(title);
            sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(), titleRow.getRowNum(), titleRow.getRowNum(),
                    this.fields.size() - 1));
        }
    }

    /**
     * 对excel表单默认第一个索引名转换成list
     *
     * @param is 输入流
     * @return 转换后集合
     */
    public List<T> importExcel(InputStream is) throws Exception {
        return importExcel(is, 0);
    }

    /**
     * 对excel表单默认第一个索引名转换成list
     *
     * @param is 输入流
     * @param titleNum 标题占用行数
     * @return 转换后集合
     */
    public List<T> importExcel(InputStream is, int titleNum) throws Exception {
        return importExcel(StringUtils.EMPTY, is, titleNum);
    }

    /**
     * 对excel表单指定表格索引名转换成list
     *
     * @param sheetName 表格索引名
     * @param titleNum 标题占用行数
     * @param is 输入流
     * @return 转换后集合
     */
    public List<T> importExcel(String sheetName, InputStream is, int titleNum) throws Exception {
        this.type = Excel.Type.IMPORT;
        this.wb = WorkbookFactory.create(is);
        List<T> list = new ArrayList<T>();
        // 如果指定sheet名,则取指定sheet中的内容 否则默认指向第1个sheet
        Sheet sheet = StringUtils.isNotEmpty(sheetName) ? wb.getSheet(sheetName) : wb.getSheetAt(0);
        if (sheet == null) {
            throw new IOException("文件sheet不存在");
        }

        // 获取最后一个非空行的行下标，比如总行数为n，则返回的为n-1
        int rows = sheet.getLastRowNum();

        if (rows > 0) {
            // 定义一个map用于存放excel列的序号和field.
            Map<String, Integer> cellMap = new HashMap<String, Integer>();
            // 获取表头
            Row heard = sheet.getRow(titleNum);
            for (int i = 0; i < heard.getPhysicalNumberOfCells(); i++) {
                Cell cell = heard.getCell(i);
                if (StringUtils.isNotNull(cell)) {
                    String value = this.getCellValue(heard, i).toString();
                    cellMap.put(value, i);
                } else {
                    cellMap.put(null, i);
                }
            }
            // 有数据时才处理 得到类的所有field.
            List<Object[]> fields = this.getFields();
            Map<Integer, Object[]> fieldsMap = new HashMap<Integer, Object[]>();
            for (Object[] objects : fields) {
                Excel attr = (Excel) objects[1];
                Integer column = cellMap.get(attr.name());
                if (column != null) {
                    fieldsMap.put(column, objects);
                }
            }
            for (int i = titleNum + 1; i <= rows; i++) {
                // 从第2行开始取数据,默认第一行是表头.
                Row row = sheet.getRow(i);
                // 判断当前行是否是空行
                if (isRowEmpty(row)) {
                    continue;
                }
                T entity = null;
                for (Map.Entry<Integer, Object[]> entry : fieldsMap.entrySet()) {
                    Object val = this.getCellValue(row, entry.getKey());

                    // 如果不存在实例则新建.
                    entity = (entity == null ? clazz.newInstance() : entity);
                    // 从map中得到对应列的field.
                    Field field = (Field) entry.getValue()[0];
                    Excel attr = (Excel) entry.getValue()[1];
                    // 取得类型,并根据对象类型设置值.
                    Class<?> fieldType = field.getType();
                    if (String.class == fieldType) {
                        String s = Convert.toStr(val);
                        if (StringUtils.endsWith(s, ".0")) {
                            val = StringUtils.substringBefore(s, ".0");
                        } else {
                            String dateFormat = field.getAnnotation(Excel.class).dateFormat();
                            if (StringUtils.isNotEmpty(dateFormat)) {
                                val = DateUtils.parseDateToStr(dateFormat, (Date) val);
                            } else {
                                val = Convert.toStr(val);
                            }
                        }
                    } else if ((Integer.TYPE == fieldType || Integer.class == fieldType) && StringUtils.isNumeric(Convert.toStr(val))) {
                        val = Convert.toInt(val);
                    }
                    //else if (Long.TYPE == fieldType || Long.class == fieldType)
                    else if ((Long.TYPE == fieldType || Long.class == fieldType) && StringUtils.isNumeric(Convert.toStr(val))) {
                        val = Convert.toLong(val);
                    } else if (Double.TYPE == fieldType || Double.class == fieldType) {
                        val = Convert.toDouble(val);
                    } else if (Float.TYPE == fieldType || Float.class == fieldType) {
                        val = Convert.toFloat(val);
                    } else if (BigDecimal.class == fieldType) {
                        val = Convert.toBigDecimal(val);
                    } else if (Date.class == fieldType) {
                        if (val instanceof String) {
                            val = DateUtils.parseDate(val);
                        } else if (val instanceof Double) {
                            val = DateUtil.getJavaDate((Double) val);
                        }
                    } else if (Boolean.TYPE == fieldType || Boolean.class == fieldType) {
                        val = Convert.toBool(val, false);
                    }
                    if (StringUtils.isNotNull(fieldType)) {
                        String propertyName = field.getName();
                        if (StringUtils.isNotEmpty(attr.targetAttr())) {
                            propertyName = field.getName() + "." + attr.targetAttr();
                        } else if (StringUtils.isNotEmpty(attr.readConverterExp())) {
                            val = reverseByExp(Convert.toStr(val), attr.readConverterExp(), attr.separator());
                        } else if (!attr.handler().equals(ExcelHandlerAdapter.class)) {
                            val = dataFormatHandlerAdapter(val, attr);
                        }
                        ReflectUtils.invokeSetter(entity, propertyName, val);
                    }
                }
                list.add(entity);
            }
        }
        return list;
    }

    /**
     * 对list数据源将其里面的数据导入到excel表单
     *
     * @param response 返回数据
     * @param list 导出数据集合
     * @param sheetName 工作表的名称
     * @return 结果
     * @throws IOException
     */
    public void exportExcel(HttpServletResponse response, List<T> list, String sheetName) throws IOException {
        exportExcel(response, list, sheetName, StringUtils.EMPTY);
    }

    /**
     * 对list数据源将其里面的数据导入到excel表单
     *
     * @param response 返回数据
     * @param list 导出数据集合
     * @param sheetName 工作表的名称
     * @param title 标题
     * @return 结果
     * @throws IOException
     */
    public void exportExcel(HttpServletResponse response, List<T> list, String sheetName, String title) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        this.init(list, sheetName, title, Type.EXPORT);
        exportExcel(response.getOutputStream());
    }

    /**
     * 对list数据源将其里面的数据导入到excel表单
     *
     * @param sheetName 工作表的名称
     * @return 结果
     */
    public void importTemplateExcel(HttpServletResponse response, String sheetName) throws IOException {
        importTemplateExcel(response, sheetName, StringUtils.EMPTY);
    }

    /**
     * 对list数据源将其里面的数据导入到excel表单
     *
     * @param sheetName 工作表的名称
     * @param title 标题
     * @return 结果
     */
    public void importTemplateExcel(HttpServletResponse response, String sheetName, String title) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        this.init(null, sheetName, title, Type.IMPORT);
        exportExcel(response.getOutputStream());
    }

    /**
     * 编码文件名
     */
    public String encodingFilename(String filename) {
        //filename = UUID.randomUUID().toString() + "_" + filename + ".xlsx";
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String time = dateTimeFormatter.format(now);
        filename = time + "_" + filename + ".xlsx";
        return filename;
    }

    /**
     * 获取下载路径
     *
     * @param filename 文件名称
     */
    public String getAbsoluteFile(String filename) {
        String downloadPath = RuoYiConfig.getDownloadPath() + filename;
        File desc = new File(downloadPath);
        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        return downloadPath;
    }

    /**
     * 对list数据源将其里面的数据导入到excel表单
     * @return 结果
     */
    public AjaxResult exportExcel(OutputStream out) {
        try {
            writeSheet();
            //wb.write(out);
            String filename = encodingFilename(sheetName);
            out = new FileOutputStream(getAbsoluteFile(filename));
            wb.write(out);
            return AjaxResult.success(filename);
        } catch (Exception e) {
            log.error("导出Excel异常{}", e.getMessage());
            throw new CustomException("导出Excel失败，请联系网站管理员！");
        } finally {
            IOUtils.closeQuietly(wb);
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 创建写入数据到Sheet
     */
    public void writeSheet() {
        // 取出一共有多少个sheet.
        int sheetNo = Math.max(1, (int) Math.ceil(list.size() * 1.0 / sheetSize));
        for (int index = 0; index < sheetNo; index++) {
            createSheet(sheetNo, index);

            // 产生一行
            Row row = sheet.createRow(rownum);
            int column = 0;
            // 写入各个字段的列头名称
            for (Object[] os : fields) {
                Excel excel = (Excel) os[1];
                this.createCell(excel, row, column++);
            }
            if (Type.EXPORT.equals(type)) {
                fillExcelData(index, row);
                addStatisticsRow();
            }
        }
    }

    /**
     * 填充excel数据
     *
     * @param index 序号
     * @param row 单元格行
     */
    public void fillExcelData(int index, Row row) {
        int startNo = index * sheetSize;
        int endNo = Math.min(startNo + sheetSize, list.size());
        for (int i = startNo; i < endNo; i++) {
            row = sheet.createRow(i + 1 + rownum - startNo);
            // 得到导出对象.
            T vo = (T) list.get(i);
            int column = 0;
            for (Object[] os : fields) {
                Field field = (Field) os[0];
                Excel excel = (Excel) os[1];
                this.addCell(excel, row, vo, field, column++);
            }
        }
    }

    /**
     * 创建表格样式
     *
     * @param wb 工作薄对象
     * @return 样式列表
     */
    private Map<String, CellStyle> createStyles(Workbook wb) {
        // 写入各条记录,每条记录对应excel表中的一行
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font titleFont = wb.createFont();
        titleFont.setFontName("宋体"); //设置字体颜色 // Arial
        titleFont.setFontHeightInPoints((short) 16);
        titleFont.setBold(true);
        style.setFont(titleFont);
        style.setWrapText(true);//设置自动换行
        styles.put("title", style);

        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        Font dataFont = wb.createFont();
        dataFont.setFontName("宋体"); //设置字体颜色 // Arial
        dataFont.setFontHeightInPoints((short) 10);
        style.setFont(dataFont);
        styles.put("data", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        //style.setBorderBottom(BorderStyle.THIN); //下边框
        style.setBorderLeft(BorderStyle.THIN);//左边框
        style.setBorderTop(BorderStyle.THIN);//上边框
        style.setBorderRight(BorderStyle.THIN);//右边框
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font headerFont = wb.createFont();
        headerFont.setFontName("宋体"); //设置字体颜色 // Arial
        headerFont.setFontHeightInPoints((short) 10); //设置字体大小
        headerFont.setBold(true);
        //headerFont.setColor(IndexedColors.WHITE.getIndex());
        style.setWrapText(true);//设置自动换行
        style.setFont(headerFont);
        styles.put("header", style);

        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font totalFont = wb.createFont();
        totalFont.setFontName("宋体"); //设置字体颜色 // Arial
        totalFont.setFontHeightInPoints((short) 10);
        style.setFont(totalFont);
        styles.put("total", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.LEFT);
        styles.put("data1", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.CENTER);
        styles.put("data2", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.RIGHT);
        styles.put("data3", style);

        return styles;
    }

    /**
     * 创建单元格
     */
    public Cell createCell(Excel attr, Row row, int column) {
        // 创建列
        Cell cell = row.createCell(column);
        // 写入列信息
        cell.setCellValue(attr.name());
        setDataValidation(attr, row, column);
        cell.setCellStyle(styles.get("header"));
        return cell;
    }

    /**
     * 设置单元格信息
     *
     * @param value 单元格值
     * @param attr 注解相关
     * @param cell 单元格信息
     */
    public void setCellVo(Object value, Excel attr, Cell cell) {
        if (ColumnType.STRING == attr.cellType()) {
            cell.setCellValue(StringUtils.isNull(value) ? attr.defaultValue() : value + attr.suffix());
        } else if (ColumnType.NUMERIC == attr.cellType()) {
            if (StringUtils.isNotNull(value)) {
                cell.setCellValue(StringUtils.contains(Convert.toStr(value), ".") ? Convert.toDouble(value) : Convert.toInt(value));
            }
        } else if (ColumnType.IMAGE == attr.cellType()) {
            ClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0, (short) cell.getColumnIndex(), cell.getRow().getRowNum(), (short) (cell.getColumnIndex() + 1), cell.getRow().getRowNum() + 1);
            String imagePath = Convert.toStr(value);
            if (StringUtils.isNotEmpty(imagePath)) {
                byte[] data = ImageUtils.getImage(imagePath);
                getDrawingPatriarch(cell.getSheet()).createPicture(anchor,
                        cell.getSheet().getWorkbook().addPicture(data, getImageType(data)));
            }
        }
    }

    /**
     * 获取画布
     */
    public static Drawing<?> getDrawingPatriarch(Sheet sheet) {
        if (sheet.getDrawingPatriarch() == null) {
            sheet.createDrawingPatriarch();
        }
        return sheet.getDrawingPatriarch();
    }

    /**
     * 获取图片类型,设置图片插入类型
     */
    public int getImageType(byte[] value) {
        String type = FileTypeUtils.getFileExtendName(value);
        if ("JPG".equalsIgnoreCase(type)) {
            return Workbook.PICTURE_TYPE_JPEG;
        } else if ("PNG".equalsIgnoreCase(type)) {
            return Workbook.PICTURE_TYPE_PNG;
        }
        return Workbook.PICTURE_TYPE_JPEG;
    }

    /**
     * 创建表格样式
     */
    public void setDataValidation(Excel attr, Row row, int column) {
        if (attr.name().indexOf("注：") >= 0) {
            sheet.setColumnWidth(column, 6000);
        } else {
            // 设置列宽
            sheet.setColumnWidth(column, (int) ((attr.width() + 0.72) * 256));
        }
        // 如果设置了提示信息则鼠标放上去提示.
        if (StringUtils.isNotEmpty(attr.prompt())) {
            // 这里默认设了2-101列提示.
            setXSSFPrompt(sheet, "", attr.prompt(), 1, 100, column, column);
        }
        // 如果设置了combo属性则本列只能选择不能输入
        if (attr.combo().length > 0)

            // 这里默认设了2-101列只能选择不能输入.
            setXSSFValidation(sheet, attr.combo(), 1, 100, column, column);
    }

    /**
     * 添加单元格
     */
    public Cell addCell(Excel attr, Row row, T vo, Field field, int column) {
        Cell cell = null;
        try {
            // 设置行高
            row.setHeight(maxHeight);
            // 根据Excel中设置情况决定是否导出,有些情况需要保持为空,希望用户填写这一列.
            if (attr.isExport()) {
                // 创建cell
                cell = row.createCell(column);
                /*int align = attr.align().value();
                cell.setCellStyle(styles.get("data" + (attr.align >= 1 && align <= 3 ? align : "")));*/
                //cell.setCellStyle(styles.get("data" + 2)); // 2：居中
                //cell.setCellStyle(styles.get(StringUtils.format("data_{}_{}_{}", attr.align()/*, attr.color(), attr.backgroundColor()*/)));
                cell.setCellStyle(styles.get(StringUtils.format("data", 2)));//2：居中
                // 用于读取对象中的属性
                Object value = getTargetValue(vo, field, attr);
                String dateFormat = attr.dateFormat();
                String readConverterExp = attr.readConverterExp();
                String separator = attr.separator();
                if (StringUtils.isNotEmpty(dateFormat) && StringUtils.isNotNull(value)) {
                    cell.setCellValue(DateUtils.parseDateToStr(dateFormat, (Date) value));
                }
                else if (StringUtils.isNotEmpty(readConverterExp) && StringUtils.isNotNull(value)) {
                    cell.setCellValue(convertByExp(Convert.toStr(value), readConverterExp, separator));
                }
                else if (value instanceof BigDecimal && -1 != attr.scale()) {
                    cell.setCellValue((((BigDecimal) value).setScale(attr.scale(), attr.roundingMode())).toString());
                }
                else if (!attr.handler().equals(ExcelHandlerAdapter.class)) {
                    cell.setCellValue(dataFormatHandlerAdapter(value, attr));
                }
                else if(StringUtils.isNotEmpty(expMap.get(attr.name()))&& StringUtils.isNotNull(value)){

                    cell.setCellValue(convertByExp(String.valueOf(value), expMap.get(attr.name()), separator));
                }
                else {
                    // 设置列类型
                    setCellVo(value, attr, cell);
                }
                addStatisticsData(column, Convert.toStr(value), attr);
            }
        } catch (Exception e) {
            log.error("导出Excel失败{}", e);
        }
        return cell;
    }

    /**
     * 设置 POI XSSFSheet 单元格提示
     *
     * @param sheet 表单
     * @param promptTitle 提示标题
     * @param promptContent 提示内容
     * @param firstRow 开始行
     * @param endRow 结束行
     * @param firstCol 开始列
     * @param endCol 结束列
     */
    public void setXSSFPrompt(Sheet sheet, String promptTitle, String promptContent, int firstRow, int endRow,
                              int firstCol, int endCol) {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        DataValidationConstraint constraint = helper.createCustomConstraint("DD1");
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        DataValidation dataValidation = helper.createValidation(constraint, regions);
        dataValidation.createPromptBox(promptTitle, promptContent);
        dataValidation.setShowPromptBox(true);
        sheet.addValidationData(dataValidation);
    }

    /**
     * 设置某些列的值只能输入预制的数据,显示下拉框.
     *
     * @param sheet 要设置的sheet.
     * @param textlist 下拉框显示的内容
     * @param firstRow 开始行
     * @param endRow 结束行
     * @param firstCol 开始列
     * @param endCol 结束列
     * @return 设置好的sheet.
     */
    public void setXSSFValidation(Sheet sheet, String[] textlist, int firstRow, int endRow, int firstCol, int endCol) {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        // 加载下拉列表内容
        DataValidationConstraint constraint = helper.createExplicitListConstraint(textlist);
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // 数据有效性对象
        DataValidation dataValidation = helper.createValidation(constraint, regions);
        // 处理Excel兼容性问题
        if (dataValidation instanceof XSSFDataValidation) {
            dataValidation.setSuppressDropDownArrow(true);
            dataValidation.setShowErrorBox(true);
        } else {
            dataValidation.setSuppressDropDownArrow(false);
        }

        sheet.addValidationData(dataValidation);
    }

    /**
     * 解析导出值 0=男,1=女,2=未知
     *
     * @param propertyValue 参数值
     * @param converterExp 翻译注解
     * @param separator 分隔符
     * @return 解析后值
     */
    public static String convertByExp(String propertyValue, String converterExp, String separator) {
        StringBuilder propertyString = new StringBuilder();
        String[] convertSource = converterExp.split(",");
        for (String item : convertSource) {
            String[] itemArray = item.split("=");
            if (StringUtils.containsAny(separator, propertyValue)) {
                for (String value : propertyValue.split(separator)) {
                    if (itemArray[0].equals(value)) {
                        propertyString.append(itemArray[1] + separator);
                        break;
                    }
                }
            }
            else {
                if (itemArray[0].equals(propertyValue)) {
                    return itemArray[1];
                }
            }
        }
        return StringUtils.stripEnd(propertyString.toString(), separator);
    }

    /**
     * 反向解析值 男=0,女=1,未知=2
     *
     * @param propertyValue 参数值
     * @param converterExp 翻译注解
     * @param separator 分隔符
     * @return 解析后值
     */
    public static String reverseByExp(String propertyValue, String converterExp, String separator) {
        StringBuilder propertyString = new StringBuilder();
        String[] convertSource = converterExp.split(",");
        for (String item : convertSource) {
            String[] itemArray = item.split("=");
            if (StringUtils.containsAny(separator, propertyValue)) {
                for (String value : propertyValue.split(separator)) {
                    if (itemArray[1].equals(value)) {
                        propertyString.append(itemArray[0] + separator);
                        break;
                    }
                }
            } else {
                if (itemArray[1].equals(propertyValue)) {
                    return itemArray[0];
                }
            }
        }
        return StringUtils.stripEnd(propertyString.toString(), separator);
    }

    /**
     * 数据处理器
     *
     * @param value 数据值
     * @param excel 数据注解
     * @return
     */
    public String dataFormatHandlerAdapter(Object value, Excel excel) {
        try {
            Object instance = excel.handler().newInstance();
            Method formatMethod = excel.handler().getMethod("format", new Class[]{Object.class, String[].class});
            value = formatMethod.invoke(instance, value, excel.args());
        } catch (Exception e) {
            log.error("不能格式化数据 " + excel.handler(), e.getMessage());
        }
        return Convert.toStr(value);
    }

    /**
     * 合计统计信息
     */
    private void addStatisticsData(Integer index, String text, Excel entity) {
        if (entity != null && entity.isStatistics()) {
            Double temp = 0D;
            if (!statistics.containsKey(index)) {
                statistics.put(index, temp);
            }
            try {
                temp = Double.valueOf(text);
            } catch (NumberFormatException e) {
            }
            statistics.put(index, statistics.get(index) + temp);
        }
    }

    /**
     * 创建统计行
     */
    public void addStatisticsRow() {
        if (statistics.size() > 0) {
            Row row = sheet.createRow(sheet.getLastRowNum() + 1);
            Set<Integer> keys = statistics.keySet();
            Cell cell = row.createCell(0);
            cell.setCellStyle(styles.get("total"));
            cell.setCellValue("合计");

            for (Integer key : keys) {
                cell = row.createCell(key);
                cell.setCellStyle(styles.get("total"));
                cell.setCellValue(DOUBLE_FORMAT.format(statistics.get(key)));
            }
            statistics.clear();
        }
    }

    /**
     * 获取bean中的属性值
     * @param vo 实体对象
     * @param field 字段
     * @param excel 注解
     * @return 最终的属性值
     * @throws Exception
     */
    private Object getTargetValue(T vo, Field field, Excel excel) throws Exception {
        Object o = field.get(vo);
        if (StringUtils.isNotEmpty(excel.targetAttr())) {
            String target = excel.targetAttr();
            if (target.indexOf(".") > -1) {
                String[] targets = target.split("[.]");
                for (String name : targets) {
                    o = getValue(o, name);
                }
            } else {
                o = getValue(o, target);
            }
        }
        return o;
    }

    /**
     * 以类的属性的get方法方法形式获取值
     * @param o
     * @param name
     * @return value
     * @throws Exception
     */
    private Object getValue(Object o, String name) throws Exception {
        if (StringUtils.isNotNull(o) && StringUtils.isNotEmpty(name)) {
            Class<?> clazz = o.getClass();
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            o = field.get(o);
        }
        return o;
    }

    /**
     * 得到所有定义字段
     */
    private void createExcelField() {
        this.fields = getFields();
        this.fields = this.fields.stream().sorted(Comparator.comparing(objects -> ((Excel) objects[1]).sort())).collect(Collectors.toList());
        this.maxHeight = getRowHeight();
    }

    /**
     * 获取字段注解信息
     */
    public List<Object[]> getFields() {
        List<Object[]> fields = new ArrayList<Object[]>();
        List<Field> tempFields = new ArrayList<>();
        tempFields.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
        tempFields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        for (Field field : tempFields) {
            // 单注解
            if (field.isAnnotationPresent(Excel.class)) {
                Excel attr = field.getAnnotation(Excel.class);
                if (attr != null && (attr.type() == Type.ALL || attr.type() == type)) {
                    field.setAccessible(true);
                    fields.add(new Object[]{field, attr});
                }
            }
            // 多注解
            if (field.isAnnotationPresent(Excels.class)) {
                Excels attrs = field.getAnnotation(Excels.class);
                Excel[] excels = attrs.value();
                for (Excel attr : excels) {
                    if (attr != null && (attr.type() == Type.ALL || attr.type() == type)) {
                        field.setAccessible(true);
                        fields.add(new Object[]{field, attr});
                    }
                }
            }
        }
        return fields;
    }

    /**
     * 根据注解获取最大行高
     */
    public short getRowHeight() {
        double maxHeight = 0;
        for (Object[] os : this.fields) {
            Excel excel = (Excel) os[1];
            maxHeight = maxHeight > excel.height() ? maxHeight : excel.height();
        }
        return (short) (maxHeight * 20);
    }

    /**
     * 创建一个工作簿
     */
    public void createWorkbook() {
        this.wb = new SXSSFWorkbook(500);
        this.sheet = wb.createSheet();
        wb.setSheetName(0, sheetName);
        this.styles = createStyles(wb);
    }

    /**
     * 创建工作表
     * @param sheetNo sheet数量
     * @param index   序号
     */
    public void createSheet(int sheetNo, int index) {
        // 设置工作表的名称.
        if (sheetNo > 1 && index > 0) {
            this.sheet = wb.createSheet();
            this.createTitle();
            wb.setSheetName(index, sheetName + index);
        }
    }

    /**
     * 获取单元格值
     * @param row    获取的行
     * @param column 获取单元格列号
     * @return 单元格值
     */
    public Object getCellValue(Row row, int column) {
        if (row == null) {
            return row;
        }
        Object val = "";
        try {
            Cell cell = row.getCell(column);
            if (StringUtils.isNotNull(cell)) {
                if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
                    val = cell.getNumericCellValue();
                    if (DateUtil.isCellDateFormatted(cell)) {
                        val = DateUtil.getJavaDate((Double) val); // POI Excel 日期格式转换
                    } else {
                        if ((Double) val % 1 != 0) {
                            val = new BigDecimal(val.toString());
                        } else {
                            val = new DecimalFormat("0").format(val);
                        }
                    }
                } else if (cell.getCellType() == CellType.STRING) {
                    val = cell.getStringCellValue();
                } else if (cell.getCellType() == CellType.BOOLEAN) {
                    val = cell.getBooleanCellValue();
                } else if (cell.getCellType() == CellType.ERROR) {
                    val = cell.getErrorCellValue();
                }

            }
        } catch (Exception e) {
            return val;
        }
        return val;
    }

    /**
     * 判断是否是空行
     * @param row 判断的行
     * @return
     */
    private boolean isRowEmpty(Row row) {
        if (row == null) {
            return true;
        }
        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

}
