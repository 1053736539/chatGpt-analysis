package com.cb.probity.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 廉政档案-10.本人工资及各类奖金、津贴、补贴情况对象 biz_probity_salary
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public class BizProbitySalary extends BaseEntity {
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
     * 工资（含津贴、补贴）
     */
    @Excel(name = "工资", readConverterExp = "含=津贴、补贴")
    private String salary;

    /**
     * 奖金
     */
    @Excel(name = "奖金")
    private String bonus;

    /**
     * 其他
     */
    @Excel(name = "其他")
    private String other;

    /**
     * 合计
     */
    @Excel(name = "合计")
    private String total;

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

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSalary() {
        return salary;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getBonus() {
        return bonus;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getOther() {
        return other;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
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
                .append("salary", getSalary())
                .append("bonus", getBonus())
                .append("other", getOther())
                .append("total", getTotal())
                .toString();
    }
}
