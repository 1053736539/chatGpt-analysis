package com.cb.assess.mapper;

import com.cb.assess.domain.BizAssessTaskAssessUserConfirm;
import com.cb.assess.domain.vo.AssessTaskProcessStatisticsVo;

import java.util.List;

/**
 * 考核任务办理过程统计接口
 *
 * @author ouyang
 * @date 2023-11-04
 */
public interface AssessTaskProcessStatisticsMapper {

    public List<AssessTaskProcessStatisticsVo> selectAssessTaskProcessCount(AssessTaskProcessStatisticsVo vo);

    /**
     * 获取平时考核部门上报数据状态
     * @param report
     * @return
     */
    public List<AssessTaskProcessStatisticsVo.EvaluateReport> selectEvaluateReportList(AssessTaskProcessStatisticsVo.EvaluateReport report);


    /**
     * 获取审核情况列表
     * @param audit
     * @return
     */
    public List<AssessTaskProcessStatisticsVo.EvaluateReportAudit> selectEvaluateReportAuditList(AssessTaskProcessStatisticsVo.EvaluateReportAudit audit);

    /**
     * 纪委打分情况
     * @param discipline
     * @return
     */
    public List<AssessTaskProcessStatisticsVo.Discipline> selectDisciplineList(AssessTaskProcessStatisticsVo.Discipline discipline);

    /**
     * 主要负责人评定等次情况
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
     * 获取被公示的用户
     * @param promulgate
     * @return
     */
    public List<AssessTaskProcessStatisticsVo.Promulgate> selectPromulgateList(AssessTaskProcessStatisticsVo.Promulgate promulgate);
}
