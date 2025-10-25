package com.cb.system.vo;

import com.cb.common.core.domain.entity.SysUser;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description:
 * @Author: ARPHS
 * @Date: 2025-05-15 17:45
 * @Version: 1.0
 **/
public interface SysUserInfoChangeApplyVo {

    @Data
    class ApprovalReq {
        @NotEmpty(message = "要审批的记录不能为空")
        private List<String> ids;
        @NotBlank(message = "审批状态不能为空")
        private String status;
        private String approvalOpinion;
    }

    /**
     * 重新申请的请求参数
     */
    @Data
    class ReApplyReq{
        @NotBlank(message = "重新申请的记录不能为空")
        private String id;
        @NotNull(message = "重新申请的数据不能为空")
        private SysUser user;
    }
}
