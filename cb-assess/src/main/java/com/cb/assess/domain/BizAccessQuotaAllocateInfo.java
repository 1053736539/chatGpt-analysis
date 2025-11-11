package com.cb.assess.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 年度机关事业单位考核人数及优秀等次名额分配信息对象 biz_access_quota_allocate_info
 * 
 * @author yangxin
 * @date 2023-12-09
 */
public class BizAccessQuotaAllocateInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 年度 */
    @Excel(name = "年度")
    private String year;

    /** 状态 1-待审核 2-审核通过 3-驳回 */
    @Excel(name = "状态 1-待审核 2-审核通过 3-驳回")
    private String status;

    /** 驳回理由 */
    @Excel(name = "驳回理由")
    private String rejectReason;

    /** 表格内容json字符串 */
    @Excel(name = "表格内容json字符串")
    private String tableInfo;

    /** 公告发布状态 1-未发布 2-已发布 */
    private String noticeFlag;

    /** 考核方案附件id */
    private String attachId;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setYear(String year) 
    {
        this.year = year;
    }

    public String getYear() 
    {
        return year;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setRejectReason(String rejectReason) 
    {
        this.rejectReason = rejectReason;
    }

    public String getRejectReason() 
    {
        return rejectReason;
    }
    public void setTableInfo(String tableInfo) 
    {
        this.tableInfo = tableInfo;
    }

    public String getTableInfo() 
    {
        return tableInfo;
    }

    public String getNoticeFlag() {
        return noticeFlag;
    }

    public void setNoticeFlag(String noticeFlag) {
        this.noticeFlag = noticeFlag;
    }

    public String getAttachId() {
        return attachId;
    }

    public void setAttachId(String attachId) {
        this.attachId = attachId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("year", getYear())
            .append("status", getStatus())
            .append("rejectReason", getRejectReason())
            .append("tableInfo", getTableInfo())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
