package com.cb.assess.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.worksituation.domain.WorkSituation;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.Date;
import java.util.Objects;

/**
 * 平时考核登记对象 biz_assess_regular_info
 *
 * @author ouyang
 * @date 2023-11-09
 */
@Data
public class BizAssessRegularInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * biz_assess_task_user_tag的ID
     */
//    @Excel(name = "biz_assess_task_user_tag的ID")
    private String userTagId;

    /**
     * 部门及职务
     */
    @Excel(name = "部门及职务")
    private String deptAndPost;

    /**
     * 从事或分管工作
     */
    @Excel(name = "从事或分管工作")
    private String responsibilities;

    /**
     * 个人总结
     */
    @Excel(name = "个人总结")
    private String personalSummary;

    /**
     * 旷工天数
     */
    @Excel(name = "旷工天数")
    private Integer absenteeismNum;

    /**
     * 请假天数
     */
    @Excel(name = "请假天数")
    private Integer leaveNum;

    /**
     * 迟到、早退次数
     */
    @Excel(name = "迟到、早退次数")
    private Integer lateArrivalEarlyDepartureNum;

    /**
     * 奖惩情况与不良行为记录
     */
    @Excel(name = "奖惩情况与不良行为记录")
    private String rewardsPunishMisconduct;

    /**
     * 主管领导点评和考核等次建议
     */
    @Excel(name = "主管领导点评和考核等次建议")
    private String evaluationLevelSuggest;

    /**
     * 机关审定意见
     */
    @Excel(name = "机关审定意见")
    private String organOpinions;

    /**
     * 未确定等次或不参加考核情况说明
     */
    @Excel(name = "未确定等次或不参加考核情况说明")
    private String othersRemark;
    // 审核人USER_ID
    private Long auditer;
    // 审核时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date auditTime;
    /**
     * 主管领导审核评价意见和考核等次建议
     * 审核状态:对个人小结情况进行审核把关 (对态度不端正、个人小结质量不高的应当要求重写)
     */
    @Excel(name = "审核状态 0 提交 1审核通过 2 不通过（可修改）")
    private String auditStatus;

    /**
     * 步骤 0 提交 1 主管领导审核 2 机关审定
     */
    private Integer step;

    /**
     * 编制类型 0 公务员 1 事业编
     */
    @Excel(name = "编制类型 0 公务员 1 事业编")
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

    /**
     * 任务
     */
    private BizAssessTaskManage manage;
    /**
     * 总结用户
     */

    private SysUser user;
    /**
     * 评议类型
     */
    private String personnelType;

    /**
     * 考勤信息
     */
    private WorkSituation workSituation;

    /**
     * 查询传参用的用户名
     */
    @Transient
    private String userName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BizAssessRegularInfo that = (BizAssessRegularInfo) o;
        boolean b = Objects.equals(id, that.id) && Objects.equals(userTagId, that.userTagId);
        return b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userTagId);
    }
}
