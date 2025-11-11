package com.cb.basedata.service.impl;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.cb.ai.domain.AiSkillModel;
import com.cb.ai.service.IAiSkillModelService;
import com.cb.basedata.domain.BasicDataCleanTaskLog;
import com.cb.basedata.domain.vo.CleanSourceDataParam;
import com.cb.basedata.handler.CleanTblResultHandler;
import com.cb.basedata.service.IBasicDataCleanTaskLogService;
import com.cb.basedata.service.ICleanTblService;
import com.cb.basedata.utils.CleanEtlUtils;
import com.cb.common.config.RuoYiConfig;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.framework.manager.AsyncManager;
import com.cb.table.domain.Columns;
import com.cb.table.domain.Table;
import com.cb.table.service.ITableService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.cb.basedata.domain.BasicDataCleanTaskRelation;
import com.cb.basedata.mapper.BasicDataCleanTaskMapper;
import com.cb.basedata.domain.BasicDataCleanTask;
import com.cb.basedata.service.IBasicDataCleanTaskService;

/**
 * 数据清洗任务Service业务层处理
 *
 * @author ouyang
 * @date 2024-11-01
 */
@Service
public class BasicDataCleanTaskServiceImpl implements IBasicDataCleanTaskService {
    @Autowired
    private BasicDataCleanTaskMapper basicDataCleanTaskMapper;
    @Autowired
    private IBasicDataCleanTaskLogService taskLogService;
    @Autowired
    private ICleanTblService cleanTblService;
    @Autowired
    private IAiSkillModelService aiSkillModelService;
    @Autowired
    private ITableService tableService;

    /**
     * 查询数据清洗任务
     *
     * @param taskId 数据清洗任务ID
     * @return 数据清洗任务
     */
    @Override
    public BasicDataCleanTask selectBasicDataCleanTaskById(String taskId) {
        return basicDataCleanTaskMapper.selectBasicDataCleanTaskById(taskId);
    }

    /**
     * 查询数据清洗任务列表
     *
     * @param basicDataCleanTask 数据清洗任务
     * @return 数据清洗任务
     */
    @Override
    public List<BasicDataCleanTask> selectBasicDataCleanTaskList(BasicDataCleanTask basicDataCleanTask) {
        return basicDataCleanTaskMapper.selectBasicDataCleanTaskList(basicDataCleanTask);
    }

    /**
     * 新增数据清洗任务
     *
     * @param basicDataCleanTask 数据清洗任务
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBasicDataCleanTask(BasicDataCleanTask basicDataCleanTask) {
        basicDataCleanTask.setTaskId(IdUtils.randomUUID());
        basicDataCleanTask.setCreateBy(SecurityUtils.getUsername());
        basicDataCleanTask.setCreateTime(DateUtils.getNowDate());
        int rows = basicDataCleanTaskMapper.insertBasicDataCleanTask(basicDataCleanTask);
        insertBasicDataCleanTaskRelation(basicDataCleanTask);
        return rows;
    }

    /**
     * 修改数据清洗任务
     *
     * @param basicDataCleanTask 数据清洗任务
     * @return 结果
     */
    @Transactional
    @Override
    public int updateBasicDataCleanTask(BasicDataCleanTask basicDataCleanTask) {
        basicDataCleanTask.setUpdateBy(SecurityUtils.getUsername());
        basicDataCleanTask.setUpdateTime(DateUtils.getNowDate());
        basicDataCleanTask.setUpdateBy(SecurityUtils.getUsername());
        basicDataCleanTaskMapper.deleteBasicDataCleanTaskRelationByCleanTaskId(basicDataCleanTask.getTaskId());
        insertBasicDataCleanTaskRelation(basicDataCleanTask);
        return basicDataCleanTaskMapper.updateBasicDataCleanTask(basicDataCleanTask);
    }

    /**
     * 批量删除数据清洗任务
     *
     * @param taskIds 需要删除的数据清洗任务ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBasicDataCleanTaskByIds(String[] taskIds) {
        basicDataCleanTaskMapper.deleteBasicDataCleanTaskRelationByCleanTaskIds(taskIds);
        return basicDataCleanTaskMapper.deleteBasicDataCleanTaskByIds(taskIds);
    }

    /**
     * 删除数据清洗任务信息
     *
     * @param taskId 数据清洗任务ID
     * @return 结果
     */
    @Override
    public int deleteBasicDataCleanTaskById(String taskId) {
        basicDataCleanTaskMapper.deleteBasicDataCleanTaskRelationByCleanTaskId(taskId);
        return basicDataCleanTaskMapper.deleteBasicDataCleanTaskById(taskId);
    }

