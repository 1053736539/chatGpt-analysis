package com.cb.assess.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class EvaluationGradeVo {

    private String taskId;

    private Long userId;

    private String name;

    private Long deptId;

    private String deptName;

    //评价得分
    private BigDecimal evaluationScore;

    // 排名
    private Integer rank;

    // 等次
    private String grade;

    private String modified;
    // 备注
    private String remark;

    @Data
    public static class EvaluationScoringMirror {
        private String evaluateTagId;

        private String taskId;
        // 打分人
        private Long voteUserId;
        // 被打分人
        private Long assessedUserId;
        // 打分状态 0 未打分 1 已打分
        private String status;
        // 0 普通科员 1主要负责人 2 分管领导 3 局主要领导  4.专项测评
        private String voteType;

        // 打分记录ID
        private String evaluateId;

        // 指标配置ID
        private String ismConfigId;
        // 指标项获得分数
        private BigDecimal score;

        // 打分时间
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date scoringTime;
    }
}
