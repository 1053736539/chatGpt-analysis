package com.cb.assess.domain.vo;

import lombok.Data;

@Data
public class VTaskAssessConfirmUser {
    private String id;
    private String taskId;
    private String confirmStatus;
    private Long userId;
    private String userName;
    private Long deptId;
    private String deptName;
    private String cycleDesc;

}
