package com.cb.assess.domain.vo;

import lombok.Data;

@Data
public class ExaminerVo {
    private Long userId;
    // 0 普通科员 1主要负责人 2 分管领导 3 局主要领导  4.专项测评的，不再区分
    private String voteType;
}
