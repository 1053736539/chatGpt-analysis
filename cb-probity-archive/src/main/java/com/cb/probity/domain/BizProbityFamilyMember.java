package com.cb.probity.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）对象 biz_probity_family_member
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public class BizProbityFamilyMember extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * probity_id
     */
    @Excel(name = "probity_id")
    private String probityId;

    /**
     * 关系
     */
    @Excel(name = "关系")
    private String relation;

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String name;

    /**
     * 出生年月
     */
    @Excel(name = "出生年月")
    private String birth;

    /**
     * 政治面貌
     */
    @Excel(name = "政治面貌")
    private String politicalStatus;

    /**
     * 工作单位及职务
     */
    @Excel(name = "工作单位及职务")
    private String workDeptPosition;

    /**
     * 身份证号码
     */
    @Excel(name = "身份证号码")
    private String idNo;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setProbityId(String probityId) {
        this.probityId = probityId;
    }

    public String getProbityId() {
        return probityId;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getRelation() {
        return relation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getBirth() {
        return birth;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setWorkDeptPosition(String workDeptPosition) {
        this.workDeptPosition = workDeptPosition;
    }

    public String getWorkDeptPosition() {
        return workDeptPosition;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdNo() {
        return idNo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("probityId", getProbityId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("relation", getRelation())
                .append("name", getName())
                .append("birth", getBirth())
                .append("politicalStatus", getPoliticalStatus())
                .append("workDeptPosition", getWorkDeptPosition())
                .append("idNo", getIdNo())
                .toString();
    }
}
