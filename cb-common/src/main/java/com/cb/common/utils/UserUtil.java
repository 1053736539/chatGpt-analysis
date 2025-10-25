package com.cb.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.cb.common.core.domain.model.LoginUser;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

/**
 * @author: hu
 * @date: 2023年11月08日
 * @description:
 */
public  class UserUtil<T> {

    public static <T> T created(T t ){
        LoginUser user = SecurityUtils.getLoginUser();
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        JSONObject s = JSONObject.parseObject(t.toString());
        s.put("createUserId",userId); // user.getUserId()
        s.put("createUser",user.getUsername());
        s.put("modifyTime", LocalDateTime.now());
        t = JSONObject.parseObject(JSONObject.toJSONString(s), (Type) t.getClass());
        return t;
    }

    public static <T> T update(T t){
        LoginUser user = SecurityUtils.getLoginUser();
        JSONObject s = JSONObject.parseObject(JSONObject.toJSONString(t));
        s.put("modifyUser",user.getUsername());
        s.put("modifyTime", LocalDateTime.now());
        t = JSONObject.parseObject(JSONObject.toJSONString(s), (Type) t.getClass());

        return t;
    }

}
