package com.cb.assess.service;

import com.cb.assess.domain.BizAssessTaskAssessUserConfirm;
import com.cb.assess.domain.VRegularFillInfo;
import com.cb.assess.domain.vo.AssessTaskProcessStatisticsVo;

import java.util.List;

public interface AssessTaskProcessStatisticsService {
    /**
     * 获取平时考核步骤统计数据
     * @param vo
     * @return
     */

    public List<AssessTaskProcessStatisticsVo> selectAssessTaskProcessCount(AssessTaskProcessStatisticsVo vo);


    /**
     * 获取考核用户确认列表
     * @param vo
     * @return
     */
    public List<BizAssessTaskAssessUserConfirm> selectUserConfirmList(AssessTaskProcessStatisticsVo vo);
    /**
     * 获取总结填报列表
     * @param info
     * @return
     */
    public List<VRegularFillInfo> selectVRegularFillInfoList(VRegularFillInfo info);

    /**
     * 获取平时考核部门上报数据状态
     * @param report
     * @return
     */
    public List<AssessTaskProcessStatisticsVo.EvaluateReport> selectEvaluateReportList(AssessTaskProcessStatisticsVo.EvaluateReport report);

    /**
     * 获取上报审核情况
     * @param audit
     * @return
     */
    public List<AssessTaskProcessStatisticsVo.EvaluateReportAudit> selectEvaluateReportAuditList(AssessTaskProcessStatisticsVo.EvaluateReportAudit audit);


    /**
     * 获取纪委打分情况数据
     * @param discipline
     * @return
     */
    public List<AssessTaskProcessStatisticsVo.Discipline> selectDisciplineList(AssessTaskProcessStatisticsVo.Discipline discipline);

    /**
     * 获取主要负责人评定等次情况
     * @param grade
     * @return
     */
    public List<AssessTaskProcessStatisticsVo.MainLevelGrade> selectMainLevelGradeList(AssessTaskProcessStatisticsVo.MainLevelGrade grade);


    /**
     * 获取机关党委建议等次情况
     * @param grade
     * @return
     */
    public List<AssessTaskProcessStatisticsVo.Grade> selectRscOpinionList(AssessTaskProcessStatisticsVo.Grade grade);

    /**
     * 获取党组会建议等次情况
     * @param grade
     * @return
     */
    public List<AssessTaskProcessStatisticsVo.Grade> selectDzhOpinionList(AssessTaskProcessStatisticsVo.Grade grade);

    /**
     * 获取公示详细
     * @param promulgate
     * @return
     */
    public List<AssessTaskProcessStatisticsVo.Promulgate> selectPromulgateList(AssessTaskProcessStatisticsVo.Promulgate promulgate);
}
