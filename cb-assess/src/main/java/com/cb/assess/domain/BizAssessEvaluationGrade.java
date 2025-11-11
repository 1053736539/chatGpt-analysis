package com.cb.assess.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.math.BigDecimal;

/**
 * 考核评定等次存储对象 biz_assess_evaluation_grade
 *
 * @author ouyang
 * @date 2023-11-24
 */
@Data
public class BizAssessEvaluationGrade extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private String id;

    /**
     * 考核任务ID
     */
    @Excel(name = "考核任务ID")
    private String taskId;

    /**
     * 被评用户Id
     */
    @Excel(name = "被评用户Id")
    private Long userId;
    @Transient
    private String  userName;
    /**
     * 部门ID
     */
    @Excel(name = "部门ID")
    private Long deptId;

    @Transient
    private String  deptName;

    @Excel(name = "民主评价-处室打分")
    private BigDecimal deptScore;

    @Excel(name = "民主评价-领导打分")
    private BigDecimal leaderScore;

    @Excel(name = "专项考核得分")
    private BigDecimal unusualScore;

    @Excel(name = "作风纪律检查、系统考核和信访举报情况评分")
    private BigDecimal disciplineScore;

    @Excel(name = "考勤得分")
    private BigDecimal attendanceScore;

    /**
     * 评价得分
     */
    @Excel(name = "评价得分")
    private BigDecimal evaluationScore;
    /**
     * 评价等次
     */
    @Excel(name = "评价等次")
    private String grade;
    /**
     * 机关党委（人事处）审定意见
     */
    @Excel(name = "机关党委", readConverterExp = "人=事处")
    private String rscOpinion;

    /**
     * 局党组会建议等次
     */
    @Excel(name = "局党组会建议等次")
    private String dzhOpinion;

    /**
     * 最终等次
     */
    @Excel(name = "最终等次")
    private String finalOption;
    /**
     * 保存时打分结果JSON
     */
    @Excel(name = "保存时打分结果JSON")
    private String scoringMirror;

    /**
     * 是否修改 0 否 1是
     */
    private String modified;

    // 部门上报数据标记ID
    private String escalationId;
    // 0 上报 1 纪委评分 2.主要负责人建议等次 3.机关党委审定等次 4.党组会建议等次
    private String step;

    private String personnelType;

}
