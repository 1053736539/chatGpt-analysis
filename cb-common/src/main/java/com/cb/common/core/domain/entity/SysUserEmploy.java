package com.cb.common.core.domain.entity;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 任免和聘用信息对象 sys_user_employ
 * 
 * @author ruoyi
 * @date 2023-11-09
 */
public class SysUserEmploy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String userId;

    /** 任免前职务 */
    @Excel(name = "任免前职务")
    private String dutiesBefor;

    /** 任免后职务 */
    @Excel(name = "任免后职务")
    private String dutiesAfter;

    /** 任免时间 */
    @Excel(name = "任现职务时间")
    private String changeTime;
    @Excel(name = "任同职务时间")
    private String equalChangeTime;

    /** 任免附件 */
    @Excel(name = "任免附件")
    private String attach;

    /** 类型，1.任免，2聘用解聘 */
    @Excel(name = "类型，1.任免，2聘用解聘")
    private String type;

    /** 1.草稿，2.保存 */
    @Excel(name = "1.草稿，2.保存")
    private String status;

    /** 任免信息快照json */
    @Excel(name = "任免信息快照json")
    private String information;

    /** 1.系统内自建，2导入 */
    @Excel(name = "1.系统内自建，2导入")
    private String saveType;

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
    public void setDutiesBefor(String dutiesBefor) 
    {
        this.dutiesBefor = dutiesBefor;
    }

    public String getDutiesBefor() 
    {
        return dutiesBefor;
    }
    public void setDutiesAfter(String dutiesAfter) 
    {
        this.dutiesAfter = dutiesAfter;
    }

    public String getDutiesAfter() 
    {
        return dutiesAfter;
    }
    public void setChangeTime(String changeTime) 
    {
        this.changeTime = changeTime;
    }

    public String getChangeTime() 
    {
        return changeTime;
    }
    public void setAttach(String attach) 
    {
        this.attach = attach;
    }

    public String getAttach() 
    {
        return attach;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setInformation(String information) 
    {
        this.information = information;
    }

    public String getInformation() 
    {
        return information;
    }
    public void setSaveType(String saveType) 
    {
        this.saveType = saveType;
    }

    public String getSaveType() 
    {
        return saveType;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public String getEqualChangeTime() {
        return equalChangeTime;
    }

    public void setEqualChangeTime(String equalChangeTime) {
        this.equalChangeTime = equalChangeTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("dutiesBefor", getDutiesBefor())
            .append("dutiesAfter", getDutiesAfter())
            .append("changeTime", getChangeTime())
            .append("attach", getAttach())
            .append("type", getType())
            .append("status", getStatus())
            .append("information", getInformation())
            .append("saveType", getSaveType())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
