package com.cb.task.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author ARPHS
 * @Date 2023/10/31 14:35
 */
public interface TaskHandleVo {

    @Data
    class AssignReq{
        @NotBlank(message = "请选择要指派的处理记录")
        private String handleId;//处理id
        @NotBlank(message = "指派的执行人不能为空")
        private String userName;//执行人userName
    }

    @Data
    class TransferReq{

        @NotBlank(message = "请选择要转办的记录")
        private String handleId;

        private String transferRemark;//转办备注

        @Valid
        @Size(min = 1, message = "至少要选择一个经办人")
        private List<HandleTransferItem> handleList;
    }

    @Data
    class HandleTransferItem{
        /** 处理类型 字典表 1-个人处理 2-部门处理 */
        @NotBlank(message = "请选择经办人的处理类型")
        private String handleType;

        // TODO 暂不考虑分到部门，预留
        /** 处理部门 部门任务时有值 */
        private Long handleDept;

        /** 处理人 个人任务/部门任务领导指派时有值 */
        @NotBlank(message = "请选择经办人")
        private String handleUser;

        /** 分数百分比 */
        @NotNull(message = "经办人的工作量占比不能为空")
        @Range(min = 1, max = 100, message = "工作量占比(%)应在{min}-{max}之间")
        private Long scorePercent;

        /** 完成度百分比 （0-100） */
        private Integer progress;

        /** 执行开始时间 yyyy-MM-dd HH:mm:ss */
        private String receiveTime;

        /** 完成时间 yyyy-MM-dd HH:mm:ss */
        private String completeTime;

        //        @NotNull(message = "请选择经办人的处理角色")
        private String handleRole;//处理角色字典表 1-主办 2-协办

        private String remark;//备注
    }

    @Data
    class ProgressReq{
        @NotBlank(message = "请选择要更新进度的处理记录")
        private String handleId;//处理id
        @NotNull(message = "进度不能为空")
        @Range(max = 100,message = "进度必须在{min}到{max}之间")
        private Integer progress;
        @Length(max = 500, message = "进度情况内容不能超过{max}个字符")
        private String completeSituation;
        private String resultAttach;
    }

    @Data
    class CompleteReq{
        @NotBlank(message = "请选择要完成的处理记录")
        private String handleId;//处理id
        @NotBlank(message = "请填写完成情况")
        @Length(max = 500, message = "完成情况内容不能超过{max}个字符")
        private String completeSituation;
        private String resultAttach;
    }

    /**
     * 罗列出用于年度考核和季度考核的工作任务信息的请求参数
     */
    @Data
    class List4AssessReq{
        @DateTimeFormat(pattern = "yyyy")
        @JsonFormat(pattern = "yyyy")
        private Date year;//年度
        @Range(min = 1, max = 4, message = "季度必须在{min}到{max}之间")
        private Integer quarter;//季度
        private String userName;//userName
        private String begin;//开始时间 yyyy-MM-dd HH:mm:ss
        private String end;//结束时间 yyyy-MM-dd HH:mm:ss
        private String taskName;//任务名称
        private String urgency;//级别 1-一般 2-重要 3-紧急 4-非常紧急
        private String handleStatus;//处理状态 1-执行中 2-已完成 3-暂停 4-取消 5-作废
        private String status;//任务状态 1-执行中 2-已完成 3-暂停 4-取消 5-作废
    }

    @Data
    class List4AssessResp{
        private String taskId;//任务id
        private String taskName;//任务名称
        private String urgency;//级别 1-一般 2-重要 3-紧急 4-非常紧急
        private String handleStatus;//处理状态 1-执行中 2-已完成 3-暂停 4-取消 5-作废
        private String status;//任务状态 1-执行中 2-已完成 3-暂停 4-取消 5-作废
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date createTime;
    }

}
