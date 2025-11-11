package com.cb.assess.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class VOrdinaryAssessTask {

    private String taskId;

    // 考核任务名称
    private String taskName;
    //方案id
    private String proId;
    // 方案名称
    private String proName;
    //考核周期
    private String cycle;

    // 月度、季度、年度 对应的值
    private String cycleDesc;
    private String belongYear;
    private String special;
    //开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    //结束时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    //人员类型
    private String type;
    // 任务状态
    private String taskStatus;
    // 任务过程状态
    private String processStatus;
}
