package com.cb.assess.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 评定等次：公务员平时考核结果分为“好、较好、一般、较差”4个等次。
 * 综合评价得分对应的等次评定为：在90分（含）—100分之间的可评为“好”等次，
 * 在80分（含）—89之间的可评为“较好”等次，在60分（含）—79之间的可评为“一般”等次，
 * 在60分（不含）以下的应评为“较差”等次。相同分值区间按得分由高到低取舍。
 * <p>
 * “好”等次原则上掌握在参加平时考核的公务员总人数的40% 以内。
 */
public enum Grade {
    EXCELLENT(90, "好"),
    BETTER(80, "较好"),
    GENERAL(60, "一般"),
    INFERIOR(0, "较差");
    private final Integer code;
    private final String desc;

    Grade(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getGradeDescByCode(int code) {
        List<Grade> grades = Arrays.stream(Grade.values()).collect(Collectors.toList());
        for (Grade grade : grades) {
            if (code >= grade.code) {
                return grade.desc;
            }
        }
        throw new IllegalArgumentException("给定分值不在正确区间!");
    }
}
