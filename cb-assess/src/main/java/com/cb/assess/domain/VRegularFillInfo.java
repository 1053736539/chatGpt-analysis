package com.cb.assess.domain;


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
public class VRegularFillInfo {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private String id;

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 用户ID
     */
    private Long userId;

    private String userName;

    private Long deptId;

    private String deptName;

    /**
     * 填报状态 0 未填 1 已填
     */
    private String status;

    /**
     * 主键
     */
    private String infoId;

    /**
     * biz_assess_task_user_tag的ID
     */

    private String userTagId;

    /**
     * 部门及职务
     */
    private String deptAndPost;

    /**
     * 从事或分管工作
     */
    private String responsibilities;

    /**
     * 个人总结
     */
    private String personalSummary;

    /**
     * 旷工天数
     */
    private Integer absenteeismNum;

    /**
     * 请假天数
     */
    private Integer leaveNum;

    /**
     * 迟到、早退次数
     */
    private Integer lateArrivalEarlyDepartureNum;

    /**
     * 奖惩情况与不良行为记录
     */
    private String rewardsPunishMisconduct;

    /**
     * 主管领导点评和考核等次建议
     */
    private String evaluationLevelSuggest;

    /**
     * 机关审定意见
     */
    private String organOpinions;

    /**
     * 未确定等次或不参加考核情况说明
     */
    private String othersRemark;
    // 审核人USER_ID
    private Long auditer;
    // 审核时间
    private Date auditTime;
    /**
     * 主管领导审核评价意见和考核等次建议
     * 审核状态:对个人小结情况进行审核把关 (对态度不端正、个人小结质量不高的应当要求重写)
     * 审核状态 -1 未填报 0 提交 1审核通过 2 不通过（可修改）
     */
    private String auditStatus;

    /**
     * 步骤 0 提交 1 主管领导审核 2 机关审定
     */
    private Integer step;

    /**
     * 编制类型 0 公务员 1 事业编
     */
    private String establishType;

    /**
     * 部门公章
     */
    private String auditerSeal;
    /**
     * 人事处公章
     */
    private String organSeal;
    /**
     * 签名图片
     * */
    private String signImg;
}
