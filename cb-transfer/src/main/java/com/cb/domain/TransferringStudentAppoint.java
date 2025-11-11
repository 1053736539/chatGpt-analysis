package com.cb.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 选调生选拔任用对象 transferring_student_appoint
 * 
 * @author ruoyi
 * @date 2023-10-27
 */
public class TransferringStudentAppoint extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    private String userId;

    /** 任用时间 */
    @Excel(name = "任职时间")
    private String appointTime;

    /** 任用部门 */
    @Excel(name = "任用部门")
    private String appointDepartment;

    /** 任用岗位 */
    @Excel(name = "任用职务")
    private String appointStation;

    /** 证明附件材料 */
    @Excel(name = "证明附件材料")
    private String appointAttach;

    /** $column.columnComment */
    private String delFlag;

    /** 任职通知文号 */
    @Excel(name = "任职通知文号")
    private String docNo;

    /** 任用部门id */
//    @Excel(name = "任用部门id")
    private Long appointDeptId;

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
    public void setAppointTime(String appointTime) 
    {
        this.appointTime = appointTime;
    }

    public String getAppointTime() 
    {
        return appointTime;
    }
    public void setAppointDepartment(String appointDepartment) 
    {
        this.appointDepartment = appointDepartment;
    }

    public String getAppointDepartment() 
    {
        return appointDepartment;
    }
    public void setAppointStation(String appointStation) 
    {
        this.appointStation = appointStation;
    }

    public String getAppointStation() 
    {
        return appointStation;
    }
    public void setAppointAttach(String appointAttach) 
    {
        this.appointAttach = appointAttach;
    }

    public String getAppointAttach() 
    {
        return appointAttach;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public Long getAppointDeptId() {
        return appointDeptId;
    }

    public void setAppointDeptId(Long appointDeptId) {
        this.appointDeptId = appointDeptId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("appointTime", getAppointTime())
            .append("appointDepartment", getAppointDepartment())
            .append("appointStation", getAppointStation())
            .append("appointAttach", getAppointAttach())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
