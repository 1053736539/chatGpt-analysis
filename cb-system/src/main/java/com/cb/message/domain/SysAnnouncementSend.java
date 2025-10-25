package com.cb.message.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 系统消息对象 sys_announcement_send
 * 
 * @author ouyang
 * @date 2023-07-12
 */
public class SysAnnouncementSend extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 消息ID */
    @Excel(name = "消息ID")
    private String anntId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 阅读状态（0未读，1已读） */
    @Excel(name = "阅读状态", readConverterExp = "0=未读，1已读")
    private String readFlag;

    /** 阅读时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "阅读时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date readTime;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setAnntId(String anntId) 
    {
        this.anntId = anntId;
    }

    public String getAnntId() 
    {
        return anntId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setReadFlag(String readFlag)
    {
        this.readFlag = readFlag;
    }

    public String getReadFlag() 
    {
        return readFlag;
    }
    public void setReadTime(Date readTime) 
    {
        this.readTime = readTime;
    }

    public Date getReadTime() 
    {
        return readTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("anntId", getAnntId())
            .append("userId", getUserId())
            .append("readFlag", getReadFlag())
            .append("readTime", getReadTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
