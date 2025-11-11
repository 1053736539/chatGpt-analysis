package com.cb.assess.domain.vo;

import com.cb.assess.domain.BizAssessIndicatorPro;
import com.cb.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class BizAssessTaskVo {

    private String taskId;

    // 考核任务名称
    private String taskName;
    /**
     * 考核周期
     */
    private String cycle;

    /**
     * 月度、季度、年度 对应的值
     */
    private String cycleDesc;

    private String belongYear;

    private String special;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 人员类型
     */
    private String type;

    private BizAssessIndicatorPro pro;

    private List<SummaryUser> summaryUsers;

    private List<EvaluationUser> evaluationUsers;

    @Data
    public static class SummaryUser {
        private Long userId;
        private String name;
    }

    @Data
    public static class EvaluationUser {
        private Long userId;
        private String name;
    }


    @Data
    public static class EvaluateLevelTempReq {
        private String taskId;
        private Long deptId;
        private String belongYear;
        private String cycle;
        private String cycleDesc;
        private String special;
        private String personnelType;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EvaluateLevelTempResp {
        private String id;
        private String taskId;
        private Long userId;
        @Builder.Default
        private BigDecimal deptScore = BigDecimal.ZERO;
        @Builder.Default
        private BigDecimal leaderScore = BigDecimal.ZERO;
        @Builder.Default
        private BigDecimal unusualScore = BigDecimal.ZERO;
        @Builder.Default
        private BigDecimal disciplineScore = BigDecimal.ZERO;

        @Builder.Default
        private Integer attendanceNum =0;

        @Excel(name = "考勤得分")
        @Builder.Default
        private BigDecimal attendanceScore = BigDecimal.ZERO;

        private String reportedGrade;
        private String rscOpinion;
        private String dzhOpinion;
        @Builder.Default
        private BigDecimal finalScore= BigDecimal.ZERO;
        private Integer rank;
        private String userName;
        private String deptName;
        private String personnelType;
        private String scoringMirror;
        private String step;
    }

    @Data
    public static class ExportComposite{
        private String belongYear;
        private String quarter;
        // 填报部门
        private String fillingDeptName;
        // 人员描述
        private String personnelDesc;

        private Integer totalNum;

        private Integer excellentNum;

        public List<ExportEvaluateComposite> list;
    }
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExportEvaluateComposite{

        private String deptName;

        private String userName;
        /**
         * 打分Json数组
         */
        private String  scoringJson;

        //部门原始打分
        private BigDecimal deptAverageScore = BigDecimal.ZERO;

        @Builder.Default
        private BigDecimal deptScore = BigDecimal.ZERO;

        @Builder.Default
        private BigDecimal leaderScore = BigDecimal.ZERO;

        @Builder.Default
        private BigDecimal unusualScore = BigDecimal.ZERO;

        @Builder.Default
        private BigDecimal subtotalScore = BigDecimal.ZERO;

        @Builder.Default
        private Integer attendanceNum =0;

        @Builder.Default
        private BigDecimal attendanceScore = BigDecimal.ZERO;

        @Builder.Default
        private BigDecimal disciplineScore = BigDecimal.ZERO;

        @Builder.Default
        private BigDecimal compositeScore= BigDecimal.ZERO;

        private Integer rank;

        private String grade;
    }
}
