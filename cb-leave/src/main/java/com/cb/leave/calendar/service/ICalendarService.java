package com.cb.leave.calendar.service;
import cn.hutool.json.JSONArray;
import com.cb.leave.calendar.domain.Calendar;

import java.util.List;

/**
 * 日历Service接口
 *
 * @author ruoyi
 * @date 2024-10-29
 */
public interface ICalendarService
{
    /**
     * 查询日历
     *
     * @param id 日历ID
     * @return 日历
     */
    public Calendar selectCalendarById(Integer id);

    /**
     * 查询日历列表
     *
     * @param calendar 日历
     * @return 日历集合
     */
    public List<Calendar> selectCalendarList(Calendar calendar);

    List<Calendar> selectCalendarListByInterval(Integer start, Integer end);

    /**
     * 新增日历
     *
     * @param calendar 日历
     * @return 结果
     */
    public int insertCalendar(Calendar calendar);

    /**
     * 修改日历
     *
     * @param calendar 日历
     * @return 结果
     */
    public int updateCalendar(Calendar calendar);

    /**
     * 批量删除日历
     *
     * @param ids 需要删除的日历ID
     * @return 结果
     */
    public int deleteCalendarByIds(Integer[] ids);

    /**
     * 删除日历信息
     *
     * @param id 日历ID
     * @return 结果
     */
    public int deleteCalendarById(Integer id);

    List<Calendar> saveCalendarForNetwork(Integer year, Integer month);


    int calcWorkDays(String begin,String end, Long leaveTypeId);

    /**
     * 导入json
     * @param jsonArray
     * @return
     */
    int importJSONArray(JSONArray jsonArray);

    /**
     * 统计条数
     * @param year
     * @param month
     * @return
     */
    int countCalendar(Integer year,Integer month);

}