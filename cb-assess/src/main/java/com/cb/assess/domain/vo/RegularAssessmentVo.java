package com.cb.assess.domain.vo;

import lombok.Data;

@Data
public class RegularAssessmentVo {
    private Long userId;
    private Long deptId;
    private Double evaluationScore;
    private String grade;
    private String year;
    private String type;
    private String cycle;
    private String cycleDesc;
}
