package com.cb.worksituation.task;

import com.cb.worksituation.service.IWorkSituationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;

@Component("workTask")
public class WorkTask {
    @Autowired
    private IWorkSituationService workSituationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkTask.class);
    public void syncClock()
    {
        try{
            LocalDate today = LocalDate.now(); // 获取当前日期
            Month currentMonth = today.getMonth(); // 获取当前月份
            int year = today.getYear();
            System.out.println(currentMonth); // 输出当前月份
            Integer integer = workSituationService.synchronousClock(String.valueOf(year), String.valueOf(currentMonth.getValue()));
            LOGGER.info("同步本月数据共计",integer);
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -1);
            int yearLast = cal.get(Calendar.YEAR);
            int monthLast = cal.get(Calendar.MONTH) + 1;  // 注意月份是从0开始的，所以需要加1
            Integer integer1 = workSituationService.synchronousClock(String.valueOf(yearLast), String.valueOf(monthLast));
            LOGGER.info("同步上月月数据共计",integer1);
        }catch (Exception e){
            LOGGER.error("同步发生错误",e.getMessage());
            e.printStackTrace();
        }
    }
}
