package com.cb.assess.mapper;

import com.cb.assess.domain.VEvaluateScore;
import com.cb.assess.domain.vo.BizAssessTaskVo;
import com.cb.assess.domain.vo.EvaluationGradeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VEvaluateScoreMapper {

    public List<BizAssessTaskVo> selectTaskList(@Param("et") BizAssessTaskVo vo,@Param("deptId") Long deptId);

    public List<VEvaluateScore.VoterDetail> selectEvaluateVoterList(@Param("taskId") String taskId,@Param("deptId") Long deptId);

    public List<BizAssessTaskVo.EvaluateLevelTempResp> selectEvaluateLevelTempList(BizAssessTaskVo.EvaluateLevelTempReq req);


    public List<BizAssessTaskVo.EvaluateLevelTempResp> selectTempEvaluateScoreList(BizAssessTaskVo.EvaluateLevelTempReq req);


    public List<BizAssessTaskVo.ExportEvaluateComposite> selectExportCompositeList(BizAssessTaskVo.EvaluateLevelTempReq req);


}
