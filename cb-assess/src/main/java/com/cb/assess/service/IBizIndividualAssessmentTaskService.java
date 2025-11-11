package com.cb.assess.service;

import com.cb.assess.domain.BizAssessIndicatorProProcedure;
import com.cb.assess.domain.BizAssessRegularInfo;
import com.cb.assess.domain.BizAssessTaskEvaluateRecord;
import com.cb.assess.domain.BizIndividualAssessmentTask;
import com.cb.assess.domain.vo.*;
import com.cb.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IBizIndividualAssessmentTaskService {

    public List<BizIndividualAssessmentTask> selectAllToDoList(BizIndividualAssessmentTask task);

    public ProcedureVo initProcedure(String taskId, String proId);


    /**
     * 获取未评
     * @param task
     * @return
     */
    public List<EvaluateVo> selectEvaluateList(BizIndividualAssessmentTask task);

    /**
     * 获取已评
     * @param task
     * @return
     */
    public List<EvaluateVo> selectCompleteEvaluateList(BizIndividualAssessmentTask task);



    public EvaluateItemVo initEvaluateItemVo(EvaluateItemParamVo vo);


    public BizAssessRegularInfo initRegularTask(String taskId);

    public int scoring(EvaluateRecordParamVo param);

    public int batchScoring(EvaluateBatchParamVo param);

    public List<BizIndividualAssessmentTask> listTaskGrade(BizIndividualAssessmentTask task);



    public List<BizAssessTaskVo> list4TimedReminderTask();
}
