package com.cb.assess.domain.vo;

import com.cb.assess.domain.BizAssessEvaluationGrade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
public class EscalationVo {
    private String taskId;
    private String title;

    private String belongYear;

    private String special;

    private String cycle;

    private String cycleDesc;

    private String escalationFlag;
    private String options;
    @Data
    public static class Voter {
        private String taskId;

        private String evaluateTagId;

        private Long voter;

        private String voterName;

        private Long deptId;

        private Long userId;

        private String voteType;

        private Integer weight;

        private String status;

        private BigDecimal score;

        private String special;

        private String cycle;

        private String cycleDesc;

        private String proId;
        private String personnelType;
    }

    @Data
    public static class VoteScore {
        private String taskId;
        //用户姓名或者其他描述
        private String describe;

        /**
         * {@link com.cb.assess.enums.RatGroup}
         */
        private String voteType;
        // 权重 （换算成小数了）
        private Float weight;

        // 总得分
        private BigDecimal totalScore;

        // 平均分
        private BigDecimal averageScore;

        // 加权平均分
        private BigDecimal weightedScore;

        private String personnelType;

        //打分记录
        private List<EscalationVo.Voter> voterList;
    }

    @Data
    public static class EvaluateCensus {
        private String taskId;
        private Long deptId;
        private String year;
        private String cycleDesc;
        private String escalationFlag;
        private List<BizAssessEvaluationGrade> evaluationGrades;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReportedEvaluate {
        private String id;
        private String taskId;

        private String special;

        private String belongYear;

        private String cycle;

        private String cycleDesc;

        private String personnelType;

        private Long userId;

        private String name;

        private Long deptId;

        private String deptName;
        @Builder.Default
        private BigDecimal deptScore = BigDecimal.ZERO;
        @Builder.Default
        private BigDecimal leaderScore = BigDecimal.ZERO;
        @Builder.Default
        private BigDecimal unusualScore = BigDecimal.ZERO;
        //评价得分
        @Builder.Default
        private BigDecimal evaluationScore = BigDecimal.ZERO;

        // 排名
        private Integer rank;

        // 等次
        private String grade;

        private String modified;
        // 备注
        private String remark;

        /**
         * 是否总结必填
         */
        private Boolean mustFillSummarize = false;

        // 步骤 0 提交 1 主管领导审核 2 机关审定
        private Integer summarizeStep;
        /**
         * 是否完成总结填写
         */
        private Boolean summarizeCompleted = false;
        //是否评价完成
        private Boolean evaluationCompleted = false;

        private String scoringMirror;
        /**
         * 打分列表
         */
        private List<Voter> voters;
    }
}
