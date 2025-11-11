package com.cb.assess.domain.vo;

import com.cb.assess.domain.BizAssessPersonalSummary;

public class BizAssessPersonalSummaryVo extends BizAssessPersonalSummary {
    private String name;
    private String deptName;
    //接口数据类型，1为自己，2为全部，3为部门，如果为空，那就按照自己的数据
    private String dataType;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

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
}
