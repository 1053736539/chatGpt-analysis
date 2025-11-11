package com.cb.assess.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class BizIndividualAssessmentTask {

    private String taskId;

    private String proId;
    // 考核任务名称
    private String taskName;

    private String belongYear;
    /**
     * 考核周期
     */
    private String assessCycle;
    /**
     * PersonnelType type
     */
    private String type;

    /**
     * 月度、季度、年度 对应的值
     */
    private String assessCycleDesc;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /**
     * 评议状态 0 未评 1已评
     */
    private String status;

    private Boolean summarizeAuditFailed = false;
    /**
     * 标记是否可修改总结
     */
    private Boolean enableEditSummarize= false;
    /**
     * 标记是否可修改评分
     */
    private Boolean enableEditEvaluate= false;

    private VRegularFillInfo regular;

    private BizAssessIndicatorPro pro;
}
