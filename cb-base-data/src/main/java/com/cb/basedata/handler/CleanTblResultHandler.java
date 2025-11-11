package com.cb.basedata.handler;

import com.alibaba.fastjson.JSONObject;
import com.cb.ai.domain.AiSkillModel;
import com.cb.basedata.domain.BasicDataCleanTask;
import com.cb.basedata.domain.BasicDataCleanTaskLog;
import com.cb.basedata.domain.vo.CleanSourceDataParam;
import com.cb.basedata.service.IBasicDataCleanTaskLogService;
import com.cb.basedata.service.ICleanTblService;
import com.cb.basedata.utils.CleanEtlUtils;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.spring.SpringUtils;
import com.cb.table.domain.Table;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CleanTblResultHandler implements ResultHandler<Map<String, Object>> {
    private static final Logger logger = LoggerFactory.getLogger(CleanTblResultHandler.class);
    private static ICleanTblService cleanTblService;
    private static IBasicDataCleanTaskLogService taskLogService;

    static {
        cleanTblService = SpringUtils.getBean(ICleanTblService.class);
        taskLogService = SpringUtils.getBean(IBasicDataCleanTaskLogService.class);
    }

    //这里每集满1000条数据，进行一次数据清洗
    private final static int BATCH_SIZE = 1000;
    //计数器
    private int size = 0;

    // 执行次数，从零开始
    private int executeNum = 0;

    private CleanSourceDataParam param;
    private BasicDataCleanTask task;
    private String sourceTablePrimaryKeys;
    private Table targetTable;
    private BasicDataCleanTaskLog log;
    private List<AiSkillModel> skillModelList;
    private List<JSONObject> errorMsgList;
    private SysUser user;

    public CleanTblResultHandler(CleanSourceDataParam param, BasicDataCleanTask task,String sourceTablePrimaryKeys,
                                 Table targetTable, BasicDataCleanTaskLog log, List<AiSkillModel> skillModelList,
                                 List<JSONObject> errorMsgList, SysUser user) {
        this.param = param;
        this.task = task;
        this.sourceTablePrimaryKeys = sourceTablePrimaryKeys;
        this.targetTable = targetTable;
        this.log = log;
        this.skillModelList = skillModelList;
        this.errorMsgList = errorMsgList;
        this.user = user;
    }

    /**
     * 存储每批数据的临时容器
     */
    private List<Map<String, Object>> cleanSourceData = new ArrayList<>();

    @Override
    public void handleResult(ResultContext<? extends Map<String, Object>> resultContext) {
        // 这里获取流式查询每次返回的单条结果
        Map<String, Object> map = resultContext.getResultObject();
        // 你可以看自己的项目需要分批进行处理或者单个处理，这里以分批处理为例
        cleanSourceData.add(map);
        size++;
        if (size == BATCH_SIZE) {
            // 如果集满1000条就往文件中写一次
            handle();
            executeNum++;
        }

    }


    /**
     * 集满1000条 执行一次的逻辑
     */
    private void handle() {
        try {
            logger.info("第executeNum={}数据清洗执行;数据条数,size={}", executeNum, size);
            // 业务逻辑
            batchDataCleaning(param, task, sourceTablePrimaryKeys, targetTable, user, skillModelList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 处理完每批数据后后将临时清空
            size = 0;
            cleanSourceData.clear();
        }
    }

    /**
     * 这个方法给外面调用，用来完成最后一批数据处理
     */
    public void end() {
        handle();// 处理最后一批不到BATCH_SIZE的数据
        // 执行完成后去更新日志状态
        log.setCleanResult(errorMsgList.toString());
        log.setStatus("1");
        log.setEndTime(DateUtils.getNowDate());
        taskLogService.updateBasicDataCleanTaskLog(log);
    }


    private void batchDataCleaning(CleanSourceDataParam param, BasicDataCleanTask task, String sourceTablePrimaryKeys, Table targetTable, SysUser user, List<AiSkillModel> skillModelList) {
        List<String> sourceTablePrimaryKeyColumns = CleanEtlUtils.getTablePrimaryKeyColumn(sourceTablePrimaryKeys);
        // 获取目标表
        String targetTableName = task.getTargetTable();
        List<String> targetTableColumns = CleanEtlUtils.buildTableColumns(targetTable);

        // 求源数据表的主键和目标表的交集
        List<String> intersection = new ArrayList<>(sourceTablePrimaryKeyColumns);
        intersection.retainAll(targetTableColumns);

        for (Map<String, Object> sourceDataMap : cleanSourceData) {
            JSONObject result = new JSONObject();
            try {
                if (param.getIsCover()) {
                    String primaryKeyCondition = CleanEtlUtils.buildPrimaryKeyConditions(intersection, sourceDataMap);
                    String finalCondition = String.format("%s and create_time = (select max(create_time) from %s where 1=1 %s)", primaryKeyCondition, targetTableName, primaryKeyCondition);
                    Map<String, Object> existTargetMap = cleanTblService.selectData(targetTableName, String.join(", ", targetTableColumns), CleanEtlUtils.buildConditions(finalCondition));
                    if (existTargetMap != null) {
                        Object primaryColumnValue = existTargetMap.get(CleanEtlUtils.DEFAULT_PRIMARY_KEY_COLUMN);
                        Map<String, Object> targetDataMap = CleanEtlUtils.buildData(sourceDataMap, targetTableColumns, true, user);
                        CleanEtlUtils.buildCleanData(task, sourceDataMap, targetDataMap, skillModelList);
                        targetDataMap.put(CleanEtlUtils.DEFAULT_PRIMARY_KEY_COLUMN, primaryColumnValue);
                        String condition = String.format("and %s='%s'", CleanEtlUtils.DEFAULT_PRIMARY_KEY_COLUMN, primaryColumnValue);
                        // 执行更新操作
                        cleanTblService.updateDataToTable(targetTableName, targetDataMap, CleanEtlUtils.buildConditions(condition));
                    } else {
                        Map<String, Object> targetDataMap = CleanEtlUtils.buildData(sourceDataMap, targetTableColumns, false, user);
                        CleanEtlUtils.buildCleanData(task, sourceDataMap, targetDataMap, skillModelList);
                        // 执行新增操作
                        cleanTblService.insertDataToTable(targetTableName, targetDataMap);
                    }
                } else {
                    Map<String, Object> targetDataMap = CleanEtlUtils.buildData(sourceDataMap, targetTableColumns, false, user);
                    CleanEtlUtils.buildCleanData(task, sourceDataMap, targetDataMap, skillModelList);
                    // 执行新增操作
                    cleanTblService.insertDataToTable(targetTableName, targetDataMap);

                }
            } catch (Exception e) {
                e.printStackTrace();
                String message = e.getMessage();
                result.put("sourceData", sourceDataMap);
                result.put("cleanStatus", "失败");
                result.put("errorMsg", message);
                errorMsgList.add(result);
                logger.error("数据清洗失败，sourceData={};失败原因", sourceDataMap, e.getMessage());
            }
        }
    }
}
