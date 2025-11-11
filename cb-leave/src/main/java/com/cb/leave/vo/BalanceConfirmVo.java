package com.cb.leave.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @Author: ARPHS
 * @Date: 2025-03-19 16:20
 * @Version: 1.0
 **/
public interface BalanceConfirmVo {

    @Data
    class ConfirmReq{
        @NotEmpty(message = "请选择要确认的记录！")
        private Integer[] ids;
        private Long leaderId;
    }

    @Data
    class ApproveReq{
        @NotEmpty(message = "请选择要审批的记录！")
        Integer[] ids;
        @NotNull(message = "请选择审批状态！")
        @Range(min = 3, max = 4, message = "审批状态不正确！")
        Integer status;
        String approvalOpinion;//审批意见
    }

}
