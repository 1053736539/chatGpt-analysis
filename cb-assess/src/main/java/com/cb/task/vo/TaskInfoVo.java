package com.cb.task.vo;

import com.cb.task.domain.BizTaskComment;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @Author ARPHS
 * @Date 2023/11/8 9:32
 */
public interface TaskInfoVo {

    /**
     * OA交办任务请求参数
     */
    @Data
    class FormOAReq{

        /** OA业务主键 */
        @NotBlank(message = "OA业务主键不能为空")
        private String businessId;

        @NotBlank(message = "OA业务主键不能为空")
        private String currHandleUserId;//当前处理人

//        /** 任务公文类型 1=待办件,2=待阅件 */
//        @NotBlank(message = "公文类型")
//        private String docType;

        /** 任务标题 */
        @NotBlank(message = "任务标题不能为空")
        private String title;

        private String docNum;//文号

        /** 登记时间 yyyy-MM-dd HH:mm:ss */
        private String registerTime;

        private String handleOpinion;//处理意见

        /** 交办时间 yyyy-MM-dd HH:mm:ss */
        /*@NotBlank(message = "交办时间不能为空")*/
        private String assignTime;

        /** 紧急程度 字典表 1-一般 2-重要 3-紧急 4-非常紧急 */
        @NotBlank(message = "紧急程度不能为空")
        private String urgency;

        private List<TransferHistoryItem> transferList;//流转记录

        private List<String> assignUserIdList;//经办人oaUserId集合

        private List<FileItem> fileList;

        private String remark;//备注

    }

    /**
     * 交办记录item
     */
    @Data
    class TransferHistoryItem{
        private String receiveUser;//接收人名称
        private String receiveTime;//接收时间
        private String processStep;//流程步骤
        private String processDept;//所属部门名称
        private String sendOutTime;//送出时间
    }

    /**
     * 附件信息
     */
    @Data
    class FileItem {
        private String name;
        private String url;
    }

    @Data
    class CreateReq{

        /** 任务来源字典表 1-OA办文事项 2-OA通知公告 3-系统自建 */
        @NotBlank(message = "任务来源不能为空")
        private String causation;

        /** 任务公文类型 1=待办件,2=待阅件 */
        @NotBlank(message = "任务公文类型")
        private String docType;

        /** 业务主键（OA办文事项/OA通知公告id） */
        private String businessId;

        /** 任务依据（任务来源为系统创建时必填） */
//        @NotBlank(message = "任务依据不能为空")
        private String basis;

        /** 所属顶级任务id */
        private String topId;

        /** 父级任务id 不空则为子任务 */
        private String parentId;

        /** 任务标题 */
        @NotBlank(message = "任务标题不能为空")
        private String title;

        /** 任务内容 */
        private String desc;

        /** 开始时间 yyyy-MM-dd HH:mm:ss */
        @NotBlank(message = "任务开始时间不能为空")
        private String beginTime;

        /** 结束时间 yyyy-MM-dd HH:mm:ss */
        @NotBlank(message = "任务结束时间不能为空")
        private String endTime;

        /** 级别 字典表 1-一般 2-重要 3-紧急 4-非常紧急 */
        @NotBlank(message = "级别不能为空")
        private String urgency;

        /** 总分数 */
        private Long planScore;

        /** 难度系数 字典表 1-简单 2-一般 3-难 4-较难 5-高难 */
        @NotBlank(message = "难度系数不能为空")
        private String difficult;

        /** 完成度百分比 （0-100） */
        private Integer progress;

        /** 任务状态 字典表 1-执行中 2-已完成 3-暂停 4-取消 5-作废 */
        private String status;

        /** 完成情况 */
        private String completeSituation;

        private String attach;//附件attachId拼接字符串

        private String remark;//备注

        @Valid
        @Size(min = 1, message = "至少要有一个经办人")
        private List<HandleBuildItem> handleList;//经办人列表

    }

    /**
     * 经办人构建item
     */
    @Data
    class HandleBuildItem {

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
        @Range(min = 0, max = 100, message = "工作量占比(%)应在{min}-{max}之间")
        private Long scorePercent;

        /** 完成度百分比 （0-100） */
        private Integer progress;

        /** 执行开始时间 yyyy-MM-dd HH:mm:ss */
        private String receiveTime;

        /** 完成时间 yyyy-MM-dd HH:mm:ss */
        private String completeTime;

//        @NotNull(message = "请选择经办人的处理角色")
        private String handleRole;//处理角色字典表 1-主办 2-协办

        private Boolean masterFlag;//是否主要负责人

        private String remark;//备注

    }

    @Data
    class AuditReq{

        @NotBlank(message = "请选择任务")
        private String id;
        @NotBlank(message = "请选择审核状态")
        private String auditStatus;

        private String rejectReason;

    }

    @Data
    class SubmitCommentReq{

        @NotBlank(message = "请选择任务")
        private String taskId;
        @Range(max = 100,message = "进度必须在{min}到{max}之间")
        private Integer progress;
        @NotBlank(message = "请填写处理情况")
        @Length(max = 500, message = "处理情况不能超过{max}个字符")
        private String completeSituation;
        private String resultAttach;

    }

    /**
     * 用户处理用于评价的请求参数
     */
    @Data
    class UserHandle4EvaluationReq{
        @NotNull(message = "用户id不能为空")
        private Long userId;
        @NotBlank(message = "起始时间不能为空")
        private String beginTime;//起始时间 yyyy-MM-dd HH:mm:ss
        @NotBlank(message = "截止时间不能为空")
        private String endTime;//截止时间 yyyy-MM-dd HH:mm:ss
    }

    @Data
    class UserHandle4EvaluationResp{
        private BigDecimal score;//任务积分
        private List<UserHandleDetail> detailList;//处理详情列表
    }

    @Data
    class UserHandleDetail{
        private String taskId;//任务id
        private String title;//任务标题
        /** 任务状态 字典表 1-执行中 2-已完成 3-暂停 4-取消 5-作废 */
        private String status;
        private List<BizTaskComment> commentList;//评论列表
    }

}
