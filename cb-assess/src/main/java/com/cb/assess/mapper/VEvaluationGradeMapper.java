package com.cb.assess.mapper;

import com.cb.assess.domain.BizAssessPromulgate;
import com.cb.assess.domain.VAssessmentProcess;
import com.cb.assess.domain.VEvaluationGrade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VEvaluationGradeMapper {
    /**
     * 获取正常的平时考核
     * @param evaluationGrade
     * @return
     */
    public List<VEvaluationGrade> selectVEvaluationGradeList(VEvaluationGrade evaluationGrade);

    public List<VEvaluationGrade.Grade> selectGradeList(VEvaluationGrade.Grade grade);

    public List<VEvaluationGrade.Grade> selectDzhGradeList(VAssessmentProcess.ProcessVo processVo);

    public List<VEvaluationGrade.ExportLevelGrade> selectExportLevelGradeList(VEvaluationGrade.ExportLevelGrade grade);
    public List<VEvaluationGrade.Composite> selectCompositeList(VEvaluationGrade.Composite composite);
    public List<VEvaluationGrade.Voter> selectVoterList(VEvaluationGrade.Voter voter);
    public List<VEvaluationGrade.Voter> selectVoterListByUserId(VEvaluationGrade.Voter voter);

    public List<VEvaluationGrade> selectDisciplineVEvaluationGradeList(VEvaluationGrade evaluationGrade);

    public List<VEvaluationGrade.Grade> selectToBeEvaluatedList(@Param("et") VEvaluationGrade.Grade grade,
                                                                @Param("isScoring") boolean isScoring);

    public List<VEvaluationGrade> selectMainChargeVEvaluationGradeList(@Param("et") VEvaluationGrade evaluationGrade,
                                                                       @Param("deptIds") List<Long> deptIds,
                                                                       @Param("personnelTypeStr") String personnelTypeStr);

    public List<VEvaluationGrade.Grade> toBeOrderGradeList(@Param("et") VEvaluationGrade.Grade grade,
                                                           @Param("deptIds") List<Long> deptIds,
                                                           @Param("personnelTypeStr") String personnelTypeStr,
                                                           @Param("isScoring") boolean isScoring);

    public List<VEvaluationGrade> selectOrganVEvaluationGradeList(VEvaluationGrade evaluationGrade);


    public List<VEvaluationGrade.Grade> selectToBeOrganGradeList(@Param("et") VEvaluationGrade.Grade grade,
                                                           @Param("isScoring") boolean isScoring);


    public List<VEvaluationGrade.ExportEvaluationComposite> selectExportEvaluationCompositeList(VEvaluationGrade.Composite composite);

    public int countDzhOpinionNull(BizAssessPromulgate promulgate);


    public List<VEvaluationGrade.Grade> selectGradeListByPromulgate(BizAssessPromulgate promulgate);

    public List<VEvaluationGrade.Publicity> selectPublicityList(VEvaluationGrade.Publicity publicity);


    public int countDisciplineScoringNum();

    public int countPartyCommitteeNum();


}
