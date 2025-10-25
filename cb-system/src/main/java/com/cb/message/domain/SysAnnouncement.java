package com.cb.message.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 系统消息对象 sys_announcement
 * 
 * @author ouyang
 * @date 2023-07-12
 */
public class SysAnnouncement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String msgContent;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 发布人 */
    @Excel(name = "发布人")
    private String sender;

    /** 优先级（L低，M中，H高） */
    @Excel(name = "优先级", readConverterExp = "L=低，M中，H高")
    private String priority;

    /** 消息类型2:系统消息 */
    @Excel(name = "消息类型1:业务提醒 2:系统消息")
    private String msgCategory;

    /** 通告对象类型（USER:指定用户，ALL:全体用户） */
    @Excel(name = "通告对象类型", readConverterExp = "U=SER:指定用户，ALL:全体用户")
    private String msgType;

    /** 发布状态（0未发布，1已发布，2已撤销） */
    @Excel(name = "发布状态", readConverterExp = "0=未发布，1已发布，2已撤销")
    private String sendStatus;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    /** 撤销时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "撤销时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date cancelTime;

    /** 删除状态（0，正常，1已删除） */
    private String delFlag;

    /** 指定用户 */
    @Excel(name = "指定用户")
    private String userIds;

    /** 摘要 */
    @Excel(name = "摘要")
    private String msgAbstract;

    /** 业务id */
    private String bizId;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMsgContent(String msgContent)
    {
        this.msgContent = msgContent;
    }

    public String getMsgContent() 
    {
        return msgContent;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setSender(String sender) 
    {
        this.sender = sender;
    }

    public String getSender() 
    {
        return sender;
    }
    public void setPriority(String priority) 
    {
        this.priority = priority;
    }

    public String getPriority() 
    {
        return priority;
    }
    public void setMsgCategory(String msgCategory) 
    {
        this.msgCategory = msgCategory;
    }

    public String getMsgCategory() 
    {
        return msgCategory;
    }
    public void setMsgType(String msgType) 
    {
        this.msgType = msgType;
    }

    public String getMsgType() 
    {
        return msgType;
    }
    public void setSendStatus(String sendStatus) 
    {
        this.sendStatus = sendStatus;
    }

    public String getSendStatus() 
    {
        return sendStatus;
    }
    public void setSendTime(Date sendTime) 
    {
        this.sendTime = sendTime;
    }

    public Date getSendTime() 
    {
        return sendTime;
    }
    public void setCancelTime(Date cancelTime) 
    {
        this.cancelTime = cancelTime;
    }

    public Date getCancelTime() 
    {
        return cancelTime;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setUserIds(String userIds) 
    {
        this.userIds = userIds;
    }

    public String getUserIds() 
    {
        return userIds;
    }
    public void setMsgAbstract(String msgAbstract) 
    {
        this.msgAbstract = msgAbstract;
    }

    public String getMsgAbstract() 
    {
        return msgAbstract;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("msgContent", getMsgContent())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("sender", getSender())
            .append("priority", getPriority())
            .append("msgCategory", getMsgCategory())
            .append("msgType", getMsgType())
            .append("sendStatus", getSendStatus())
            .append("sendTime", getSendTime())
            .append("cancelTime", getCancelTime())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("userIds", getUserIds())
            .append("msgAbstract", getMsgAbstract())
            .toString();
    }
}
