package com.cb.task.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Description:
 * @Author ARPHS
 * @Date 2023/11/20 9:42
 */
public interface TaskStatisticsVo {

    /**
     * 子级（一级部门/指定部门下的人） 已完成/执行中 数量统计请求参数
     */
    @Data
    class HandleNumCountReq {
        private Long deptId;//部门id 不给则查全单位的子级部门，给了就查对应部门下人员的
        private String beginTime;//起止时间 yyyy-MM-dd HH:mm:ss
        private String endTime;//截止时间 yyyy-MM-dd HH:mm:ss
    }

    @Data
    class HandleNumCountResp {

        private List<HandleCountItem> finishList;//已完成

        private List<HandleCountItem> executingList;//执行中

        private Long finishTotal;//已完成总数

        private Long executingTotal;//未完成总数

    }

    @Data
    class HandleCountItem {
        private String label;//显示名称
        private Long num;//数量
        private Long deptId;//部门id 为部门数据时有值
        private String userName;//用户登录名 为用户数据时有值
        private Long userId;//用户id
        private String type;// dept-部门 user-用户
    }

    @Data
    class TaskCountReq {
        private Long deptId;//部门id 不给则查所有，给了就查对应部门
        private String beginTime;//起止时间 yyyy-MM-dd HH:mm:ss
        private String endTime;//截止时间 yyyy-MM-dd HH:mm:ss
    }

    @Data
    class TaskCountResp {
        private List<TaskCountItem> causationList;//来源
        private List<TaskCountItem> docTypeList;//分类
        private List<TaskCountItem> difficultList;//难度
    }

    @Data
    class TaskCountItem{
        private String label;//显示名
        private Long num;//数量
        private String groupId;//group分组策略对应的id
    }

    /**
     * 忙闲分析经办数量统计
     */
    @Data
    class MxfxHandleNumReq{
        private Long deptId;//部门id
        @NotBlank(message = "年度不能为空！")
        private String year;//年度
        @Range(min = 1,max = 4,message = "季度应在{min}-{max}之间")
        private Integer quarter;//季度
    }

    @Data
    class MxfxHandleNumResp{
        @JsonProperty("xData")
        private List<String> xData;//x轴名称
        private List<MxfxHandleNumItem> data;//数据
    }

    @Data
    class MxfxHandleNumItem{
        private String label;//series名称
        private List<Long> list;//y轴数据
        private Long sum;//合计
    }

    @Data
    class MxfxHandleNumDeptItem{
        private Long deptId;
        private String deptName;
        private String yearMonth;
        private Long num;
    }

    @Data
    class MxfxHandleNumUserItem{
        private String userName;
        private String name;
        private String yearMonth;
        private Long num;
    }

}
