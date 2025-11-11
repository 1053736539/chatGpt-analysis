package com.cb.assess.domain.vo;

import com.cb.assess.domain.BizAssessIndicator;
import com.cb.assess.domain.BizAssessIndicatorPro;
import com.cb.assess.domain.BizAssessRegularInfo;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.task.vo.TaskInfoVo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 *评价打分的对象
 */

@Data
public class EvaluateItemVo {

    private SysUser user;

    private BizAssessRegularInfo regular;

    private List<IsmConfig> ismConfigList;
    private BizAssessIndicatorPro pro;
    // 任务管理数据来源
    private TaskInfoVo.UserHandle4EvaluationResp userHandle4EvaluationResp;
    @Data
    public static class IsmConfig{
        // 指标配置汉字名称
        private String indicatorLabel;

        private String ismConfigId;
        // 指标标准集合
        private List<BizAssessIndicator> indicators;

        //分值比重
        private Integer scoreWeight;

        private BigDecimal score;
    }
}
