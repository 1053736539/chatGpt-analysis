package com.cb.adopt.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 信息采用记录对象 biz_adopt_record
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
public class BizAdoptRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private String id;

    /** 期号 */
    @Excel(name = "期号")
    private String issueNo;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 采用级别 */
    @Excel(name = "采用级别")
    private String level;

    /** 采用载体 */
    @Excel(name = "采用载体")
    private String carrier;

    /** 采用时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "采用时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date adoptDate;

    /** 附件id */
    private String attach;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    /** 内容 */
    private String destination;

    private Date startAdoptDate;

    private Date endAdoptDate;
   /** 单位名称 */
    private String deptName;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    private List<BizAdoptRecordScore> bizAdoptRecordScoreList;

    public List<BizAdoptRecordScore> getBizAdoptRecordScoreList() {
        return bizAdoptRecordScoreList;
    }

    public void setBizAdoptRecordScoreList(List<BizAdoptRecordScore> bizAdoptRecordScoreList) {
        this.bizAdoptRecordScoreList = bizAdoptRecordScoreList;
    }

    public Date getStartAdoptDate() {
        return startAdoptDate;
    }

    public void setStartAdoptDate(Date startAdoptDate) {
        this.startAdoptDate = startAdoptDate;
    }

    public Date getEndAdoptDate() {
        return endAdoptDate;
    }

    public void setEndAdoptDate(Date endAdoptDate) {
        this.endAdoptDate = endAdoptDate;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setIssueNo(String issueNo) 
    {
        this.issueNo = issueNo;
    }

    public String getIssueNo() 
    {
        return issueNo;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setLevel(String level) 
    {
        this.level = level;
    }

    public String getLevel() 
    {
        return level;
    }
    public void setCarrier(String carrier) 
    {
        this.carrier = carrier;
    }

    public String getCarrier() 
    {
        return carrier;
    }
    public void setAdoptDate(Date adoptDate) 
    {
        this.adoptDate = adoptDate;
    }

    public Date getAdoptDate() 
    {
        return adoptDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("issueNo", getIssueNo())
            .append("title", getTitle())
            .append("level", getLevel())
            .append("carrier", getCarrier())
            .append("adoptDate", getAdoptDate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
