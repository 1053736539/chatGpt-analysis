package com.cb.assess.domain.vo;

import com.cb.assess.domain.BizAssessRegularInfo;
import lombok.Data;

@Data
public class AssessRegularVo extends BizAssessRegularInfo {

    private String userName;
    private String auditName;
    private String auditDateTime;
    private String cycle;
    private String cycleDesc;
    private String quarter;
}
