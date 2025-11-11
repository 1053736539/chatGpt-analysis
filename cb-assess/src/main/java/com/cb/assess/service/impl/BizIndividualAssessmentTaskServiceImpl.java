package com.cb.assess.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.cb.assess.domain.*;
import com.cb.assess.domain.vo.*;
import com.cb.assess.enums.Special;
import com.cb.assess.mapper.*;
import com.cb.assess.service.IBizAssessTaskEvaluateRecordService;
import com.cb.assess.service.IBizIndividualAssessmentTaskService;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.filemanage.domain.BizAttach;
import com.cb.filemanage.service.IBizAttachService;
import com.cb.system.service.ISysDictTypeService;
import com.cb.system.service.ISysUserService;
import com.cb.task.service.IBizTaskInfoService;
import com.cb.task.vo.TaskInfoVo;
import com.cb.worksituation.domain.WorkSituation;
import com.cb.worksituation.service.IWorkSituationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BizIndividualAssessmentTaskServiceImpl implements IBizIndividualAssessmentTaskService {

    @Autowired
    private BizIndividualAssessmentTaskMapper individualTaskMapper;
    @Autowired
    private BizAssessIndicatorProMapper proMapper;
    @Autowired
    private BizAssessIndicatorIsmMapper ismMapper;
    @Autowired
    private ISysDictTypeService dictTypeService;
    @Autowired
    private BizAssessIndicatorMapper indicatorMapper;
    @Autowired
    private BizAssessIndicatorProProcedureMapper procedureMapper;
    @Autowired
    private IBizAssessTaskEvaluateRecordService evaluateRecordService;
    @Autowired
    private BizAssessTaskEvaluateTagMapper evaluateTagMapper;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private BizAssessRegularInfoMapper regularInfoMapper;
    @Autowired
    private BizAssessTaskManageMapper taskManageMapper;
    @Autowired
    private IBizTaskInfoService bizTaskInfoService;
    @Autowired
    private IWorkSituationService workSituationService;
    @Autowired
    private IBizAttachService bizAttachService;


    @Override
    public List<BizIndividualAssessmentTask> selectAllToDoList(BizIndividualAssessmentTask task) {
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        String status = task.getStatus();
        if(StringUtils.isBlank(status)) task.setStatus("0");
        return individualTaskMapper.selectAllToDoTaskList(userId,task);
    }

    @Override
    public ProcedureVo initProcedure(String taskId, String proId) {
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        List<BizAssessIndicatorProProcedure> procedureList = procedureMapper.selectProProcedureByProId(proId);
        ProcedureVo procedureVo = new ProcedureVo();
        procedureVo.setProcedureList(procedureList);
        procedureVo.setEnableSummary(individualTaskMapper.checkEnableSummary(userId, taskId));
        procedureVo.setIsFillIn(individualTaskMapper.isFillIn(userId, taskId));
        procedureVo.setEnableEvaluate(individualTaskMapper.checkEnableEvaluate(userId, taskId));

        BizAssessIndicatorPro pro = proMapper.selectBizAssessIndicatorProById(proId);
        procedureVo.setProWorkTipFileId(pro.getProWorkTipFileId());
        if(StringUtils.isNotBlank(pro.getProWorkTipFileId())){
            List<BizAttach> workTipFileList = bizAttachService.selectVFolderOrFileListByIds(pro.getProWorkTipFileId());
            procedureVo.setProWorkTipFile(workTipFileList);
        } else {
            procedureVo.setProWorkTipFile(Collections.emptyList());
        }

        return procedureVo;
    }


    @Override
//    @EncryptMethod(businessType = EODType.DECRYPT)
    public List<EvaluateVo> selectEvaluateList(BizIndividualAssessmentTask task) {
        String taskId = task.getTaskId();
        if (StringUtils.isBlank(taskId)) {
            throw new RuntimeException("参数异常") ;
        }
        String status = task.getStatus();
        if(StringUtils.isBlank(status)) task.setStatus("0");
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        return individualTaskMapper.selectEvaluateList(taskId, userId,task.getStatus());
    }

    @Override
    public List<EvaluateVo> selectCompleteEvaluateList(BizIndividualAssessmentTask task) {
        String taskId = task.getTaskId();
        if (StringUtils.isBlank(taskId)) {
            throw new RuntimeException("参数异常") ;
        }
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        return individualTaskMapper.selectCompleteEvaluateList(taskId,userId,task.getStatus());
    }

    @Override
    public EvaluateItemVo initEvaluateItemVo(EvaluateItemParamVo paramVo) {
        String tagId = paramVo.getTagId();
        Long userId = paramVo.getUserId();
        String taskId = paramVo.getTaskId();
        String proId = paramVo.getProId();
        BizAssessTaskManage assessTask = taskManageMapper.selectBizAssessTaskManageById(taskId);
        //获取字典键值
        Map<String, String> dictMap = dictTypeService.selectDictDataMapByType("assess_indicators");



        BizAssessIndicatorPro pro = proMapper.selectBizAssessIndicatorProById(proId);
        if(StringUtils.isNotBlank(pro.getProWorkTipFileId())){
            List<BizAttach> workTipFileList = bizAttachService.selectVFolderOrFileListByIds(pro.getProWorkTipFileId());
            pro.setProWorkTipFile(workTipFileList);
        }
        String ismId = pro.getProIsmId();
        BizAssessIndicatorIsm ism = ismMapper.selectBizAssessIndicatorIsmById(ismId);
        List<BizAssessIndicatorIsmConfig> configList = ism.getConfigList();
        // 获取已打分数
        List<BizAssessTaskEvaluateRecord> evaluateRecords = evaluateRecordService.selectBizAssessTaskEvaluateRecordList(new BizAssessTaskEvaluateRecord().setEvaluateTagId(tagId));
        List<EvaluateItemVo.IsmConfig> collect = configList.stream().map(item -> {
            String configId = item.getConfigId();
            EvaluateItemVo.IsmConfig config = new EvaluateItemVo.IsmConfig();
            config.setIsmConfigId(configId);
            config.setIndicatorLabel(dictMap.get(item.getIndicatorType()));
            config.setScoreWeight(item.getScoreWeight());
            String indicatorSnapshot = item.getIndicatorSnapshot(); // 指标快照
            if(StringUtils.isBlank(indicatorSnapshot)){
                List<BizAssessIndicator> indicators = indicatorMapper.selectAllIndicator();
                List<String> indIds = JSON.parseArray(item.getContent(), String.class);
                List<BizAssessIndicator> standards = indicators.stream().filter(o -> indIds.indexOf(o.getIndId()) > -1).collect(Collectors.toList());
                config.setIndicators(standards);
            }else{
                List<BizAssessIndicator> standards = JSON.parseArray(indicatorSnapshot, BizAssessIndicator.class);
                config.setIndicators(standards);
            }

            if(CollectionUtil.isNotEmpty(evaluateRecords)){
                BizAssessTaskEvaluateRecord evaluateRecord = evaluateRecords.stream().filter(e -> configId.equals(e.getIsmConfigId())).findFirst().orElseThrow(()->new NullPointerException("未匹配到评分项"));
                config.setScore(evaluateRecord.getScore());
            }
            return config;
        }).collect(Collectors.toList());
        EvaluateItemVo vo = new EvaluateItemVo();
        SysUser user = userService.selectUserById(userId);
        vo.setUser(user);
        vo.setIsmConfigList(collect);
        BizAssessRegularInfo info = regularInfoMapper.selectBizAssessRegularInfoByTaskId(taskId, userId);
        vo.setRegular(info);
        vo.setPro(pro);
        if(Special.NORMAL.getCode().equals(assessTask.getSpecial())){
            // 处理任务来源
            TaskInfoVo.UserHandle4EvaluationReq req = buildUserHandle4EvaluationReq(userId, assessTask);
            TaskInfoVo.UserHandle4EvaluationResp userHandle4Evaluation = bizTaskInfoService.getUserHandle4Evaluation(req);
            vo.setUserHandle4EvaluationResp(userHandle4Evaluation);
        }
        return vo;
    }

    private TaskInfoVo.UserHandle4EvaluationReq buildUserHandle4EvaluationReq(Long userId, BizAssessTaskManage task) {
        TaskInfoVo.UserHandle4EvaluationReq req = new TaskInfoVo.UserHandle4EvaluationReq();
        req.setUserId(userId);
        String cycle = task.getAssessCycle();
        String start = null;
        String end = null;
        if ("1".equals(cycle)) { // 月度考核
            String taskMoth = task.getTaskMoth();
            DateTime parse = DateUtil.parse(taskMoth, "yyyy-MM");
            DateTime startTime = DateUtil.beginOfMonth(parse);
            DateTime endTime = DateUtil.endOfMonth(parse);
            start = DateUtil.formatDateTime(startTime);
            end = DateUtil.formatDateTime(endTime);
        } else if ("2".equals(cycle)) { //季度考核
            String taskQuarter = task.getTaskQuarter();
            Date parse = DateUtil.parse(taskQuarter, "yyyy-MM-dd");
            DateTime startTime = DateUtil.beginOfQuarter(parse);
            DateTime endTime = DateUtil.endOfQuarter(parse);
            start = DateUtil.formatDateTime(startTime);
            end = DateUtil.formatDateTime(endTime);
        } else if ("3".equals(cycle)) { // 年度考核
            String taskYear = task.getTaskYear();
            Date parse = DateUtil.parse(taskYear, "yyyy");
            DateTime startTime = DateUtil.beginOfYear(parse);
            DateTime endTime = DateUtil.endOfYear(parse);
            start = DateUtil.formatDateTime(startTime);
            end = DateUtil.formatDateTime(endTime);
        } else {

        }
        req.setBeginTime(start);
        req.setEndTime(end);
        return req;
    }

    @Override
    public BizAssessRegularInfo initRegularTask(String taskId) {
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        BizAssessRegularInfo regular = individualTaskMapper.initRegularTask(userId, taskId);
        BizAssessTaskManage manage = taskManageMapper.selectBizAssessTaskManageById(taskId);
        String belongYear = manage.getBelongYear();
        String cycle = manage.getAssessCycle();
        Integer start = null;
        Integer end = null;
        if ("1".equals(cycle)) { // 月度考核
            String taskMoth = manage.getTaskMoth();
            DateTime parse = DateUtil.parse(taskMoth, "yyyy-MM");
            DateTime startTime = DateUtil.beginOfMonth(parse);
            start = startTime.month();
        } else if ("2".equals(cycle)) { //季度考核
            String taskQuarter = manage.getTaskQuarter();
            Date parse = DateUtil.parse(taskQuarter, "yyyy-MM-dd");
            DateTime startTime = DateUtil.beginOfQuarter(parse);
            DateTime endTime = DateUtil.endOfQuarter(parse);
            start = startTime.monthBaseOne();
            end = endTime.monthBaseOne();
        }
        WorkSituation workSituation = workSituationService.selectAssessWorkSituation(userId, belongYear, start, end, cycle);
        regular.setWorkSituation(workSituation);
        return regular;
    }


    @Override
    @Transactional
    public int scoring(EvaluateRecordParamVo param) {
        String evaluateTagId = param.getEvaluateTagId();
        List<EvaluateRecordParamVo.EvaluateRecord> evaluateList = param.getEvaluateList();
        List<BizAssessTaskEvaluateRecord> evaluateRecords = evaluateRecordService.selectBizAssessTaskEvaluateRecordList(new BizAssessTaskEvaluateRecord().setEvaluateTagId(evaluateTagId));

        if(CollectionUtil.isNotEmpty(evaluateRecords)){
            evaluateRecords.forEach(o->{
                String ismConfigId = o.getIsmConfigId();
                EvaluateRecordParamVo.EvaluateRecord submitEvaluateRecord = evaluateList.stream().filter(e -> ismConfigId.equals(e.getIsmConfigId())).findFirst().orElseThrow(() -> new NullPointerException("为获取到提交的打分数据"));
                o.setScore(submitEvaluateRecord.getScore());
                evaluateRecordService.updateBizAssessTaskEvaluateRecord(o);
            });
            return 1;
        }else{
            List<BizAssessTaskEvaluateRecord> records = new ArrayList<>();
            for (EvaluateRecordParamVo.EvaluateRecord record : evaluateList) {
                BizAssessTaskEvaluateRecord evaluateRecord = new BizAssessTaskEvaluateRecord();
                evaluateRecord.setEvaluateId(IdUtils.randomUUID());
                evaluateRecord.setEvaluateTagId(evaluateTagId);
                evaluateRecord.setIsmConfigId(record.getIsmConfigId());
                evaluateRecord.setScore(record.getScore());
                evaluateRecord.setCreateBy(SecurityUtils.getUsername());
                evaluateRecord.setCreateTime(DateUtils.getNowDate());
                records.add(evaluateRecord);
            }
            if (CollectionUtil.isNotEmpty(records)) {
                evaluateRecordService.batchInsertBizAssessTaskEvaluateRecord(records);
            }
            return evaluateTagMapper.changeEvaluateTagStatus(evaluateTagId, "1");
        }
    }

    @Override
    public int batchScoring(EvaluateBatchParamVo param) {
        String proId = param.getProId();
        BizAssessIndicatorPro pro = proMapper.selectBizAssessIndicatorProById(proId);
        String ismId = pro.getProIsmId();
        BizAssessIndicatorIsm ism = ismMapper.selectBizAssessIndicatorIsmById(ismId);
        List<BizAssessIndicatorIsmConfig> configList = ism.getConfigList();
        Integer proScore = pro.getScore();
        List<EvaluateBatchParamVo.Record> evaluateList = param.getEvaluateList();
        List<BizAssessTaskEvaluateRecord> records = new ArrayList<>();
        for (EvaluateBatchParamVo.Record record : evaluateList) {
            BigDecimal totalScore = record.getScoring().setScale(2, BigDecimal.ROUND_DOWN);
            BigDecimal totalDelScore = BigDecimal.valueOf(proScore).subtract(totalScore).setScale(2, BigDecimal.ROUND_DOWN);//总的需要减去的分数
            BigDecimal avgDelScore = totalDelScore.divide(BigDecimal.valueOf( configList.size()),2,BigDecimal.ROUND_UP);//每项平均下来需要减去的分数
            BigDecimal needDelScore = BigDecimal.valueOf(proScore).subtract(totalScore).setScale(2, BigDecimal.ROUND_DOWN);//当前剩余需要减去的分数
            for (int i = 0; i < configList.size(); i++) {
                BizAssessIndicatorIsmConfig config = configList.get(i);
                BizAssessTaskEvaluateRecord evaluateRecord = new BizAssessTaskEvaluateRecord();
                evaluateRecord.setEvaluateId(IdUtils.randomUUID());
                evaluateRecord.setEvaluateTagId(record.getTagId());
                evaluateRecord.setIsmConfigId(config.getConfigId());
                Integer scoreWeight = config.getScoreWeight();
                evaluateRecord.setCreateBy(SecurityUtils.getUsername());
                evaluateRecord.setCreateTime(DateUtils.getNowDate());
                // totalScore * scoreWeight / proScore
                if(i != configList.size() - 1){
                    //还没扣完的话
                    if(needDelScore.compareTo(BigDecimal.ZERO) > 0){
                        //需要减去的小于平均减去的值
                        // TODO 目前没考虑扣的分均分下来后超过该项总分的情况
                        if(needDelScore.compareTo(avgDelScore) < 0){
                            BigDecimal score = BigDecimal.valueOf(scoreWeight).subtract(needDelScore);
                            evaluateRecord.setScore(score);
                            needDelScore = needDelScore.subtract(needDelScore);
                        } else{
                            BigDecimal score = BigDecimal.valueOf(scoreWeight).subtract(avgDelScore);
                            evaluateRecord.setScore(score);
                            needDelScore = needDelScore.subtract(avgDelScore);
                        }
                    } else {
                        evaluateRecord.setScore(BigDecimal.valueOf(scoreWeight));
                    }

                } else {
                    evaluateRecord.setScore(BigDecimal.valueOf(scoreWeight).subtract(needDelScore));
                }
                records.add(evaluateRecord);
            }
        }
        if (CollectionUtil.isNotEmpty(records)) {
            evaluateRecordService.batchInsertBizAssessTaskEvaluateRecord(records);
        }
        List<String> ids = evaluateList.stream().map(EvaluateBatchParamVo.Record::getTagId).collect(Collectors.toList());
        return evaluateTagMapper.batchChangeEvaluateTagStatus(ids, "1");
    }

    @Override
    public List<BizIndividualAssessmentTask> listTaskGrade(BizIndividualAssessmentTask task) {
        Long deptId = SecurityUtils.getLoginUser().getUser().getDeptId();

        // 部门负责人
        if (SecurityUtils.hasForceRole("dept_master_leader")) {
            task.setType("_other");
            return individualTaskMapper.listTaskGrade(task, deptId, false);
        }
        //机关党委（人事处）
        if (SecurityUtils.hasForceRole("organ")) {
            task.setType("_other");
            return individualTaskMapper.listTaskGrade(task, null, true);
        }
        // 系统管理员获取所有
        if (SecurityUtils.hasForceRole("admin")) {
            return individualTaskMapper.listTaskGrade(task, null, false);
        }
        return Collections.emptyList();
    }

    @Override
    public List<BizAssessTaskVo> list4TimedReminderTask() {
        return individualTaskMapper.list4TimedReminderTask();
    }
}
