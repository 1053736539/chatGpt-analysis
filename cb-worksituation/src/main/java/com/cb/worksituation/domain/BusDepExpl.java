package com.cb.worksituation.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 评分说明对象 BUS_DEP_EXPL
 *
 * @author ruoyi
 * @date 2025-10-11
 */
public class BusDepExpl extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 所属科室部门
     * 1-县（市）区纪委监委
     * 2-委机关监督检查室
     * 3-委机关审查调查室
     * 4-委机关综合业务部门
     * 5-开发（度假）园区
     * 6-纪检监察组
     * */
    @Excel(name = "所属科室部门")
    private String divisionDept;

    /** 一级指标 */
    @Excel(name = "一级指标")
    private String firstLevel;

    /** 二级指标 */
    @Excel(name = "二级指标")
    private String twoLevel;

    /** 分值 */
    @Excel(name = "分值")
    private Integer score;

    /** 评价部门 */
    @Excel(name = "评价部门")
    private String evaluationDept;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setDivisionDept(String divisionDept)
    {
        this.divisionDept = divisionDept;
    }

    public String getDivisionDept()
    {
        return divisionDept;
    }
    public void setFirstLevel(String firstLevel)
    {
        this.firstLevel = firstLevel;
    }

    public String getFirstLevel()
    {
        return firstLevel;
    }
    public void setTwoLevel(String twoLevel)
    {
        this.twoLevel = twoLevel;
    }

    public String getTwoLevel()
    {
        return twoLevel;
    }
    public void setScore(Integer score)
    {
        this.score = score;
    }

    public Integer getScore()
    {
        return score;
    }
    public void setEvaluationDept(String evaluationDept)
    {
        this.evaluationDept = evaluationDept;
    }

    public String getEvaluationDept()
    {
        return evaluationDept;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("divisionDept", getDivisionDept())
            .append("firstLevel", getFirstLevel())
            .append("twoLevel", getTwoLevel())
            .append("score", getScore())
            .append("evaluationDept", getEvaluationDept())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
