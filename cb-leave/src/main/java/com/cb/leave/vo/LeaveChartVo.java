package com.cb.leave.vo;

import com.cb.common.annotation.Excel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Description:
 * @Author: ARPHS
 * @Date: 2025-01-22 09:28
 * @Version: 1.0
 **/
public interface LeaveChartVo {

    @Data
    class HomeChartReq{
        @NotBlank(message = "请选择年度")
        private String year;

//        @NotBlank(message = "请选择部门")
        private long deptId;

        private String identityType;//编制类型 1=行政,2=参公,3=事业,4=企业
    }

    @Data
    class HomeChartResp{
        private float totalUsedDays;//总已休天数
        private float totalRemainingDays;//总未休天数
        private List<HomeChartItem> itemList;//数据
    }

    @Data
    class HomeChartItem {
        private Long deptId;
        private String label;
        private float usedDays;//已休天数
        private float remainingDays;//剩余天数
    }

    /**
     * 导出柱状图数据
     */
    @Data
    class ExportBarDataItem{
        @Excel(name = "部门名称")
        private String deptName;
        @Excel(name = "总天数")
        private float totalDays;
        @Excel(name = "已休")
        private float totalUsedDays;
        @Excel(name = "未休")
        private float totalRemainingDays;
        @Excel(name = "公休率")
        private String usedRate;

        public ExportBarDataItem(HomeChartItem item){
            this.deptName = item.getLabel();
            this.totalUsedDays = item.getUsedDays();
            this.totalRemainingDays = item.getRemainingDays();
            this.totalDays = this.totalUsedDays + this.totalRemainingDays;
            if(this.totalUsedDays <= 0 || this.totalDays <= 0){
                this.usedRate = "0%";
            } else {
                this.usedRate = String.format("%.0f", item.getUsedDays() / (totalDays) * 100) + "%";
            }
        }

    }

}
