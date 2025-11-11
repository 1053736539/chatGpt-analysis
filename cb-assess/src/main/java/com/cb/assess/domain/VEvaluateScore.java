package com.cb.assess.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import oshi.jna.platform.mac.SystemB;

import java.math.BigDecimal;
import java.util.List;

@Data
public class VEvaluateScore {

    // 用户ID
    private Long userId;

    // 部门名称
    private String deptName;

    // 用户姓名
    private String name;

    //最终得分
    private BigDecimal finalScore;
    // 最终返回到前端的数据
    private List<VoteScore> voteScoreList;

    @Data
    public static class VoteScore {
        //用户姓名
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

        // 加权平局分
        private BigDecimal weightedScore;

        //打分记录
        private List<VoterDetail> voterDetailList;
    }

    @Data
    public static class VoterDetail {
        private String taskId;

        private String evaluateTagId;

        private Long voter;

        private String voterName;

        private Long userId;

        private String voteType;

        private Integer weight;

        private String status;

        private BigDecimal score;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Comprehensive {
        // 用户姓名
        private String name;
        // 部门名称
        private String deptName;
        // 互评得分
        @Builder.Default
        private BigDecimal rateEach= BigDecimal.ZERO;
        //主要负责人评分
        @Builder.Default
        private BigDecimal rateDirector= BigDecimal.ZERO;
        //分管领导评分
        @Builder.Default
        private BigDecimal rateChargeLeader= BigDecimal.ZERO;
        // 主要领导评分
        @Builder.Default
        private BigDecimal rateMainLeader= BigDecimal.ZERO;
        // 专项考核
        @Builder.Default
        private BigDecimal rateSpecial= BigDecimal.ZERO;
        // 以上小计
        @Builder.Default
        private BigDecimal subtotalScore= BigDecimal.ZERO;
        //作风纪律检查、系统考核和信访举报情况
        @Builder.Default
        private BigDecimal discipline= BigDecimal.ZERO;
        // 综合得分
        @Builder.Default
        private BigDecimal comprehensiveScore= BigDecimal.ZERO;
        // 排名
        private Integer rank;

        // 建议等次
        private String grade;

    }

}
