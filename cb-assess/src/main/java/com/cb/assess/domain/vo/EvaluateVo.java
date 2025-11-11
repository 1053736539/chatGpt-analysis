package com.cb.assess.domain.vo;

import com.cb.common.core.domain.entity.SysUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 考核分级评价对象
 */
@Data
public class EvaluateVo {
    // 考核任务（互评）Id
    private String tagId;
    // 任务管理ID
    private String taskId;
    // 用户ID
    private Long userId;
    // 方案ID
    private String proId;
    // 任务名称
    private String taskName;
    // 考核周期
    private String cycleType;
    // 考核周期对应的值
    private String cycleDesc;

    //开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    //结束时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private BigDecimal maxScore;
    private BigDecimal scoring;
    /**
     * 总结是否必填
     */
    private Boolean mustFill;
    /**
     * 总结是否填写
     */
    private Boolean isFill;
    // 被考核的用户
    private SysUser user;
}
