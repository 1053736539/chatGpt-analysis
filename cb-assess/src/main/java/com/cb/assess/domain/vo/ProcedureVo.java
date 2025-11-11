package com.cb.assess.domain.vo;

import com.cb.assess.domain.BizAssessIndicatorProProcedure;
import com.cb.filemanage.domain.BizAttach;
import lombok.Data;

import java.util.List;

/**
 * @author ouyang
 * @date 2023-11-03
 */
@Data
public class ProcedureVo {
    List<BizAssessIndicatorProProcedure> procedureList;

    // 个人总结
    private Boolean enableSummary;
    // 是否已填写个人总结
    private Boolean isFillIn;

    // 互评的
    private Boolean enableEvaluate;

    private String proWorkTipFileId;//工作提示附件id

    private List<BizAttach> proWorkTipFile;//工作提示附件信息

}
