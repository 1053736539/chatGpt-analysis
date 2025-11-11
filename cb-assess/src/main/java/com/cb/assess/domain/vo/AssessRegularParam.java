package com.cb.assess.domain.vo;

import com.cb.assess.domain.BizAssessRegularInfo;
import lombok.Data;

import java.util.List;

@Data
public class AssessRegularParam {
    private static final long serialVersionUID = 1L;
    private List<BizAssessRegularInfo> regulars;
}
