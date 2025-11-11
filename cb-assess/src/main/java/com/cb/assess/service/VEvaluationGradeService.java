package com.cb.assess.service;

import com.cb.assess.domain.BizAssessEvaluationGrade;
import com.cb.assess.domain.BizAssessPromulgate;
import com.cb.assess.domain.BizAssessRegularInfo;
import com.cb.assess.domain.VEvaluationGrade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface VEvaluationGradeService {

    public List<VEvaluationGrade> selectVEvaluationGradeList(VEvaluationGrade evaluationGrade);

    public List<VEvaluationGrade> selectDisciplineVEvaluationGradeList(VEvaluationGrade evaluationGrade);

    public List<VEvaluationGrade.Grade> selectGradeList(VEvaluationGrade.Grade grade);

    public List<VEvaluationGrade.ExportLevelGrade> selectExportGradeList(VEvaluationGrade.ExportLevelGrade grade);

    public BizAssessRegularInfo selectRegularInfo(String taskId, Long userId);


    public List<VEvaluationGrade.Composite> selectCompositeList(VEvaluationGrade.Composite composite);

    public VEvaluationGrade.PersonalScore selectVoterDetailList(VEvaluationGrade.Voter voter);


    public int saveComprehensive(VEvaluationGrade.ScoreLevelTempParam param);

    public List<VEvaluationGrade.Grade> selectToBeEvaluatedList(VEvaluationGrade.Grade grade, Boolean isScoring);


    public List<VEvaluationGrade> selectMainChargeVEvaluationGradeList(VEvaluationGrade evaluationGrade);

    public List<VEvaluationGrade.Grade> toBeOrderGradeList(VEvaluationGrade.Grade grade, Boolean isScoring);

    public List<VEvaluationGrade> selectOrganVEvaluationGradeList(VEvaluationGrade evaluationGrade);


    public List<VEvaluationGrade.Grade> selectToBeOrganGradeList(VEvaluationGrade.Grade grade, Boolean isScoring);


    public void exportRegular2Word(String taskId, Long userId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    public void exportEvaluationCompositeLevel(VEvaluationGrade.Composite composite, HttpServletResponse response);

    public int saveDzhOpinion(BizAssessEvaluationGrade grade);

    public int batchSaveDzhOpinion(List<BizAssessEvaluationGrade> gradeList);

    public int publicity(BizAssessPromulgate promulgate);

    public List<VEvaluationGrade.Publicity> selectPublicityList(VEvaluationGrade.Publicity publicity);

    public VEvaluationGrade.PromulgateResult selectPromulgateResult(VEvaluationGrade.Publicity publicity);

    public void exportInstructionWord(HttpServletResponse response, VEvaluationGrade.Composite composite);

}
