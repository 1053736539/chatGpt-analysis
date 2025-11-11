package com.cb.assess.domain.vo;

import com.cb.assess.domain.BizAssessCadrePoliticalQualityIdentify;

public class BizAssessCadrePoliticalQualityIdentifyVo extends BizAssessCadrePoliticalQualityIdentify {
    private String name;
    private String deptName;

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
