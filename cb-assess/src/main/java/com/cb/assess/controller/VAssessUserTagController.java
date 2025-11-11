package com.cb.assess.controller;

import com.cb.assess.domain.VAssessUserTag;
import com.cb.assess.service.VAssessUserTagService;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/assess/vAssessUserTag")
public class VAssessUserTagController extends BaseController {

    @Autowired
    private VAssessUserTagService userTagService;

    @GetMapping("/list")
    public TableDataInfo list(VAssessUserTag tag){
        startPage();
        List<VAssessUserTag> list = userTagService.selectVAssessUserTagList(tag);
        return getDataTable(list);
    }

    @GetMapping("/voteStatusList/{taskId}/{userId}")
    public AjaxResult voteStatusList(@PathVariable String taskId, @PathVariable Long userId){
        if(StringUtils.isBlank(taskId) || userId == null){
            return AjaxResult.error("参数异常");
        }
        List<Map<String, Object>> mapList = userTagService.selectVoteStatusList(taskId, userId);
        return AjaxResult.success(mapList);
    }

}
