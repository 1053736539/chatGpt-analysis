package com.cb.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Transient;

/**
 * 监督管理-谈心谈话对象 transferring_student_talk
 * 
 * @author yangxin
 * @date 2024-08-27
 */
public class TransferringStudentTalk extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    private Long userId;

    /** $column.columnComment */
    private String delFlag;

    /** 谈话类型(1=试用期,2=到基层锻炼前,3=结束基层锻炼后,4=工作调整,5=发现问题) */
    @Excel(name = "谈话类型",readConverterExp = "1=试用期,2=到基层锻炼前,3=结束基层锻炼后,4=工作调整,5=发现问题")
    private String talkType;

    /** 谈话人user_id */
    private Long talkLeaderId;

    /** 谈话时间 */
    @Excel(name = "谈话时间")
    private String talkTime;

    /** 谈话地点 */
    @Excel(name = "谈话地点")
    private String talkAddr;

    /** 谈话内容 */
    @Excel(name = "谈话内容")
    private String talkContent;

    /** 谈话人职务 */
    @Excel(name = "谈话人职务")
    private String talkLeaderPost;

    /** 谈话对象职务 */
    @Excel(name = "谈话对象职务")
    private String talkObjPost;

    /** 谈话时意见 */
    @Excel(name = "谈话时意见")
    private String talkOpinion;

    /** 谈话人 */
    @Transient
    @Excel(name = "谈话人")
    private String talkLeaderName;

    /** 谈话对象 */
    @Transient
    @Excel(name = "谈话对象")
    private String talkObjName;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setTalkType(String talkType) 
    {
        this.talkType = talkType;
    }

    public String getTalkType() 
    {
        return talkType;
    }
    public void setTalkLeaderId(Long talkLeaderId)
    {
        this.talkLeaderId = talkLeaderId;
    }

    public Long getTalkLeaderId()
    {
        return talkLeaderId;
    }
    public void setTalkTime(String talkTime) 
    {
        this.talkTime = talkTime;
    }

    public String getTalkTime() 
    {
        return talkTime;
    }
    public void setTalkAddr(String talkAddr) 
    {
        this.talkAddr = talkAddr;
    }

    public String getTalkAddr() 
    {
        return talkAddr;
    }
    public void setTalkContent(String talkContent) 
    {
        this.talkContent = talkContent;
    }

    public String getTalkContent() 
    {
        return talkContent;
    }

    public String getTalkLeaderName() {
        return talkLeaderName;
    }

    public void setTalkLeaderName(String talkLeaderName) {
        this.talkLeaderName = talkLeaderName;
    }

    public String getTalkLeaderPost() {
        return talkLeaderPost;
    }

    public void setTalkLeaderPost(String talkLeaderPost) {
        this.talkLeaderPost = talkLeaderPost;
    }

    public String getTalkObjPost() {
        return talkObjPost;
    }

    public void setTalkObjPost(String talkObjPost) {
        this.talkObjPost = talkObjPost;
    }

    public String getTalkOpinion() {
        return talkOpinion;
    }

    public void setTalkOpinion(String talkOpinion) {
        this.talkOpinion = talkOpinion;
    }

    public String getTalkObjName() {
        return talkObjName;
    }

    public void setTalkObjName(String talkObjName) {
        this.talkObjName = talkObjName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("talkType", getTalkType())
            .append("talkLeaderId", getTalkLeaderId())
            .append("talkTime", getTalkTime())
            .append("talkAddr", getTalkAddr())
            .append("talkContent", getTalkContent())
            .toString();
    }
}
