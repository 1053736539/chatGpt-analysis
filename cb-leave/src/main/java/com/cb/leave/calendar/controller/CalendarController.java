package com.cb.leave.calendar.controller;

import cn.hutool.json.JSONArray;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.utils.CalendarUtils;
import com.cb.leave.calendar.service.ICalendarService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

@RestController
@RequestMapping("/Calendar")
public class CalendarController extends BaseController {
    @Resource
    private ICalendarService calendarService;


    @PostMapping("/saveData")
    public AjaxResult saveData() {
        calendarService.saveCalendarForNetwork(2023, 12);
        return null;
    }

    /**
     * 计算工作日
     *
     * @param start 2024-10-10
     * @param end   2024-10-15
     * @param leaveTypeId 请假类型id
     * @return
     */
    @GetMapping("/getWorkDays")
    public AjaxResult getCalendarData(String start, String end, Long leaveTypeId) {
          return AjaxResult.success(calendarService.calcWorkDays(start, end, leaveTypeId));

//        Integer workDays = 1;
//        Calendar currentDate = Calendar.getInstance();
//        Calendar endDate = Calendar.getInstance();
//        try {
//            currentDate.setTime(DateUtils.parseDate(start, DateUtils.YYYY_MM_DD));
//            endDate.setTime(DateUtils.parseDate(end, DateUtils.YYYY_MM_DD));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        while (currentDate.before(endDate)) {
//            //每周第一天是星期天
//            if (currentDate.get(Calendar.DAY_OF_WEEK) == 1 || currentDate.get(Calendar.DAY_OF_WEEK) == 7) {
//                // 跳过周末
//                currentDate.add(Calendar.DATE, 1);
//                continue;
//            }
//            workDays++;
//            currentDate.add(Calendar.DATE, 1);
//        }
//        return AjaxResult.success(workDays);
    }

    /**
     * 下载百度的日历数据
     * @param request
     * @param response
     * @param year
     * @param month
     * @throws IOException
     */
    @GetMapping("/downloadJsonByYear")
    public void downloadJsonByYear(HttpServletRequest request, HttpServletResponse response,
                                   Integer year, Integer month) throws IOException {
        if(null == year){
            throw new IllegalArgumentException("请选择年份");
        }
        JSONArray jsonArray = new JSONArray();
        String fileName = null == month ?  year + ""  : year + "-" + month;
        fileName = "Calendar_" + fileName + ".json";
        if(null == month){
            //一次会返回当月以及上下月的数据，所以 2, 5, 8, 11 月即可
            for (int i = 2; i < 12; i+=3) {
                JSONArray monthArr = CalendarUtils.queryCalendarList(year, i);
                jsonArray.addAll(monthArr);
            }
        } else if(month < 1 || month > 12){
            throw new IllegalArgumentException("月份不正确！");
        } else {
            JSONArray monthArr = CalendarUtils.queryCalendarList(year, month);
            jsonArray.addAll(monthArr);
        }

        System.out.println(jsonArray.size());

        // 将 JSONArray 转换为字符串
        String jsonString = jsonArray.toString();
        // 将字符串转换为字节数组
        byte[] jsonBytes = jsonString.getBytes(StandardCharsets.UTF_8);

        // 设置响应头
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setContentLength(jsonBytes.length);

        // 获取响应输出流
        OutputStream outputStream = response.getOutputStream();
        // 将字节数组写入输出流
        outputStream.write(jsonBytes);
        // 刷新输出流
        outputStream.flush();
        // 关闭输出流
        outputStream.close();
    }

    @PostMapping("importJSON")
    public AjaxResult importJSON(HttpServletRequest httpServletRequest){
        try {
            if(httpServletRequest instanceof MultipartHttpServletRequest){
                MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
                Iterator<String> fileNames = request.getFileNames();
                if (fileNames.hasNext()) {
                    String fileName = fileNames.next();
                    MultipartFile file = request.getFile(fileName);
                    if (file != null) {
                        try( BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));){
                            StringBuilder content = new StringBuilder();
                            String line;
                            while ((line = reader.readLine()) != null) {
                                content.append(line);
                            }
                            JSONArray jsonArray = new JSONArray(content.toString());
                            int i = calendarService.importJSONArray(jsonArray);
                            return AjaxResult.success("共导入" + i + "条日历数据！");
                        }
                    }
                }
                return AjaxResult.success();
            }
            throw new RuntimeException("未检测到文件");
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/countCalendar")
    public AjaxResult countCalendar(Integer year, Integer month){
        if(null == year){
            throw new IllegalArgumentException("请选择年份");
        }
        Integer count = calendarService.countCalendar(year, month);
        return AjaxResult.success(count);
    }

}
