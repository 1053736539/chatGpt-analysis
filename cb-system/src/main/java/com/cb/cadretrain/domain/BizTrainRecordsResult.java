package com.cb.cadretrain.domain;

/**
 * @Author: yangcd
 * @Date: 2023-11-06-15:24
 * @Description:
 */
public class BizTrainRecordsResult extends BizTrainRecords{
    private String totalDuration;

    private String traineeDeptName;

    public String getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(String totalDuration) {
        this.totalDuration = totalDuration;
    }

    public String getTraineeDeptName() {
        return traineeDeptName;
    }

    public void setTraineeDeptName(String traineeDeptName) {
        this.traineeDeptName = traineeDeptName;
    }
}
