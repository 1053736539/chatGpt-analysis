package com.cb.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 监督管理-走访基层对象 transferring_student_visit
 * 
 * @author yangxin
 * @date 2024-08-27
 */
public class TransferringStudentVisit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    private Long userId;

    /** $column.columnComment */
    private String delFlag;

    /** 走访时间 */
    @Excel(name = "走访时间")
    private String visitTime;

    /** 走访地点 */
    @Excel(name = "走访地点")
    private String visitAddr;

    /** 了解情况 */
    @Excel(name = "了解情况")
    private String visitContent;

    /** 走访对象数组json格式字符串 */
    private String visitObjJson;

    /**走访人数组json格式字符串 */
    /*
        [
            {
                "userId":123,
                "userName":"xxx",
                "workPost":"xxxx",
            }
        ]
    */

    private String visitUserJson;

    /** 走访事由 */
    private String visitCauses;

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
    public void setVisitTime(String visitTime) 
    {
        this.visitTime = visitTime;
    }

    public String getVisitTime() 
    {
        return visitTime;
    }
    public void setVisitAddr(String visitAddr) 
    {
        this.visitAddr = visitAddr;
    }

    public String getVisitAddr() 
    {
        return visitAddr;
    }
    public void setVisitContent(String visitContent) 
    {
        this.visitContent = visitContent;
    }

    public String getVisitContent() 
    {
        return visitContent;
    }

    public String getVisitObjJson() {
        return visitObjJson;
    }

    public void setVisitObjJson(String visitObjJson) {
        this.visitObjJson = visitObjJson;
    }

    public String getVisitUserJson() {
        return visitUserJson;
    }

    public void setVisitUserJson(String visitUserJson) {
        this.visitUserJson = visitUserJson;
    }

    public String getVisitCauses() {
        return visitCauses;
    }

    public void setVisitCauses(String visitCauses) {
        this.visitCauses = visitCauses;
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
            .append("visitTime", getVisitTime())
            .append("visitAddr", getVisitAddr())
            .append("visitContent", getVisitContent())
            .toString();
    }
}
