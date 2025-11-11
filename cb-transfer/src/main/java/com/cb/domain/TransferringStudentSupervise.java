package com.cb.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 选调生教育监督对象 transferring_student_supervise
 *
 * @author ruoyi
 * @date 2023-10-27
 */
public class TransferringStudentSupervise extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String userId;

    /** 监督内容 */
    @Excel(name = "监督内容")
    private String supervisionContent;

    /** 监督方式 */
    @Excel(name = "监督方式")
    private String supervisionWay;

    /** 证明材料 */
    @Excel(name = "证明材料")
    private String supervisionAttach;

    /** $column.columnComment */
    private String delFlag;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserId()
    {
        return userId;
    }
    public void setSupervisionContent(String supervisionContent)
    {
        this.supervisionContent = supervisionContent;
    }

    public String getSupervisionContent()
    {
        return supervisionContent;
    }
    public void setSupervisionWay(String supervisionWay)
    {
        this.supervisionWay = supervisionWay;
    }

    public String getSupervisionWay()
    {
        return supervisionWay;
    }
    public void setSupervisionAttach(String supervisionAttach)
    {
        this.supervisionAttach = supervisionAttach;
    }

    public String getSupervisionAttach()
    {
        return supervisionAttach;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userId", getUserId())
                .append("supervisionContent", getSupervisionContent())
                .append("supervisionWay", getSupervisionWay())
                .append("supervisionAttach", getSupervisionAttach())
                .append("createBy", getCreateBy())
                .append("updateTime", getUpdateTime())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("delFlag", getDelFlag())
                .toString();
    }
}