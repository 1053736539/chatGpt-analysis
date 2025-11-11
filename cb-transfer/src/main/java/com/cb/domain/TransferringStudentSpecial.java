package com.cb.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 选调生锻炼培养-参与专项任务对象 transferring_student_special
 * 
 * @author yangxin
 * @date 2024-08-28
 */
public class TransferringStudentSpecial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long userId;

    /** $column.columnComment */
    private String delFlag;

    /** 工作任务内容 */
    @Excel(name = "工作任务内容")
    private String workContent;

    /** 开始时间 */
    @Excel(name = "开始时间")
    private String beginTime;

    /** 结束时间 */
    @Excel(name = "结束时间")
    private String endTime;

    /** 地点 */
    @Excel(name = "地点")
    private String addr;

    /** 附件材料 */
    @Excel(name = "附件材料")
    private String attach;

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
    public void setWorkContent(String workContent) 
    {
        this.workContent = workContent;
    }

    public String getWorkContent() 
    {
        return workContent;
    }
    public void setBeginTime(String beginTime) 
    {
        this.beginTime = beginTime;
    }

    public String getBeginTime() 
    {
        return beginTime;
    }
    public void setEndTime(String endTime) 
    {
        this.endTime = endTime;
    }

    public String getEndTime() 
    {
        return endTime;
    }
    public void setAddr(String addr) 
    {
        this.addr = addr;
    }

    public String getAddr() 
    {
        return addr;
    }
    public void setAttach(String attach) 
    {
        this.attach = attach;
    }

    public String getAttach() 
    {
        return attach;
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
            .append("workContent", getWorkContent())
            .append("beginTime", getBeginTime())
            .append("endTime", getEndTime())
            .append("addr", getAddr())
            .append("attach", getAttach())
            .toString();
    }
}
