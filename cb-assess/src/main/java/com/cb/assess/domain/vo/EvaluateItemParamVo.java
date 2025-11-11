package com.cb.assess.domain.vo;

import lombok.Data;

@Data
public class EvaluateItemParamVo {
    private String tagId;
    private Long userId;
    private String taskId;
    private String proId;
}
