package com.cb.assess.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Transient;

/**
 * 年度处级领导干部民主测评对象 biz_assess_cadre_democratic_record
 * 
 * @author yangxin
 * @date 2023-11-25
 */
public class BizAssessCadreDemocraticRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 任务id */
//    @Excel(name = "任务id")
    private String taskId;

    /** 被评价人 */
//    @Excel(name = "被评价人")
    private Long assessedUserId;

    /** 评价人 */
//    @Excel(name = "评价人")
    private Long voteUserId;

    /** 状态 0-待评价 1-已评价 */
    @Excel(name = "状态")
    private String status;

    /** 整体评价 1-优秀 2-称职 3-基本称职 4-不称职 */
    @Excel(name = "整体评价", readConverterExp = "1=优秀,2=称职,3=基本称职,4=不称职")
    private String overallEvaluation;

    /** 德的评价 1-优秀 2-良好 3-一般 4-较差 */
    @Excel(name = "德", readConverterExp = "1=优秀,2=良好,3=一般,4=较差")
    private String deEvaluation;

    /** 能的评价 1-优秀 2-良好 3-一般 4-较差 */
    @Excel(name = "能", readConverterExp = "1=优秀,2=良好,3=一般,4=较差")
    private String nengEvaluation;

    /** 勤的评价 1-优秀 2-良好 3-一般 4-较差 */
    @Excel(name = "勤", readConverterExp = "1=优秀,2=良好,3=一般,4=较差")
    private String qinEvaluation;

    /** 绩的评价 1-优秀 2-良好 3-一般 4-较差 */
    @Excel(name = "绩", readConverterExp = "1=优秀,2=良好,3=一般,4=较差")
    private String jiEvaluation;

    /** 廉的评价 1-优秀 2-良好 3-一般 4-较差 */
    @Excel(name = "廉", readConverterExp = "1=优秀,2=良好,3=一般,4=较差")
    private String lianEvaluation;

    /** 意见建议 */
    private String opinion;

    /** 以下为扩展属性 **/

    @Transient
    @Excel(name = "任务年度")
    private String taskYear;//任务年度

    @Transient
    @Excel(name = "任务备注")
    private String taskRemark;//任务备注

    @Transient
    @Excel(name = "任务创建人")
    private String taskCreateByName;//任务创建人名称

    @Transient
    @Excel(name = "评测对象")
    private String assessedUserName;//评测对象

    @Transient
    @Excel(name = "评测对象所属部门")
    private String assessedUserDeptName;//评测对象所属部门

    @Transient
    @Excel(name = "评测对象职务")
    private String assessedUserWorkPost;//评测对象职务

    @Transient
    @Excel(name = "评价人")
    private String voteUserName;//评价人

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setTaskId(String taskId) 
    {
        this.taskId = taskId;
    }

    public String getTaskId() 
    {
        return taskId;
    }
    public void setAssessedUserId(Long assessedUserId) 
    {
        this.assessedUserId = assessedUserId;
    }

    public Long getAssessedUserId() 
    {
        return assessedUserId;
    }
    public void setVoteUserId(Long voteUserId) 
    {
        this.voteUserId = voteUserId;
    }

    public Long getVoteUserId() 
    {
        return voteUserId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setOverallEvaluation(String overallEvaluation) 
    {
        this.overallEvaluation = overallEvaluation;
    }

    public String getOverallEvaluation() 
    {
        return overallEvaluation;
    }
    public void setDeEvaluation(String deEvaluation) 
    {
        this.deEvaluation = deEvaluation;
    }

    public String getDeEvaluation() 
    {
        return deEvaluation;
    }
    public void setNengEvaluation(String nengEvaluation) 
    {
        this.nengEvaluation = nengEvaluation;
    }

    public String getNengEvaluation() 
    {
        return nengEvaluation;
    }
    public void setQinEvaluation(String qinEvaluation) 
    {
        this.qinEvaluation = qinEvaluation;
    }

    public String getQinEvaluation() 
    {
        return qinEvaluation;
    }
    public void setJiEvaluation(String jiEvaluation) 
    {
        this.jiEvaluation = jiEvaluation;
    }

    public String getJiEvaluation() 
    {
        return jiEvaluation;
    }
    public void setLianEvaluation(String lianEvaluation) 
    {
        this.lianEvaluation = lianEvaluation;
    }

    public String getLianEvaluation() 
    {
        return lianEvaluation;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getTaskYear() {
        return taskYear;
    }

    public void setTaskYear(String taskYear) {
        this.taskYear = taskYear;
    }

    public String getTaskRemark() {
        return taskRemark;
    }

    public void setTaskRemark(String taskRemark) {
        this.taskRemark = taskRemark;
    }

    public String getTaskCreateByName() {
        return taskCreateByName;
    }

    public void setTaskCreateByName(String taskCreateByName) {
        this.taskCreateByName = taskCreateByName;
    }

    public String getAssessedUserName() {
        return assessedUserName;
    }

    public void setAssessedUserName(String assessedUserName) {
        this.assessedUserName = assessedUserName;
    }

    public String getVoteUserName() {
        return voteUserName;
    }

    public void setVoteUserName(String voteUserName) {
        this.voteUserName = voteUserName;
    }

    public String getAssessedUserDeptName() {
        return assessedUserDeptName;
    }

    public void setAssessedUserDeptName(String assessedUserDeptName) {
        this.assessedUserDeptName = assessedUserDeptName;
    }

    public String getAssessedUserWorkPost() {
        return assessedUserWorkPost;
    }

    public void setAssessedUserWorkPost(String assessedUserWorkPost) {
        this.assessedUserWorkPost = assessedUserWorkPost;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskId", getTaskId())
            .append("assessedUserId", getAssessedUserId())
            .append("voteUserId", getVoteUserId())
            .append("status", getStatus())
            .append("overallEvaluation", getOverallEvaluation())
            .append("deEvaluation", getDeEvaluation())
            .append("nengEvaluation", getNengEvaluation())
            .append("qinEvaluation", getQinEvaluation())
            .append("jiEvaluation", getJiEvaluation())
            .append("lianEvaluation", getLianEvaluation())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
