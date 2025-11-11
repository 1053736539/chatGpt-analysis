package com.cb.assess.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description:
 * @Author ARPHS
 * @Date 2023/11/27 10:13
 */
public interface CadreDemocraticRecordVo {

    /**
     * 提交评价请求参数
     */
    @Data
    class SubmitReq{
        @NotBlank(message = "请选择评议记录")
        private String id;//记录id
        @NotBlank(message = "整体评价不能为空")
        private String overallEvaluation;//整体评价 1-优秀 2-称职 3-基本称职 4-不称职
        @NotBlank(message = "考德评价不能为空")
        private String deEvaluation;//德的评价 1-优秀 2-良好 3-一般 4-较差
        @NotBlank(message = "考能评价不能为空")
        private String nengEvaluation;//能的评价 1-优秀 2-良好 3-一般 4-较差
        @NotBlank(message = "考勤评价不能为空")
        private String qinEvaluation;//勤的评价 1-优秀 2-良好 3-一般 4-较差
        @NotBlank(message = "考绩评价不能为空")
        private String jiEvaluation;//绩的评价 1-优秀 2-良好 3-一般 4-较差
        @NotBlank(message = "考廉评价不能为空")
        private String lianEvaluation;//廉的评价 1-优秀 2-良好 3-一般 4-较差

        private String opinion;//意见建议
    }

}
