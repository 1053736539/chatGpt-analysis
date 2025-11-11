package com.cb.assess.scheduler;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.cb.assess.domain.BizAssessOverallScoreLevelRecord;
import com.cb.assess.domain.BizAssessPromulgate;
import com.cb.assess.domain.vo.BizAssessTaskVo;
import com.cb.assess.service.IBizAssessOverallScoreLevelRecordService;
import com.cb.assess.service.IBizAssessPromulgateService;
import com.cb.assess.service.IBizIndividualAssessmentTaskService;
import com.cb.assess.utils.CycleUtil;
import com.cb.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 考核任务定时处理器
 */
@Component("assessScheduler")
public class AssessScheduler {
    @Autowired
    private IBizIndividualAssessmentTaskService taskService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private IBizAssessPromulgateService promulgateService;
    @Autowired
    private IBizAssessOverallScoreLevelRecordService levelRecordService;

    public void timedReminderBeforeByDay(Integer day) {
        if (null == day || day <= 0) {
            day = 5;
        }
        // 1.获取时间段内的任务
        List<BizAssessTaskVo> tasks = taskService.list4TimedReminderTask();
        tasks.stream().forEach(item -> {
            List<Long> userIds = new ArrayList<>();
            List<BizAssessTaskVo.SummaryUser> summaryUsers = item.getSummaryUsers();
            if (CollectionUtil.isNotEmpty(summaryUsers)) {
                userIds.addAll(summaryUsers.stream().map(BizAssessTaskVo.SummaryUser::getUserId).collect(Collectors.toList()));
            }
            List<BizAssessTaskVo.EvaluationUser> evaluationUsers = item.getEvaluationUsers();
            if (CollectionUtil.isNotEmpty(evaluationUsers)) {
                userIds.addAll(evaluationUsers.stream().map(BizAssessTaskVo.EvaluationUser::getUserId).collect(Collectors.toList()));
            }
            if (CollectionUtil.isNotEmpty(userIds)) {
                sendMessage(item, userIds);
            }
        });
    }

    private void sendMessage(BizAssessTaskVo vo, List<Long> userIds) {

        String title = "考核任务提醒";
        String end = DateUtil.format(vo.getEndTime(), "yyyy-MM-dd HH:mm");
        String message = String.format("您有考核任务:%s【%s】，任务截止时间为:%s，请及时处理。",
                vo.getTaskName(), CycleUtil.parseCycle(vo.getCycle(), vo.getCycleDesc()), end);
        messageService.sendMessage2User(title, message, "admin", userIds);
    }


    /**
     * 平时考核公示时间超过所填写的公示时间后，自动转为正式数据
     */
    public void assessPublicityFinishCronTask() {
        // 获取已公示的平时考核记录
        List<BizAssessPromulgate> list = promulgateService.selectPromulgateList();
        List<BizAssessOverallScoreLevelRecord> levelRecordList = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        list.stream().forEach(item -> {
            String quarter = item.getQuarter();
            Date quarterDate = DateUtil.parse(quarter, "yyyy-MM-dd");
            int year = DateUtil.year(quarterDate);
            String taskId = item.getTaskId();
            if (this.overTime(item)) {
                List<BizAssessOverallScoreLevelRecord> LevelRecords =
                        levelRecordService.selectVEvaluationGradeAsLevelRecords(taskId, String.valueOf(year), quarter);
                levelRecordList.addAll(LevelRecords);
                ids.add(item.getId());
            }
        });
        if(ids.size() > 0){
            promulgateService.batchChangePromulgateStatus(ids);
        }
        if(levelRecordList.size() > 0){
            levelRecordService.batchInsertOrUpdateLevelRecord(levelRecordList);
        }
    }

    private Boolean overTime(BizAssessPromulgate promulgate) {
        //1.获取公示时间
        Date publicityTime = promulgate.getPublicityTime();
        //2.获取公示天数
        Integer publicityDays = promulgate.getPublicityDays();
        //3.判断是否超过公示时间
        DateTime now = DateUtil.date();
        long difference = DateUtil.between(publicityTime, now, DateUnit.DAY);
        if (difference >= publicityDays) {
            return true;
        }
        return false;
    }

}
