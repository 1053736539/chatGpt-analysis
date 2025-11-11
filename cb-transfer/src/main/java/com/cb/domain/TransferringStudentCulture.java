package com.cb.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 选调生培养对象 transferring_student_culture
 * 
 * @author ruoyi
 * @date 2023-10-27
 */
public class TransferringStudentCulture extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    private String userId;

    @Excel(name = "培养方式", readConverterExp = "1=驻村,2=挂职,3=培训,4=参与其他任务")
    private String trainingMode;

    @Excel(name = "地点")
    private String trainingAddr;

    /** 培训编号 */
    @Excel(name = "培训编号")
    private String trainingNumber;

    /** 培训主题 */
    @Excel(name = "培训主题")
    private String trainingTheme;

    /** 时间 */
    @Excel(name = "时间")
    private String trainingTime;

    /** 时长 */
    @Excel(name = "培训时长")
    private Long trainingDuration;

    /** 附件材料 */
    @Excel(name = "附件材料")
    private String trainingAttach;

    /** $column.columnComment */
    private String delFlag;

    /** $column.columnComment */

    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setTrainingNumber(String trainingNumber) 
    {
        this.trainingNumber = trainingNumber;
    }

    public String getTrainingNumber() 
    {
        return trainingNumber;
    }
    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setTrainingTheme(String trainingTheme) 
    {
        this.trainingTheme = trainingTheme;
    }

    public String getTrainingTheme() 
    {
        return trainingTheme;
    }
    public void setTrainingTime(String trainingTime) 
    {
        this.trainingTime = trainingTime;
    }

    public String getTrainingTime() 
    {
        return trainingTime;
    }
    public void setTrainingDuration(Long trainingDuration) 
    {
        this.trainingDuration = trainingDuration;
    }

    public Long getTrainingDuration() 
    {
        return trainingDuration;
    }
    public void setTrainingAttach(String trainingAttach) 
    {
        this.trainingAttach = trainingAttach;
    }

    public String getTrainingAttach() 
    {
        return trainingAttach;
    }

    public String getTrainingMode() {
        return trainingMode;
    }

    public void setTrainingMode(String trainingMode) {
        this.trainingMode = trainingMode;
    }

    public String getTrainingAddr() {
        return trainingAddr;
    }

    public void setTrainingAddr(String trainingAddr) {
        this.trainingAddr = trainingAddr;
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
            .append("userId", getUserId())
            .append("trainingNumber", getTrainingNumber())
            .append("id", getId())
            .append("trainingTheme", getTrainingTheme())
            .append("trainingTime", getTrainingTime())
            .append("trainingDuration", getTrainingDuration())
            .append("trainingAttach", getTrainingAttach())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
