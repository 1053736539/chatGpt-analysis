package com.cb.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author ruoyi
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils
{

    private static final SimpleDateFormat format_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat format_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static String[] parsePatterns = {
            "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd",
            "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd",
            "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd",
            "yyyy年MM月dd日 HH时mm分ss秒","yyyy年MM月dd日","yyyy年MM月dd",
            "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd HH:mm", "yyyy.MM",
            "yyyy年MM月dd日 HH时mm分", "yyyy年MM月","yyyy年MM",
            "yyyy",
            "yyyy年",
    };

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate()
    {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate()
    {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime()
    {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow()
    {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format)
    {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date)
    {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date)
    {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts)
    {
        try
        {
            return new SimpleDateFormat(format).parse(ts);
        }
        catch (ParseException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str)
    {
        if (str == null)
        {
            return null;
        }
        try
        {
            return parseDate(str.toString(), parsePatterns);
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate()
    {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate)
    {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     *
     * @param endDate
     * @param nowDate
     * @param format nd 天; nh 小时 ;nm 分钟 ;ns 秒
     * @return
     */
    public static Long getDatePoor(Date endDate, Date nowDate, String format) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        long diff = endDate.getTime() - nowDate.getTime();
        if ("nd".equals(format)) {
            long day = diff / nd;
            return day;
        }
        if("nh".equals(format)){
            long hour = diff % nd / nh;
            return hour;
        }
        if("nm".equals(format)){
            // 计算差多少分钟
            long min = diff % nd % nh / nm;
            return min;
        }
        if("ns".equals(format)){
            // 计算差多少秒
            long sec = diff % nd % nh % nm / ns;
            return sec;
        }
        return diff;
    }

    /**
     * 获取倒计时（分：秒）
     * @param diff 时间戳
     * @return
     */
    public static String getCountdownTime(Long diff) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        long min = diff % nd % nh / nm;
        long sec = diff % nd % nh % nm / ns;
        return  + min + "分"+sec+"秒";
    }

    /**
     * 获取未来 第 past 天的日期
     * @param past
     * @return
     */
    public static Date getFutureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        return today;
    }

    /**
     * 以毫秒表示的时间
     */
    private static final long DAY_IN_MILLIS = 24 * 3600 * 1000;
    private static final long HOUR_IN_MILLIS = 3600 * 1000;
    private static final long MINUTE_IN_MILLIS = 60 * 1000;
    private static final long SECOND_IN_MILLIS = 1000;

    /**
     * 指定日历的毫秒数
     *
     * @param cal 指定日历
     * @return 指定日历的毫秒数
     */
    public static long getMillis(Calendar cal) {
        return cal.getTime().getTime();
    }

    public static int dateDiff(char flag, Date dateSrc, Date dateDes) {
        return dateDiff(flag, toCalendar(dateSrc), toCalendar(dateDes));
    }

    /**
     * 计算两个时间之间的差值，根据标志的不同而不同
     *
     * @param flag   计算标志，表示按照年/月/日/时/分/秒等计算
     * @param calSrc 减数
     * @param calDes 被减数
     * @return 两个日期之间的差值
     */
    public static int dateDiff(char flag, Calendar calSrc, Calendar calDes) {

        long millisDiff = getMillis(calSrc) - getMillis(calDes);
        char year = 'y';
        char day = 'd';
        char hour = 'h';
        char minute = 'm';
        char second = 's';
        char month = 'M';

        if (flag == year) {
            return (calSrc.get(Calendar.YEAR) - calDes.get(Calendar.YEAR));
        }

        if (flag == month) {
            return (int) ((millisDiff / DAY_IN_MILLIS)/30);
        }

        if (flag == day) {
            return (int) (millisDiff / DAY_IN_MILLIS);
        }

        if (flag == hour) {
            return (int) (millisDiff / HOUR_IN_MILLIS);
        }

        if (flag == minute) {
            return (int) (millisDiff / MINUTE_IN_MILLIS);
        }

        if (flag == second) {
            return (int) (millisDiff / SECOND_IN_MILLIS);
        }

        return 0;
    }

    /**
     * 日期格式字符串标准化转换
     * @param source 原始字符串
     * @param keepOnFail 解析失败保持返回source还是返回null
     * @param format 要转成的日期字符串格式，不写默认 yyyy-MM-dd
     * @return
     */
    public static String dateStdFormat(String source,boolean keepOnFail,String... format){
        Date date = parseDate(source);
        if(date == null){
            return keepOnFail ? source : null;
        }
        SimpleDateFormat sdf = format_YYYY_MM_DD;
        if(format.length > 0){
            if("yyyy-MM-dd".equals(format[0])) {
                // 默认的
            } else if("yyyy-MM-dd HH:mm:ss".equals(format[0])){
                // 比较常用的
                sdf = format_YYYY_MM_DD_HH_MM_SS;
            } else {
                sdf = new SimpleDateFormat(format[0]);
            }
        }
        return sdf.format(date);
    }

}
