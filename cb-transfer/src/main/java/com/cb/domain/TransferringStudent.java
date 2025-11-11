package com.cb.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 选调生对象 transferring_student
 * 
 * @author ruoyi
 * @date 2023-10-31
 */
public class TransferringStudent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户id */
    private String userId;

    /** 入选日期 */
    @Excel(name = "入选日期")
    private String selectedTime;

    /** 入选岗位 */
//    @Excel(name = "入选岗位")
    private String selectedStation;

    /** 入党时间 */
    @Excel(name = "入党时间")
    private String partyTime;

    /** 入选证明材料 */
    @Excel(name = "入选证明材料")
    private String selectedAttach;

    /** $column.columnComment */
    private String delFlag;

    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setSelectedTime(String selectedTime) 
    {
        this.selectedTime = selectedTime;
    }

    public String getSelectedTime() 
    {
        return selectedTime;
    }
    public void setSelectedStation(String selectedStation) 
    {
        this.selectedStation = selectedStation;
    }

    public String getSelectedStation() 
    {
        return selectedStation;
    }
    public void setSelectedAttach(String selectedAttach) 
    {
        this.selectedAttach = selectedAttach;
    }

    public String getSelectedAttach() 
    {
        return selectedAttach;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public String getPartyTime() {
        return partyTime;
    }

    public void setPartyTime(String partyTime) {
        this.partyTime = partyTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("selectedTime", getSelectedTime())
            .append("selectedStation", getSelectedStation())
            .append("selectedAttach", getSelectedAttach())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
