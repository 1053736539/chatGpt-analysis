package com.cb.cadretrain.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Transient;

import java.util.Date;

/**
 * 培训记录对象 biz_train_records
 * 
 * @author yangcd
 * @date 2023-10-30
 */
public class BizTrainRecords extends BaseEntity
{
//    private static final long serialVersionUID = 1L;

    /** 培训记录ID */
    private String id;

    private Long userId;

    /** 受训人姓名 */
    @Excel(name = "受训人姓名")
    private String traineeName;

    /** 培训类型 */
    @Excel(name = "培训类型")
    private String trainType;

    /** 培训名称 */
    @Excel(name = "培训名称")
    private String trainName;

    /** 培训内容 */
    @Excel(name = "培训内容")
    private String trainOrganizer;

    /** 培训开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "培训开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date trainStartDate;

    /** 培训结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "培训结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date trainEndDate;

    /** 培训时长 */
    @Excel(name = "培训时长")
    private String trainDuration;

    /** 培训地点 */
    @Excel(name = "培训地点")
    private String trainLocation;

    /** 培训证明材料 */
    @Excel(name = "培训证明材料")
    private String trainProofDocument;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private String approvalStatus;

    /** 审核人姓名 */
    @Excel(name = "审核人姓名")
    private String approverName;

    /** 审核人部门 */
    @Excel(name = "审核人部门")
    private String approverDept;

    @Transient
    private String deptId;//查询时传参

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setTraineeName(String traineeName) 
    {
        this.traineeName = traineeName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTraineeName() 
    {
        return traineeName;
    }
    public void setTrainType(String trainType) 
    {
        this.trainType = trainType;
    }

    public String getTrainType() 
    {
        return trainType;
    }
    public void setTrainName(String trainName) 
    {
        this.trainName = trainName;
    }

    public String getTrainName() 
    {
        return trainName;
    }

    public String getTrainOrganizer() {
        return trainOrganizer;
    }

    public void setTrainOrganizer(String trainOrganizer) {
        this.trainOrganizer = trainOrganizer;
    }

    public void setTrainStartDate(Date trainStartDate)
    {
        this.trainStartDate = trainStartDate;
    }

    public Date getTrainStartDate() 
    {
        return trainStartDate;
    }
    public void setTrainEndDate(Date trainEndDate) 
    {
        this.trainEndDate = trainEndDate;
    }

    public Date getTrainEndDate() 
    {
        return trainEndDate;
    }
    public void setTrainDuration(String trainDuration) 
    {
        this.trainDuration = trainDuration;
    }

    public String getTrainDuration() 
    {
        return trainDuration;
    }
    public void setTrainLocation(String trainLocation) 
    {
        this.trainLocation = trainLocation;
    }

    public String getTrainLocation() 
    {
        return trainLocation;
    }
    public void setTrainProofDocument(String trainProofDocument) 
    {
        this.trainProofDocument = trainProofDocument;
    }

    public String getTrainProofDocument() 
    {
        return trainProofDocument;
    }
    public void setApprovalStatus(String approvalStatus) 
    {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovalStatus() 
    {
        return approvalStatus;
    }
    public void setApproverName(String approverName) 
    {
        this.approverName = approverName;
    }

    public String getApproverName() 
    {
        return approverName;
    }
    public void setApproverDept(String approverDept) 
    {
        this.approverDept = approverDept;
    }

    public String getApproverDept() 
    {
        return approverDept;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("traineeName", getTraineeName())
            .append("trainType", getTrainType())
            .append("trainName", getTrainName())
            .append("trainOrganizer", getTrainOrganizer())
            .append("trainStartDate", getTrainStartDate())
            .append("trainEndDate", getTrainEndDate())
            .append("trainDuration", getTrainDuration())
            .append("trainLocation", getTrainLocation())
            .append("trainProofDocument", getTrainProofDocument())
            .append("approvalStatus", getApprovalStatus())
            .append("approverName", getApproverName())
            .append("approverDept", getApproverDept())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