    /**
     * 新增数据清洗任务字段关系信息
     *
     * @param basicDataCleanTask 数据清洗任务对象
     */
    public void insertBasicDataCleanTaskRelation(BasicDataCleanTask basicDataCleanTask) {
        List<BasicDataCleanTaskRelation> basicDataCleanTaskRelationList = basicDataCleanTask.getBasicDataCleanTaskRelationList();
        String taskId = basicDataCleanTask.getTaskId();
        if (StringUtils.isNotNull(basicDataCleanTaskRelationList)) {
            List<BasicDataCleanTaskRelation> list = new ArrayList<BasicDataCleanTaskRelation>();
            for (BasicDataCleanTaskRelation basicDataCleanTaskRelation : basicDataCleanTaskRelationList) {
                basicDataCleanTaskRelation.setRelationId(IdUtils.randomUUID());
                basicDataCleanTaskRelation.setCleanTaskId(taskId);
                list.add(basicDataCleanTaskRelation);
            }
            if (list.size() > 0) {
                basicDataCleanTaskMapper.batchBasicDataCleanTaskRelation(list);
            }
        }
    }

    @Override
    public int dataCleaning(CleanSourceDataParam param) {
        BasicDataCleanTaskLog log = new BasicDataCleanTaskLog();
        log.setCleanTaskId(param.getTaskId());
        log.setStartTime(DateUtils.getNowDate());
        log.setStatus("0");
        taskLogService.insertBasicDataCleanTaskLog(log);
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 异步执行数数据清洗
        AsyncManager.me().execute(batchCleanTask(param, log, user));
        return 1;
    }



    private TimerTask batchCleanTask(CleanSourceDataParam param, BasicDataCleanTaskLog log, SysUser user) {
        return new TimerTask() {
            @Override
            public void run() {
                BasicDataCleanTask task = basicDataCleanTaskMapper.selectBasicDataCleanTaskById(param.getTaskId());
                List<AiSkillModel> skillModelList = aiSkillModelService.selectAllAiSkillModelList();
                // 获取源数据表以及主键字段
                String sourceTableName = task.getSourceTable();
                Table sourceTable = tableService.selectTableListByTableName(sourceTableName);
                String primaryKeyColumn = cleanTblService.selectTablePrimaryKeyColumn(sourceTable.getOwner(), sourceTableName);

                //获取目标表
                String targetTableName = task.getTargetTable();
                Table targetTable = tableService.selectTableListByTableName(targetTableName);

                List<JSONObject> errorMsgList = new ArrayList<>(); // 清洗错误值
                CleanTblResultHandler cleanTblResultHandler = new CleanTblResultHandler(param, task,primaryKeyColumn
                        ,targetTable, log, skillModelList, errorMsgList, user);
                List<String> sourceTableColumns = CleanEtlUtils.buildTableColumns(sourceTable);  // 构建目标表的字段
                /**
                 * 构建源数据查询条件
                 */
                String startTime = DateUtil.formatDateTime(param.getStartTime());
                String endTime = DateUtil.formatDateTime(DateUtil.endOfDay(param.getEndTime()));
                String sourceCondition = String.format("and create_time BETWEEN '%s' AND '%s'", startTime, endTime);
                // 调用流式查询，并执行清洗任务
                cleanTblService.streamQueryDataList(sourceTableName, String.join(", ", sourceTableColumns),
                        CleanEtlUtils.buildConditions(sourceCondition), cleanTblResultHandler);
                // 执行完最后一批数据的逻辑
                cleanTblResultHandler.end();
            }
        };
    }

    @Override
    public AjaxResult importTemplate(String taskId) {
        BasicDataCleanTask task = this.selectBasicDataCleanTaskById(taskId);
        String sourceTable = task.getSourceTable();
        Table table = tableService.selectTableListByTableName(sourceTable);
        List<Columns> columns = table.getColumns();
        List<String> filedList = columns.stream()
                .filter(o->{
                    String column = o.getColumnName();
                    return StringUtils.isNotBlank(column) && !column.equals(CleanEtlUtils.DEFAULT_AI_MODEL_CODE)
                            && !Arrays.asList(CleanEtlUtils.BASE_ENTITY_COLUMNS).contains(column);
                })
                .map(Columns::getComments)
                .distinct()
                .collect(Collectors.toList());
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(table.getTableName());

        CellStyle style = createStyle(workbook);
        // 创建表头
        Row header = sheet.createRow(0);
        for (int i = 0; i < filedList.size(); i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(filedList.get(i));
            cell.setCellStyle(style);
        }
        OutputStream out = null;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String time = dateTimeFormatter.format(now);
        String filename = time + "_" + table.getTableComments() + ".xlsx";
        String downloadPath = RuoYiConfig.getDownloadPath() + filename;
        File desc = new File(downloadPath);
        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        try {
            out = new FileOutputStream(downloadPath);
            workbook.write(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return AjaxResult.success(filename);
    }

    private CellStyle createStyle(XSSFWorkbook workbook){
        CellStyle style = workbook.createCellStyle();
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
        style.setBorderBottom(BorderStyle.THIN); //下边框
        style.setBorderLeft(BorderStyle.THIN);//左边框
        style.setBorderTop(BorderStyle.THIN);//上边框
        style.setBorderRight(BorderStyle.THIN);//右边框
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font headerFont = workbook.createFont();
        headerFont.setFontName("宋体"); //设置字体颜色 // Arial
        headerFont.setFontHeightInPoints((short) 10); //设置字体大小
        headerFont.setBold(true);
        style.setWrapText(true);//设置自动换行
        style.setFont(headerFont);
        return style;
    }
}
