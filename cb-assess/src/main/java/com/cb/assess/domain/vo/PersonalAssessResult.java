package com.cb.assess.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PersonalAssessResult {
    private String id;
    private String regularId;
    private Long userId;
    private String taskId;
    private BigDecimal finalScore;
    private String finalOption;
    private String remark;
    private String special;
    private String year;
    private String cycle;
    private String cycleDesc;
    private String personnelType;
}
