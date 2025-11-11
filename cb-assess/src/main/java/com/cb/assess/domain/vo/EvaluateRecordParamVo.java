package com.cb.assess.domain.vo;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * 测评打分请求参数
 */
@Data
public class EvaluateRecordParamVo {

    private String evaluateTagId;

    @Valid
    private List<EvaluateRecord> evaluateList;

    @Data
    public static class EvaluateRecord {
        private String ismConfigId;
        @NotNull(message = "存在考核项未填写分数，请填写分数！")
        private BigDecimal score;
    }
}
