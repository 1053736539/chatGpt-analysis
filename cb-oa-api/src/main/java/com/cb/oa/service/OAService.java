package com.cb.oa.service;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cb.oa.vo.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author ARPHS
 * @Date 2023/11/30 11:58
 */
@Service
public class OAService {

    @Value("${oa.baseUrl}")
    private String base;

    /***
     * GET 分页查询用户接口
     * @param page 当前页 大于等于 1
     * @param size 每页显示条数 1-100 之间
     * @return
     */
    public TableResp<UserItem> pageUser(int page, int size){
        String url = String.format("/tj/user/%d/%d",page,size);
        String respStr = HttpUtil.get(base + url);

//        ClassPathResource classPathResource = new ClassPathResource("/resp_user.json");
        try {
//            InputStream inputStream = classPathResource.getInputStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
//            StringBuilder stringBuilder = new StringBuilder();
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                stringBuilder.append(line);
//            }
            TableResp<UserItem> resp = JSONObject.parseObject(respStr, new TypeReference<TableResp<UserItem>>() {});
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
//        TableResp<UserItem> resp = JSONObject.parseObject(respStr, new TypeReference<TableResp<UserItem>>() {});
//        return resp;
    }

    /**
     * GET 查询部门接口
     * @return
     */
    public ListResp<DeptItem> listDept(){
        String url = "/tj/dept";
        String respStr = HttpUtil.get(base + url);
        ListResp<DeptItem> resp = JSONObject.parseObject(respStr, new TypeReference<ListResp<DeptItem>>() {});
        return resp;
    }

    /**
     * POST 获取考勤记录
     * @param clockInDate 查询月份,格式为 YYYY-MM 的字符串
     * @return
     */
    public ListResp<ClockItem> listClock(String clockInDate){
        String url = "/tj/clock";
        JSONObject body = new JSONObject();
        body.put("clockInDate",clockInDate);
        String respStr = HttpUtil.post(base + url,body.toJSONString());
        ListResp<ClockItem> resp = JSONObject.parseObject(respStr, new TypeReference<ListResp<ClockItem>>() {});
        return resp;
    }

}
