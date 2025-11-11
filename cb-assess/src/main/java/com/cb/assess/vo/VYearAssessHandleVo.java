package com.cb.assess.vo;

import lombok.Data;

/**
 *  年度考核相关任务合并 实体
 */
public interface VYearAssessHandleVo {

    @Data
    class Req{
        private String belongYear;
        private Long voterId;//处理人id
        private String handleStatus; //0 未处理 1 已处理
        private Integer bizType; //1 优秀互评 2 A类评议表 3 B类评议表 4 专项测评 5 民主测评
    }

    @Data
    class HandleInfo{
        //年度
        private String belongYear;
        private String taskName;
        private Long voterId;
        //0 未处理 1 已处理
        private String handleStatus;
        //1 优秀互评 2 A类评议表 3 B类评议表 4 专项测评 5 民主测评
        private Integer bizType;
    }

}
