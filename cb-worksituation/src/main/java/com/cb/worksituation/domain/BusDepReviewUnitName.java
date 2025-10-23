package com.cb.worksituation.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;

import java.math.BigDecimal;
import java.util.List;

/**
 * 【请填写功能名称】对象 BUS_DEP_REVIEW_UNIT_NAME
 *
 * @author ruoyi
 * @date 2025-10-15
 */
@Data
public class BusDepReviewUnitName extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private String id;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String unitName;

    /**
     * 合计
     */
    @Transient
    private BigDecimal total;

    /**
     * 平均分
     */
    @Transient
    private BigDecimal avgScore;

    @Transient
    private List<BusDepReviewMemberUnit> busDepReviewMemberUnits;

}
