package com.cb.worksituation.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.List;

/**
 * 部门评分对象 BUS_DEP_REVIEW
 *
 * @author ruoyi
 * @date 2025-10-11
 */
@Data
public class BusDepReview extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private String id;

    /**
     * 年份
     */
    @Excel(name = "年份")
    private String busYear;

    /**
     * 名称
     */
    @Excel(name = "名称")
    private String busName;

    /**
     * 所属科室部门
     */
    @Excel(name = "所属科室部门")
    private String divisionDept;

    /**
     * 评价对象
     */
    @Excel(name = "评价对象")
    private String evaluationTarget;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remarks;

    /**
     * 对应表头字段
     */
    @Transient
    private List<BusDepReviewHeader> busDepReviewHeaderList;

    /**
     * 表数据
     */
    @Transient
    private List<BusDepReviewData> busDepReviewDataList;
}
