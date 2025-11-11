package com.cb.api.controller;

import com.cb.api.service.ApiService;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/app/v1")
public class ApiController extends BaseController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/appHomePageCount")
    public AjaxResult appHomePageCount(){
        HashMap<String, Object> map = new HashMap<>();
        int toDoNum = apiService.countAssessToDoNum();
        map.put("toDoNum",toDoNum);
        int disciplineNum = apiService.countDisciplineScoringNum();
        map.put("disciplineNum",disciplineNum);
        int partyCommitteeNum = apiService.countPartyCommitteeNum();
        map.put("partyCommitteeNum",partyCommitteeNum);

        //任务
        int taskMyTodoNum = apiService.countTaskMyTodoNum();
        int taskMyUnClaimNum = apiService.countTaskMyUnClaimNum();
        int taskMyExpireNum = apiService.countTaskMyExpireNum();
        map.put("taskMyTodoNum",taskMyTodoNum);
        map.put("taskMyUnClaimNum",taskMyUnClaimNum);
        map.put("taskMyExpireNum",taskMyExpireNum);

        //政治素质考察
        int qualityCountMyTask= apiService.selectQualityCountMyTask();
        map.put("qualityCountMyTask",qualityCountMyTask);

        //优秀推荐互评
        int myExcellentEvaluationCount = apiService.selectMyExcellentEvaluationCount();
        map.put("myExcellentEvaluationCount",myExcellentEvaluationCount);
        //民主测评待填
        int countMyDemocratic = apiService.selectCountMyDemocratic();
        map.put("countMyDemocratic",countMyDemocratic);
        //AB类表
        int aTypeCount = apiService.selectAnnualReviewTypeACount();
        int bTypeCount = apiService.selectAnnualReviewTypeBCount();
        map.put("aTypeCount",aTypeCount);
        map.put("bTypeCount",bTypeCount);
        return AjaxResult.success(map);
    }

}
