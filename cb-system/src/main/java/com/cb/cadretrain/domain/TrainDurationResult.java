package com.cb.cadretrain.domain;

/**
 * @Author: yangcd
 * @Date: 2023-11-01-14:11
 * @Description:
 */
public class TrainDurationResult {
    private String quarterName;
    private String totalDuration;

    public String getQuarterName() {
        return quarterName;
    }

    public void setQuarterName(String quarterName) {
        this.quarterName = quarterName;
    }

    public String getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(String totalDuration) {
        this.totalDuration = totalDuration;
    }
}
