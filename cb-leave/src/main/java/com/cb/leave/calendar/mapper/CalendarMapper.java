package com.cb.leave.calendar.mapper;


import com.cb.leave.calendar.domain.Calendar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【calendar(日历表)】的数据库操作Mapper
* @createDate 2023-09-15 15:40:03
* @Entity com.xong.boot.domain.calendar.Calendar
*/
@Mapper
public interface CalendarMapper  {
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
    public List<Calendar> selectCalendarListByInterval(@Param("start") Integer start,@Param("end") Integer end);

    /**
     * 新增日历
     *
     * @param calendar 日历
     * @return 结果
     */
    public int insertCalendar(Calendar calendar);
    public int insertCalendarBatch(List<Calendar> calendar);

    /**
     * 统计条数
     * @param year
     * @param month
     * @return
     */
    public int countCalendar(@Param("year") Integer year, @Param("month") Integer month);

    /**
     * 修改日历
     *
     * @param calendar 日历
     * @return 结果
     */
    public int updateCalendar(Calendar calendar);

    /**
     * 删除日历
     *
     * @param id 日历ID
     * @return 结果
     */
    public int deleteCalendarById(Integer id);

    /**
     * 批量删除日历
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCalendarByIds(Integer[] ids);
}




