package com.cb.assess.domain;

import com.cb.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 考核方法程序对象 biz_assess_indicator_pro_procedure
 *
 * @author ouyang
 * @date 2023-11-03
 */
public class BizAssessIndicatorProProcedure {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private String procedureId;

    /**
     * 方案ID
     */
    @Excel(name = "方案ID")
    private String proId;

    /**
     * 名称
     */
    @Excel(name = "名称")
    private String name;

    /**
     * 步骤
     */
    @Excel(name = "步骤")
    private Integer step;

    /**
     * 是否强制执行才能到下一步
     */
    @Excel(name = "是否强制执行才能到下一步")
    private String enforce;

    /**
     * 描述
     */
    @Excel(name = "描述")
    private String describe;


    public String getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(String procedureId) {
        this.procedureId = procedureId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProId() {
        return proId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getStep() {
        return step;
    }

    public void setEnforce(String enforce) {
        this.enforce = enforce;
    }

    public String getEnforce() {
        return enforce;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }
}
