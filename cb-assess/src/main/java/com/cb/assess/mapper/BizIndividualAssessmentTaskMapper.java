package com.cb.assess.mapper;

import com.cb.assess.domain.BizAssessRegularInfo;
import com.cb.assess.domain.BizIndividualAssessmentTask;
import com.cb.assess.domain.VRegularFillInfo;
import com.cb.assess.domain.vo.BizAssessTaskVo;
import com.cb.assess.domain.vo.EvaluateVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BizIndividualAssessmentTaskMapper {

    public List<BizIndividualAssessmentTask> selectAllToDoTaskList(@Param("userId") Long userId,@Param("et")BizIndividualAssessmentTask task);


    public VRegularFillInfo  selectVRegularFillInfoList(@Param("taskId") String taskId,@Param("userId") Long userId);


    public List<EvaluateVo> selectEvaluateList(@Param("taskId") String taskId,@Param("userId") Long userId,@Param("status")String status);

    public List<EvaluateVo> selectCompleteEvaluateList(@Param("taskId") String taskId,@Param("userId") Long userId,@Param("status")String status);

    public boolean checkEnableSummary(@Param("userId") Long userId, @Param("taskId") String taskId);

    public boolean isFillIn(@Param("userId") Long userId, @Param("taskId") String taskId);

    public boolean checkEnableEvaluate(@Param("userId") Long userId, @Param("taskId") String taskId);

    public BizAssessRegularInfo initRegularTask(@Param("userId") Long userId,@Param("taskId")String taskId);


    /**
     *
     * @param task
     * @param deptId
     * @param isExclude 是否排除
     * @return
     */
    public List<BizIndividualAssessmentTask> listTaskGrade(@Param("et") BizIndividualAssessmentTask task,
                                                           @Param("deptId") Long deptId,@Param("isExclude")Boolean isExclude);

    public List<BizAssessTaskVo> list4TimedReminderTask();

    public int countAssessToDoNum(Long userId);
}
