package com.cb.salary.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 工资条对象 salary
 *
 * @author hujilie
 * @date 2024-06-06
 */
public class Salary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 用户ID */
    private Long userId;

    /**
     * 人员编制类型：1-公务员，2-事业单位
     */
    private Integer userType;

    @Excel(name = "姓名")
    private String name;
    private String deptName;
    //用来查询部门的
    private String deptId;

    private List<String> months;

    /** 年度 */
    @Excel(name = "业务年度")
    private String year;

    /** 月份 */
    @Excel(name = "月份")
    private String month;

    /** 职务(岗位)工资 */
    @Excel(name = "职务（事业岗位）工资")
    private Double postSalary;

    /** 级别(薪级)工资 */
    @Excel(name = "级别（事业薪级）工资")
    private Double gradSalary;

    /** 工作性津贴 */
    @Excel(name = "工作性津贴（事业无）")
    private Double workSubsidy;

    /** 生活性补贴(奖励性绩效) */
    @Excel(name = "生活性补贴（事业奖励性绩效）")
    private Double lifeSubsidy;

    /** 改革性补贴 */
    @Excel(name = "改革性补贴")
    private Double reformSubsidy;

    /** 2017规范改革性补贴(奖励性补贴) */
    @Excel(name = "2017规范改革性补贴（事业奖励性补贴）")
    private Double reformSubsidy2017;

    /** 独生子女保健费 */
    @Excel(name = "独生子女保健费")
    private Double oneChildSubsidy;

    /** 回族补贴 */
    @Excel(name = "回族补贴")
    private Double huiNationSubsidy;

    /** 年终一次性奖金 */
    @Excel(name = "年终一次性奖金（事业无）")
    private Double yearlyBonus;

    /** 公务交通补贴 */
    @Excel(name = "公务交通补贴（事业无）")
    private Double trafficSubsidy;

    /** 基础绩效奖(基础性绩效) */
    @Excel(name = "基础绩效奖（事业基础性绩效）")
    private Double basicMerit;

    /** 年度考核奖(奖励性绩效) */
    @Excel(name = "年度考核奖（事业奖励性绩效）")
    private Double yearlyAssessAward;

    /** 其他 */
    @Excel(name = "其他")
    private Double other;

    /** 补发合计 */
    @Excel(name = "补发合计")
    private Double reissueSum;

    /** 补扣合计 */
    @Excel(name = "补扣合计")
    private Double rededuceSum;

    /** 应发项 */
    @Excel(name = "应发项")
    private Double payableItem;

    /** 住房公积金 */
    @Excel(name = "住房公积金")
    private Double housingFee;

    /** 养老保险费和职业年金 */
    @Excel(name = "养老保险费和职业年金")
    private Double retirementInsurance;

    /** 医疗保险费 */
    @Excel(name = "医疗保险费")
    private Double medicalInsurance;

    /** 所得税 */
    @Excel(name = "所得税")
    private Double incomeTax;

    /** 扣发工资 */
    @Excel(name = "扣发工资")
    private Double deducedSalary;

    /** 实发工资 */
    @Excel(name = "实发工资")
    private Double payedSalary;

    @Excel(name = "备注")
    private String remark;

    /** $column.columnComment */
    private String delFlag;

    /** 状态 */
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public List<String> getMonths() {
        return months;
    }

    public void setMonths(List<String> months) {
        this.months = months;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setYear(String year)
    {
        this.year = year;
    }

    public String getYear()
    {
        return year;
    }
    public void setMonth(String month)
    {
        this.month = month;
    }

    public String getMonth()
    {
        return month;
    }
    public void setPostSalary(Double postSalary)
    {
        this.postSalary = postSalary;
    }

    public Double getPostSalary()
    {
        return postSalary;
    }
    public void setGradSalary(Double gradSalary)
    {
        this.gradSalary = gradSalary;
    }

    public Double getGradSalary()
    {
        return gradSalary;
    }
    public void setWorkSubsidy(Double workSubsidy)
    {
        this.workSubsidy = workSubsidy;
    }

    public Double getWorkSubsidy()
    {
        return workSubsidy;
    }
    public void setLifeSubsidy(Double lifeSubsidy)
    {
        this.lifeSubsidy = lifeSubsidy;
    }

    public Double getLifeSubsidy()
    {
        return lifeSubsidy;
    }
    public void setReformSubsidy(Double reformSubsidy)
    {
        this.reformSubsidy = reformSubsidy;
    }

    public Double getReformSubsidy()
    {
        return reformSubsidy;
    }
    public void setReformSubsidy2017(Double reformSubsidy2017)
    {
        this.reformSubsidy2017 = reformSubsidy2017;
    }

    public Double getReformSubsidy2017()
    {
        return reformSubsidy2017;
    }
    public void setOneChildSubsidy(Double oneChildSubsidy)
    {
        this.oneChildSubsidy = oneChildSubsidy;
    }

    public Double getOneChildSubsidy()
    {
        return oneChildSubsidy;
    }
    public void setHuiNationSubsidy(Double huiNationSubsidy)
    {
        this.huiNationSubsidy = huiNationSubsidy;
    }

    public Double getHuiNationSubsidy()
    {
        return huiNationSubsidy;
    }
    public void setYearlyBonus(Double yearlyBonus)
    {
        this.yearlyBonus = yearlyBonus;
    }

    public Double getYearlyBonus()
    {
        return yearlyBonus;
    }
    public void setTrafficSubsidy(Double trafficSubsidy)
    {
        this.trafficSubsidy = trafficSubsidy;
    }

    public Double getTrafficSubsidy()
    {
        return trafficSubsidy;
    }
    public void setBasicMerit(Double basicMerit)
    {
        this.basicMerit = basicMerit;
    }

    public Double getBasicMerit()
    {
        return basicMerit;
    }
    public void setYearlyAssessAward(Double yearlyAssessAward)
    {
        this.yearlyAssessAward = yearlyAssessAward;
    }

    public Double getYearlyAssessAward()
    {
        return yearlyAssessAward;
    }
    public void setOther(Double other)
    {
        this.other = other;
    }

    public Double getOther()
    {
        return other;
    }
    public void setReissueSum(Double reissueSum)
    {
        this.reissueSum = reissueSum;
    }

    public Double getReissueSum()
    {
        return reissueSum;
    }
    public void setRededuceSum(Double rededuceSum)
    {
        this.rededuceSum = rededuceSum;
    }

    public Double getRededuceSum()
    {
        return rededuceSum;
    }
    public void setPayableItem(Double payableItem)
    {
        this.payableItem = payableItem;
    }

    public Double getPayableItem()
    {
        return payableItem;
    }
    public void setHousingFee(Double housingFee)
    {
        this.housingFee = housingFee;
    }

    public Double getHousingFee()
    {
        return housingFee;
    }
    public void setRetirementInsurance(Double retirementInsurance)
    {
        this.retirementInsurance = retirementInsurance;
    }

    public Double getRetirementInsurance()
    {
        return retirementInsurance;
    }
    public void setMedicalInsurance(Double medicalInsurance)
    {
        this.medicalInsurance = medicalInsurance;
    }

    public Double getMedicalInsurance()
    {
        return medicalInsurance;
    }
    public void setIncomeTax(Double incomeTax)
    {
        this.incomeTax = incomeTax;
    }

    public Double getIncomeTax()
    {
        return incomeTax;
    }
    public void setDeducedSalary(Double deducedSalary)
    {
        this.deducedSalary = deducedSalary;
    }

    public Double getDeducedSalary()
    {
        return deducedSalary;
    }
    public void setPayedSalary(Double payedSalary)
    {
        this.payedSalary = payedSalary;
    }

    public Double getPayedSalary()
    {
        return payedSalary;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("year", getYear())
            .append("month", getMonth())
            .append("postSalary", getPostSalary())
            .append("gradSalary", getGradSalary())
            .append("workSubsidy", getWorkSubsidy())
            .append("lifeSubsidy", getLifeSubsidy())
            .append("reformSubsidy", getReformSubsidy())
            .append("reformSubsidy2017", getReformSubsidy2017())
            .append("oneChildSubsidy", getOneChildSubsidy())
            .append("huiNationSubsidy", getHuiNationSubsidy())
            .append("yearlyBonus", getYearlyBonus())
            .append("trafficSubsidy", getTrafficSubsidy())
            .append("basicMerit", getBasicMerit())
            .append("yearlyAssessAward", getYearlyAssessAward())
            .append("other", getOther())
            .append("reissueSum", getReissueSum())
            .append("rededuceSum", getRededuceSum())
            .append("payableItem", getPayableItem())
            .append("housingFee", getHousingFee())
            .append("retirementInsurance", getRetirementInsurance())
            .append("medicalInsurance", getMedicalInsurance())
            .append("incomeTax", getIncomeTax())
            .append("deducedSalary", getDeducedSalary())
            .append("payedSalary", getPayedSalary())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("delFlag", getDelFlag())
            .append("status", getStatus())
            .toString();
    }
}
