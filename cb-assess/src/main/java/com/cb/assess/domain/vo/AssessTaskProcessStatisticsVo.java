package com.cb.assess.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AssessTaskProcessStatisticsVo {
    private String taskId;
    private String proId;
    private String special;
    private Integer process;
    private String processName;
    private Integer totalNum;
    private Integer completedNum;

    @Data
    public static class EvaluateReport {
        private String taskId;
        private String taskName;
        private Long deptId;
        private String deptName;
        private Integer reportStatus;
        private String reportUserName;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date reportTime;
        private Integer auditStatus;
        private String options;
    }

    @Data
    public static class EvaluateReportAudit {
        private String taskId;
        private Long deptId;
        private String deptName;
        private String auditer;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date auditTime;
        private Integer auditStatus;
        private String options;
    }

    /**
     * 纪委评分
     */
    @Data
    public static class Discipline {
        private String taskId;
        private String userName;
        private Long deptId;
        private String deptName;
        private BigDecimal disciplineScore;
        private Integer attendanceNum;
        private BigDecimal attendanceScore;
        private Integer handleStatus;
    }

    /**
     * 主要负责人评定等次
     */
    @Data
    public static class MainLevelGrade {
        private String taskId;
        private String deptName;
        private String userName;
        private BigDecimal score;
        private Integer attendanceNum;
        private BigDecimal attendanceScore;
        private BigDecimal disciplineScore;
        private String grade;
        private Integer handleStatus;
    }

    /**
     * 获得等次
     */
    @Data
    public static class Grade {
        private String taskId;
        private String deptName;
        private String userName;
        private BigDecimal score;
        private Integer attendanceNum;
        private BigDecimal attendanceScore;
        private BigDecimal disciplineScore;
        private String grade;
        private String rscOpinion;
        private String dzhOpinion;
        private Integer handleStatus;
    }

    /**
     * 公示详情
     */
    @Data
    public static class Promulgate {
        private String taskId;
        private String deptName;
        private String userName;
        private String finalOpinion;
        private Integer handleStatus;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date publicityTime;
        private Integer publicityDays;
        private String finishStatus;
    }
}
