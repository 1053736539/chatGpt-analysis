package com.cb.web.controller.diy;

import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.redis.RedisCache;
import com.cb.common.utils.SecurityUtils;
import com.cb.diy.model.res.DiyDebugRes;
import com.cb.diy.model.res.DiyIndicatorRes;
import com.cb.diy.model.res.DiyModelRes;
import com.cb.diy.model.resp.IndicatorData;
import com.cb.diy.model.resp.WarningCacheData;
import com.cb.diy.model.resp.WarningData;
import com.cb.diy.service.DiyEngineService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 流程引擎
 * @author xiehong
 */
@RestController
@RequestMapping("/diy/engine")
public class DiyEngineController extends BaseController {
    private final DiyEngineService diyEngineService;
    private final RedisCache redisCache;

    private static final String WARNING_KEY = "diy:model:warning";

    public DiyEngineController(DiyEngineService diyEngineService, RedisCache redisCache) {
        this.diyEngineService = diyEngineService;
        this.redisCache = redisCache;
    }

    /**
     * 获取启动参数
     */
    @GetMapping("/start/params")
    public AjaxResult getStartParams(Long id) {
        return AjaxResult.success(diyEngineService.getStartParams(id));
    }

    /**
     * @param id
     * @Auther: yangcd
     * @Date: 2024/11/1 18:01
     * @Description: 获取启动参数 getModelStartParams
     */
    @GetMapping("/start/modelParams")
    public AjaxResult getModelStartParams(Long id) {
        return AjaxResult.success(diyEngineService.getModelStartParams(id));
    }

    /**
     * 调试
     */
//    @PreAuthorize("@ss.hasPermi('diy:engine:run')")
    @PostMapping("/run/debug")
    public AjaxResult runDebug(@RequestBody DiyDebugRes diyDebugRes) {
        try {
            IndicatorData indicatorData = diyEngineService.runIndicator(diyDebugRes.getName(), diyDebugRes.getProcess(), diyDebugRes.getVariables());
            return AjaxResult.success(indicatorData);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 运行指标
     */
//    @PreAuthorize("@ss.hasPermi('diy:engine:run')")
    @PostMapping("/run/indicator")
    public AjaxResult runIndicator(@RequestBody DiyIndicatorRes diyDebugRes) {
        try {
            IndicatorData indicatorData = diyEngineService.runIndicator(diyDebugRes.getId(), diyDebugRes.getVariables());
            return AjaxResult.success(indicatorData);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 运行模型
     */
//    @PreAuthorize("@ss.hasPermi('diy:engine:run')")
    @PostMapping("/run/model")
    public AjaxResult runModel(@RequestBody DiyModelRes diyModelRes) {
        try {
            List<WarningData> warningDatas = diyEngineService.runModel(diyModelRes.getModelId(), diyModelRes.getVariables());
            if (diyModelRes.getCache() == null || diyModelRes.getCache()) {
                List<WarningCacheData> warnCacheDatas = new ArrayList<>();
                for (WarningData warnData : warningDatas) {
                    WarningCacheData warnCacheData = new WarningCacheData();
                    warnCacheData.setId(warnData.getId());
                    warnCacheData.setName(warnData.getName());
                    warnCacheData.setTotal(warnData.getTotal());
                    warnCacheData.setParams(diyModelRes.getVariables().get(warnData.getId()));
                    warnCacheDatas.add(warnCacheData);
                }
                redisCache.setCacheObject(WARNING_KEY + ":" + SecurityUtils.getUsername(), warnCacheDatas);
            }
            return AjaxResult.success(warningDatas);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 获取预警列表
     */
    @GetMapping("/warning")
    public AjaxResult getWarning() {
        List<WarningCacheData> listData = redisCache.getCacheObject(WARNING_KEY + ":" + SecurityUtils.getUsername());
        return AjaxResult.success(listData);
    }
}
