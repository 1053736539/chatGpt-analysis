package com.cb.assess.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description: 年度机关事业单位考核人数及优秀等次名额分配表Vo
 * @Author ARPHS
 * @Date 2023/12/9 16:20
 */
public interface QuotaAllocateInfoVo {

    @Data
    class TableItemInfo{
        private String specialName;//特殊名称
        private Long deptId;//单位id
        private String deptName;//单位名称
        private List<Long> userIds;//参加考核人员名单
        private String deptType;//单位类型 机关/事业
        private Integer allocateNum;//名额数量
    }

}
