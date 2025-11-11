package com.cb.assess.mapper;

import com.cb.assess.domain.BizAssessEvaluationGradeSaveMark;
import com.cb.assess.domain.VEvaluationGrade;
import com.cb.assess.domain.vo.EscalationVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EscalationMapper {

    public List<EscalationVo> selectEscalationList(@Param("et") EscalationVo vo,@Param("deptId") Long deptId);

    public List<EscalationVo> selectExamineEscalationList(@Param("et") EscalationVo vo,@Param("deptId") Long deptId);

    public boolean checkReported(@Param("et") EscalationVo.EvaluateCensus evaluateCensus,@Param("deptId") Long deptId);

    public List<EscalationVo.Voter> selectVoterList(@Param("et") EscalationVo.Voter voter, @Param("deptId") Long deptId);

    public List<EscalationVo.Voter> selectVoterListByKeys(@Param("taskIds") List<String> taskIds, @Param("userIds")List<Long> userIds);

    public List<EscalationVo.ReportedEvaluate> selectReportedList(EscalationVo.ReportedEvaluate reported);

    public List<BizAssessEvaluationGradeSaveMark> selectEvaluationGradeSaveMark(EscalationVo.EvaluateCensus evaluateCensus);

    public List<VEvaluationGrade.Voter> selectVEvaluateScoreList(@Param("taskIds") List<String> taskIds, @Param("userIds") List<Long> userIds);
}
