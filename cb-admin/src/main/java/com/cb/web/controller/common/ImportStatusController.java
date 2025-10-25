package com.cb.web.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.redis.RedisCache;
import com.cb.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 数据导入状态查询
 * @Author: ARPHS
 * @Date: 2025-01-17 17:47
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/importStatus")
public class ImportStatusController {

    @Autowired
    private RedisCache redisCache;

    /**
     * 导入进度查询
     * @param importId
     * @return
     */
    @GetMapping(value = "/{importId}")
    public AjaxResult getImportStatus(@PathVariable("importId") String importId)
    {
        if(StringUtils.isEmpty(importId)) {
            return AjaxResult.error("导入ID不能为空");
        }
        String[] keys = new String[]{"error", "success", "totalRecord", "totalInserted"};
        JSONObject data = new JSONObject();
        for(String key : keys) {
            Object value = redisCache.getCacheMapValue(importId, key);
            if(value != null) {
                data.put(key, value);
            }
        }
        if(data.containsKey("error") || data.containsKey("success")) {
            redisCache.deleteObject(importId);
        }
        return AjaxResult.success(data);
    }

}
