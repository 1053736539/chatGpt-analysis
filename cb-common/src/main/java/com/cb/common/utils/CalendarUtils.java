package com.cb.common.utils;

import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.json.JSONArray;


import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class CalendarUtils {

    /**
     * 查询日历列表，注意：返回结果的oDate采用的时区是0时区
     * @param year 年，如：2023
     * @param month 月，如：2
     * @return 返回当前月，上一个月以及下一个月的日历数据
     */
    public static JSONArray queryCalendarList(int year, int month){
        String url = "https://opendata.baidu.com/api.php";

        //拼接参数
        url = url + "?" + getQueryStr(year, month);

//        log.info("完整请求地址: {}", url);

        //发起调用
        String response = HttpUtil.get(url);

        //可以看到返回的是JSONP
//        log.info("原始响应结果: {}", response);

        //去除多余字符，提取JSON部分(返回结果为JSONP时)
        String jsonStr = response.substring(response.indexOf("{"), response.lastIndexOf("}")+1);


        //解析返回结果
        JSONObject json = JSONUtil.parseObj(jsonStr);

        //仅当状态=0时调用成功
        if(!"0".equals(json.getStr("status"))){
            return null;
        }

        //提取data数组
        JSONArray data = json.getJSONArray("data");
        if(data.isEmpty()){
            return null;
        }

        //取第一个元素
        JSONObject data_0 = data.getJSONObject(0);

        //提取almanac数组并返回
        return data_0.getJSONArray("almanac");
    }

    private static String getQueryStr(int year, int month){
        Map<String,Object> params = new HashMap<>();
        params.put("tn", "wisetpl");
        params.put("format", "json");
        params.put("resource_id", "39043");
        params.put("t", System.currentTimeMillis());
        params.put("cb", "callback"); //不加这个参数返回结果为JSON，加了返回的是JSONP
        params.put("query", year+"年"+month+"月");

        StringBuffer qs = new StringBuffer();
        params.forEach((k, v) -> {
            qs.append(k).append("=").append(v).append("&");
        });

        return URLUtil.encode(qs.deleteCharAt(qs.length()-1).toString(), StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        JSONArray objects = CalendarUtils.queryCalendarList(2024, 01);
//        List<Calendar> list = objects.toList(Calendar.class);
//        List<Calendar> collect = list.stream().map(e -> {
//            String month1 = e.getMonth()+"";
//            String day = e.getDay()+"";
//            if (month1.length()==1) {
//                month1="0" + e.getMonth();
//            }
//            if (day.length() == 1) {
//                day="0" + e.getDay();
//            }
//            String id = e.getYear() + month1 + day;
//            e.setId(Integer.valueOf(id));
//            return e;
//        }).collect(Collectors.toList());
        System.out.println(

        );
    }
}
