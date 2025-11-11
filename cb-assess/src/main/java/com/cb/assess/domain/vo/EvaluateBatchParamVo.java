package com.cb.assess.domain.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
/**
 * 批量测评打分请求参数
 */
@Data
public class EvaluateBatchParamVo {
    private String taskId;
    private String proId;
    private List<Record> evaluateList;
    @Data
    public static class Record {
        private String tagId;
        private Long userId;
        private BigDecimal scoring;
    }
}
