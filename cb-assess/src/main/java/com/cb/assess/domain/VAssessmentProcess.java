package com.cb.assess.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class VAssessmentProcess {
    private String taskId;
    private String name;
    private String belongYear;
    private String special;
    private String cycle;
    private String cycleDesc;
    private Integer process;
    private Integer handleStatus;
    /*其他状态*/
    private Integer otherStatus;
    /**
     * 其他状态附属说明
     */
    private String otherText;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class ProcessVo{
        private String taskId;
        private String taskName;
        private String belongYear;
        private String special;
        private String cycle;
        private String cycleDesc;
        private String personnelType;
        private String taskStatus;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date startTime;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date endTime;
        private String delFlag;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date createTime;
        private Long voter;
        private Long userId;
        private String userName;
        private Long deptId;
        private Integer process;
        private Integer handleStatus;
    }
    @Data
    public static class EvaluationScoreReq{
        private String special;
        private List<String> taskIds;
        private List<Long> userIds;
    }
    @Data
    public static class EvaluationScoreResp {
        private Long userId;
        private String deptName;
        private String userName;
        private BigDecimal score;
    }
}
