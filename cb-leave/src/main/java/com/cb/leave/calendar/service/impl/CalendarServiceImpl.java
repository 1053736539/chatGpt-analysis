package com.cb.leave.calendar.service.impl;

import cn.hutool.json.JSONArray;
import com.cb.common.utils.CalendarUtils;
import com.cb.leave.calendar.domain.Calendar;
import com.cb.leave.calendar.mapper.CalendarMapper;
import com.cb.leave.calendar.service.ICalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalendarServiceImpl implements ICalendarService
{
    @Autowired
    private CalendarMapper calendarMapper;

    /**
     * 查询日历
     *
     * @param id 日历ID
     * @return 日历
     */
    @Override
    public Calendar selectCalendarById(Integer id)
    {
        return calendarMapper.selectCalendarById(id);
    }

    /**
     * 查询日历列表
     *
     * @param calendar 日历
     * @return 日历
     */
    @Override
    public List<Calendar> selectCalendarList(Calendar calendar)
    {
        return calendarMapper.selectCalendarList(calendar);
    }

    /**
     * 根据id区间查询日历
     * @param start
     * @param end
     * @return
     */
    @Override
    public List<Calendar> selectCalendarListByInterval(Integer start,Integer end)
    {
        return calendarMapper.selectCalendarListByInterval(start,end);
    }

    /**
     * 新增日历
     *
     * @param calendar 日历
     * @return 结果
     */
    @Override
    public int insertCalendar(Calendar calendar)
    {
        return calendarMapper.insertCalendar(calendar);
    }

    /**
     * 修改日历
     *
     * @param calendar 日历
     * @return 结果
     */
    @Override
    public int updateCalendar(Calendar calendar)
    {
        return calendarMapper.updateCalendar(calendar);
    }

    /**
     * 批量删除日历
     *
     * @param ids 需要删除的日历ID
     * @return 结果
     */
    @Override
    public int deleteCalendarByIds(Integer[] ids)
    {
        return calendarMapper.deleteCalendarByIds(ids);
    }

    /**
     * 删除日历信息
     *
     * @param id 日历ID
     * @return 结果
     */
    @Override
    public int deleteCalendarById(Integer id)
    {
        return calendarMapper.deleteCalendarById(id);
    }

    @Override
    public List<Calendar> saveCalendarForNetwork(Integer year, Integer month) {
        JSONArray objects = CalendarUtils.queryCalendarList(year, month);
        List<Calendar> list = objects.toList(Calendar.class);
        List<Calendar> collect = list.stream().map(e -> {
            String month1 = e.getMonth()+"";
            String day = e.getDay()+"";
            if (month1.length()==1) {
                month1="0" + e.getMonth();
            }
            if (day.length() == 1) {
                day="0" + e.getDay();
            }
            String id = e.getYear() + month1 + day;
            e.setId(Integer.valueOf(id));
            return e;
        }).collect(Collectors.toList());
        int i = calendarMapper.insertCalendarBatch(collect);
        return collect;
    }

    @Override
    public int calcWorkDays(String start, String end, Long leaveTypeId) {
        //格式化时间
        String startNumStr = start.replace("-", "");
        String endNumStr = end.replace("-", "");
        //时间转int
        int startInt = Integer.parseInt(startNumStr);
        int endInt = Integer.parseInt(endNumStr);
        List<Calendar> calendars = selectCalendarListByInterval(startInt, endInt);
        //进行是否补全数据判断
        Boolean need=calendars == null || calendars.size() == 0;
        if(!need){
            //另外，两端数据不全也进行请求
            Integer id1 = calendars.get(0).getId();
            Integer id2 = calendars.get(calendars.size()-1).getId();
            need= (startInt!=id1)&&(endInt!=id2);
        }
        if (need) {
            //无数据
            //进行数据请求
            String[] startSplit = start.split("-");
            String[] endSplit = end.split("-");
            //计算两个日期之间的包含月份
            int startMonth = Integer.parseInt(startSplit[1]);
            int endMonth = Integer.parseInt(endSplit[1]);
            int startYear=Integer.parseInt(startSplit[0]);
            int endYear=Integer.parseInt(endSplit[0]);
            //得到月份差
            int monthDifference =(endYear-startYear)*12+ endMonth-startMonth;
            for (int i = 0; i <= monthDifference; i++) {
                int m=startMonth+i;
                int y= m>12?startYear+1:startYear;
                m=m>12?m-12:m;
                saveCalendarForNetwork(y, m);
            }
            //最后再查询一次
            calendars = selectCalendarListByInterval(startInt, endInt);
        }

        Integer workDays = 0;
        // 公休、事假、病假、丧假、补休，需要在假期天数排除周末和法定节假日
        // 1-公休假 2-病假 3-事假 6-丧假 11-补休
        if(null != leaveTypeId &&
                ( leaveTypeId.equals(1L) || leaveTypeId.equals(2L)
                || leaveTypeId.equals(3L) || leaveTypeId.equals(6L) || leaveTypeId.equals(11L) )){
            //计算，如果status为1，则是休假，2是补班，其他情况按照正常周末放假走
            for (Calendar calendar : calendars) {
                if ("1".equals(calendar.getStatus())) {
                    continue;
                } else if ("2".equals(calendar.getStatus())) {
                    workDays += 1;
                } else {
                    if ("六".equals(calendar.getCnDay())|| "日".equals(calendar.getCnDay()))continue;
                    //不在放假和补班的日期，按照正常周末双休工作日计算
                    workDays += 1;
                }
            }
        } else {
            workDays = calendars.size();
        }
        return workDays;
    }


    @Transactional
    @Override
    public int importJSONArray(JSONArray jsonArray) {
        List<Calendar> list = jsonArray.toList(Calendar.class);
        List<Calendar> collect = list.stream().map(e -> {
            String month1 = e.getMonth()+"";
            String day = e.getDay()+"";
            if (month1.length()==1) {
                month1="0" + e.getMonth();
            }
            if (day.length() == 1) {
                day="0" + e.getDay();
            }
            String id = e.getYear() + month1 + day;
            e.setId(Integer.valueOf(id));
            return e;
        }).collect(Collectors.toList());
        // 分批插入
        int batchSize = 50;
        int totalInserted = 0;
        for (int start = 0; start < collect.size(); start += batchSize) {
            int end = Math.min(start + batchSize, collect.size());
            List<Calendar> batch = collect.subList(start, end);
            totalInserted += calendarMapper.insertCalendarBatch(batch);
        }
        return totalInserted;
    }

    @Override
    public int countCalendar(Integer year, Integer month) {
        return calendarMapper.countCalendar(year, month);
    }
}