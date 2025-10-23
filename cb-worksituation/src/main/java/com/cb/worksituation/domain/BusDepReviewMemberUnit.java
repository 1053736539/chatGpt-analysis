package com.cb.worksituation.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Transient;

import java.math.BigDecimal;

/**
 * 【请填写功能名称】对象 BUS_DEP_REVIEW_MEMBER_UNIT
 *
 * @author ruoyi
 * @date 2025-10-15
 */
@Data
public class BusDepReviewMemberUnit extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private String id;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String memberUnit;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String busDepReviewUnitNameId;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String memberOrder;

    /**
     * 得分
     */
    @Transient
    private BigDecimal score;


}
