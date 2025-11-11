package com.cb.assess.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 平时考核上报状态
 *  0 上报 1 纪委评分 2.主要负责人建议等次 3.机关党委审定等次 4.党组会建议等次
 */
public enum EvaluateStep {
    UN_REPORT("-1", "未上报"),
    REPORT("0", "上报"),
    DISCIPLINE("1", "纪委评分"),
    CHARGE_SUGGESTS("2", "主要负责人建议等次"),
    PARTY_SUGGESTS("3", "机关党委审定等次"),
    GM_SUGGESTS("4", "党组会建议等次");

    private final String code;
    private final String desc;

    EvaluateStep(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
    public static String getDescByCode(String code) {
        List<EvaluateStep> steps = Arrays.stream(EvaluateStep.values()).collect(Collectors.toList());
        for (EvaluateStep step : steps) {
            if (code.equals(step.getCode())) {
                return step.desc;
            }
        }
        throw new IllegalArgumentException("未匹配到指定值!");
    }
}
