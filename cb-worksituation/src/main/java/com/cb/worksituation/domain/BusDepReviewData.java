package com.cb.worksituation.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 评分数据对象 BUS_DEP_REVIEW_DATA
 *
 * @author ruoyi
 * @date 2025-10-11
 */
@Data
public class BusDepReviewData extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private String id;

    /**
     * 部门评分表ID
     */
    @Excel(name = "部门评分表ID")
    private String busDepReviewId;

    /**
     * 评价对象
     */
    @Excel(name = "评价对象")
    private String evaluatTarget;

    /**
     * 角色定位
     */
    @Excel(name = "角色定位")
    private String rolePosit;

    /**
     * 数据
     */
    @Excel(name = "数据")
    private String dataJson;

    /**
     * 业务评价得分
     */
    @Excel(name = "业务评价得分")
    private BigDecimal reviewScore;

    /**
     * 建业务协作单元党
     */
    @Excel(name = "建业务协作单元党")
    private BigDecimal busUnitScore;

    /**
     * （1-草稿，2-提交）
     */
    @Excel(name = "", readConverterExp = "1=-草稿，2-提交")
    private String dataStatus;

    /**
     * 定量评价得分（70分)小计
     */
    private String subtotalQuanScore;

    /**
     * 加分小计
     */
    private String bonusSubtotal;


    /**
     * 文件路径
     */
    @Excel(name = "文件路径")
    private String filePath;


    /**
     * 文件索引id
     */
    @Excel(name = "文件索引id")
    private String attachId;

}
