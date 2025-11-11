package com.cb.assess.domain;

import java.math.BigDecimal;
import java.util.List;

import com.cb.common.core.domain.BaseEntity;
import com.cb.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Transient;

/**
 * 指标体系对象 biz_assess_indicator_ism
 *
 * @author ouyang
 * @date 2023-10-27
 */
@Data
public class BizAssessIndicatorIsm extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String ismId;

    /** 名称 */
    @Excel(name = "名称")
    private String ismName;

    /** 年份 */
    @Excel(name = "年份")
    @JsonFormat(pattern = "yyyy")
    private String ismYear;

    /** 删除标记（字典） */
    private String delFlag;

    /** 状态（字典） */
    @Excel(name = "状态", readConverterExp = "字=典")
    private String status;

    @Transient
    private BigDecimal maxScore;

    /**指标配置*/
    private List<BizAssessIndicatorIsmConfig> configList;
    /**
     * 编辑是否可以修改
     */
    private Boolean enableEdit = true;
}