package com.cb.message.domain;

import com.cb.common.core.domain.entity.SysUser;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MessageVo {

    private String id;

    private String title;

    private String msgContent;

    private String sender;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    private String priority;

    private String msgCategory;

    private String msgType;

    private String msgAbstract;

    private Long userId;

    private SysUser user;

    private String readFlag;

    private Date readTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getMsgCategory() {
        return msgCategory;
    }

    public void setMsgCategory(String msgCategory) {
        this.msgCategory = msgCategory;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgAbstract() {
        return msgAbstract;
    }

    public void setMsgAbstract(String msgAbstract) {
        this.msgAbstract = msgAbstract;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(String readFlag) {
        this.readFlag = readFlag;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "MessageVo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", msgContent='" + msgContent + '\'' +
                ", sender='" + sender + '\'' +
                ", priority='" + priority + '\'' +
                ", msgCategory='" + msgCategory + '\'' +
                ", msgType='" + msgType + '\'' +
                ", msgAbstract='" + msgAbstract + '\'' +
                ", userId=" + userId +
                ", readFlag='" + readFlag + '\'' +
                ", readTime=" + readTime +
                '}';
    }
}
