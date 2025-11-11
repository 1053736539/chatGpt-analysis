package com.cb.assess.domain;

import com.cb.assess.vo.CadreDemocraticTaskVo;
import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Transient;

import java.util.List;

/**
 * 年度处级干部民主测评任务对象 biz_assess_cadre_democratic_task
 * 
 * @author yangixn
 * @date 2023-11-25
 */
public class BizAssessCadreDemocraticTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 年度 */
    @Excel(name = "年度")
    private String year;

    private Long createDept;//创建部门

    private String reportFlag;//上报标记 0-未上报 1-已上报人事处

    private String reportDate;//上报日期 yyyy-MM-dd

    /** 以下为扩展字段 **/
    @Excel(name = "创建人名称")
    @Transient
    private String createByName;//创建人名称
    @Transient
    private String createDeptName;//创建部门名称
    @Transient
    private List<Long> assessedUserIds;//被评价人ids
    @Transient
    private List<Long> voteUserIds;//评价人ids
    @Transient
    private List<CadreDemocraticTaskVo.assessedUserInfo> assessedUserInfoList;//被评价人信息集合

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

    public Long getCreateDept() {
        return createDept;
    }

    public void setCreateDept(Long createDept) {
        this.createDept = createDept;
    }

    public String getReportFlag() {
        return reportFlag;
    }

    public void setReportFlag(String reportFlag) {
        this.reportFlag = reportFlag;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public List<Long> getAssessedUserIds() {
        return assessedUserIds;
    }

    public void setAssessedUserIds(List<Long> assessedUserIds) {
        this.assessedUserIds = assessedUserIds;
    }

    public List<Long> getVoteUserIds() {
        return voteUserIds;
    }

    public void setVoteUserIds(List<Long> voteUserIds) {
        this.voteUserIds = voteUserIds;
    }

    public String getCreateDeptName() {
        return createDeptName;
    }

    public void setCreateDeptName(String createDeptName) {
        this.createDeptName = createDeptName;
    }

    public List<CadreDemocraticTaskVo.assessedUserInfo> getAssessedUserInfoList() {
        return assessedUserInfoList;
    }

    public void setAssessedUserInfoList(List<CadreDemocraticTaskVo.assessedUserInfo> assessedUserInfoList) {
        this.assessedUserInfoList = assessedUserInfoList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("year", getYear())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
